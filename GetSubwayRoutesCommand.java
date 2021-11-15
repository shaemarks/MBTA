import java.io.IOException;
import java.net.ProtocolException;
import java.util.List;

/**
 * Command to retrieve all of the subway routes of a MBTA system using a given IMBTAModel to obtain this information.
 */
public class GetSubwayRoutesCommand implements IMBTACommand {
    private final IMBTAView view;

    /**
     * Constructs a command object for retrieving the subway routes of an MBTA system.
     * @param view the view to display the subway routes found
     * @throws IllegalArgumentException if {@code view} is null
     */
    public GetSubwayRoutesCommand(IMBTAView view) throws IllegalArgumentException {
        this.view = Utils.requireNonNull(view, "View cannot be null");
    }

    @Override
    public void runCommand(IMBTAModel model) throws IllegalArgumentException, IllegalStateException, ProtocolException {
        Utils.requireNonNull(model, "Model cannot be null");
        List<String> routes = model.retrieveSubwayRoutes();

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
