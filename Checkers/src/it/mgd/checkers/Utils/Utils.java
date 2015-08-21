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
    public static final int pieceSize = 32;                             /** Size of piece image */
    public static final String whiteMan = "assets/WhitePawn.png";       /** Path of white man image */
    public static final String whiteKing = "assets/WhiteDama.png";      /** Path of white king image */
    public static final String blackMan = "assets/BlackPawn.png";       /** Path of black man image */
    public static final String blackKing = "assets/BlackDama.png";      /** Path of black king image */
    
    public static final int tileSize = 64;                              /** Size of tile image */
    public static final String whiteTile = "assets/White.png";          /** Path of white tile image */
    public static final String blackTile = "assets/Black.png";          /** Path of black tile image */
    
    public static final String selectedTile = "assets/Selected.png";    /** Path of selected tile image */
    public static final String invalidTile = "assets/Invalid.png";      /** Path of invalid selected tile image */

    public static final int numberOfTiles = 8;                           /** Number of tiles on checkerboard */
    public static final int numberOfLines = 3;                           /** Number of lines of pieces */

    //MEMBER FUNCTION
    /** Return the image icon created from the image with filename and dimension specified on parameters list */
    public static ImageIcon loadIcon(String filename, int sizeX, int sizeY){
        ImageIcon icon = new ImageIcon(filename);
        Image imgIcon = icon.getImage();
        imgIcon = imgIcon.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        return new ImageIcon(imgIcon);
    }
}
