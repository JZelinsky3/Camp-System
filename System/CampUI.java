package System;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CampUI {
    private Scanner scanner;
    private SystemFACADE systemFac;

    //creates an instance of the UI class
    public CampUI(){
        scanner = new Scanner(System.in);
        systemFac = new SystemFACADE();
    }

    public void run(){
        System.out.println("\nWelcome to Camp Congaree!");
        int choice;
        boolean stop = false;
        Type accountType;
        while(true){
            introMenu();
            choice = getUserCommand(3);
            if(choice == -1){
                System.out.println("Input invalid.");
            }else if(choice == 1){
                accountType = login();
                break;
            }else if(choice == 2){
                createAccount();
            } else if(choice == 3){
                systemFac.logout();
                System.out.println("Goodbye.");
                return;
            } 
        }

        while(!stop){
            if(accountType == Type.PARENT){
                userMenu();
                choice = getUserCommand(4);
            }else if(accountType == Type.COUNSELOR){
                counselorMenu();
                choice = getUserCommand(8);
            }else if(accountType == Type.DIRECTOR){
                directorMenu();
                choice = getUserCommand(8);
            }
                
            switch(choice){
                case 1:
                    addCamper();
                    break;
                case 2:
                    sessionSignup();
                    break;
                case 3:
                    printCampDescription();
                    break;
                case 4:
                    logout();
                    stop = true;
                    break;
                case 5:
                    giveExpulsion();
                    break;
                case 6:
                    if(accountType == Type.DIRECTOR){
                        createCamp();
                    }else{
                        printRoster();
                    }
                    break;
                case 7:
                    printWeekInfo();
                    break;
                case 8:
                    printSchedule();
                    break;
                default:
                    System.out.println("Input invalid.");
            }   
        } 
    }

    //displays the intro screen
    private void introMenu(){
        System.out.println("Press (1) to sign in");
        System.out.println("Press (2) to create a new account");
        System.out.println("Press (3) to quit");
    }

    //user's menu
    private void userMenu(){
        System.out.println("\nWelcome! How can we help? \n(1) Add a new Camper \n(2) Sign camper up for session \n(3) Camp Description \n(4) Logout");
    }

    //counselor's menu
    private void counselorMenu(){
        System.out.println("(6) Return directory for cabin");
        System.out.println("(7) Return weeks info");
        System.out.println("(8) Return Schedule");
    }

    //director's menu
    private void directorMenu(){
        System.out.println("(5) Expell a Camper");
        System.out.println("(6) Create new Camp");
        System.out.println("(7) Return weeks info");
        System.out.println("(8) Return Camp Schedule");
    }

    private int getUserCommand(int commands){
        int choice = scanner.nextInt();
        if(choice > 0 && choice <= commands){
            return choice;
        }
        return -1;
    }

    //creates a new user account
    private void createAccount(){
        while(true){
            System.out.println("Who are you? Please choose which account type: (C)ounselor or (P)arent or press (X) to cancel");
            scanner.nextLine();
            String accountType = scanner.nextLine();
            if(accountType.equalsIgnoreCase("x")){
                break;
            }
            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();
            String username;
            while(true){
                System.out.print("Enter a username: ");
                username = scanner.nextLine();
                if(!systemFac.checkUsernameAvailability(username)){
                    System.out.println("Username already has been taken. Retry.");
                    continue;
                }
                break;
            }
            System.out.print("Enter a password: ");
            String password = scanner.nextLine();
            System.out.print("Enter your email address: ");
            String email = scanner.nextLine();
            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter your birthday (format: yyyy-mm-dd): ");
            String birthdayString = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(birthdayString);
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            if(accountType.equalsIgnoreCase("c")){
                System.out.println("Enter a short bio: ");
                String bio = scanner.nextLine();
                Medical medical = getMedical();
                systemFac.createCounselorAccount(username, password, email, lastName, firstName, phoneNumber, birthday, address, bio, medical);
            }
            else{
                systemFac.createUserAccount(username, password, email, lastName, firstName, phoneNumber, birthday, address);
            }
            break;
        }
        
    }

    private Type login(){
        while(true){
            System.out.print("Username: ");
            scanner.nextLine();
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            int loginResult = systemFac.login(username, password);
            Type loginType = Type.COUNSELOR;
            if(loginResult == -1){
                System.out.println("Username or password invalid. Try again.");
            }else{
                switch(loginResult){
                    case 1:
                        loginType = Type.PARENT;
                        break;
                    case 2:
                        loginType = Type.DIRECTOR;
                        break;
                }
                return loginType;
            }
            
        }
    }

    //adds camper to users account
    private void addCamper(){
        System.out.print("Campers first name: ");
        scanner.nextLine();
        String firstName = scanner.nextLine();
        System.out.print("Campers last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Campers birthday (format: yyyy-mm-dd): ");
        String birthdayString = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(birthdayString);
        String yn = "y";
        ArrayList<Contact> emergencyContacts = new ArrayList<Contact>();
        while(yn.equalsIgnoreCase("y")){
            System.out.println("\nEnter the Emergency Contact's information:");
            Contact emergencyContact = createContact();
            emergencyContacts.add(emergencyContact);
            System.out.println("Is there another Emergency Contact? (Yes/No)");
            yn = scanner.nextLine();
        }

        Medical medical = getMedical();

        }

    private Medical getMedical(){
        System.out.println("\nEnter the Physician's information");
        Contact physicianContact = createContact();
        System.out.println("Does the camper take any treatments? Yes/No");
        String yn = scanner.nextLine();
        ArrayList<Treatment> treatments = new ArrayList<Treatment>();
        while(yn.equalsIgnoreCase("y")){
            System.out.println("\nWhat is the name of the treatment?");
            String treatName = scanner.nextLine();
            System.out.println("");
            String treatDescription = scanner.nextLine();
            System.out.println("When does the medicine have to be taken?");
            String treatTime = scanner.nextLine();
            Treatment treatment = new Treatment(treatName, treatDescription, treatTime);
            treatments.add(treatment);
            System.out.println("\nDo you want to add another treatment? (Yes/No)");
            yn = scanner.nextLine();
        }
        //adding allergies
        System.out.println("\nDoes the camper have any allergies? (Yes/No)");
        yn = scanner.nextLine();
        ArrayList<String> allergies = new ArrayList<String>();
        while(yn.equalsIgnoreCase("y")){
            System.out.println("\nWhat is the camper allergic to?");
            String allergy = scanner.nextLine();
            allergies.add(allergy);
            System.out.println("\nDo you wish to add another? (Yes/No)");
            yn = scanner.nextLine();
        }
        Medical medical = new Medical(physicianContact);
        medical.addAllergies(allergies);
        medical.addTreatments(treatments);
        return medical;
    }

    //creates a new contact 
    private Contact createContact(){
        Contact contact;

        System.out.print("Enter first name: "); 
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: "); 
        String lastName = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Relationship: ");
        contact = new Contact(firstName, lastName, phoneNumber);
        
        return contact;
    }

    //general description of the camp
    private void printCampDescription(){
        String information = systemFac.getUserInformation();
        System.out.println(information);
        ArrayList<Session> avSessions = systemFac.findAvailableSessions();
        System.out.println("The current available sessions: ");
        for(Session s : avSessions){
            System.out.println(" - " + s.getStartDate() + " - " + s.getEndDate() + ", Theme: " + s.getTheme());
        }
        System.out.println("> The address of the camp is: 100 National Park Rd, Hopkins, SC 29061 ");
        System.out.println("> The camps phone number is \"887-549-7361\"");
        System.out.println("> The camps email is \"campcongaree@gmail.com \".");
        System.out.println("> Camp Congaree is located next door to Congaree National Park. Includes hiking trails, rivers, and lots more!");
    }
   
    //sign camper up for a session
    private void sessionSignup(){
        System.out.println("\nEnter the first name of the camper you would like to register for a session: ");
        scanner.nextLine();
        String firstName = scanner.nextLine();
        System.out.println("\nEnter the last name of the camper you would like to register for a session: ");
        String lastName = scanner.nextLine();
        Camper camper = systemFac.findCamper(firstName, lastName);
        Session session = chooseSession(camper);
        if(session == null){
            return;
        }
        int signupResult = systemFac.sessionSignup(camper, session);
        if(signupResult == -1){
            System.out.println("No cabin available for your camper's age!");
            return;
        }
        System.out.println(firstName + lastName + "was successfully signed up for the Session in Cabin " + signupResult);
    }

    private Session chooseSession(Camper camper){
        ArrayList<Session> options = systemFac.findAvailableSessions();
        if(options.size() == 0){
            System.out.println("No sessions available for this camper!");
            return null;
        }
        int counter = 1;
        for(Session s : options){
            System.out.println(counter + ") " + "Start: " + s.getStartDate() + ", End: " + s.getEndDate() + ", Theme: " + s.getTheme());
            counter++;
        }
        System.out.print("Choose a session by selecting a number: ");
        int selection = scanner.nextInt();
        return options.get(selection - 1);
    }


    //gives an expulsion to a camper
    private void giveExpulsion(){
        System.out.print("Campers first name: ");
        String firstName = scanner.next();
        System.out.print("Campers last name: ");
        String lastName = scanner.next();
        System.out.print("Reason for the expulsion: ");
        String reason = scanner.next();
        systemFac.giveExpulsion(firstName, lastName, reason);
        System.out.println("Expulsion given to Camper " + firstName + lastName);
    }

    private void createCamp(){
        System.out.print("Number of Sessions: ");
        int numberSessions = scanner.nextInt();
        scanner.nextLine();
        for(int i = 1; i <= numberSessions; i++){
            System.out.println("Session " +i+ " Theme: ");
            String theme = scanner.nextLine();
            System.out.println("Start date (format: yyyy-mm-dd): ");
            String start = scanner.nextLine();
            LocalDate startDate = LocalDate.parse(start);
            System.out.println("End date (format yyyy-mm-dd): ");
            String end = scanner.nextLine();
            LocalDate endDate = LocalDate.parse(end);
            systemFac.createSession(theme, startDate, endDate);
        }
        System.out.println("Number of Cabins: ");
        int numberCabins = scanner.nextInt();
        for(int i = 1; i <= numberCabins; i++){
            System.out.println("Lowest age for this cabin: ");
            int lowCabinAge = scanner.nextInt();
            System.out.println("Max age for this cabin: ");
            int maxCabinAge = scanner.nextInt();
            Cabin newCabin = new Cabin(lowCabinAge, maxCabinAge);
            systemFac.addCabinToSessions(newCabin);
        }
    }
    //print all the people in one session
    private void printRoster(){
        System.out.println(systemFac.listSessions());
        System.out.println("");
        int sessionNum = scanner.nextInt();
        systemFac.printRoster(sessionNum);
    }

    private void printWeekInfo(){
        System.out.println(systemFac.listSessions());
        System.out.println("The information for which session do you want printed? Enter session number: ");
        int sessionNum = scanner.nextInt();
        systemFac.printWeekInfo(sessionNum);
    }

    private void printSchedule(){
        System.out.println(systemFac.listSessions());
        System.out.println("The schedule for which session do you want printed? Enter session number: ");
        int sessionNum = scanner.nextInt();
        systemFac.printSchedule(sessionNum);
    }
    

    //logs user out of the system
    private void logout(){
        System.out.println("Goodbye!");
        systemFac.logout();
    }

    public static void main(String[] args){
        CampUI ui = new CampUI();
        ui.run();
    }
    
}