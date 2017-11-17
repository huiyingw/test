package domain.entityclasses;

import java.util.Date;
import java.util.ArrayList;
import util.DateRange;
import database.query.StorableInterface;
import database.query.StorageDriverBase;
import database.query.driver.EventStorageDriver;

public class Event implements Schedulable, StorableInterface{

	//empty Event object
	public Event() {}

	//Event object with specified parameters
	public Event(String s, Date start, Date end) {
		this.name = s;
		dateRange = new DateRange(start, end);
	}

	public void setName(String s) {
		name = s;
	}

  public void setId(long id){
    eventId = id;
  }

	public void setStartDate(Date start) {
		dateRange = dateRange == null ?
        new DateRange(start,new Date()) : new DateRange(start, dateRange.getEnd());
	}

	public void setEndDate(Date end) {
    dateRange = dateRange == null ?
        new DateRange(new Date(), end) : new DateRange(dateRange.getStart(),end);
	}

  // Schedulable interface -----------------
  @Override
  public String getName(){
    return name;
  }

  @Override
  public long getId(){
    return eventId;
  }

  @Override
  public SchedulableType getType(){
    return SchedulableType.SCHED_EVENT;
  }

  @Override
  public ArrayList<AssetType> getRequirements(){
    //TODO figure this out!
    return new ArrayList<AssetType>();
  }

  @Override
  public void setAssigned(ArrayList<AssetType> assigned){
    assignedResources = assigned;
  }

  @Override
  public ArrayList<AssetType> getAssigned(){
    return assignedResources;
  }

  @Override
  public DateRange getDateRange(){
    return dateRange;
  }

  @Override
  public void setDateRange(DateRange dr){
    dateRange = dr;
  }
  //////////////////////////////////////////////////
  // StorableInterface -----------------------------
  @Override
  public StorageDriverBase getStorageDriver(){
    return new EventStorageDriver();
  }

  @Override
  public ArrayList<Object> getStorageValues(){
    ArrayList<Object> vals = new ArrayList<>();
    vals.add((Object)eventId);
    vals.add((Object)0l);//TODO Schedule id goes here
    vals.add((Object)name);
    vals.add((Object)assignedResources);
    return vals;
  }

  @Override
  public String getStorageLocation(){
    return "events";
  }
  ////////////////////////////////////////////////////

  private String name;
  private DateRange dateRange = null;
  private ArrayList<AssetType> assignedResources = null;
  private long eventId;
}
