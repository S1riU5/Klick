package de.htw.beleg2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.*;

//sebsch use the Gameclass!

/**
 * 
 * GUI Class
 * 
 */
public class CliGui extends JFrame {

    private static final long serialVersionUID = -1286802610738061627L;

    private JFrame boardFrame;
    private Game game;
    private JButton[][] boards;
    private int height = 16;
    private int width = 16;
    private int numcol = 2;
    private JLabel Score;
    private TopTen scoreboard;
    private JFrame gameover;

    /**
     * konstruktor run the GUI
     */
  
    public CliGui() {
         scoreboard = new TopTen();
        runloop();

    }

    /**
     * runloop method to start menu();
     */
    public void runloop() {
        menu();
    }

    /**
     * menu to select the size of the gameboard
     */

    public void HightWidthselection() {
        // define window
        final JFrame hws = new JFrame("select Fieldsize");

      

        // set Layout of the windwo
        hws.setLayout(new FlowLayout());

        // creat mainpart
        JPanel selection = new JPanel();

        // set Panel Layout
        selection.setLayout(new BorderLayout());

        // create internal Panal
        JPanel internSel = new JPanel();

        // set internal Layout
        internSel.setLayout(new BoxLayout(internSel, BoxLayout.X_AXIS));

        // draw cover for Panel and set Border
        internSel.setBorder(BorderFactory.createTitledBorder("Selection"));

        // create textfields
        final JTextField highttext = new JTextField();
        final JTextField widthtext = new JTextField();

        // create JLables for internal Panel
        JLabel hight = new JLabel("heigtht");
        JLabel width = new JLabel("width");
        JButton OK = new JButton("OK");

        // set size of textfield
        highttext.setPreferredSize(new Dimension(100, 20));
        widthtext.setPreferredSize(new Dimension(100, 20));

        // add Lables to internal Panel and add Textfield to internal Panal
        internSel.add(hight);
        internSel.add(highttext);
        internSel.add(width);
        internSel.add(widthtext);
        internSel.add(OK);

        // insert internal panale to covering panel
        selection.add(internSel, BorderLayout.CENTER);

        // add panels to frame
        hws.add(selection);

        // add actionlistener

        OK.addActionListener(new ActionListener() {
            /**
             * Create performed action 
             * save the parses string ton integer and save integer to variable
             */
            public void actionPerformed(ActionEvent e) {
                String height = highttext.getText();
                String width = widthtext.getText();
                CliGui.this.width = Integer.parseInt(width);
                CliGui.this.height = Integer.parseInt(height);
                hws.setVisible(false);

            }
        });

        // set size of window
        hws.setSize(410, 100);

        // set window visible
        hws.setVisible(true);

    }

    /**
     * Create menu for all settings and to start the game
     */
    public void menu() {
        // Definiere Fenster
        final JFrame frameMenu = new JFrame("Klickibunti Menu");



        // set Layout window
        frameMenu.setLayout(new BoxLayout(frameMenu.getContentPane(), BoxLayout.Y_AXIS));

        // Set menu Buttons
        JButton Highscore = new JButton("Highscore");
        JButton Colors = new JButton("Colors");
        JButton Board = new JButton("Board size");
        JButton Play = new JButton("Play");
        JButton Close = new JButton("Close");

        // set buttonsize
        
        Colors.setMaximumSize(new Dimension(250, 300));
        Board.setMaximumSize(new Dimension(250, 300));
        Play.setMaximumSize(new Dimension(250, 300));
        Close.setMaximumSize(new Dimension(250, 300));

        // add Buttons

       
        frameMenu.add(Colors, Component.CENTER_ALIGNMENT);
        frameMenu.add(Board, Component.CENTER_ALIGNMENT);
        frameMenu.add(Play, Component.CENTER_ALIGNMENT);
        frameMenu.add(Close, Component.CENTER_ALIGNMENT);

        // set window

        frameMenu.setSize(250, 250);

        // Display window
        frameMenu.setVisible(true);

      
        Colors.addActionListener(new ActionListener() {
            /**
             * Create performed action
             * Open Color select menu
             */
            public void actionPerformed(ActionEvent e) {
                colorSelection();

            }
        });

        Board.addActionListener(new ActionListener() {
            /**
             * Create performed action
             * Open select size menu
             */
            public void actionPerformed(ActionEvent e) {
                HightWidthselection();

            }
        });
      
        Play.addActionListener(new ActionListener() {
            
            /**
             * Create performed action
             * Creates the board
             */
            public void actionPerformed(ActionEvent e) {
                buttonBoard();

            }
        });

        Close.addActionListener(new ActionListener() {
            /**
             *  Create performed action
             *  Set the Window Unvisable 
             */
            public void actionPerformed(ActionEvent e) {
                frameMenu.setVisible(false);

            }
        });
    }

    /**
     * Menu to select the Colors
     */

