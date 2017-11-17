package controllers;

import util.JSONification;
import domain.entityclasses.Event;

import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
class ManagerRestController {

  @RequestMapping("/manager/events")
  public String serveEvents(@RequestParam(value="start") String start_date, @RequestParam(value="end") String end_date){
    //TODO load events some how???
    Event e1 = new Event("B Day Party", new Date(2017 - 1900, 10, 10, 10,0), new Date(2017 - 1900, 10, 10, 13,0));
    e1.setId(123);
    Event e2 = new Event("Rave!", new Date(2017 - 1900, 10, 17, 15,0), new Date(2017 - 1900, 10, 18, 21,0));
    e2.setId(456);
    Event [] elist = {e1, e2};
    return "[" +JSONification.eventToJSON(elist) + "]";
  }

}
