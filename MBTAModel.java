import pair.IPair;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Implementation of a model of a MBTA processor that can query and process information about MBTA routes
 */
public class MBTAModel implements IMBTAModel {
    private final HttpURLConnection con;

    /**
     * Creates a MBTAModel that obtains information from the HTTP requests from {@code url}
     * @param url the url of the MBTA API to obtain information about the route system
     * @throws IllegalArgumentException if {@code url} is null
     * @throws IOException if opening the connection through the url fails
     */
    public MBTAModel(URL url) throws IllegalArgumentException, IOException {
        this.con = (HttpURLConnection) Utils.requireNonNull(url, "url cannot be null").openConnection();
        con.setRequestMethod("GET");
    }

    /**
     * Default constructor for creating an MBTAModel that relies on the MBTA API url
     * @throws MalformedURLException if the URL created by the address is nost structured correctly
     * @throws IOException if opening the connection through the url fails
     */
    public MBTAModel() throws IOException {
        this(new URL("https://api-v3.mbta.com/portal"));
    }


    @Override
    public List<String> retrieveSubwayRoutes() {
        //I would use a GET request to retrieve the lines of the MBTA station
        //on first implementation, I may let the API filter itself by first retrieving the lines from
        //of type 0 and then type 1

        //however, filtering locally should have the advantage of ensuring that valid subways routes aren't left behind
        //so with more experience, I might update to filtering locally
        return null;
    }

    @Override
    public IPair<String, Integer> findRouteMostStops() {
        //ideally, the routes would be stored as objects with their name, their type, and a list of their stops
        //(obtained from retrieveSubwayRoutes above)
        //to determine the route with the most stops, have a String local variable for the name of the route
        // and an integer local variable for the number of stops of the route currently known to have the most

        //when analyzing a new route, check the size of the list of stops
        //if greater than the current max, update the local variables and continue

        //return as a new pair object
        return null;
    }

    @Override
    public IPair<String, Integer> findRouteLeastStops() {
        //same as above but now care about the minimum
        return null;
    }

    @Override
    public List<IPair<String, List<String>>> getIntersections() {
        //create a hashmap that is string to list of string

        //iterate through all known subway routes and their list of stops

        //iterate through list of stops for a route

        //if the name of the stop is not currently in the hashmap, add it as the key with a value that is a single
        //element list containing the name of the subway route

        //if name of the stop is currently in the hashmap, add the name of the subway line to the value associated with
        //the stop if the name of the route does not currently belong to the list

        //convert to a list --> only include stops whose list of routes has a size greater than 1
        return null;
    }

    @Override
    public List<String> findConnectingRoutes(String start, String dest) throws IllegalArgumentException {
        //could represent all subway routes as nodes in a graph

        //if there exists an intersection who belongs to two of the routes, connect the nodes associated with the
        //routes by an undirected edge (does not need to be weighted bc problem just asked for A path, but the
        //most efficient path)

        //determine the routes that the start and destination stops belong to (will become the start and destination
        //nodes)

        //perform DFS on the graph, keeping track of the parent of each node so we can backtrack at the end and \
        //determine the particular path
        return null;
    }
}
