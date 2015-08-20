/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.model;

public class Piece 
{
    //ENUM
    public enum PieceColor
    {
        BLACK,
        WHITE
    }
    
    //MEMBER
    private PieceColor color;
    private boolean isKing;
    private int posX;
    private int posY;
    
    //CONSTRUCTOR
    public Piece()
    {
        color = null;
        isKing = false;
        posX = 0;
        posY = 0;
    }
    
    //MEMBER FUNCTION
    public boolean isKing()
    {
        return isKing;
    }
    
    public boolean isMan()
    {
        return !isKing;
    }
    
    public void promote()
    {
        isKing = true;
    }
    
    public int getX()
    {
        return posX;
    }
    
    public int getY()
    {
        return posY;
    }
    
    public void setX(int x)
    {
        posX = x;
    }
    
    public void setY(int y)
    {
        posY = y;
    }
    
    public PieceColor getColor()
    {
        return color;
    }
    
    public void setColor(PieceColor color)
    {
        this.color = color;
    }
}