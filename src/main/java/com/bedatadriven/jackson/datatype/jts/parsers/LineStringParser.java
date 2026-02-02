package com.bedatadriven.jackson.datatype.jts.parsers;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.JsonNode;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import static com.bedatadriven.jackson.datatype.jts.GeoJson.COORDINATES;

/**
 * Created by mihaildoronin on 11/11/15.
 */
public class LineStringParser extends BaseParser implements GeometryParser<LineString> {

    public LineStringParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    public LineString lineStringFromJson(JsonNode root) {
        return geometryFactory.createLineString(
                PointParser.coordinatesFromJson(root.get(COORDINATES)));
    }

    @Override
    public LineString geometryFromJson(JsonNode node, JsonParser jsonParser) throws DatabindException {
        return lineStringFromJson(node);
    }
}
