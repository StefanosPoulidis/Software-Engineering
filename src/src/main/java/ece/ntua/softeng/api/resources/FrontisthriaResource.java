package ece.ntua.softeng.api.resources;

import ece.ntua.softeng.api.representations.JsonMapRepresentation;
import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
import ece.ntua.softeng.data.Limits;
import ece.ntua.softeng.data.model.Frontisthrio;
import ece.ntua.softeng.data.model.User;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrontisthriaResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    private final User user = Configuration.getInstance().getUser();

    @Override
    protected Representation get() throws ResourceException {

        List<Frontisthrio> frs = dataAccess.getFrontisthria(new Limits(0, 10));

        Map<String, Object> map = new HashMap<>();
        //map.put("start", xxx);
        //map.put("count", xxx);
        //map.put("total", xxx);
        map.put("frontisthria", frs);

        return new JsonMapRepresentation(map);
    }

}
