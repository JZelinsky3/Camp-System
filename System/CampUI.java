package System;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

public class CampUI {
	private Scanner scanner;
    private SystemFACADE campFacade;

    public CampUI(){
        scanner = new Scanner(System.in);
        campFacade = new SystemFACADE();
    }

    public void run(){
        System.out.println("Welcome to our camp website!");
        int choice;
        boolean stop = false;
        while(true){
            displayIntroMenu();
            choice = getUserCommand(2);
            if(choice == -1){
                System.out.println("Invalid input.");
            }else if(choice == 1){
                login();
                break;
            }else if(choice == 2){
                createAccount();
            }  
        }
		
		while(!stop){
            displayMainMenu();
            choice = getUserCommand(6);
            switch(choice){
				case(0):
					createAccount();
					break;
				case(1):
					login();
					break;
				case (2):
                    addCamper();
                    break;
                case (3):
                    sessionSignup();
                    break;
                case (4):
                    printGeneralInformation();
                    break;
                case (5):
                    logout();
                    stop = true;
                    break;
                default:
                    System.out.println("Invalid input");
			}
		}
		
		System.out.println("Good bye, and have a nice day");
		
	}

	private void displayIntroMenu(){
        System.out.println("Press (1) to sign in to your account");
        System.out.println("Press (2) to create a new account");
    }

    private void displayMainMenu(){ //for choices: create child acct, add child to session
        System.out.println("What would you like to do today? \n(1) add a new Camper \n(2) sign up camper for a session \n\n(3) General Information \n(5) Logout");
    }

    private int getUserCommand(int commands){
        int choice = scanner.nextInt();
        if(choice > 0 && choice <= commands){
            return choice;
        }
        return -1;
    }

    private void createAccount(){
        while(true){
            System.out.print("\nEnter your first name: ");
            String firstName = scanner.nextLine();
            System.out.print("\nEnter your last name: ");
            String lastName = scanner.nextLine();
            System.out.print("\nEnter a username: ");
            String username = scanner.nextLine();
            System.out.print("\nEnter a password: ");
            String password = scanner.nextLine();
            System.out.print("\nEnter your email address: ");
            String email = scanner.nextLine();
            System.out.print("\nEnter your phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("\nEnter your birthday (format: yyyy-mm-dd): ");
            String birthdayString = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(birthdayString);
            System.out.print("\nEnter your address: ");
            String address = scanner.nextLine();
            if(campSystem.createAccount(username, password, email, lastName, firstName, phoneNumber, birthday, address) == true){
                System.out.println("\nAccount created successfully.");
                break;
            }
            System.out.println("\nInvalid input. Start over.");
        }
        
    }

    private void login(){
        while(true){
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            if(campFacade.login(username, password) == true){
                System.out.println("Login successful.");
                break;
            }
            System.out.println("Username or password invalid. Try again.");
        }
    }

    private void addCamper(){
        while(true){
            System.out.print("\nEnter the Campers first name: ");
            String firstName = scanner.nextLine();
            System.out.print("\nEnter the Campers last name: ");
            String lastName = scanner.nextLine();
            System.out.print("\nEnter Campers birthday (format: yyyy-mm-dd): ");
            String birthdayString = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(birthdayString);
            System.out.println("\n Enter the following information about the EMERGENCY CONTACT:");
            Contact emergencyContact = createContact();
            //maybe add guardians here (?)
            //medical info starts here
            System.out.println("\nEnter the following information about the Campers PHYSICIAN");
            Contact physicianContact = createContact();
            System.out.println("\nWould you like to add Treatments to your Campers Account (All necessary Treatments to be taken during camp must be added) Y/N");
            String yn = scanner.nextLine();
            ArrayList<Treatment> treatments;
            while(yn.equalsIgnoreCase("y")){
                System.out.print("\nWhat is the name of the Campers treatment");
                String treatName = scanner.nextLine();
                System.out.print("\nWhat time of day does the camper need to take medicine" );
                String treatTime = scanner.nextLine();
                Treatment treatment = new Treatment(treatName, treatTime);
                treatments.add(treatment);
                System.out.print("\nDo you want to add another treatment? (Y/N) ");
                yn = scanner.nextLine();
            }
            //for adding allergies
            System.out.println("\nWould you like to add any Allergies to your Campers Account (All necessary allergies to be taken during camp must be added) Y/N");
            yn = scanner.nextLine();
            ArrayList<String> allergies;
            while(yn.equalsIgnoreCase("y")){
                System.out.print("\nWhat is the campers allergy to?");
                String allergy = scanner.nextLine();
                allergies.add(allergy);
                System.out.print("\nDo you want to add another allergy? Y/N");
                yn = scanner.nextLine();
            }

    }

    private Contact createContact(){
        Contact contact;

        System.out.print("\n Enter the first name: "); 
        String firstName = scanner.nextLine();
        System.out.print("\n Enter the last name: "); 
        String lastName = scanner.nextLine();
        System.out.print("\nEnter the address: ");
        String address = scanner.nextLine();
        System.out.print("\nEnter the phone number: ");
        String phoneNumber = scanner.nextLine();
        contact = new Contact(firstName, lastName, phoneNumber, address);
        
        return contact;
    }


    private void logout(){
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private void sessionSignup(){
        System.out.print("\nEnter the first name of the camper you would like to register for a session: ");
        String firstName = scanner.nextLine();
        System.out.print("\nEnter the last name of the camper you would like to register for a session: ");
        String lastName = scanner.nextLine();
        Camper camper = campFacade.findCamperByName(firstName, lastName);
        Session session = chooseSession(camper);
        campFacade.sessionSignup(camper, session);
    }

    private Session chooseSession(Camper camper){
        int age = camper.getAge();
        ArrayList<Session> options = campFacade.findAvailableSessions(age);
        int counter = 1;
        for(Session s : options){
            System.out.println(counter + ") " + "Start: " + s.getStartDate() + ", End: " + s.getEndDate());
        }
        System.out.print("Choose a session by selecting a number: ");
        int selection = scanner.nextInt();
        return options.get(selection - 1);
    }

    public static void main(String[] args){
        CampUI ui = new CampUI();
        ui.run();
    }
    
}