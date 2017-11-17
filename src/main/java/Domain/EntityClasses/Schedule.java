package domain.entityclasses;

import java.util.HashMap;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.Long;

public class Schedule implements Iterable<Schedulable>{

  public Schedule(){
    scheduleHash = new HashMap<Long, Schedulable>();
  }

  // initialize internal hashmap with provided hash map
  public Schedule(HashMap<Long, Schedulable> initHash){
    scheduleHash = initHash;
  }

  public Schedulable getById(long id){
    return scheduleHash.get(id);
  }

  public Schedulable getByName(String name){
    for(Schedulable s : scheduleHash.values()){
      if(s.getName().equals(name)){
        return s;
      }
    }
    return null;
  }

  // returns and ArrayList of all events in this schedule
  public ArrayList<Schedulable> dumpSchedule(){
    return new ArrayList<Schedulable>(scheduleHash.values());
  }

  public void Add(Schedulable s){
    scheduleHash.put(s.getId(),s);
  }

  public void Remove(Long id){
    scheduleHash.remove(id);
  }

  public void Remove(Schedulable s){
    scheduleHash.remove(s.getId());
  }

  // Iterable interface ------------------------------
  @Override
  public Iterator<Schedulable> iterator(){
    return scheduleHash.values().iterator();
  }
  ////////////////////////////////////////////////////

  private HashMap<Long, Schedulable> scheduleHash;
}
