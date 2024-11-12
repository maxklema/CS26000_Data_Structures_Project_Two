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
 * 	FileIO handles input and output operations with merge, read, and write functionalities.
 *
 * CONSTRUCTOR METHODS
 *	FileIO
 *		Creates a FileIO object which takes in a memoLinkedList and assigns it to the instance variable.
 *
 * INSTANCE VARIABLES
 * 	memoLinkedList
 * 		performs write, merge, and read operations on the linked list stored in this instance variable.
 *
 * INSTANCE METHODS
 * 	readMemos
 * 		takes in a txt file, and assigns the memoLinkedList to the linked list returned in readMemosHelperMethod.
 * 
 * readMemosHelperMethod
 * 		Takes in a memoLinkedList and an input text filer, uses a scanner to parse the file, and stores in the linked list.
 *
 * writeMemos
 * 		Takes in a input file, parses through the memos in the linked list, and writes it to the file.
 * 
 * mergeMemos
 * 	Takes a memos file, calls the helper method to parse the file into a linked list, and then calls the merge method.
 *
 */

import java.io.*;
import java.util.Scanner;

public class FileIO {
	private MemoLinkedList memoLinkedList; //performs write, merge, and read operations on the linked list stored in this instance variable.

	//
	// Creates a FileIO object which takes in a memoLinkedList and assigns it to the instance variable.
	//
    public FileIO(MemoLinkedList memoLinkedList){
        this.memoLinkedList = memoLinkedList;
    } //end fileIO

    //
    // Takes in a txt file, and assigns the memoLinkedList to the linked list returned in readMemosHelperMethod.
    //
    public void readMemos(File fileToRead) throws IOException {
        this.memoLinkedList = readMemosHelperMethod(this.memoLinkedList, fileToRead);
    } //end readMemos

    //
    // Takes in a memoLinkedList and an input text filer, uses a scanner to parse the file, and stores in the linked list.
    //
    public MemoLinkedList readMemosHelperMethod(MemoLinkedList memoList, File fileToRead) throws IOException {
        if (fileToRead != null){
            memoList.clear(); //clear the list

            //read through each line in the txt file
            Scanner scanner = new Scanner(fileToRead);
            while (scanner.hasNextLine()){

                //parse memoID
                String memoIDString = scanner.nextLine();
                int memoID = Integer.parseInt(memoIDString);

                //parse XY coordinates
                String coordinatesXY = scanner.nextLine();
                String[] coordinatesArray = coordinatesXY.split("\\s");
                int x = Integer.parseInt(coordinatesArray[0]);
                int y = Integer.parseInt(coordinatesArray[1]);

                //Parse lines to form Note
                String[] lines = {"", "", ""};
                int line = 0;
                String nextLine = scanner.nextLine();

                while(!nextLine.startsWith("Color.")){
                    lines[line] = nextLine;
                    nextLine = scanner.nextLine();
                    line++;
                }

                //set foreground and background colors;
                String foregroundColor = nextLine.substring(6);
                String backgroundColor = scanner.nextLine().substring(6);

                //create a new memo and add it to the linkedList
                Memo memo = new Memo(memoID, x, y, lines[0], lines[1], lines[2], foregroundColor, backgroundColor);
                memoList.insertAfter(memo);
            }
            scanner.close();
        } else {
            throw new RuntimeException("There was a problem reading the file.");
        }

        return memoList;
    } //end readMemosHelperMethod
    
    //
    // Takes in a input file, parses through the memos in the linked list, and writes it to the file.
    //
    public void writeMemos(File fileToWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(fileToWrite);

        //iterate over the MemoLinkedList
        this.memoLinkedList.gotoBeginning();
        MemoNode cursor = this.memoLinkedList.getCursor();
        while (cursor != null){
            //write the different memo components to the output file
           fileWriter.write(String.format("%d\n", cursor.getMemo().getMemoID()));
           fileWriter.write(String.format("%d %d\n", cursor.getMemo().getX(), cursor.getMemo().getY()));
           fileWriter.write(cursor.getMemo().getNote() + "\n");
           fileWriter.write("Color." + cursor.getMemo().getFgColor() + "\n");
           fileWriter.write("Color." + cursor.getMemo().getBgColor() + "\n");
           cursor = cursor.getLink();
        }

        fileWriter.close();
    } //end writeMemos

    //
    // takes a memos file, calls the helper method to parse the file into a linked list, and then calls the merge method.
    //
    public void mergeMemos(File memoFileToMerge) throws IOException {
        MemoLinkedList memoListToMerge = new MemoLinkedList();
        memoListToMerge = readMemosHelperMethod(memoListToMerge, memoFileToMerge);
        memoLinkedList.merge(memoListToMerge);
    } //end mergeMemos
} //end FileIO
