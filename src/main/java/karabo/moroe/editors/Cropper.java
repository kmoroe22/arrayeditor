package karabo.moroe.editors;

import karabo.moroe.datastructures.EditableArray;
import karabo.moroe.datastructures.Point;
import karabo.moroe.datastructures.PointIsNotWithinArrayException;

public class Cropper {

    public Cropper(EditableArray array) {
        this.array = array;
    }

    private EditableArray array;

    public void crop(int startX, int startY, int endX, int endY) throws PointIsNotWithinArrayException {
        array.crop(new Point(startX, startY), new Point(endX, endY));
    }
}
