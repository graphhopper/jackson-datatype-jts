package com.bedatadriven.jackson.datatype.jts.parsers;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.JsonNode;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import static com.bedatadriven.jackson.datatype.jts.GeoJson.COORDINATES;

/**
 * Created by mihaildoronin on 11/11/15.
 */
public class PointParser extends BaseParser implements GeometryParser<Point> {

    public PointParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    public static Coordinate coordinateFromJson(JsonNode array) {
        assert array != null && array.isArray() && (array.size() == 2 || array.size() == 3) : "expecting coordinate array with single point [ x, y, |z| ]";

        if (array.size() == 2) {
            return new Coordinate(
                    array.get(0).asDouble(),
                    array.get(1).asDouble());
        }

        return new Coordinate(
                array.get(0).asDouble(),
                array.get(1).asDouble(),
                array.get(2).asDouble());
    }

    public static Coordinate[] coordinatesFromJson(JsonNode array) {
        Coordinate[] points = new Coordinate[array.size()];
        for (int i = 0; i != array.size(); ++i) {
            points[i] = PointParser.coordinateFromJson(array.get(i));
        }
        return points;
    }

    public Point pointFromJson(JsonNode node) {
        return geometryFactory.createPoint(
                coordinateFromJson(node.get(COORDINATES)));
    }

    @Override
    public Point geometryFromJson(JsonNode node, JsonParser jsonParser) throws DatabindException {
        return pointFromJson(node);
    }
}
