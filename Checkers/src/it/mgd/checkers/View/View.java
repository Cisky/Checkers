/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.View;

import it.mgd.checkers.controller.Controller;
import it.mgd.checkers.model.Model;

public interface View{
    
    public Model getModel();

    public void setController(Controller controller);
    
    public void update();
    
    public void selectCell(int x, int y);
    
    public void invalidMove(int x, int y);

    public void deselectCell(int x, int y);

    public void deselectAllCells();
}