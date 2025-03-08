/**
 * IgleAergern gameplay class: Contains the main gameplay 
 * including setupPhase and mainPhase and carry the game forward. 
 * @author Advitya Singh
 * I affirm that I have carried out my academic endeavors
with full academic honesty. [Advitya Singh] 
 */

package igel;

import java.util.ArrayList;

public class IgelAergern {

    private Board board;
    private IgelView view;
    private ArrayList<Player> players;
    private Player winner;

    private final int MIN_PLAYERS = 3;
    private final int MAX_PLAYERS = 6;
    private final int PLAYER_TOKENS = 4;
    private final int DIE_TYPE = 6;

    public IgelAergern() {
        this.board = new Board(); // DO NOT MODIFY THIS LINE
        this.view = new IgelView(); // DO NOT MODIFY THIS LINE
        this.players = new ArrayList<Player>(); // DO NOT MODIFY THIS LINE
        this.winner = null; // DO NOT MODIFY THIS LINE
        // You may add additional code here, if you want to.
    }

    /**
     * Play one game of Igel Aergern.
     * 
     * DO NOT MODIFY THIS METHOD.
     */
    public void play() {
        introduction();
        setupPhase();
        mainPhase();
        end();
    }
    
    private void introduction() {
        view.refresh(null); // DO NOT MODIFY THIS LINE
        createPlayers(); // DO NOT MODIFY THIS LINE
        // You may add additional code here, if you want to.
    }

    // DO NOT MODIFY THIS METHOD (but do add javadocs)
    /*
     * Places 4 tokens for each player in the start column. 
     */
    private void setupPhase() {
        view.nextChapter();
        view.refresh(board.toString());
        int currentPlayer = 0;
        for (int tokensPlaced = 0; tokensPlaced < players.size() * PLAYER_TOKENS; tokensPlaced++) {
            setupTurn(currentPlayer);
            view.refresh(board.toString());
            currentPlayer = nextPlayer(currentPlayer);
        }
    }

    private void sidewaysMoveForMainPhase(){
        // @return Moves token sideways, if the user wishes to. 
        String requestMessage = "Do you want to slide a token sideways? If yes, enter row number from which you want to slide token. Else, enter 0";
        String errorMessage = "Please input a number between " + 1 + " and " + 6 + ".";
        
        // Request input (repeatedly until the player provides a valid answer)
        String sidewaysWhichRow = view.requestInput(requestMessage);
        while (! isDigit(sidewaysWhichRow) || ! isInRange(Integer.decode(sidewaysWhichRow), 0, 6)) {
            sidewaysWhichRow = view.requestInput(errorMessage);
        }

        // If the user enters a row number, ask for which column. 
        if (Integer.decode(sidewaysWhichRow)>0){
            String columnRequest = "What column do you want to slide top token from?";
            String sidewaysWhichColumn = view.requestInput(columnRequest);
            String columnError = "Invalid column. Try again";
            while (! isDigit(sidewaysWhichColumn) || ! isInRange(Integer.decode(sidewaysWhichColumn), 1, 9)) {
                sidewaysWhichColumn = view.requestInput(columnError);
            }
            // Asks the user if they want to slide token up or down. 
            String upOrDown = "Do you want to move this token up or down? Enter U for up and D for down.";
            String errorString = "You must either input a U or D!";
            String direction = view.requestInput(upOrDown);
            while (!direction.equals("D") && !direction.equals("U")) {
                sidewaysWhichRow = view.requestInput(errorString);
            }

            // checks if the user is trying to move a token sideways from a active trap.
            while (board.isActiveTrap(Integer.decode(sidewaysWhichRow)-1, Integer.decode(sidewaysWhichColumn)-1) && board.tokensBehind(Integer.decode(sidewaysWhichColumn))){
                sidewaysWhichColumn = view.requestInput("Choose a different column! This column in a trap!");
            }    

            // moves token upwards or downward based on user input.
            if (direction.equals("U")) {
                board.moveSideways(Integer.decode(sidewaysWhichRow)-1, Integer.decode(sidewaysWhichColumn)-1, -1);
                view.refresh(board.toString());
            } else if (direction.equals("D")) {
                board.moveSideways(Integer.decode(sidewaysWhichRow)-1, Integer.decode(sidewaysWhichColumn)-1, 1);
                view.refresh(board.toString());
            }
            }
        }
    
    
    private int forwardWhichColumn(int row){
        // @return column number that the user wants to move top token forward from. 
        // Prepare messages
        String requestMessage = "Which column do you want to move the top token from?";
        String errorMessage = "Please input a number between " + MIN_PLAYERS + " and " + MAX_PLAYERS + ".";
        String errorMessage2 = "This is an active trap cell! Chose another column to move.";
        
        // Request input (repeatedly until the player provides a valid answer)
        String forwardWhichColumn = view.requestInput(requestMessage);
        while (! isDigit(forwardWhichColumn) || ! isInRange(Integer.decode(forwardWhichColumn), 0, 8)) {
            forwardWhichColumn = view.requestInput(errorMessage);
        }

        // checks if the user is trying to move a token forward from a active trap.
        while (board.isActiveTrap(row, Integer.decode(forwardWhichColumn)-1) && board.tokensBehind(Integer.decode(forwardWhichColumn))){
            forwardWhichColumn = view.requestInput(errorMessage2);
        }    
        return Integer.decode(forwardWhichColumn);
    }

