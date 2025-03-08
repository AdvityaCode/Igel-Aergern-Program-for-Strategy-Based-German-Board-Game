/**
 * Board Class: Represents the board as an arrayList of arrays containing cell objects. 
 * @author Advitya Singh
 * I affirm that I have carried out my academic endeavors
with full academic honesty. [Advitya Singh] 
 */

package igel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    
    private Cell[][] board;
    public static final int ROWS = 6;
    public static final int COLUMNS = 9;
    public static final int[] TRAPS = {3, 6, 4, 5, 2, 7}; //these are column indexes for traps

    /**
     * Create an empty Igel Aergern board. The board has 6 rows and 9 columns. 
     * The traps are in colums 3, 6, 4, 5, 2, 7 (one per row from row 1 to row 6).
     * 
     * Board instance variable is defined as an array of length 6. Each of these arrays contain 9 Cell objects each. 
     */
    public Board() {
        board = new Cell[6][9];  // Create a 2D array with 6 rows and 9 columns
        
        // Initialize each cell in the 2D array
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 9; col++) {
                board[row][col] = new Cell();  // Each cell is an instance of the Cell class
            }
        }
        board[0][3].toggleTrap();
        board[1][6].toggleTrap();
        board[2][4].toggleTrap();
        board[3][5].toggleTrap();
        board[4][2].toggleTrap();
        board[5][7].toggleTrap();

    }

    /**
     * Return a string representation of the board that is suitable to be passed 
     * to an IgelView interface.
     * 
     * The tracks/rows are separated from each other using // and cells within one 
     * track are separated using |. Empty cells are represented using an empty string. 
     * Otherwise they are represented as a sequence of the stacked colors. E.g. 'RBO' 
     * represents a stack of red at the bottom, blue, and orange.
     * 
     * Here is an example of a valid board representation:
     * "Y||||||||//||||||||Y//Y||R||B||O||//YR|RBO||RBOO|||||//||||||||//||BR||||||O"
     */
    public String toString() {
	    // Already implemented for you. Uncomment once you have finished the
	    // constructor.
        String repr = "" ;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                repr += board[r][c].toString();
                if (c < COLUMNS-1) {
                    repr += "|";
                }
            }
            if (r < ROWS-1) {
                repr += "//";
            }
        }
        return repr;
    }
    
    /**
     * Create a new token of the given color and place it on the board.
     * 
     * @param row row number where to place the token
     * @param col column number where to place the token
     * @param color character indicating the color; has to be from Players.COLORS
     */
    public void placeToken(int row, int col, char color) {
        if (row >= 0 && row <= 5 && col >= 0 && col <= 8) {
            board[row][col].addColorAsChar(color);  // Add color to the top of the cell (last index)
        } else {
            System.out.println("Invalid row or column index");
        }
    }

    /**
     * Move the top token in the cell indicated by the row and column numbers sideways.
     * 
     * @param row row number of token to move
     * @param col column number of token to move
     * @param dir a negative number indicates a sideways move to row-1; otherwise move to row+1
     */
    public void moveSideways(int row, int col, int dir) {
        if (row >= 0 && row < 5 && col >= 0 && col < 8) {
            String topToken = board[row][col].getTopTokenAsString();
            if (dir == -1){
                board[row-1][col].addColorAsString(topToken);
                board[row][col].removeColor();
            } else if (dir == 1) {
                board[row+1][col].addColorAsString(topToken);
                board[row][col].removeColor();
            }
            } else {
            System.out.println("Invalid row or column index");
        }
    }

    /**
     * Move the top token in the cell indicated by the row and column numbers 1 step forward.
     * 
     * Note: This method does no checking whether there is a token in that cell and whether 
     * it is legal to move it forward.
     * 
     * @param row row number of token to move
     * @param col column number of token to move
     */
    public void moveForward(int row, int col) {
        if (row >= 0 && row <= 5 && col >= 0 && col < 8) {
            String topToken = board[row][col].getTopTokenAsString();
            board[row][col+1].addColorAsString(topToken);
            board[row][col].removeColor();
            } else {
            System.out.println("Invalid row or column index");
    }
    }

    /**
     * Return how many tokens of the given color are in the given column.
     * 
     * @param col Column number in which tokens should be counted from
     * @param color token of which color should be counted
     * @return the number of tokens of the color in column provided in as parameters
     */
    public int countTokensInColumn(int col, char color) {
        int colorCount = 0;
        for (int r = 0; r < ROWS; r++) {
            String boardAsString;
            boardAsString = board[r][col].toString();
            for (int eachTokenInCell = 0; eachTokenInCell < boardAsString.length(); eachTokenInCell++) {
                if (boardAsString.charAt(eachTokenInCell) == color) {
                    colorCount++;
                }
            }
        }
        return colorCount ;
    }

    /**
     * Check whether the stack in the cell indicated by row and col contains a
     * token of the given color.
     * 
     * @param row row number on board
     * @param col column number in board
     * @param color color of token to check in row and column provided 
     * @return boolean indicating if a certain cell contains a token of a particular color. 
     */
    public boolean containsColor(int row, int col, char color) {
        int colorCount = 0;
        Boolean colorPresence;
        String boardAsString;
        boardAsString = board[row][col].toString();
            for (int eachTokenInCell = 0; eachTokenInCell < boardAsString.length(); eachTokenInCell++) {
                if (boardAsString.charAt(eachTokenInCell) == color) {
                    colorCount++;
                }
            }
            if (colorCount>0){
                colorPresence = true;
            } else {
                colorPresence = false;
            }
            
        return colorPresence; // DUMMY; REPLACE
    }

    /**
     * Check whether the top most token in the cell indicated by row and col is 
     * of the given color.
     * 
     * @param row row number on board
     * @param col column number on board
     * @param player
     * @return boolean indicating whether top token in a cell is of the color provided.
     */
    public boolean colorOnTop(int row, int col, char color) {
        String topToken;
        topToken = board[row][col].getTopTokenAsString();
        boolean isGivenColor;
        isGivenColor = false;
        if (topToken.equals("" + color)){
            isGivenColor = true;
        } else if (!topToken.equals("" + color)){
            isGivenColor = false;
        }
        return isGivenColor; // DUMMY; REPLACE
    }

    /**
     * Return the number of tokens in the stack in the cell indicated by row and col.
     * @param row row number on board
     * @param col column number on board
     * @return integer indicating number of tokens in cell
     */
    public int stackHeight(int row, int col) {
        int tokenCount = 0;
        String boardAsString;
        boardAsString = board[row][col].toString();
            for (int eachTokenInCell = 0; eachTokenInCell < boardAsString.length(); eachTokenInCell++) {
                tokenCount++;
                }
            if (board[row][col].isTrap() == true){
                tokenCount = tokenCount - 1;
            }
        return tokenCount; 
    }

    /**
     * Check whether the cell indicated by row and col is empty.
     * @param row row number on board
     * @param col column number on board
     * @return
     */
    public boolean isEmpty(int row, int col) {
        boolean isEmpty;
        isEmpty = true;
        String boardAsString = board[row][col].toString();
        if (boardAsString.length() == 0){
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        return isEmpty; // DUMMY; REPLACE 
    }

    /**
     * Check whether the cell indicated by row and col is an active trap.
     * @param row row number on board
     * @param col column number on board
     * @return
     */
    public boolean isActiveTrap(int row, int col) {
        if (board[row][col].isTrap() && tokensBehind(col))
        {
            return true;
        }
        return false;
    }    

    /** 
     * Checks if a certain has column has tokens behind itself. 
     * @param column column number on board
     * @return boolean indicating whether there are tokens behind a certain column. 
     */
    public boolean tokensBehind(int column){
        for (int eachRow = 0; eachRow < ROWS; eachRow++){
            for (int eachColumn = 0; eachColumn < column; eachColumn++){
                if (!isEmpty(eachRow, eachColumn)){
                    return true; 
                }
            }
        }
        return false;
    }

    /**
     * Deactivat the trap at the cell indicated by row and col (if that cell is a trap).
     * @param row row number on board of which you want to deactivate trap
     * @param col column number on board of which you want to deactivate trap
     * @return
     */
    public void deactivateTrap(int row, int col) {
        if (board[row][col].isTrap() == true) {
            board[row][col].toggleTrap();
        }
    }
}
