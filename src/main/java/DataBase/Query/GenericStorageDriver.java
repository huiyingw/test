package database.query;

import java.util.ArrayList;
import java.util.HashMap;

public class GenericStorageDriver extends StorageDriverBase{

    // dumb convert of storage values to sql insert statment.
    @Override
    public String toInsertSQL(StorableInterface obj) throws StorageDriverException{
      ArrayList<Object> values = obj.getStorageValues();
      String sqlQuery = "INSERT INTO " + obj.getStorageLocation() + " VALUES (";
      sqlQuery += arrayListToCSV(values);
      return sqlQuery + ");";
    }

    // NOT IMPLEMENTED
    @Override
    public String toSelectSQL(StorableInterface obj) throws StorageDriverException{
      throw new StorageDriverException("Generic Storage Driver cannot convert storable to selection SQL");
    }

    // NOT IMPLEMENTED
    @Override
    public void fromSQL(ArrayList<HashMap<String, Object>> queryResults, StorableInterface obj)
        throws StorageDriverException{
      throw new StorageDriverException("Generic Storage Driver cannot load from SQL");
    }
}
