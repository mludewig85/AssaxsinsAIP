package lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;
import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport; 

public class GnereateList {
    
	public static void main(String[] args) 
	{
	    ArrayList<NamesList> names = new ArrayList<NamesList>();
	    
	    Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of players for the game:");
        
        int numPlayers = s.nextInt();
        
        String test[][] = new String[numPlayers][numPlayers];
        for(int i = 0; i < numPlayers; i++) {
        	String name;
        	String email;
        	System.out.print("Enter the name of player " + (i + 1) + ": ");
        	name = s.next();
        	System.out.print("Enter the email of " + name + ": ");
        	email = s.next();
        	
        	NamesList temp = new NamesList(name, email);
        	
        	names.add(temp);
        	
        	
        	
        }
        
        String senderEmail;
        System.out.print("Enter the gmail adress that you want to send the emails from ");
        
        senderEmail = s.next();
        
        String senderPassword;
        
        System.out.print("Enter the password for that email");
        senderPassword = s.next();
        
        s.close();
        

        
        Collections.shuffle(names);
       
        
        for(int i = 0; i <numPlayers; i++) {
        	
        	
        	//String sender = "mludewig@iastate.edu";
        	
        	
        	Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            String[] tokens = senderEmail.split("@");
            props.put("mail.stmp.user", tokens[0]);
            //props.put("mail.stmp.user", "mludewig");
            
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                                      "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                 @Override
                      protected PasswordAuthentication getPasswordAuthentication() {
                         String username = senderEmail;
                         String password = senderPassword;
                      return new PasswordAuthentication(username,password); 
                      }
               });
        	
//        	// using host as localhost 
//            String host = "127.0.0.1"; 
//        
//            // Getting system properties 
//            Properties properties = System.getProperties(); 
//        
//            // Setting up mail server 
//            properties.setProperty("mail.smtp.host", host); 
//        
//            // creating session object to get properties 
//            Session session = Session.getDefaultInstance(properties); 
            
            
            try {
            //MimeMessage object. 
            MimeMessage message = new MimeMessage(session); 
     
            // Set From Field: adding senders email to from field. 
            message.setFrom(new InternetAddress(senderEmail)); 
            
            NamesList temp = names.get(i);
        	//System.out.println(temp.getName() + " " + temp.getEmail());
        	
        	String reciver = temp.getEmail();
     
            // Set To Field: adding recipient's email to from field. 
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(reciver)); 
     
            // Set Subject: subject of the email 
            message.setSubject("Assaxsin Target"); 
     
            // set body of the email.
            if(i == numPlayers - 1 ) {
            	message.setText("Your Target is: " + names.get(0).getName());
            }
            else {
            	message.setText("Your Target is: " + names.get(i+1).getName());
            }
             
     
            // Send email. 
            Transport.send(message); 
            System.out.println("Mail successfully sent"); 
         } 
         catch (MessagingException mex)  
         { 
            mex.printStackTrace(); 
         } 
        }
    }
}
