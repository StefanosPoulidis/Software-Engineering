package ece.ntua.softeng.api.representations;

import com.google.gson.Gson;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;

public class JsonLoginRepresentation extends WriterRepresentation {

    private final String userPass;

    public JsonLoginRepresentation(String userPass) {
        super(MediaType.APPLICATION_JSON);
        this.userPass = userPass;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(userPass));
    }
}
