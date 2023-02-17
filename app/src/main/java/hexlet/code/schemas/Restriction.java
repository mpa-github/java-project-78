package hexlet.code.schemas;

@FunctionalInterface
public interface Restriction<T> {

    boolean check(T object);
}
