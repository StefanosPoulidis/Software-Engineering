package ece.ntua.softeng.api;

import ece.ntua.softeng.api.resources.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RestfulApp extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {

        Router router = new Router(getContext());

        // [GET]
        router.attach("/users", UsersResource.class);

        // [GET]
        router.attach("/frontisthria", FrontisthriaResource.class);

        // [GET]
        router.attach("/packages", PackagesResource.class);

        // [GET, POST, PUT]
        router.attach("/users/{usr_id}", UserResource.class);

        // [GET, POST, DELETE]
        router.attach("/frontisthria/{fr_id}", FrontisthrioResource.class);

        // [GET, DELETE]
        router.attach("/packages/{pkg_id}", PackageResource.class);

        // [POST]
        router.attach("/login", LoginResource.class);

        // [POST]
        router.attach("/sign-up", SignupResource.class);

        // [DELETE]
        router.attach("/sign-out", SignoutResource.class);

        return router;
    }
}
