
# Jackson-datatype-jts

Jackson Module which provides custom serializers and deserializers for [JTS Geometry](https://projects.eclipse.org/projects/locationtech.jts) objects
using the [GeoJSON format](http://www.geojson.org/geojson-spec.html)

## Installation 

Releases of jackson-datatype-jts are available on Maven Central.

### Maven

To use the module in Maven-based projects, use following dependency:

```xml
<dependency>
  <groupId>com.graphhopper.external</groupId>
  <artifactId>jackson-datatype-jts</artifactId>
  <version>[latest]</version>
</dependency>    
```

GraphHopper updates compared to [upstream](https://github.com/bedatadriven/jackson-datatype-jts):

 * 2.14       uses JTS 1.19.0, requires Java 8
 * 0.12-2.5-1 uses JTS 1.15.1
 * 0.10-2.5-2 new groupId `com.graphhopper.external` and introduced JTS 1.15.0
 * 0.12-2.5-0 with the original jackson-databind dependency 2.4.2 and JTS 1.15.1
 * 0.12-2.5-1 with jackson-databind 2.9.6
 * 1.0-2.7    with jackson-databind 2.9.9 and JTS 1.16.0


## Usage

### Registering module

To use JTS geometry datatypes with Jackson, you will first need to register the module first (same as
with all Jackson datatype modules):

```java
ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JtsModule());
```

### Reading and Writing Geometry types

After registering JTS module, [Jackson Databind](https://github.com/FasterXML/jackson-databind)
will be able to write Geometry instances as GeoJSON and
and read GeoJSON geometries as JTS Geometry objects.

To write a Point object as GeoJSON:

```java
GeometryFactory gf = new GeometryFactory();
Point point = gf.createPoint(new Coordinate(1.2345678, 2.3456789));
String geojson = objectMapper.writeValueAsString(point);
```

You can also read GeoJSON in as JTS geometry objects:

```java
InputStream in;
Point point = mapper.readValue(in, Point.class);
```
