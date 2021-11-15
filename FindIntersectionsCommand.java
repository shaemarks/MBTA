import pair.IPair;
import java.io.IOException;
import java.util.List;

/**
 * Command to determine the stops along the MBTA system that connect two or more routes.
 */
public class FindIntersectionsCommand implements IMBTACommand {
    private final IMBTAView view;

    /**
     * Constructs a command object for retrieving the intersection stops of the MBTA system
     * @param view the view to display the intersections found
     * @throws IllegalArgumentException if {@code view} is null
     */
    public FindIntersectionsCommand(IMBTAView view) {
        this.view = Utils.requireNonNull(view, "View cannot be null");
    }

    @Override
    public void runCommand(IMBTAModel model) throws IllegalArgumentException, IllegalStateException {
        Utils.requireNonNull(model, "Model cannot be null");
        List<IPair<String, List<String>>> intersections = model.getIntersections();

        //transform into a single string
        StringBuilder stringBuilder = new StringBuilder();
        for (IPair<String, List<String>> stop : intersections) {
            //add intersection name
            stringBuilder.append(stop.getFirst());
            stringBuilder.append(" : ");

            //add routes connected
            List<String> routes = stop.getSecond();
            for(int i = 0; i < routes.size(); i++) {
                stringBuilder.append(routes.get(i));

                //add comma if not last route in list
                if (i < routes.size() - 1) {
                    stringBuilder.append(", ");
                }
            }

            //add new line
            stringBuilder.append("\n");

        }
        stringBuilder.append("\n");

        //try to print result
        try {
            this.view.renderMessage(stringBuilder.toString());
        } catch (IOException e) {
            throw new IllegalStateException("Transmission via view failed.");
        }
    }
}
