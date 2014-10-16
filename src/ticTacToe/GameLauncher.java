/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ticTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Thilini
 */
public class GameLauncher extends JFrame{
    
    JPanel imagepanel;
    JPanel buttons;
    
    JButton newGame,exit,stat;
    
    
    public GameLauncher(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        
        setLayout(new GridLayout(2,1));
        
        
        imagepanel = new JPanel();
        imagepanel.setBackground(new Color(3,2,155));
        
        ImageIcon img;
        img = new ImageIcon("tictactoe.jpg");
        
        JLabel label = new JLabel(); 
        label.setIcon(img); 
        
        imagepanel.add(label);
        
        add(imagepanel);
        
        GridLayout gridBtn = new GridLayout(2,2);
        gridBtn.setHgap(50);
        gridBtn.setVgap(50);
        buttons= new JPanel(gridBtn);
        buttons.setBackground(new Color(3,2,155));
        buttons.setBorder(new EmptyBorder(20,20,20,20));
        
        ButtonClickHandler evt = new ButtonClickHandler();
        
        newGame = new JButton("New Game");
        newGame.setBackground(new Color(3,2,155));
        ImageIcon start = new ImageIcon("start.png");
        newGame.setIcon(start);
        newGame.setForeground(Color.yellow);
        newGame.addActionListener(evt);
        
        
        exit = new JButton("Exit");
        ImageIcon exiti= new ImageIcon("exit.jpg");
        exit.setIcon(exiti);
        exit.setBackground(new Color(3,2,155));
        exit.setForeground(Color.yellow);
        exit.addActionListener(evt);
        
                
        stat = new JButton("Statistics");
        ImageIcon statistics = new ImageIcon("statistics.png");
        stat.setIcon(statistics);
        stat.setBackground(new Color(3,2,155));
         stat.setForeground(Color.yellow);
         stat.addActionListener(evt);
        
        buttons.add(newGame);
        buttons.add(exit);
        buttons.add(stat);
        
        add(buttons,BorderLayout.SOUTH);
        
    }
    private class ButtonClickHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            JButton button = (JButton) e.getSource();
            
            switch (button.getText()) {
                case "Exit":
                    System.exit(0);
                case "New Game":
                    TicTacToeGame game = new TicTacToeGame();
                    break;
                case "Statistics":
                    JFrame f = new JFrame();
                    f.setVisible(true);    
                    f.setSize(500, 500);
                    break;
            }
           
        }
        
    }
    
    
}
