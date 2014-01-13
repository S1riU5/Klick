package de.htw.beleg2;

/* what is needed?
 * 
 * [ ] Gamearea-Array n = 2
 * 
 * [ ] several fields
 * 
 * [ ] check the neighbors
 * 
 * [ ] close the holes
 * 
 * [ ] UIpackage klickibunti;

 * 
 * [ ] GUI
 * 
 */
public class App {

    /**
     * @author s0543196
     * @author s0537090
     * 
     * @param args
     * 
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-cli")) {
            cliUI ui = new cliUI();
        } else {
            CliGui GUI = new CliGui();
            GUI.colorSelection();
            GUI.buttonBoard();
        }
    }
}
