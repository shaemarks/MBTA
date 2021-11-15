import java.io.IOException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Implementation of IMBTAController for processing user input / commands to retrieve and perform operations on data
 * about MBTA routes and stops.
 */
public class MBTAController implements IMBTAController {
    private final Readable rd;
    private final IMBTAView view;
    private final IMBTAModel model;
    protected final Map<String, Function<Scanner, IMBTACommand>> knownCommands;

    /**
     * Constructs a controller for a MBTA route processor.
     * @param rd a readable where the user supplies commands for interacting with the image processor
     * @param view a view for displaying information processed by the model
     * @param model a model for an MBTA route processor that queries and processes information about routes and stops
     * @throws IllegalArgumentException if any of the arguments are null
     */
    public MBTAController(Readable rd, IMBTAView view, IMBTAModel model) throws IllegalArgumentException {
        this.rd = Utils.requireNonNull(rd, "Readable cannot be null.");
        this.view = Utils.requireNonNull(view, "View cannot be null.");
        this.model = Utils.requireNonNull(model, "Model cannot be null.");

        this.knownCommands = new HashMap<>();
        this.knownCommands.putIfAbsent("subwayRoutes", s -> new GetSubwayRoutesCommand(this.view));
        this.knownCommands.putIfAbsent("mostStops", s -> new MostStopsCommand(this.view));
        this.knownCommands.putIfAbsent("leastStops", s -> new LeastStopsCommand(this.view));
        this.knownCommands.putIfAbsent("intersections", s -> new FindIntersectionsCommand(this.view));
        this.knownCommands.putIfAbsent("createPathTo", s -> new CreatePathToCommand(this.view, s.next(), s.next()));
    }


    @Override
    public void processCommands() {
    //scanner to process the contents of the readable
        Scanner scanner = new Scanner(this.rd);

        //tell view to show options for processing info in the MBTA system
        this.showOptionsView();

        while(scanner.hasNext()) {
            //retrieve the next command
            String input = scanner.next();

            //end program if the user decides to quit
            if (input.equalsIgnoreCase("quit")) { //quit program
                this.printMsgView("Quitting program.\n");
                return;
            }

            Function<Scanner, IMBTACommand> command = this.knownCommands.getOrDefault(input, null);

            //if this is not a known command, print an error message

            if (command == null) {
                this.printMsgView("Unknown command. Try again.\n\n");

            } else { //valid command; take in additional info if necessary

                IMBTACommand cmd;
                try { //try to parse additional input needed for the command
                    cmd = command.apply(scanner);
                } catch (NoSuchElementException e) {
                    throw new IllegalStateException("Ran out of input.\n");
                }

                try { //perform command on the model
                    cmd.runCommand(this.model);
                } catch (IllegalArgumentException | ProtocolException e) { //command failed in some way
                    this.printMsgView("Command failed. Try again. " + e.getMessage() + "\n\n");
                }

                //print available commands
                this.printMsgView("\n\n");
                this.showOptionsView();
            }
        }


    }


    /**
     * Tells the view to display the given message
     *
     * @param message the message to be printed to the user with the view
     * @throws IllegalStateException if an IO exception is thrown because transmission failed
     */
    private void printMsgView(String message) {
        try {
            this.view.renderMessage(message);
        } catch (IOException e) {
            throw new IllegalStateException("Transmission via view failed.");
        }
    }

    /**
     * Tells the view to display the commands offered to the user.
     * @throws IllegalStateException if an IO exception is thrown because transmission failed
     */
    private void showOptionsView() throws IllegalStateException{
        try {
            this.view.showOptions();
            this.printMsgView("\n\n");
        } catch (IOException e) {
            throw new IllegalStateException("Transmission via view failed.");
        }
    }
}
