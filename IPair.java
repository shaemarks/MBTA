package pair;

/**
 * Represents a grouping of two values.
 *
 * @param <K> The data type for first item in the pair
 * @param <V> The data type for second item in the pair
 */
public interface IPair<K,V> {

    /**
     * Gets the first item in this pair.
     *
     * @return the first object stored in this pair.
     */
    K getFirst();

    /**
     * Gets the second item in this pair.
     *
     * @return the second object stored in this pair.
     */
    V getSecond();
}
