/**
 * The class  <b>LinearRegression</b> implements gradient
 * descent with 1 variable.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class LinearRegression {


	/**
	 * Number of samples (usually "m" in litterature)
	 */
	private int nbreOfSamples;


	/**
	 * the sample vector
	 */
	private double[] samples;

	/**
	 * the samples target values
	 */
	private double[] samplesValues;

	/**
	 * the current hypthesis function: theta0 + theta1 x
	 */
	private double theta0, theta1;


	/**
	 * used to ensure that the object is ready
	 */
	private int currentNbreOfSamples;



	/**
	 * counts how many iterations have been performed
	 */
	private int iteration;


	/**
	 * Object's contructor. The constructor initializes the instance
	 * variables. The starting hypthesis is y = 0;
	 *
	 *
	 * @param m the number of samples that we will have
	 *
	 */
	public LinearRegression(int m){

		// your code goes there
		nbreOfSamples = m;
		currentNbreOfSamples = 0;
		samples = new double[m];
		samplesValues = new double[m];
		iteration = 0;
	}

	/**
	 * Adds a new sample to sample and to samplesValues. This
	 * method must be iteratively called with all the samples
	 * before the gradient descent can be started
	 *
	 * @param x the new sample
	 * @param y the corresponding expected value
	 *
	 */
	public void addSample(double x, double y){
		samples[currentNbreOfSamples] = x;
		samplesValues[currentNbreOfSamples] = y;
		currentNbreOfSamples++;
	}

	/**
	 * Returns the current hypothesis for the value of the input
	 * @param x the input for which we want the current hypothesis
	 *
	 * @return theta0 + theta1 x
	 */
	private double hypothesis(double x){
		return theta0 + theta1 * x;
	}

	/**
	 * Returns a string representation of hypthesis function
	 *
	 * @return the string "theta0 + theta1 x"
	 */
	public String currentHypothesis(){
		return theta0 + "+" + theta1 + "x";
	}

	/**
	 * Returns the current cost
	 *
	 * @return the current value of the cost function
	 */
	public double currentCost(){
		// your code goes there
		double cost = 0;
		for (int i = 0; i < nbreOfSamples; i++){
			cost += Math.pow((hypothesis(samples[i]) - samplesValues[i]),2);
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
		// your code goes there
		for (int i = 0; i < numberOfSteps; i++) {
			double sum0 = 0;
			double sum1 = 0;
			for (int j = 0; j < nbreOfSamples; j++) {
				sum0 += (hypothesis(samples[j]) - samplesValues[j]);
				sum1 += (hypothesis(samples[j]) - samplesValues[j]) * samples[j];
			}
			theta0 = theta0 - alpha * (2.0 / nbreOfSamples) * sum0;
			theta1 = theta1 - alpha * (2.0 / nbreOfSamples) * sum1;
			iteration++;
		}
	}



	/**
	 * Getter for theta0
	 *
	 * @return theta0
	 */

	public double getTheta0(){
		// your code goes there
		return theta0;
	}

	/**
	 * Getter for theta1
	 *
	 * @return theta1
	 */

	public double getTheta1(){
		// your code goes there
		return theta1;
	}

	/**
	 * Getter for samples
	 *
	 * @return samples
	 */

	public double[] getSamples(){
		// your code goes there
		return samples;
	}

	/**
	 * Getter for getSamplesValues
	 *
	 * @return getSamplesValues
	 */

	public double[] getSamplesValues(){
		// your code goes there
		return samplesValues;
	}

	/**
	 * Getter for iteration
	 *
	 * @return iteration
	 */

	public int getIteration(){
		// your code goes there
		return iteration;
	}
}
