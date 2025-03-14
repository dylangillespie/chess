
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
      ArrayList<Square> controlledSquares = new ArrayList<>();

      int startRow = start.getRow();
      int startCol = start.getCol();
      
      int[] rowDirections = {-1, -1, 1, 1};
      int[] colDirections = {-1, 1, -1, 1};
      
      for (int direction = 0; direction < 4; direction++) {
          int row = startRow;
          int col = startCol;
          
          while (true) {
              row += rowDirections[direction];
              col += colDirections[direction];
              
              if (row < 0 || row >= 8 || col < 0 || col >= 8) break;
              
              Square square = board[row][col];
              
              if (square.isOccupied()) {
                  controlledSquares.add(square);
                  if (square.getOccupyingPiece().getColor() != this.getColor()) {
                      break;
                  }
              } else {
                  controlledSquares.add(square);
              }
          }
      }
      return controlledSquares;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      ArrayList<Square> legalMoves = new ArrayList<>();
      int startRow = start.getRow();
      int startCol = start.getCol();
      
      //Up-Left Movement
      for (int i = 1; startRow - i >= 0 && startCol - i >= 0; i++) {
          Square sq = b.getSquareArray()[startRow - i][startCol - i];
          if (sq.isOccupied()) {
              if (sq.getOccupyingPiece().getColor() != this.getColor()) {
                  legalMoves.add(sq);
              }
              break;
          }
          legalMoves.add(sq);
      }
      
      //Up-Right Movement
      for (int i = 1; startRow - i >= 0 && startCol + i < 8; i++) {
          Square sq = b.getSquareArray()[startRow - i][startCol + i];
          if (sq.isOccupied()) {
              if (sq.getOccupyingPiece().getColor() != this.getColor()) {
                  legalMoves.add(sq);
              }
              break;
          }
          legalMoves.add(sq);
      }
      
      //Down-Left Movement
      for (int i = 1; startRow + i < 8 && startCol - i >= 0; i++) {
          Square sq = b.getSquareArray()[startRow + i][startCol - i];
          if (sq.isOccupied()) {
              if (sq.getOccupyingPiece().getColor() != this.getColor()) {
                  legalMoves.add(sq);
              }
              break;
          }
          legalMoves.add(sq);
      }
      
      //Down-Right Movement
      for (int i = 1; startRow + i < 8 && startCol + i < 8; i++) {
          Square sq = b.getSquareArray()[startRow + i][startCol + i];
          if (sq.isOccupied()) {
              if (sq.getOccupyingPiece().getColor() != this.getColor()) {
                  legalMoves.add(sq);
              }
              break;
          }
          legalMoves.add(sq);
      }
      return legalMoves;
    }
}