    public void colorSelection() {
        // Definiere Fenster
        final JFrame frameMenu = new JFrame("Farbenanzahl");

       

        // set Layout window
        frameMenu.setLayout(new BoxLayout(frameMenu.getContentPane(), BoxLayout.Y_AXIS));

        // Set menu Buttons
        JButton tow = new JButton("2 Colors");
        JButton three = new JButton("3 Colors");
        JButton four = new JButton("4 Colors");
        JButton five = new JButton("5 Colors");

        // set buttonsize
        tow.setMaximumSize(new Dimension(250, 300));
        three.setMaximumSize(new Dimension(250, 300));
        four.setMaximumSize(new Dimension(250, 300));
        five.setMaximumSize(new Dimension(250, 300));

        // add Buttons

        frameMenu.add(tow, Component.CENTER_ALIGNMENT);
        frameMenu.add(three, Component.CENTER_ALIGNMENT);
        frameMenu.add(four, Component.CENTER_ALIGNMENT);
        frameMenu.add(five, Component.CENTER_ALIGNMENT);

        // set window

        frameMenu.setSize(250, 250);
        // Display window
        frameMenu.setVisible(true);
        // add Action Listener
        tow.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                numcol = 2;
                frameMenu.setVisible(false);

            }
        });
        three.addActionListener(new ActionListener() {
            /**
             * Create performed action
             * save the number of colors
             * set Windowunvisable
             */
            public void actionPerformed(ActionEvent e) {
                numcol = 3;
                frameMenu.setVisible(false);

            }
        });

        four.addActionListener(new ActionListener() {
            /**
             * Create performed action
             * save the number of colors
             * set Window unvisable
             */
            public void actionPerformed(ActionEvent e) {
                numcol = 4;
                frameMenu.setVisible(false);

            }
        });

        five.addActionListener(new ActionListener() {
            /**
             * Create performed action
             * save the number of colors
             * set Window unvisable
             */
            public void actionPerformed(ActionEvent e) {
                numcol = 5;
                frameMenu.setVisible(false);

            }
        });
    }

    /**
     * creates a Board Window
     * 
     */

    public void buttonBoard() {
        game = new Game(height,width, numcol);
        final int height = game.getBoardHeight();
        final int width = game.getBoardWidth();

        boardFrame = new JFrame("Klickibunti");
        JMenuBar Bar = new JMenuBar();
        Score = new JLabel();
        JMenu gamemen = new JMenu("Game");
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem close = new JMenuItem("Close");
        boardFrame.setJMenuBar(Bar);
        Bar.add(gamemen);
        gamemen.add(undo);
        gamemen.add(close);
        Bar.add(Score);
        // add ActionListener
        undo.addActionListener(new ActionListener() {
            /**
             * Create performed action
             * undo last step
             * rebuild window
             */
            public void actionPerformed(ActionEvent e) {
                game.tardis();
                rebuild();

            }
        });

        close.addActionListener(new ActionListener() {
            /**
             * Create performed action
             * set Window unvisable
             */
            public void actionPerformed(ActionEvent e) {
                boardFrame.setVisible(false);

            }
        });

       

        boards = new JButton[height][width];

        // create a callback to be set to every button we instantiate
        Runnable callback = new Runnable() {

            public void run() {
                rebuild();
            }
        };

        boardFrame.setLayout(new GridLayout(height, width));

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {
                JButton button = new JButton("     ");
                boards[i][j] = button;
                boardFrame.add(button);
                boards[i][j].setPreferredSize(new Dimension(60, 40));
                boards[i][j].addActionListener(new TileActionlistener(i, j, game, callback));
            }
        }
        rebuild();

        boardFrame.setSize(800, 700);
        boardFrame.setVisible(true);

    }
    
    /**
     * rebuild the gameboard (button array)
     */

    private void rebuild() {

        boolean finished = false;

        for (int i = 0; i < game.getBoardHeight(); i++) {

            for (int j = 0; j < game.getBoardWidth(); j++) {
                // Farben: Blau(24,0,243);Rot(255,17,0);
                // gelb(255,249.43);Lila(247,16,217);gr�n(7,249,55);
                // Schwarz(0,0,0)

                JButton button = boards[i][j];
                button.setEnabled(true);

                switch (game.getValue(i, j)) {
                    case 0:
                        button.setBackground(Color.BLACK);
                        button.setEnabled(false);
                        break;
                    case 1:
                        button.setBackground(Color.RED);
                        break;
                    case 2:
                        button.setBackground(Color.BLUE);
                        break;
                    case 3:
                        button.setBackground(Color.GREEN);
                        break;
                    case 4:
                        button.setBackground(Color.YELLOW);
                        break;
                    case 5:
                        button.setBackground(Color.MAGENTA);
                        break;
                }

                if (game.status() != 0) {
                    finished = true;
                    break;
                }

            }
            if (finished == true) {
                this.scoreboard.saveHighscore(game.getPoints());
                this.boardFrame.dispose();
                Gameover();
               
                break;

            }
        }
        Score.setText("Score: " + game.getPoints());
    }
/**
 * creat a Windwo  with hightscore
 */
    public void Gameover() {
        gameover = new JFrame();
        gameover.setLayout(new BoxLayout(gameover.getContentPane(), BoxLayout.Y_AXIS));
        String HString = "<HTML><ol>";

        JLabel score = new JLabel();

        JLabel Highscore = new JLabel();
        Highscore.setSize(60, 197);
    
        for (int points : scoreboard.getHighscore()) {
           // HString += ("<b>" + count + ": </b>" + points + "<BR>");
            HString += ("<li>" + points + "</li>");
        }
        
        HString += "</ol></HTML>";
        Highscore.setText(HString);

        score.setText("Score: " + game.getPoints());

        gameover.add(score, Component.CENTER_ALIGNMENT);
        gameover.add(Highscore, Component.LEFT_ALIGNMENT); 
        gameover.setSize(120, 300);
        gameover.setVisible(true);

    }
}
