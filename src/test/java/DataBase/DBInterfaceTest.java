import org.junit.*;

import database.api.DebugDBInterface;
import database.query.StorableInterface;
import database.query.StorageDriverBase;
import database.query.StorageDriverException;
import database.jdbc.JDBCException;

import domain.entityclasses.Employee;
import domain.entityclasses.Equipment;
import domain.entityclasses.Event;
import domain.entityclasses.AssetType;
import web.Account;

import java.util.ArrayList;
import java.util.HashMap;

public class DBInterfaceTest{

  public DBInterfaceTest(){
    //nop
  }

  @Test
  public void storeLoadTest()throws Exception {
    DebugDBInterface dbI = DebugDBInterface.getInstance();
    dbI.initialize((q) -> {
      try{
        q.query("CREATE TABLE IF NOT EXISTS foobar (id INTEGER, x INTEGER);");
      }
      catch(Exception e){
        // cannot throw exceptions from lambdas :(
        // why java... why you do this!!!!!!
      }
    });

    Thing thing1 = new Thing();
    thing1.id = 420;
    thing1.x = 42;
    dbI.store(thing1);

    Thing thing2 = new Thing();
    thing2.id = 420;
    dbI.load(thing2);

    assert(thing2.x.equals(thing1.x));
  }

  @Test
  public void employeeDB()throws Exception {
    DebugDBInterface dbI = DebugDBInterface.getInstance();
    dbI.NUKE();
    dbI.initializeDB();
    Employee frank = new Employee();
    frank.setName("Frank");
    frank.setId(1234);

    dbI.store(frank);
    frank.setName("NotFrank");

    dbI.load(frank);
    assert(frank.getName().equals("Frank"));
  }

  @Test
  public void equipmentDB()throws Exception {
    DebugDBInterface dbI = DebugDBInterface.getInstance();
    dbI.NUKE();
    dbI.initializeDB();
    Equipment food = new Equipment();
    food.setName("Burger");
    food.setId(2894);

    dbI.store(food);
    food.setName("Spinich");

    dbI.load(food);
    assert(food.getName().equals("Burger"));
  }

  @Test
  public void eventDB()throws Exception {
    DebugDBInterface dbI = DebugDBInterface.getInstance();
    dbI.NUKE();
    dbI.initializeDB();
    Event event = new Event();
    event.setName("Party_My_House");
    event.setId(1337);

    Employee bob = new Employee();
    bob.setId(789);
    bob.setName("Bob");
    ArrayList<AssetType> emps = new ArrayList<>();
    emps.add(bob);
    event.setAssigned(emps);

    dbI.store(bob);
    dbI.store(event);
    event.setName("no party");
    event.setAssigned(null);

    dbI.load(event);
    assert(event.getName().equals("Party_My_House"));
    assert(event.getAssigned().get(0).equals(bob));
    assert(((Employee)event.getAssigned().get(0)).getName().equals(bob.getName()));
  }

  @Test
  public void accountDB()throws Exception {
    DebugDBInterface dbI = DebugDBInterface.getInstance();
    dbI.NUKE();
    dbI.initializeDB();
    Account acc = new Account();
    acc.setuserID("bill");
    acc.setuserType(1);
    acc.setPassword("WIN");

    dbI.store(acc);
    acc.setPassword("FAIL");
    acc.setuserType(2);

    dbI.load(acc);
    assert(acc.getPassword().equals("WIN"));
    assert(acc.getuserType() == 1);
  }

}

// thing to store
class Thing implements StorableInterface{

  @Override
  public StorageDriverBase getStorageDriver(){
    return new ThingStorageDriver();
  }

  @Override
  public ArrayList<Object> getStorageValues(){
    ArrayList<Object> res = new ArrayList<>();
    res.add(id);
    res.add(x);
    return res;
  }

  @Override
  public String getStorageLocation(){
    return "foobar";
  }
  public Integer id = 42;
  public Integer x = 3;
}

// storage driver for Thing object
class ThingStorageDriver extends StorageDriverBase{

  @Override
  public String toInsertSQL(StorableInterface obj) throws StorageDriverException{
    Thing th = (Thing) obj;
    return "INSERT INTO " + obj.getStorageLocation() + " VALUES (" + th.id + "," + th.x + ");";
  }

  @Override
  public String toSelectSQL(StorableInterface obj) throws StorageDriverException{
    Thing th = (Thing) obj;
    return "SELECT * FROM " + obj.getStorageLocation() + " WHERE id = " + th.id + ";";
  }

  @Override
  public void fromSQL(ArrayList<HashMap<String, Object>> queryResults, StorableInterface obj)
      throws StorageDriverException{
    Thing th = (Thing) obj;
    th.id = (Integer)queryResults.get(0).get("id");
    th.x = (Integer)queryResults.get(0).get("x");
  }
}
