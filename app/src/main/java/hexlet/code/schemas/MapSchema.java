package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        setRequired();
        return this;
    }

    public MapSchema sizeof(int size) {
        Restriction<Object> restriction = (object) -> object instanceof Map<?, ?>
                && ((Map<?, ?>) object).size() == size;
        addRestriction(EnumRestriction.SIZE_OF, restriction);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        Restriction<Object> restriction = object -> {
            if (!(object instanceof Map<?, ?> mapToValidate)) {
                return false;
            }
            for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema keyValueSchema = entry.getValue();
                if (mapToValidate.containsKey(key) && !keyValueSchema.isValid(mapToValidate.get(key))) {
                    return false;
                }
            }
            return true;
        };
        addRestriction(EnumRestriction.SHAPE, restriction);
        return this;
    }
}
