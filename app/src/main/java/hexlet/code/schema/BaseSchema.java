package hexlet.code.schema;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseSchema<T, E> {

    protected final Map<E, Restriction<T>> restrictions = new LinkedHashMap<>();
    protected final Class<T> thisClass;

    public BaseSchema(Class<T> thisClass) {
        this.thisClass = thisClass;
    }

    public boolean isValid(Object object) {
        if (object != null && !(thisClass.isInstance(object))) {
            return false;
        }
        T thisClassObject = thisClass.cast(object);
        for (Map.Entry<E, Restriction<T>> entry : restrictions.entrySet()) {
            Restriction<T> nextRestriction = entry.getValue();
            if (!nextRestriction.check(thisClassObject)) {
                return false;
            }
        }
        return true;
    }
}
