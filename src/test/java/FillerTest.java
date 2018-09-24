import org.junit.Assert;
import org.junit.Test;

public class FillerTest {

    @Test
    public void whenNoNeighboursHaveSameValuesThenFillOnlyAppliesToStartElement() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

        Filler filler = new Filler(array);

        int newValue = 10;
        filler.fill(newValue, 1, 1);

        Assert.assertEquals(1, array.valueAt(0, 0));
        Assert.assertEquals(2, array.valueAt(0, 1));
        Assert.assertEquals(3, array.valueAt(0, 2));

        Assert.assertEquals(4, array.valueAt(1, 0));
        Assert.assertEquals(newValue, array.valueAt(1, 1));
        Assert.assertEquals(6, array.valueAt(1, 2));

        Assert.assertEquals(7, array.valueAt(2, 0));
        Assert.assertEquals(8, array.valueAt(2, 1));
        Assert.assertEquals(9, array.valueAt(2, 2));
    }

    @Test
    public void whenNeighboursHaveSameValuesThenFillCascadesNewValue() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new int[][]{{1, 5, 3}, {5, 5, 6}, {7, 8, 9}});

        Filler filler = new Filler(array);

        int newValue = 10;
        filler.fill(newValue, 1, 1);

        Assert.assertEquals(1, array.valueAt(0, 0));
        Assert.assertEquals(newValue, array.valueAt(0, 1));
        Assert.assertEquals(3, array.valueAt(0, 2));

        Assert.assertEquals(newValue, array.valueAt(1, 0));
        Assert.assertEquals(newValue, array.valueAt(1, 1));
        Assert.assertEquals(6, array.valueAt(1, 2));

        Assert.assertEquals(7, array.valueAt(2, 0));
        Assert.assertEquals(8, array.valueAt(2, 1));
        Assert.assertEquals(9, array.valueAt(2, 2));
    }

    @Test
    public void whenNeighboursHaveTransientSameValuesThenNewValueCascades() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new int[][]{{1, 2, 3}, {1, 1, 1}, {7, 8, 1}});

        Filler filler = new Filler(array);

        int newValue = 10;
        filler.fill(newValue, 0, 0);

        Assert.assertEquals(newValue, array.valueAt(0, 0));
        Assert.assertEquals(2, array.valueAt(0, 1));
        Assert.assertEquals(3, array.valueAt(0, 2));

        Assert.assertEquals(newValue, array.valueAt(1, 0));
        Assert.assertEquals(newValue, array.valueAt(1, 1));
        Assert.assertEquals(newValue, array.valueAt(1, 2));

        Assert.assertEquals(7, array.valueAt(2, 0));
        Assert.assertEquals(8, array.valueAt(2, 1));
        Assert.assertEquals(newValue, array.valueAt(2, 2));
    }

    @Test
    public void whenNeighboursDoNotHaveMatchingValueButTransientNeighboursDoValueDoesNotCascadeToTransientNeighbours() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new int[][]{{1, 2, 1}, {4, 1, 6}, {7, 8, 1}});

        Filler filler = new Filler(array);

        int newValue = 10;
        filler.fill(newValue, 0, 0);

        Assert.assertEquals(newValue, array.valueAt(0, 0));
        Assert.assertEquals(2, array.valueAt(0, 1));
        Assert.assertEquals(1, array.valueAt(0, 2));

        Assert.assertEquals(4, array.valueAt(1, 0));
        Assert.assertEquals(1, array.valueAt(1, 1));
        Assert.assertEquals(6, array.valueAt(1, 2));

        Assert.assertEquals(7, array.valueAt(2, 0));
        Assert.assertEquals(8, array.valueAt(2, 1));
        Assert.assertEquals(1, array.valueAt(2, 2));
    }

}