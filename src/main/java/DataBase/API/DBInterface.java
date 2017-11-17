package database.api;

import java.util.function.Consumer;
import database.query.StorableInterface;
import database.query.StorageDriverException;
import database.jdbc.JDBCConnectionManager;
import database.jdbc.JDBCException;
import database.jdbc.JDBCConnectionType;

// interface used to interact with the data base.
public interface DBInterface {

  // store the passed object to the data base (see StorableInterface for more info)
  public abstract void store(StorableInterface objToStore) throws JDBCException, StorageDriverException;

  // load the passed object from the data base. (see StorableInterface for more info)
  public abstract void load(StorableInterface objToLoad) throws JDBCException, StorageDriverException;

  // accepts a lambda function that will be passed the JDBCConnectionManager object
  // this lambda can do what ever it wants but, it is expected that it perform DB specific
  // initialization functions such as creating DATABASEs / TABLEs .... etc.
  public abstract void initialize(Consumer<JDBCConnectionManager> initFunction) throws JDBCException;
}
