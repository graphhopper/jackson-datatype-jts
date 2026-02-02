package com.bedatadriven.jackson.datatype.jts.parsers;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.JsonNode;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;

import static com.bedatadriven.jackson.datatype.jts.GeoJson.GEOMETRIES;

/**
 * Created by mihaildoronin on 11/11/15.
 */
public class GeometryCollectionParser extends BaseParser implements GeometryParser<GeometryCollection> {

    private final GenericGeometryParser genericGeometriesParser;

    public GeometryCollectionParser(GeometryFactory geometryFactory, GenericGeometryParser genericGeometriesParser) {
        super(geometryFactory);
        this.genericGeometriesParser = genericGeometriesParser;
    }

    private Geometry[] geometriesFromJson(JsonNode arrayOfGeoms, JsonParser jsonParser) throws DatabindException {
        Geometry[] items = new Geometry[arrayOfGeoms.size()];
        for(int i=0;i!=arrayOfGeoms.size();++i) {
            items[i] = genericGeometriesParser.geometryFromJson(arrayOfGeoms.get(i), jsonParser);
        }
        return items;
    }

    @Override
    public GeometryCollection geometryFromJson(JsonNode node, JsonParser jsonParser) throws DatabindException {
        return geometryFactory.createGeometryCollection(
                geometriesFromJson(node.get(GEOMETRIES), jsonParser));
    }
}
