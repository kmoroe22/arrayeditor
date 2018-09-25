package karabo.moroe.interactors;

import karabo.moroe.editors.EditableArrayRenderer;

import java.util.Scanner;

public abstract class ConsoleOneDimensionInteractor<Editor> {

    protected static final Scanner scanner = new Scanner(System.in);
    protected EditableArrayRenderer renderer;
    protected Editor editor;

    public ConsoleOneDimensionInteractor(EditableArrayRenderer renderer, Editor editor) {
        this.renderer = renderer;
        this.editor = editor;
    }

    public abstract void interact();

}
