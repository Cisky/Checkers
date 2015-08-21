package it.mgd.checkers.controller;

import it.mgd.checkers.View.View;
import it.mgd.checkers.model.Model;
/**
 *
 * @author Giuliano
 */
public class ViewStub implements View{

    @Override
    public Model getModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setController(Controller controller) {
    }

    @Override
    public void update() {
    }

    @Override
    public void selectTile(int x, int y) {
    }

    @Override
    public void invalidMove(int x, int y) {
    }

    @Override
    public void deselectTile(int x, int y) {
    }

    @Override
    public void deselectAllTiles() {
    }
    
}
