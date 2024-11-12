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
 * 	The MemoLinkedList class creates a single linked list of memo nodes.
 *
 * CONSTRUCTOR METHODS
 *	MemoLinkedList
 *		Creates a new memo linked list. It sets cursor and head to null and manyItems to 0.
 *
 * INSTANCE VARIABLES
 * 	head
 * 		Stores the first memo node of the list.
 *	cursor
 *		Stores the memory address of the currently selected node.
 *	manyItems
 *		stores the total amount of memo nodes in the list.
 *
 * INSTANCE METHODS
 * insertAfter
 * 	Inserts the input argument after the cursor pointing node in a linked list.
 * search
 * 	This method will find a location to which applies the insertion or deletion operations.
 * remove
 * 	When a list is not empty, this method removes the cursor pointing node from the list.
 * retrieve
 * 	When a list is not empty, this method returns a copy of the cursor pointing node
 * merge
 * 	Merges nodes in the input argument list, "other_list", into this list
 * goToPrior
 * 	sets cursor to the previous node
 * clear
 * 	removes all the nodes in a list.
 * goToEnd
 * 	Sets the cursor to last node in the list.
 * gotoNext
 * 	Sets the cursor to the next node in the list.
 * gotoBeginning
 * 	Sets the cursor to the first node in the list.
 * getHead
 * 	Gets the node that head points to.
 * setHead
 * 	Sets the node that head points to.
 * getCursor
 * 	Gets the node that cursor points to.
 * setCursor
 * 	Sets the node that cursor points to.
 * getManyItems
 * 	Retrieves the amount of items in the linked list
 */

public class MemoLinkedList {

    private MemoNode head; // Stores the first memo node of the list.
    private MemoNode cursor; // Stores the memory address of the currently selected node.
    private int manyItems; // Stores the total number of memo nodes in the list.

    //
    // Creates a new memo linked list. It sets the cursor and head to null and manyItems to 0.
    //
    public MemoLinkedList() {
        this.head = null;
        this.cursor = null;
        this.manyItems = 0;
    } // end MemoLinkedList

    //
    // Inserts the input argument after the node that the cursor is pointing to in the linked list.
    //
    public void insertAfter(Memo newMemo) {
        // Check if the list is empty
        if (manyItems == 0) {
            head = new MemoNode(newMemo, head);
            cursor = head;
            manyItems++;
        } else {
            if (search(newMemo.getMemoID())) { // If that Memo ID already exists, update the memo
                cursor.setMemo(newMemo);
            } else { // Insert new memoNode after cursor and update cursor
                cursor.setLink(new MemoNode(newMemo, cursor.getLink()));
                cursor = cursor.getLink();
                manyItems++;
            }
        }
    } // end insertAfter

    //
    // This method finds a location for the insertion or deletion operations.
    //
    private boolean search(int memoID) {
        // Use a temporary cursor to search for a memo, if it exists
        for (MemoNode temporaryCursor = head; temporaryCursor != null; temporaryCursor = temporaryCursor.getLink()) {
            if (temporaryCursor.getMemo().getMemoID() == memoID) {
                cursor = temporaryCursor;
                return true;
            }
        }
        return false;
    } // end search

    //
    // When the list is not empty, this method removes the node that the cursor is pointing to from the list.
    //
    public void remove(int memoID) {
        if (manyItems != 0) {
            if (search(memoID)) { // If memo ID is in the list
                if (head == cursor) {
                    head = head.getLink();
                } else {
                    goToPrior(); // Set cursor to prior node
                    cursor.setLink(cursor.getLink().getLink());
                }
                cursor = cursor.getLink();
                // If cursor pointed to the last element, set it to the head pointing element
                if (cursor == null) {
                    gotoBeginning();
                }
                manyItems--;
            }
        }
    } // end remove

    //
    // When the list is not empty, this method returns a copy of the node that the cursor is pointing to.
    //
    public MemoNode retrieve() throws CloneNotSupportedException {
        if (manyItems != 0) {
            return cursor.clone();
        }
        return new MemoNode(null, null);
    } // end retrieve

