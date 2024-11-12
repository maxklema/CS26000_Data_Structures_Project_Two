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
 * 	The Memo Node Class creates a Memo Node object, storing the memo and the next memo node.
 *
 * CONSTRUCTOR METHODS
 *	MemoNode
 *		Creates a new memo node, taking in the memo and the next memo node as parameters and setting them to instance variables.
 *
 * INSTANCE VARIABLES
 * 	memoNode
 * 		Stores the memo node link to the next memo node.
 *	Memo
 *		Stores the memo object that is stored in the memo node.
 *
 * INSTANCE METHODS
 *	getLink
 *		Retrieves the memo node link from the memo node.
 *	setLink
 *		sets the memo node link inside the memo node.
 *	getMemo
 *		Retrieves the memo from the memo node.
 *	setMemo
 *		sets the memo inside the memo node.
 *	clone
 *		Creates a copy of the memo node object.
 *
 */

public class MemoNode implements Cloneable {

    private MemoNode link; // Stores the memo node link to the next memo node.
    private Memo memo; // Stores the memo object that is stored in the memo node.

    //
    // Creates a new memo node, taking in the memo and the next memo node as parameters and setting them to instance variables.
    //
    public MemoNode(Memo memo, MemoNode link) {
        this.memo = memo;
        this.link = link;
    } // end MemoNode

    //
    // Retrieves the memo from the memo node.
    //
    public Memo getMemo() {
        return memo;
    } // end getMemo

    //
    // Sets the memo inside the memo node.
    //
    public void setMemo(Memo memo) {
        this.memo = memo;
    } // end setMemo

    //
    // Retrieves the memo node link from the memo node.
    //
    public MemoNode getLink() {
        return link;
    } // end getLink

    //
    // Sets the memo node link inside the memo node.
    //
    public void setLink(MemoNode link) {
        this.link = link;
    } // end setLink

    //
    // Creates a copy of the memo node object.
    //
    public MemoNode clone() throws CloneNotSupportedException {
        MemoNode copy = (MemoNode) super.clone();
        copy.memo = this.memo.clone();
        copy.link = this.link;
        return copy;
    } // end clone
} // end MemoNode
