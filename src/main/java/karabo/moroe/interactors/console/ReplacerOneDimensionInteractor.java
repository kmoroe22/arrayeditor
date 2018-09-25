package karabo.moroe.interactors.console;

import karabo.moroe.datastructures.PointIsNotWithinArrayException;
import karabo.moroe.editors.EditableArrayRenderer;
import karabo.moroe.editors.Filler;
import karabo.moroe.editors.Replacer;
import karabo.moroe.interactors.ConsoleOneDimensionInteractor;

public class ReplacerOneDimensionInteractor extends ConsoleOneDimensionInteractor<Replacer> {

    public ReplacerOneDimensionInteractor(EditableArrayRenderer renderer, Replacer replacer) {
        super(renderer, replacer);
    }

    @Override
    public void interact() {
        System.out.println("Enter value to replace");
        int valueToReplace = scanner.nextInt();
        System.out.println("Enter value to replace with");
        int newValue = scanner.nextInt();
        editor.replace(valueToReplace, newValue);
        System.out.println(renderer.render());
    }
}
