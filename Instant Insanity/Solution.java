public class Solution {
    private Cube[] cubes;

    public Solution(Cube[] cubes) {
        this.cubes = new Cube[cubes.length];
        System.arraycopy(cubes, 0, this.cubes, 0, cubes.length);
    }

    public Solution(Solution other, Cube c) {
        if (other != null) {
            this.cubes = new Cube[other.cubes.length + 1];
            System.arraycopy(other.cubes, 0, this.cubes, 0, other.cubes.length);
            cubes[cubes.length - 1] = c;
        } else {
            this.cubes = new Cube[]{c};
        }
    }

    public int size(){
        return cubes.length;
    }

    public Cube getCube(int pos){
        return cubes[pos];
    }

    public boolean isValid(){
        for (int i = 0; i < cubes.length - 1; i++) {
            for (int j = i+1; j < cubes.length; j++) {
                if (cubes[i].getFront() == cubes[j].getFront() || cubes[i].getRight() == cubes[j].getRight() ||
                        cubes[i].getBack() == cubes[j].getBack() || cubes[i].getLeft() == cubes[j].getLeft()) {
                    return false;
                }
            }
        }
        return true;
    }

    public  boolean isValid(Cube next){
        Solution temp = new Solution(this, next);
        return temp.isValid();
    }

    public String toString() {
        String string = "[";
        for (int i = 0; i < cubes.length; i++) {
            if (i != cubes.length - 1) {
                string += cubes[i].toString() + ", ";
            } else {
                string += cubes[i].toString();
            }
        }
        string += "]";
        return string;
    }

}
