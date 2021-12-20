
package AdamsDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
/*
 * TABLE COMMANDER
 *
 * This class allows us to execute the required queries and commands on the database using 
 * SQL statements. An object of this class is created in the main function of the UI class. 
 * When the user decides to execute a command from the menu, the TableCommander object
 * is called along with the associated function, based off the user input.
 * 
 *
 * @author Adam Ierullo-Woloszczak
 *
 * November 27, 2021
 */

public class TableCommander {
        final String url = "jdbc:mysql://localhost:3306/mysql"; //used to connect to database
        final String username="root"; //username used to connect to database
        final String password="9384103"; //password used to connect to database
        String sql = ""; //used to store different SQL commands


        
        protected TableCommander(){}
        
         
        //connects to the database and executes a given SQL command from the parameter
        protected void executeSQLCommand(String sql){
            try (Connection conn = DriverManager.getConnection(url,username, password);Statement stmt = conn.createStatement()){
                stmt.execute(sql);} 
    
            catch (SQLException e){ e.printStackTrace();} //if an error occurs while trying to connect, the exception is caught and stack trace is printed
        } 
        
        
        //creates the CUSTOMER table in the database
        protected void createCustomerTable(){
            sql = "CREATE TABLE customer (" +
                  "CusNumber INT PRIMARY KEY AUTO_INCREMENT,\n" +
                  "CusEmail VARCHAR(40),\n" +
                  "FirstName VARCHAR(40),\n" +
                  "LastName VARCHAR(50)," +
                  "UserID INT,\n"+      
                  "CONSTRAINT fk_customerUser FOREIGN KEY (UserID) REFERENCES users(UserID) ON DELETE CASCADE)";
                   
            
            executeSQLCommand(sql);
        
            sql = "ALTER TABLE customer AUTO_INCREMENT=4000";
            executeSQLCommand(sql); //sets first CusNumber to 4000 and increment by one each time another CUSTOMER record is added
        }          
       
        
        
        //creates the USERS table in the database
        protected void createUserIDTable(){
            sql = "CREATE TABLE users (" + //'user' is a reserved word in SQL so had to make it plural
                  "UserID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                  "Username VARCHAR(20) UNIQUE,\n" +
                  "Password VARCHAR(20))"; 
            
            executeSQLCommand(sql);
        
            sql = "ALTER TABLE users AUTO_INCREMENT=1";
            executeSQLCommand(sql); //sets first UserID to start at 1 and increment by one each time another USERS record is added
        }
    
        
        
        //creates the CREDIT CARD table in the database
        protected void createCreditCardTable(){
            sql = "CREATE TABLE creditcard (" +
                  "CardNumber BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
                  "CusNumber INT,\n" +
                  "CreditLimit FLOAT(2),\n" +
                  "Balance FLOAT(2),\n"+ 
                  "CONSTRAINT fk_ccCusNumber FOREIGN KEY (CusNumber) REFERENCES customer(CusNumber) ON DELETE CASCADE )"; 
           
            executeSQLCommand(sql);
        
            sql = "ALTER TABLE creditcard AUTO_INCREMENT=4505445700000000";
            executeSQLCommand(sql); //sets first CardNumber and increments by one each time another CREDITCARD record is added
        
            }           
    
        
        
        //creates the BRANCH LOCATION table in the database
        //
        //differentiates all branch locations
        protected void createBranchLocationTable(){
            sql = "CREATE TABLE branchlocation (" +
                  "BranLocationID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                  "BranAddress VARCHAR(30),\n" +
                  "BranCity VARCHAR(30),\n"+ 
                  "BranPostalCode VARCHAR(7))"; 
           
            executeSQLCommand(sql);
        
            sql = "ALTER TABLE branchlocation AUTO_INCREMENT=1";
            executeSQLCommand(sql); //sets first BranLocationID to start at 1 and increment by one each time another BRANCHLOCATION record is added
        }         
    
        
        
