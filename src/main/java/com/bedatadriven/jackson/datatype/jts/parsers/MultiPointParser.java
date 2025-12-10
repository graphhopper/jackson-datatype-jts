package com.bedatadriven.jackson.datatype.jts.parsers;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.JsonNode;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;

import static com.bedatadriven.jackson.datatype.jts.GeoJson.COORDINATES;

/**
 * Created by mihaildoronin on 11/11/15.
 */
public class MultiPointParser extends BaseParser implements GeometryParser<MultiPoint> {

    public MultiPointParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    public MultiPoint multiPointFromJson(JsonNode root) {
        return geometryFactory.createMultiPointFromCoords(
                PointParser.coordinatesFromJson(root.get(COORDINATES)));
    }

    @Override
    public MultiPoint geometryFromJson(JsonNode node, JsonParser jsonParser) throws DatabindException {
        return multiPointFromJson(node);
    }
}
