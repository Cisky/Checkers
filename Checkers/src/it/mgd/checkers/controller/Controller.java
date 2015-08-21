/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.controller;

public interface Controller {
    
    /** Start a new game */
    public void onNewGame();
    
    /** Manages the actions to take when a tile is selected */
    public void onClick(int x, int y);
}