/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.Utils;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Utils 
{
    //CONSTANTS
    public static final int pieceSize = 32; 
    public static final String whiteMan = "assets/WhitePawn.png";
    public static final String whiteKing = "assets/WhiteDama.png"; 
    public static final String blackMan = "assets/BlackPawn.png"; 
    public static final String blackKing = "assets/BlackDama.png"; 
    
    public static final int cellSize = 64; 
    public static final String blackCell = "assets/Black.png";
    public static final String whiteCell = "assets/White.png";
    
    public static final String selectedCell = "assets/Selected.png";
    public static final String invalidCell = "assets/InvalidBlack.png";

    public static final int numberOfCells = 8;
    public static final int numberOfLines = 3;

    //MEMBER FUNCTION
    public static ImageIcon loadIcon(String filename, int sizeX, int sizeY){
        ImageIcon icon = new ImageIcon(filename);
        Image imgIcon = icon.getImage();
        imgIcon = imgIcon.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        return new ImageIcon(imgIcon);
    }
}
