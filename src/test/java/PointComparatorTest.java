import karabo.moroe.datastructures.Point;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointComparatorTest {

    @Test
    public void whenPointsAreSortedTheyAreSortedByPlaceInGrid() {
        Point point2 = new Point(0, 0);
        Point point4 = new Point(0, 0);
        Point point6 = new Point(2, 0);
        Point point3 = new Point(1, 1);
        Point point1 = new Point(0, 2);
        Point point5 = new Point(1, 2);

        List<Point> points = new ArrayList<>(Arrays.asList(point1, point2, point3, point4, point5, point6));

        points.sort(Point::compareTo);

        Assert.assertEquals(point2, points.get(0));
        Assert.assertEquals(point4, points.get(1));
        Assert.assertEquals(point6, points.get(2));
        Assert.assertEquals(point3, points.get(3));
        Assert.assertEquals(point1, points.get(4));
        Assert.assertEquals(point5, points.get(5));
    }

}