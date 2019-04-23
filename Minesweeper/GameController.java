import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener, MouseListener {

    // ADD YOUR INSTANCE VARIABLES HERE
    private GameModel gameModel;
    private GameView gameView;


    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int height, int numberOfMines) {
    // ADD YOU CODE HERE
        gameModel = new GameModel(width, height, numberOfMines);
        gameView = new GameView(gameModel, this);
    }

    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
    // ADD YOU CODE HERE
        if (e.getActionCommand().equals("reset") || e.getActionCommand().equals("play again")) {
            reset();
            for (Window window : gameView.getOwnedWindows()) {
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            }
        } else if (e.getActionCommand().equals("quit")) {
            gameView.dispatchEvent(new WindowEvent(gameView, WindowEvent.WINDOW_CLOSING));
        } else if (e.getActionCommand().equals("leftClick") && !gameModel.isFinished()){
            DotButton position = (DotButton) e.getSource();
            if(!gameModel.get(position.getColumn(), position.getRow()).isFlagged()) {
                gameModel.step();
                play(position.getColumn(), position.getRow());
            }
        } else if (e.getActionCommand().equals("rightClick") && !gameModel.isFinished()) {
            DotButton position = (DotButton) e.getSource();
            gameModel.flag(position.getColumn(), position.getRow());
            gameView.update();
        }
    }

    /**
     * resets the game
     */
    private void reset(){
    // ADD YOU CODE HERE
        gameModel.reset();
        gameView.update();
    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){
    // ADD YOU CODE HERE
      DotInfo played = gameModel.get(width, heigth);
      if (!played.hasBeenClicked()) {
          gameModel.uncover(played.getX(),played.getY());
          played.click();
          if(!played.isMined()) {
              if (played.getNeighbooringMines() == 0) {
                  clearZone(played);
              }
          } else {
              gameModel.uncoverAll();
              JOptionPane dialogue = new JOptionPane();
              JButton[] options = new JButton[2];
              options[0] = new JButton("quit");
              options[0].addActionListener(this);
              options[1] = new JButton("play again");
              options[1].addActionListener(this);
              gameView.update();
              dialogue.showOptionDialog(gameView, "Uh oh, you clicked a mine and lost in " + gameModel.getNumberOfSteps() + " steps!", "You Lose", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
          }
          gameView.update();
          if (gameModel.isFinished()) {
              gameModel.uncoverAll();
              gameView.update();
              JOptionPane dialogue = new JOptionPane();
              JButton[] options = new JButton[2];
              options[0] = new JButton("quit");
              options[0].addActionListener(this);
              options[1] = new JButton("play again");
              options[1].addActionListener(this);
              dialogue.showOptionDialog(gameView, "Congratulations, you won in " + gameModel.getNumberOfSteps() + " steps!", "You Win", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
          }
      }
    }

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {
        Stack<DotInfo> dotInfoStack;
        dotInfoStack = new GenericArrayStack<DotInfo>(1);
        dotInfoStack.push(initialDot);
        while (!dotInfoStack.isEmpty()) {
            DotInfo topDot = dotInfoStack.pop();
            for (int i = -1; i <= 1; i++) {
                if (topDot.getX()+i != -1 && topDot.getX()+i < gameModel.getHeigth()) {
                    for (int j = -1; j <= 1; j++) {
                        if (topDot.getY()+j != -1 && topDot.getY()+j < gameModel.getWidth()) {
                            play(topDot.getX()+i,topDot.getY()+j);
                        }
                    }
                }
            }
        }
    }

    /**
     * The void method <b>mouseClicked</b> is used to differentiate
     * between left and right mouse clicks.
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            ActionEvent actionEvent = new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "leftClick");
            actionPerformed(actionEvent);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            ActionEvent actionEvent = new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "rightClick");
            actionPerformed(actionEvent);
        }

    }

    /**
     * Not used
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Not used
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Not used
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Not used
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
