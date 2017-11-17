package web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


import database.api.DebugDBInterface;
import database.query.StorableInterface;
import database.query.StorageDriverBase;
import database.query.StorageDriverException;
import database.jdbc.JDBCException;

@Controller
public class LoginController {


	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
	public Greeting checkExist(Account message)throws Exception{
		DebugDBInterface dbI = DebugDBInterface.getInstance();
	    dbI.NUKE();
	    dbI.initializeDB();
	    Account manager = new Account();
	    manager.setuserID("manager");
	    manager.setuserType(1);
	    manager.setPassword("123456");
		dbI.store(manager);


	    Account employee = new Account();
	    employee.setuserID("employee");
	    employee.setuserType(2);
	    employee.setPassword("123456");
	    dbI.store(employee);

	    Account temp = new Account();
	    temp.setuserID(message.getuserID());


		dbI.load(temp);
		//TODO: if not load, how to check!?!?
		Thread.sleep(1000);
		if (temp.getPassword().equals(message.getPassword())){
			String s = new String("Event Scheduler");
			if(temp.getuserType()==2){
				s="employee";
			}
			return new Greeting("Account found! \n" +"Hey, " + message.getuserID() +"\n You are a " +s);
		}
		else if (!temp.getPassword().equals(message.getPassword())) {
			return new Greeting("Password Wrong");
		}
		else{
			return new Greeting("User ID Not found");
		}
	}
}
	
