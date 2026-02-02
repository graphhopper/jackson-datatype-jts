package com.bedatadriven.jackson.datatype.jts;

import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.*;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import tools.jackson.databind.json.JsonMapper;


public class JtsModuleTest {
	private ObjectMapper mapper;
	@Before
	public void setupMapper() {

		mapper = JsonMapper.builder().addModule(new JtsModule()).build();
	}

	@Test(expected = DatabindException.class)
	public void invalidGeometryType() {
		String json = "{\"type\":\"Singularity\",\"coordinates\":[]}";
		mapper.readValue(json, Geometry.class);
	}
	
	@Test(expected = DatabindException.class)
	public void unsupportedGeometry() {
		Geometry unsupportedGeometry = EasyMock.createNiceMock("NonEuclideanGeometry", Geometry.class);
		EasyMock.replay(unsupportedGeometry);
		
		mapper.writeValue(System.out, unsupportedGeometry);
	}

}
