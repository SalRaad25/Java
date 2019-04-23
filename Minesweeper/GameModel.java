import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {
     // ADD YOUR INSTANCE VARIABLES HERE
    private Random generator;
    private int heigthOfGame;
    private DotInfo[][] model;
    private int numberOfMines;
    private int numberOfSteps;
    private int numberUncovered;
    private int widthOfGame;

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {
    // ADD YOU CODE HERE
        this.numberOfMines = numberOfMines;
        heigthOfGame = heigth;
        widthOfGame = width;
        numberOfSteps = 0;
        numberUncovered = 0;
        model = new DotInfo[heigthOfGame][widthOfGame];
        for(int i = 0; i < heigth; i++) {
            for(int j = 0; j < width; j++) {
                model[i][j] = new DotInfo(i, j);
            }
        }
        generator = new Random();
        reset();
    }


 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset() {
        numberOfSteps = 0;
        for(int i = 0; i < heigthOfGame; i++) {
            for(int j = 0; j < widthOfGame; j++) {
                model[i][j] = new DotInfo(i, j);
            }
        }
        int x;
        int y;
        for (int i = 0; i < numberOfMines; i++) {
            x = generator.nextInt(heigthOfGame);
            y = generator.nextInt(widthOfGame);
            while (model[x][y].isMined() == true) {
                x = generator.nextInt(heigthOfGame);
                y = generator.nextInt(widthOfGame);
            }
            model[x][y].setMined();
            for (int j = -1; j <= 1; j++) {
                if (x+j != -1 && x+j < heigthOfGame) {
                    for (int k = -1; k <= 1; k++){
                        if(y+k != -1 && y+k < widthOfGame){
                            if(!model[x+j][y+k].isMined()){
                                model[x+j][y+k].setNeighbooringMines(model[x+j][y+k].getNeighbooringMines() + 1);

                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth() {
    // ADD YOU CODE HERE
        return heigthOfGame;
    }

    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){
    // ADD YOU CODE HERE
        return widthOfGame;
    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
    // ADD YOU CODE HERE
        return model[i][j].isMined();
    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
    // ADD YOU CODE HERE
        return model[i][j].hasBeenClicked();
    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
    // ADD YOU CODE HERE
        return model[i][j].getNeighbooringMines() == 0;
    }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){
    // ADD YOU CODE HERE
        return model[i][j].isCovered();
    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
    // ADD YOU CODE HERE
        return model[i][j].getNeighbooringMines();
    }


    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
    // ADD YOU CODE HERE
        model[i][j].uncover();
        numberUncovered++;
    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
    // ADD YOU CODE HERE
        model[i][j].click();
    }
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
    // ADD YOU CODE HERE
        for (int i = 0; i < heigthOfGame; i++){
            for (int j = 0; j < widthOfGame; j++){
                model[i][j].uncover();
            }
        }
    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
    // ADD YOU CODE HERE
        return numberOfSteps;
    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
    // ADD YOU CODE HERE
        return model[i][j];
    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
     public void step(){
    // ADD YOU CODE HERE
         numberOfSteps++;
    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
    // ADD YOU CODE HERE
        return (numberUncovered == widthOfGame*heigthOfGame || numberUncovered == widthOfGame*heigthOfGame - numberOfMines);
    }

    /**
     * The void method <b>flag</b> updates the flagged status.
     *
     * @param i
     *           the x-coordinate of the dot
     * @param j
     *           the y-coordinate of the dot
     */
    public void flag(int i, int j) {
        model[i][j].flagged();
    }

   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
    // ADD YOU CODE HERE
        String string = "Minesweeper";
        for (int i = 0; i < heigthOfGame; i++){
            string += "\n";
            for (int j = 0; j < widthOfGame; j++){
               string += model[i][j].getNeighbooringMines() + " ";
            }
        }
        return string;
    }
}
