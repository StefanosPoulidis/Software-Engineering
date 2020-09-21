package ece.ntua.softeng.data;

import ece.ntua.softeng.data.model.Package;
import ece.ntua.softeng.data.model.Frontisthrio;
import ece.ntua.softeng.data.model.Fields;
import ece.ntua.softeng.data.model.User;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import java.sql.Timestamp;

public class DataAccess {

    private static final Object[] EMPTY_ARGS = new Object[0];

    private static final int MAX_TOTAL_CONNECTIONS = 16;
    private static final int MAX_IDLE_CONNECTIONS = 8;

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setup(String driverClass, String url, String user, String pass) throws SQLException {

        //initialize the data source
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(driverClass);
        bds.setUrl(url);
        bds.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        bds.setMaxIdle(MAX_IDLE_CONNECTIONS);
        bds.setUsername(user);
        bds.setPassword(pass);
        bds.setValidationQuery("SELECT 1");
        bds.setTestOnBorrow(true);
        bds.setDefaultAutoCommit(true);

        //check that everything works OK
        bds.getConnection().close();

        //initialize the jdbc template utility
        jdbcTemplate = new JdbcTemplate(bds);
    }

    /**************************** Delete Functions ***************************/

    public void deleteUser(long id) {
        // Delete a User with a specific id
        jdbcTemplate.execute("delete from users where id = ?",new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setLong(1, id);

                return ps.execute();

            }
        });
    }

    public void deleteFrontisthrio(long id) {
        // Delete a Frontisthrio with a specific id
        jdbcTemplate.execute("delete from frontisthrio where id = ?",new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setLong(1, id);

                return ps.execute();

            }
        });
    }

    public void deletePackage(long id) {
        // Delete a Package with a specific id
        jdbcTemplate.execute("delete from package where id = ?",new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setLong(1, id);

                return ps.execute();

            }
        });
    }

    /**********************************************************************/

    /**************************** Get Functions ***************************/

    public List<User> getUsers(Limits limits) {
        return jdbcTemplate.query("select * from users order by id", EMPTY_ARGS, new UserRowMapper());
    }

    public List<Frontisthrio> getFrontisthria(Limits limits) {
        //TODO: Support limits
        return jdbcTemplate.query("select * from frontisthrio order by id", EMPTY_ARGS, new FrontisthrioRowMapper());
    }

    public List<Package> getPackages(Limits limits) {
        //TODO: Support limits
        return jdbcTemplate.query("select id,field,price,success_rate,city,street,street_number,description,ST_X(geopoint) as latitude, ST_Y(geopoint) as longitude,frontisthrio_id from package order by id", EMPTY_ARGS, new PackageRowMapper());
    }

    public List<Package> getPackagesByDistance(float latitude, float longitude, int radius) {
        Integer[] params = new Integer[]{radius};

        jdbcTemplate.execute("set @userPoint = ST_GeomFromText(ST_AsText(Point(?,?)), 4326);", new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setFloat(1, latitude);
                ps.setFloat(2, longitude);

                return ps.execute();

            }
        });

        return jdbcTemplate.query("select id,field,price,success_rate,city,street,street_number,description,ST_X(geopoint) as latitude, ST_Y(geopoint) as longitude,frontisthrio_id from package where ST_Distance(geopoint, @userPoint) <= ?", params, new PackageRowMapper());

    }

    public User getUser(long id) {
        Long[] params = new Long[]{id};
        List<User> usrs = jdbcTemplate.query("select * from users where id = ?", params, new UserRowMapper());
        if (usrs.size() == 1) {
            return usrs.get(0);
        }
        else {
            return new User();
        }
    }

    public User getUser(String username, String password) {
        String[] params = new String[]{username, password};
        List<User> usrs = jdbcTemplate.query("select * from users where username = ? and password = ?", params, new UserRowMapper());
        if (usrs.size() == 1) {
            return usrs.get(0);
        }
        else {
            return new User();
        }
    }

    public Optional<Frontisthrio> getFrontisthrio(long id) {
        Long[] params = new Long[]{id};
        List<Frontisthrio> fr = jdbcTemplate.query("select * from frontisthrio where id = ?", params, new FrontisthrioRowMapper());
        if (fr.size() == 1)  {
            return Optional.of(fr.get(0));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Package> getPackage(long id) {
        Long[] params = new Long[]{id};
        List<Package> pkg = jdbcTemplate.query("select id,field,price,success_rate,city,street,street_number,description,ST_X(geopoint) as latitude, ST_Y(geopoint) as longitude,frontisthrio_id from package where id = ?", params, new PackageRowMapper());
        if (pkg.size() == 1) {
            return Optional.of(pkg.get(0));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<List> getUserFrontisthria(long id) {
        Long[] params = new Long[]{id};
        List<Frontisthrio> frs = jdbcTemplate.query("select * from frontisthrio where user_id = ?", params, new FrontisthrioRowMapper());
        if (frs.size() >= 1) {
            return Optional.of(frs);
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<List> getFrontisthrioPackages(long id) {
        Long[] params = new Long[]{id};
        List<Package> packs = jdbcTemplate.query("select id,field,price,success_rate,city,street,street_number,description,ST_X(geopoint) as latitude, ST_Y(geopoint) as longitude,frontisthrio_id from package where frontisthrio_id = ?", params, new PackageRowMapper());
        if (packs.size() >= 1)  {
            return Optional.of(packs);
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Frontisthrio> getFrontisthrioByName(String name) {
        String[] params = new String[]{name};
        List<Frontisthrio> fr = jdbcTemplate.query("select * from usersfrontisthriopackageview where frontisthrioName = ?", params, new FrontisthrioRowMapper());
        if (fr.size() == 1)  {
            return Optional.of(fr.get(0));
        }
        else {
            return Optional.empty();
        }
    }

    /**********************************************************************/

    /**************************** Add Functions ***************************/


    public User addUser(long id, String username, String password, String email, short privilege) {
        //Create the new product record using a prepared statement
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(
                        "insert into users(id, username, password, email, privilege) values(?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setLong(1, id);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.setString(4, email);
                ps.setShort(5, privilege);
                return ps;
            }
        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int cnt = jdbcTemplate.update(psc, keyHolder);

        if (cnt == 1) {
            //New row has been added
            //keyHolder.getKey().intValue(), the newly created project id
            User usr = new User(
                    0,
                    username,
                    password,
                    email,
                    privilege
            );
            return usr;

        }
        else {
            throw new RuntimeException("Creation of Product failed");
        }
    }

    public Frontisthrio addFrontisthrio(String name, String description, Timestamp timestamp, String phonenumber, long user_id) {
        //Create the new product record using a prepared statement
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(
                        "insert into frontisthrio(name, description, timestamp, phonenumber, user_id) values(?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setTimestamp(3, timestamp);
                ps.setString(4, phonenumber);
                ps.setLong(5, user_id);
                return ps;
            }
        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int cnt = jdbcTemplate.update(psc, keyHolder);

        if (cnt == 1) {
            //New row has been added
            //keyHolder.getKey().intValue(), the newly created project id
            Frontisthrio fr = new Frontisthrio(
                    0,
                    name,
                    description,
                    timestamp,
                    phonenumber,
                    user_id
            );
            return fr;

        }
        else {
            throw new RuntimeException("Creation of Product failed");
        }
    }

    public Package addPackage(long id, Fields field, short price, int success_rate, String city, String street, int street_number, float latitude, float longitude, long frontisthrio_id) {
        //Create the new product record using a prepared statement
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(
                        "insert into package(id, field, price, success_rate, city, street, street_number, geopoint, frontisthrio_id) values(?, ?, ?, ?, ?, ?, ?, ST_GeomFromText(ST_AsText(Point(?, ?)), 4326), ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setLong(1, id);
                ps.setString(2, field.name());
                ps.setShort(3, price);
                ps.setInt(4, success_rate);
                ps.setString(5, city);
                ps.setString(6, street);
                ps.setInt(7, street_number);
                ps.setFloat(8, latitude);
                ps.setFloat(9, longitude);
                ps.setLong(10, frontisthrio_id);
                return ps;
            }
        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int cnt = jdbcTemplate.update(psc, keyHolder);

        if (cnt == 1) {
            //New row has been added
            Package pack = new Package(
                    id, //the newly created project id
                    field,
                    price,
                    success_rate,
                    city,
                    street,
                    street_number,
                    latitude,
                    longitude,
                    frontisthrio_id
            );
            return pack;

        }
        else {
            throw new RuntimeException("Creation of Product failed");
        }
    }

    /*********************************************************************/

    public boolean checkOwnership(long fr_id, long usr_id) {
        Long[] params = new Long[]{fr_id};
        List<Frontisthrio> frs = jdbcTemplate.query("select * from frontisthrio where id = ?", params, new FrontisthrioRowMapper());

        if (frs.get(0).getUserId() == usr_id) return true;
        return false;
    }
}
