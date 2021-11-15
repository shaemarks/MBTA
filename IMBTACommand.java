import java.net.ProtocolException;

/**
 * Represents an operation for a IMBTAModel
 */
public interface IMBTACommand {

    /**
     * Performs the command on {@code model}
     * @param model the MBTA processor model to perform a command with
     * @throws IllegalArgumentException if {@code model} is null
     * @throws IllegalStateException if transmission of the result to the view fails
     */
    void runCommand(IMBTAModel model) throws IllegalArgumentException, IllegalStateException, ProtocolException;
}