    //
    // Merges nodes from the input argument list, "otherList," into this list.
    //
    public void merge(MemoLinkedList otherList) {
        // Compare each node to remove duplicate memo IDs
        cursor = head;
        while (cursor != null) {
            MemoNode cursorNew = otherList.getCursor();
            MemoNode headNew = otherList.getHead();
            cursorNew = headNew;
            while (cursorNew != null) {
                if (cursorNew.getMemo().getMemoID() == cursor.getMemo().getMemoID()) {
                    int idToRemove = cursor.getMemo().getMemoID();
                    cursor = head;
                    remove(idToRemove);
                    break;
                }
                cursorNew = cursorNew.getLink();
            }
            cursor = cursor.getLink();
        }

        cursor = head;
        gotoEnd(); // Set the cursor to the end of the current list
        if (manyItems != 0) {
            cursor.setLink(otherList.getHead());
        } else {
            if (otherList.getManyItems() != 0) {
                MemoNode firstMemo = otherList.getHead();
                head = firstMemo;
            }
        }
        manyItems += otherList.getManyItems();
    } // end merge

    //
    // Sets the cursor to the previous node.
    //
    private void goToPrior() {
        MemoNode temporaryCursor = head;
        while (temporaryCursor.getLink() != cursor) {
            temporaryCursor = temporaryCursor.getLink();
        }
        cursor = temporaryCursor;
    } // end goToPrior

    //
    // Removes all the nodes in the list.
    //
    public void clear() {
        head = null;
        cursor = null;
        manyItems = 0;
    } // end clear

    //
    // Sets the cursor to the last node in the list.
    //
    public void gotoEnd() {
        if (manyItems != 0) {
            while (cursor.getLink() != null) {
                cursor = cursor.getLink();
            }
        } else {
            cursor = head;
        }
    } // end gotoEnd

    //
    // Sets the cursor to the next node in the list.
    //
    public void gotoNext() {
        if (cursor.getLink() != null) {
            cursor = cursor.getLink();
        }
    } // end gotoNext

    //
    // Sets the cursor to the first node in the list.
    //
    public void gotoBeginning() {
        cursor = head;
    } // end gotoBeginning

    //
    // Gets the node that head points to.
    //
    public MemoNode getHead() {
        return head;
    } // end getHead

    //
    // Sets the node that head points to.
    //
    public void setHead(MemoNode head) {
        this.head = head;
    } // end setHead

    //
    // Gets the node that cursor points to.
    //
    public MemoNode getCursor() {
        return cursor;
    } // end getCursor

    //
    // Sets the node that cursor points to.
    //
    public void setCursor(MemoNode cursor) {
        this.cursor = cursor;
    } // end setCursor

    //
    // Retrieves the number of items in the linked list.
    //
    public int getManyItems() {
        return manyItems;
    } // end getManyItems

    //hw3
    public void deleteOddNodes() {
        head = head.getLink();
        cursor = head;
        manyItems--;
        while (cursor != null && cursor.getLink() != null){
            cursor.setLink(cursor.getLink().getLink());
            manyItems--;
            cursor = cursor.getLink();
        }
    }

    //hw3
    public void printReverse(){
        cursor = head;

        MemoLinkedList reverseList = new MemoLinkedList();
        while (cursor != null) {
            reverseList.head = new MemoNode(cursor.getMemo(), reverseList.head);
            reverseList.manyItems++;
            cursor = cursor.getLink();
        }

        System.out.println("many Items: " + reverseList.manyItems);

        //print reverse list
//        reverseList.cursor = reverseList.head;
//        while (reverseList.cursor != null){
//            System.out.println(reverseList.cursor.getMemo().getMemoID());
//            reverseList.cursor.setLink(reverseList.cursor.getLink());
//        }
        MemoNode memoNode = reverseList.getHead();
        int manyItems = reverseList.getManyItems();
        for (int i = 0; i < manyItems; i++) {

            System.out.println(memoNode.getMemo().getMemoID());
            memoNode = memoNode.getLink();
        }
    }

} //end memoLinked List

