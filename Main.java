import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that contains the main method for interaction with the MBTA route processor program. The main method uses the
 * {@link MBTAModel} implementation of the model, the {@link MBTAController} implementation of the controller, and
 * the {@link MBTAView} implementation of the view. Input will be taken from the keyboard and output will be printed to
 * the console.
 */
public class Main {

    /**
     * Runs the MBTA route processing program.
     */
    public static void main(String[] args) {
        IMBTAModel model;
        try {
            model = new MBTAModel();
        } catch (IOException e) {
            throw new IllegalStateException("Could not properly connect to server socket.");
        }

        IMBTAView view = new MBTAView(System.out);
        Readable input = new InputStreamReader(System.in);
        IMBTAController controller = new MBTAController(input, view, model);

        controller.processCommands();
    }
}
