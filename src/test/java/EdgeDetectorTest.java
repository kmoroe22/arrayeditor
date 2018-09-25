import karabo.moroe.datastructures.EditableArray;
import karabo.moroe.editors.EdgeDetector;
import org.junit.Assert;
import org.junit.Test;

public class EdgeDetectorTest {


    @Test
    public void whenValuesChangeThenEdgeDetectorArrayIndicatesChange() {
        EditableArray array = new EditableArray(new double[][]{{5, 5, 5, 2, 2, 2, 2, 3, 3}});

        EdgeDetector edgeDetector = new EdgeDetector(array);

        boolean[][] edges = edgeDetector.findEdges();

        Assert.assertFalse(edges[0][0]);
        Assert.assertFalse(edges[0][1]);
        Assert.assertTrue(edges[0][2]);
        Assert.assertTrue(edges[0][3]);
        Assert.assertFalse(edges[0][4]);
        Assert.assertFalse(edges[0][5]);
        Assert.assertTrue(edges[0][6]);
        Assert.assertTrue(edges[0][7]);
        Assert.assertFalse(edges[0][8]);
    }
}