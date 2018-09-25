package karabo.moroe;

import karabo.moroe.datastructures.EditableArray;
import karabo.moroe.editors.*;

public class ArrayEditor {

    private EditableArray array;
    private Replacer replacer;
    private Cropper cropper;
    private Filler filler;
    private Blurrer blurrer;
    private Smoother smoother;
    private EdgeDetector edgeDetector;
    private EditableArrayExporter exporter;
    private EditableArrayRenderer renderer;

    public ArrayEditor(EditableArray array) {
        this.array = array;
        replacer = new Replacer(array);
        cropper = new Cropper(array);
        filler = new Filler(array);
        blurrer = new Blurrer(array);
        smoother = new Smoother(array);
        edgeDetector = new EdgeDetector(array);
        exporter = new EditableArrayExporter(array);
        renderer = new EditableArrayRenderer(array);
    }


    public Replacer getReplacer() {
        return replacer;
    }

    public Cropper getCropper() {
        return cropper;
    }

    public Filler getFiller() {
        return filler;
    }

    public Blurrer getBlurrer() {
        return blurrer;
    }

    public Smoother getSmoother() {
        return smoother;
    }

    public EdgeDetector getEdgeDetector() {
        return edgeDetector;
    }

    public EditableArrayExporter getExporter() {
        return exporter;
    }

    public EditableArrayRenderer getRenderer() {
        return renderer;
    }
}
