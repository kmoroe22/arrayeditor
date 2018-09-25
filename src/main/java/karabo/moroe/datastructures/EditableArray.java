package karabo.moroe.datastructures;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class EditableArray {

    private ArrayElement[][] sourceArray;
    private SortedMap<Double, List<ArrayElement>> elementsByValue;

    public EditableArray(double[][] original) {
        assert original.length > 0;
        for (double[] yAxisArray : original) {
            assert yAxisArray.length > 0;
        }
        sourceArray = new ArrayElement[original.length][];
        elementsByValue = new TreeMap<>();
        for (int i = 0; i < original.length; i++) {
            sourceArray[i] = new ArrayElement[original[i].length];
            for (int j = 0; j < original[i].length; j++) {
                ArrayElement arrayElement = new ArrayElement(original[i][j], i, j);
                sourceArray[i][j] = arrayElement;
                elementsByValue.computeIfAbsent(arrayElement.getValue(), integer -> new LinkedList<>()).add(arrayElement);
                if (i > 0) {
                    arrayElement.linkLeft(sourceArray[i - 1][j]);
                }
                if (j > 0) {
                    arrayElement.linkUp(sourceArray[i][j - 1]);
                }
            }
        }
    }

    public void setValue(double value, int x, int y) throws PointIsNotWithinArrayException {
        if (!isWithinArray(x, y)) {
            throw new PointIsNotWithinArrayException();
        }
        ArrayElement arrayElement = sourceArray[x][y];
        elementsByValue.get(arrayElement.getValue()).removeIf(a -> a.equals(arrayElement));
        arrayElement.setValue(value);
        elementsByValue.computeIfAbsent(arrayElement.getValue(), integer -> new LinkedList<>()).add(arrayElement);
    }

    public List<ArrayElement> findByValue(double value) {
        List<ArrayElement> values = elementsByValue.getOrDefault(value, new LinkedList<>());
        return new LinkedList<>(values);
    }

    public double valueAt(int x, int y) throws PointIsNotWithinArrayException {
        if (!isWithinArray(x, y)) {
            throw new PointIsNotWithinArrayException();
        }
        return sourceArray[x][y].getValue();
    }

    public ArrayElement elementAt(int x, int y) throws PointIsNotWithinArrayException {
        if (!isWithinArray(x, y)) {
            throw new PointIsNotWithinArrayException();
        }
        return sourceArray[x][y];
    }

    public void setValue(double value, Point point) throws PointIsNotWithinArrayException {
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
                arrayElement.getPoint().move(newArrayX, newArrayY);
                elementsByValue.computeIfAbsent(arrayElement.getValue(), integer -> new LinkedList<>()).add(arrayElement);
                if (newArrayX == 0) {
                    arrayElement.setLeft(null);
                } else {
                    arrayElement.linkLeft(newArray[newArrayX - 1][newArrayY]);
                }
                if (newArrayX == newArray.length - 1) {
                    arrayElement.setRight(null);
                }

                if (newArrayY == 0) {
                    arrayElement.setUp(null);
                } else {
                    arrayElement.linkUp(newArray[newArrayX][newArrayY - 1]);
                }
                if (newArrayY == newArray[newArrayX].length - 1) {
                    arrayElement.setDown(null);
                }
            }
        }
        this.sourceArray = newArray;
    }

    public SortedMap<Double, List<ArrayElement>> getElementsByValue() {
        return elementsByValue;
    }

    public ArrayElement[][] getSourceArray() {
        return sourceArray;
    }
}
