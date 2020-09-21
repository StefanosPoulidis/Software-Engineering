package ece.ntua.softeng.api.resources;

import ece.ntua.softeng.api.representations.JsonUserRepresentation;
import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
import ece.ntua.softeng.data.model.User;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class LoginResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {

        Form form = new Form(entity);

        String username = form.getFirstValue("username");
        String password = form.getFirstValue("password");

        Configuration.getInstance().setUser(dataAccess.getUser(username, password));

        User user = Configuration.getInstance().getUser();

        return new JsonUserRepresentation(user);

    }
}
