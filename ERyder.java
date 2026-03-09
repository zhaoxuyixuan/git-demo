// Core class for eRyder e-bike sharing service
public class ERyder {
    // Instance variables containing bike attributes
    private String bikeID;          // A distinctive alphanumeric code assigned to every e-bike
    private int batteryLevel;       //Current battery charge level (ranging from 0% to 100%)
    private boolean isAvailable;    // Current operational readiness level
    private double kmDriven;        // Cumulative walking distance (kilometers)

    
    public ERyder() {
        this.bikeID = "DEFAULT-000";
        this.batteryLevel = 0;
        this.isAvailable = false;
        this.kmDriven = 0.0;
    }

    // Parameterized constructor: Full initialization of all bike properties
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        setBatteryLevel(batteryLevel); // Use the verified battery integrity setter
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }

   // Core operational method: Validates ride eligibility
public void ride() {
    // Determine the state using a combination of conditions
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
            System.out.printf("E-bike %s cannot be ridden: insufficient battery (%d%%).%n", 
                              bikeID, batteryLevel);
            break;
            
        case 2:
            System.out.printf("E-bike %s cannot be ridden: marked as unavailable.%n", bikeID);
            break;
            
        default:
            // This should never happen
            System.out.printf("E-bike %s is in an unknown state.%n", bikeID);
            break;
    }
}
    // Comprehensive details printer with formatted output
    public void printBikeDetails() {
        System.out.println("\n=== eRyder E-Bike Detailed Profile ===");
        System.out.println("Unique Bike Identifier: " + this.bikeID);
        System.out.println("Battery Remaining: " + this.batteryLevel + "%");
        System.out.println("Availability Status: " + (this.isAvailable ? "Available for rental" : "Unavailable (in maintenance/charging)"));
        System.out.printf("Total Distance Covered: %.2f kilometers%n", this.kmDriven);
        System.out.println("========================================");
    }

    // Getter method (in line with the principle of encapsulation)
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

    // Method for setting up with the function of executing business rules
    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    // Battery power setting device with strict range verification (0 - 100) function
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.err.printf("Invalid battery level (%d%%) – must be between 0 and 100. Retaining current level (%d%%).%n", 
                              batteryLevel, this.batteryLevel);
        }
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setKmDriven(double kmDriven) {
       // Prevent the occurrence of negative distance values (in terms of data integrity)
        this.kmDriven = (kmDriven >= 0) ? kmDriven : this.kmDriven;
    }
}

// The main execution class used for demonstrating the eRyder service
class ERyderMain {
    public static void main(String[] args) {
        // Scenario 1: Default constructor initialization + Detailed information printing
        ERyder defaultBike = new ERyder();
        System.out.println("=== Demonstration 1: Default Bike Initialization ===");
        defaultBike.printBikeDetails();

        // Scenario 2: Parameterized constructor + ride check + details print
        ERyder premiumBike = new ERyder("ER-789X", 85, true, 127.45);
        System.out.println("\n=== Demonstration 2: Custom Bike Initialization ===");
        premiumBike.ride(); // Validate ride eligibility
        premiumBike.printBikeDetails();

        // Additional edge case demonstration (invalid battery set)
        System.out.println("\n=== Demonstration 3: Invalid Battery Level Test ===");
        premiumBike.setBatteryLevel(110); 
        premiumBike.setBatteryLevel(-5);
        premiumBike.setBatteryLevel(75);
        premiumBike.printBikeDetails();
    }
}