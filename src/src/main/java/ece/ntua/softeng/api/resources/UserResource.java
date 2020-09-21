package ece.ntua.softeng.api.resources;

import ece.ntua.softeng.api.representations.JsonFrontisthrioRepresentation;
import ece.ntua.softeng.api.representations.JsonMapRepresentation;
import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
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
import java.util.Optional;

public class UserResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();
    private final User user = Configuration.getInstance().getUser();

    @Override
    protected Representation get() throws ResourceException {

        if (user.getPrivilege() < 1) throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "You do not have access");

        // Set an anchor for the user's id
        setAttribute("usr_id", Long.toString(user.getId()));

        User usr = dataAccess.getUser(user.getId());

        Optional<List> frs_optional = dataAccess.getUserFrontisthria(user.getId());
        List<Frontisthrio> frs = frs_optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "A single frontisthrio wasn't found"));

        Map<String, Object> map = new HashMap<>();
        map.put("user", usr);
        map.put("frontisthria", frs);

        return new JsonMapRepresentation(map);
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {

        if (user.getPrivilege() < 1) throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "You do not have access");

        // Set an anchor for the user's id
        setAttribute("usr_id", Long.toString(user.getId()));

        Form form = new Form(entity);

        String name = form.getFirstValue("name");
        String description = form.getFirstValue("description");
        Timestamp timestamp = Timestamp.valueOf(form.getFirstValue("timestamp"));
        String phonenumber = form.getFirstValue("phonenumber");

        Frontisthrio fr = dataAccess.addFrontisthrio(name, description, timestamp, phonenumber, user.getId());

        return new JsonFrontisthrioRepresentation(fr);
    }

    @Override
    protected Representation put(Representation entity) throws ResourceException {
        //TODO: update specific fields
        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }

}
