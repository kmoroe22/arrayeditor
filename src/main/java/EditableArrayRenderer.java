public class EditableArrayRenderer {

    private EditableArray array;

    public EditableArrayRenderer(EditableArray array) {
        this.array = array;
    }

    public String render() {
        ArrayElement[][] sourceArray = array.getSourceArray();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < sourceArray.length; i++) {
            builder.append("[");
            for (int j = 0; j < sourceArray[i].length; j++) {
                builder.append(String.format("%.2f", sourceArray[i][j].getValue()));
                if (j != sourceArray.length - 1) {
                    builder.append(",");
                }
            }
            builder.append("]\n");
        }
        return builder.toString();
    }
}
