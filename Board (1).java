/*
* Author(s): Vikash Sahu & Mary Han
* Project: Reversi
* Topics Covered: Methods, arrays, arraylist, conditionals, classes & objects, copy constructor, loops
* In this project a 8x8 array of position objects is used to simulate a simplified version of Reversi.
* The purpose of this project is to have a deeper understanding of 2d arrays & how to use objects.
*/
import java.util.ArrayList;
public class Board { //TODO: rename to Board
	private Position.Color currentPlayer;
	private Position[][] board;
	
	/*
	 * Method: Board
	 * Parameters: none
	* Returns: none
	* Functionality: creates 8x8 board of position objects, initializes each cell to empty, sets initial 4 pieces, starts w/ black
	*/
	
	public Board() {
		
      board = new Position[8][8];
      currentPlayer = Position.Color.BLACK;
      for (int i = 0; i < 8; i++) {
          for (int j = 0; j < 8; j++) {
              board[i][j] = new Position(i, j, Position.Color.EMPTY);
          }
      }
      board[3][3].setPiece(Position.Color.WHITE);
      board[4][4].setPiece(Position.Color.WHITE);
      board[3][4].setPiece(Position.Color.BLACK);
      board[4][3].setPiece(Position.Color.BLACK);
	}
	/*
	* Method: getCurrentPlayer
	* Parameters: none
	* Returns: Position.Color
	* Functionality: returns the color of the current player
	*/	
	public Position.Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	/*
	* Method: getBoard
	* Parameters: none
	* Returns: Position[][]
	* Functionality: returns the board
	*/
	public Position[][] getBoard() {
		return board;
	}
	
	/*
	* Method: countColorPieces
	* Parameters: Position.Color c
	* Returns: int
	* Functionality: loops through the board to count how many positions have a piece of the given color
	*/
	public int countColorPieces(Position.Color c) {
      int count = 0;
      for (Position[] row : board) {
          for (Position p : row) {
              if (p.getPiece() == c) {
                  count++;
              }
          }
      }
      return count;
	}
	
	/*
	* Method: determineWinner
	* Parameters: none
	* Returns: Position.Color
	* Functionality: compares count of black & white pieces on the board and returns who wins
	*/
	public Position.Color determineWinner() {
      int blackCount = countColorPieces(Position.Color.BLACK);
      int whiteCount = countColorPieces(Position.Color.WHITE);
    
      if (blackCount > whiteCount) {
      	return Position.Color.BLACK;
      }
    
      if (whiteCount > blackCount) {
      	return Position.Color.WHITE;
      }
    
      return Position.Color.EMPTY;
	}
	
	/*
	* Method: piecesHaveSameColor
	* Parameters: ArrayList<Position> pieces
	* Returns: boolean
	* Functionality: checks if all the positions in the provided list have the same color
	*/
	public static boolean piecesHaveSameColor(ArrayList<Position> pieces) {
	    Position.Color color = pieces.get(0).getPiece();
	   
	    for (Position p : pieces) {
	        if (p.getPiece() != color) {
	            return false;
	        }
	    }
	    return true;
	}
	
	/*
	* Method: toString
	* Parameters: none
	* Returns: String
	* Functionality: makes string representation of the board w/ labels
	*/
	public String toString() {
		String colHeader = "  a b c d e f g h\n";
		String s = "";
		s+= colHeader;
		for (int i=0; i<board.length; i++) {
			s += i+1 + " ";
			for (int j=0; j<board[i].length; j++) {
				if (board[i][j].getPiece()==Position.Color.WHITE) {
					s += "W ";
				} else if (board[i][j].getPiece()==Position.Color.BLACK) {
					s += "B ";
				} else {
					s += "  ";
				}
			}
			s += i + 1 + "\n";
		}
		s += colHeader;
		return s;
	}
	
	/*
	* Method: isValidMove
	* Parameters: Position p
	* Returns: boolean
	* Functionality: checks if the move is valid
	*/
	public boolean isValidMove(Position p) {
		
	  if (p.getPiece() != currentPlayer) {
	        return false;
	  }
	   
      if (p.getRow() < 0 || p.getRow() >= 8 || p.getColumn() < 0 || p.getColumn() >= 8) {
          return false;
      }
    
      if (board[p.getRow()][p.getColumn()].getPiece() != Position.Color.EMPTY) {
          return false;
      }
     
      ArrayList<Position> capturedHorizontal = checkHorizontalRight(p);
      ArrayList<Position> capturedVertical = checkVerticalDown(p);
      ArrayList<Position> capturedDiagonal = checkDiagonalUpLeft(p);
     
      if (capturedHorizontal.size() == 0 && capturedVertical.size() == 0 && capturedDiagonal.size() == 0) {
   	   return false;
      }
     
      return true;
	}
	
	/*
	* Method: checkHorizontalRight
	* Parameters: Position startPosition
	* Returns: ArrayList<Position>
	* Functionality: checks horizontal right to the placed piece to see if any opponent pieces can be captured
	* returns what would be flipped
	*/
	public ArrayList<Position> checkHorizontalRight(Position startPosition) {
		
      ArrayList<Position> captured = new ArrayList<>();
      int row = startPosition.getRow();
      int col = startPosition.getColumn();
      Position.Color opponentColor;
      if (currentPlayer == Position.Color.BLACK) {
          opponentColor = Position.Color.WHITE;
      }
      else {
          opponentColor = Position.Color.BLACK;
      }
      int j = col + 1;
      while (j < 8 && board[row][j].getPiece() == opponentColor) {
          captured.add(board[row][j]);
          j++;
      }
      if (j < 8 && board[row][j].getPiece() == currentPlayer) {
    	  java.util.Collections.sort(captured);
          return captured;
      }

      return new ArrayList<>();
	}
	
