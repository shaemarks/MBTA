/**
 * Interface for a controller for a MBTA route processor. An implementation will work with the IMBTAModel interface
 * to provide operations and retrive information about the MBTA routes.
 */
public interface IMBTAController {

    /**
     * Await and process commands about the routes of the MBTA system.
     */
    void processCommands();
}
