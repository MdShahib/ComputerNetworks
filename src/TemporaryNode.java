// IN2011 Computer Networks
// Coursework 2023/2024
//
// Submission by
// YOUR_NAME_GOES_HERE
// YOUR_STUDENT_ID_NUMBER_GOES_HERE
// YOUR_EMAIL_GOES_HERE
import java.io.*;
import java.net.*;

// DO NOT EDIT starts
interface TemporaryNodeInterface {
    public boolean start(String startingNodeName, String startingNodeAddress);
    public boolean store(String key, String value);
    public String get(String key);
}
// DO NOT EDIT ends


public class TemporaryNode implements TemporaryNodeInterface {

//    public boolean start(String startingNodeName, String startingNodeAddress) {
//	// Implement this!
//	// Return true if the 2D#4 network can be contacted
//	// Return false if the 2D#4 network can't be contacted
//	return true;
//    }
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public boolean start(String startingNodeName, String startingNodeAddress) {
        try {
                // Assuming default port, replace with actual if different
            int port = 12345;
            socket = new Socket(startingNodeAddress, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Example: Send a simple protocol-specific message to check connectivity
            out.println("HELLO " + startingNodeName);

                // Wait for a response, could be a simple ACK or any protocol-defined message
            String response = in.readLine();
            if (response != null && response.equals("ACK")) {
                return true; // Proper response received
            } else {
                return false; // Incorrect or no response
            }
        } catch (IOException e) {
            System.err.println("Error connecting to the network: " + e.getMessage());
            return false; // Connection failed
        }
    }

        // Clean up resources
        public void closeConnection() {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing the network connection: " + e.getMessage());
            }
    }

    public boolean store(String key, String value) {
	// Implement this!
	// Return true if the store worked
	// Return false if the store failed
	return true;
    }

    public String get(String key) {
	// Implement this!
	// Return the string if the get worked
	// Return null if it didn't
	return "Not implemented";
    }
}
