package karabo.moroe.editors;

import karabo.moroe.datastructures.ArrayElement;
import karabo.moroe.datastructures.EditableArray;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class EditableArrayRenderer {

    private EditableArray array;
    private DecimalFormat format;

    public EditableArrayRenderer(EditableArray array) {
        this.array = array;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        format = new DecimalFormat("###.###", symbols);
    }

    public String render() {
        ArrayElement[][] sourceArray = array.getSourceArray();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < sourceArray.length; i++) {
            builder.append("[");
            for (int j = 0; j < sourceArray[i].length; j++) {
                builder.append(format.format(sourceArray[i][j].getValue()));
                if (j != sourceArray[i].length - 1) {
                    builder.append(", ");
                }
            }
            builder.append("]\n");
        }
        return builder.toString();
    }
}
