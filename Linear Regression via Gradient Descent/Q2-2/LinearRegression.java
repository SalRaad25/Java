/**
 * The class  <b>LinearRegression</b> implements gradient
 * descent for multiple variables
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class LinearRegression{


	/**
	 * Number of features (usually "n" in litterature)
	 */
	private int nbreOfFeatures;

	/**
	 * Number of samples (usually "m" in litterature)
	 */
	private int nbreOfSamples;


	/**
	 * the nbreOfFeatures X nbreOfSamples Matrix of samples
	 */
	private double[][] samplesMatrix;

	/**
	 * the nbreOfSamples Matrix of samples target values
	 */
	private double[] samplesValues;

	/**
	 * the nbreOfFeatures Matrix theta of current hypthesis function
	 */
	private double[] theta;


	/**
	 * number of samples received so far
	 */
	private int currentNbreOfSamples;

	/**
	 * a place holder for theta during descent calculation
	 */
	private double[] tempTheta;


	/**
	 * counts how many iterations have been performed
	 */
	private int iteration;


	/**
	 * Object's contructor. The constructor initializes the instance
	 * variables. The starting hypthesis is theta[i]=0.0 for all i
	 *
	 * @param n the number of features that we will have
	 * @param m the number of samples that we will have
	 *
	 */
	public LinearRegression(int n, int m){
		nbreOfFeatures = n;
		nbreOfSamples = m;
		samplesMatrix = new double[m][n];
		samplesValues = new double[m];
		theta = new double[n+1];
		iteration = 0;
		currentNbreOfSamples = 0;
		tempTheta = new double[n+1];
	}

	/**
	 * Add a new sample to samplesMatrix and samplesValues
	 *
	 * @param x the vectors of samples
	 * @param y the coresponding expected value
	 *
	 */
	public void addSample(double[] x, double y){
		samplesMatrix[currentNbreOfSamples] = x;
		samplesValues[currentNbreOfSamples] = y;
		currentNbreOfSamples++;
	}

	/**
	 * Returns the current hypothesis for the value of the input
	 * @param x the input vector for which we want the current hypothesis
	 *
	 * @return h(x)
	 */

	private double hypothesis(double[] x){
		double hypothesis = theta[0];
		for (int i = 1; i < theta.length; i++) {
			hypothesis += theta[i]*x[i-1];
		}
		return hypothesis;
	}

	/**
	 * Returns a string representation of hypthesis function
	 *
	 * @return the string "theta0 x_0 + theta1 x_1 + .. thetan x_n"
	 */

	public String currentHypothesis(){
		String hypothesis = Double.toString(theta[0]);
		for(int i = 1; i < theta.length; i++) {
			hypothesis += " + " + theta[i] + "x" + i;
		}
		return hypothesis;
	}

	/**
	 * Returns the current cost
	 *
	 * @return the current value of the cost function
	 */

	public double currentCost(){
		double cost = 0;
		for (int i = 0; i < nbreOfSamples; i++){
			cost += Math.pow((hypothesis(samplesMatrix[i]) - samplesValues[i]),2);
		}
		return cost/nbreOfSamples;
	}

	/**
	 * runs several iterations of the gradient descent algorithm
	 *
	 * @param alpha the learning rate
	 *
	 * @param numberOfSteps how many iteration of the algorithm to run
	 */

	public void gradientDescent(double alpha, int numberOfSteps) {
		tempTheta = new double[theta.length];
		for (int i = 0; i< numberOfSteps; i ++) {
			for (int j = 0; j < theta.length; j++) {
				double sum = 0;
				for (int k = 0; k < nbreOfSamples; k++) {
					if (j == 0) {
						sum += (hypothesis(samplesMatrix[k]) - samplesValues[k]);
					} else {
						sum += (hypothesis(samplesMatrix[k]) - samplesValues[k]) * samplesMatrix[k][j - 1];
					}
				}
				tempTheta[j] = (theta[j] - alpha * (2.0 / nbreOfSamples) * sum);
			}
			iteration++;
			for (int j = 0; j < theta.length; j++) {
				theta[j] = tempTheta[j];
			}
		}
	}


	/**
	 * Getter for theta
	 *
	 * @return theta
	 */

	public double[] getTheta(){
		return theta;
	}

	/**
	 * Getter for iteration
	 *
	 * @return iteration
	 */

	public int getIteration() {
		return iteration;
	}
}