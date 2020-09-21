package ece.ntua.softeng.data;

import ece.ntua.softeng.data.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id             = rs.getLong("id");
        String username     = rs.getString("username");
        String password     = rs.getString("password");
        String email        = rs.getString("email");
        short privilege     = rs.getShort("privilege");

        return new User(id, username, password, email, privilege);
    }
}
