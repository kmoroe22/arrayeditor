package karabo.moroe.editors;

import karabo.moroe.datastructures.ArrayElement;
import karabo.moroe.datastructures.EditableArray;
import karabo.moroe.datastructures.PointIsNotWithinArrayException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Smoother {

    private EditableArray array;

    public Smoother(EditableArray array) {
        this.array = array;
    }

    public void smooth(double min, double max)  {
        Map<Double, List<ArrayElement>> entriesLessThanMin = array.getElementsByValue().headMap(min);
        Map<Double, List<ArrayElement>> entriesMoreThanMax = array.getElementsByValue().tailMap(max + 1);
        List<ArrayElement> allElementsToSmooth = Stream.of(entriesLessThanMin.values(), entriesMoreThanMax.values())
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(ArrayElement::getPoint))
                .collect(Collectors.toList());

        for (ArrayElement element : allElementsToSmooth) {
            double neighbourAverage = findNeighbourAverage(element);
            try {
                array.setValue(neighbourAverage, element.getPoint());
            } catch (PointIsNotWithinArrayException e) {
                throw new RuntimeException("Tried to set value that was not within bounds of array");
            }
        }
    }

    private double findNeighbourAverage(ArrayElement element) {
        OptionalDouble average = Stream.of(element.getLeft(), element.getUp(), element.getRight(), element.getDown())
                .filter(Objects::nonNull)
                .mapToDouble(ArrayElement::getValue).average();
        return average.orElse(element.getValue());
    }


}
