import java.io.IOException;

/**
 * Interface with operations for a text-based view that prints information to the user about the
 * available command options and messages.
 */
public interface IMBTAView {

    /**
     * Appends the accepted text-based command options to the specified data destination.
     *
     * @throws IOException if transmission to the provided data destination fails
     */
    void showOptions() throws IOException;

    /**
     * Appends {@code message} to the specified destination. If the message is null, prints nothing.
     *
     * @param message string with the message to be rendered.
     * @throws IOException if transmission of the message to the provided data destination fails
     */
    void renderMessage(String message) throws IOException;
}
