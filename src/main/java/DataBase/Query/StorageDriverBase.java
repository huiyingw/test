package database.query;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

// driver that knows how to convert a object or objects
// to/from SQL querys.
public abstract class StorageDriverBase{

  // convert storable object to insertion sql code
  public abstract String toInsertSQL(StorableInterface obj) throws StorageDriverException;

  // convert storable object to selection SQL (as to load object from database).
  public abstract String toSelectSQL(StorableInterface obj) throws StorageDriverException;

  // initilize object from SQL results
  public abstract void fromSQL(ArrayList<HashMap<String, Object>> queryResults, StorableInterface obj)
      throws StorageDriverException;


  protected String arrayListToCSV(List<Object> arr){
    String csv = "";
    for(int i =0; i < arr.size();i++){
      String tmpStr = arr.get(i).toString();
      if(!isNumeric(tmpStr)){
        tmpStr = "\"" + tmpStr + "\"";
      }
      csv += tmpStr;

      if(i + 1 < arr.size()){
        csv += ",";
      }
    }
    return csv;
  }

  private boolean isNumeric(String s) {
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");
  }
}
