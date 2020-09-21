package ece.ntua.softeng.api;

import com.google.gson.Gson;
import org.restlet.data.MediaType;
import ece.ntua.softeng.data.model.Package;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;

public class JsonPackageRepresentation extends WriterRepresentation {

    private final Package pkg;

    public JsonPackageRepresentation (Package pkg) {
        super(MediaType.APPLICATION_JSON);
        this.pkg = pkg;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(pkg));
    }
}
