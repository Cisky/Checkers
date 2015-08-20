/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.View;

import it.mgd.checkers.controller.Controller;
import it.mgd.checkers.model.Model;
import it.mgd.checkers.model.Piece;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class CheckersPanel extends JPanel implements View
{
    //MEMBER
    private final JFrame frame;
    private final Model model;
    private Controller controller;
    
    private final int cellsNumber = 8;
    private final int cellsSize = 64;
    private final JButton[][] board;

    //CONSTRUCTOR
    public CheckersPanel(Model model, JFrame frame) 
    {
        this.frame = frame;
        this.model = model;

        board = new JButton[cellsNumber][cellsNumber];

        initBoard();
    }
        
    //MEMBER FUNCTION
    private void initBoard()
    {       
        setLayout(new GridLayout(cellsNumber, cellsNumber));
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));
        
        for (int y = 0; y < cellsNumber; ++y)
            for (int x = 0; x < cellsNumber; ++x) 
            {
                ImageIcon cellIcon;
                if ((x % 2 == 1 && y % 2 == 1) || (x % 2 == 0 && y % 2 == 0))
                    cellIcon = loadIcon("assets/White.png", cellsSize, cellsSize);
                else
                    cellIcon = loadIcon("assets/Black.png", cellsSize, cellsSize);
                
                board[x][y] = new JButton(cellIcon);
                board[x][y].setBorder(new EmptyBorder(0, 0, 0, 0));
                
                add(board[x][y]);
            }
    }
    
    private void setPieceAt(int x, int y, Piece value) 
    {
        ImageIcon cellIcon = null;
        if (value != null)
            switch(value.getColor())
            {
                case WHITE:
                    cellIcon = loadIcon("assets/WhitePawn.png", cellsSize / 2, cellsSize / 2);
                    break;
                case BLACK:
                    cellIcon = loadIcon("assets/BlackPawn.png", cellsSize / 2, cellsSize / 2);
                    break;
            }
                
        if(cellIcon != null)
        {
            JLabel pieceLabel = new JLabel(cellIcon);
            pieceLabel.setAlignmentX(CENTER_ALIGNMENT);
            pieceLabel.setAlignmentY(CENTER_ALIGNMENT);

            board[x][y].add(pieceLabel);
        }
        
        board[x][y].addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mousePressed(MouseEvent evt)
                    {
                        controller.onClick(x, y);
                    }
                });
    }
    
    private void removePieceAt(int x, int y)
    {
        Component[] component = board[x][y].getComponents();
        if(!model.isOccupied(x, y) && component.length > 0)
        {
            for(int index = 0; index < component.length; ++index)
                if(component[index].getClass().getName().contains("JLabel"))
                    board[x][y].remove(index);
        }
    }
    
    private ImageIcon loadIcon(String filename, int sizeX, int sizeY)
    {
        ImageIcon icon = new ImageIcon(filename);
        Image imgIcon = icon.getImage();
        imgIcon = imgIcon.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
            
        return new ImageIcon(imgIcon);
    }
    
    @Override
    public Model getModel() 
    {
        return model;
    }
	
    @Override
    public void setController(Controller controller) 
    {
        this.controller = controller;
    }
    
    @Override
    public void update()
    {
        for (int y = 0; y < cellsNumber; ++y)
            for (int x = 0; x < cellsNumber; ++x) 
            {
                setPieceAt(x, y, model.pieceAt(x, y));
                removePieceAt(x, y);
            }

        revalidate();
    }
}