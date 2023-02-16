package hexlet.code.schema;

@FunctionalInterface
public interface Restriction<T> {

    boolean check(T object);
}
