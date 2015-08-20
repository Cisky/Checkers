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
    public CheckersModel(){
        board = new Tile[Utils.numberOfCells][Utils.numberOfCells];
        for(int row = 0; row < Utils.numberOfCells; ++row)
            for(int column = 0; column < Utils.numberOfCells; ++column)
                board[row][column] = new Tile();

        pieces = new ArrayList();
        for(int index = 0; index < Utils.numberOfCells * Utils.numberOfLines; ++index)
            pieces.add(new Piece());
    }
      
    //PUBLIC MEMBER FUNCTION
    @Override
    public void start(){
        
        for(int column = 0; column < Utils.numberOfCells; ++column)
            for(int row = 0; row < Utils.numberOfCells; ++row)
                board[row][column].free();
        
        Piece tmp;
        int k = 0;
        for(int i = 0; i < Utils.numberOfLines; ++i){
            for(int j = i % 2; j < Utils.numberOfCells; j += 2, ++k){
                tmp = pieces.get(k);
                tmp.setColor(PieceColor.BLACK);
                tmp.setX(Utils.numberOfCells - 1 - i);
                tmp.setY(j);
                board[j][Utils.numberOfCells - 1 - i].occupy(tmp);
                pieces.add(tmp);
            }
        }
        
        for(int i = 0; i < Utils.numberOfLines; ++i){
            for(int j = (i+1) % 2; j < Utils.numberOfCells; j += 2, ++k){
                tmp = pieces.get(k);
                tmp.setColor(PieceColor.WHITE);
                tmp.setX(i);
                tmp.setY(j);
                board[j][i].occupy(tmp);
                pieces.add(tmp);
            }
        }
    }
    
    @Override
    public void movePiece(int x, int y, int finalX, int finalY){
        Piece tmp = board[x][y].getPiece();
        board[x][y].free();
        tmp.setX(finalX);
        tmp.setY(finalY);
        board[finalX][finalY].occupy(tmp);

    }
    
    @Override
    public void capture(int x, int y){
        board[x][y].free();
    }
    
    @Override
    public Piece pieceAt(int x, int y){
        return board[x][y].getPiece();
    }
    
    @Override
    public boolean isOccupied(int x, int y){
        return board[x][y].isOccupied();
    }
    
    //INNER CLASS
    private class Tile{
        //CONSTRUCTOR
        public Tile(){
            isOccupied = false;
            piece = null;

        }
        
        //PUBLIC MEMBER FUNCTION
        public void occupy(Piece Pawn){
            isOccupied = true;
            piece=Pawn;
        }
        
        public void free(){
            isOccupied = false;
            piece = null;
        }
        
        public Piece getPiece(){
            return piece;
        }
        
        public boolean isOccupied(){
            return isOccupied;
        }
        
        //MEMBER
        private boolean isOccupied;
        private Piece piece;
    }
        
    //MEMBER
    final private List<Piece> pieces;
    final private Tile[][] board;
}