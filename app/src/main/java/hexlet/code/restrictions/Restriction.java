package hexlet.code.restrictions;

@FunctionalInterface
public interface Restriction<T> {

    boolean check(T object);
}
