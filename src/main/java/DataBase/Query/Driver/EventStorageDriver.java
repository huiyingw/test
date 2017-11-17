package database.query.driver;

import java.util.ArrayList;
import java.util.HashMap;
import database.query.*;
import database.jdbc.JDBCException;
import database.api.DefaultDBInterface;
import domain.entityclasses.Event;
import domain.entityclasses.Employee;
import domain.entityclasses.Equipment;
import domain.entityclasses.AssetType;
import domain.entityclasses.AssetTypeMap;
import util.StorableCollection;

public class EventStorageDriver extends StorageDriverBase{

  // convert storable object to insertion sql code
  @Override
  public String toInsertSQL(StorableInterface obj) throws StorageDriverException{
    ArrayList<Object> vals = obj.getStorageValues();

    //store assigned assets
    ArrayList<AssetType> assets = (ArrayList<AssetType>)vals.get(3);
    try{
      storeAssetsArray(assets,(Long)vals.get(0),obj.getStorageLocation()+"_resources");
    }
    catch(JDBCException e){
      throw new StorageDriverException("Could not store assets assigned to this event: " + e);
    }

    String rSQL = "INSERT INTO " + obj.getStorageLocation() + " VALUES (" + arrayListToCSV(vals.subList(0,vals.size()-1)) + ");";
    return rSQL;
  }

  // convert storable object to selection SQL (as to load object from database).
  @Override
  public String toSelectSQL(StorableInterface obj) throws StorageDriverException{
    ArrayList<Object> valsToStore = obj.getStorageValues();
    return "SELECT * FROM " + obj.getStorageLocation() + " WHERE id = " + valsToStore.get(0) + ";";
  }

  // initilize object from SQL results
  @Override
  public void fromSQL(ArrayList<HashMap<String, Object>> queryResults, StorableInterface obj)
      throws StorageDriverException{
      HashMap<String,Object> row1 = queryResults.get(0);
      Event event = (Event)obj;

      //load assets
      // NOTE, Number is used instead of Long due to database type swapping. e.i. SQLITE will
      // return java.lang.Integer while MYSQL will return java.lang.Long
      StorableCollection<Number> assets = new StorableCollection<Number>(new ArrayList<Number>()
          ,event.getStorageLocation()+"_resources",event.getId(),2);
      try{
        event.setAssigned(loadAssetsArray(assets));
      }
      catch(JDBCException e){
        throw new StorageDriverException("Could not load Assets assigned to this event: " + e);
      }

      event.setName(row1.get("event_name").toString());
  }

  // store an array of Assets in to the DB.
  private void storeAssetsArray(ArrayList<AssetType> ass, long event_id, String storage_location) throws JDBCException, StorageDriverException{
    if(ass != null){
      ArrayList<Long> assetIds = new ArrayList<>();
      for( AssetType at : ass){
        assetIds.add(at.getId());
        assetIds.add(at.getAssetType());
      }

      StorableCollection<Long> aStore = new
          StorableCollection<Long>(assetIds, storage_location, event_id, 2);
      DefaultDBInterface.getInstance().store(aStore);
    }
  }

  private ArrayList<AssetType> loadAssetsArray(StorableCollection<Number> sc) throws JDBCException, StorageDriverException{
    ArrayList<AssetType> assets = new ArrayList<>();

    String [] mapping = {"resource_id","asset_type"};
    sc.setColumnMap(mapping);
    DefaultDBInterface dbI = DefaultDBInterface.getInstance();
    dbI.load(sc);

    for(int i =0;i < sc.size();i +=2){

      long assetType = sc.get(i+1).longValue();
      long objectId = sc.get(i).longValue();

      //TODO employee and equipment should be loaded from a repo in the domain layer
      // NOT directly loaded here. but, you know said repo doesn't exist yet so...
      if(assetType == AssetTypeMap.getAssetId("Employee")){
        //employee asset
        Employee tmpEmployee = new Employee("??",objectId);
        dbI.load(tmpEmployee);
        assets.add((AssetType)tmpEmployee);
      }
      else{
        //equipment of some sort.
        Equipment tmpEquip = new Equipment();
        tmpEquip.setId(objectId);
        dbI.load(tmpEquip);
        assets.add((AssetType)tmpEquip);
      }
    }
    return assets;
  }

}
