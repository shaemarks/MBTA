/**
 * Utility class that contains useful methods that do not belong to a particular class or interface
 * in an MBTA processor. This class contains a single method that checks if an argument is null.
 */
public class Utils {

    /**
     * Checks that {@code obj} is not null, and throws an exception with {@code message} if it is.
     *
     * @param obj     the object that should not be null
     * @param message an informative message for the exception explaining why it failed
     * @param <T>     any object type
     * @return {@code obj} if it is not null
     * @throws IllegalArgumentException if {@code obj} or {@code message} are null
     */
    public static <T> T requireNonNull(T obj, String message) throws IllegalArgumentException {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }
}