/**
 * BoardTester Class: Tests every method of the board class with different possible outcomes ensuring its accuracy. 
 * @author Advitya Singh
 * I affirm that I have carried out my academic endeavors
with full academic honesty. [Advitya Singh] 
 */

import igel.Board;

public class BoardTester {
        private static void testConstructor() {
        Board board = new Board();
        String expected = "|||@|||||//||||||@||//||||@||||//|||||@|||//||@||||||//|||||||@|";
        String actual = board.toString();
        Testing.assertEquals("Constructing a new empty board.", expected, actual);
    }

    private static void testPlaceToken() {
        Board board = new Board();
        board.placeToken(0,0,'R');
        String expected = "R|||@|||||//||||||@||//||||@||||//|||||@|||//||@||||||//|||||||@|";
        String actual = board.toString();
        Testing.assertEquals("Testing PlaceToken by adding an R token at row 1, col 1", expected, actual);
    }

    private static void testMoveSidewaysDown() {
        Board board = new Board();
        board.placeToken(0,0,'R');
        board.moveSideways(0,0,1);
        String expected = "|||@|||||//R||||||@||//||||@||||//|||||@|||//||@||||||//|||||||@|";
        String actual = board.toString();
        Testing.assertEquals("Testing moveSideways (moving down) by moving R from row 1, col 1 to row 2, col 1", expected, actual);
    }

    private static void testMoveSidewaysUp() {
        Board board = new Board();
        board.placeToken(1,0,'R');
        board.moveSideways(1,0,-1);
        String expected = "R|||@|||||//||||||@||//||||@||||//|||||@|||//||@||||||//|||||||@|";
        String actual = board.toString();
        Testing.assertEquals("Testing moveSideways (moving up) by moving R from row 2, col 1 to row 1, col 1", expected, actual);
    }

    private static void testMoveSidewaysUp2() {
        Board board = new Board();
        board.placeToken(1,0,'R');
        board.placeToken(1,0,'R');
        board.placeToken(1,0,'B');
        board.moveSideways(1,0,-1);
        String expected = "B|||@|||||//RR||||||@||//||||@||||//|||||@|||//||@||||||//|||||||@|";
        String actual = board.toString();
        Testing.assertEquals("Testing moveSideways (moving up) by moving R from row 2, col 1 to row 1, col 1", expected, actual);
    }


    private static void testMoveForward() {
        Board board = new Board();
        board.placeToken(0,0,'R');
        board.moveForward(0,0);
        String expected = "|R||@|||||//||||||@||//||||@||||//|||||@|||//||@||||||//|||||||@|";
        String actual = board.toString();
        Testing.assertEquals("Testing moveForward by moving R from row 1, col 1 to row 1, col 2", expected, actual);
    }

    private static void testCountTokensInColumn() {
        Board board = new Board();
        board.placeToken(0,0,'R');
        board.placeToken(0,0,'R');
        board.placeToken(0,0,'R');
        board.placeToken(1,0,'R');
        board.placeToken(3,0,'R');
        board.placeToken(5,0,'R');
        board.placeToken(5,0,'R');
        System.out.println(board.toString());
        int expected = 7;
        int actual = board.countTokensInColumn(0,'R');
        Testing.assertEquals("Testing CountTokensInColumn() by counting Rs in column 1", expected, actual);
    }

    private static void testContainsColor() {
        Board board = new Board();
        board.placeToken(0,0,'R');
        Boolean expected = true;
        Boolean actual = board.containsColor(0,0,'R');
        Testing.assertEquals("Testing ContainsColor() for row 1, col 1", expected, actual);
    }

    private static void testContainsColor2() {
        Board board = new Board();
        Boolean expected = false;
        Boolean actual = board.containsColor(0,0,'R');
        Testing.assertEquals("Testing ContainsColor() for row 1, col 1", expected, actual);
    }

    private static void testColorOnTop() {
        Board board = new Board();
        board.placeToken(0,0,'R');
        board.placeToken(0,0,'G');
        board.placeToken(0,0,'B');
        Boolean expected = true;
        Boolean actual = board.colorOnTop(0,0,'B');
        Testing.assertEquals("Testing ColorOnTop() where top token is B as expected", expected, actual);
    }

