/**
 * The class  <b>Assignment</b> is used to
 * test our LinearRegression class. It uses the
 * provided class <b>Display</b> to show the results
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class Assignment {


	/** 
     * Random generator 
     */
	private static java.util.Random generator = new java.util.Random();

		/** 
     * In this second method, we will select a line at random.
     * 	1) we select a line y = ax + b, with a randomly selected
     * between -100 and +100 and b randomly selected between 
     * -250 and +250
     * 	2) we add 500 samples randomly selected on the line
     * between -100 and +300. For each sample we add a "noise" 
     * randomly selected between -1000 and +1000 (that is, for
     * each randomly selected x, we add the sample (x, ax+b+noise).
     * where "noise" is randomly selected between -1000 and 1000
     *  3) We create an instance of Display
     *  4) we iterate gradient descent (find a number of iterations,
     * a number of updates to the instance of Display, and a 
     * step alpha that seems to work
     */
	private static void randomLine(){
		double a = 100.0 - (generator.nextDouble()*200);
		double b = 250.0 - (generator.nextDouble()*500);
		LinearRegression regression = new LinearRegression(500);
		for (int i = 0; i < 500; i++){
			double x = -100 + (generator.nextDouble()*400);
			double noise = -1000 + (generator.nextDouble()*2000);
			regression.addSample(x, a*x + b + noise);
		}
		Display display = new Display(regression);
		display.setTarget(a, b);
		for(int i = 0; i < 50; i ++){
			regression.gradientDescent(0.00000003,200);
			System.out.println("Current hypothesis: " + regression.currentHypothesis());
			System.out.println("Current cost: " + regression.currentCost());
			System.out.println("Aiming for: " + a + "x + " + b);
			display.update();
		}
	}


	public static void main(String[] args) {

	    StudentInfo.display();

		System.out.println("randomLine");
		randomLine();

	}

}
