/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mgd.checkers.model;

import it.mgd.checkers.View.View;

/**
 *
 * @author Giuliano
 */
public interface Model 
{
 
    public void Start();
    public void MovePiece(int x,int y,int fx,int fy);
    public Piece PieceAt(int x,int y);
    public void Capture(int x,int y);
    public boolean IsOccupied(int x,int y);
}
