import pair.IPair;

import java.io.IOException;

/**
 * Command to determine the subway route with the most stops and the number of stops along the route.
 */
public class MostStopsCommand implements IMBTACommand {
    private final IMBTAView view;

    /**
     * Constructs a command object for determining the subway route with the most stops and the number of stops along
     * the route.
     * @param view the view to display the route and number of stops
     * @throws IllegalArgumentException if {@code view} is null
     */
    public MostStopsCommand(IMBTAView view) throws IllegalArgumentException {
        this.view = Utils.requireNonNull(view, "View cannot be null");
    }

    @Override
    public void runCommand(IMBTAModel model) throws IllegalArgumentException, IllegalStateException {
        Utils.requireNonNull(model, "Model cannot be null");
        IPair<String, Integer> route = model.findRouteMostStops();

        String message = "Route with the most stops: " + route.getFirst() + " with " + route.getSecond() + " stops.\n";

        //try to print result
        try {
            this.view.renderMessage(message);
        } catch (IOException e) {
            throw new IllegalStateException("Transmission via view failed.");
        }
    }
}
