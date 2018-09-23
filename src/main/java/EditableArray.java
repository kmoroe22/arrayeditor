import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EditableArray {

    private ArrayElement[][] sourceArray;
    //SortedMap
    private Map<Integer, List<ArrayElement>> elementsByValue;

    public EditableArray(int[][] original) {
        assert original.length > 0;
        for (int[] yAxisArray : original) {
            assert yAxisArray.length > 0;
        }
        sourceArray = new ArrayElement[original.length][];
        elementsByValue = new HashMap<>();
        for (int i = 0; i < original.length; i++) {
            sourceArray[i] = new ArrayElement[original[i].length];
            for (int j = 0; j < original[i].length; j++) {
                ArrayElement arrayElement = new ArrayElement(original[i][j], i, j);
                sourceArray[i][j] = arrayElement;

            }
        }
    }

    public void setValue(int value, int x, int y) throws PointIsNotWithinArrayException {
        if (!isWithinArray(x, y)) {
            throw new PointIsNotWithinArrayException();
        }
        ArrayElement arrayElement = sourceArray[x][y];
        elementsByValue.get(arrayElement.getValue()).removeIf(a -> a.equals(arrayElement));
        arrayElement.setValue(value);
        elementsByValue.computeIfAbsent(arrayElement.getValue(), integer -> new LinkedList<>()).add(arrayElement);
    }

    public List<ArrayElement> findByValue(int value) {
        List<ArrayElement> values = elementsByValue.getOrDefault(value, new LinkedList<>());
        return new LinkedList<>(values);
    }

    public int valueAt(int x, int y) throws PointIsNotWithinArrayException {
        if (!isWithinArray(x, y)) {
            throw new PointIsNotWithinArrayException();
        }
        return sourceArray[x][y].getValue();
    }

    public int valueAt(Point point) throws PointIsNotWithinArrayException {
        return valueAt(point.getX(), point.getY());
    }

    public void setValue(int value, Point point) throws PointIsNotWithinArrayException {
        setValue(value, point.getX(), point.getY());
    }

    private boolean isWithinArray(int x, int y) {
        if (x >= sourceArray.length || x < 0) {
            return false;
        }
        if (y >= sourceArray[x].length || y < 0) {
            return false;
        }
        return true;
    }

    private boolean isWithinArray(Point point) {
        if (point.getX() >= sourceArray.length || point.getX() < 0) {
            return false;
        }
        if (point.getY() >= sourceArray[point.getX()].length || point.getY() < 0) {
            return false;
        }
        return true;
    }

    public void crop(Point start, Point end) throws PointIsNotWithinArrayException {
        if (!isWithinArray(start) || !isWithinArray(end)) {
            throw new PointIsNotWithinArrayException();
        }
        if (end.getX() < start.getX()) {
            throw new IllegalArgumentException("End x location cannot exceed start x location");
        }

        if (end.getY() < start.getY()) {
            throw new IllegalArgumentException("End y location cannot exceed start y location");
        }

        ArrayElement[][] newArray = new ArrayElement[end.getX() - start.getX() + 1][];
        elementsByValue.clear();
        for (int i = start.getX(); i <= end.getX(); i++) {
            int newArrayX = i - start.getX();
            newArray[newArrayX] = new ArrayElement[end.getY() - start.getY() + 1];
            for (int j = start.getY(); j <= end.getY(); j++) {
                int newArrayY = j - start.getY();
                ArrayElement arrayElement = sourceArray[i][j];
                newArray[newArrayX][newArrayY] = arrayElement;
                elementsByValue.computeIfAbsent(arrayElement.getValue(), integer -> new LinkedList<>()).add(arrayElement);
            }
        }
        this.sourceArray = newArray;
    }

    //    public Optional<Integer> getLeftNeighbour(int x, int y) {
//        try {
//            return Optional.of(valueAt(x - 1, y));
//        } catch (PointIsNotWithinArrayException e) {
//            return Optional.empty();
//        }
//    }
//
//    public Optional<Integer> getRightNeighbour(int x, int y) {
//        try {
//            return Optional.of(valueAt(x + 1, y));
//        } catch (PointIsNotWithinArrayException e) {
//            return Optional.empty();
//        }
//    }
//
//    public Optional<Integer> getUpperNeighbour(int x, int y) {
//        try {
//            return Optional.of(valueAt(x, y - 1));
//        } catch (PointIsNotWithinArrayException e) {
//            return Optional.empty();
//        }
//    }
//
//    public Optional<Integer> getLowerNeighbour(int x, int y) {
//        try {
//            return Optional.of(valueAt(x, y + 1));
//        } catch (PointIsNotWithinArrayException e) {
//            return Optional.empty();
//        }
//    }

}
