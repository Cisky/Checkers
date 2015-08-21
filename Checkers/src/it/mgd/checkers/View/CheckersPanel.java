/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.View;

import it.mgd.checkers.Utils.Utils;
import it.mgd.checkers.controller.Controller;
import it.mgd.checkers.model.Model;
import it.mgd.checkers.model.Piece;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CheckersPanel extends JPanel implements View{

    //CONSTRUCTOR
    /** Create checkerboard */
    public CheckersPanel(Model model){
        this.model = model;

        board = new JButton[Utils.numberOfTiles][Utils.numberOfTiles];
        createBoard();
    }

    //PUBLIC MEMBER FUNCTION
    /** Get the model */
    @Override
    public Model getModel(){
        return model;
    }
	
    /** Set the Controller */
    @Override
    public void setController(Controller controller){
        this.controller = controller;
    }
    
    /** Update the pieces on the checkerboard */
    @Override
    public void update(){
        for (int y = 0; y < Utils.numberOfTiles; ++y){
            for (int x = 0; x < Utils.numberOfTiles; ++x){
                removePieceAt(x, y);
                setPieceAt(x, y, model.pieceAt(x, y));
                board[x][y].repaint();
            }
        }
    }
    
    /** Colors the tile selected on the checkerboard at position (x, y) */
    @Override
    public void selectTile(int x, int y){
        board[x][y].setIcon(Utils.loadIcon(Utils.selectedTile, Utils.tileSize, Utils.tileSize));
        board[x][y].setBorder(new LineBorder(Color.BLACK));
    }
    
    /** Deselect the tile at position (x, y) previously selected on the checkerboard */
    @Override
    public void deselectTile(int x, int y){
        if(board[x][y].getBackground() == Color.BLACK)
            board[x][y].setIcon(Utils.loadIcon(Utils.blackTile, Utils.tileSize, Utils.tileSize));
        else
            board[x][y].setIcon(Utils.loadIcon(Utils.whiteTile,  Utils.tileSize, Utils.tileSize));

        board[x][y].setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    /** Deselect all tile on the checkerboard */
    @Override
    public void deselectAllTiles(){
        for (int y = 0; y < Utils.numberOfTiles; ++y)
            for (int x = 0; x < Utils.numberOfTiles; ++x) 
                deselectTile(x, y);
    }

    /** Colors the tile at position (x, y) as invalid when the move is wrong */
    @Override
    public void invalidMove(int x, int y){
        if (board[x][y].getBackground() == Color.BLACK){
            board[x][y].setIcon(Utils.loadIcon(Utils.invalidTile, Utils.tileSize, Utils.tileSize));
            board[x][y].setBorder(new LineBorder(Color.BLACK));
        }
        
        Timer timer = new Timer(1000, (ActionEvent ae) -> {
            if (board[x][y].getBackground() == Color.BLACK){
                board[x][y].setIcon(Utils.loadIcon(Utils.blackTile, Utils.tileSize, Utils.tileSize));
                board[x][y].setBorder(new EmptyBorder(0, 0, 0, 0));
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /** Create  checkerboard */
    //PRIVATE MEMBER FUNCTION
    private void createBoard(){       
        setLayout(new GridLayout(Utils.numberOfTiles, Utils.numberOfTiles));
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        
        for (int y = 0; y < Utils.numberOfTiles; ++y){
            for (int x = 0; x < Utils.numberOfTiles; ++x){
                board[x][y] = new JButton();
                ImageIcon tileIcon;
                if ((x % 2 == 1 && y % 2 == 1) || (x % 2 == 0 && y % 2 == 0)){
                    board[x][y].setBackground(Color.WHITE);
                    tileIcon = Utils.loadIcon(Utils.whiteTile, Utils.tileSize, Utils.tileSize);
                }else{
                    board[x][y].setBackground(Color.BLACK);
                    tileIcon = Utils.loadIcon(Utils.blackTile, Utils.tileSize, Utils.tileSize);
                }

                board[x][y].setIcon(tileIcon);
                board[x][y].setBorder(new EmptyBorder(0, 0, 0, 0));
                addListener(x, y);
                add(board[x][y]);
            }
        }
    }
    
    /** Add Listener for mouse input when clicked on tile in position (x, y) */
    private void addListener(int x,int y){
            board[x][y].addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent evt){
                    controller.onClick(x, y);
                }
            });
    }
    
    /** Set piece on checkerboard in position (x, y) */
    private void setPieceAt(int x, int y, Piece value){
        if (value != null)
            board[x][y].add(value.getLabel());
    }
    
    /** Remove piece on checkerboard in position (x, y) */
    private void removePieceAt(int x, int y){
        if(!model.isOccupied(x, y) && board[x][y].getComponents().length > 0){
            board[x][y].remove(0);
        }
    }
    
    //MEMBER
    private final Model model;      /** Model of checker game */
    private Controller controller;  /** Controller of checker game */
    
    private final JButton[][] board;/** Matrix of JButton objects that define the aspect and the operations that it's possible to do on the checkerboard GUI*/
}