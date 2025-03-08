/**
 * Player class: Each player as Player + their color. 
 * @author Advitya Singh
 * I affirm that I have carried out my academic endeavors
with full academic honesty. [Advitya Singh] 
 */

package igel;

public class Player {
    public static final char[] COLORS = {'Y', 'R', 'G', 'P', 'B', 'O'};
    private char color;

    /**
     * Create a player. The player's tokens are of the given color. The color 
     * has to be one of Player.COLORS.
     * 
     * @param color
     */
    public Player(char color) {
        this.color = color;
        String playerName;
        playerName = "Player" + color;
    }

    public char getColor() {
        return color;
    }
}
