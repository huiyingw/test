function MenuDisplay(){
	var displayer = document.getElementById("menu_shower");
	var value = document.getElementById("sel1").value;
	//document.getElementById("menu_shower").innerHTML = "You selected: " + value;
	if (value=='Blank' || value=="Other"){
		displayer.classList.add("hide");
		console.log('it is blank or other');
	}else{
		if (value=='Chinese'){
			displayer.classList.remove("hide");
			displayer.innerHTML = "Menu Teamplate [Chinese]:";

		}
		if (value=='French'){
			displayer.classList.remove("hide");
			displayer.innerHTML = "Menu Teamplate [French]:";

		}
		if (value=='American'){
			displayer.classList.remove("hide");
			displayer.innerHTML = "Menu Teamplate [Amercian]:";

		}
		if (value=='Mexcian'){
			displayer.classList.remove("hide");
			displayer.innerHTML = "Menu Teamplate [Mexcian]:";

		}
	}
}