package ticTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author A.H.F. Riyafa
 */
public class TicTacToeGame extends JFrame{
    private final static int ROWS = 3;
    private final static int COLUMNS = 3;
    private JButton button[];
    private char sign[][];
    private JPanel buttonsPanel;
    private JButton newButton;
    private JLabel label;
    private PlayersFrame dialog;
    private Font font;
    private String player1,player2,next;
    
    public TicTacToeGame(){
        // set frame's title
        setTitle("Tic Tac Toe");
        
        /*
         * create an array to keep track of
         * the players
         */
        sign = new char[ ROWS ][ COLUMNS ];
        
        //create new font
        font=new Font("AGaramondPro-Bold",Font.BOLD,36);
        
        dialog=new PlayersFrame(this);
        // create a JButton object named newutton
        newButton = new JButton("New Game");
        newButton.setFont(font);
        newButton.setBackground(Color.BLACK);
        newButton.setForeground(Color.white);
        newButton.addActionListener(evt->{            
            resetGame();
        });
        add(newButton,BorderLayout.NORTH);
        
        // create a panel as a container for butons
        buttonsPanel = new JPanel();
        // set panel's layout manager
        buttonsPanel.setLayout( new GridLayout( ROWS , COLUMNS , 1 , 1 ) );
        
        // create an array of JButtons
        button = new JButton[ ROWS * COLUMNS ];
        
        // initialize each button and add it to buttonsPanel
        for ( int i = 0 ; i < ROWS * COLUMNS ; i++ ) {
               button[ i ] = new JButton();
               button[ i ].setFocusPainted( false );
               button[ i ].setActionCommand( Integer.toString( i ) );
               button[ i ].setFont(font);
               button[i].setBackground(Color.BLUE);
               button[i].setForeground(Color.WHITE);
               button[ i ].setPreferredSize( new Dimension( 50 , 50 ) );
               button[ i ].setToolTipText( "Click to make your move" );
               button[ i ].addActionListener( new ButtonClickHandler() );
               buttonsPanel.add( button[ i ] );
        }
        
        // add buttonsPanel to frame
        add(buttonsPanel);
        
        //label to display results
        label=new JLabel();
        label.setFont(font);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.MAGENTA);
        label.setText("Tic Tac Toe");
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label,BorderLayout.SOUTH);
        
        // set some of JFrame's propeties
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // don't allow user to resize the Jframe
        setResizable( false );
        
        // resize frame to a suitable size
        setSize(500,500);

        // don't allow user to resize the game window
        setVisible( true );

        resetGame();
    }
    private void setButtonsEnabled(String player,boolean enabled) {
            for ( int i = 0 ; i < ROWS * COLUMNS ; i++ ) {
                button[i].setEnabled(enabled);               
            }
            if (!player.equals("tie")) {
                label.setText(player+" has won");
            }
        }
        private void setButtonsEnabled(String player) {
            setButtonsEnabled(player,false);
        }

    private void resetGame() {
        dialog.createAndShowGUI();
        
        player1=dialog.getPlayer1();
        player2=dialog.getPlayer2();
        setButtonsEnabled("tie", true);
        sign = new char[ROWS][COLUMNS];
        
        for ( int i = 0 ; i < ROWS * COLUMNS ; i++ )
            button[i].setText("");
        if (player2.equals("Computer")) {
             int answer = JOptionPane.showConfirmDialog( null , "Begin with the computer?",
                                                    "Tic Tac Toe:New Game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if ( answer == JOptionPane.YES_OPTION )
                {
                    Random rnd = new Random();
                    int rndNo;
                    rndNo =  rnd.nextInt(ROWS*COLUMNS);
                    button[rndNo].setText( "O" );
                    sign[ rndNo / ROWS ][ rndNo % COLUMNS ] = 'O';
                }
            next=player1;
        }            
        next=player1;
        label.setText(player1+"'s turn");
         for ( int i = 0 ; i < ROWS * COLUMNS ; i++ ) {
             button[i].setBackground(Color.BLUE);
         }
    }

    private class ButtonClickHandler implements ActionListener {
        private int i,j;
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            int row = Integer.parseInt( button.getActionCommand() ) / ROWS;
            int col = Integer.parseInt( button.getActionCommand() ) % COLUMNS;

            // is the square that user has clicked is occupied?
            if ( isOccupied( row , col ) == true  )
            {
                Toolkit.getDefaultToolkit().beep();
                label.setText("The cell is already occupied");
            }
            else
            {
                if (next.equals(player1)) {
                    // the square is not occupied so put
                    // an X on it.
                    button.setText( "X" );
                                      
                    // mark that square as occupied
                    sign[ row ][ col ] = 'X';
                    //next is player2's turn
                    next=player2;
                    label.setText(player2+"'s turn");
                
                    // check whether player1 has won the game
                    if ( isWinner( 'X' ) == true )
                    {
                         setButtonsEnabled(player1);                                                
                         return;
                    }else if (player2.equals("Computer")) {
                        // player1 has not won the game so it's computer
                        // turn to make a move.
                        computerMove();
                        // has Computer won the game?
                        if ( isWinner( 'O' ) == true )
                        {
                            setButtonsEnabled(player2);                            
                            return;
                        }
                        //next is player1's turn
                         next=player1;
                         label.setText(player1+"'s turn");    
                    }  
                    
                }else{
                        
                             //player 2's turn
                        // the square is not occupied so put
                        // an X on it.
                        button.setText( "O" );

                        // mark that square as occupied
                        sign[ row ][ col ] = 'O';  
                        
                        // has player2 won the game?
                        if ( isWinner( 'O' ) == true )
                        {
                            setButtonsEnabled(player2);                            
                            return;
                        }
                        //next is player1's turn
                         next=player1;
                         label.setText(player1+"'s turn");                    

                    }
                
            }

            /*
             * check whether the game is a tie or not.
             * if it's a tie then reset the game
             */
            if ( isTie() == true )
            {
                setButtonsEnabled("Tie");
                label.setText("It's a tie!");               
            }
            
        }

        private boolean isOccupied(int row, int col) {
            if ( sign[row][col] == 'X' || sign[row][col] == 'O' )
                return true;
            else
                return false;
        }

        private boolean isWinner(char playerSign) {
            byte r,c,rowNo=0,colNo=0;
            byte c1,c2,c3,c4;

            c1 = c2 = c3 =  c4 = 0;

            for ( r = 0 ; r < ROWS ; r++ )
            {
                c1 = c2 = 0;                
                for ( c = 0 ; c < COLUMNS ; c++ )
                {

                    // check horizontally
                    if ( sign[r][c] == playerSign ){
                        rowNo=r;
                        c1++;
                    }                   

                    // check vertically
                    if ( sign[c][r] == playerSign ){
                        colNo=r;
                        c2++;
                    }
                        

                    // check diagonally
                    if ( (r + c) == ROWS - 1 )
                    {
                       if ( sign[r][c] == playerSign )
                           c3++;
                    }

                    if ( r == c )
                    {
                       if ( sign[r][r] == playerSign )
                           c4++;
                    }
                }

                // check whether there is a winner or not
                if ( c1 == ROWS || c2 == COLUMNS || c3 == ROWS || c4 == ROWS ){
                    //colors winning row
                    if (c1==ROWS) {
                        button[rowNo*ROWS].setBackground(Color.BLACK);
                        button[rowNo*ROWS+1].setBackground(Color.BLACK);
                        button[rowNo*ROWS+2].setBackground(Color.BLACK);
                    }else  if (c1==ROWS) {//colors winning column
                        button[colNo].setBackground(Color.BLACK);
                        button[colNo+COLUMNS].setBackground(Color.BLACK);
                        button[colNo+2*COLUMNS].setBackground(Color.BLACK);                       
                    }else if(c3==ROWS){//colors winning diagonal
                        button[2].setBackground(Color.BLACK);
                        button[4].setBackground(Color.BLACK);
                        button[6].setBackground(Color.BLACK);
                    }else if(c4==ROWS){//colors winning diagonal
                        button[0].setBackground(Color.BLACK);
                        button[4].setBackground(Color.BLACK);
                        button[8].setBackground(Color.BLACK);
                    }
 
                    return true;
                }
                   
            }

            // there is no winner so return false
            return false;
        }

        private void computerMove() {
            // is there a possibility for user to win the game.
            if ( canWin('O','X', ROWS-1 ) == true )
            {
                // there is a possibility for user to win.
                // make a suitable move to prevent user
                // from winning the game.
                button[ this.i * ROWS + this.j ].setText("O");
                sign[this.i][this.j] = 'O';

            }
            else if ( canWin('X','O', ROWS-1 ) == true )
                {
                    // there is a possibility for computer to win.
                    // so make a suitable move to win the game.
                    button[this.i * ROWS + this.j ].setText("O");
                    sign[this.i][this.j] = 'O';
                }
            else
            {
                // neither user nor computer can win the game.
                // make a sutable move.
                for ( int r = 0 ; r < (ROWS*COLUMNS) ; r++ )
                        if ( !isOccupied( r / ROWS, r % COLUMNS ) == true )
                        {
                            button[ r ].setText("O");
                            sign[r/ROWS][r%COLUMNS]='O';
                            break;
                        }

            }
        }        
        private boolean isTie() {
            boolean tie = true;

            /* loop through all array elements and check
             * whether there is an empty element or not.
             * if there is at least one empty element then
             * the game is not a tie.
             */
            for ( int r = 0 ; r < ROWS ; r++)
            {
                for ( int c = 0 ; c < COLUMNS ; c++)
                {
                    if ( sign[r][c] != 'O' && sign[r][c]!= 'X' )
                    {
                        tie = false;
                    }
                }
            }
            
            return tie;
        }

        private boolean canWin(char playerSign, char opponentSign, int count) {
            byte r,c;
            byte c1,c2,c3,c4;
            byte i1,i2,i3,i4;
            byte j1,j2,j3,j4;

            i1 = i2 = i3 = i4 = -1;
            j1 = j2 = j3 = j4 = -1;
            c1 = c2 = c3 = c4 = 0;
            
            for ( r = 0 ; r < ROWS ; r++ )
            {
                c1 = c2 = 0;
                i1 = i2 = i3 = i4 = -1;
                j1 = j2 = j3 = j4 = -1;
                for ( c = 0 ; c < COLUMNS ; c++ )
                {
                    // check horizontal possibility
                    if ( sign[r][c] == playerSign )
                        c1++;
                    else
                    {
                        if ( sign[r][c] != opponentSign )
                        {
                            i1 = r;
                            j1 = c;
                        }
                    }

                    // check vertical possibility
                    if ( sign[c][r] == playerSign )
                        c2++;
                    else
                    {
                        if ( sign[c][r] != opponentSign )
                        {
                            i2 = c;
                            j2 = r;
                        }
                    }

                    // check diagonal possibilities
                    if ( r == c )
                    {
                        if ( sign[r][c] == playerSign )
                            c3++;
                        else
                        {
                            if ( sign[r][c] != opponentSign )
                            {
                                i3 = r;
                                j3 = c;
                            }
                        }
                    }
                    
                    if ( (r+c) == ROWS - 1 )
                    {
                        if ( sign[r][c] == playerSign )
                            c4++;
                        else
                        {
                            if ( sign[r][c] != opponentSign )
                            {
                                i4 = r;
                                j4 = c;
                            }
                        }
                    }

                    // check whether there is a possibility to win or not
                    if ( c1 == count && i1 != -1 && j1 != -1 )
                    {
                        this.i = i1;
                        this.j = j1;
                        return true;
                    }
                    else if ( c2 == count && i2 != -1 && j2 != -1 )
                    {
                        this.i = i2;
                        this.j = j2;
                        return true;
                    }
                    else if ( c3 == count && i3 != -1 && j3 != -1 )
                    {
                        this.i = i3;
                        this.j = j3;
                        return true;
                    }
                    else if ( c4 == count && i4 != -1 && j4 != -1 )
                    {
                        this.i = i4;
                        this.j = j4;
                        return true;
                    }
                }
            }

            // there is no possibility to win
            return false;
        }
    }
        
}
    
    

