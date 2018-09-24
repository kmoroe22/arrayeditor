import java.util.Objects;

public class ArrayElement {

    private int value;
    private Point point;
    private ArrayElement left;
    private ArrayElement right;
    private ArrayElement up;
    private ArrayElement down;

    public ArrayElement(int value, int x, int y) {
        this.value = value;
        this.point = new Point(x, y);
    }

    public int getValue() {
        return value;
    }

    public Point getPoint() {
        return point;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public void setRight(ArrayElement right) {
        this.right = right;
    }

    public void setLeft(ArrayElement left) {
        this.left = left;
    }

    public void setDown(ArrayElement down) {
        this.down = down;
    }

    public void setUp(ArrayElement up) {
        this.up = up;
    }

    public void linkLeft(ArrayElement left) {
        this.setLeft(left);
        left.setRight(this);
    }

    public void linkUp(ArrayElement up) {
        this.setUp(up);
        up.setDown(this);
    }

    public ArrayElement getLeft() {
        return left;
    }

    public ArrayElement getRight() {
        return right;
    }

    public ArrayElement getUp() {
        return up;
    }

    public ArrayElement getDown() {
        return down;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayElement that = (ArrayElement) o;
        return value == that.value &&
                Objects.equals(point, that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, point);
    }
}
