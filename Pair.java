package pair;

/**
 * Represents a pairing of two objects. This implementation of {@link IPair} does not guarantee that
 * the items are non-null to allow for situations where there is no appropriate second value. This
 * implementation should be used with caution since getters return the actual reference to the
 * field.
 *
 * @param <K> The data type of the first item in the pair
 * @param <V> The data type of the second item in the pair
 */
public class Pair<K,V> implements IPair<K,V> {
    private final K first;
    private final V second;

    /**
     * Constructs a pair containing {@code first} and {@code second}.
     *
     * @param first  the first item in this pair
     * @param second the second item in this pair
     */
    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public K getFirst() {
        return this.first;
    }

    @Override
    public V getSecond() {
        return this.second;
    }
}
