package ece.ntua.softeng.api;

import ece.ntua.softeng.api.representations.JsonMapRepresentation;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class SiteRepresentation extends WriterRepresentation {

    private Map<String, Object> map;
    private Representation packageJSONObject = new JsonMapRepresentation(map);

    public SiteRepresentation(Map<String, Object> map) {
        super(MediaType.TEXT_HTML);
        this.map = map;
    }

    public void write (Writer writer) throws IOException {
        writer.write(String.valueOf(packageJSONObject));
    }
}
