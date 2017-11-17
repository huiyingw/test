package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManagerController{

	@RequestMapping("/manager")
	public String route(Model model){
    return "manager";
	}

  // display info page for specific event!
  @RequestMapping("/manager/event")
  public String route(@RequestParam(value="eid", required=true) String eid, Model model){
    //TODO: use real data.
    model.addAttribute("name", "Wedding At Tim's House");
    model.addAttribute("event_desc", "this is a description\nOf the event\nWhich is happening\nKHANNNN!!!");
    // {<name>,<id>}
    String [][] employeeArray = {{"Jon","123"},{"Frank","456"}};
    String [][] equipmentArray = {{"JackHammer","234"}, {"Spice","63464"}};
    model.addAttribute("employees",employeeArray);
    model.addAttribute("equipment",equipmentArray);
    return "eventInfo";
  }
}
