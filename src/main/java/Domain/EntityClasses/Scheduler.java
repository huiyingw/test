import java.util.ArrayList;
import java.util.HashMap;
/*
import database.query.StorableInterface;
import database.query.StorageDriverBase;
import database.query.driver.EmployeeStorageDriver;
import database.query.driver.EquipmentStorageDriver;
import database.query.driver.EventStorageDriver;*/

public class Scheduler{
	
	public static double timeDuration(long id, int startTime, int endTime){
		double t = endTime - startTime;
		return t;
	}
	
	public static int assignEmployeeNum(int numGuest){
		
		int numEmployee = 12;
		int numGuestLeft = numGuest;
		
		while(numGuestLeft >= 50){		
				numEmployee += 5;
				numGuestLeft -= 50;	
		}
		return numEmployee;
		
	}
	
	
	public void assignEmployee(int numEmployee, ArrayList<String> employeeList){
		//TODO：set arranging priority to the 12 employee in the company
		ArrayList employeeSched = new ArrayList<String>();
		
			 
	}
		
		
	public static ArrayList<String> assignEquip(int numGuest, String menuType){
		
		ArrayList equipList = new ArrayList<String>();
		equipList.add("Plates: " + numGuest);
		equipList.add("Cutlery: " + numGuest);
		equipList.add("Glassware: " + numGuest);
		equipList.add("Linen" + numGuest*2);

		if(menuType == "wedding"){
			equipList.add("candle: " + numGuest/4);
			equipList.add("flowers: " + numGuest);
			return equipList;
		}
		if(menuType == "seafood"){
			equipList.add("Shells: " + numGuest/4);
			equipList.add("Starfish Decor: " + numGuest/4);	
			return equipList;
		}

		if(menuType == "afternoonTea"){
			equipList.add("Cats: " + 1);	
			return equipList;
		}
			
		return equipList;
	}
	
	public static ArrayList<String> assignFood(int numGuest, String menuType){
		
	
		ArrayList foodList = new ArrayList<String>();
		
		if(menuType == "bbq"){
			foodList.add("Grilled PorterHouse with Shallots and Potatoes: " + numGuest);
			foodList.add("Grilled Tomatoes with Oregano and Lemon Tricolor Salad: " + numGuest);
			foodList.add("Watermelon,Orange, and Feta Salad: " + numGuest);
			foodList.add("Red, White, and Blue Berry Trifle: " + numGuest);
			return foodList;


		}
		if(menuType == "wedding"){
			foodList.add("Seafood salad: shrimp, calamari, scungilli, octopus, and mussels with lemon and extra-virgin olive oil: " + numGuest);
			foodList.add("Fresh figs: draped with prosciutto di Parma: " + numGuest);
			foodList.add("Tuscan garden salad: romaine lettuce, Bibb lettuce, radicchio, tomatoes, and radishes with balsamic vinaigrette:" + numGuest);
			foodList.add("Salmon: broiled and seasoned with bread crumbs, garlic, lemon, and butter" + numGuest);
			foodList.add("Wedding Cake Tower: " + 1);
			return foodList;

		}
		if(menuType == "seafood"){
			foodList.add("Sea Breeze Cocktail: " + numGuest);
			foodList.add("Bacon- Wrapped Scallops with Spicy Cilantro Mayonnaise: " + numGuest);
			foodList.add("Smoked Salmon Deviled Eggs: " + numGuest);
			foodList.add("Summer Gratin: " + numGuest);
			foodList.add("Baked Salmon with Creamy Coconut-Ginger Sause over Coconut-Ginger Rice: " + numGuest);
			foodList.add("Key Lime Pie: " + numGuest);
			return foodList;
		}
		if(menuType == "afternoonTea"){
			foodList.add("Rocket Pestro and Cherry Tomato Sandwich: " + numGuest);
			foodList.add("Crushed White Pepper with Cucumber Sandwich: " + numGuest);
			foodList.add("Ham and English Mustard Sandwich: " + numGuest);
			foodList.add("Chedder & Chutney Sandwich: " + numGuest);
			foodList.add("Lavender Scones with Lemon Curd: " + numGuest);
			foodList.add("Gooey Chocolate Brownies: " + numGuest);
			foodList.add("Victoria Sponge Cake: " + numGuest);
			foodList.add("Chocolate Eclairs: " + numGuest);
			foodList.add("Mille Feuille: " + numGuest);
			foodList.add("Macaron: " + numGuest);
			return foodList;
		}
		if(menuType == "italian"){
			foodList.add("Antipasto Platter: " + numGuest);
			foodList.add("Mushroom and Spinach Lasagna: " + numGuest);
			foodList.add("Tricolore Salad With Parmesan: " + numGuest);
			foodList.add("Garlic Bread: " + numGuest);
			foodList.add("Olive Oil Cake With Vanilla Orange: " + numGuest);
			foodList.add("" + numGuest);
			return foodList;
		}
		if(menuType == "cocktailParty"){
			foodList.add("Grilled coriander giant prawns with a chili-lime sauce: " + numGuest);
			foodList.add("Rosemary-roasted baby lamb chops with apricot-mint salsa: " + numGuest);
			foodList.add("Chicken satays with a tangy peanut sauce: " + numGuest);
			foodList.add("Bite-size chicken fajitas with avocado salsa: " + numGuest);
			foodList.add("Three onion, Feta, and sun-dried tomato tartlets: " + numGuest);
			foodList.add("Mini lobster rolls on toasted brioche: " + numGuest);
			foodList.add("Sauteed softshell crabs with a spicy rémoulade" + numGuest);
			foodList.add("Miso-glazed cod with Asian pesto" + numGuest);
			foodList.add("Authentic dim sum: crystal shrimp, sweet rice, spinach, and shiitake mushroom" + numGuest);
			foodList.add("Dumplings served with three dipping sauces" + numGuest);
			return foodList;
		}
		return foodList;
	}
	
		
	/*public static void main(String [] args){
		double t = timeDuration(123,4,6);
		System.out.println(t);
		
		int x = assignEmployeeNum(200); 
		System.out.println(x);
		
		ArrayList foodList = new ArrayList<String>();
		foodList = assignFood(200, "wedding");
		ArrayList equipList = new ArrayList<String>();
		equipList = assignEquip(200, "wedding");

		System.out.println(foodList);
		System.out.println(equipList);
		
		/*To be asked:
			1) event: need guest number and menu type
			2) how do i test my code?

		*/
	} 

}