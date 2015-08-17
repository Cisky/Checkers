/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mgd.checkers.controller;
import it.mgd.checkers.model.Model;
import it.mgd.checkers.model.Piece;
import it.mgd.checkers.View.View;
/**
 *
 * @author Giuliano
 */
public class CheckersController implements Controller 
{
    public CheckersController(View view, Model model)
    {
        this.model = model;
        this.view = view;
        view.SetController(this);
    }
    
    @Override
    public void OnNewGame()
    {
        model.Start();

    }
       
    @Override
    public void OnDragAndDrop(int initialX,int initialY,int finalX,int finalY)
    {
        switch(ValidateMove(initialX,initialY,finalX,finalY)){
            case MOVE:
                model.MovePiece(initialX,initialY,finalX,finalY);
                break;
                
            case CAPTURE:
                model.MovePiece(initialX,initialY,finalX,finalY);
                initialX += (finalX - initialX)/2;
                initialY += (finalY - initialY)/2;
                model.Capture(initialX, initialY);
                break;
                
            case INVALID:
                //view.ShowInvalidMoveDialog();
                break;
        
            default:
                break;
        }
    }
    
    private MoveType ValidateMove(int x,int y,int fx,int fy)
    {
        MoveType result = MoveType.INVALID;
        int diffy,diffx;
        diffy = fy-y;
        diffx = fx-x;
        if(!model.IsOccupied(fx,fy)||Math.abs(diffy)==Math.abs(diffx)){
            Piece piece = model.PieceAt(x, y);

        switch(diffy){
            case 1:
                
                if(piece.IsKing() || piece.GetColor().equals("WHITE"))
                    result = MoveType.MOVE;
                break;
                
            case -1:
                
                if(piece.IsKing() || piece.GetColor().equals("BLACK"))
                   result = MoveType.MOVE;
                break;
                
            case 2:
                
                if(piece.IsKing() || piece.GetColor().equals("WHITE") ){
                    Piece captured = model.PieceAt(x+diffx/2,y+diffy/2);
                    if(captured != null){
                        if(!captured.GetColor().equals(piece.GetColor())
                            && (piece.IsKing() || captured.IsMan()))
                            result = MoveType.CAPTURE;
                    }else{
                        if(piece.IsKing())
                            result = MoveType.MOVE;
                    }
                }
                break;
                
            case -2:
                
                if(piece.IsKing() || piece.GetColor().equals("BLACK") ){
                    Piece captured = model.PieceAt(x+diffx/2,y+diffy/2);
                    if(captured != null){
                        if(!captured.GetColor().equals(piece.GetColor()) && 
                            (piece.IsKing() || captured.IsMan()))
                            result = MoveType.CAPTURE;
                    }else{
                        if(piece.IsKing())
                            result = MoveType.MOVE;
                    }
                }
                break;
                
            default:
                
                if(piece.IsKing()){
                    int incrementX,incrementY;
                    incrementX = Integer.signum(diffx);
                    incrementY = Integer.signum(diffy);
                    boolean Valid = true;
                    for(int i=x,j=y; i != fx && Valid;i+=incrementX,j+=incrementY)
                        Valid = !model.IsOccupied(i, j);
                    if(Valid)
                        result = MoveType.MOVE;
                }
                break;
            }
        }
        return result;
    }
    
    private enum MoveType
    {
        INVALID,
        MOVE,
        CAPTURE
    }

    private final Model model;
    private final View view;
}
