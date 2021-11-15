import java.io.IOException;
import java.util.List;

/**
 * Command to determine a list of routes that can be taken to go from a given starting stop to a given destination stop.
 */
public class CreatePathToCommand implements IMBTACommand {
    private final IMBTAView view;
    private final String start;
    private final String dest;


    /**
     * Constructs a command object for determining a list of routes from a given start to end stop on the MBTA
     * @param view the view to display the path of routes found
     * @param start the name of the beginning MBTA stop
     * @param dest the name of the ending MBTA stop
     * @throws IllegalArgumentException if any argument is null
     */
    public CreatePathToCommand(IMBTAView view, String start, String dest) {
        this.view = Utils.requireNonNull(view, "View cannot be null");
        this.start = Utils.requireNonNull(start, "Starting stop cannot be null");
        this.dest = Utils.requireNonNull(dest, "Ending stop cannot be null");
    }

    @Override
    public void runCommand(IMBTAModel model) throws IllegalArgumentException, IllegalStateException {
        Utils.requireNonNull(model, "Model cannot be null");

        List<String> routes;

        try {
            routes = model.findConnectingRoutes(this.start, this.dest);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Start or destination stop is not a valid MBTA stop");
        }

        //transform into a single string
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < routes.size(); i++) {
            stringBuilder.append(routes.get(i));

            //add comma if not last route in list
            if (i < routes.size() - 1) {
                stringBuilder.append(", ");
            }
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
