package ece.ntua.softeng.api.resources;

import ece.ntua.softeng.api.representations.JsonLoginRepresentation;
import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class SignupResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post (Representation entity) throws ResourceException {

        Form form = new Form(entity);

        String username = form.getFirstValue("username");
        String password = form.getFirstValue("password");
        String email = form.getFirstValue("email");

        if (username == null | password == null | email == null)
            throw new ResourceException(Status.CLIENT_ERROR_NOT_ACCEPTABLE, "You have to type all the necessary fields to sign up");

        dataAccess.addUser(0, username, password, email, (short) 1);

        return new JsonLoginRepresentation(username+" "+password);

    }

}
