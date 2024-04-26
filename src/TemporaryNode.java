// IN2011 Computer Networks
// Coursework 2023/2024
//
// Submission by
// YOUR_NAME_GOES_HERE
// YOUR_STUDENT_ID_NUMBER_GOES_HERE
// YOUR_EMAIL_GOES_HERE
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


// DO NOT EDIT starts
interface TemporaryNodeInterface {
    public boolean start(String startingNodeName, String startingNodeAddress);
    public boolean store(String key, String value);
    public String get(String key);
}
// DO NOT EDIT ends


//public class TemporaryNode implements TemporaryNodeInterface {

//    public boolean start(String startingNodeName, String startingNodeAddress) {
//	// Implement this!
//	// Return true if the 2D#4 network can be contacted
//	// Return false if the 2D#4 network can't be contacted
//	return true;
//    }
//
//    public boolean store(String key, String value) {
//	// Implement this!
//	// Return true if the store worked
//	// Return false if the store failed
//	return true;
//    }
//
//    public String get(String key) {
//	// Implement this!
//	// Return the string if the get worked
//	// Return null if it didn't
//	return "Not implemented";
//    }
//}

    public class TemporaryNode implements TemporaryNodeInterface {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public boolean start(String startingNodeName, String startingNodeAddress) {
            try {
                socket = new Socket(startingNodeAddress, 8080); // Assuming default port is 8080
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                return true;
            } catch (IOException e) {
                System.err.println("Error connecting to the 2D#4 network: " + e.getMessage());
                return false;
            }
        }

        public boolean store(String key, String value) {
            try {
                out.println("PUT? 1 " + value.split("\n").length);
                out.println(key);
                out.println(value);
                String response = in.readLine();
                return response.equals("SUCCESS");
            } catch (IOException e) {
                System.err.println("Error storing key-value pair: " + e.getMessage());
                return false;
            }
        }

        public String get(String key) {
            try {
                out.println("GET? 1");
                out.println(key);
                String response = in.readLine();
                if (response.equals("NOPE")) {
                    return null;
                } else if (response.startsWith("VALUE")) {
                    int numLines = Integer.parseInt(response.split(" ")[1]);
                    StringBuilder value = new StringBuilder();
                    for (int i = 0; i < numLines; i++) {
                        value.append(in.readLine()).append("\n");
                    }
                    return value.toString();
                }
            } catch (IOException e) {
                System.err.println("Error retrieving value for key: " + e.getMessage());
            }
            return null;
        }
    }