	/*
	* Method: checkVerticalDown
	* Parameters: Position startPosition
	* Returns: ArrayList<Position>
	* Functionality: checks vertical down to the placed piece to see if any opponent pieces can be captured
	* returns what would be flipped
	*/
	public ArrayList<Position> checkVerticalDown(Position startPosition) {
				
      ArrayList<Position> captured = new ArrayList<>();
      int row = startPosition.getRow();
      int col = startPosition.getColumn();
      Position.Color opponentColor;
      if (currentPlayer == Position.Color.BLACK) {
          opponentColor = Position.Color.WHITE;
      }
      else {
          opponentColor = Position.Color.BLACK;
      }
      int j = row + 1;
      while (j < 8 && board[j][col].getPiece() == opponentColor) {
          captured.add(board[j][col]);
          j++;
      }
      if (j < 8 && board[j][col].getPiece() == currentPlayer) {
    	  java.util.Collections.sort(captured);
          return captured;
      }
      return new ArrayList<>();
	}
	/*
	* Method: checkDiagonalUpLeft
	* Parameters: Position startPosition
	* Returns: ArrayList<Position>
	* Functionality: checks diagonal left to the placed piece to see if any opponent pieces can be captured,
	* returns what would be flipped
	*/
	public ArrayList<Position> checkDiagonalUpLeft(Position startPosition) {
	       ArrayList<Position> captured = new ArrayList<>();
	       int row = startPosition.getRow();
	       int col = startPosition.getColumn();
	       Position.Color opponentColor;
	       if (currentPlayer == Position.Color.BLACK) {
	           opponentColor = Position.Color.WHITE;
	       }
	       else {
	           opponentColor = Position.Color.BLACK;
	       }
	       int j = row - 1;
	       int k = col - 1;
	      
	       while (j >= 0 && k >= 0 && board[j][k].getPiece() == opponentColor) {
	           captured.add(board[j][k]);
	           j--;
	           k--;
	       }
	      
	       if (j >= 0 && k >= 0 && board[j][k].getPiece() == currentPlayer) {
	    	   java.util.Collections.sort(captured);
	           return captured;
	       }
	      
	       return new ArrayList<>();
	}
	
	/*
	* Method: play
	* Parameters: Position p
	* Returns: ArrayList<Object>
	* Functionality: attempts to place piece at position p if it's valid, collects captured opponent pieces & flips, returns
	* an arraylist of the captured positions
	*/
	public ArrayList<Object> play(Position p) {
		
	    ArrayList<Object> result = new ArrayList<>();
	  
	    if (p.getPiece() != currentPlayer) {
	        return new ArrayList<>();
	    }
	   
	    if (!isValidMove(p)) {
	        return new ArrayList<>();
	    }
	   
	    int row = p.getRow();
	    int col = p.getColumn();
	   
	    board[row][col].setPiece(currentPlayer);
	   
	    ArrayList<Position> capturedHorizontal = checkHorizontalRight(board[row][col]);
	    ArrayList<Position> capturedVertical = checkVerticalDown(board[row][col]);
	    ArrayList<Position> capturedDiagonal = checkDiagonalUpLeft(board[row][col]);
	   
	    ArrayList<Position> capturedAll = new ArrayList<>();
	    capturedAll.addAll(capturedHorizontal);
	    capturedAll.addAll(capturedVertical);
	    capturedAll.addAll(capturedDiagonal);
	   
	    //copy constructor
	    ArrayList<Position> capturedCopies = new ArrayList<>();
	    for (Position pos : capturedAll) {
	        capturedCopies.add(new Position(pos));
	    }
	   
	    java.util.Collections.sort(capturedCopies);
	   
	    capturePieces(capturedAll);
	    result.add(board[row][col]);
	    result.add(capturedCopies);
	    if (currentPlayer == Position.Color.BLACK) {
	        currentPlayer = Position.Color.WHITE;
	    } else {
	        currentPlayer = Position.Color.BLACK;
	    }
	   
	    return result;
	}
	
	/*
	* Method: capturePieces
	* Parameters: ArrayList<Position> piecesToCapture
	* Returns: none
	* Functionality: flips color of each position in piecesToCapture
	*/
	public void capturePieces(ArrayList<Position> piecesToCapture) {
		 for (Position pos : piecesToCapture) {
		        if (pos.getPiece() == Position.Color.BLACK) {
		            pos.setPiece(Position.Color.WHITE);
		        } else if (pos.getPiece() == Position.Color.WHITE) {
		            pos.setPiece(Position.Color.BLACK);
		        }
		    }
	}
	/*
	    public static void main(String[] args) {
		Board b = new Board();
		System.out.println(b);

		System.out.println(b.play(new Position("c4", Position.Color.BLACK)));
		System.out.println(b);
		
		System.out.println(b.play(new Position("c5", Position.Color.WHITE)));
		System.out.println(b);

		System.out.println(b.play(new Position("f6", Position.Color.BLACK)));
		System.out.println(b);
		
		System.out.println(b.play(new Position("c3", Position.Color.WHITE)));
		System.out.println(b);
		
		System.out.println(b.play(new Position("b4", Position.Color.BLACK)));
		System.out.println(b);
		
		System.out.println(b.play(new Position("g7", Position.Color.WHITE)));
		System.out.println(b);
		
		System.out.println(b.play(new Position("c2", Position.Color.BLACK)));
		System.out.println(b);
		
		System.out.println(b.play(new Position("c1", Position.Color.WHITE)));
		System.out.println(b);
	}
   */
}


