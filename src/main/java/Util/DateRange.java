package util;

import java.util.Date;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DateRange{

  public DateRange(Date start, Date end){
    this.start = start;
    this.end = end;
  }

  public Date getStart(){
    return start;
  }

  public Date getEnd(){
    return end;
  }

  public Duration getDuration(){
    return Duration.of(end.getTime() - start.getTime(), ChronoUnit.SECONDS);
  }

  private Date start;
  private Date end;
}
