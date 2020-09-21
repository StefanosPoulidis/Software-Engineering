package ece.ntua.softeng.data;

import ece.ntua.softeng.data.model.Package;
import ece.ntua.softeng.data.model.Fields;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageRowMapper implements RowMapper {

    @Override
    public Package mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id                 = rs.getLong("id");
        Fields field            = Fields.valueOf(rs.getString("field"));
        short price             = rs.getShort("price");
        int success_rate        = rs.getInt("success_rate");
        String city             = rs.getString("city");
        String street           = rs.getString("street");
        int street_number       = rs.getInt("street_number");
        float latitude          = rs.getFloat("latitude");
        float longitude         = rs.getFloat("longitude");
        long frontisthrio_id    = rs.getLong("frontisthrio_id");

        return new Package(id, field, price, success_rate, city, street, street_number, latitude, longitude, frontisthrio_id);
    }

}

