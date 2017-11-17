package database.query.driver;

import java.util.ArrayList;
import java.util.HashMap;
import database.query.*;
import util.StorableCollection;

public class StorableCollectionDriver<T> extends GenericStorageDriver{

  @Override
  public String toSelectSQL(StorableInterface obj) throws StorageDriverException{
    StorableCollection<T> sc = (StorableCollection<T>)obj;
    return "SELECT * FROM " + obj.getStorageLocation() + " WHERE id = " + sc.getId() + ";";
  }

  @Override
  public void fromSQL(ArrayList<HashMap<String, Object>> queryResults, StorableInterface obj)
      throws StorageDriverException{
      StorableCollection<T> sc = (StorableCollection<T>)obj;
      sc.clear();
      String [] columnMap = sc.getColumnMap();

      if(columnMap != null){
        for(HashMap<String,Object> row : queryResults){
          for(String mapping : columnMap){
            sc.add((T)row.get(mapping));
          }
        }
      }
      else{
        throw new StorageDriverException("No Column mapping set for StorableCollection!");
      }
  }
}
