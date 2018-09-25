package karabo.moroe.editors;

import karabo.moroe.datastructures.ArrayElement;
import karabo.moroe.datastructures.EditableArray;

public class EditableArrayExporter {

    private EditableArray array;

    public EditableArrayExporter(EditableArray array) {
        this.array = array;
    }

    public double[][] export() {
        ArrayElement[][] sourceArray = array.getSourceArray();
        double[][] exportedArray = new double[sourceArray.length][];

        for (int i = 0; i < sourceArray.length; i++) {
            exportedArray[i] = new double[sourceArray[i].length];
            for (int j = 0; j < sourceArray[i].length; j++) {
                exportedArray[i][j] = sourceArray[i][j].getValue();
            }
        }
        return exportedArray;
    }


}
