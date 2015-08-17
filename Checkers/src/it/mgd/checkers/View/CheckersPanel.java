package it.mgd.checkers.View;

import it.mgd.checkers.controller.Controller;
import it.mgd.checkers.model.Model;
import it.mgd.checkers.model.Piece;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Francesco Battipaglia
 */

public class CheckersPanel extends JPanel implements View
{
    //CAMPI
    private static final long serialVersionUID = 1L;

    private final JFrame frame;
    private final Model model;
    private Controller controller;
    
    private final int cellsNumber = 8;
    private final int cellsSize = 64;
    
    private JLayeredPane layeredPane;
    private final JButton[][] checker = new JButton[cellsNumber][cellsNumber];
    private int draggedAtX;
    private int draggedAtY;
    private int originalPositionX;
    private int originalPositionY;

    //COSTRUTTORE
    public CheckersPanel(Model model, JFrame frame) 
    {
        this.frame = frame;
        this.model = model;
        
        initializeBoard();
    }
    
    //METODI
    private void initializeBoard() 
    {       
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(cellsSize * cellsNumber, cellsSize * cellsNumber));
        
        for (int column = 0; column < cellsNumber; column++)
            for (int row = 0; row < cellsNumber; row++) 
            {
                ImageIcon cellIcon;
                if ((row % 2 == 1 && column % 2 == 1) || (row % 2 == 0 && column % 2 == 0))
                    cellIcon = new ImageIcon("assets/White.png");
                else
                    cellIcon = new ImageIcon("assets/Black.png");

                Image img = cellIcon.getImage();
                Image newimg = img.getScaledInstance(cellsSize, cellsSize, Image.SCALE_SMOOTH);
            
                JButton cellButton = new JButton(new ImageIcon(newimg));
                cellButton.setBorder(new EmptyBorder(0, 0, 0, 0));
                cellButton.setBounds(cellsSize * row, cellsSize * column,  cellsSize,  cellsSize);

                checker[row][column] = cellButton;

                layeredPane.add(checker[row][column], 0);
            }   
            
//        for (int column = 0; column < cellsNumber; column++)
//            for (int row = 0; row < cellsNumber; row++) 
//                loadPieceAt(row, column, model.PieceAt(row, column));

        add(layeredPane);
    }
    
    private void loadPieceAt(int row, int column, Piece value) 
    {
        ImageIcon cellIcon = null;
//        if (value == 1)
//            cellIcon  = new ImageIcon("assets/WhitePawn.png");
//        else if (value == 2)
//            cellIcon = new ImageIcon("assets/BlackPawn.png");
       
        if(cellIcon != null)
        {
            Image img = cellIcon.getImage();
            Image newimg = img.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                
            JLabel pieceLabel = new JLabel(new ImageIcon(newimg));
            pieceLabel.setAlignmentX(CENTER_ALIGNMENT);
            pieceLabel.setAlignmentY(CENTER_ALIGNMENT);
            
            pieceLabel.setLabelFor(checker[row][column]);
            checker[row][column].add(pieceLabel);

            pieceLabel.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mousePressed(MouseEvent evt)
                    {
                        draggedAtX = evt.getX();
                        draggedAtY = evt.getY();
                        
                        originalPositionX = pieceLabel.getX();
                        originalPositionY = pieceLabel.getY();
                        
//                        if (controller != null)
//                            controller.OnDragAndDrop(row, column, );
                    }

                    @Override
                    public void mouseReleased(MouseEvent evt)
                    {
                        pieceLabel.setLocation(originalPositionX, originalPositionY);
                    }
                });
            
            pieceLabel.addMouseMotionListener(new MouseMotionAdapter()
                {
                    @Override
                    public void mouseDragged(MouseEvent evt)
                    {
                        pieceLabel.setLocation( evt.getX() - draggedAtX + pieceLabel.getLocation().x,
                                                evt.getY() - draggedAtY + pieceLabel.getLocation().y);
                    }
                });
        }
    }
    
    @Override
    public Model GetModel() 
    {
        return model;
    }
	
    @Override
    public void SetController(Controller controller) 
    {
        this.controller = controller;
    }
}
