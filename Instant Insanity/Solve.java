public class Solve {

    private static Cube[] cubes = {
            new Cube(new Cube.Color[]{Cube.Color.BLUE, Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.GREEN, Cube.Color.BLUE, Cube.Color.RED}),
            new Cube(new Cube.Color[]{Cube.Color.WHITE, Cube.Color.GREEN, Cube.Color.BLUE, Cube.Color.WHITE, Cube.Color.RED, Cube.Color.RED}),
            new Cube(new Cube.Color[]{Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.RED, Cube.Color.BLUE, Cube.Color.RED, Cube.Color.RED}),
            new Cube(new Cube.Color[]{Cube.Color.BLUE, Cube.Color.RED, Cube.Color.GREEN, Cube.Color.GREEN, Cube.Color.WHITE, Cube.Color.WHITE})};

    private static int numberOfCalls;

    public static void main(String[] args) {
        StudentInfo.display();

        numberOfCalls = 0;
        long start, stop;

        System.out.println("generateAndTest:");
        start = System.currentTimeMillis();

        generateAndTest();

        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop-start) + " milliseconds");

        System.out.println("breadthFirstSearch:");
        start = System.currentTimeMillis();

        breadthFirstSearch();

        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop-start) + " milliseconds");



    }

    public static Queue<Solution> generateAndTest() {
        Queue<Solution> solutionQueue = new ArrayQueue<>();
        Solution temp1 = new Solution(null, cubes[0]);
        Cube[] cube = new Cube[cubes.length];
        System.arraycopy(cubes, 0, cube, 0, cubes.length);
        resetNumberOfCalls();
        for(int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                Solution temp2 = new Solution(temp1, cube[1]);
                for (int k = 0; k < 24; k++) {
                    Solution temp3 = new Solution(temp2, cube[2]);
                    for (int l = 0; l < 24; l++) {
                        if (temp3.isValid(cube[3]) == true) {
                            Solution temp4 = new Solution(temp3, cube[3]);
                            solutionQueue.enqueue(temp4);
                            }
                            numberOfCalls++;
                            if (cube[3].hasNext() == true) {
                                cube[3].next();
                            }
                    }
                    if (cube[2].hasNext() == true) {
                        cube[2].next();
                    }
                }
            }
            if (cube[1].hasNext() == true) {
                cube[1].next();
            }
        }
        if (cube[0].hasNext() == true) {
            cube[0].next();
        }
        System.out.println(getNumberOfCalls());
        return solutionQueue;
    }

    public static Queue<Solution> breadthFirstSearch() {
        Queue<Solution> solutionQueue = new ArrayQueue<>();
        Queue<Solution> open = new ArrayQueue<>();
        open.enqueue(new Solution(null, cubes[0]));
        Solution current;
        resetNumberOfCalls();
        while (open.isEmpty() == false) {
            current = open.dequeue();
            if (current.isValid(cubes[current.size()]) == true && current.size() < 4) { numberOfCalls++; open.enqueue(new Solution(current, cubes[current.size()])); }
            else if (current.isValid(cubes[current.size()]) == true && current.size() == 4) { numberOfCalls++; solutionQueue.enqueue(new Solution(current, cubes[current.size()])); }
            if (current.getCube(current.size() -1).hasNext()) {
                current.getCube(current.size() -1).next();
                if (current.isValid() == true && current.size() < 4) {  open.enqueue(current); }
                else if (current.isValid() == true && current.size() == 4) {  solutionQueue.enqueue(current); }
            }
            numberOfCalls++;
        }
        System.out.println(getNumberOfCalls());
        return solutionQueue;
    }

    public static int getNumberOfCalls(){
        return numberOfCalls;
    }

    public static void resetNumberOfCalls(){
        numberOfCalls = 0;
    }

}