        //creates the BRANCH table in the database
        protected void createBranchTable(){
            sql = "CREATE TABLE branch (" +
                  "BranNumber INT PRIMARY KEY AUTO_INCREMENT,\n" +
                  "BranLocationID INT,\n" +
                  "EmpNumber INT,\n"+ //manager of the branch
                  "CONSTRAINT fk_branchLocation FOREIGN KEY (BranLocationID) REFERENCES branchlocation(BranLocationID) ON DELETE CASCADE,\n"+ 
                  "CONSTRAINT fk_branchEmp FOREIGN KEY (EmpNumber) REFERENCES employee(EmpNumber) ON DELETE CASCADE)"; 
           
            executeSQLCommand(sql);
        
            sql = "ALTER TABLE branch AUTO_INCREMENT=1";
            executeSQLCommand(sql); //sets first BranNumber to start at 1 and increment by one each time another BRANCH record is added
        }            
    
        
        
        //creates the EMPLOYEE table in the database
        protected void createEmployeeTable(){
            sql = "CREATE TABLE employee (" +
                  "EmpNumber INT PRIMARY KEY AUTO_INCREMENT,\n" +
                  "FirstName VARCHAR(30),\n" +
                  "LastName VARCHAR(40),\n" +
                  "EmpRole VARCHAR(30),\n" +
                  "EmpSalary FLOAT (2) ,\n" +
                  "EmpYears INT)"; 
          
            executeSQLCommand(sql);
        
            sql = "ALTER TABLE employee AUTO_INCREMENT=1";
            executeSQLCommand(sql); //sets first EmpNumber to start at 1 and increment by one each time another EMPLOYEE record is added
        }
    
         
         
          //creates the ACCOUNT TYPE table in the database
          //
          //this table is used to differentiate account types, for example:
          // AccountTypeID: 1      AccountType: Chequing
          // AccountTypeID: 2      AccountType: Savings
        protected void createAccountTypeTable(){
            sql = "CREATE TABLE accounttype (" +
                  "AccountTypeID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                  "AccountType VARCHAR(30))"; 
            
            executeSQLCommand(sql); 
            
