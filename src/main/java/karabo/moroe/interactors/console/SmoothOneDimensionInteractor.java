package karabo.moroe.interactors.console;

import karabo.moroe.editors.EditableArrayRenderer;
import karabo.moroe.editors.Smoother;
import karabo.moroe.interactors.ConsoleOneDimensionInteractor;

public class SmoothOneDimensionInteractor extends ConsoleOneDimensionInteractor<Smoother> {

    public SmoothOneDimensionInteractor(EditableArrayRenderer renderer, Smoother smoother) {
        super(renderer, smoother);
    }

    @Override
    public void interact() {
        System.out.println("Enter minimum value for smooth");
        double minimum = scanner.nextDouble();
        System.out.println("Enter maximum value for smooth");
        double maximum = scanner.nextDouble();

        if (minimum > maximum) {
            System.out.println("Minimum cannot exceed maximum");
            return;
        }

        editor.smooth(minimum, maximum);
        System.out.println(renderer.render());
    }
}
