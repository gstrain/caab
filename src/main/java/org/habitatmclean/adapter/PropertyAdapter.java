package org.habitatmclean.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.habitatmclean.entity.Property;

import java.lang.reflect.Type;

public class PropertyAdapter implements JsonSerializer<Property> {
    @Override
    public JsonElement serialize(Property property, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("property_no", property.getProperty_no());
        return jsonObject;
    }
}
