package domain.entityclasses;

import java.util.*;
import database.query.StorableInterface;
import database.query.StorageDriverBase;
import database.query.driver.EquipmentStorageDriver;

public class Equipment implements AssetType, StorableInterface{

	public Equipment() {}

	//new Equipment object with name and quantity, none currently in use
	public Equipment(String s) {
		this.name = s;
	}

	public void setName(String s) {
		name = s;
	}

  public String getName(){
    return name;
  }

  public void setSchedule(Schedule s){
    mySchedule = s;
  }

  public ArrayList<Schedulable> getAllScheduleItems(){
    return mySchedule != null ? mySchedule.dumpSchedule() : null;
  }
  //TODO more specific accessor functions

  // AssetType Interface --------------------------
  @Override
  public long getAssetType(){
    return AssetTypeMap.getAssetId(assetName);
  }

  @Override
  public void setId(long id){
    equipmentId = id;
  }

  @Override
  public long getId(){
    return equipmentId;
  }
  /////////////////////////////////////////////////
  // StorableInterface -----------------------------
  @Override
  public StorageDriverBase getStorageDriver(){
    return new EquipmentStorageDriver();
  }
  @Override
  public ArrayList<Object> getStorageValues(){
    ArrayList<Object> vals = new ArrayList<>();
    vals.add((Object)equipmentId);
    vals.add((Object)AssetTypeMap.getAssetId(assetName));
    vals.add((Object)0l);//TODO Schedule id goes here
    vals.add((Object)name);
    return vals;
  }

  @Override
  public String getStorageLocation(){
    return "equipment";
  }
  ////////////////////////////////////////////////////

  private String name;
  private String assetName = "null";
  private long equipmentId;
  private Schedule mySchedule = null;
}