            sql = "ALTER TABLE accounttype AUTO_INCREMENT=1";
            executeSQLCommand(sql); //sets first AccountTypeID to start at 1 and increment by one each time another ACCOUNTTYPE record is added
        
    }
         
          
          
          //creates the ACCOUNT table in the database
        protected void createAccountTable(){
            sql = "CREATE TABLE account (" +
                  "AccNumber INT PRIMARY KEY AUTO_INCREMENT,\n" +
                  "AccTypeID INT,\n" +
                  "Balance FLOAT(2),\n" +
                  "BranNumber INT,\n"+ //the branch where the account is located
                  "CusNumber INT,\n"+  //to identify the customer   
                  "CONSTRAINT fk_accountType FOREIGN KEY (AccTypeID) REFERENCES accounttype(AccountTypeID) ON DELETE CASCADE,\n" +
                  "CONSTRAINT fk_accountBranch FOREIGN KEY (BranNumber) REFERENCES branch(BranNumber) ON DELETE CASCADE,\n" +
                  "CONSTRAINT fk_accountCusNumber FOREIGN KEY (CusNumber) REFERENCES customer(CusNumber) ON DELETE CASCADE)"; 
           
            executeSQLCommand(sql);
        
            sql = "ALTER TABLE account AUTO_INCREMENT=500000";
            executeSQLCommand(sql); //sets first AccNumber to start at 500000 and increment by one each time another ANOTHER record is added
        }
    
          
          
          //creates the CHEQUING ACCOUNT table in the database
        protected void createChequingAccountTable(){
            sql = "CREATE TABLE chequing (" +
                 "AccNumber INT,\n" +
                 "OverdraftAmount FLOAT(2),\n" + 
                 "OverdraftInterestRate FLOAT(2),\n" + 
                 "MonthlyFee FLOAT(2),\n" + 
                 "CONSTRAINT fk_chequingAccNum FOREIGN KEY (AccNumber) REFERENCES account(AccNumber) ON DELETE CASCADE)"; 
            
            executeSQLCommand(sql); 
    }  
          
          
          //creates the SAVINGS ACCOUNT table in the database
        protected void createSavingsAccountTable(){
            sql = "CREATE TABLE savings (" +
                 "AccNumber INT,\n" +
                 "OverdraftAmount FLOAT(2),\n" + 
                 "InterestRate FLOAT(3),\n" + 
                 "CONSTRAINT fk_savingsAccNum FOREIGN KEY (AccNumber) REFERENCES account(AccNumber) ON DELETE CASCADE)"; 
           
            executeSQLCommand(sql); 
    }
          
          
          //creates the TRANSACTIONS table in the database
        protected void createTransactionsTable(){
            sql = "CREATE TABLE transactions(" + //using transactions plural as 'transaction' is a reserved word in SQL
                  "TranNumber INT PRIMARY KEY AUTO_INCREMENT,\n" +
                  "Amount FLOAT(2),\n" +
                  "DateYear INTEGER CHECK (DateYear BETWEEN 1900 AND 2022),\n" +
                  "DateMonth INTEGER CHECK (DateMonth BETWEEN 01 AND 12),\n" +
                  "DateDay INTEGER CHECK (DateDay BETWEEN 01 AND 31))";
                   
            executeSQLCommand(sql);   
            
            sql = "ALTER TABLE transactions AUTO_INCREMENT=1";
            executeSQLCommand(sql); //sets first AccNumber to start at 5000000 and increment by one each time another ANOTHER record is added
        
    }
          
          
          //creates the DEPOSIT table in the database
        protected void createDepositTable(){
            sql = "CREATE TABLE deposit(" +
                  "TranNumber INT,\n" +
                  "AccountNumberTo INT,\n"+ 
                  "CONSTRAINT fk_depositTranNumber FOREIGN KEY (TranNumber) REFERENCES transactions(TranNumber) ON DELETE CASCADE,\n"+
                  "CONSTRAINT fk_depositAccNumTo FOREIGN KEY (AccountNumberTo) REFERENCES account(AccNumber) ON DELETE CASCADE)";
   
            executeSQLCommand(sql);           
    }
          
          
          //creates the WITHDRAWAL table in the database
        protected void createWithdrawalTable(){
            sql = "CREATE TABLE withdrawal(" +
                  "TranNumber INT,\n" +
                  "AccountNumberFrom INT,\n"+ 
                  "CONSTRAINT fk_withdrawalTranNumber FOREIGN KEY (TranNumber) REFERENCES transactions(TranNumber) ON DELETE CASCADE,\n"+
                  "CONSTRAINT fk_withdrawalAccNumFrom FOREIGN KEY (AccountNumberFrom) REFERENCES account(AccNumber) ON DELETE CASCADE)";
   
            executeSQLCommand(sql);           
    }
          
          
          //creates the TRANSFER table in the database
        protected void createTransferTable(){
            sql = "CREATE TABLE transfer(" +
                  "TranNumber INT,\n" +
                  "AccountNumberFrom INT,\n"+ 
                  "AccountNumberTo INT,\n"+ 
                  "CONSTRAINT fk_transferAccNumTo FOREIGN KEY (AccountNumberTo) REFERENCES account(AccNumber) ON DELETE CASCADE,\n"+
                  "CONSTRAINT fk_transferTranNumber FOREIGN KEY (TranNumber) REFERENCES transactions(TranNumber) ON DELETE CASCADE,\n"+
                  "CONSTRAINT fk_transferAccNumFrom FOREIGN KEY (AccountNumberFrom) REFERENCES account(AccNumber) ON DELETE CASCADE)";
   
            executeSQLCommand(sql);           
    }
          
          
          //creates the CREDIT CARD PAYMENT table in the database
        protected void createCreditCardPaymentTable(){
            sql = "CREATE TABLE creditcardpayment(" +
                  "TranNumber INT,\n" +
                  "AccountNumberFrom INT,\n"+ 
                  "CreditCardNumber BIGINT,\n"+ 
                  "CONSTRAINT fk_ccpaymentAccNumberFrom FOREIGN KEY (AccountNumberFrom) REFERENCES account(AccNumber) ON DELETE CASCADE,\n"+
                  "CONSTRAINT fk_ccpaymentTranNumber FOREIGN KEY (TranNumber) REFERENCES transactions(TranNumber) ON DELETE CASCADE,\n"+
                  "CONSTRAINT fk_ccpaymentCCNumber FOREIGN KEY (CreditCardNumber) REFERENCES creditcard(CardNumber) ON DELETE CASCADE)";
   
            executeSQLCommand(sql);           
    }
          
          
          //creates all the tables in the database at once
        protected void createAllTables(){
            createUserIDTable();
            createCustomerTable();
            createCreditCardTable();
            createEmployeeTable();
            createBranchLocationTable();
            createBranchTable();
            createAccountTypeTable();
            createAccountTable();
            createChequingAccountTable();
            createSavingsAccountTable();
            createTransactionsTable(); 
            createDepositTable();
            createWithdrawalTable();
            createTransferTable();
            createCreditCardPaymentTable();
     }

        
        
          //drops all tables in the database at once
        protected void dropAllTables(){
            dropAllConstraints(); //first we drop all constraints on all tables to avoid compilation/syntax errors
            
            //now we make a list of all our table names
            List<String> tableNames = Arrays.asList("customer", "users", "creditcard", "branchlocation",
                    "branch", "employee", "accounttype", "account", "chequing", "transactions",
                    "savings", "deposit", "withdrawal", "transfer", "creditcardpayment");
                
                //since there's 15 tables, we will run the loop 15 times, inserting each table name in 
                //the SQL delete statemenet so it can be deleted
                for(int i=0; i<15;i++){
               
                     try (Connection conn = DriverManager.getConnection(url,username, password);Statement stmt = conn.createStatement()){
                           sql= "DROP TABLE "+tableNames.get(i);
                           stmt.execute(sql); //table is deleted
                             
                        } 
     
                     catch (SQLException e){ e.printStackTrace();}
                    }
          }
           
          
           //tables are populated with dummy/test values
        protected void populateTables(){
               
                sql = "INSERT INTO users VALUES (NULL, 'adamski', '12345')"; 
                   executeSQLCommand(sql);
                   
                sql = "INSERT INTO customer VALUES (NULL,'adam@ryerson.ca', 'Adam', 'Ierullo', 1)"; 
                   executeSQLCommand(sql);

                   
                sql = "INSERT INTO users VALUES (NULL, 'arman', '12345')"; 
                   executeSQLCommand(sql);
            
                sql = "INSERT INTO customer VALUES (NULL, 'arman@ryerson.ca','Arman','Kahrizi', 2)"; 
                   executeSQLCommand(sql);
                   
   
                sql = "INSERT INTO users VALUES (NULL, 'megan', '12345')"; 
                   executeSQLCommand(sql);
                
                sql = "INSERT INTO customer VALUES (NULL, 'megan@ryerson.ca', 'Megan', 'Mac', 3)"; 
                   executeSQLCommand(sql);
                

                sql = "INSERT INTO users VALUES (NULL, 'madeed', '12345')"; 
                   executeSQLCommand(sql);
               
                sql = "INSERT INTO customer VALUES (NULL, 'madeed@ryerson.ca', 'Madeed', 'Srosh', 4)"; 
                   executeSQLCommand(sql);
            
             
                sql = "INSERT INTO employee VALUES (NULL, 'John', 'Smith', 'Teller', 50000, 1)"; 
                   executeSQLCommand(sql);
                   
                sql = "INSERT INTO employee VALUES (NULL, 'Mike', 'Nunes', 'Executive', 100000, 5)"; 
                   executeSQLCommand(sql);
            
                sql = "INSERT INTO branchlocation VALUES (NULL, '350 Victoria Street', 'Toronto', 'M5B 2K3')"; 
                   executeSQLCommand(sql);
            
                sql = "INSERT INTO branch VALUES (NULL, 1 ,2)"; 
                   executeSQLCommand(sql);
                   
                sql = "INSERT INTO accounttype VALUES (NULL, 'Chequing')"; 
                   executeSQLCommand(sql);
            
                sql = "INSERT INTO accounttype VALUES (NULL, 'Savings')"; 
                   executeSQLCommand(sql);   
                 
                sql = "INSERT INTO accounttype VALUES (NULL, 'Business')"; 
                   executeSQLCommand(sql); 
                   
                sql = "INSERT INTO account VALUES (NULL, 2, 2000312.31, 1, 4001)"; 
                   executeSQLCommand(sql);
                
                sql = "INSERT INTO savings VALUES (500000, 500, 0.758)"; 
                   executeSQLCommand(sql); 
            
                sql = "INSERT INTO account VALUES (NULL, 1, 938192, 1, 4002)"; 
                   executeSQLCommand(sql);  
                   
                sql = "INSERT INTO chequing VALUES (500001, 200, 22.99, 10)"; 
                   executeSQLCommand(sql);
     
                sql = "INSERT INTO creditcard VALUES (NULL, 4000, 10000, 0)"; 
                   executeSQLCommand(sql);
                   
                sql = "INSERT INTO transactions VALUES (NULL, 500, 2021, 9, 20)"; 
                   executeSQLCommand(sql);
                   
                sql = "INSERT INTO deposit VALUES (1, 500000)"; 
                   executeSQLCommand(sql);
            }
           
           
        
           //Used in the basic UI when the user creates a new customer
        protected void insertNewCustomer(String firstName, String lastName, String cusUsername, String cusPassword, String email){
                ResultSet result=null;
                //first we use the username and password from user to create a new USERS record
                sql = "INSERT INTO users VALUES (NULL, '"+cusUsername+"', '"+ cusPassword+"')"; 
                    executeSQLCommand(sql);                   

                    
                //now we need to obtain the correct new UserID in order to insert a new CUSTOMER record
                String query="SELECT UserID FROM users WHERE Username = '"+cusUsername+"'";
                
                int cusUserID=0; //initialize the customer's UserID to zero
                
                try (Connection conn = DriverManager.getConnection(url,username, password);Statement stmt = conn.createStatement()){
                     result= stmt.executeQuery(query); 
                while(result.next()){ 
 
                     cusUserID=result.getInt("UserID"); //stores the customer's UserID 

                 }}
                catch (SQLException e){ e.printStackTrace();}
                
                //now that we have the correct UserID, we can insert the value into the UserID field of the new CUSTOMER record
                sql = "INSERT INTO customer VALUES (NULL,'"+email+"','"+firstName+"','"+lastName+"',"+cusUserID+")"; 
                    executeSQLCommand(sql);

           }
           
        
           //SQL query that shows all customers in database
        protected void queryShowAllCustomers(){
                sql = "SELECT CusNumber, FirstName, LastName "+
                      "FROM customer ORDER BY CusNumber ASC"; //query to get the CusNumber, First and Last name of all the customers, in order of ascending customer number
              
                ResultSet result=null;
             
                //forming a table for the query results
                System.out.print("\n\n"); 
                System.out.println("Customer#"+"\t "+"First Name"+"\t "+"Last Name");
                System.out.println("---------"+"\t "+"----------"+"\t "+"---------");
             
                try (Connection conn = DriverManager.getConnection(url,username, password);Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
                        while(result.next()){
                            int cusNumber=result.getInt("CusNumber");
                            String cusFirstName=result.getString("FirstName");
                            String cusLastName= result.getString("LastName");  
                
                            System.out.println(cusNumber + "\t          " + cusFirstName + "             " + cusLastName);
                        }
                } 
                catch (SQLException e){ e.printStackTrace();}
        }
        
        
        //SQL query that shows all employees in database
        protected void queryShowAllEmployees(){
                sql = "SELECT EmpNumber, FirstName, LastName, EmpRole "+
                      "FROM employee ORDER BY EmpNumber ASC"; //query to get the EmpNumber, First and Last name of all the employees, in order of ascending employee number
              
                ResultSet result=null;
             
                //forming a table for the query results
                System.out.print("\n\n"); 
                System.out.println("Employee#"+"\t "+"First Name"+"\t "+"Last Name"+"\t "+"   Role");
                System.out.println("---------"+"\t "+"----------"+"\t "+"---------"+"\t "+"----------");
             
                try (Connection conn = DriverManager.getConnection(url,username, password);Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
                        while(result.next()){
                            int empNumber=result.getInt("EmpNumber");
                            String empFirstName=result.getString("FirstName");
                            String empLastName= result.getString("LastName");  
                            String empRole= result.getString("EmpRole"); 
                
                            System.out.println(empNumber + "\t          " + empFirstName + "             " + empLastName+ "             " + empRole);
                        }
                } 
                catch (SQLException e){ e.printStackTrace();}
                
                System.out.print("\n\n"); 
        }
        
        
        
        //used in the GUI to check if a customer with the inputted username exists
        protected boolean checkIfCustomerExists(String cusUsername){
                boolean exists=false; //initializes the result to false, only changed to true if there is a match
                
                sql="SELECT * FROM users WHERE Username ='"+cusUsername+"'"; //query to get all users with the inputted username
                ResultSet result=null;
                String usernameResult=null; //initialize resulting username to null
                
                try (Connection conn = DriverManager.getConnection(url,username, password);Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
                        while(result.next()){
                            usernameResult=result.getString("Username");
                            exists=true; //if a match is found, our return boolean variable is set to true
                        }
                }   
                catch (SQLException e){ e.printStackTrace();}
                
                return exists; //if a match is found this will return true, if no match was found this will return false
        }
        
        
            //SQL query that shows all accounts in the database
        protected void queryShowAllAccounts(){
                sql = "SELECT account.AccNumber, account.Balance, customer.FirstName, customer.LastName, accounttype.AccountType "+
                      "FROM account,customer,accounttype WHERE customer.CusNumber=account.CusNumber AND "+
                      " accounttype.AccountTypeID=account.AccTypeID ORDER BY account.AccNumber ASC";
                ResultSet result=null;
             
                //forming a table for the query results
                System.out.print("\n\n"); 
                System.out.println("Account#"+"\t  "+"Balance"+"\t       "+"First Name"+"\t "+"Last Name"+"\t        "+"Account Type");
                System.out.println("--------"+"\t  "+"-------"+"\t       "+"----------"+"\t "+"---------"+"\t        "+"------------");
             
                try (Connection conn = DriverManager.getConnection(url,username, password);Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
                        while(result.next()){
                              int accNumber=result.getInt("AccNumber");
                              double accBalance=result.getInt("Balance");
                              String cusFirstName=result.getString("FirstName");
                              String cusLastName= result.getString("LastName");  
                              String accType= result.getString("AccountType");
                
                              System.out.println(accNumber + "\t          " + accBalance + "\t          " + cusFirstName + "\t           " + cusLastName + "\t          " + accType);
                        }
                } 
                catch (SQLException e){ e.printStackTrace();}
              
                System.out.println("\n\n");
        }
           
           
        
           //SQL query that returns customers based off user's input for last name
        protected void queryCustomerSearchByLastName(String lastName){
                sql="SELECT * FROM customer WHERE LastName ='"+lastName+"'"; //query to get all customers with the inputted last name
                ResultSet result=null;
           
                System.out.print("\n\n\n");
                System.out.println("Customer#"+"\t    "+"First Name"+"\t    "+"Last Name");
                System.out.println("---------"+"\t    "+"----------"+"\t    "+"---------");
             
                try (Connection conn = DriverManager.getConnection(url,username, password);
                                 Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
               
                    while(result.next()){
                        int cusNumber=result.getInt("CusNumber");
                        String cusFirstName=result.getString("FirstName");
                        String cusLastName=result.getString("LastName");
                
                        System.out.println("  "+cusNumber + "\t               " + cusFirstName+ "\t        " + cusLastName);
                    }
                } 
                catch (SQLException e){ e.printStackTrace();}
              
                System.out.println("\n\n");
        }
           
           
           
           
           //SQL query that shows the customer with the customer number inputted by the user
        protected void queryCustomerSearchByCusNumber(int num){
                sql="SELECT CusNumber,FirstName,LastName FROM customer WHERE CusNumber ="+num;
                ResultSet result=null;
           
                System.out.print("\n\n\n");
                System.out.println("Customer#"+"\t    "+"  First Name"+"\t"+" Last Name");
                System.out.println("---------"+"\t    "+" -----------"+"\t"+" ---------");
             
                try (Connection conn = DriverManager.getConnection(url,username, password);
                          Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
             
                    while(result.next()){
                
                        int cusNumber=result.getInt("CusNumber");
                        String cusFirstName=result.getString("FirstName");
                        String cusLastName=result.getString("LastName");
                
                        System.out.println(cusNumber + "\t                 " + cusFirstName+ "\t          " + cusLastName);;
                    }
                } 
                catch (SQLException e){ e.printStackTrace();}
                
                System.out.println("\n\n");
        }
           
           
        //SQL query to get the customer's password from their username
        //
        //used in the GUI to see if the user inputted the correct password for the inputted username
        protected String getPasswordByUsername(String cusUsername){
                sql="SELECT Password FROM users WHERE Username ='"+cusUsername+"'";
                ResultSet result=null;
                String cusPassword="";
         
                try (Connection conn = DriverManager.getConnection(url,username, password);
                           Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
             
                    while(result.next()){
                        cusPassword=result.getString("Password");
                    }
                } 
                catch (SQLException e){ e.printStackTrace();}
                     
                return cusPassword;
        }
        
           
        //SQL query that gets the first name of the customer from their username
        //
        //used in the GUI's welcome message after the customer successfully logs in
        protected String getFirstNameByUsername(String cusUsername){
                sql="SELECT FirstName FROM users,customer WHERE users.Username ='"+cusUsername+"' AND customer.UserID=users.UserID";
                ResultSet result=null;
                String cusFirstName="";
    
                try (Connection conn = DriverManager.getConnection(url,username, password);
                           Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
             
                        while(result.next()){
                            cusFirstName=result.getString("FirstName");
                        }
                } 
                catch (SQLException e){ e.printStackTrace();}
                     
                return cusFirstName;
        }
        
        
           //SQL query that shows the user information (username and password) of the username inputted by user
           //
           //used in the basic UI when a customer's password is changed
        protected void queryUserSearchByUsername(String cusUsername){
                sql="SELECT * FROM users WHERE Username ='"+cusUsername+"'";
                ResultSet result=null;
          
                System.out.print("\n\n");
                System.out.println("UserID"+"\t    "+"Username"+"\t    "+"Password");
                System.out.println("------"+"\t    "+"--------"+"\t    "+"--------");
             
                try (Connection conn = DriverManager.getConnection(url,username, password);
                           Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
             
                    while(result.next()){
                        int userID=result.getInt("UserID");
                        String custUsername=result.getString("Username");
                        String cusPassword=result.getString("Password");

                        System.out.println(userID + "\t    " + custUsername+ "\t    " + cusPassword);
                    }
                } 
                catch (SQLException e){ e.printStackTrace();}
          
                System.out.println("\n\n");
        }
           
           
           
           //SQL query that shows all customers in database with an account balance of over 1 million dollars
        protected void queryCustomersWithAccountBalanceOverOneMillionDollars(){
                sql="SELECT FirstName, LastName, Balance\n " +
                    " FROM customer, account\n" +
                    " WHERE account.CusNumber = customer.CusNumber\n" +
                    " AND account.Balance > 1000000\n" +
                    " ORDER BY customer.LastName";
                ResultSet result=null;
            
                System.out.println("First Name"+"\t"+"LastName"+"\t"+" Balance");
                System.out.println("----------"+"\t"+"--------"+"\t"+"---------");
           
                try (Connection conn = DriverManager.getConnection(url,username, password);
                           Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
             
                    while(result.next()){
                        String firstName=result.getString("FirstName");
                        String lastName=result.getString("LastName");
                        double balance=result.getDouble("Balance");

                        System.out.println(firstName + "\t        " + lastName+ "\t        " + balance);
                    }
                } 
                catch (SQLException e){ e.printStackTrace();}
          
                System.out.println("\n\n");
        }
           
           
           
           //SQL query that shows all branches in database located in the city of Toronto
        protected void queryBranchesInToronto(){
                sql="SELECT branch.BranNumber, branch.EmpNumber, branchlocation.BranAddress, branchlocation.BranPostalCode \n" +
                    " FROM branch, branchlocation\n" +
                    " WHERE branchlocation.BranCity = 'Toronto'\n" +
                    " AND  branchlocation.BranLocationID = branch.BranLocationID\n" +
                    " ORDER BY  branch.BranNumber ASC";
                ResultSet result=null;
           
                System.out.println("Branch#"+"\t"+"         Address"+"\t"+"          Postal Code"+"\t"+"  Manager#");
                System.out.println("-------"+"\t"+"        ---------"+"\t"+"         -----------"+"\t"+"  --------");
                
                try (Connection conn = DriverManager.getConnection(url,username, password);Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(sql);
                         
                    while(result.next()){
                        int branNumber=result.getInt("BranNumber");
                        String address=result.getString("BranAddress");
                        String postalCode=result.getString("BranPostalCode");
                        int empNumber=result.getInt("EmpNumber"); 
                     
                        System.out.println("   "+branNumber + "\t     " + address+ "\t    " + postalCode+ "\t     " + empNumber);
                    } 
                } 
                catch (SQLException e){ e.printStackTrace();}
                
                System.out.println("\n\n");
        }
           
           
           
           //updates the user's password based off user input
        protected void changePassword(String cusUsername, String newPassword){
                sql="UPDATE users SET Password ='"+newPassword+"' WHERE Username='"+cusUsername+"'";
                executeSQLCommand(sql); 
        }
           
           
           
           //Deletes customer from user's input for customer number
        protected void deleteCustomerByNumber(int cusNum){       
                String query="SELECT UserID FROM customer WHERE CusNumber ="+cusNum; //query to get all the UserID's under the customer number
                ResultSet result=null;
                int userID=0; //to store the UserID of the customer

                try (Connection conn = DriverManager.getConnection(url,username, password);Statement stmt = conn.createStatement()){
                    result= stmt.executeQuery(query);
                    
                    while(result.next()){
                        userID=result.getInt("UserID"); //stores the UserID of the customer from the query result

                    }
                }
                catch (SQLException e){ e.printStackTrace();}
              
                
                //the record with the UserID found and is deleted from the USERS table, which will cascade through the other 
                //tables and delete all other records from the other tables associated with this customer
                sql="DELETE FROM users WHERE UserID="+userID;
                    executeSQLCommand(sql);    
        }  
           
           
        
           //drops ALL constraints for ALL tables at once
           //
           //used in the basic UI when deleting all tables at once
        protected void dropAllConstraints(){
                //first we make a list of all the constraints of the tables
                List<String> constraintNames = Arrays.asList("fk_customerUser",
                "fk_ccCusNumber", "fk_branchLocation", "fk_branchEmp", "fk_accountType", "fk_accountBranch",
                "fk_accountCusNumber", "fk_chequingAccNum", "fk_savingsAccNum", "fk_depositTranNumber", "fk_depositAccNumTo",
                "fk_withdrawalTranNumber", "fk_withdrawalAccNumFrom", "fk_transferAccNumTo", "fk_transferTranNumber", "fk_transferAccNumFrom",
                "fk_ccpaymentAccNumberFrom", "fk_ccpaymentTranNumber", "fk_ccpaymentCCNumber"); 
                
                //now we create a list of all the tables containing these constraints
                List<String> tableNames = Arrays.asList("customer","creditcard", "branch", "account", "chequing",
                        "savings", "deposit", "withdrawal", "transfer", "creditcardpayment");
                
                //we will use this variable to go through the tableNames list
                int j=0;
                
                //initialize i to 0 which we will use to go through the constraintNames list
                for(int i=0; i<19;i++){
                    if(i==1 || i==2 || i==4 || i==7 || i==8 || i==9 || i==11 || i==13 || i==16) j++; //if i is equal to any of these numbers, it means we need to move to the next table name, done by increasing the j variable
                    
                        try (Connection conn = DriverManager.getConnection(url,username, password);Statement stmt = conn.createStatement()){
                            sql= "ALTER TABLE "+tableNames.get(j)+" DROP FOREIGN KEY "+constraintNames.get(i);
                            stmt.execute(sql);
                             
                        } 
     
                        catch (SQLException e){ e.printStackTrace();}
                }
        }
}
            
    
           
                   
    
          
          
        
        
