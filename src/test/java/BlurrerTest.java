import org.junit.Assert;
import org.junit.Test;

public class BlurrerTest {

    @Test
    public void whenBlurIsCalledValuesAreReplacedWithAdjacentAverages() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{5, 5, 5}, {5, 5, 5}, {5, 5, 2}});
        Blurrer blurrer = new Blurrer(array);
        blurrer.blur();

        Assert.assertEquals(5, array.valueAt(0, 0), 0);
        Assert.assertEquals(5, array.valueAt(0, 1), 0);
        Assert.assertEquals(5, array.valueAt(0, 2), 0);
        Assert.assertEquals(5, array.valueAt(1, 0), 0);
        Assert.assertEquals(5, array.valueAt(1, 1), 0);
        Assert.assertEquals(4.25, array.valueAt(1, 2), 0);
        Assert.assertEquals(5, array.valueAt(2, 0), 0);
        Assert.assertEquals(4.25, array.valueAt(2, 1), 0);
        Assert.assertEquals(3.5, array.valueAt(2, 2), 0);

    }

}