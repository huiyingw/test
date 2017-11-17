package domain.entityclasses;

import java.util.ArrayList;
import java.util.Date;
import util.DateRange;

// implemented by objects that can be stored in a schedule
public interface Schedulable {

  // return name of the schedulable object
  public abstract String getName();

  // return object id
  public abstract long getId();

  // return the type of the schedulable object
  public abstract SchedulableType getType();

  // return a list of AssetType objects which describe what assets
  // this "event" needs. The "event" is considered satisfied if all AssetTypes
  // in the returned list can be mapped to a physical asset within the system.
  public abstract ArrayList<AssetType> getRequirements();

  //set / get assigned resources for this event.
  public abstract void setAssigned(ArrayList<AssetType> assigned);
  public abstract ArrayList<AssetType> getAssigned();

  public abstract DateRange getDateRange();
  public abstract void setDateRange(DateRange dr);
}
