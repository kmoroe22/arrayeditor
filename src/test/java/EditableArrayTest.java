import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class EditableArrayTest {


    @Test
    public void whenAValueIsSetOutsideTheRangeOfTheArrayThenAnExceptionIsThrown() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        expectPointIsNotWithinArrayExceptionFor(array, -1, 0);
        expectPointIsNotWithinArrayExceptionFor(array, 3, 0);
        expectPointIsNotWithinArrayExceptionFor(array, 0, -1);
        expectPointIsNotWithinArrayExceptionFor(array, 0, 3);
    }

    @Test
    public void whenAValueIsSetWithinRangeOfArrayValueCanBeRetrievedFromSameIndex() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        expectSetValueToMatchRetrieveValue(array, 99, 0, 0);
        expectSetValueToMatchRetrieveValue(array, 98, 0, 1);
        expectSetValueToMatchRetrieveValue(array, 97, 0, 2);
        expectSetValueToMatchRetrieveValue(array, 96, 1, 0);
        expectSetValueToMatchRetrieveValue(array, 95, 1, 1);
        expectSetValueToMatchRetrieveValue(array, 94, 1, 2);
        expectSetValueToMatchRetrieveValue(array, 93, 2, 0);
        expectSetValueToMatchRetrieveValue(array, 92, 2, 1);
        expectSetValueToMatchRetrieveValue(array, 91, 2, 2);
    }

    @Test
    public void whenArrayStartAndEndAreChangedValuesForNewLocationsAreRetrieved() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        int value1 = array.valueAt(0, 0);
        int value2 = array.valueAt(1, 0);
        int value3 = array.valueAt(2, 0);

        Point newStart = new Point(0, 1);
        Point newEnd = new Point(2, 1);

        array.crop(newStart, newEnd);

        Assert.assertNotEquals(value1, array.valueAt(0, 0));
        Assert.assertNotEquals(value2, array.valueAt(1, 0));
        Assert.assertNotEquals(value3, array.valueAt(2, 0));

        Assert.assertEquals(2, array.valueAt(0, 0));
        Assert.assertEquals(5, array.valueAt(1, 0));
        Assert.assertEquals(8, array.valueAt(2, 0));
    }

    @Test
    public void whenArrayIsCreatedNodeNeighboursAreLinked() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

        ArrayElement firstElement = array.elementAt(0, 0);
        Assert.assertNull(firstElement.getLeft());
        Assert.assertNull(firstElement.getUp());
        Assert.assertEquals(firstElement.getDown().getValue(), 2);

        ArrayElement firstMiddle = firstElement.getRight();
        Assert.assertEquals(firstMiddle.getLeft(), firstElement);
        Assert.assertNull(firstMiddle.getUp());
        Assert.assertEquals(firstMiddle.getDown().getValue(), 5);


        ArrayElement firstLast = firstMiddle.getRight();
        Assert.assertEquals(firstLast.getLeft(), firstMiddle);
        Assert.assertNull(firstLast.getRight());
        Assert.assertNull(firstLast.getUp());
        Assert.assertEquals(firstLast.getDown().getValue(), 8);

        ArrayElement middleFirst = firstElement.getDown();
        Assert.assertEquals(middleFirst.getUp(), firstElement);
        Assert.assertNull(middleFirst.getLeft());
        Assert.assertEquals(middleFirst.getDown().getValue(), 3);

        ArrayElement middleMiddle = middleFirst.getRight();
        Assert.assertEquals(middleMiddle.getLeft(), middleFirst);
        Assert.assertEquals(middleMiddle.getUp(), firstMiddle);
        Assert.assertEquals(middleMiddle.getRight().getValue(), 8);
        Assert.assertEquals(middleMiddle.getDown().getValue(), 6);

        ArrayElement middleLast = middleMiddle.getRight();
        Assert.assertEquals(middleLast.getLeft(), middleMiddle);
        Assert.assertEquals(middleLast.getUp(), firstLast);
        Assert.assertEquals(middleLast.getDown().getValue(),9);
        Assert.assertNull(middleLast.getRight());

        ArrayElement lastFirst = middleFirst.getDown();
        Assert.assertNull(lastFirst.getLeft());
        Assert.assertNull(lastFirst.getDown());
        Assert.assertEquals(lastFirst.getUp(), middleFirst);
        Assert.assertEquals(lastFirst.getRight().getValue(), 6);

        ArrayElement lastMiddle = lastFirst.getRight();
        Assert.assertNull(lastMiddle.getDown());
        Assert.assertEquals(lastMiddle.getLeft(), lastFirst);
        Assert.assertEquals(lastMiddle.getUp(), middleMiddle);
        Assert.assertEquals(lastMiddle.getRight().getValue(), 9);

        ArrayElement lastLast = lastMiddle.getRight();
        Assert.assertNull(lastLast.getDown());
        Assert.assertNull(lastLast.getRight());
        Assert.assertEquals(lastLast.getLeft(), lastMiddle);
        Assert.assertEquals(lastLast.getUp(), middleLast);
    }

    @Test
    public void whenArrayIsResizedTo1DArrayLinksAreReset() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Point newStart = new Point(0, 1);
        Point newEnd = new Point(2, 1);

        array.crop(newStart, newEnd);

        ArrayElement firstElement = array.elementAt(0, 0);
        Assert.assertEquals(firstElement.getValue(), 2);
        Assert.assertNull(firstElement.getLeft());
        Assert.assertNull(firstElement.getUp());
        Assert.assertNull(firstElement.getDown());
        Assert.assertEquals(firstElement.getRight().getValue(), 5);

        ArrayElement firstMiddle = firstElement.getRight();
        Assert.assertEquals(firstMiddle.getLeft(), firstElement);
        Assert.assertNull(firstMiddle.getUp());
        Assert.assertNull(firstMiddle.getDown());
        Assert.assertEquals(firstMiddle.getRight().getValue(), 8);


        ArrayElement firstLast = firstMiddle.getRight();
        Assert.assertEquals(firstLast.getLeft(), firstMiddle);
        Assert.assertNull(firstLast.getRight());
        Assert.assertNull(firstLast.getUp());
        Assert.assertNull(firstLast.getDown());

    }

    @Test
    public void whenArrayIsResizedTo2DArrayLinksAreReset() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Point newStart = new Point(0, 1);
        Point newEnd = new Point(2, 2);

        array.crop(newStart, newEnd);

        ArrayElement firstElement = array.elementAt(0, 0);
        Assert.assertNull(firstElement.getLeft());
        Assert.assertNull(firstElement.getUp());
        Assert.assertEquals(firstElement.getDown().getValue(), 3);

        ArrayElement firstMiddle = firstElement.getRight();
        Assert.assertEquals(firstMiddle.getLeft(), firstElement);
        Assert.assertNull(firstMiddle.getUp());
        Assert.assertEquals(firstMiddle.getDown().getValue(), 6);


        ArrayElement firstLast = firstMiddle.getRight();
        Assert.assertEquals(firstLast.getLeft(), firstMiddle);
        Assert.assertNull(firstLast.getRight());
        Assert.assertNull(firstLast.getUp());
        Assert.assertEquals(firstLast.getDown().getValue(), 9);

        ArrayElement lastFirst = firstElement.getDown();
        Assert.assertEquals(lastFirst.getUp(), firstElement);
        Assert.assertNull(lastFirst.getLeft());
        Assert.assertNull(lastFirst.getDown());

        ArrayElement lastMiddle = lastFirst.getRight();
        Assert.assertEquals(lastMiddle.getLeft(), lastFirst);
        Assert.assertEquals(lastMiddle.getUp(), firstMiddle);
        Assert.assertEquals(lastMiddle.getRight().getValue(), 9);
        Assert.assertNull(lastMiddle.getDown());

        ArrayElement lastLast = lastMiddle.getRight();
        Assert.assertEquals(lastLast.getLeft(), lastMiddle);
        Assert.assertEquals(lastLast.getUp(), firstLast);
        Assert.assertNull(lastLast.getRight());
        Assert.assertNull(lastLast.getDown());


    }


    private void expectSetValueToMatchRetrieveValue(EditableArray array, int valueToSetTo, int x, int y) throws PointIsNotWithinArrayException {
        array.setValue(valueToSetTo, x, y);
        int value = array.valueAt(x, y);
        Assert.assertEquals(value, valueToSetTo);
    }

    private void expectPointIsNotWithinArrayExceptionFor(EditableArray array, int x, int y) {
        try {
            array.setValue(99, x, y);
            fail();
        } catch (PointIsNotWithinArrayException ignored) {

        }
    }

}