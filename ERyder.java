// Core Business Class: Implements the basic attribute management and billing logic for eRyder's shared electric bicycles
public class ERyder {
    // Enterprise logo constant: Fixed brand name
    public static final String COMPANY_NAME = "ERyder";
    // Billing constant: Base fare for cycling (initial price)
    public static final double BASE_FARE = 1.0;
    // Billing constant: Cost per minute of cycling (duration fee)
    public static final double PER_MINUTE_FARE = 0.5;
    
    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;
    private String bikeID;          // Vehicle Attributes: Each electric vehicle is identified by a unique alphanumeric code.
    private int batteryLevel;       // Vehicle Attributes: Current battery charge percentage (0% - 100%)
    private boolean isAvailable;    // Vehicle status: Whether it is in an operational-ready state or not (e.g., in maintenance,charging, or availablie for rental)
    private double kmDriven;        // Vehicle Data: Cumulative Driving Mileage (Unit: Kilometers)
    private int totalUsageInMinutes; // Cycling Data: Total cycling duration of the user (unit: minutes)
    private double totalFare;        // Billing data: The total cost that the user needs to pay after the ride ends

    // Updated default constructor with parameters
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {

        this.bikeID = bikeID;
        setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        
        // Initialize final variables with default values
        this.LINKED_ACCOUNT = "guest_user";
        this.LINKED_PHONE_NUMBER = "000-000-0000";
        
        // Initialize new private variables
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }

    // Parameterized constructor: Full initialization of all bike properties
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven, 
                  String linkedAccount, String linkedPhoneNumber) {
        // Vehicle core attribute assignment (including battery legality verification)        
        this.bikeID = bikeID;
        setBatteryLevel(batteryLevel); // Use the verified battery integrity setter
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        
        // Initialize final variables with provided values
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
        
        // Initialize new private variables
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }

    // Core operational method: Validates ride eligibility
    public void ride() {
        // Vehicle status code: 0= Available 1= Low battery 2= Unavailable
        int state;
        if (batteryLevel >= 10 && isAvailable) {
            state = 0; // Ready state
        } else if (batteryLevel < 10) {
            state = 1; // Low battery state
        } else {
            state = 2; // Unavailable state
        }
        
        // Switch based on the determined state
        switch (state) {
            case 0:
                System.out.printf("E-bike %s is ready for use – battery at %d%% and fully operational.%n", 
                                  bikeID, batteryLevel);
                break;
                
            case 1:
                System.out.printf("E-bike %s cannot be ridden: battery level is too low (%d%%).%n", 
                                  bikeID, batteryLevel);
                break;
                
            case 2:
                System.out.printf("E-bike %s cannot be ridden: marked as unavailable.%n", bikeID);
                break;
                
            default:
                
                System.out.printf("E-bike %s is in an unknown state.%n", bikeID);
                break;
        }
    }
    
    // New public method to print ride details
    public void printRideDetails(int usageInMinutes) {
        // Calculate the fare using the private method
        double fare = calculateFare(usageInMinutes);
        
        // Store the usage and fare
        this.totalUsageInMinutes = usageInMinutes;
        this.totalFare = fare;
        
        // Print all the details
        System.out.println("\n=== eRyder Ride Details ===");
        System.out.println("Service Provider: " + COMPANY_NAME);
        System.out.println("Linked Account: " + LINKED_ACCOUNT);
        System.out.println("Contact Phone: " + LINKED_PHONE_NUMBER);
        System.out.println("Bike Identifier: " + bikeID);
        System.out.println("Usage Time: " + usageInMinutes + " minutes");
        System.out.printf("Total Fare: $%.2f%n", fare);
        System.out.println("Initial Fare: $" + BASE_FARE);
        System.out.println("Per Minute Rate: $" + PER_MINUTE_FARE);
        System.out.println("===========================");
    }
    
    // New private method to calculate fare
    private double calculateFare(int usageInMinutes) {
        return BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
    }

    // Comprehensive details printer with formatted output
    public void printBikeDetails() {
        System.out.println("\n=== " + COMPANY_NAME + " E-Bike Detailed Profile ===");
        System.out.println("Bike Unique ID: " + this.bikeID);
        System.out.println("Battery Remaining: " + this.batteryLevel + "%");
        System.out.println("Rental Status: " + (this.isAvailable ? "Available for rental" : "Unavailable (in maintenance/charging)"));
        System.out.printf("Total Distance Covered: %.2f kilometers%n", this.kmDriven);
        System.out.println("Linked Account: " + LINKED_ACCOUNT);
        System.out.println("Contact Number: " + LINKED_PHONE_NUMBER);
        System.out.println("Total Ride Time: " + totalUsageInMinutes + " minutes");
        System.out.printf("Total Fare Paid: $%.2f%n", totalFare);
        System.out.println("========================================");
    }

   // Obtain the unique vehicle identification code,battery level, availability status, and cumulative mileage data for the e-bike)
    public String getBikeID() {
        return bikeID;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getKmDriven() {
        return kmDriven;
    }
    
    public String getLinkedAccount() {
        return LINKED_ACCOUNT;
    }
    
    public String getLinkedPhoneNumber() {
        return LINKED_PHONE_NUMBER;
    }
    
    public int getTotalUsageInMinutes() {
        return totalUsageInMinutes;
    }
    
    public double getTotalFare() {
        return totalFare;
    }

    // Set a unique vehicle identifier
    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    // Battery power setting device with strict range verification (0 - 100) function
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.err.printf("Invalid battery level (%d%%) – must be between 0 and 100. Current level (%d%%) remains unchanged.%n", 
                              batteryLevel, this.batteryLevel);
        }
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setKmDriven(double kmDriven) {
       // Mileage assignment rule: If a negative value is passed in, the current value is retained (to ensure data integrity)
        this.kmDriven = (kmDriven >= 0) ? kmDriven : this.kmDriven;
    }
}

