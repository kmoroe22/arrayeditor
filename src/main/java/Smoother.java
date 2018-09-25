import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Smoother {

    private EditableArray array;

    public Smoother(EditableArray array) {
        this.array = array;
    }

    public void smooth(double min, double max) throws PointIsNotWithinArrayException {
        Map<Double, List<ArrayElement>> entriesLessThanMin = array.getElementsByValue().headMap(min);
        Map<Double, List<ArrayElement>> entriesMoreThanMax = array.getElementsByValue().tailMap(max + 1);
        List<ArrayElement> allElementsToSmooth = Stream.of(entriesLessThanMin.values(), entriesMoreThanMax.values())
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(ArrayElement::getPoint))
                .collect(Collectors.toList());

        for (ArrayElement element : allElementsToSmooth) {
            double neighbourAverage = findNeighbourAverage(element);
            array.setValue(neighbourAverage, element.getPoint());
        }
    }

    private double findNeighbourAverage(ArrayElement element) {
        OptionalDouble average = Stream.of(element.getLeft(), element.getUp(), element.getRight(), element.getDown())
                .filter(Objects::nonNull)
                .mapToDouble(ArrayElement::getValue).average();
        return average.orElse(element.getValue());
    }


}
