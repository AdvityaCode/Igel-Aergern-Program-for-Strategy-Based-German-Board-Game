/**
 * CellTester Class: Tests every method of the cell class with different possible outcomes ensuring its accuracy. 
 * @author Advitya Singh
 * I affirm that I have carried out my academic endeavors
with full academic honesty. [Advitya Singh] 
 */

import igel.Cell;

public class CellTester {

    private static void testConstructor() {
        Cell cell = new Cell();
        String expected = "";
        String actual = cell.toString();
        Testing.assertEquals("Constructing a new empty cell.", expected, actual);
    }

    private static void testTrap() {
        Cell cell = new Cell();
        cell.toggleTrap();
        String expected = "@";
        String actual = cell.toString();
        Testing.assertEquals("Making a newly created cell a trap.", expected, actual);
    }

    private static void testisTrapnoTrap() {
        Cell cell = new Cell();
        cell.isTrap();
        Boolean expected = false;
        Boolean actual = cell.isTrap();
        Testing.assertEquals("Checking if a non-trap cell returns false for isTrap", expected, actual);
    }

    private static void testisTrap() {
        Cell cell = new Cell();
        cell.toggleTrap();
        cell.isTrap();
        Boolean expected = true;
        Boolean actual = cell.isTrap();
        Testing.assertEquals("Checking if a trap cell returns true for isTrap.", expected, actual);
    }

    private static void testtoString() {
        Cell cell = new Cell();
        String expected = "";
        String actual = cell.toString();
        Testing.assertEquals("Checking if toString returns correct representation of cell.", expected, actual);
    }

    private static void testtoStringTrap() {
        Cell cell = new Cell();
        cell.toggleTrap();
        String expected = "@";
        String actual = cell.toString();
        Testing.assertEquals("Checking if toString returns correct representation of cell.", expected, actual);
    }

    public static void main(String[] args) {
        Testing.setVerbose(true);
        Testing.startTests();
        Testing.testSection("Cell class");
        testConstructor();
        testTrap();
        testisTrap();
        testisTrapnoTrap();
        testtoString();
        testtoStringTrap();
        Testing.finishTests();
    }
}



