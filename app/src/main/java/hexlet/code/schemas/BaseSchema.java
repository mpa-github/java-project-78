package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseSchema {

    protected final Map<EnumRestriction, Restriction<Object>> restrictions = new LinkedHashMap<>();
    protected boolean isRequired = false;

    public boolean isValid(Object object) {
        if (object == null) {
            return !this.isRequired;
        }
        for (Map.Entry<EnumRestriction, Restriction<Object>> entry : this.restrictions.entrySet()) {
            Restriction<Object> nextRestriction = entry.getValue();
            if (!nextRestriction.check(object)) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        this.isRequired = false;
        this.restrictions.clear();
    }
}
