/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mgd.checkers.model;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Giuliano
 */
public class Checkerboard implements Model{
    
    private class Tile{
        private boolean isOccupied;
        private Piece piece;
        public Tile(){
            isOccupied = false;
            piece = null;
        }
        public void Occupy(Piece Pawn){
            isOccupied = true;
            piece=Pawn;
        }
        public void Free()
        {
            isOccupied = false;
            piece=null;
        }
        
        public Piece GetPiece(){
            return piece;
        }
        
        
        public boolean IsOccupied(){
            return isOccupied;
        }
    }
    
    
    public Checkerboard(){
        board = new Tile[dimension][dimension];
        //Num Pieces (Dimension/2)*3*2 
        //works on assumption that Dimension is even
        pieces = new ArrayList(dimension*3);
    }
    
    
    public ListIterator<Piece> GetPieces(){
        return pieces.listIterator();
    }
       
    @Override
    public void Start(){
        for(int i = 0; i < 3; ++i){
            for(int j = i%2, k = i*dimension; j < dimension; j+=2,k+=2){
                Piece tmp = new Piece();
                tmp.SetColor("WHITE");
                tmp.SetX(i);
                tmp.SetY(j);
                board[i][j].Occupy(tmp);
                pieces.add(tmp);
                tmp = new Piece();
                tmp.SetColor("BLACK");
                tmp.SetX(dimension-1-i);
                tmp.SetY(j);
                board[dimension-1-i][j].Occupy(tmp);
                pieces.add(tmp);
            }
        }
    }
    
    @Override
    public void MovePiece(int x,int y,int fx,int fy){
        
        Piece tmp = board[x][y].GetPiece();
        tmp.SetX(fx);
        tmp.SetY(fy);
        board[fx][fy].Occupy(tmp);
        board[x][y].Free();
        
    }
    
    @Override
    public void Capture(int x,int y){
        board[x][y].GetPiece();
        //tmp.SetIsCaptured(true);
        board[x][y].Free();
    };
    
    @Override
    public Piece PieceAt(int x,int y){
        return board[x][y].GetPiece();
    }
    
    @Override
    public boolean IsOccupied(int x,int y){
        return board[x][y].IsOccupied();
    }
    
    final private List<Piece> pieces;
    final private Tile[][] board;
    //private View view;
    //Must be always an even number
    private final int dimension = 8;
}
