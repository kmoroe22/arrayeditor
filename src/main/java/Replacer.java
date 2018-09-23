import java.util.ArrayList;
import java.util.List;

public class Replacer {

    private EditableArray array;

    public Replacer(EditableArray array) {
        this.array = array;
    }

    public void replace(int valueToReplace, int newValue) throws PointIsNotWithinArrayException {
        List<ArrayElement> values = array.findByValue(valueToReplace);
        for (ArrayElement element : values) {
            array.setValue(newValue, element.getPoint());
        }
    }
}
