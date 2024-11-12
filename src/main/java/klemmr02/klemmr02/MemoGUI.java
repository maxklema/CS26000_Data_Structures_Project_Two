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
 * 	The Memo GUI holds all the functionality that handles GUI interactions with the user.
 *
 * INSTANCE VARIABLES
 * 	memoPane
 * 		Stores the pane that will contain all of the memo notes.
 * 	backgroundColorChoiceBox
 * 		A select box where the user can select one of nine background colors for the memo note.
 * foregroundColorChoiceBox
 * 		A select box where the user can select one of nine foreground colors for the memo note.
 * memoLinkedList
 * 		The memo linked list that will keep track of all the memos in the GUI
 * fileIO
 *		The File IO object to handle read, write, and merge operations.
 *
 * INSTANCE METHODS
 * initialize
 * 	Sets the choice for the background and foreground colors, and prompts the user to choose a memo file to read.
 * addMemo
 * 	Creates a GUI form for the user to create or update memos.
 * moveMemo
 * 	Creates a GUI form for the user to move an existing memo through the memo ID.
 * deleteMemo
 * 	creates a GUI form for the user to delete an existing memo through the memo ID.
 * readMemos
 * 	Allows the user to select a memo .txt file display on the GUI.
 * mergeMemos
 * 	Allows the user to select a memo .txt file to merge with existing memos on the GUI.
 * writeMemos
 * 	Allows the user to select a memo .txt file to store the memos on the GUI to.
 * clearMemos
 * 	Clears all the memos on the GUI.
 * selectMemoFile
 * 	A helper method to show the user's file explorer, allowing them to choose a memo file to operate on.
 * quitGUI
 * 	Exists the application.
 * createMemos
 * 	A helper method that iterates through every method in the list and creates a memo card in the pane.
 * getColor
 * 	Takes in a color string and returns a color object.
 * createLabel
 * 	Creates a GUI component label.
 * createTextField
 * 	Creates a GUI TextField Component.
 * createButton
 * 	Creates a GUI Button Component.
 * fitInPane
 * 	Returns a boolean value depending on if the memo coordinates are in the pane.
 */

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MemoGUI {

	@FXML
	private Pane memoPane; //Stores the pane that will contain all of the memo notes.
	
	@FXML
	private ChoiceBox<String> backgroundColorChoiceBox; //A select box where the user can select one of nine background colors for the memo note.
	
	@FXML
	private ChoiceBox<String> foregroundColorChoiceBox; //A select box where the user can select one of nine foreground colors for the memo note.
	
	public MemoLinkedList memoLinkedList = new MemoLinkedList(); //The memo linked list that will keep track of all the memos in the GUI
	private FileIO fileIO = new FileIO(memoLinkedList); //The File IO object to handle read, write, and merge operations.
	
	//
	// Sets the choice for the background and foreground colors, and prompts the user to choose a memo file to read.
	//
	public void initialize() {
		
		//set values in background and foreground choice boxes
		backgroundColorChoiceBox.getItems().setAll("Magenta", "Black", "Green", "Red", "Blue", "Cyan", "White", "Pink", "Yellow");
		backgroundColorChoiceBox.setValue("Black");
		
		foregroundColorChoiceBox.getItems().setAll("Magenta", "Black", "Green", "Red", "Blue", "Cyan", "White", "Pink", "Yellow");
		foregroundColorChoiceBox.setValue("White");
		
		// Prompt the user for the name of a memo note file once the GUI loads
		Platform.runLater(() -> {
	        try {
				readMemos();
			} catch (IOException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
	    });
	} //end initialize
	
	// 
	// Creates a GUI form for the user to create or update memos.
	//
	public void addMemo() throws IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Stage addMemoStage = new Stage();
		
		//create components for the form
		Label enterMemoIdLabel = createLabel("Enter Memo ID:");
		TextField memoIdField = createTextField("Enter Memo ID");
		Label enterXCoordLabel = createLabel("Enter X Position");
	    TextField memoXCoordField = createTextField("Enter X Coordinate");
	    Label enterYCoordLabel = createLabel("Enter Y Coordinate:");
	    TextField memoYCoordField = createTextField("Enter Y Coordinate");
	    
	    Label MemoMessage = createLabel("Message:");
	    TextField lineTwo = createTextField("Line 2");
	    TextField lineThree = createTextField("Line 3");
	    TextField lineFour = createTextField("Line 4");
	    Button addMemo = createButton("Add Memo");
	    
	    addMemo.setOnAction(event -> {
	    	
	    	//get memo ID, and X and Y coordinates of the memo to move
	        int memoId = Integer.parseInt(memoIdField.getText());
	        int xCoord = Integer.parseInt(memoXCoordField.getText());
	        int yCoord = Integer.parseInt(memoYCoordField.getText());
	        String lineTwoString = lineTwo.getText();
	        String lineThreeString = lineThree.getText();
	        String lineFourString = lineFour.getText();
	        String foregroundColor = foregroundColorChoiceBox.getValue();
	        String backgroundColor = backgroundColorChoiceBox.getValue();
	        
	        //ensure memo can fit inside the pane
	        if (!fitInPane(xCoord, yCoord)) {
	        	throw new IllegalArgumentException("Invalid argument!");
	        }
	        
	        Memo newMemo = new Memo(memoId, xCoord, yCoord, lineTwoString, lineThreeString, lineFourString, foregroundColor, backgroundColor);
	        memoLinkedList.insertAfter(newMemo);
	        
	        addMemoStage.close();
	        
	        try {
				createMemos();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	    });
	    
	    // Add components to VBox
	    VBox vbox = new VBox(10); // 10px spacing
	    vbox.getChildren().addAll(enterMemoIdLabel, memoIdField, enterXCoordLabel, memoXCoordField, enterYCoordLabel, memoYCoordField, MemoMessage, lineTwo, lineThree, lineFour, addMemo);
	    vbox.setPadding(new Insets(5,5,5,5));
	    
	    // Create a Scene and set it on the stage
	    Scene scene = new Scene(vbox, 300, 350);
	    addMemoStage.setScene(scene);
	    addMemoStage.setTitle("Add Memo");
	    addMemoStage.show();    
	} //end addMemo
	
	//
	// Creates a GUI form for the user to move an existing memo through the memo ID.
	//
	public void moveMemo() {
		Stage moveMemoStage = new Stage();
		
		//create components for the move memo form
	    Label label = createLabel("Memo ID to Move:");
	    TextField memoIDField = createTextField("Enter Memo ID");
	    TextField memoXCoordField = createTextField("Enter X Coordinate");
	    TextField memoYCoordField = createTextField("Enter Y Coordinate");
	    Button moveMemo = createButton("Move Memo");
	    
	    moveMemo.setOnAction(event -> {
	    	
	    	//get memo ID, and X and Y coordinates of the memo to move
	        int memoId = Integer.parseInt(memoIDField.getText());
	        int xCoord = Integer.parseInt(memoXCoordField.getText());
	        int yCoord = Integer.parseInt(memoYCoordField.getText());
	        
	        //ensure memo can fit inside the pane
	        if (!fitInPane(xCoord, yCoord)) {
	        	throw new IllegalArgumentException("Invalid argument!");
	        }
	        
	        //cycle through the nodes until the memo node is found, and then change its coordinates
	        MemoNode head = memoLinkedList.getHead();
	        MemoNode cursor = memoLinkedList.getCursor();
	        cursor = head;
	        while(cursor != null) {
	        	if (cursor.getMemo().getMemoID() == memoId) {
	        		cursor.getMemo().setX(xCoord);
	        		cursor.getMemo().setY(yCoord);
	        	}
	        	cursor = cursor.getLink();
	        }
	   
	       moveMemoStage.close();
	        
	        try {
				createMemos();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	    });
	    
	    // Add components to VBox
	    VBox vbox = new VBox(10); // 10px spacing
	    vbox.getChildren().addAll(label, memoIDField, memoXCoordField, memoYCoordField, moveMemo);
	    vbox.setPadding(new Insets(5,5,5,5));
	    
	    // Create a Scene and set it on the stage
	    Scene scene = new Scene(vbox, 300, 175);
	    moveMemoStage.setScene(scene);
	    moveMemoStage.setTitle("Move Memo");
	    moveMemoStage.show();    
	} //end moveMemo
	
	//
	// Creates a GUI form for the user to delete an existing memo through the memo ID.
	//
	public void deleteMemo() {
		Stage deleteMemoStage = new Stage();

	    // Create components
	    Label label = createLabel("Memo ID:");
	    TextField memoIDField = createTextField("Enter Memo ID");
	    Button deleteMemo = createButton("Delete Memo");

	    // Set up the action for the delete button
	    deleteMemo.setOnAction(event -> {
	        String memoId = memoIDField.getText();
	        memoLinkedList.remove(Integer.parseInt(memoId));
	        deleteMemoStage.close();
	        try {
				createMemos();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	    });

	    // Add components to VBox
	    VBox vbox = new VBox(10); // 10px spacing
	    vbox.getChildren().addAll(label, memoIDField, deleteMemo);
	    vbox.setPadding(new Insets(5,5,5,5));

	    // Create a Scene and set it on the stage
	    Scene scene = new Scene(vbox, 300, 100);
	    deleteMemoStage.setScene(scene);
	    deleteMemoStage.setTitle("Delete Memo");
	    deleteMemoStage.show();
	} //end deleteMemo
	
	//
	// Allows the user to select a memo .txt file display on the GUI.
	//
	public void readMemos() throws IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		File memoFileToRead = selectMemoFile("Select a Text File to Read Memos From");
		fileIO.readMemos(memoFileToRead);
		createMemos();
	} //end readMemos
	
	//
	// Allows the user to select a memo .txt file to merge with existing memos on the GUI.
	//
	public void mergeMemos() throws IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		File memoFileToMerge = selectMemoFile("Selected a Text File to Merge From");
		fileIO.mergeMemos(memoFileToMerge);
		createMemos();
	} //end mergeMemos
	
	//
	// Allows the user to select a memo .txt file to store the memos on the GUI to.
	//
	public void writeMemos() throws IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		File fileToWriteMemo = selectMemoFile("Select a Text File to Write Memos To");
		fileIO.writeMemos(fileToWriteMemo);
		createMemos();
	} //end writeMemos
	
	//
	// Clears all the memos on the GUI.
	//
	public void clearMemos() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		memoLinkedList.clear();
		createMemos();
	} //end clearMemos
	
	//
	// A helper method to show the user's file explorer, allowing them to choose a memo file to operate on.
	//
	private File selectMemoFile(String title){
		
		FileChooser chooseMemoFC = new FileChooser();
		FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
		chooseMemoFC.setTitle(title);
		chooseMemoFC.getExtensionFilters().add(fileFilter);
		
		File projectDirectory = new File(System.getProperty("user.dir"));
		chooseMemoFC.setInitialDirectory(projectDirectory);
		
		File selectedMemoFile = chooseMemoFC.showOpenDialog(backgroundColorChoiceBox.getScene().getWindow());
		return selectedMemoFile;
	} //end selectMemoFile
	
	
	//
	// Exists the application.
	//
	@FXML
	private void quitGUI() {
		Platform.exit();
	} //end quitGUI
	
	//
	// A helper method that iterates through every method in the list and creates a memo card in the pane.
	//
	private void createMemos() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		memoPane.getChildren().clear();
		
		//iterate through every memo node and create a memo GUI
		MemoNode memoNode = memoLinkedList.getHead();
		int manyItems = memoLinkedList.getManyItems();
		for (int i = 0; i < manyItems; i++) {
			
	        //create vbox to organize memo information
	        VBox vBox = new VBox();
	        vBox.setSpacing(2);
	        vBox.setPrefSize(160.0, 160.0);
	        vBox.setLayoutX(0);
	        vBox.setLayoutY(0);
	        vBox.setPadding(new Insets(5, 5, 5, 5));
	        
	        //set Text of Memo
	        Text memoID = new Text("Memo " + memoNode.getMemo().getMemoID());
	        memoID.setFill(getColor(memoNode.getMemo().getFgColor().toUpperCase()));
	        memoID.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	        
	        Text memoText = new Text(memoNode.getMemo().getNote());
	        memoText.setFill(getColor(memoNode.getMemo().getFgColor().toUpperCase()));
	        memoText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
	        
	        //add memo information to the vbox
	        vBox.getChildren().addAll(memoID, memoText);
	        
	        //add pane to place vbox in
			Pane pane = new Pane();
			pane.setPrefSize(160.0, 160.0);
			pane.getChildren().add(vBox);
			pane.setBackground(new Background(new BackgroundFill(
					getColor(memoNode.getMemo().getBgColor().toUpperCase()), // Set desired color here
		            CornerRadii.EMPTY, // No rounded corners
		            Insets.EMPTY)));

	        // Set the position of the pane and vbox (x, y coordinates)
	        pane.setLayoutX(memoNode.getMemo().getX()); // X coordinate
	        pane.setLayoutY(memoNode.getMemo().getY()); // Y coordinate

	        // Add the square to the pane
	        memoPane.getChildren().add(pane);
	        
			memoNode = memoNode.getLink();
		}
	} //end createMemos
	
	//
	// Takes in a color string and returns a color object.
	//
	private Color getColor(String Color) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        // I researched this method online.
		Field field = Color.class.getField(Color);
        return (Color) field.get(null);
	} //end getColor
	
	//
	// Creates a GUI component label.
	//
	private Label createLabel(String labelText) {
		Label label = new Label(labelText);
	    label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    return label;
	} //end createLabel
	
	//
	// Creates a GUI TextField Component
	//
	private TextField createTextField(String promptText){
		TextField textField = new TextField();
	    textField.setPromptText(promptText);
	    textField.setMinWidth(200);
	    return textField;
	} //end createTextField
	
	//
	// Creates a GUI Button Component
	//
	private Button createButton(String buttonText){
		Button button = new Button(buttonText);
	    button.setMinWidth(290);
	    return button;
	} //end createButton
	
	//
	// Returns a boolean value depending on if the memo coordinates are in the pane.
	//
	private boolean fitInPane(int x, int y) {
		if (x <= 566 && y <= 408) {
			return true;
		} else {
			return false;
		}
	} //end fitInPane
} //end MemoGUI
