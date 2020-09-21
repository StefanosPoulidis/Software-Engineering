package ece.ntua.softeng.api;

import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
import org.restlet.data.Form;
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

        dataAccess.addUser(0, username, password, (short) 1);

        return new JsonLoginRepresentation(username+" "+password);

    }

}