    private static void testColorOnTop2() {
        Board board = new Board();
        board.placeToken(0,0,'R');
        board.placeToken(0,0,'G');
        board.placeToken(0,0,'B');
        Boolean expected = false;
        Boolean actual = board.colorOnTop(0,0,'R');
        Testing.assertEquals("Testing ColorOnTop() where top token is R but expected is B", expected, actual);
    }

    private static void testStackHeight() {
        Board board = new Board();
        board.placeToken(0,3,'R');
        board.placeToken(0,3,'G');
        board.placeToken(0,3,'B');
        int expected = 3;
        int actual = board.stackHeight(0,3);
        Testing.assertEquals("Testing StackHeight() for a column with 3 tokens", expected, actual);
    }

    private static void testStackHeight2() {
        Board board = new Board();
        int expected = 0;
        int actual = board.stackHeight(0,0);
        Testing.assertEquals("Testing StackHeight() for a column with 0 tokens", expected, actual);
    }

    private static void testIsEmpty() {
        Board board = new Board();
        boolean expected = true;
        boolean actual = board.isEmpty(0,0);
        Testing.assertEquals("Testing isEmpty() for an empty column", expected, actual);
    }

    private static void testIsEmpty2() {
        Board board = new Board();
        board.placeToken(0,0,'R');
        boolean expected = false;
        boolean actual = board.isEmpty(0,0);
        Testing.assertEquals("Testing isEmpty() for a non-empty column", expected, actual);
    }

    private static void testIsActiveTrapForActiveTrapCell() {
        Board board = new Board();
        boolean expected = true;
        board.placeToken(1,2,'R');
        board.placeToken(1,2,'B');
        System.out.println(board);
        boolean actual = board.isActiveTrap(1,6);
        Testing.assertEquals("Testing if IsActiveTrap returns false for Active trap cell.", expected, actual);
    }

    private static void testIsActiveTrapForNonActiveTrapCell() {
        Board board = new Board();
        boolean expected = true;
        boolean actual = board.isActiveTrap(0,3);
        Testing.assertEquals("Testing if IsActiveTrap returns false for Non-active trap cell.", expected, actual);
    }

    private static void testIsActiveTrapNonTrapCell() {
        Board board = new Board();
        boolean expected = false;
        boolean actual = board.isActiveTrap(0,0);
        Testing.assertEquals("Testing if IsActiveTrap returns false for non-trap cell.", expected, actual);
    }

    private static void testDeactivateTrapForTrapCell() {
        Board board = new Board();
        boolean expected = false;
        board.deactivateTrap(0,3);
        boolean actual = board.isActiveTrap(0,3);
        Testing.assertEquals("Testing deactivateTrap for a trap cell.", expected, actual);
    }

    private static void testDeactivateTrapForNonTrapCell() {
        Board board = new Board();
        boolean expected = false;
        board.deactivateTrap(0,0);
        boolean actual = board.isActiveTrap(0,0);
        Testing.assertEquals("Testing deactivateTrap for a non-trap cell.", expected, actual);
    }

    public static void main(String[] args) {
        Testing.setVerbose(true);
        Testing.startTests();
        Testing.testSection("Board class");
        testConstructor();
        testPlaceToken();
        testMoveSidewaysDown();
        testMoveSidewaysUp();
        testMoveSidewaysUp2();
        testMoveForward();
        testCountTokensInColumn();
        testContainsColor();
        testContainsColor2();
        testColorOnTop();
        testColorOnTop2();
        testStackHeight();
        testStackHeight2();
        testIsEmpty();
        testIsEmpty2();
        testIsActiveTrapForActiveTrapCell();
        testIsActiveTrapForNonActiveTrapCell();
        testIsActiveTrapNonTrapCell();
        testDeactivateTrapForTrapCell();
        testDeactivateTrapForNonTrapCell();
        Testing.finishTests();
    }
}

