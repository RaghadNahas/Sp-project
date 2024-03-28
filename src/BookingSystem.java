import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class BookingSystem extends Thread {
    String[] loginInfo = new String[2];
    protected Socket clientSocket;
    static PrintWriter out;

    public static boolean authenticateAdmin(String username, String password) throws IOException {
        boolean valid = false;
        BufferedReader reader = new BufferedReader(new FileReader("C:\\FlightSystem\\Admins.txt"));
        String line;
        while ((line = reader.readLine()) != null && !valid) {
            String[] parts = line.split(": ");
            if (parts[0].equals("Username") && parts[1].equals(username)) {
                line = reader.readLine();
                parts = line.split(": ");
                if (parts[0].equals("Password") && parts[1].equals(password)) {
                    valid = true;
                }
            }
        }
        reader.close();
        return valid;
    }
    public static boolean authenticateUser(String passportID, String password) throws IOException {
        boolean valid = false;
        BufferedReader reader = new BufferedReader(new FileReader("C:\\FlightSystem\\Passengers.txt"));
        String line;
        while ((line = reader.readLine()) != null && !valid) {
            String[] parts = line.split(": ");
            if (parts[0].equals("Passport ID") && parts[1].equals(passportID)) {
                line = reader.readLine();
                parts = line.split(": ");
                if (parts[0].equals("Password") && parts[1].equals(password)) {
                    valid = true;
                }
            }
        }
        reader.close();
        return valid;
    }

    public static void fetchFlight(String ID) throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        boolean areWeThereYet = false;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID") && parts[1].equals(ID))
                areWeThereYet = true;
            else if (parts[0].equals("Flight ID"))
                areWeThereYet = false;
            if (parts.length > 1 && areWeThereYet)
                out.println(parts[1]);
        }
        reader.close();
    }
    public static void fetchUser(String ID) throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Passengers.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        boolean areWeThereYet = false;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Passport ID") && parts[1].equals(ID))
                areWeThereYet = true;
            else if (parts[0].equals("Passport ID"))
                areWeThereYet = false;
            if (parts.length > 1 && areWeThereYet) {
                System.out.println(parts[1]);
                out.println(parts[1]);
            }
        }
        reader.close();
    }

    public static void addFlight(String id, String deptPlace, String arrvlPlace, String type, ArrayList<String> docs, String classType, String dateAndTime, double cost, String approxTime) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\FlightSystem\\Flights.txt", true));
        out.write("Flight ID: " + id.toLowerCase() + "\nDeparture Place: " + deptPlace + "\nArrival Place: " + arrvlPlace + "\nType: " + type + "\nRequired Documents: " + docs.toString().substring(1, docs.toString().length()-1) + "\nClass Type: " + classType + "\nDate and Time: " + dateAndTime + "\nCost: " + cost + "\nApproximate Time: " + approxTime + "\n\n");
        out.flush();
        out.close();
    }
    public static void addUser(String fullName, String passportID, String balance, String password) throws IOException {
        int numH=0,numB=0;
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\FlightSystem\\Passengers.txt", true));
        out.write("Passport ID: " + passportID.toLowerCase() + "\nPassword: " + password + "\nBalance: " + balance + "\nFull Name: " + fullName +"\nNumber_of_flights_in_class_High: "+numH+"\nNumber_of_flights_in_class_Business: "+numB);
        out.flush();
        out.close();
    }
    public static void calculateNumofClassFlight(String Flight_ID, String fullName, String passportID, String balance, String password) throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        String line;
        int numA = 0,numB=0;
        String flightID, classType, DAT, cost, approxTime,deptPlace,arrvlPlace,type,docs;
        while ((line = reader.readLine()) != null) {
            flightID = "";
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID") && parts[1].equals(Flight_ID)) {
                flightID += line;
                deptPlace = reader.readLine();
                arrvlPlace = reader.readLine();
                type = reader.readLine();
                docs = reader.readLine();
                classType = reader.readLine();
                DAT = reader.readLine();
                cost = reader.readLine();
                approxTime = reader.readLine();
                if (classType.toLowerCase().contains("high")) {

                    File fileToBeModifiedHigh = new File("C:\\FlightSystem\\Passengers.txt");
                    BufferedReader readerHigh = new BufferedReader(new FileReader(fileToBeModifiedHigh));
                    String lineHigh;
                    while ((lineHigh = readerHigh.readLine()) != null) {
                        String[] partsHigh = lineHigh.split(": ");
                        if (partsHigh[0].equals("Passport ID") && partsHigh[1].equals(passportID)) {
                            lineHigh = readerHigh.readLine();
                            partsHigh = lineHigh.split(": ");
                            if (partsHigh[0].equals("Number_of_flights_in_class_High")) {
                                numA=Integer.parseInt(partsHigh[1]);
                                numA++;

                            }
                            if (partsHigh[0].equals("Number_of_flights_in_class_Business")) {
                                numB=Integer.parseInt(partsHigh[1]);

                            }
                        }
                            }
                                out.write("Passport ID: " + passportID.toLowerCase() + "\nPassword: " + password + "\nBalance: " + balance + "\nFull Name: " + fullName + "\nNumber_of_flights_in_class_High: " + numA + "\nNumber_of_flights_in_class_Business: " +numB );
                                out.flush();
                                out.close();

                        }
                else if (classType.toLowerCase().contains("business")){


                        File fileToBeModifiedHigh = new File("C:\\FlightSystem\\Passengers.txt");
                        BufferedReader readerb = new BufferedReader(new FileReader(fileToBeModifiedHigh));
                        String lineHigh;
                        while ((lineHigh = readerb.readLine()) != null) {
                            String[] partsHigh = lineHigh.split(": ");
                            if (partsHigh[0].equals("Passport ID") && partsHigh[1].equals(passportID)) {
                                lineHigh = readerb.readLine();
                                partsHigh = lineHigh.split(": ");
                                if (partsHigh[0].equals("Number_of_flights_in_class_High")) {
                                    numA=Integer.parseInt(partsHigh[1]);


                                }
                                if (partsHigh[0].equals("Number_of_flights_in_class_Business")) {
                                    numB=Integer.parseInt(partsHigh[1]);
                                    numB++;

                                }
                            }
                        }
                        out.write("Passport ID: " + passportID.toLowerCase() + "\nPassword: " + password + "\nBalance: " + balance + "\nFull Name: " + fullName + "\nNumber_of_flights_in_class_High: " + numA + "\nNumber_of_flights_in_class_Business: " +numB );
                        out.flush();
                        out.close();

                    }

                }
            }
                }
        public static void Discount(){



        }
    public static void findVisaFlights() throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        String line;
        String flightID, deptPlace, arrvlPlace, type, docs, classType, DAT, cost, approxTime;
        while ((line = reader.readLine()) != null) {
            flightID = "";
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID")) {
                flightID += line;
                deptPlace = reader.readLine();
                arrvlPlace = reader.readLine();
                type = reader.readLine();
                docs = reader.readLine();
                classType = reader.readLine();
                DAT = reader.readLine();
                cost = reader.readLine();
                approxTime = reader.readLine();
                if (docs.toLowerCase().contains("visa")) {
                    out.println(flightID);
                    out.println(deptPlace);
                    out.println(arrvlPlace);
                    out.println(type);
                    out.println(docs);
                    out.println(classType);
                    out.println(DAT);
                    out.println(cost);
                    out.println(approxTime);
                }
            }
        }
        out.println("0K1zzR4zM3zZ2z7zS8Hz6z9CzWzFzV5zJzTzPzY");
        reader.close();
    }
    public static void findPassportFlights() throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Flights.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        String line;
        String flightID, deptPlace, arrvlPlace, type, docs, classType, DAT, cost, approxTime;
        while ((line = reader.readLine()) != null) {
            flightID = "";
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID")) {
                flightID += line;
                deptPlace = reader.readLine();
                arrvlPlace = reader.readLine();
                type = reader.readLine();
                docs = reader.readLine();
                classType = reader.readLine();
                DAT = reader.readLine();
                cost = reader.readLine();
                approxTime = reader.readLine();
                if (!docs.toLowerCase().contains("visa")) {
                    out.println(flightID);
                    out.println(deptPlace);
                    out.println(arrvlPlace);
                    out.println(type);
                    out.println(docs);
                    out.println(classType);
                    out.println(DAT);
                    out.println(cost);
                    out.println(approxTime);
                }
            }
        }
        out.println("0K1zzR4zM3zZ2z7zS8Hz6z9CzWzFzV5zJzTzPzY");
        reader.close();
    }
    public static boolean flightExists(String id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\FlightSystem\\Flights.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID") && parts[1].equals(id))
                return true;
        }
        reader.close();
        return false;
    }
    public static boolean userExists(String id) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\FlightSystem\\Passengers.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Passport ID") && parts[1].equals(id))
                return true;
        }
        reader.close();
        return false;
    }
    static void removeFlight(String flightID) throws IOException {
        File fileToBeModified = new File("C:\\FlightSystem\\Flights.txt");
        String newContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        boolean removing = false;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            if (parts.length > 1 && parts[0].equals("Flight ID") && parts[1].equals(flightID))
                removing = true;
            else if (parts[0].equals("Flight ID"))
                removing = false;
            if (!removing)
                newContent += line + System.lineSeparator();
        }
        reader.close();
        FileWriter writer = new FileWriter(fileToBeModified);
        writer.write(newContent);
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(7000);
            System.out.println("Connection Socket Created on server Socket on port 7000");
            try {
                while (true) {
                    System.out.println("Waiting for Connection");
                    new BookingSystem(serverSocket.accept());
                    System.out.println("welcome");
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 7000.");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Could not close port: 7000.");
            }
        }
    }

    private BookingSystem(Socket clientSoc) {
        this.clientSocket = clientSoc;
        start();
    }

    public void run() {
        System.out.println("New Communication Thread Started");
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Server: " + inputLine);
                switch (inputLine) {
                    case "6K7uqR0aM9lZ8y3gS4Hi2n5CdWeFbV1oJjTwPkY" -> {
                        loginInfo[0] = in.readLine();
                        loginInfo[1] = in.readLine();
                        System.out.println(authenticateAdmin(loginInfo[0], loginInfo[1]));
                        if (authenticateAdmin( loginInfo[0], loginInfo[1]))
                            out.println("SuccessfulAdminLogin");
                        else
                            out.println("FailureAdminLogin");
                    }
                    case "5J6tpQ9zL8kY7x2fR3Gh1m4BcVdEaU0nIiSvOjX" -> {
                        loginInfo[0] = in.readLine();
                        loginInfo[1] = in.readLine();
                        System.out.println(authenticateUser( loginInfo[0], loginInfo[1]));
                        if (authenticateUser( loginInfo[0], loginInfo[1]))
                            out.println("SuccessfulUserLogin");
                        else
                            out.println("FailureUserLogin");
                    }
                    case "7L8vrS1bN0mA9z4hT5Ij3o6DeXfGcW2pKkUxQlZ" -> {
                        System.out.println("Removing flight...");
                        removeFlight(in.readLine());
                    }
                    case "8M9wtT2cO1nB0a5iU6Jk4p7EfYgHdX3qLlVyRmA" -> {
                        System.out.println("Checking flight...");
                        if (flightExists(in.readLine()))
                            out.println("FlightFound");
                        else
                            out.println("FlightNotFound");
                    }
                    case "9N0xuU3dP2oC1b6jV7Kl5q8FgZhIeY4rMmWzSnB" -> {
                        System.out.println("Adding flight...");
                        addFlight(in.readLine(), in.readLine(), in.readLine(), in.readLine(), new ArrayList<>(Arrays.asList(in.readLine().split(" - "))), in.readLine(), in.readLine(), Double.parseDouble(in.readLine()), in.readLine());
                    }
                    case "2Q3zxX6gS5rF4e9mY0No8t1IjCkLhB7uPpZzVqE"  -> {
                        fetchFlight(in.readLine());
                    }
                    case "0O1yvV4eQ3pD2c7kW8Lm6r9GhAiJfZ5sNnXzToC" -> {
                        System.out.println("Checking user...");
                        if (userExists(in.readLine()))
                            out.println("UserFound");
                        else
                            out.println("UserNotFound");
                    }
                    case "1P2zwW5fR4qE3d8lX9Mn7s0HiBjKgA6tOoYzUpD" -> {
                        Sygitstem.out.println("Creating user...");
                        addUser(in.readLine(), in.readLine(), in.readLine(), in.readLine());
                    }
                    case "3R4zyY7hT6sG5f0nZ1Op9u2JkDlMiC8vQqAzWrF" -> {
                        System.out.println("Fetching user...");
                        fetchUser(in.readLine());
                    }
                    case "4S5zzZ8iU7tH6g1oA2Pq0v3KlEmNjD9wRrBzXsG" -> {
                        findVisaFlights();
                    }
                    case "5T6zzA9jV8uI7h2pB3Qr1w4LmFnOkE0xSsCzYtH" -> {
                        findPassportFlights();
                    }
                    default -> {
                        System.out.println("Unknown request");
                    }
                }
            }
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Connection lost");
        }
    }
}