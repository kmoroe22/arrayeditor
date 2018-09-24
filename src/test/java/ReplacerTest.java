import org.junit.Assert;
import org.junit.Test;

public class ReplacerTest {


    @Test
    public void whenAValueIsReplacedThenRetrievedUsingPointReturnsNewValue() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{1, 2, 3}, {4, 1, 6}, {7, 8, 1}});
        Replacer replacer = new Replacer(array);
        replacer.replace(1, 99);

        Assert.assertEquals(99, array.valueAt(0,0), 0);
        Assert.assertEquals(99, array.valueAt(1,1), 0);
        Assert.assertEquals(99, array.valueAt(2,2), 0);
    }

    @Test
    public void whenNoExistingValuesIsReplacedThenArrayHasSameValues() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Replacer replacer = new Replacer(array);
        replacer.replace(21, 99);

        Assert.assertEquals(1, array.valueAt(0,0), 0);
        Assert.assertEquals(2, array.valueAt(0,1), 0);
        Assert.assertEquals(3, array.valueAt(0,2), 0);
        Assert.assertEquals(4, array.valueAt(1,0), 0);
        Assert.assertEquals(5, array.valueAt(1,1), 0);
        Assert.assertEquals(6, array.valueAt(1,2), 0);
        Assert.assertEquals(7, array.valueAt(2,0), 0);
        Assert.assertEquals(8, array.valueAt(2,1), 0);
        Assert.assertEquals(9, array.valueAt(2,2), 0);
    }

}