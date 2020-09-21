package ece.ntua.softeng.api.resources;

import ece.ntua.softeng.api.representations.JsonUserRepresentation;
import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
import ece.ntua.softeng.data.model.User;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class SignoutResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();
    private final User user = Configuration.getInstance().getUser();

    @Override
    protected Representation delete() throws ResourceException {

        User usr = user;

        // Remove the privileges from the user
        Configuration.getInstance().setUser(new User());

        dataAccess.deleteUser(usr.getId());

        return new JsonUserRepresentation(usr);

    }

}
