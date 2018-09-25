import karabo.moroe.datastructures.EditableArray;
import karabo.moroe.datastructures.Point;
import karabo.moroe.datastructures.PointIsNotWithinArrayException;
import karabo.moroe.editors.Cropper;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class CropperTest {

    @Test
    public void whenArrayIsCroppedTo2DimensionalElementAccessIndexesAreChanged() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Cropper cropper = new Cropper(array);

        cropper.crop(0, 0, 2, 1);

        Assert.assertEquals(1, array.valueAt(0, 0), 0);
        Assert.assertEquals(2, array.valueAt(0, 1), 0);
        Assert.assertEquals(4, array.valueAt(1, 0), 0);
        Assert.assertEquals(5, array.valueAt(1, 1), 0);
        Assert.assertEquals(7, array.valueAt(2, 0), 0);
        Assert.assertEquals(8, array.valueAt(2, 1), 0);
    }

    @Test
    public void whenArrayIsCroppedArrayElementPointsHaveCorrectValues() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Cropper cropper = new Cropper(array);

        cropper.crop(1, 0, 1, 2);

        Assert.assertEquals(4, array.valueAt(0, 0), 0);
        Assert.assertEquals(5, array.valueAt(0, 1), 0);
        Assert.assertEquals(6, array.valueAt(0, 2), 0);


        Assert.assertEquals(new Point(0, 0), array.elementAt(0, 0).getPoint());
        Assert.assertEquals(new Point(0, 1), array.elementAt(0, 1).getPoint());
        Assert.assertEquals(new Point(0, 2), array.elementAt(0, 2).getPoint());
    }

    @Test
    public void whenArrayIsCroppedThenElementValuesAreUpdated() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Cropper cropper = new Cropper(array);

        cropper.crop(0, 0, 2, 1);

        Assert.assertEquals(1, array.findByValue(1.0).size());
        Assert.assertEquals(1, array.findByValue(2.0).size());
        Assert.assertEquals(1, array.findByValue(4.0).size());
        Assert.assertEquals(1, array.findByValue(5.0).size());
        Assert.assertEquals(1, array.findByValue(7.0).size());
        Assert.assertEquals(1, array.findByValue(8.0).size());
        Assert.assertEquals(0, array.findByValue(6.0).size());
        Assert.assertEquals(0, array.findByValue(3.0).size());
        Assert.assertEquals(0, array.findByValue(9.0).size());
    }

    @Test
    public void whenArrayIsCroppedAndOldIndexIsQueriedThenExceptionIsThrown() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Cropper cropper = new Cropper(array);
        cropper.crop(0, 0, 2, 1);

        try {
            array.elementAt(2, 2);
            fail("Cropped Index should not be successfully retrieved");
        } catch (PointIsNotWithinArrayException ignored) {
        }

        try {
            array.elementAt(1, 2);
            fail("Cropped Index should not be successfully retrieved");
        } catch (PointIsNotWithinArrayException ignored) {
        }

        try {
            array.elementAt(0, 2);
            fail("Cropped Index should not be successfully retrieved");
        } catch (PointIsNotWithinArrayException ignored) {
        }
    }

}