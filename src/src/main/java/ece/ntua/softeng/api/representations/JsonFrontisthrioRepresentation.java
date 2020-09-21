package ece.ntua.softeng.api.representations;


import com.google.gson.Gson;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;


import ece.ntua.softeng.data.model.Frontisthrio;

public class JsonFrontisthrioRepresentation extends WriterRepresentation {

    private final Frontisthrio fr;

    public JsonFrontisthrioRepresentation(Frontisthrio fr) {
        super(MediaType.APPLICATION_JSON);
        this.fr = fr;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(fr));
    }
}
