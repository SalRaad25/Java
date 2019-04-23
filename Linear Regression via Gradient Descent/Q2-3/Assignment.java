/**
 * The class  <b>Assignment</b> is used to
 * test our LinearRegression class. 
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
     * In this first method, we are simply using sample points that are
     * on a straight plane. We will use the plane z= x + 2x.
     * In his method, 
     * 	1) we create an instance of LinearRegression.
     * 	2) we add 2,000 samples from the plane z= x + 2x as follows:
     * 		add the sample [(i, 2i), 5i] for 0&lt;=i&lt;=999
     * 		add the sample [(2i, i), 4i] for 0&lt;=i&lt;=999
     *  3) we iterate gradient descent 10,000, printing out the
     * current hypothesis and the current cost every 1,000 
     * iterations, using a step alpha of 0.000000003
     */
	private static void setPlane(){
		LinearRegression regression = new LinearRegression(2,2000);
		for(int i = 0; i < 1000; i++){
			double[] x1 = {i, 2*i};
			regression.addSample(x1, 5*i);
			double[] x2 = {2*i, i};
			regression.addSample(x2, 4*i);
		}
		for(int i = 0; i < 10; i ++){
			System.out.println("Current hypothesis: " + regression.currentHypothesis());
			System.out.println("Current cost: " + regression.currentCost());
			regression.gradientDescent(0.000000003,1000);
		}
	}

	/** 
     * In this second method, we will select a plane at random.
     * 	1) we select a line z = ax + by + c, with a, b and c 
     * randomly selected between -100 and +100 
     * 	2) we add 5000 samples randomly selected on the plane
     * with x and y both randomly selected between 50 and 4000. 
     * For each sample we add a "noise" 
     * randomly selected between -20 and +20 (that is, for
     * each randomly selected x and y we add the sample 
     *[ (x,y), ax+by+c+noise).
     * where "noise" is randomly selected between -20 and 20
     *  4) we iterate gradient descent (find a number of iterations,
     * and a step alpha that seems to work, regularly printing
     * the target,  the current hypothesis and the current cost)
     */

	private static void randomPlane(){
		LinearRegression regression = new LinearRegression(2, 5000);
		double a = 100.0 - (generator.nextDouble()*200);
		double b = 100.0 - (generator.nextDouble()*200);
		double c = 100.0 - (generator.nextDouble()*200);
		for (int i = 0; i < 5000; i++){
			double [] x = {50 + (generator.nextDouble()*3950), 50 + (generator.nextDouble()*3950)};
			double noise = -20 + (generator.nextDouble()*40);
			double x3 = c + a * x[0] + b * x[1] + noise;
			regression.addSample(x, x3);
		}
		for(int i = 0; i < 10; i ++){
			System.out.println("Aiming for: " + c + " + " + a + "x1 + " + b + "x2");
			System.out.println("Current hypothesis: " + regression.currentHypothesis());
			System.out.println("Current cost: " + regression.currentCost());
			regression.gradientDescent(0.000000003,1000);
		}
	}

	/** 
     * In this third method, we will follow the same approach
     * that the one followed in the method  randomPlane, but
     * this time we will have a variable number of dimensions,
     * specified by the parameter "dimension". We will
     * create 5000 samples of "dimension" dimension, where each
     * dimension will be ranmly selected between  -100 and +100,
     * and a randomly selected noise between -20 and +20 will be
     * added to the result.We will then iterate gradient descent 
     * (find a number of iterations,
     * and a step alpha that seems to work, regularly printing
     * the target,  the current hypothesis and the current cost)
     *
     * @param dimension the number of features
     */
	private static void randomDimension(int dimension){
		LinearRegression regression = new LinearRegression(dimension, 5000);
		double [] t = new double[dimension + 1];
		for (int i = 0; i < t.length; i++){
			t[i] = -100 + generator.nextDouble()*200;
		}
		for (int i = 0; i < 5000; i++){
            double [] x = new double[dimension];
		    double r = t[0];
		    for (int j = 0; j < dimension; j++){
		        x[j] = 50 + generator.nextDouble()*3950;
		        r += t[j+1]*x[j];
            }
            regression.addSample(x,r);
        }
        for(int i = 0; i < 10; i ++){
		    String aimedHypothesis = Double.toString(t[0]);
		    for (int j = 1; j < t.length; j++) {
		        aimedHypothesis += " + " + Double.toString(t[j]) + "x" + Integer.toString(j);
            }
            System.out.println("Aiming for: " + aimedHypothesis);
            System.out.println("Current hypothesis: " + regression.currentHypothesis());
            System.out.println("Current cost: " + regression.currentCost());
            regression.gradientDescent(0.000000003,1000);
        }
	}


	public static void main(String[] args) {

		StudentInfo.display();

		System.out.println("randomDimension");
		randomDimension(50);


	}

}
