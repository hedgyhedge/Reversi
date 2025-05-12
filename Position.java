/*
 * Author: Mary Han
 * Project: Reversi (Part 1)
 * Topics Covered: arrays, loops, private variables, constructors, methods
 * This project implements a Position class for the Reversi game.
 * The purpose is to gain experience with java and how to program
 * effectively with many different methods. 
 */

public class Position implements Comparable<Position>{
    public enum Color{
        BLACK, WHITE, EMPTY
    }

    private int row; 
    private int col; 
    private Color piece; 

    
    /*
     * Method toString
     * Returns: String 
     * Functionality: Returns a String representation of a Position object
     */
    public String toString() {
        String rep = "";
        String letters = "abcdefgh"; 
        String digits = "12345678"; 
        rep += letters.substring(this.col, this.col + 1);
        rep += digits.substring(this.row, this.row + 1);
        if (this.piece == Color.BLACK) {
            rep += "B";
        }
        if (this.piece == Color.WHITE) {
            rep += "W";
        }
        return rep;
    }

    
    /*
     * Method equals
     * Parameters: Object o
     * Returns: boolean
     * Functionality: Checks if the given object is equal to this Position object
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof Position)) {
            return false;
        }
        Position pos = (Position) o;
        return pos.row == this.row && pos.col == this.col && pos.piece.equals(this.piece);
    }

    
    /*
     * Method compareTo
     * Parameters: Position o
     * Returns: int
     * Functionality: Compares this position to another based on row and column 
     */
    public int compareTo(Position o) {
        if (this.row != o.row) {
            return this.row - o.row;
        }
        return this.col - o.col;
    }

    
    /*
     * Method getRowAndColFromStringPosition
     * Parameters: String pos
     * Returns: int[]
     * Functionality: Returns the row and column indices for a given position string
     */
    public static int[] getRowAndColFromStringPosition(String pos) {
        pos = pos.toLowerCase();
        int[] place = new int[2];
        place[0] = -1;  
        place[1] = -1;
        
        String letters = "abcdefgh";   
        String digits = "12345678";   

        for (int i = 0; i < letters.length(); i++) {
            if (pos.substring(0, 1).equals(letters.substring(i, i + 1))) {
                place[1] = i;
            }
        }
        for (int j = 0; j < digits.length(); j++) {
            if (pos.substring(1, 2).equals(digits.substring(j, j + 1))) {
                place[0] = j;
            }
        }

        if (place[0] == -1 || place[1] == -1) {
            place[0] = -1;
            place[1] = -1;
        }

        return place;
    }

    
    /*
     * Constructor Position
     * Parameters: String pos, Color c
     * Functionality: Initializes the Position object with row, column, and piece color
     */
    public Position(String pos, Color c) {
        int[] indices = getRowAndColFromStringPosition(pos);
        this.row = indices[0];
        this.col = indices[1];
        this.piece = c;
    }

    
    /*
     * Constructor Position
     * Parameters: int row, int col, Color c
     * Functionality: Initializes the Position object with specified row, column, and color
     */
    public Position(int row, int col, Color c) {
        this.row = row;
        this.col = col;
        this.piece = c;
    }

    
    /*
     * Getter for row
     * Returns: int
     * Functionality: Retrieves the row index of the position
     */
    public int getRow() {
        return row;
    }

    
    /*
     * Setter for row
     * Parameters: int row
     * Functionality: Sets the row index of the position
     */
    public void setRow(int row) {
        this.row = row;
    }

    
    /*
     * Getter for column
     * Returns: int
     * Functionality: Retrieves the column index of the position
     */
    public int getColumn() {
        return col;
    }

    
    /*
     * Setter for column
     * Parameters: int col
     * Functionality: Sets the column index of the position
     */
    public void setCol(int col) {
        this.col = col;
    }

    
    /*
     * Getter for piece
     * Returns: Color
     * Functionality: Retrieves the color of the piece at this position
     */
    public Color getPiece() {
        return piece;
    }

    
    /*
     * Setter for piece
     * Parameters: Color piece
     * Functionality: Sets the color of the piece at this position
     */
    public void setPiece(Color piece) {
        this.piece = piece;
    }
}
