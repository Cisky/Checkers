package it.mgd.checkers.View;

import it.mgd.checkers.model.CheckersModel;
import it.mgd.checkers.controller.CheckersController;
import it.mgd.checkers.controller.Controller;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Francesco Battipaglia
 */

public class CheckersFrame extends JFrame 
{
    //CAMPI
    private static final long serialVersionUID = 1L;
    
    private final CheckersModel model;
    private final Controller controller;

    //COSTRUTTORE
    public CheckersFrame() 
    {
        setTitle("Checkers");

        View view = createBoard();
        model = new CheckersModel();
        controller = new CheckersController(view, model);
        
        setIconImage(new ImageIcon("assets/WhiteDama.png").getImage());
        setResizable(false);
        
        pack();

        setLocationRelativeTo(null);
    }

    //METODI
    private void addControlButtons() 
    {
        JPanel panel = new JPanel();

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(event -> controller.OnNewGame());
        panel.add(newGame);

        add(panel, BorderLayout.WEST);
    }

    private View createBoard()
    {
        addControlButtons();

        CheckersPanel panel = new CheckersPanel(model, this);
        add(panel, BorderLayout.CENTER);

        return panel;
    }
}