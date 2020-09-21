package ece.ntua.softeng.api;

import com.google.gson.Gson;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class JsonFrontisthrioPackagesRepresentation extends WriterRepresentation {

    private final Map fr_pkgs_map;

    public JsonFrontisthrioPackagesRepresentation(Map fr_pkgs_map) {
        super(MediaType.APPLICATION_JSON);
//        this.fr = fr;
        this.fr_pkgs_map = fr_pkgs_map;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
//        writer.write(gson.toJson(fr));
        writer.write(gson.toJson(fr_pkgs_map));
    }
}
