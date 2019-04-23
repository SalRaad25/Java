public class Cube {
    public enum Color {
        BLUE, GREEN, RED, WHITE;
    }

    private Color[] face;
    private Color[] original;
    private int state;

    public Cube(Color[] face) {
        this.face = new Color[face.length];
        this.original = new Color[face.length];
        System.arraycopy(face, 0, this.face, 0, face.length);
        System.arraycopy(face, 0, original, 0, face.length);
        state = 0;
    }

    public Cube(Cube other) {
        this.face = new Color[face.length];
        this.original = new Color[face.length];
        System.arraycopy(other.face, 0, this.face, 0, face.length);
        System.arraycopy(other.face, 0, original, 0, face.length);
        state = other.state;
    }

    public void next() throws IllegalStateException {
        if (state < 24) {
            if (state == 0) {
                state++;
            } else if (state % 4 != 0) {
                rotate();
            } else if (state <= 8 || state == 20) {
                rightRoll();
            } else {
                leftRoll();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean hasNext() {
        return state < 24;
    }

    public void reset() {
        System.arraycopy(original, 0, face, 0, face.length);
        state = 0;
    }

    public Cube copy(){
        return new Cube(this);
    }

    public Color getUp(){
        return face[0];
    }

    public Color getFront(){
        return face[1];
    }

    public Color getRight(){
        return face[2];
    }

    public Color getBack() {
        return face[3];
    }

    public Color getLeft(){
        return face[4];
    }

    public Color getDown(){
        return face[5];
    }

    public String toString(){
        return "[" + getUp() + ", " + getFront() + ", " + getRight() + ", " + getBack() + ", " + getLeft() + ", " + getDown() + "]";
    }

    private void rotate() {
        Color[] temp = {face[0], face[4], face[1], face[2], face[3], face[5]};
        System.arraycopy(temp, 0, face, 0, face.length);
        state++;
    }

    private void rightRoll(){
        Color[] temp = {face[4], face[1], face[0], face[3], face[5], face[2]};
        System.arraycopy(temp, 0, face, 0, face.length);
        state++;
    }

    private void leftRoll(){
        Color[] temp = {face[2], face[1], face[5], face[3], face[0], face[4]};
        System.arraycopy(temp, 0, face, 0, face.length);
        state++;
    }
}
