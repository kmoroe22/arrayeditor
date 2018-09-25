import org.junit.Assert;
import org.junit.Test;

public class SmootherTest {


    @Test
    public void whenNoValuesFallBelowMinOrAboveMaxThenArrayIsNotChange() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Smoother smoother = new Smoother(array);
        smoother.smooth(1, 9);
        Assert.assertEquals(1, array.valueAt(0, 0), 0);
        Assert.assertEquals(2, array.valueAt(0, 1), 0);
        Assert.assertEquals(3, array.valueAt(0, 2), 0);
        Assert.assertEquals(4, array.valueAt(1, 0), 0);
        Assert.assertEquals(5, array.valueAt(1, 1), 0);
        Assert.assertEquals(6, array.valueAt(1, 2), 0);
        Assert.assertEquals(7, array.valueAt(2, 0), 0);
        Assert.assertEquals(8, array.valueAt(2, 1), 0);
        Assert.assertEquals(9, array.valueAt(2, 2), 0);
    }

    @Test
    public void whenNoValuesFallAboveMaximumThenThoseValuesAreChanged() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{11, 2, 3}, {13, 5, 6}, {7, 200, 9}});
        Smoother smoother = new Smoother(array);
        smoother.smooth(1, 9);

        Assert.assertNotEquals(11, array.valueAt(0, 0), 0);
        Assert.assertEquals(2, array.valueAt(0, 1), 0);
        Assert.assertEquals(3, array.valueAt(0, 2), 0);
        Assert.assertNotEquals(13, array.valueAt(1, 0), 0);
        Assert.assertEquals(5, array.valueAt(1, 1), 0);
        Assert.assertEquals(6, array.valueAt(1, 2), 0);
        Assert.assertEquals(7, array.valueAt(2, 0), 0);
        Assert.assertNotEquals(200, array.valueAt(2, 1), 0);
        Assert.assertEquals(9, array.valueAt(2, 2), 0);
    }

    @Test
    public void whenValuesFallBelowMinimumThenThoseValuesAreChanged() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Smoother smoother = new Smoother(array);
        smoother.smooth(4, 9);

        Assert.assertNotEquals(1, array.valueAt(0, 0), 0);
        Assert.assertNotEquals(2, array.valueAt(0, 1), 0);
        Assert.assertNotEquals(3, array.valueAt(0, 2), 0);
        Assert.assertEquals(4, array.valueAt(1, 0), 0);
        Assert.assertEquals(5, array.valueAt(1, 1), 0);
        Assert.assertEquals(6, array.valueAt(1, 2), 0);
        Assert.assertEquals(7, array.valueAt(2, 0), 0);
        Assert.assertEquals(8, array.valueAt(2, 1), 0);
        Assert.assertEquals(9, array.valueAt(2, 2), 0);
    }

    @Test
    public void whenElementIsEligibleForSmoothingAverageOfNeighboursBecomesNewValue() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{7, 2, 5}, {4, 5, 6}, {7, 8, 20}});
        Smoother smoother = new Smoother(array);
        smoother.smooth(3, 9);
        double firstExpectedAverage = (7.0 + 5.0 + 5.0) / 3.0;
        double secondExpectedAverage = (6.0 + 8.0) / 2.0;

        Assert.assertEquals(firstExpectedAverage, array.valueAt(0, 1), 0);
        Assert.assertEquals(secondExpectedAverage, array.valueAt(2, 2), 0);
    }

    @Test
    public void whenAdjacentElementsAreEligibleForChangeSmallerPointGetsChangedFirst() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{1, 2, 5}, {4, 5, 6}, {7, 20, 20}});
        Smoother smoother = new Smoother(array);
        smoother.smooth(3, 9);
        double firstExpectedAverage = (2.0 + 4.0) / 2.0;
        double secondExpectedAverage = (firstExpectedAverage + 5.0 + 5.0) / 3.0;
        double thirdExpectedAverage = (7.0 + 5.0 + 20.0) / 3.0;
        double fourthExpectedAverage = (thirdExpectedAverage + 6.0) / 2.0;
        Assert.assertEquals(firstExpectedAverage, array.valueAt(0, 0), 0);
        Assert.assertEquals(secondExpectedAverage, array.valueAt(0, 1), 0);
        Assert.assertEquals(thirdExpectedAverage, array.valueAt(2, 1), 0);
        Assert.assertEquals(fourthExpectedAverage, array.valueAt(2, 2), 0);
    }

    @Test
    public void whenElementIsEligibleForSmoothingInOneDimensionalArrayThen2AdjacentNeighbourAverageIsUsed() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{7, 2, 5}});
        Smoother smoother = new Smoother(array);
        smoother.smooth(3, 9);
        double expectedAverage = (7.0 + 5.0) / 2.0;
        Assert.assertEquals(expectedAverage, array.valueAt(0, 1), 0);
    }


}