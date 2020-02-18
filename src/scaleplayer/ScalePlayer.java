/*
 * CS 300-A, 2017S
 */
package scaleplayer;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This JavaFX app lets the user play scales.
 * @author Janet Davis 
 * @author Buyaki Nyatichi
 * @author Zoe Hill
 * @author Tracy Cui
 * @since February 10, 2020
 */
public class ScalePlayer extends Application {
   
    private MidiPlayer composition; //current composition declaration
    
    @FXML
    private MenuItem menuItem1;
    @FXML
    private Button playScaleBtn;
    @FXML
    private Button stopPlayingBtn;
   
    /**
     * Plays Do Re Mi Fa Sol La Ti Do notes starting at a note given by the
     * user, going up in pitch and back down from the highest note.
     * @param startNote
     * @return none
     */
    public void playComposition(int startNote){
        composition.clear();
        
        int[] step;
        step = new int[]{0,2,2,1,2,2,2,1};
        int currentNote = startNote;
        for(int i = 1; i <= 8; i++){
            currentNote = currentNote + step[i-1];
            composition.addNote(currentNote, 127, i, 1, 0, 7); // scale going up
            composition.addNote(currentNote, 127, 17-i, 1, 0, 7); //scale going down
        }
        composition.play();
    }
    
    /**
     * Creates a Text Input Dialog that asks the user for a starting note.
     * @param unused
     * @return none
     */

    public void inputTextDialog(){
        TextInputDialog dialog = new TextInputDialog();
       
        dialog.setTitle("Starting note");
        dialog.setHeaderText("Give me a starting note (0-115):");
       
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
           
            String note;
            note = result.get();
            int noteValue = Integer.parseInt(note);
            playComposition(noteValue); 
        }

    }

    /**
     * create a new  Midi composition object
     */
    public void initialize(){
        composition = new MidiPlayer(1,60);
    }
    
    /**
    * Call initialize to launch a new Midi composition object and calls createWindow.
    * @param primaryStage
     * @throws java.io.IOException
    */
    @Override
    public void start(Stage primaryStage) throws IOException {
        initialize();
        Parent root = FXMLLoader.load(getClass().getResource("ScalePlayer.fxml"));
        primaryStage.setTitle("ScalePlayer");
        primaryStage.setScene(new Scene(root,300,250));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent t){
                Platform.exit();
                System.exit(0);
            }
        });
        
    }
  
    /** launch the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void HandleExit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void HandlePlayBotton(ActionEvent event) {
        inputTextDialog(); 
    }

    @FXML
    private void HandleStopBotton(ActionEvent event) {
        composition.stop();
        composition.clear(); 
    }
   
}