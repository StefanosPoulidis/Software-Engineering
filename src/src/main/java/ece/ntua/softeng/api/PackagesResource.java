package ece.ntua.softeng.api;

import ece.ntua.softeng.data.model.Package;

import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
import ece.ntua.softeng.data.Limits;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

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

//        return new SiteRepresentation(map);
//        rep.write(new Writer());
        return new JsonMapRepresentation(map);
    }

}