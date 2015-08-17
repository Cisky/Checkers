/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mgd.checkers.model;

/**
 *
 * @author Giuliano
 */
public class Piece {
    private String color;
    private boolean isKing;
    private int posX,posY;
    
    public Piece(){
        color = null;
        isKing = false;
        posX = 0;
        posY = 0;
    }
    
    public boolean IsKing(){
        return isKing;
    }
    
    public boolean IsMan(){
        return !isKing;
    }
    
    public void Promote(){
        isKing = true;
    }
    
    public void SetX(int x){
        posX = x;
    }
    
    public int GetX(){
        return posX;
    }
    
    public void SetY(int y){
        posY = y;
    }
    
    public int GetY(){
        return posY;
    }
    
    public void SetColor(String Col){
        color = Col;
    }
    
    public String GetColor(){
        return color;
    }
}
