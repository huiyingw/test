package database.query;

import java.util.ArrayList;

// implement this, and you can pass the implementing
// class in to storage methods to store it in the db :)
public interface StorableInterface{

  // return the storage driver for this object.
  public abstract StorageDriverBase getStorageDriver();

  // return array list of storage values in column order
  // each of said values should be able to produce a SQL readable / logical
  // string from its toString() method. (for example date object may need to be reformated).
  public abstract ArrayList<Object> getStorageValues();

  // return the name of the storage location in which this object should be stored
  // i.e. the SQL table name
  public abstract String getStorageLocation();
}
