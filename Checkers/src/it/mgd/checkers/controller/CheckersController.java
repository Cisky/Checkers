/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.controller;

import it.mgd.checkers.Utils.Utils;
import it.mgd.checkers.model.Model;
import it.mgd.checkers.model.Piece;
import static it.mgd.checkers.model.Piece.PieceColor.BLACK;
import static it.mgd.checkers.model.Piece.PieceColor.WHITE;
import it.mgd.checkers.View.View;
import it.mgd.checkers.model.Piece.PieceColor;

import static java.lang.Math.abs;

public class CheckersController implements Controller {

    //CONSTRUCTOR
    /** Create a controller with view and model specified in the parameters list */
    public CheckersController(View view, Model model){
        isWhiteTurn = true;
        this.model = model;
        this.view = view;
        view.setController(this);
    }
    
    //PUBLIC MEMBER FUNCTION
    /** Start a new game */
    @Override
    public void onNewGame(){
        model.start();
        isWhiteTurn = true;
        view.update();
    }
       
    /** Manages the actions to take when a tile is selected */
    @Override
    public void onClick(int x, int y){
        if(model.pieceAt(x, y) != null && checkTurn(model.pieceAt(x, y).getColor())){
            if(!isPieceSelected){
                selectPiece(x, y);
            }
            else{
                deselectPiece();
                selectPiece(x, y);
            }
        }else if(isPieceSelected){
            deselectPiece();
            
            switch(validateMove(pieceSelectedX, pieceSelectedY, x, y)){
                case MOVE:
                    model.movePiece(pieceSelectedX, pieceSelectedY, x, y);
                    isWhiteTurn=!isWhiteTurn;
                    promotionCheck(x,y,model.pieceAt(x,y).getColor());
                    break;

                case CAPTURE:
                    model.movePiece(pieceSelectedX, pieceSelectedY, x, y);
                    pieceSelectedX += (x - pieceSelectedX) / 2;
                    pieceSelectedY += (y - pieceSelectedY) / 2;
                    model.capture(pieceSelectedX, pieceSelectedY);
                    isWhiteTurn=!isWhiteTurn;
                    promotionCheck(x,y,model.pieceAt(x,y).getColor());
                    break;

                case INVALID:
                    view.invalidMove(x, y);
                    break;
            }      
            view.update();
        }else if(!checkTurn(model.pieceAt(x, y).getColor())){
            view.invalidMove(x, y);
        }
    }
    
    //PROTECTED MEMBER FUNCTION
    //NEEDED FOR TEST
    protected static MoveType GetMove(){
        return MoveType.MOVE;
    }
    
    //NEEDED FOR TEST
    protected static MoveType GetCapture(){
        return MoveType.CAPTURE;
    }
    
    //NEEDED FOR TEST
    protected static MoveType GetInvalid(){
        return MoveType.INVALID;
    }
    
    protected MoveType ValidateMoveTest(int x,int y,int finalX,int finalY){
        return validateMove(x,y,finalX,finalY);
    }
    
    //PRIVATE MEMBER FUNCTION
    /** Sets the piece in position (x, y) as selected and colors the selected tile on the checkerboard */
    private void selectPiece(int x, int y){
        pieceSelectedX = x;
        pieceSelectedY = y;
        isPieceSelected = true;
        view.selectTile(x, y);
    }
    
    /** Sets the pieces on the checkerboard as deselected and returns the tile to the original color */
    private void deselectPiece(){
        isPieceSelected = false;
        view.deselectAllTiles();
    }
    
    /** Returns: INVALID if the move is wrong, 
     *           MOVE if the move is legal and there aren't pieces of other color on  its path 
     *           CAPTURE if the move is legal and there are a pieces of other color on its path */
    private MoveType validateMove(int x, int y, int finalX, int finalY){
        MoveType result = MoveType.INVALID;
        int diffY, diffX;
        diffY = finalY - y;
        diffX = finalX - x;
        if(!model.isOccupied(finalX, finalY) && abs(diffY) == abs(diffX)){
            Piece piece = model.pieceAt(x, y);

            switch(diffY){
                case 1:
                    if(piece.isKing() || piece.getColor() == WHITE )
                        result = MoveType.MOVE;
                    break;
                
                case -1:
                    if(piece.isKing() || piece.getColor() == BLACK)
                       result = MoveType.MOVE;
                    break;

                case 2:
                    if(piece.isKing() || piece.getColor() == WHITE ){
                        Piece captured = model.pieceAt(x + diffX / 2, y + diffY / 2);
                        if(captured != null){
                            if(!captured.getColor().equals(piece.getColor()) && (piece.isKing() || captured.isMan()))
                                result = MoveType.CAPTURE;
                        }else{
                            if(piece.isKing())
                                result = MoveType.MOVE;
                        }
                    }
                    break;

                case -2:
                    if(piece.isKing() || piece.getColor() == BLACK){
                        Piece captured = model.pieceAt(x + diffX / 2, y + diffY / 2);
                        if(captured != null){
                            if(!captured.getColor().equals(piece.getColor()) && (piece.isKing() || captured.isMan()))
                                result = MoveType.CAPTURE;
                        }else{
                            if(piece.isKing())
                                result = MoveType.MOVE;
                        }
                    }
                    break;

                default:
                    if(piece.isKing()){
                        int incrementX = Integer.signum(diffX);
                        int incrementY = Integer.signum(diffY);
                        boolean Valid = true;
                        
                        for(int i = x, j = y; i != finalX && Valid; i += incrementX, j += incrementY)
                            Valid = !model.isOccupied(i, j);
                        
                        if(Valid)
                            result = MoveType.MOVE;
                    }
                    break;
            }
        }
        return result;
    } 
    
    /** Returns true if it is the turn of the piece with color 'color', false otherwise */
    private boolean checkTurn(PieceColor color){
        return (isWhiteTurn && color == WHITE) || (!isWhiteTurn && color == BLACK);
    }
    
    /** Check if the piec with color 'color' at position (x, y) on the checkerboard can be promoted */
    private void promotionCheck(int posX, int posY, PieceColor color){
        if((posY == 0 && color == BLACK) || (posY == Utils.numberOfTiles - 1 && color == WHITE))
            model.pieceAt(posX,posY).promote();
    }
   
    //MEMBER
    private final Model model;      /** Model of checker game */
    private final View view;        /** View of checker game */
    private boolean isPieceSelected;/** The piece on tile is selected or unselected */
    private int pieceSelectedX;     /** Position X of the piece selected on checkerboard */
    private int pieceSelectedY;     /** Position Y of the piece selected on checkerboard */
    private boolean isWhiteTurn;    /** Is turn of the white piece or of the black piece */
    
    /** Type of move made */
    private enum MoveType{
        INVALID,
        MOVE,
        CAPTURE
    }
}