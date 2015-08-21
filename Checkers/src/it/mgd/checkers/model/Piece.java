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
    /** Create a Piece object with a specific color, position on checkerboard and 
     *  a label that will contain the image of the piece */
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
    /** Returns true if the piece is king, otherwise return false */
    public boolean isKing(){
        return isKing;
    }
    
    /** Returns true if the piece is man, otherwise return false */
    public boolean isMan(){
        return !isKing;
    }
    
    /** Promotes Man to King */
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
    
    /** Returns position X of the piece on the checkerboard */
    public int getX(){
        return posX;
    }
    
    /** Returns position Y of the piece on the checkerboard */
    public int getY(){
        return posY;
    }
    
    /** Set position X of the piece on the checkerboard */
    public void setX(int x){
        posX = x;
    }
    
    /** Set position Y of the piece on the checkerboard */
    public void setY(int y){
        posY = y;
    }
    
    /** Returns the color of the piece */
    public PieceColor getColor(){
        return color;
    }
    
    /** Set the color of the piece and load the image */
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
          
    /** Returns the label that contain the image of the piece */
    public JLabel getLabel(){
        return label;
    }
    
    //PRIVATE MEMBER FUNCTION
    /** Set a image to the label */
    private void setLabel(String filename, int sizeX, int sizeY){
        label.setIcon(Utils.loadIcon(filename, sizeX, sizeY));
    }
    
    /** Color of piece */
    //PUBLIC MEMBER
    public enum PieceColor{
        BLACK,
        WHITE
    }
    
    //MEMBER
    private PieceColor color;   /** Color of piece*/
    private boolean isKing;     /** Is King or Man*/
    private int posX;           /** Position X of the piece on the checkerboard */
    private int posY;           /** Position Y of the piece on the checkerboard */
    private final JLabel label; /** Label that will contain the image of the piece */
}