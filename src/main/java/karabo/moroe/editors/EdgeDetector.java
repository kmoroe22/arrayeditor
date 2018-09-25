package karabo.moroe.editors;

import karabo.moroe.datastructures.ArrayElement;
import karabo.moroe.datastructures.EditableArray;

import java.util.Objects;
import java.util.stream.Stream;

public class EdgeDetector {

    private EditableArray array;

    public EdgeDetector(EditableArray array) {
        this.array = array;
    }

    public boolean[][] findEdges() {
        ArrayElement[][] sourceArray = array.getSourceArray();
        boolean[][] edgeArray = new boolean[sourceArray.length][];

        for (int i = 0; i < sourceArray.length; i++) {
            edgeArray[i] = new boolean[sourceArray[i].length];
            for (int j = 0; j < sourceArray[i].length; j++) {
                edgeArray[i][j] = isEdge(sourceArray[i][j]);
            }
        }
        return edgeArray;
    }

    private boolean isEdge(ArrayElement element) {
        return Stream.of(element.getLeft(), element.getUp(), element.getRight(), element.getDown())
                .filter(Objects::nonNull)
                .anyMatch(a -> a.getValue() != element.getValue());
    }
}
