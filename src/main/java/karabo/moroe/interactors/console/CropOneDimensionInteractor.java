package karabo.moroe.interactors.console;

import karabo.moroe.datastructures.PointIsNotWithinArrayException;
import karabo.moroe.editors.Cropper;
import karabo.moroe.editors.EditableArrayRenderer;
import karabo.moroe.interactors.ConsoleOneDimensionInteractor;

public class CropOneDimensionInteractor extends ConsoleOneDimensionInteractor<Cropper> {

    public CropOneDimensionInteractor(EditableArrayRenderer renderer, Cropper cropper) {
        super(renderer, cropper);
    }

    @Override
    public void interact() {
        System.out.println("Enter start index of crop");
        int startLocation = scanner.nextInt();
        System.out.println("Enter end index of crop");
        int endLocation = scanner.nextInt();
        try {
            editor.crop(0, startLocation, 0, endLocation);
        } catch (PointIsNotWithinArrayException e) {
            System.out.println("Cannot crop value that does not fall within bounds of array");
            return;
        }
        System.out.println(renderer.render());
    }

    private boolean canBeConvertedToInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
