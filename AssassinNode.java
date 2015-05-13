// CS 143, Win 2015 ms rs
// p3 Assassin
//
// AssinNode.java Instructor-provided support class.  
// You must not modify this code to work with your own linked list class!

/** Each AssassinNode object represents a single node in a linked list
    for a game of Assassin. */
public class AssassinNode {
    public String name;        // this person's name
    public String killer;      // name of who killed this person (null if alive)
    public AssassinNode next;  // next node in the list (null if none)
    
    /** Constructs a new node to store the given name and no next node. */
    public AssassinNode(String name) {
        this(name, null);
    }

    /** Constructs a new node to store the given name and a reference
        to the given next node. */
    public AssassinNode(String name, AssassinNode next) {
        this.name = name;
        this.killer = null;
        this.next = next;
    }
}
