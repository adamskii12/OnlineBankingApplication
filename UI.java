
package AdamsDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * UI (User Interface)
 *
 * This class contains the main method that provides the user an interface to interact with the database.
 * 
 *
 *
 * @author Adam Ierullo-Woloszczak
 *
 * November 27, 2021
 */

public class UI {
       private static Scanner user = new Scanner(System.in); //used for reading user input
       static Connection myCon = null; 
       static Statement myStat = null;
       static ResultSet myRes = null;  
       static TableCommander commander = new TableCommander();//this commander object will execute the necessary commands on the database
       
       public static void main(String[] args) {
           
       int answer=100; //for user's answer, initialized to an unused answer option to avoid compilation error
       
       while (!(answer==0)){    //continues to give the user the menu as long as their answer isn't 0, which ends the program
       System.out.println("Choose a number:\n\n 1)Create Tables\n"
               + " 2)Populate Tables\n 3)Drop Tables\n 4)Show all customers\n"
               + " 5)Search for a customer by last name\n 6)Delete a customer\n 7)Change customer password\n"
               + " 8)Show customers with account balances greater than $1,000,000\n"
               + " 9)Show all branches located in the city of Toronto\n"
               + " 10)Insert a new customer\n"
               + " 11)Show all accounts\n"
               + " 12)Show all employees\n"
               + " 0)End the program\n ");
       
       answer= user.nextInt(); //the user's answer
       
       
       if (answer==1) {
           commander.createAllTables();
                             
           System.out.println("\n\nTABLES CREATED\n\n");} 
       
       
       if (answer==2) {
           commander.populateTables();
           System.out.println("\n\nTABLES POPULATED\n\n");} 
       
       
       if (answer==3) {
           commander.dropAllTables();
           System.out.println("\n\nTABLES DROPPED\n\n");} 
       
       
       if (answer==4) {
           commander.queryShowAllCustomers();
           System.out.println("\n\n");}
       
       
       if (answer==5) {
           String lastName = ""; //used to store user's input
           
           System.out.println("\n\nEnter the last name of the customer (case sensitive):\n\n");
           lastName=user.next();
           commander.queryCustomerSearchByLastName(lastName);}
       
       
       if (answer==6) {
           int response; //used to store user's input
           
           System.out.println("\n\nEnter the customer number of the customer:\n\n");
           response=user.nextInt();
           commander.queryCustomerSearchByCusNumber(response); //the customer with the associated number (if exists) is outputted
           
           System.out.print("\n\nDelete this customer?\n\n1)Yes\n2)No\n\n"); //asks the user for confirmation that the correct customer is to be deleted
           if(user.nextInt()==1){
                commander.deleteCustomerByNumber(response);
           System.out.println("CUSTOMER DELETED\n");}   
       }
       
       if (answer==7) {
           String cusUsername; //store's user input
           
           System.out.println("\n\nEnter the username of the customer:\n\n");
           cusUsername=user.next();
           commander.queryUserSearchByUsername(cusUsername);
           
           System.out.print("\n\nEnter the new password (enter q to return to menu):\n\n");
           String newPass = user.next();
           
           if(!("q".equals(newPass))){ //if answer is NOT q, password is changed
              commander.changePassword(cusUsername, newPass);
              System.out.println("PASSWORD CHANGED\n\n");}}
       
 
       if (answer==8) {
           commander.queryCustomersWithAccountBalanceOverOneMillionDollars();}
       
       
       if (answer==9) {
           commander.queryBranchesInToronto();}           
       
       
       if (answer==10) {
           String firstName; //store's first name of new customer 
           String lastName; //store's last name of new customer 
           String username; //store's username of new customer 
           String password; //store's password of new customer 
           String email; //store's email of new customer 
           
           System.out.println("\n\nEnter the first name of the customer:\n\n");
           firstName=user.next();
           
           System.out.println("\n\nEnter the last name of the customer:\n\n");
           lastName=user.next();
           
           System.out.println("\n\nEnter a username for the customer:\n\n");
           username=user.next();
           
           System.out.println("\n\nEnter a password for the customer:\n\n");
           password=user.next();
           
           System.out.println("\n\nEnter the email of the customer:\n\n");
           email=user.next();
           
           commander.insertNewCustomer(firstName, lastName, username, password, email); //inserts the new customer into the database
           
           System.out.println("\n\nCUSTOMER ADDED\n");
           }
       
       
       if (answer==11) {
           commander.queryShowAllAccounts();}    
       
       
       if (answer==12) {
           commander.queryShowAllEmployees();}
       
       } //end of while loop
       
         System.out.println("\n\nProgram Ended.\n");
}
}