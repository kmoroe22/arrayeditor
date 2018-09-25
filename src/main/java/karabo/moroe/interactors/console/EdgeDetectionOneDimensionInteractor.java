package karabo.moroe.interactors.console;

import karabo.moroe.editors.EdgeDetector;
import karabo.moroe.editors.EditableArrayRenderer;
import karabo.moroe.interactors.ConsoleOneDimensionInteractor;

public class EdgeDetectionOneDimensionInteractor extends ConsoleOneDimensionInteractor<EdgeDetector> {

    public EdgeDetectionOneDimensionInteractor(EditableArrayRenderer renderer, EdgeDetector edgeDetector) {
        super(renderer, edgeDetector);
    }

    @Override
    public void interact() {
        boolean[][] edges = editor.findEdges();
        System.out.println(toString(edges));
    }

    private String toString(boolean[][] edgeArray) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < edgeArray.length; i++) {
            builder.append("[");
            for (int j = 0; j < edgeArray[i].length; j++) {
                builder.append(booleanToString(edgeArray[i][j]));
                if (j != edgeArray[i].length - 1) {
                    builder.append(", ");
                }

            }
            builder.append("]");
        }
        return builder.toString();
    }

    public static String booleanToString(boolean value) {
        int bitValue = value ? 1 : 0;
        return Integer.toString(bitValue);
    }
}
