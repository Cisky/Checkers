/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.model;

import it.mgd.checkers.Utils.Utils;
import static java.awt.Component.CENTER_ALIGNMENT;
import javax.swing.JLabel;

public class Piece {

    //CONSTRUCTOR
    public Piece(){
        color = null;
        isKing = false;
        posX = 0;
        posY = 0;
        label = new JLabel();
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setAlignmentY(CENTER_ALIGNMENT);
    }
    
    //PUBLIC MEMBER FUNCTION
    public boolean isKing(){
        return isKing;
    }
    
    public boolean isMan(){
        return !isKing;
    }
    
    public void promote(){
        isKing = true;
        switch(color){
            case WHITE:
                setLabel(Utils.whiteKing, Utils.pieceSize, Utils.pieceSize);
                break;
            case BLACK:
                setLabel(Utils.blackKing, Utils.pieceSize, Utils.pieceSize);
                break;
        }
    }
    
    public int getX(){
        return posX;
    }
    
    public int getY(){
        return posY;
    }
    
    public void setX(int x){
        posX = x;
    }
    
    public void setY(int y){
        posY = y;
    }
    
    public PieceColor getColor(){
        return color;
    }
    
    public void setColor(PieceColor color){
        this.color = color;
        switch(color){
            case WHITE:
                setLabel(Utils.whiteMan, Utils.pieceSize, Utils.pieceSize);
                break;
            case BLACK:
                setLabel(Utils.blackMan, Utils.pieceSize, Utils.pieceSize);
                break;
        }
    }
          
    public JLabel getLabel(){
        return label;
    }
    
    //PRIVATE MEMBER FUNCTION
    private void setLabel(String filename, int sizeX, int sizeY){
        label.setIcon(Utils.loadIcon(filename, sizeX, sizeY));
    }
    
    //PUBLIC MEMBER
    public enum PieceColor{
        BLACK,
        WHITE
    }
    
    //MEMBER
    private PieceColor color;
    private boolean isKing;
    private int posX;
    private int posY;
    private final JLabel label;
}