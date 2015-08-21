/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.model;

public interface Model { 
    
    /** Set the pieces on the checkerboard at the start position */
    public void start();
    
    /** Move the piece on the checkerboard from position (x, y) to position (finalX, finalY) */
    public void movePiece(int x, int y, int finalX, int finalY);
    
    /** Returns the piece on the checkerboard at position (x, y) */
    public Piece pieceAt(int x, int y);
    
    /** Capture the piece on the checkerboard at position (x, y) */
    public void capture(int x, int y);
    
    /** Return true if the tile on the checkerboard at position (x, y) is occupied, otherwise return false */
    public boolean isOccupied(int x, int y);
}