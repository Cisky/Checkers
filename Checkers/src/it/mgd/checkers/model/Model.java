/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.model;

public interface Model { 
    
    public void start();
    
    public void movePiece(int x, int y, int finalX, int finalY);
    
    public Piece pieceAt(int x, int y);
    
    public void capture(int x, int y);
    
    public boolean isOccupied(int x, int y);
}