package application.dao;

import application.model.Ertekeles;
import application.model.FelhasznaloSzerep;
import application.model.Szerep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class FelhasznaloSzerepDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    public void createFelhasznaloSzerep(FelhasznaloSzerep felhasznaloSzerep) {
        String sql = "INSERT INTO felhasznalo_szerep(felhasznalo_id, szerep_id) VALUES (?, ?)";
        getJdbcTemplate().update(sql,
                felhasznaloSzerep.getFelhasznaloID(),
                felhasznaloSzerep.getSzerepID()
        );
    }

    public List<FelhasznaloSzerep> listFelhasznaloSzerep() {
        String sql = "SELECT * FROM felhasznalo_szerep";

        return rowsToList(getJdbcTemplate().queryForList(sql));
    }

    public List<FelhasznaloSzerep> saveAll() {
        String sql = "SELECT * FROM felhasznalo_szerep";

        return rowsToList(getJdbcTemplate().queryForList(sql));
    }

    public FelhasznaloSzerep felhasznaloSzerepByFelhasznaloID(int felhasznaloID) {
        String sql = "SELECT * FROM felhasznalo_szerep WHERE felhasznalo_id=?";

        List<FelhasznaloSzerep> result = rowsToList(getJdbcTemplate().queryForList(sql, felhasznaloID));
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    private List<FelhasznaloSzerep> rowsToList(List<Map<String, Object>> rows) {
        List<FelhasznaloSzerep> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            result.add(new FelhasznaloSzerep(
                    (Integer) row.get("felhasznalo_id"),
                    (Integer) row.get("szerep_id")
            ));
        }
        return result;
    }

    public void updateFelhasznaloSzerep(int felhasznaloID, int szerepID) {
        String sql = "UPDATE felhasznalo_szerep SET szerep_id=? WHERE felhasznalo_id=?";
        getJdbcTemplate().update(sql,
                szerepID,
                felhasznaloID
        );
    }
    public void deleteFelhasznaloSzerep(int felhasznaloID) {
        String sql = "DELETE FROM felhasznalo_szerep WHERE felhasznalo_id=?";
        getJdbcTemplate().update(sql, felhasznaloID);
    }
}