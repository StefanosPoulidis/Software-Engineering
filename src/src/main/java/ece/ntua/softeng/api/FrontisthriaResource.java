package ece.ntua.softeng.api;

import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
import ece.ntua.softeng.data.Limits;
import ece.ntua.softeng.data.model.Frontisthrio;
import ece.ntua.softeng.data.model.User;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrontisthriaResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    private final User user = Configuration.getInstance().getUser();

    @Override
    protected Representation get() throws ResourceException {

        if (user.getPrivilege() < 2) throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "You do not have access");

        List<Frontisthrio> frs = dataAccess.getFrontisthria(new Limits(0, 10));

        Map<String, Object> map = new HashMap<>();
        //map.put("start", xxx);
        //map.put("count", xxx);
        //map.put("total", xxx);
        map.put("frontisthria", frs);

        return new JsonMapRepresentation(map);
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {

        if (user.getPrivilege() < 1) throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "You do not have access");

        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        String name = form.getFirstValue("name");
        String description = form.getFirstValue("description");
        Timestamp timestamp = Timestamp.valueOf(form.getFirstValue("timestamp"));

        // Get id of the current user
        long user_id = user.getId();

        Frontisthrio fr = dataAccess.addFrontisthrio(name, description, timestamp, user_id);

        return new JsonFrontisthrioRepresentation(fr);
    }
}
