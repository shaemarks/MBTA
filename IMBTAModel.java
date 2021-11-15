import pair.IPair;

import java.net.ProtocolException;
import java.util.List;

/**
 * This is an interface representing the model (in a MVC design structure) that queries and processes information about
 * routes of the Massachusetts Bay Transportation Authority system.
 */
public interface IMBTAModel {

    /**
     * Retrieves the subway routes of the MBTA system and stores their long names in a list.
     * @return a list a strings where each element is a distinct subway route of the MBTA system
     */
    List<String> retrieveSubwayRoutes() throws ProtocolException;

    /**
     * Determines the subway route with the most stops and the count of its stops.
     * @return a pair where the first element is the name of the subway route with the most stops and the second
     * element is the number of stops
     */
    IPair<String, Integer> findRouteMostStops();

    /**
     * Determines the subway route with the least stops and the count of its stops.
     * @return a pair where the first element is the name of the subway route with the least stops and the second
     * element is the number of stops
     */
    IPair<String, Integer> findRouteLeastStops();

    /**
     * Finds all of the intersection stops that connect two or more subway routes along with their corresponding route
     * names for each stop.
     * @return a list of pairs, where the first element of each pair is the name of an intersecting stop and the second
     * element is a list of the names of the routes the stop connects
     */
    List<IPair<String, List<String>>> getIntersections();

    /**
     * Determines routes that can be taken to travel from the MBTA stops {@code start} to {@code dest}.
     * @param start the name of the starting MBTA stop
     * @param dest the name of the destination MBTA stop
     * @return a list of the names of routes that could be taken to travel from {@code start} to {@code dest}.
     * @throws IllegalArgumentException if {@code start} or {@code dest} are null or not valid stops on the MBTA subway
     * routes
     */
    List<String> findConnectingRoutes(String start, String dest) throws IllegalArgumentException;
}
