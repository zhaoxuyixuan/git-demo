// Core class for eRyder e-bike sharing service
public class ERyder {
    // Required instance variables
    private String bikeID;       // Unique ID for each bike
    private int batteryLevel;    // Battery level (0-100%)
    private boolean isAvailable; // Availability status (true = available)
    private double kmDriven;     // Total distance traveled (kilometers)

    // Default constructor (no parameters)
    public ERyder() {
        // Default values for testing
        this.bikeID = "DEFAULT-000";
        this.batteryLevel = 100;  // Full battery by default
        this.isAvailable = true;  // Available by default
        this.kmDriven = 0.0;      // 0 km driven initially
    }

    // Parameterized constructor (initializes all variables)
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        // Use validated setter for battery level
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }

    // ride() method: Check battery and availability
    public void ride() {
        // Bike is usable only if battery > 0 AND available
        if (this.batteryLevel > 0 && this.isAvailable) {
            System.out.println("Ride successful! The bike is available.");
        } else {
            System.out.println("Ride failed! The bike is not available (low battery or already in use).");
        }
    }

    // printBikeDetails() method: Print all bike information
    public void printBikeDetails() {
        System.out.println("===== Bike Details =====");
        System.out.println("Bike ID: " + this.bikeID);
        System.out.println("Battery Level: " + this.batteryLevel + "%");
        System.out.println("Availability: " + (this.isAvailable ? "Available" : "Unavailable"));
        System.out.println("Total KM Driven: " + this.kmDriven + " km");
        System.out.println("========================");
    }

    // Getters and Setters (with validation for batteryLevel)
    // Getter for bikeID
    public String getBikeID() {
        return bikeID;
    }

    // Setter for bikeID
    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    // Getter for batteryLevel
    public int getBatteryLevel() {
        return batteryLevel;
    }

    // Setter for batteryLevel (validates 0-100 range)
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            // Default to 0% if invalid value is provided
            System.out.println("Error: Battery level must be between 0 and 100! Set to 0% by default.");
            this.batteryLevel = 0;
        }
    }

    // Getter for isAvailable
    public boolean isAvailable() {
        return isAvailable;
    }

    // Setter for isAvailable
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Getter for kmDriven
    public double getKmDriven() {
        return kmDriven;
    }

    // Setter for kmDriven
    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }
     public static void main(String[] args) {
        // 1. Create object with default constructor and print details
        System.out.println("=== Testing Bike with Default Constructor ===");
        ERyder bike1 = new ERyder();
        bike1.printBikeDetails();

        // 2. Create object with parameterized constructor
        // Call ride() and printBikeDetails()
        System.out.println("\n=== Testing Bike with Parameterized Constructor ===");
        ERyder bike2 = new ERyder("EB-001", 80, true, 15.5);
        bike2.ride();
        bike2.printBikeDetails();

        // Optional: Test battery level validation
        System.out.println("\n=== Testing Battery Level Validation ===");
        bike2.setBatteryLevel(120); // Invalid value (120%)
        System.out.println("Battery level after validation: " + bike2.getBatteryLevel() + "%");
    }
}
// Main class to test the ERyder e-bike service

   