// The main execution class used for demonstrating the eRyder service
class ERyderMain {
    public static void main(String[] args) {
        // Display company information
        System.out.println("Welcome to " + ERyder.COMPANY_NAME + " e-Bike Sharing Service!");
        System.out.println("Initial Fare: $" + ERyder.BASE_FARE + ", Per Minute: $" + ERyder.PER_MINUTE_FARE);
        
        // Scene 1: Default constructor initialization (Guest account)
        ERyder defaultBike = new ERyder("DEFAULT-001", 100, true, 25.5);
        System.out.println("\n=== Demonstration 1: Default Bike Initialization (Guest User) ===");
        defaultBike.printBikeDetails();
        
        // Call printRideDetails for the default bike
        System.out.println("\n=== Testing ride for default bike ===");
        defaultBike.printRideDetails(15);
        
        // Scenario 2: Using the parameterized constructor with linked account info
        ERyder premiumBike = new ERyder("ER-789X", 85, true, 127.45, 
                                        "john_doe", "555-123-4567");
        System.out.println("\n=== Demonstration 2: Custom Bike Initialization (Registered User) ===");
        premiumBike.ride();
        premiumBike.printBikeDetails();
        
        // Call printRideDetails for the premium bike
        System.out.println("\n=== Testing ride for premium bike ===");
        premiumBike.printRideDetails(30);
        
        // Additional demonstration
        System.out.println("\n=== Demonstration 3: Another registered user ===");
        ERyder anotherBike = new ERyder("ER-456Y", 60, true, 75.2, 
                                        "jane_smith", "555-987-6543");
        anotherBike.printRideDetails(45);
        
        // Demonstate the use of the private method and explain why it cannot be called directly
        System.out.println("\n=== Trying to call calculateFare() method ===");
        System.out.println("What happens when we try to call calculateFare()?");
        System.out.println("Answer: We get a compilation error because calculateFare() is a PRIVATE method.");
        System.out.println("Private methods can only be called from WITHIN the same class.");
        System.out.println("They cannot be accessed directly from outside the class (like from ERyderMain).");
        
        System.out.println("\n=== How to properly call calculateFare() method ===");
        System.out.println("The calculateFare() method is properly called INSIDE the printRideDetails() method.");
        System.out.println("This is the correct way to use a private method - it's an internal helper method.");
        System.out.println("Example from printRideDetails():");
        System.out.println("    double fare = calculateFare(usageInMinutes);");
        System.out.println("This works because printRideDetails() is in the same class as calculateFare().");
        
        // Show that we can still get the fare through public methods
        System.out.println("\nWe can still access the fare information through public methods:");
        System.out.println("For example, using getTotalFare(): $" + premiumBike.getTotalFare());
        System.out.println("Or through the printRideDetails() method which already displays it.");
    }
}