package ece.ntua.softeng.api;

import ece.ntua.softeng.conf.Configuration;
import ece.ntua.softeng.data.DataAccess;
import ece.ntua.softeng.data.model.Package;
import ece.ntua.softeng.data.model.User;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.data.Status;

import java.util.Optional;

public class PackageResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    private final User user = Configuration.getInstance().getUser();

    @Override
    protected Representation delete() throws ResourceException {

        if (user.getPrivilege() < 1) throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "You do not have access");

        String idAttr = getAttribute("fr_id");
        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing frontisthrio id");
        }

        Long fr_id = null;
        try {
            fr_id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid frontisthrio id: " + idAttr);
        }

        if (!dataAccess.checkOwnership(user.getId(), fr_id))
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Frontisthrio with id " + user.getId() + " doesn't have access to manipulate the id: " + (fr_id+1));

        /* =========================================================================== */

        String pidAttr = getAttribute("pkg_id");
        if (pidAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing package id");
        }

        Long pkg_id = null;
        try {
            pkg_id = Long.parseLong(pidAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid frontisthrio id: " + pidAttr);
        }

        /* =========================================================================== */

        Optional<Package> pkg_optional = dataAccess.getPackage(pkg_id);
        Package pkg = pkg_optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "A single package with id " +
                pidAttr +" wasn't found"));

        dataAccess.deletePackage(pkg_id);

        return new JsonPackageRepresentation(pkg);
    }
}
