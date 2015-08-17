/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mgd.checkers.controller;

/**
 *
 * @author Giuliano
 */
public interface Controller {
    
    public void OnNewGame();
    
    public void OnDragAndDrop(int intialX,int initialY,int finalX, int finalY);
}
