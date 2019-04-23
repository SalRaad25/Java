import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {
     // ADD YOUR INSTANCE VARIABLES HERE
    private DotButton [][] board;
    private GameModel gameModel;
    private JLabel nbreOfStepsLabel;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {
    // ADD YOU CODE HERE
        this.gameModel = gameModel;
        board = new DotButton[gameModel.getHeigth()][gameModel.getWidth()];
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        setLayout(layout);
        for(int i = 0; i < gameModel.getHeigth(); i++) {
            for(int j = 0; j < gameModel.getWidth(); j++) {
                board[i][j] = new DotButton(i, j, getIcon(i, j));
                board[i][j].addMouseListener(gameController);
                add(board[i][j]);
            }
        }
        JPanel panel = new JPanel();
        FlowLayout botLayout = new FlowLayout();
        botLayout.setVgap(10);
        botLayout.setHgap(10);
        panel.setLayout(botLayout);
        nbreOfStepsLabel = new JLabel("Number of steps: 0");
        panel.add(nbreOfStepsLabel);
        JButton reset = new JButton("reset");
        reset.addActionListener(gameController);
        panel.add(reset);
        JButton quit = new JButton("quit");
        quit.addActionListener(gameController);
        panel.add(quit);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(gameModel.getWidth()*30, gameModel.getHeigth()*28+85);
        setVisible(true);
    }

    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
    // ADD YOU CODE HERE
        for(int i = 0; i < gameModel.getHeigth(); i++) {
            for(int j = 0; j < gameModel.getWidth(); j++) {
                board[i][j].setIconNumber(getIcon(i, j));
            }
        }
        nbreOfStepsLabel.setText("Number of steps: " + gameModel.getNumberOfSteps());
    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
    // ADD YOU CODE HERE
        if (gameModel.get(i, j).isCovered() == true) {
            if (gameModel.get(i, j).isFlagged() == true) {
                return DotButton.FLAGGED;
            }
            return DotButton.COVERED;
        } else {
            if (gameModel.get(i, j).isMined() == true) {
                if (gameModel.get(i, j).hasBeenClicked()) {
                    return DotButton.CLICKED_MINE;
                } else {
                    return DotButton.MINED;
                }
            }
        }
        return gameModel.get(i, j).getNeighbooringMines();
    }
}
