/**
 * Cell Class: Represents a single cell on the board as an arrayList containing strings. 
 * @author Advitya Singh
 * I affirm that I have carried out my academic endeavors
with full academic honesty. [Advitya Singh] 
 */


package igel;

import java.util.ArrayList;
/*
 * Models a single cell of the Igel Aergern board.
 * @author Aditya Singh
 * Honor Code: 
 */
public class Cell {
    
    private ArrayList<String> eachCell = new ArrayList<>();
    private Boolean isTrap;
    private Boolean trapStatus;

    /*
     * @instanceVariable eachCell is an ArrayList of strings where each element is a token of given cell. 
     * @instanceVariable isTrap is true if cell is trap and false is cell is not trap.
     * @instanceVariable trapStatus is true for a cell if that a trap cell has a token in it. 
     * it is false if trap cell is empty. 
     */

    /**
     * Create a single cell of an Igel Aergern board.
     */
    public Cell() {
        this.eachCell.add("");
        this.isTrap = false;
        this.trapStatus = false;
    }

    /**
     * Toggle whether or not this cell is a trap. If the cell is NOT a trap, 
     * call this method turns it into one. If the cell IS a trap, calling this
     * method turns it into a regular cell. 
     */
    public void toggleTrap() {
        if (this.isTrap == false){
            // Make a cell a trap if it not a trap
            this.isTrap = true; 
        } else if (this.isTrap == true){
            // Remove trap if cell is a trap
            this.isTrap = false; 
        }
    }

    /**
     * Checks whether the cell is a trap.
     * 
     * @return
     */
    public boolean isTrap() {
        return this.isTrap;
    }

    /**
     * Return a string representation of the cell that is suitable for the IgelView
     * interface. E.g. 'RBO' represents a stack of red at the bottom, blue, and orange.
     * '@BY' represents a cell that is a trap with a stack of blue at the bottom and
     * yellow on top.
     */
    public String toString() {
        String repr = "";
        if (this.isTrap == true){
        // If the cell is a trap, adds '@' as the first element.
            repr += ('@');
        }

        // Adds every element from the arrayList to the string representation
        for(int eachToken=0; eachToken<eachCell.size();eachToken++){
            repr += eachCell.get(eachToken);
        }
        return repr;
    }

    public void addColorAsChar(char color) {
        // Add the color to the end of the eachCell list (last index)
        this.eachCell.add(String.valueOf(color));
    }    

    public void addColorAsString(String color) {
        // Add the color to the end of the eachCell list (last index)
        this.eachCell.add(String.valueOf(color));
    }    

    public void removeColor() {
        // Add the color to the end of the eachCell list (last index)
        this.eachCell.remove(eachCell.size() - 1);
    }    

    public String getTopTokenAsString() {
        // gets the top token of a cell as a string
        String topToken = this.eachCell.get(eachCell.size() - 1);
        return topToken;
    }    

    // Complete the above methods and add any additional methods you need.
    
}
