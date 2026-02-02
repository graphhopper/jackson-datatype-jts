package com.bedatadriven.jackson.datatype.jts.parsers;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.JsonNode;
import org.locationtech.jts.geom.Geometry;

/**
 * Created by mihaildoronin on 11/11/15.
 */
public interface GeometryParser<T extends Geometry> {

    T geometryFromJson(JsonNode node, JsonParser jsonParser) throws DatabindException;

}
