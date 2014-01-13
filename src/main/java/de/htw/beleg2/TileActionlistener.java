package de.htw.beleg2;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * TODO: javadoc
 * 
 * @author simon
 */
public class TileActionlistener implements ActionListener {

    private int choordX;
    private int choordY;

    private Runnable callback;
    private final Game game;

    /**
     * Constructor.
     * 
     * @param choordX the x coordinate of the button
     * @param choordY the y coordinate of the button
     * @param game the game class which holds the playground logic
     * @param callback a callback that may be set, to notify e.g a gui class to rebuild the view
     */
    public TileActionlistener(int choordX, int choordY, final Game game, Runnable callback) {

        this.choordX = choordX;
        this.choordY = choordY;

        this.game = game;
        this.callback = callback;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent arg0) {

        game.delete(choordX, choordY);
        game.cleanBoard();

        if (callback != null) {
            callback.run();
        }
    }

}
