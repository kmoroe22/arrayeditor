package karabo.moroe.editors;

import karabo.moroe.datastructures.ArrayElement;
import karabo.moroe.datastructures.EditableArray;
import karabo.moroe.datastructures.PointIsNotWithinArrayException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Blurrer {

    private EditableArray array;

    public Blurrer(EditableArray array) {
        this.array = array;
    }

    public void blur() {
        List<ArrayElement> allElements = array
                .getElementsByValue()
                .values()
                .stream().flatMap(Collection::stream)
                .sorted(Comparator.comparing(ArrayElement::getPoint)).collect(Collectors.toList());
        for (ArrayElement element : allElements) {
            double neighbourAverage = findAverage(element);
            try {
                array.setValue(neighbourAverage, element.getPoint());
            } catch (PointIsNotWithinArrayException e) {
                throw new RuntimeException("Tried to edit a value that is out of bounds of array");
            }
        }
    }


    private double findAverage(ArrayElement element) {
        OptionalDouble average = Stream.of(element, element.getLeft(), element.getUp(), element.getRight(), element.getDown())
                .filter(Objects::nonNull)
                .mapToDouble(ArrayElement::getValue).average();
        return average.orElse(element.getValue());
    }

}
