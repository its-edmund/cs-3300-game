package networkingexample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * This is JavaFX application which is used to illustrate some basic concepts of JavaFX including invocation, layouts,
 * useful components and action handlers (button clicks).
 *
 * +--------------------------------------+
 * |                                      |
 * |  Message:  ________________________  |
 * |  Response: ________________________  |
 * |                            [Do It!]  |
 * |                                      |
 * +--------------------------------------+
 *
 */
public class Main extends Application {

    /**
     * This method is the implemented method from the parent class; this is the entry point into the UI.
     *
     * @see javafx.application.Application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // The primaryStage is passed into this method by the JavaFX runtime. The stage is where we can place UI
        // components
        //
        // Set the title of the application (this will be the title bar)
        primaryStage.setTitle("JavaFX Welcome");


        // We need to lay out our components in some manner; we choose a GridPane because it is relatively simple to
        // use - the GridPane provides rectangles, indexed by column and row. This is not unlike a checkerboard.
        //
        // The GridPane is the layout we use
        GridPane grid = new GridPane();
        // Set the alignment and some layout attributes (padding)
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        // Now we setup the UI components that will be used. The UI should look something like this:
        //
        //    +--------------------------------------+
        //    |                                      |
        //    |  Message:  ________________________  |
        //    |  Response: ________________________  |
        //    |                            [Do It!]  |
        //    |                                      |
        //    +--------------------------------------+
        //
        // The label for the "message" text field
        Label messageLabel = new Label("Message:");
        // Add the label to the layout
        grid.add(messageLabel, 0,0);

        // The "message" text field
        TextField messageTextField = new TextField();
        // Add the text field to the layout
        grid.add(messageTextField, 1, 0);

        // The "response" label
        Label responseLabel = new Label("Response:");
        // Add the "response" label to the layout
        grid.add(responseLabel, 0, 1);

        // The "response" text field
        TextField responseTextField = new TextField();
        // Disable the "response" text field so that it cannot be edited by the user
        responseTextField.setDisable(true);
        // Add the "response" text field to the layout
        grid.add(responseTextField, 1, 1);

        // The "do it!" button
        Button doItButton = new Button("Do It!");
        // Add the "do it!" button to the layout
        // First, create a container so we can align the button to the RIGHT (by default, the button would be aligned
        // to the LEFT
        HBox hbBtn = new HBox(10);
        // Set the alignment
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        // Add the button to the HBox
        hbBtn.getChildren().add(doItButton);
        // Add the HBox to the layout
        grid.add(hbBtn, 1, 2);

        // This code determines what happens when the "do it!" button is pressed
        doItButton.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Handles the action event (click) on the button
             *
             * @param event the event which triggered this method invocation
             */
            @Override
            public void handle(ActionEvent event) {
                try {
                    // The host to connect to
                    String host = "localhost";
                    // The port to connect to
                    int port = 1234;

                    // Create a socket
                    Socket socket = new Socket(host, port);
                    // Prepare the reader and writer
                    BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // Write the message
                    socketWriter.write(messageTextField.getText() + "\n");
                    socketWriter.flush();

                    // Read the response
                    String response = socketReader.readLine();
                    responseTextField.setText(response);

                    // Cleanup the connection
                    socketWriter.close();
                    socketReader.close();
                    socket.close();
                } catch (Exception exception) {
                    responseTextField.setText("ERROR: " + exception.getMessage());
                    exception.printStackTrace(System.err);
                }
            }
        });


        // A "Scene" is a container for UI elements
        //
        // Create a new scene and add the layout to the scene.
        // We also set the size of the scene (this is found by trial and error - try different dimensions in order to
        // see what the effects are).
        Scene scene = new Scene(grid, 275, 125);


        // Set the scene in the primary stage to be the scene we just created
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }


    /**
     * The main method, the entry point for the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // This method, launch, is implemented in the parent class; this is how we start the application.
        //
        // We pass args to the launch method in case there are any arguments which need to be accessed in the
        // application.
        launch(args);
    }
}