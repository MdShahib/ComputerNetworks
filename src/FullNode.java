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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

// DO NOT EDIT starts
//interface FullNodeInterface {
//    public boolean listen(String ipAddress, int portNumber);
//    public void handleIncomingConnections(String startingNodeName, String startingNodeAddress);
//}
//// DO NOT EDIT ends


//public class FullNode implements FullNodeInterface {
//
//    public boolean listen(String ipAddress, int portNumber) {
//	// Implement this!
//	// Return true if the node can accept incoming connections
//	// Return false otherwise
//	return true;
//    }
//
//    public void handleIncomingConnections(String startingNodeName, String startingNodeAddress) {
//	// Implement this!
//	return;
//    }
//}

// IN2011 Computer Networks
// Coursework 2023/2024
//
// Submission by
// YOUR_NAME_GOES_HERE
// YOUR_STUDENT_ID_NUMBER_GOES_HERE
// YOUR_EMAIL_GOES_HERE


// DO NOT EDIT starts
interface FullNodeInterface {
    public boolean listen(String ipAddress, int portNumber);
    public void handleIncomingConnections(String startingNodeName, String startingNodeAddress);
}
// DO NOT EDIT ends


public class FullNode implements FullNodeInterface {

    private ServerSocket serverSocket;

    public boolean listen(String ipAddress, int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber, 50, InetAddress.getByName(ipAddress));
            System.out.println("Listening on " + ipAddress + ":" + portNumber);
            handleIncomingConnections(); // No parameters needed
            return true;
        } catch (IOException e) {
            System.err.println("Could not listen on " + ipAddress + ":" + portNumber);
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void handleIncomingConnections(String startingNodeName, String startingNodeAddress) {

    }

    public void handleIncomingConnections() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> {
                    processClientRequest(clientSocket);
                }).start();
            }
        } catch (IOException e) {
            System.err.println("Error handling incoming connections: " + e.getMessage());
        }
    }

    private void processClientRequest(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine); // Debugging output
                // Handle different requests based on the protocol
                if (inputLine.startsWith("PUT?")) {
                    handlePutRequest(in, out, inputLine);
                } else if (inputLine.startsWith("GET?")) {
                    handleGetRequest(in, out, inputLine);
                } else if (inputLine.startsWith("NOTIFY?")) {
                    handleNotifyRequest(in, out, inputLine);
                } // Add more handlers as needed
            }
        } catch (IOException e) {
            System.err.println("Error communicating with client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    private void handlePutRequest(BufferedReader in, PrintWriter out, String initialLine) {
        try {
            // Implementation details here...
        } catch (Exception e) {
            System.err.println("Failed to handle PUT request: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleGetRequest(BufferedReader in, PrintWriter out, String initialLine) {
        try {
            // Implementation details here...
        } catch (Exception e) {
            System.err.println("Failed to handle GET request: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleNotifyRequest(BufferedReader in, PrintWriter out, String initialLine) {
        try {
            // Implementation details here...
        } catch (Exception e) {
            System.err.println("Failed to handle NOTIFY request: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
