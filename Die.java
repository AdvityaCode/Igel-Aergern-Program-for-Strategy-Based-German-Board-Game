package igel;

import java.util.Random;

/**
 * Models a die.
 * @author Advitya Singh
 * I affirm that I have carried out my academic endeavors
with full academic honesty. [Advitya Singh] 
 */

public class Die {

    public int sides;
    private int current_val;

    /**
     * Create a die from given number but takes no parameter
     *  
     */
    public Die(){
        this.sides = 6;
        this.current_val = 1;
    }

    /**
     * Create a die from given number
     * 
     * @param sides any integer 
     */
    public Die(int sides){
        this.sides = sides;
        this.current_val = 1;
    }
    /*
     * roll die and return random number from 1 to sides
     */
    public void roll(){
        Random rand = new Random();
        this.current_val = rand.nextInt(1,this.sides+1);
    }
    /*
     * returns the value of the die rolled
     * 
     * @return current_val of the die 
     */
    public int getValue(){
        return this.current_val;
    }

}
