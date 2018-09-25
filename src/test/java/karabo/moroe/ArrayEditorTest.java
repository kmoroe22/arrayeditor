package karabo.moroe;

import karabo.moroe.datastructures.EditableArray;
import karabo.moroe.datastructures.PointIsNotWithinArrayException;
import org.junit.Assert;
import org.junit.Test;

public class ArrayEditorTest {


    @Test
    public void whenEditsAreAppliedThenChangesReflectOnEditableArray() throws PointIsNotWithinArrayException {
        EditableArray array = new EditableArray(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        ArrayEditor editor = new ArrayEditor(array);

        editor.getReplacer().replace(9, 100);
        Assert.assertEquals(100, array.valueAt(2, 2), 0);

        editor.getCropper().crop(1, 0, 1, 2);

        Assert.assertEquals(4, array.valueAt(0, 0), 0);
        Assert.assertEquals(5, array.valueAt(0, 1), 0);
        Assert.assertEquals(6, array.valueAt(0, 2), 0);

        editor.getReplacer().replace(6, 5);

        Assert.assertEquals(4, array.valueAt(0, 0), 0);
        Assert.assertEquals(5, array.valueAt(0, 1), 0);
        Assert.assertEquals(5, array.valueAt(0, 2), 0);

        editor.getFiller().fill(10, 0, 2);

        Assert.assertEquals(4, array.valueAt(0, 0), 0);
        Assert.assertEquals(10, array.valueAt(0, 1), 0);
        Assert.assertEquals(10, array.valueAt(0, 2), 0);

        editor.getSmoother().smooth(0, 9);

        Assert.assertEquals(4, array.valueAt(0, 0), 0);
        Assert.assertEquals(7, array.valueAt(0, 1), 0);
        Assert.assertEquals(7, array.valueAt(0, 2), 0);

        boolean[][] edges = editor.getEdgeDetector().findEdges();
        Assert.assertTrue(edges[0][0]);
        Assert.assertTrue(edges[0][1]);
        Assert.assertFalse(edges[0][2]);

        editor.getBlurrer().blur();

        Assert.assertEquals(5.5, array.valueAt(0, 0), 0);
        Assert.assertEquals(6.5, array.valueAt(0, 1), 0);
        Assert.assertEquals(6.75, array.valueAt(0, 2), 0);

    }

}