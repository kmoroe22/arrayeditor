package karabo.moroe.interactors.console;

import karabo.moroe.editors.Blurrer;
import karabo.moroe.editors.EditableArrayRenderer;
import karabo.moroe.interactors.ConsoleOneDimensionInteractor;

public class BlurOneDimensionInteractor extends ConsoleOneDimensionInteractor<Blurrer> {

    public BlurOneDimensionInteractor(EditableArrayRenderer renderer, Blurrer blurrer) {
        super(renderer, blurrer);
    }

    @Override
    public void interact() {
        super.editor.blur();
        System.out.println(renderer.render());
    }

}
