package com.bedatadriven.jackson.datatype.jts.parsers;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.JsonNode;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.HashMap;
import java.util.Map;

import static com.bedatadriven.jackson.datatype.jts.GeoJson.*;

/**
 * Created by mihaildoronin on 11/11/15.
 */
public class GenericGeometryParser extends BaseParser implements GeometryParser<Geometry> {

    private final Map<String, GeometryParser<? extends Geometry>> parsers;

    public GenericGeometryParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
        parsers = new HashMap<String, GeometryParser<? extends Geometry>>();
        parsers.put(POINT, new PointParser(geometryFactory));
        parsers.put(MULTI_POINT, new MultiPointParser(geometryFactory));
        parsers.put(LINE_STRING, new LineStringParser(geometryFactory));
        parsers.put(MULTI_LINE_STRING, new MultiLineStringParser(geometryFactory));
        parsers.put(POLYGON, new PolygonParser(geometryFactory));
        parsers.put(MULTI_POLYGON, new MultiPolygonParser(geometryFactory));
        parsers.put(GEOMETRY_COLLECTION, new GeometryCollectionParser(geometryFactory, this));
    }

    @Override
    public Geometry geometryFromJson(JsonNode node, JsonParser jsonParser) throws DatabindException {
        String typeName = node.get(TYPE).asString();
        GeometryParser<? extends Geometry> parser = parsers.get(typeName);
        if (parser != null) {
            return parser.geometryFromJson(node, jsonParser);
        }
        else {
            throw DatabindException.from(jsonParser,"Invalid geometry type: " + typeName);
        }
    }
}
