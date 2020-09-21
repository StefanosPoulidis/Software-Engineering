package ece.ntua.softeng.api.resources;

import ece.ntua.softeng.api.representations.JsonMapRepresentation;
import ece.ntua.softeng.data.geomodel.Geometry;
import ece.ntua.softeng.data.geomodel.Property;
import ece.ntua.softeng.data.model.Frontisthrio;
import ece.ntua.softeng.data.model.Package;

import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
import ece.ntua.softeng.data.Limits;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackagesResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {


        List<Package> pkgs = dataAccess.getPackages(new Limits(0, 10));

        Map<String, Object> map = new HashMap<>();
        //map.put("start", xxx);
        //map.put("count", xxx);
        //map.put("total", xxx);
        map.put("packages", pkgs);

        return new JsonMapRepresentation(map);
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {

        Form form = new Form(entity);

        float latitude = Float.parseFloat(form.getFirstValue("latitude"));
        float longitude = Float.parseFloat(form.getFirstValue("longitude"));
        int radius = Integer.parseInt(form.getFirstValue("radius"));


        List<Package> pkgs = dataAccess.getPackagesByDistance(latitude, longitude, radius);

        /* ===================== Convert to GeoJson ====================== */

        List<Map<String, Object>> features = new ArrayList<>();

        for (int i = 0; i < pkgs.size(); i++) {

            Package pkg = pkgs.get(i);
            Frontisthrio fr = dataAccess.getFrontisthrio(pkg.getFrontisthrioId()).orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "The frontisthrio or package doesn't exist"));

            Map<String, Object> geoUnit = new HashMap<>();
            geoUnit.put("type", "Feature");
            geoUnit.put("geometry", new Geometry(pkg.getLatitude(), pkg.getLongitude()));
            geoUnit.put("properties", new Property(pkg.getId(), pkg.getField(), pkg.getPrice(), pkg.getStreet(), pkg.getStreetNumber(), fr.getName()));

            features.add(i, geoUnit);

        }

        Map<String, Object> geoJSONMap = new HashMap<>();
        geoJSONMap.put("type", "FeatureCollection");
        geoJSONMap.put("features", features);

        // Clean up the packages list
        pkgs.clear();

        return new JsonMapRepresentation(geoJSONMap);

    }

}