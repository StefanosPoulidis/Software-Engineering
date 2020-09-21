package ece.ntua.softeng.api.resources;

import ece.ntua.softeng.api.representations.JsonMapRepresentation;
import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
import ece.ntua.softeng.data.Limits;
import ece.ntua.softeng.data.model.User;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();
    private final User user = Configuration.getInstance().getUser();

    @Override
    protected Representation get() throws ResourceException {

//        if (user.getPrivilege() < 2) throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "You do not have access");

        List<User> users = dataAccess.getUsers(new Limits(0, 10));
        Map<String, Object> map = new HashMap<>();

        map.put("users", users);

        return new JsonMapRepresentation(map);
    }

}