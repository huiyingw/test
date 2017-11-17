package database.api;

import java.util.function.Consumer;
import java.util.HashMap;
import java.util.ArrayList;

import database.query.StorableInterface;
import database.query.StorageDriverBase;
import database.query.StorageDriverException;
import database.jdbc.JDBCConnectionManager;
import database.jdbc.JDBCConnectionType;
import database.jdbc.JDBCException;


public class DefaultDBInterface implements DBInterface{

    protected DefaultDBInterface() throws JDBCException{}

    // singleton instance
    private static DefaultDBInterface ptrDefaultDBInterface = null;
    //get singleton
    public static DefaultDBInterface getInstance() throws JDBCException{
      if(ptrDefaultDBInterface == null){
        ptrDefaultDBInterface = new DefaultDBInterface();
        ptrDefaultDBInterface.conManager = ptrDefaultDBInterface.connectToDB();
      }
      return ptrDefaultDBInterface;
    }

    protected JDBCConnectionManager connectToDB() throws JDBCException{
      return JDBCConnectionManager.getInstance(JDBCConnectionType.MYSQL);
    }

    // store the passed object to the data base (see StorableInterface for more info)
    @Override
    public void store(StorableInterface objToStore) throws JDBCException, StorageDriverException{
      StorageDriverBase storageD = objToStore.getStorageDriver();
      conManager.query(storageD.toInsertSQL(objToStore));
    }

    // load the passed object from the data base. (see StorableInterface for more info)
    @Override
    public void load(StorableInterface objToLoad) throws JDBCException, StorageDriverException{
      StorageDriverBase storageD = objToLoad.getStorageDriver();
      ArrayList<HashMap<String, Object>> res = conManager.query(storageD.toSelectSQL(objToLoad));
      storageD.fromSQL(res,objToLoad);
    }

    // accepts a lambda function that will be passed the JDBCConnectionManager object
    // this lambda can do what ever it wants but, it is expected that it perform DB specific
    // initialization functions such as creating DATABASEs / TABLEs .... etc.
    @Override
    public void initialize(Consumer<JDBCConnectionManager> initFunction) throws JDBCException{
      initFunction.accept(conManager);
    }

    protected JDBCConnectionManager conManager;
}
