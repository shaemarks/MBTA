import java.io.IOException;

/**
 * Implementation of a text-based view for an MBTA route processor. Appends strings representing the
 * available command options and specified messages to a specified data destination.
 */
public class MBTAView implements IMBTAView {
    private final Appendable ap;

    /**
     * Constructs a text-bassed view for an MBTA route processor that transmits information about the model
     * to the destination {@code ap}.
     *
     * @param ap the appendable destination for the view to transmit information to
     * @throws IllegalArgumentException if either argument is null
     */
    public MBTAView(Appendable ap) throws IllegalArgumentException{
        this.ap = Utils.requireNonNull(ap, "Appendable destination cannot be null.");
    }

    @Override
    public void showOptions() throws IOException {
        this.ap.append("Command Options: \n");
        this.ap.append("quit: quits using the program\n");
        this.ap.append("subwayRoutes: determines the subways routes of the MBTA system\n");
        this.ap.append("mostStops: determines the route with the most amount of stops and the count of stops\n");
        this.ap.append("leastStops: determines the route with the least amount of stops and the count of stops\n");
        this.ap.append("intersections: determines the stops that are intersections of two or more routes\n");
        this.ap.append("createPathTo <start> <dest>: determines a path of routes from the given starting to destination stops\n");
    }

    @Override
    public void renderMessage(String message) throws IOException {
        if (message != null) {
            this.ap.append(message);
        }
    }
}
