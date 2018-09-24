import java.util.HashSet;
import java.util.Set;

public class Filler {

    private EditableArray array;

    public Filler(EditableArray array) {
        this.array = array;
    }

    public void fill(int newValue, int fromX, int fromY) throws PointIsNotWithinArrayException {
        ArrayElement arrayElement = array.elementAt(fromX, fromY);
        fill(arrayElement, newValue, arrayElement.getValue(), new HashSet<>());
    }

    private void fill(ArrayElement element, double newValue, double oldValue, Set<Point> visited) throws PointIsNotWithinArrayException {
        if (element == null) {
            return;
        }
        array.setValue(newValue, element.getPoint());
        visited.add(element.getPoint());
        fillNeighbour(newValue, oldValue, element.getLeft(), visited);
        fillNeighbour(newValue, oldValue, element.getUp(), visited);
        fillNeighbour(newValue, oldValue, element.getRight(), visited);
        fillNeighbour(newValue, oldValue, element.getDown(), visited);
    }

    private void fillNeighbour(double newValue, double oldValue, ArrayElement arrayElement, Set<Point> visited) throws PointIsNotWithinArrayException {
        if (arrayElement != null && arrayElement.getValue() == oldValue && !visited.contains(arrayElement.getPoint())) {
            fill(arrayElement, newValue, oldValue, visited);
        }
    }

}
