package karabo.moroe.interactors.console;

import karabo.moroe.datastructures.PointIsNotWithinArrayException;
import karabo.moroe.editors.EditableArrayRenderer;
import karabo.moroe.editors.Filler;
import karabo.moroe.interactors.ConsoleOneDimensionInteractor;

public class FillerOneDimensionInteractor extends ConsoleOneDimensionInteractor<Filler> {

    public FillerOneDimensionInteractor(EditableArrayRenderer renderer, Filler filler) {
        super(renderer, filler);
    }

    @Override
    public void interact() {
        System.out.println("Enter location to fill from");
        int fillLocation = scanner.nextInt();
        System.out.println("Enter new value to fill");
        double newValue = scanner.nextDouble();
        try {
            editor.fill(newValue, 0, fillLocation);
        } catch (PointIsNotWithinArrayException e) {
            System.out.println("Cannot fill from index that is not within array");
        }
        System.out.println(renderer.render());
    }
}
