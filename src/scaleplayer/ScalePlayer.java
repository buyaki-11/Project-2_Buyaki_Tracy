/*
 * CS 300-A, 2017S
 */
package scaleplayer;

import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
 * @since February 10, 2020
 */
public class ScalePlayer extends Application {
   
    private MidiPlayer composition;
    
     /**
     * Creates a window with a "File" menu, an "Exit" menu item and the "play"
     * and "stop buttons.
     * @param primaryStage
     * @return nothing
     */
    private void createWindow(Stage primaryStage){
        Button playScaleBtn = new Button();
        playScaleBtn.setText("Play Scale");
        playScaleBtn.setStyle("-fx-background-color: #16a80a; ");
        playScaleBtn.setOnAction(new EventHandler<ActionEvent>(){
           
            @Override
            public void handle(ActionEvent event) {
                inputTextDialog(); 
            }
        });
         
        Button stopPlayingBtn = new Button();
        stopPlayingBtn.setText("Stop Playing");
        stopPlayingBtn.setStyle("-fx-background-color: #dc1205; ");
        stopPlayingBtn.setOnAction(new EventHandler<ActionEvent>(){
           
            @Override
            public void handle(ActionEvent event) {
                composition.stop();
                composition.clear();
               
            }
        });
       
        Menu menu = new Menu("File");
        MenuItem menuItem1 = new MenuItem("Exit");
        menuItem1.setOnAction(e -> {
            primaryStage.close();
        });
       
        menu.getItems().add(menuItem1);
        MenuBar mb = new MenuBar();
        mb.getMenus().add(menu);
       
        VBox vb = new VBox(mb);
        HBox hb = new HBox();
        HBox root = new HBox();
       
        hb.getChildren().add(playScaleBtn);
        hb.getChildren().add(stopPlayingBtn);
        hb.setSpacing(15);
        hb.setAlignment(Pos.CENTER);
       
        BorderPane border = new BorderPane();
        border.setTop(vb);
        border.setCenter(hb);
       
        StackPane menuStack = new StackPane();
        menuStack.getChildren().add(border);
        root.getChildren().add(menuStack);
        HBox.setHgrow(menuStack, Priority.ALWAYS);
       
        Scene scene = new Scene(root, 300, 250);
       
        primaryStage.setTitle("Scale Player");
        primaryStage.setScene(scene);
        primaryStage.show();
        
         primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent t){
                Platform.exit();
                System.exit(0);
            }
        });
   
    }
   
    /**
     * Plays Do Re Mi Fa Sol La Ti Do notes starting at a note given by the
     * user, going up in pitch and back down from the highest note.
     * @param startNote
     * @return nothing
     */
    private void playComposition(int startNote){
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
     * @return nothing
     */

    private void inputTextDialog(){
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
        /*
        if((buttonResult.isPresent()) && (buttonResult.get() == ButtonType.OK)){
            composition.stop();
        }
        */
    }
   /**
    * Declares a new Midi composition object and calls createWindow.
    * @param primaryStage 
    * @return nothing
    */
    @Override
    public void start(Stage primaryStage) {
        composition = new MidiPlayer(1,60);
        createWindow(primaryStage);    
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
}