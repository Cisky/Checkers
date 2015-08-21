/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.model;

import it.mgd.checkers.Utils.Utils;
import it.mgd.checkers.model.Piece.PieceColor;
import java.util.ArrayList;
import java.util.List;

public class CheckersModel implements Model{
    
    //CONSTRUCTOR
    /** Create a checkerboard of tile and initialize the array of pieces*/
    public CheckersModel(){
        board = new Tile[Utils.numberOfTiles][Utils.numberOfTiles];
        for(int row = 0; row < Utils.numberOfTiles; ++row)
            for(int column = 0; column < Utils.numberOfTiles; ++column)
                board[row][column] = new Tile();

        pieces = new ArrayList();
        for(int index = 0; index < Utils.numberOfTiles * Utils.numberOfLines; ++index)
            pieces.add(new Piece());
    }
      
    //PUBLIC MEMBER FUNCTION
    /** Set the pieces on the checkerboard at the start position */
    @Override
    public void start(){
        
        for(int column = 0; column < Utils.numberOfTiles; ++column)
            for(int row = 0; row < Utils.numberOfTiles; ++row)
                board[row][column].free();
        
        Piece tmp;
        int k = 0;
        for(int i = 0; i < Utils.numberOfLines; ++i){
            for(int j = i % 2; j < Utils.numberOfTiles; j += 2, ++k){
                tmp = pieces.get(k);
                tmp.setColor(PieceColor.BLACK);
                tmp.setX(Utils.numberOfTiles - 1 - i);
                tmp.setY(j);
                board[j][Utils.numberOfTiles - 1 - i].occupy(tmp);
                pieces.add(tmp);
            }
        }
        
        for(int i = 0; i < Utils.numberOfLines; ++i){
            for(int j = (i+1) % 2; j < Utils.numberOfTiles; j += 2, ++k){
                tmp = pieces.get(k);
                tmp.setColor(PieceColor.WHITE);
                tmp.setX(i);
                tmp.setY(j);
                board[j][i].occupy(tmp);
                pieces.add(tmp);
            }
        }
    }
    
    /** Move the piece on the checkerboard from position (x, y) to position (finalX, finalY) */
    @Override
    public void movePiece(int x, int y, int finalX, int finalY){
        Piece tmp = board[x][y].getPiece();
        board[x][y].free();
        tmp.setX(finalX);
        tmp.setY(finalY);
        board[finalX][finalY].occupy(tmp);
    }
    
    /** Returns the piece on the checkerboard at position (x, y) */
    @Override
    public Piece pieceAt(int x, int y){
        return board[x][y].getPiece();
    }
    
    /** Capture the piece on the checkerboard at position (x, y) */
    @Override
    public void capture(int x, int y){
        board[x][y].free();
    }
 
    /** Return true if the tile on the checkerboard at position (x, y) is occupied, otherwise return false */
    @Override
    public boolean isOccupied(int x, int y){
        return board[x][y].isOccupied();
    }
    
    //INNER CLASS
    private class Tile{
        
        //CONSTRUCTOR
        /** Create a Tile Object that rappresent a tile on checkerboard*/
        public Tile(){
            isOccupied = false;
            piece = null;
        }
        
        //PUBLIC MEMBER FUNCTION
        /** Set Tile occupied and fills it with the piece specified in the parameters list*/
        public void occupy(Piece piece){
            isOccupied = true;
            this.piece = piece;
        }
        
        /** Free the Tile. Set Tile as free and removes piece on it */
        public void free(){
            isOccupied = false;
            piece = null;
        }

        /** Return piece on Tile*/
        public Piece getPiece(){
            return piece;
        }
        
        /** Returns true if the Tile is occupied, otherwise return false */
        public boolean isOccupied(){
            return isOccupied;
        }
        
        //MEMBER
        private boolean isOccupied;     /** Is occupied or free */
        private Piece piece;            /** Piece on Tile */
    }
        
    //MEMBER
    final private List<Piece> pieces;   /** List of the pieces on the checkerboard */
    final private Tile[][] board;       /** Matrix of Tile objects that represent the tiles of the checkerboard */
}