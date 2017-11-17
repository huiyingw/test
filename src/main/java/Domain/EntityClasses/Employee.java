package domain.entityclasses;

import java.util.ArrayList;
import database.query.StorableInterface;
import database.query.StorageDriverBase;
import database.query.driver.EmployeeStorageDriver;

public class Employee implements AssetType, StorableInterface{

  public Employee(){}

  //constructor with name and ID
  public Employee(String s, long id){
    this.name = s;
    this.employeeId = id;
  }

  public void setName(String s){
    this.name = s;
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
  //TODO add more specific schedule accessor methods

  // AssetType Interface --------------------------
  @Override
  public long getAssetType(){
    return AssetTypeMap.getAssetId(assetName);
  }
  
  @Override
  public void setId(long id){
    employeeId = id;
  }

  @Override
  public long getId(){
    return employeeId;
  }
  // ///////////////////////////////////////////////
  // StorableInterface -----------------------------
  @Override
  public StorageDriverBase getStorageDriver(){
    return new EmployeeStorageDriver();
  }

  @Override
  public ArrayList<Object> getStorageValues(){
    ArrayList<Object> vals = new ArrayList<>();
    vals.add((Object)employeeId);
    vals.add((Object)AssetTypeMap.getAssetId(assetName));
    vals.add((Object)0l);//TODO Schedule id goes here
    vals.add((Object)name);
    return vals;
  }

  @Override
  public String getStorageLocation(){
    return "employees";
  }
  ////////////////////////////////////////////////////

  private String name;
  private String assetName = "Employee";
  private long employeeId;
  private Schedule mySchedule = null;
}
