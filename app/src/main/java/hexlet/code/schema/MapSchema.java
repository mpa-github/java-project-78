package hexlet.code.schema;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        this.isRequired = true;
        return this;
    }

    public MapSchema sizeOf(int size) {
        Restriction<Object> restriction = (map) -> map instanceof Map<?, ?>
                && ((Map<?, ?>) map).size() == size;
        this.restrictions.put(EnumRestriction.SIZE_OF, restriction);
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
        this.restrictions.put(EnumRestriction.SHAPE, restriction);
        return this;
    }
}
