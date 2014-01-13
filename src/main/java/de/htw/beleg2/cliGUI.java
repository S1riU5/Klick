package de.htw.beleg2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//sebsch use the Gameclass!

/**
 * 
 * @author simon GUI Class
 * 
 */
public class CliGui extends JFrame {

    private static final long serialVersionUID = -1286802610738061627L;

    /**
     * Mainloop for the GUI
     */
    private Game game = new Game(4, 4, 2);
    private JButton[][] boards;

    public void runGUI() {
        runloop();

    }

    /**
     * runloop method
     */
    public void runloop() {
        menu();
    }

    /**
     * Window method combines the menu and board
     */

    public void HightWidthselection() {
        // define window
        JFrame hws = new JFrame("Auswahl Feldgrï¿½ï¿½e");

        // define Closeoperation
        hws.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        JTextField highttext = new JTextField();
        JTextField widthtext = new JTextField();

        // create JLables for internal Panel
        JLabel hight = new JLabel("Höhe");
        JLabel width = new JLabel("Breite");

        // set size of textfield
        highttext.setPreferredSize(new Dimension(100, 20));
        widthtext.setPreferredSize(new Dimension(100, 20));

        // add Lables to internal Panel and add Textfield to internal Panal
        internSel.add(hight);
        internSel.add(highttext);
        internSel.add(width);
        internSel.add(widthtext);

        // insert internal panale to covering panel
        selection.add(internSel, BorderLayout.CENTER);

        // add panels to frame
        hws.add(selection);

        // set size of windwo
        hws.setSize(410, 100);

        // set windwo visible
        hws.setVisible(true);

    }

    public void menu() {
        // Definiere Fenster
        final JFrame frameMenu = new JFrame("Klickibunti Menu");

        // schliï¿½e fenster
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set Layout window
        frameMenu.setLayout(new BoxLayout(frameMenu.getContentPane(), BoxLayout.Y_AXIS));

        // Set menu Buttons
        JButton Colors = new JButton("Farben");
        JButton Board = new JButton("Spielfeld");
        JButton Play = new JButton("Spiele");
        JButton Close = new JButton("Schließen");

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

        // add Action Listener

    }

    public void colorSelection() {
        // Definiere Fenster
        final JFrame frameMenu = new JFrame("Farbenanzahl");

        // schliï¿½e fenster
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set Layout window
        frameMenu.setLayout(new BoxLayout(frameMenu.getContentPane(), BoxLayout.Y_AXIS));

        // Set menu Buttons
        JButton tow = new JButton("2 Farben");
        JButton three = new JButton("3 Farben");
        JButton four = new JButton("4 Farben");
        JButton five = new JButton("5 Farben");

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
    }

    /**
     * ButtonBoard creat's the GUI Board
     * 
     * @param hight
     * @param width
     * @param boardUI
     */

    public void buttonBoard() {
        final int height = game.getBoardHeight();
        final int width = game.getBoardWidth();

        JFrame boardFrame = new JFrame("Klickibunti");

        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        boardFrame.setSize(410, 200);
        boardFrame.setVisible(true);

    }

    private void rebuild() {

        for (int i = 0; i < game.getBoardHeight(); i++) {

            for (int j = 0; j < game.getBoardWidth(); j++) {
                // Farben: Blau(24,0,243);Rot(255,17,0);
                // gelb(255,249.43);Lila(247,16,217);grï¿½n(7,249,55);
                // Schwarz(0,0,0)

                JButton button = boards[i][j];
                button.setEnabled(true);

                switch (game.getValue(i, j)) {
                    case 0:
                        button.setBackground(Color.BLACK);
                        button.setEnabled(false);
                        break;
                    case 1:
                        button.setBackground(new Color(24, 0, 243));
                        break;
                    case 2:
                        button.setBackground(new Color(255, 249, 43));
                        break;
                    case 3:
                        button.setBackground(new Color(7, 249, 55));
                        break;
                    case 4:
                        button.setBackground(new Color(255, 17, 0));
                        break;
                    case 5:
                        button.setBackground(new Color(255, 17, 0));
                        break;
                }
            }
        }
    }
}