    private void mainPhase() {
        view.nextChapter();  // DO NOT MODIFY THIS LINE
        view.refresh(board.toString());  // DO NOT MODIFY THIS LINE
        int currentIndex = 0;
        Die die = new Die(DIE_TYPE);
        while (checkWinner()== false) {
            view.inform("Player " + players.get(currentIndex).getColor() + ", your turn.");
            die.roll();
            int dieResult = die.getValue();
            view.inform("The die rolled " + dieResult);
            sidewaysMoveForMainPhase();
            int forwardWhichColumn = forwardWhichColumn(dieResult-1);
            board.moveForward(dieResult-1, forwardWhichColumn-1);
            view.refresh(board.toString());
            if (currentIndex >= players.size()-1){
                currentIndex = 0;
            }
            else{
                currentIndex++;
            }
        }
        winner = players.get(currentIndex);

    }

    private boolean checkWinner() {
        int YCounter = board.countTokensInColumn(8,'Y');
        int RCounter = board.countTokensInColumn(8,'R');
        int GCounter = board.countTokensInColumn(8,'G');
        int PCounter = board.countTokensInColumn(8,'P');
        int BCounter = board.countTokensInColumn(8,'B');
        int OCounter = board.countTokensInColumn(8,'O');
        return (YCounter == 3 || RCounter == 3 || GCounter == 3 || PCounter == 3 || BCounter == 3 || OCounter == 3);

    }

    private void end() {
        view.nextChapter(); // DO NOT MODIFY THIS LINE
        view.refresh(board.toString(), winner.toString()); // DO NOT MODIFY THIS LINE
        // You may add additional code here, if you want to.
    }

    private void createPlayers() {
        int numPlayers = getPlayerNumber();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(Player.COLORS[i]));
        }
    }

    private int nextPlayer(int current) {
        return (current + 1) % players.size();
    }

    private void setupTurn(int currentPlayer) {
        view.inform("Player " + players.get(currentPlayer).getColor() + ", your turn.");
        int row = getSetupMove(currentPlayer);
        board.placeToken(row, 0, players.get(currentPlayer).getColor());
    }

    private int getPlayerNumber() {
        // Prepare messages
        String requestMessage = "How many players? (" + MIN_PLAYERS + "-" + MAX_PLAYERS + ")";
        String errorMessage = "Please input a number between " + MIN_PLAYERS + " and " + MAX_PLAYERS + ".";
        // Request input (repeatedly until the player provides a valid answer)
        String numPlayersInput = view.requestInput(requestMessage);
        while (! isDigit(numPlayersInput) || ! isInRange(Integer.decode(numPlayersInput), MIN_PLAYERS, MAX_PLAYERS)) {
            numPlayersInput = view.requestInput(errorMessage);
        }
        return Integer.decode(numPlayersInput);
    }

    private int getSetupMove(int currentPlayer) {
        // Prepare messages
        String requestMessage = "In which row do you want to place your token? (1-6)  ";
        ArrayList<Integer> options = collectSetupOptions(currentPlayer);
        String optionsString = options.toString();
        String errorMessage = "Please input a valid row number: " + optionsString;
        // Request input (repeatedly until the player provides a valid answer)
        String rowInput = view.requestInput(requestMessage);
        while (! isDigit(rowInput) || ! options.contains(Integer.decode(rowInput))) {
            rowInput = view.requestInput(errorMessage);
        }
        return Integer.decode(rowInput) - 1;
    }

    private ArrayList<Integer> collectSetupOptions(int currentPlayer) {
        // Collect rows with lowest stacks (-> options) while also keeping track of
        // whether one of the lowest stacks does NOT contain the current player's color
        ArrayList<Integer> options = new ArrayList<>();
        ArrayList<Integer> allRows = new ArrayList<>();
        boolean nonBlockingOptionExists = false;
        int minStackHeight = board.stackHeight(0, 0);
        for (int r = 0; r < Board.ROWS; r++) {
            int rStackHeight = board.stackHeight(r, 0);
            if (rStackHeight < minStackHeight) {
                options.clear();
		nonBlockingOptionExists = false;
                minStackHeight = rStackHeight;
            }
            if (rStackHeight == minStackHeight) {
                options.add(r+1);
                if (! board.containsColor(r, 0, players.get(currentPlayer).getColor())) {
                    nonBlockingOptionExists = true;
                }
            }
            allRows.add(r+1);
        }
        if (nonBlockingOptionExists) {
            return options;
        }
        else {
            return allRows;
        }
    }

    private boolean isDigit(String str) {
        return str.matches("\\d");
    }

    private boolean isInRange(int numToTest, int start, int end) {
        return numToTest >= start && numToTest <= end;
    }
}
