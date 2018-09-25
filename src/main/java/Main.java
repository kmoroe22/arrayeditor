import karabo.moroe.ArrayEditor;
import karabo.moroe.datastructures.EditableArray;
import karabo.moroe.editors.EditableArrayRenderer;
import karabo.moroe.interactors.ConsoleOneDimensionInteractor;
import karabo.moroe.interactors.console.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please provide array by inputting comma delimited array representation for example: 1,2,3,4,5");
        String arrayString = scanner.nextLine();
        String[] strings = arrayString.split(",");
        if (strings.length < 1) {
            System.out.println("Invalid input array");
            return;
        }

        double[] array = new double[strings.length];

        for (int i = 0; i < strings.length; i++) {
            array[i] = Double.parseDouble(strings[i]);
        }

        EditableArray editableArray = new EditableArray(new double[][]{array});
        System.out.println(new EditableArrayRenderer(editableArray).render());
        ArrayEditor arrayEditor = new ArrayEditor(editableArray);
        Map<Integer, ConsoleOneDimensionInteractor> interactors = intializeInteractors(arrayEditor);

        while (true) {
            System.out.print("1. replace\n" +
                    "2. crop\n" +
                    "3. fill\n" +
                    "4. smooth\n" +
                    "5. blur\n" +
                    "6. edge detection\n" +
                    "7. exit\n");
            int menuItem = scanner.nextInt();
            if (menuItem < 1 || menuItem > 8) {
                System.out.print("Invalid menu item");
                continue;
            } else if (menuItem == 7) {
                System.exit(0);
            } else {
                interactors.get(menuItem).interact();
            }
        }
    }

    private static Map<Integer, ConsoleOneDimensionInteractor> intializeInteractors(ArrayEditor editor) {
        Map<Integer, ConsoleOneDimensionInteractor> interactors = new HashMap<>();
        interactors.put(1, new ReplacerOneDimensionInteractor(editor.getRenderer(), editor.getReplacer()));
        interactors.put(2, new CropOneDimensionInteractor(editor.getRenderer(), editor.getCropper()));
        interactors.put(3, new FillerOneDimensionInteractor(editor.getRenderer(), editor.getFiller()));
        interactors.put(4, new SmoothOneDimensionInteractor(editor.getRenderer(), editor.getSmoother()));
        interactors.put(5, new BlurOneDimensionInteractor(editor.getRenderer(), editor.getBlurrer()));
        interactors.put(6, new EdgeDetectionOneDimensionInteractor(editor.getRenderer(), editor.getEdgeDetector()));
        return interactors;
    }


}
