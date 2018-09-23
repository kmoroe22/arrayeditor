import java.util.Objects;

public class ArrayElement {

    private int value;

    private Point point;
//    private ArrayElement left;
//    private ArrayElement right;
//    private ArrayElement up;
//    private ArrayElement down;

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
