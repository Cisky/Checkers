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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CheckersPanel extends JPanel implements View{

    //CONSTRUCTOR
    public CheckersPanel(Model model, JFrame frame){
        this.frame = frame;
        this.model = model;

        board = new JButton[Utils.numberOfCells][Utils.numberOfCells];
        initBoard();
    }

    //PUBLIC MEMBER FUNCTION
    @Override
    public Model getModel(){
        return model;
    }
	
    @Override
    public void setController(Controller controller){
        this.controller = controller;
    }
    
    @Override
    public void update(){
        for (int y = 0; y < Utils.numberOfCells; ++y){
            for (int x = 0; x < Utils.numberOfCells; ++x){
                removePieceAt(x, y);
                setPieceAt(x, y, model.pieceAt(x, y));
                board[x][y].repaint();
            }
        }
    }
    
    @Override
    public void selectCell(int x, int y){
        board[x][y].setIcon(Utils.loadIcon(Utils.selectedCell, Utils.cellSize, Utils.cellSize));
        board[x][y].setBorder(new LineBorder(Color.BLACK));
    }
    
    @Override
    public void deselectCell(int x, int y){
        if(board[x][y].getBackground() == Color.BLACK)
            board[x][y].setIcon(Utils.loadIcon(Utils.blackCell, Utils.cellSize, Utils.cellSize));
        else
            board[x][y].setIcon(Utils.loadIcon(Utils.whiteCell,  Utils.cellSize, Utils.cellSize));

        board[x][y].setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    @Override
    public void deselectAllCells(){
        for (int y = 0; y < Utils.numberOfCells; ++y)
            for (int x = 0; x < Utils.numberOfCells; ++x) 
                deselectCell(x, y);
    }

    @Override
    public void invalidMove(int x, int y){
        if (board[x][y].getBackground() == Color.BLACK){
            board[x][y].setIcon(Utils.loadIcon(Utils.invalidCell, Utils.cellSize, Utils.cellSize));
            board[x][y].setBorder(new LineBorder(Color.BLACK));
        }
        
        Timer timer = new Timer(1000, (ActionEvent ae) -> {
            if (board[x][y].getBackground() == Color.BLACK){
                board[x][y].setIcon(Utils.loadIcon(Utils.blackCell, Utils.cellSize, Utils.cellSize));
                board[x][y].setBorder(new EmptyBorder(0, 0, 0, 0));
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    //PRIVATE MEMBER FUNCTION
    private void initBoard(){       
        setLayout(new GridLayout(Utils.numberOfCells, Utils.numberOfCells));
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        
        for (int y = 0; y < Utils.numberOfCells; ++y){
            for (int x = 0; x < Utils.numberOfCells; ++x){
                board[x][y] = new JButton();
                ImageIcon cellIcon;
                if ((x % 2 == 1 && y % 2 == 1) || (x % 2 == 0 && y % 2 == 0)){
                    board[x][y].setBackground(Color.WHITE);
                    cellIcon = Utils.loadIcon(Utils.whiteCell, Utils.cellSize, Utils.cellSize);
                }else{
                    board[x][y].setBackground(Color.BLACK);
                    cellIcon = Utils.loadIcon(Utils.blackCell, Utils.cellSize, Utils.cellSize);
                }

                board[x][y].setIcon(cellIcon);
                board[x][y].setBorder(new EmptyBorder(0, 0, 0, 0));
                addListener(x,y);
                add(board[x][y]);
            }
        }
    }
    
    private void addListener(int x,int y){
            board[x][y].addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent evt){
                    controller.onClick(x, y);
                }
            });
    }
    
    private void setPieceAt(int x, int y, Piece value) 
    {
        if (value != null)
            board[x][y].add(value.getLabel());
    }
    
    private void removePieceAt(int x, int y){
        if(!model.isOccupied(x, y) && board[x][y].getComponents().length > 0){
            board[x][y].remove(0);
        }
    }
    
    //MEMBER
    private final JFrame frame;
    private final Model model;
    private Controller controller;
    
    private final JButton[][] board;
}