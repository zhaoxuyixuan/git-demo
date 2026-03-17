import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration {
    // final variables
    private final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    private final double VIP_DISCOUNT_UNDER_18 = 20.0;
    private final double VIP_BASE_FEE = 100.0;
    
    // private variables
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailValid;
    private boolean minorAndBirthday;
    private boolean minor;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;

    //registration method
    public void registration() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("Here are your two options:");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.print("Please enter your choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            userType = "Regular User";
        } else {
            userType = "VIP User";
        }
        //name
        System.out.print("Enter your full name: ");
        fullName = scanner.nextLine();
        //email
        System.out.print("Enter your email address: ");
        emailAddress = scanner.nextLine();
        emailValid = analyseEmail(emailAddress);
        // date of birth
        System.out.print("Enter your date of birth (YYYY-MM-DD): ");
        dateOfBirth = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);
       
        // card number
        System.out.print("Enter your card number (VISA, MasterCard, American Express only): ");
        cardNumber = scanner.nextLong();
        scanner.nextLine();
        cardNumberValid = analyseCardNumber(cardNumber);
       
        // card expiry date
        System.out.print("Enter your card expiry date (MM/YY): ");
        cardExpiryDate = scanner.nextLine();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);
        
        // card CVV
        System.out.print("Enter your card CVV: ");
        cvv = scanner.nextInt();
        scanner.nextLine();
        validCVV = analyseCVV(cvv);

        // final checkpoint
        finalCheckpoint();
        scanner.close();
    }
    // private method: analyse email
    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address. Going back to the start of the registration");
            registration();
            return false;
        }
    }
    // private method: analyse age
    private boolean analyseAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dob, currentDate).getYears();
        
        boolean isBirthday = (dob.getMonth() == currentDate.getMonth() && 
                              dob.getDayOfMonth() == currentDate.getDayOfMonth());
        
        if (userType.equals("VIP User")) {
            if (age <= 18 && age > 12 && isBirthday) {
                System.out.println("Happy Birthday!");
                System.out.println("You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                minorAndBirthday = true;
            } else if (age <= 18 && age > 12 && !isBirthday){
                System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                minor = true;
            }
        }
        
        if (age <= 12 || age > 120) {
            System.out.println("Looks like you are either too young or already dead. Sorry, you can't be our user. Have a nice day");
            return false;
        }
        
        return true;
    }
    // private method: analyse card number
   private boolean analyseCardNumber(long cardNum) {
        String cardNumStr = String.valueOf(cardNum);
        cardNumStr.length();
        
        if (cardNumStr.length()>= 2) {
            String firstTwoStr = cardNumStr.substring(0, 2);
            int firstTwoDigits = Integer.parseInt(firstTwoStr);
            
            int firstFourDigits = 0;
            if (cardNumStr.length() >= 4) {
                String firstFourStr = cardNumStr.substring(0, 4);
                firstFourDigits = Integer.parseInt(firstFourStr);
            }
            // check VISA
            if ((cardNumStr.length() == 13 || cardNumStr.length() == 15 || cardNumStr.length() == 16) 
                    && cardNumStr.startsWith("4")) {
                cardProvider = "VISA";
                return true;
            }
            
            // check MasterCard
            else if (cardNumStr.length() == 16 && 
                    ((firstTwoDigits >= 51 && firstTwoDigits <= 55) || 
                     (firstFourDigits >= 2221 && firstFourDigits <= 2720))) {
                cardProvider = "MasterCard";
                return true;
            }
            
            // check American Express
            else if (cardNumStr.length() == 15 && 
                    (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
                cardProvider = "American Express";
                return true;
            }else{
                System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.");
                System.out.println("Going back to the start of the registration.");
                registration();
                return false;
             }
        }
        return ageValid;
    }
    // check feedback length and create review ID
    private boolean analyseCardExpiryDate(String expiryDate) {
        String monthStr = expiryDate.substring(0, 2);
        String yearStr = expiryDate.substring(3, 5);
        
        int month = Integer.parseInt(monthStr);
        int year = Integer.parseInt(yearStr) + 2000;
        
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        
        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("The card is still valid");
            return true;
        } else {
            System.out.println("Sorry, your card has expired. Please use a different card.");
            System.out.println("Going back to the start fo the registration process…");
            registration();
            registration();
            return false;
        }
    }
    // private method: analyse CVV
    private boolean analyseCVV(int cvvNum) {
        String cvvStr = Integer.toString(cvvNum);
        
        if ((cardProvider.equals("American Express") && cvvStr.length() == 4) ||
            (cardProvider.equals("VISA") && cvvStr.length() == 3) ||
            (cardProvider.equals("MasterCard") && cvvStr.length() == 3)) {
            System.out.println("Card CVV is valid.");
            return true;
        } else {
            System.out.println("Invalid CVV for the given card.");
            System.out.println("Going back to the start of the registration process.");
            registration();
            return false;
        }
    }
    // private method: final checkpoint
   private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        } else {
            System.out.println("Sorry, your registration was unsuccessful due to the following reason(s)");
            if (!emailValid) System.out.println("Invalid email address");
            if (!ageValid) System.out.println("Invalid age");
            if (!cardNumberValid) System.out.println("Invalid card number");
            if (!cardStillValid) System.out.println("Card has expired");
            if (!validCVV) System.out.println("Invalid CVV");
            System.out.println("Going back to the start of the registration process.");
            registration();
        }
    }
    // private method: charge fees
    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY / 100);
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18 / 100);
        } else {
            feeToCharge = VIP_BASE_FEE;
        }
        
        String cardNumberStr = Long.toString(cardNumber);
        String lastFourDigitsString = cardNumberStr.substring(cardNumberStr.length() - 4);
        
        System.out.println("Thank you for your payment.");
        System.out.println("A fee of " + feeToCharge + " has been charged to your card ending with ****");
    }
    
    // override toString method to display user information
    @Override
    public String toString() {
       String cardNumberStr = String.valueOf(cardNumber);
        String censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;
        
        return "\nRegistration successful! Here are your details:\n" +
               "User Type: " + userType + "\n" +
               "Full Name: " + fullName + "\n" +
               "Email Address: " + emailAddress + "\n" +
               "Date of Birth: " + dateOfBirth + "\n" +
               "Card Number: " + censoredNumber + "\n" +
               "Card Provider: " + cardProvider + "\n" +
               "Card Expiry Date: " + cardExpiryDate + "\n";
    }
}