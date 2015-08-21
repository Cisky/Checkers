/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.View;

import it.mgd.checkers.controller.Controller;
import it.mgd.checkers.model.Model;

public interface View{
    
    /** Get the model */
    public Model getModel();

    /** Set the Controller */
    public void setController(Controller controller);

    /** Update the pieces on the board */
    public void update();

    /** Colors the tile selected on the checkerboard at position (x, y) */
    public void selectTile(int x, int y);

    /** Deselect the tile at position (x, y) previously selected on the checkerboard */
    public void deselectTile(int x, int y);

    /** Deselect all tile on the checkerboard */
    public void deselectAllTiles();
    
    /** Colors the tile at position (x, y) as invalid when the move is wrong */
    public void invalidMove(int x, int y);
}