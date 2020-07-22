package com.bedatadriven.jackson.datatype.jts;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;

import org.junit.Test;

/**
 * Created by mihaildoronin on 11/11/15.
 */
public class PointTest extends BaseJtsModuleTest<Point> {
    @Override
    protected Class<Point> getType() {
        return Point.class;
    }

    @Override
    protected String createGeometryAsGeoJson() {
        return "{\"type\":\"Point\",\"coordinates\":[1.2345678,2.3456789]}";
    }

    @Override
    protected Point createGeometry() {
        return gf.createPoint(new Coordinate(1.2345678, 2.3456789));
    }
    
    /**
     * Expectation that a malformed json tree node will perform an assertion of not null rather
     * than an unexpected {@link NullPointerException} being thrown.
     * @throws Exception
     */
    @Test(expected = AssertionError.class)
    public void shouldThrowDeserializeConcreteType() throws Exception {
    	
        // This malformed value has a typo of: 'cordinates'
        String malformedSerializedPoint = "{\"type\":\"Point\",\"cordinates\":[1.2345678,2.3456789]}";
        this.mapper.readValue(malformedSerializedPoint, getType());
    }
}
