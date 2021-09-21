package networkingexample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * An application which listens on a port for a message and returns a modified version of the message.
 */
public class ReverseServer {

    // The port to listen to
    private int port;

    /**
     * Constructs a new server on the designated port.
     *
     * @param listenPort the port to listen on
     */
    public ReverseServer(int listenPort) {
        this.port = listenPort;
    }

    /**
     * Starts the server.
     *
     * @throws IOException when the server cannot start or the server cannot accept a socket connection
     */
    public void start() throws IOException {
        // Start the server
        System.out.printf("Starting the server on port %d\n", port);
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("The server is listening");

        // Run until the server is terminated
        while (true) {
            // ServerSocket.accept() blocks until a connection has been established
            System.out.println("Waiting for a connection");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Kick off a new thread to handle the socket
            System.out.println("Launching new thread to handle client connection");
            startSocketConectionAsThread(socket);

            // Rinse and repeat
        }
    }

    /**
     * This method handles a socket connection within a new thread.
     *
     * @param socket the socket connection to handle
     */
    private void startSocketConectionAsThread(Socket socket) {
        // Create a new thread
        // The Thread class can take a Runnable instance as the code to run; a Runnable instance only has to implement
        // a "run" method
        Thread thread = new Thread(new Runnable() {
            /**
             * Handles a socket connection.
             */
            @Override
            public void run() {
                try {
                    System.out.println("Handling client connection");
                    // Handle the socket connection
                    handleSocketConnection(socket);
                } catch (Exception exception) {
                    System.err.printf("There was a problem with a connection: %s", exception.getMessage());
                    exception.printStackTrace(System.err);
                }
            }
        });

        // Start the thread
        thread.start();
    }

    /**
     * Handles a socket connection by reading a message sent from the client and responding with a new message.
     *
     * @param socket the socket to handle
     * @throws IOException when there is a problem reading or writing the socket
     */
    private void handleSocketConnection(Socket socket) throws IOException {
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = socketReader.readLine();
        System.out.printf("Read message '%s'\n", message);

        // Create the response
        String response =  "";
        for (int i = message.length()-1; i >= 0; i--) {
            response += message.charAt(i);
        }
        // Append a newline (for the client to know when to stop reading)
        response += "\n";

        // Write the response
        BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        System.out.printf("Sending response '%s'", response);
        socketWriter.write(response);
        // Done writing
        socketWriter.flush();

        // Close the writer
        socketWriter.close();

        // Close the reader
        socketReader.close();

        // Close the connection
        socket.close();
        System.out.println("Closed client connection");
    }

    public static void main(String[] args) {
        try {
            new ReverseServer(1234).start();
        } catch (Exception exception) {
            System.err.printf("There was a problem establishing a connection: %s", exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

}