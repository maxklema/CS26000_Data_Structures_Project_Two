package klemmr02.klemmr02;

/*
 * NAME: Maxwell Klema
 * PROJECT: PROJECT TWO
 * COURSE: CS26000
 * INSTRUCTOR: KIM, B.
 * LAB TIME: N/A
 * DUE DATE: 10/24/24
 * 
 * DESCRIPTION
 * 	Project2 includes the main driver as well as some supplementary javafx methods to load all GUI scenes.
 *
 * INSTANCE VARIABLES
 * 	scene
 * 		Stores the main GUI scene based on styles in primary.fxml
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Project2 extends Application {

    private static Scene scene; //The main GUI Scene

    @Override
    //
    // sets the scene to the main GUI scene using primary.fxml and displays it
    //
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 940, 560);
        stage.setScene(scene);
        stage.setTitle("Maxwell Klema Project Two CS26000");
        stage.show();
    } //end start

    //
    //sets the scene's root to primary.fxml
    //
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    } //end setRoot

    //
    //loads the primary.fxml which stores the style for the GUI
    //
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Project2.class.getResource(fxml + ".fxml"));  
        return fxmlLoader.load();
    } //end loadFXML

    public static void main(String[] args) {
        launch(); //all GUI-related logic is in the *GUI.java files. 	
    } //end main
    
} //end Project2