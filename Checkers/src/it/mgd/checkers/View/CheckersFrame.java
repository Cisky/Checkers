/**************************
*  Francesco Battipaglia  *
*  Giuliano Focchiatti    *
**************************/
package it.mgd.checkers.View;

import it.mgd.checkers.model.CheckersModel;
import it.mgd.checkers.controller.CheckersController;
import it.mgd.checkers.controller.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CheckersFrame extends JFrame{

    //CONSTRUCTOR
    /** Create a window with control buttons and the checkerboard initialized */
    public CheckersFrame(){
        model = new CheckersModel();
        View view = createBoard();
        controller = new CheckersController(view, model);
        controller.onNewGame();
        
        framePosition = null;

        setIconImage(new ImageIcon("assets/WhiteDama.png").getImage());
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
    }

    //PRIVATE MEMBER FUNCTION
    /** Add at window button new game and button X which allows to close it.
     *  Handles the mouse input for these components */
    private void addControlButtons(){
        JButton newGame = createButton("New Game", new Color(33,33,33), Color.WHITE, new LineBorder(Color.BLACK), new Dimension(110, 34), false);
        newGame.addActionListener(event -> controller.onNewGame());

        JButton exitGame = createButton("X", new Color(33,33,33), new Color(236, 36, 36), new LineBorder(Color.BLACK), new Dimension(30, 22), false);
        exitGame.addActionListener(event -> System.exit(0));
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraintsNewGame = buttonConstraints(0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 10, 5, 0));
        GridBagConstraints constraintsExitGame = buttonConstraints(1, 0, 1, 1, GridBagConstraints.EAST, new Insets(0, 0, 0, 8));
        
        JPanel menu = new JPanel();
        menu.setLayout(layout);
        menu.setBackground(Color.WHITE);
        menu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        menu.add(newGame, constraintsNewGame);
        menu.add(exitGame, constraintsExitGame);
        
        menu.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mousePressed(MouseEvent evt){
                        framePosition = evt.getPoint();
                    }
                });
        
        menu.addMouseMotionListener(new MouseMotionAdapter(){
                @Override
                public void mouseDragged(MouseEvent evt){
                    Point currCoords = evt.getLocationOnScreen();
                    setLocation(currCoords.x - framePosition.x, currCoords.y - framePosition.y);
                }
            });
        
        add(menu, BorderLayout.PAGE_START);
    }
    
    /** Returns a button with name, foreground color, background color, border and size specified in the parameters list */
    private JButton createButton(String name, Color foreground, Color background, Border border, Dimension size, boolean isFocusPainted){
        JButton button = new JButton(name);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setBorder(border);
        button.setPreferredSize(size);
        button.setFocusPainted(isFocusPainted);
        
        return button;
    }
    
    /** Returns a GridBagConstraints for a component */
    private GridBagConstraints buttonConstraints(int gridX, int gridY, int weightX, int weightY, int anchor, Insets insest){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.weightx = weightX;
        constraints.weighty = weightY;
        constraints.anchor = anchor;
        constraints.insets.top = insest.top;
        constraints.insets.bottom = insest.bottom;
        constraints.insets.left = insest.left;
        constraints.insets.right = insest.right;
        
        return constraints;
    }

    /** Returns a chekerboard and add it at window*/
    private View createBoard(){
        addControlButtons();

        CheckersPanel panel = new CheckersPanel(model);
        add(panel, BorderLayout.CENTER);

        return panel;
    }
    
    //MEMBER
    private final CheckersModel model;      /** Model of checker game */
    private final Controller controller;    /** Controller of checker game */
    private Point framePosition;            /** Position frame on screen */
}