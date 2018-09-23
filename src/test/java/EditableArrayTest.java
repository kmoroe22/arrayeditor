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