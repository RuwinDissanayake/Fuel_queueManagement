import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        FuelQueue pumpOne = new FuelQueue("Pump One");
        FuelQueue pumpTwo = new FuelQueue("Pump Two");
        FuelQueue pumpThree = new FuelQueue("Pump Three");         //the five pumps are defined
        FuelQueue pumpFour = new FuelQueue("Pump Four");
        FuelQueue pumpFive = new FuelQueue("Pump Five");

        WaitingQueue waitingQueue = new WaitingQueue();           //the waiting queue used which is defined in waitingQueue class
        String dataStored = "";

        boolean Program = true;

        while (true) {

            while (Program) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("""
                        100 or VFQ: View all Fuel Queues.
                        101 or VEQ: View all Empty Queues.
                        102 or ACQ: Add customer to a Queue.                            //the options displayed to user
                        103 or RCQ: Remove a customer from a Queue.
                        104 or PCQ: Serve and remove a customer from Queue.
                        105 or VCS: View Customers.
                        106 or SPD: Store Program Data into file.
                        107 or LPD: Load Program Data from file.
                        108 or STK: View Remaining Fuel Stock.
                        109 or AFS: Add Fuel Stock.
                        110 or IFQ: Check the Total Profit.
                        999 or EXT: Exit the Program.""");
                System.out.print("\nEnter option :");
                Scanner scanner4;
                switch (scanner.next().toUpperCase()) {
                    case "100":
                    case "VFQ":
                        System.out.println(pumpOne.getName());
                        System.out.println(pumpTwo.getName());
                        System.out.println(pumpThree.getName());
                        System.out.println(pumpFour.getName());
                        System.out.println(pumpFive.getName());
                        Thread.sleep(2000);                                      //time stop of 2 seconds given to make it easier for the user to understand
                        break;
                    case "101":
                    case "VEQ":
                        if (pumpOne.isEmpty()) {
                            System.out.println(pumpOne.getName());                   //all empty pumps displayed which is defined in FuelQueue
                        }

                        if (pumpTwo.isEmpty()) {
                            System.out.println(pumpTwo.getName());
                        }

                        if (pumpThree.isEmpty()) {
                            System.out.println(pumpThree.getName());
                        }

                        if (pumpFour.isEmpty()) {
                            System.out.println(pumpFour.getName());
                        }

                        if (pumpFive.isEmpty()) {
                            System.out.println(pumpFive.getName());
                        }
                        Thread.sleep(2000);
                        break;
                    case "102":
                    case "ACQ":
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("Enter the  Firstname of the Customer");
                        String firstName = scanner1.next();                                 //information taken by using scanner and to be printed in 107/LPD
                        Scanner scanner11 = new Scanner(System.in);
                        System.out.println("Enter the  Surname of the Customer");
                        String Surname = scanner11.next();
                        Scanner scanner12 = new Scanner(System.in);
                        System.out.println("Enter the  vehicle number of the Customer");
                        int vehicleNumber = scanner12.nextInt();
                        Scanner scanner13 = new Scanner(System.in);
                        System.out.println("Enter the number of litres needed");
                        float customerLitres = scanner13.nextFloat();
                        Passenger passenger = new Passenger(firstName, Surname, vehicleNumber, customerLitres);
                        if (pumpOne.isFull() && pumpTwo.isFull() && pumpThree.isFull() && pumpFour.isFull() && pumpFive.isFull()) {
                            waitingQueue.addCustomer(passenger);
                            break;
                        }

                        FuelQueue shortestQueue = getShortestQueue(pumpOne, pumpTwo, pumpThree, pumpFour, pumpFive);    //method shortestQueue used which is defined at bottom of code
                        shortestQueue.addto(passenger);
                        Thread.sleep(2000);
                        break;
                    case "103":
                    case "RCQ":
                        Scanner scanner3 = new Scanner(System.in);
                        System.out.println("""
                                In which pump is the Customer there
                                1. Pump One
                                2. Pump Two
                                3. Pump Three
                                4. Pump Four
                                5. Pump Five""");
                        String pump = scanner3.next();        //can remove a customer using the removefrom method which is defined in FuelQueue class
                        int numberCustomer = -1;
                        if (pump.equals("1") || pump.equals("2") || pump.equals("3") || pump.equals(("4")) || pump.equals("5")) {
                            scanner4 = new Scanner(System.in);
                            System.out.println("What is the position of the customer? ");
                            numberCustomer = scanner4.nextInt();
                        }

                        switch (pump) {
                            case "1" -> {
                                pumpOne.removefrom(numberCustomer);
                                Thread.sleep(2000);
                                continue;
                            }
                            case "2" -> {
                                pumpTwo.removefrom(numberCustomer);
                                Thread.sleep(2000);
                                continue;
                            }
                            case "3" -> {
                                pumpThree.removefrom(numberCustomer);
                                Thread.sleep(2000);
                                continue;
                            }
                            case "4" -> {
                                pumpFour.removefrom(numberCustomer);
                                Thread.sleep(2000);
                                continue;
                            }
                            case "5" -> {
                                pumpFive.removefrom(numberCustomer);
                                Thread.sleep(2000);
                                continue;
                            }
                            default -> {
                                System.out.println("Enter a valid input!");
                                Thread.sleep(2000);
                                continue;

                            }
                        }

                    case "104":
                    case "PCQ":
                        scanner4 = new Scanner(System.in);
                        System.out.println("""
                                Select which pump to serve?
                                1. Pump One                                         
                                2. Pump Two
                                3. Pump Three
                                4. Pump Four
                                5. Pump Five""");
                        switch (scanner4.next()) {
                            case "1" -> {
                                dataStored = pumpOne.serve(dataStored);                            //serve a customer using the serve method
                                if (waitingQueue.getFirstPassenger() != null) {
                                    pumpOne.addto(waitingQueue.getFirstPassenger());
                                    waitingQueue.sendCustomerToFuelQueue();
                                }
                                Thread.sleep(2000);
                                continue;
                            }
                            case "2" -> {
                                dataStored = pumpTwo.serve(dataStored);
                                if (waitingQueue.getFirstPassenger() != null) {
                                    pumpTwo.addto(waitingQueue.getFirstPassenger());
                                    waitingQueue.sendCustomerToFuelQueue();
                                }
                                Thread.sleep(2000);
                                continue;
                            }
                            case "3" -> {
                                dataStored = pumpThree.serve(dataStored);
                                if (waitingQueue.getFirstPassenger() != null) {
                                    pumpThree.addto(waitingQueue.getFirstPassenger());
                                    waitingQueue.sendCustomerToFuelQueue();
                                }
                                Thread.sleep(2000);
                                continue;
                            }
                            case "4" -> {
                                dataStored = pumpFour.serve(dataStored);
                                if (waitingQueue.getFirstPassenger() != null) {
                                    pumpFour.addto(waitingQueue.getFirstPassenger());
                                    waitingQueue.sendCustomerToFuelQueue();
                                }
                                Thread.sleep(2000);
                                continue;
                            }
                            case "5" -> {
                                dataStored = pumpFive.serve(dataStored);
                                if (waitingQueue.getFirstPassenger() != null) {
                                    pumpFive.addto(waitingQueue.getFirstPassenger());
                                    waitingQueue.sendCustomerToFuelQueue();
                                }
                                Thread.sleep(2000);
                                continue;
                            }
                            default -> {
                                System.out.println("Enter a valid input!");
                                Thread.sleep(2000);
                                continue;
                            }
                        }
                    case "105":
                    case "VCS":
                        Scanner scanner5 = new Scanner(System.in);
                        System.out.println("""
                                What Pump Customers do you want to Check?
                                1. Pump One
                                2. Pump Two
                                3. Pump Three
                                4. Pump Four
                                5. Pump Five""");


                        switch (scanner5.next()) {
                            case "1" -> {                                              //view customers with view method
                                pumpOne.view();
                                Thread.sleep(2000);
                                continue;
                            }
                            case "2" -> {
                                pumpTwo.view();
                                Thread.sleep(2000);
                                continue;
                            }
                            case "3" -> {
                                pumpThree.view();
                                Thread.sleep(2000);
                                continue;
                            }
                            case "4" -> {
                                pumpFour.view();
                                Thread.sleep(2000);
                                continue;
                            }
                            case "5" -> {
                                pumpFive.view();
                                Thread.sleep(2000);
                                continue;
                            }
                            default -> {
                                System.out.println("Enter a valid Input!");
                                Thread.sleep(2000);
                                continue;
                            }
                        }
                    case "106":
                    case "SPD":
                        try {
                            FileWriter fw = new FileWriter("Data.txt");
                            fw.write(dataStored);
                            fw.close();
                            System.out.println("Data added to file");
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                        Thread.sleep(2000);                          //add the data of the served customers to a text file
                        break;

                    case "107":
                    case "LPD":
                        try{
                            File myObj = new File("Data.txt");
                            Scanner myReader = new Scanner(myObj);
                            while (myReader.hasNextLine()){
                                String data = myReader.nextLine();
                                System.out.println(data);
                            }                                                          //print the added data in the text file
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Thread.sleep(2000);
                        break;



                    case "108":
                    case "STK":
                        System.out.println(FuelQueue.viewFuelStock() + "Litres");
                        Thread.sleep(2000);                                              //view the remaining fuel stock
                        break;

                    case "109":
                    case "AFS":
                        Scanner scanner6 = new Scanner(System.in);
                        System.out.println("Enter the amount of Litres to stock");
                        float stock = scanner6.nextFloat();                             //add more fuel to the stock
                        FuelQueue.addFuelStock(stock);
                        Thread.sleep(2000);
                        break;

                    case "110":
                    case "IFQ":
                        System.out.println("Income Pump One: Rs." + pumpOne.getTotalIncome());
                        System.out.println("Income Pump Two: Rs." + pumpTwo.getTotalIncome());              //shows income of each pump
                        System.out.println("Income Pump Three: Rs." + pumpThree.getTotalIncome());
                        System.out.println("Income Pump Four: Rs." + pumpFour.getTotalIncome());
                        System.out.println("Income Pump Five: Rs." + pumpFive.getTotalIncome());
                        Thread.sleep(2000);
                        break;

                    case "999":
                    case "EXT":
                        Program = false;
                        Thread.sleep(2000);
                        break;
                    default:
                        System.out.println("Enter a valid Input!");
                }
            }

            return;
        }
    }

    public static FuelQueue getShortestQueue(FuelQueue pumpOne, FuelQueue pumpTwo, FuelQueue pumpThree, FuelQueue pumpFour, FuelQueue pumpFive) {
        FuelQueue smallest;
        if (pumpFive.getNoOfCustomers() < pumpOne.getNoOfCustomers()) {
            if (pumpThree.getNoOfCustomers() < pumpTwo.getNoOfCustomers()) {
                if (pumpTwo.getNoOfCustomers() < pumpThree.getNoOfCustomers()) {
                    if (pumpFive.getNoOfCustomers() < pumpFour.getNoOfCustomers()){
                        smallest = pumpFive;
                    }else {                                             //the method defined where the customer is added to the queue with the lowest customers
                        smallest = pumpFour;
                    }
                } else {
                    smallest = pumpThree;
                }
            } else{
                smallest = pumpTwo;
            }
        } else {
            smallest = pumpOne;
        }

            return smallest;
        }
    }
