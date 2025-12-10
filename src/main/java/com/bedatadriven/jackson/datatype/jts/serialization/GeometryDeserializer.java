package com.bedatadriven.jackson.datatype.jts.serialization;

import com.bedatadriven.jackson.datatype.jts.parsers.GeometryParser;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import org.locationtech.jts.geom.Geometry;
import tools.jackson.databind.deser.std.StdDeserializer;

/**
 * Created by mihaildoronin on 11/11/15.
 */
public class GeometryDeserializer<T extends Geometry> extends StdDeserializer<T> {

    private final GeometryParser<T> geometryParser;

    public GeometryDeserializer(GeometryParser<T> geometryParser) {
        super(Geometry.class);
        this.geometryParser = geometryParser;
    }

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonNode root = jsonParser.objectReadContext().readTree(jsonParser);
        return geometryParser.geometryFromJson(root, jsonParser);
    }
}
