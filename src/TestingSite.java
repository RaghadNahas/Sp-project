import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;

import static java.lang.System.out;

public class TestingSite {
    public static void main(String[] args) throws IOException {


        File fileToBeModified = new File("C:\\FlightSystem\\Passengers.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        String line;
        double H, B, balance = 0, cost =1;

        File fileToBeModifiedHigh = new File("C:\\FlightSystem\\Passengers.txt");
        BufferedReader readerHigh = new BufferedReader(new FileReader(fileToBeModifiedHigh));
        String lineHigh;

        while ((lineHigh = readerHigh.readLine()) != null) {
            String[] partsHigh = lineHigh.split(": ");
            if (partsHigh[0].equals("Number_of_flights_in_class_High")) {
                System.out.println("1");
                H = Double.parseDouble(partsHigh[1]);

                if (H >= 3) {

                    while ((lineHigh = readerHigh.readLine()) != null) {
                        partsHigh = lineHigh.split(": ");
                       // System.out.println(partsHigh[0]);
                        if (partsHigh[0].equals("Passport ID") && partsHigh[1].equals("ahmad")) {
                            System.out.println("ahmad");
                            lineHigh = readerHigh.readLine();
                            partsHigh = lineHigh.split(": ");
                            break;
                        }
                    }

                    File fileforcost = new File("C:\\FlightSystem\\Flights.txt");
                    BufferedReader readercost = new BufferedReader(new FileReader(fileforcost));
                    String linecost;
                    while ((linecost = readercost.readLine()) != null) {
                        String[] partscost = linecost.split(": ");
                        //System.out.println(partscost[0]);
                        if (partscost[0].equals("Flight ID") && partscost[1].equals("idid")) {
                            System.out.println("flightid");
                            linecost = readercost.readLine();
                            partscost = linecost.split(": ");
                            break;

                        }
                    }

                    while ((linecost = readercost.readLine()) != null) {
                        String[] partscost = linecost.split(": ");
                       // System.out.println(partscost[0]);
                        if (partscost[0].equals("Cost")) {
                            System.out.println("cccc");
                            linecost = readercost.readLine();
                            partscost = linecost.split(": ");
                          System.out.println(Integer.parseInt(partscost[1]));
                           // cost = Integer.parseInt(partscost[1])+0.0;
                            System.out.println(cost);
                            break;

                        }
                    }

                    while ((lineHigh = readerHigh.readLine()) != null) {
                        partsHigh = lineHigh.split(": ");
                       // System.out.println(partsHigh[0]);
                        if (partsHigh[0].equals("Balance")) {
                            System.out.println("balance");
                            lineHigh = readerHigh.readLine();
                            partsHigh = lineHigh.split(": ");
                            balance = Double.parseDouble(partsHigh[1]);
                            break;
                        }
                    }


                    balance = (cost * 0.5) - balance;
                    System.out.println(balance);

                }
                break;

            }


            if (partsHigh[0].equals("Number_of_flights_in_class_Business")) {
                B = Integer.parseInt(partsHigh[1]);
                if (B >= 3) {

                    while ((lineHigh = readerHigh.readLine()) != null) {
                        partsHigh = lineHigh.split(": ");
                        //System.out.println(partsHigh[0]);
                        if (partsHigh[0].equals("Passport ID") && partsHigh[1].equals("ahmad")) {
                            System.out.println("help");
                            lineHigh = readerHigh.readLine();
                            partsHigh = lineHigh.split(": ");
                            break;
                        }
                    }

                    File fileforcost = new File("C:\\FlightSystem\\Flights.txt");
                    BufferedReader readercost = new BufferedReader(new FileReader(fileforcost));
                    String linecost;
                    while ((linecost = readercost.readLine()) != null) {
                        String[] partscost = linecost.split(": ");
                       // System.out.println(partscost[0]);
                        if (partscost[0].equals("Flight ID")) {
                            System.out.println("hh");
                            linecost = readercost.readLine();
                            partscost = linecost.split(": ");
                            break;

                        }
                    }

                    while ((linecost = readercost.readLine()) != null) {
                        String[] partscost = linecost.split(": ");
                        //System.out.println(partscost[0]);
                        if (partscost[0].equals("Cost")) {
                            System.out.println("cccc");
                            linecost = readercost.readLine();
                            partscost = linecost.split(": ");
                            cost = Double.parseDouble(partscost[1]);
                            System.out.println(cost);
                            break;

                        }
                    }

                    while ((lineHigh = readerHigh.readLine()) != null) {
                        partsHigh = lineHigh.split(": ");
                      //  System.out.println(partsHigh[0]);
                        if (partsHigh[0].equals("Balance")) {
                            System.out.println("help");
                            lineHigh = readerHigh.readLine();
                            partsHigh = lineHigh.split(": ");
                            balance = Double.parseDouble(partsHigh[1]);
                            break;
                        }
                    }
                    balance = (cost * 0.3) - balance;
                    System.out.println(balance);

                }
                break;

            }

        }

    }
}



