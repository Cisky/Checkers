/***************************************************************
*  Checkers - Project of Fundamentals of Software Engineering  * 
*  Francesco Battipaglia                                       *
*  Giuliano Focchiatti                                         *
****************************************************************/
package it.mgd.checkers;

import it.mgd.checkers.View.CheckersFrame;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Checkers {
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                JFrame frame = new CheckersFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
    
}