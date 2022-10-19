package System;
import java.util.Scanner;

public class CampUI {
	private static final String WELCOME_MESSAGE = "Welcome to Camp Congaree";
	private String[] mainMenuOptions = {""};
	private Scanner scanner;
	private Camp camp;
	
	CampUI(){
		scanner = new Scanner(System.in);
		camp = new Camp();
	}
	
	public void run() {
		System.out.println(WELCOME_MESSAGE);
		
		//Loop as long as we want to keep interacting with the library
		while(true) {
			displayMainMenu();
			
			int userCommand = getUserCommand(mainMenuOptions.length);
			
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
			}
			
			//if they picked the last option then log them out
			if(userCommand == mainMenuOptions.length -1) {
				camp.logout();
				break;
			}
		
			switch(userCommand) {
				case(0):
					createAccount();
					break;
				case(1):
					login();
					break;
			}
		}
		
		System.out.println("Good bye, and have a nice day");
		
	}
	
	private void displayMainMenu() {
		System.out.println("\n************ Main Menu *************");
		for(int i=0; i< mainMenuOptions.length; i++) {
			System.out.println((i+1) + ". " + mainMenuOptions[i]);
		}
		System.out.println("\n");
	}
	
	//get the users command number, if it's not valid, return -1
	private int getUserCommand(int numCommands) {
		System.out.print("What would you like to do?: ");
		
		String input = scanner.nextLine();
		int command = Integer.parseInt(input) - 1;
		
		if(command >= 0 && command <= numCommands -1) return command;
		
		return -1;
	}
	
	private void createAccount() {
		String userName = getField("Username");
		String firstName = getField("First Name");
		String lastName = getField("Last Name");
		int age = Integer.parseInt(getField("Age"));
		String phoneNumber = getField("Phone Number");
		
		if(camp.createAccount(userName, firstName, lastName, age, phoneNumber)) {
			System.out.println("You have successfully created an account");
		} else {
			System.out.println("Sorry an account with that username already exists");
		}
	}
	
	private void login() {
		String userName = getField("Username");
		
		if(camp.login(userName)) {
			User currentUser = camp.getCurrentUser();
			System.out.println("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName() + "!");
		} else {
			System.out.println("Sorry, invalid username ");
		}
	}
	
	private String getField(String prompt) {
		System.out.print(prompt + ": ");
		return scanner.nextLine();
	}
	
	public static void main(String[] args) {
		CampUI libraryInterface = new CampUI();
		libraryInterface.run();

	}

}
