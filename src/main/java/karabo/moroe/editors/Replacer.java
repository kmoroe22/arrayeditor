package karabo.moroe.editors;

import karabo.moroe.datastructures.ArrayElement;
import karabo.moroe.datastructures.EditableArray;
import karabo.moroe.datastructures.PointIsNotWithinArrayException;

import java.util.List;

public class Replacer {

    private EditableArray array;

    public Replacer(EditableArray array) {
        this.array = array;
    }

    public void replace(double valueToReplace, double newValue) {
        List<ArrayElement> values = array.findByValue(valueToReplace);
        for (ArrayElement element : values) {
            try {
                array.setValue(newValue, element.getPoint());
            } catch (PointIsNotWithinArrayException e) {
                throw new RuntimeException("Tried to replace value that is not within bounds of array");
            }
        }
    }
}
