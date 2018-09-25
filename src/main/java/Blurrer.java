import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Blurrer {

    private EditableArray array;

    public Blurrer(EditableArray array) {
        this.array = array;
    }

    public void blur() throws PointIsNotWithinArrayException {
        List<ArrayElement> allElements = array
                .getElementsByValue()
                .values()
                .stream().flatMap(Collection::stream)
                .sorted(Comparator.comparing(ArrayElement::getPoint)).collect(Collectors.toList());
        for (ArrayElement element : allElements) {
            double neighbourAverage = findAverage(element);
            array.setValue(neighbourAverage, element.getPoint());
        }
    }


    private double findAverage(ArrayElement element) {
        OptionalDouble average = Stream.of(element, element.getLeft(), element.getUp(), element.getRight(), element.getDown())
                .filter(Objects::nonNull)
                .mapToDouble(ArrayElement::getValue).average();
        return average.orElse(element.getValue());
    }

}
