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
 * 	The Memo class represents memos to be stored in a memoLinkedList.
 *
 * CONSTRUCTOR METHODS
 *	Memo
 *		Creates a new memo, assigning it x y coordinates, background and foreground colors, a memo ID, and text.
 *
 * INSTANCE VARIABLES
 * 	memoID
 * 		Stores the ID of the memo.
 *	x
 *		Stores the x coordinate of the memo.
 *	y
 *		Stores the y coordinate of the memo.
 *	note
 *		Combines lines 2, 3, and 4 into a single note
 *	fgColor
 *		Stores the foreground color of a note.
 *	bgColor
 *		Stores the background color of a note.
 *
 * INSTANCE METHODS
 * validateColors
 * 		A helper method to ensure the user-inputed colors are formatted correctly
 * equals
 * 		A method to compare two memos and evaluate if they are the same.
 * clone
 * 		A method to clone a memo
 * get memoID
 * 		Retrieves the memoID
 * set memoID
 * 		sets the memoID
 * getX
 * 		Retrieves the X Coordinate
 * setX
 * 		Sets the X Coordinate.
 * getY
 * 		Retrieves the X Coordinate.
 * setY
 * 		Sets the X Coordinate.
 * getNote
 * 		Retrieves the Note.
 * setNote
 * 		Sets the contents of the Note.
 * getFgColor
 * 		Gets the foreground color.
 * setFgColor
 * 		Sets the foreground color.
 * getBgColor
 * 		Gets the background color.
 * setBgColor
 * 		Sets the background color.
 */

public class Memo implements Cloneable {

    private int memoID; // Stores the ID of the memo.
    private int x; // Stores the x coordinate of the memo.
    private int y; // Stores the y coordinate of the memo.
    private String note; // Combines lines 2, 3, and 4 into a single note
    private String fgColor; // Stores the foreground color of a note.
    private String bgColor; // Stores the background color of a note.

    //
    // Creates a new memo, assigning it x y coordinates, background and foreground colors, a memo ID, and text.
    //
    public Memo(int memoID, int x, int y, String lineTwo, String lineThree, String lineFour, String fgColor, String bgColor) {
        this.memoID = memoID;
        this.x = x;
        this.y = y;
        this.note = "";
        this.fgColor = fgColor;
        this.bgColor = bgColor;

        //validate line lengths for note
        String[] lines = {lineTwo, lineThree, lineFour};

        //iterate over each line and concatenate them to note
        for (String line : lines) {
            if (line.length() > 20){
                this.note += line.substring(0,20) + "\n";
            } else if (!line.isEmpty()){
                this.note += line + "\n";
            }
        }
        //ensure that note does not end on a new line
        if (note.endsWith("\n")){
            note = note.substring(0, note.length()-1);
        }

        validateColors();
    } //endMemo

    //
	// A helper method to ensure the user-inputed colors are formatted correctly
	//
	 private void validateColors(){
	     String[] colors = {"magenta", "black", "green", "red", "blue", "cyan", "white", "pink", "yellow"};
	     int i;
	
	     // check if the foreground color is valid
	     for (i = 0; i < colors.length; i++){
	         if (this.fgColor.toLowerCase().equals(colors[i])){
	             break;
	         }
	     }
	     if (i == colors.length){
	         this.fgColor = "white";
	     }
	
	     // check if the background color is valid
	     for (i = 0; i < colors.length; i++){
	         if (this.bgColor.toLowerCase().equals(colors[i])){
	             break;
	         }
	     }
	     if (i == colors.length){
	         this.bgColor = "black";
	     }
	 } // end validateColors
	
	 //
	 // A method to compare two memos and evaluate if they are the same.
	 //
	 public boolean equals(Object obj){
	     Memo memoToCompare;
	     if (obj instanceof Memo){
	         memoToCompare = (Memo) obj;
	         return memoToCompare.memoID == this.memoID && memoToCompare.x == this.x && memoToCompare.y == this.y && memoToCompare.note.equals(this.note) && fgColor.equals(memoToCompare.fgColor) && bgColor.equals(memoToCompare.bgColor);
	     } else {
	         return false;
	     }
	 } // end equals
	
	 //
	 // A method to clone a memo
	 //
	 public Memo clone() throws CloneNotSupportedException {
	     Memo copy = (Memo) super.clone();
	     copy.note = this.note;
	     copy.fgColor = this.fgColor;
	     copy.bgColor = this.bgColor;
	     return copy;
	 } // end clone
	
	 //
	 // Retrieves the memoID
	 //
	 public int getMemoID() {
	     return memoID;
	 } // end getMemoID
	
	 //
	 // Sets the memoID
	 //
	 public void setMemoID(int memoID) {
	     this.memoID = memoID;
	 } // end setMemoID
	
	 //
	 // Retrieves the X Coordinate
	 //
	 public int getX() {
	     return x;
	 } // end getX
	
	 //
	 // Sets the X Coordinate.
	 //
	 public void setX(int x) {
	     this.x = x;
	 } // end setX
	
	 //
	 // Retrieves the Y Coordinate.
	 //
	 public int getY() {
	     return y;
	 } // end getY
	
	 //
	 // Sets the Y Coordinate.
	 //
	 public void setY(int y) {
	     this.y = y;
	 } // end setY
	
	 //
	 // Retrieves the Note.
	 //
	 public String getNote() {
	     return note;
	 } // end getNote
	
	 //
	 // Sets the contents of the Note.
	 //
	 public void setNote(String note) {
	     this.note = note;
	 } // end setNote
	
	 //
	 // Gets the foreground color.
	 //
	 public String getFgColor() {
	     return fgColor;
	 } // end getFgColor
	
	 //
	 // Sets the foreground color.
	 //
	 public void setFgColor(String fgColor) {
	     this.fgColor = fgColor;
	 } // end setFgColor
	
	 //
	 // Gets the background color.
	 //
	 public String getBgColor() {
	     return bgColor;
	 } // end getBgColor
	
	 //
	 // Sets the background color.
	 //
	 public void setBgColor(String bgColor) {
	     this.bgColor = bgColor;
	 } // end setBgColor
} //end Memo