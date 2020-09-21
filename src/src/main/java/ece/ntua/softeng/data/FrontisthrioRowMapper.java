package ece.ntua.softeng.data;

import java.sql.Timestamp;

import ece.ntua.softeng.data.model.Frontisthrio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FrontisthrioRowMapper implements RowMapper {

    @Override
    public Frontisthrio mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id                  = rs.getLong("id");
        String name              = rs.getString("name");
        String description       = rs.getString("description");
        Timestamp timestamp      = rs.getTimestamp("timestamp");
        String phonenumber       = rs.getString("phonenumber");
        long user_id             = rs.getLong("user_id");

        return new Frontisthrio(id, name, description, timestamp, phonenumber, user_id);
    }
}
