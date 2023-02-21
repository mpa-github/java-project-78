package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseSchema {

    private final Map<EnumRestriction, Restriction<Object>> restrictions = new LinkedHashMap<>();
    private boolean isRequired = false;

    public final boolean isValid(Object object) {
        if (!isRequired && object == null) {
            return true;
        }
        for (Map.Entry<EnumRestriction, Restriction<Object>> entry : this.restrictions.entrySet()) {
            Restriction<Object> nextRestriction = entry.getValue();
            if (!nextRestriction.check(object)) {
                return false;
            }
        }
        return true;
    }

    public final void clear() {
        this.isRequired = false;
        this.restrictions.keySet().removeIf(key -> !(key.equals(EnumRestriction.IS_INSTANCE)));
    }

    protected final void addRestriction(EnumRestriction name, Restriction<Object> restriction) {
        this.restrictions.put(name, restriction);
    }

    protected final void setRequired() {
        this.isRequired = true;
    }
}
