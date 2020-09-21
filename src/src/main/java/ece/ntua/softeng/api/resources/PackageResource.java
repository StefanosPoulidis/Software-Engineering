package ece.ntua.softeng.api.resources;

import ece.ntua.softeng.api.representations.JsonPackageRepresentation;
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
    protected Representation get() {

        String idAttr = getAttribute("pkg_id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing package id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch (Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_NOT_ACCEPTABLE, "Invalid package id: " + idAttr);
        }

        Optional<Package> pkg_optional = dataAccess.getPackage(id);
        Package pkg = pkg_optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_ACCEPTABLE, "Package not found - id: " + idAttr));

        return new JsonPackageRepresentation(pkg);

    }

    @Override
    protected Representation delete() throws ResourceException {

        if (user.getPrivilege() < 1)
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "You do not have access");

        String idAttr = getAttribute("pkg_id");
        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing package id");
        }

        Long pkg_id = null;
        try {
            pkg_id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid package id: " + idAttr);
        }

        Optional<Package> pkg_optional = dataAccess.getPackage(pkg_id);
        Package pkg = pkg_optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_ACCEPTABLE,
                "The specific package with id " + idAttr + " doesn't exist"));

        Long fr_id = pkg.getFrontisthrioId();
        if (!dataAccess.checkOwnership(fr_id, user.getId()))
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Canceling request. Tried to access unauthorised resources");

        dataAccess.deletePackage(pkg_id);

        return new JsonPackageRepresentation(pkg);

    }

}
