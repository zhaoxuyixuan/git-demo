public class Feedback {
    // private variables
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;
    
    //Initialization firstName、lastName and email
    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
   // public method: analyseFeedback
    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2, 
                                String sent3, String sent4, String sent5) {
        // change the value of LongFeedback based on the length of completeFeedback
        int option = isConcatenation ? 1 : 0;
        
        switch (option) {
            case 1:
                // use string concatenation
                this.completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
                checkFeedbackLength(this.completeFeedback);
                createReviewID(this.firstName, this.lastName, this.completeFeedback);
                break;
                
            case 0:
                // use StringBuilder
                StringBuilder sb = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
                this.completeFeedback = sb.toString();
                checkFeedbackLength(this.completeFeedback);
                createReviewID(this.firstName, this.lastName, this.completeFeedback);
                break;
        }
    }
    // private method: using string concatenation
    private String feedbackUsingConcatenation(String sent1, String sent2, String sent3, 
                                              String sent4, String sent5) {
        String concatenatedFeedback = sent1 + sent2 + sent3 + sent4 + sent5;
        return concatenatedFeedback;
    }
    
    // private method：using StringBuilder
    private StringBuilder feedbackUsingStringBuilder(String sent1, String sent2, String sent3, 
                                                     String sent4, String sent5) {
        StringBuilder sb = new StringBuilder();
        sb.append(sent1);
        sb.append(sent2);
        sb.append(sent3);
        sb.append(sent4);
        sb.append(sent5);
        return sb;
    }
    
   // private method: check feedback length
    private boolean checkFeedbackLength(String completeFeedback) {
        if (completeFeedback.length() > 500) {
            return true;
        } else {
            return false;
        }
    }
    
    // private method: create review ID
    private void createReviewID(String firstName, String lastName, String completeFeedback) {
        // extract the substring from the combination of firstName and lastName (indices 2 to 6) and convert to uppercase
        String namePart = (firstName + lastName).substring(2, 6).toUpperCase();
        
        // extract the substring from completeFeedback (indices 10 to 15) and convert to lowercase
       String feedbackPart = "";
        if (completeFeedback.length() >= 16) { //ensure there are enough characters to extract
            feedbackPart = completeFeedback.substring(10, 16).toLowerCase();
        } else { // if not enough characters, extract from index 10 to the end of the string
            feedbackPart = completeFeedback.substring(Math.min(10, completeFeedback.length())).toLowerCase();
        }
        
        // get the length of the feedback
        String lengthPart = String.valueOf(completeFeedback.length());
        
        // get the system timestamp
        String timeStamp = String.valueOf(System.currentTimeMillis());
        
        // combine all parts
        String rawReviewID = namePart + feedbackPart + lengthPart + "_" + timeStamp;
        
        // remove all spaces
        this.reviewID = rawReviewID.replace(" ", "");
    }
    
    // toString method
    @Override
    public String toString() {
        return "feedback information：\n" +
               "First Name：" + firstName + "\n" +
               "Last Name：" + lastName + "\n" +
               "Email：" + email + "\n" +
               "Complete Feedback：" + completeFeedback + "\n" +
               "Is Long Feedback（>500 characters）：" + longFeedback + "\n" +
               "Review ID：" + reviewID;
    }
    
    // main method
    public static void main(String[] args) {
        // create sample feedback sentences
        String sent1 = "I was very satisfied with the service.";
        String sent2 = "The e-Bike is quite comfortable to ride.";
        String sent3 = "The battery life of the e-Bike is impressive.";
        String sent4 = "The customer support was helpful and responsive.";
        String sent5 = "I would recommend this e-Bike to my friends and family.";
        
        // create a Feedback object and call the analyseFeedback method
        Feedback feedback = new Feedback("John", "Doe", "john.doe@email.com");
        
        // call the analyseFeedback method, setting isConcatenation to true (using string concatenation)
        System.out.println("=== Using String Concatenation ===");
        feedback.analyseFeedback(true, sent1, sent2, sent3, sent4, sent5);
        System.out.println(feedback.toString());
        
        System.out.println("\n=== Using StringBuilder ===");
        // call the analyseFeedback method again, setting isConcatenation to false (using StringBuilder)
        // reset the feedback before calling the method again
        feedback.analyseFeedback(false, sent1, sent2, sent3, sent4, sent5);
        System.out.println(feedback.toString());
    }
}
