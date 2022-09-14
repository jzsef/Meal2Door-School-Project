package application.dao;

import application.model.Ertekeles;
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
public class SzerepDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    public List<Szerep> listSzerep() {
        String sql = "SELECT * FROM szerep";

        return rowsToList(getJdbcTemplate().queryForList(sql));
    }

    public List<Szerep> saveAll() {
        String sql = "SELECT * FROM szerep";

        return rowsToList(getJdbcTemplate().queryForList(sql));
    }

    public Szerep getSzerepByID(int id) {
        String sql = "SELECT * FROM szerep WHERE szerep_id=?";

        List<Szerep> result = rowsToList(getJdbcTemplate().queryForList(sql, id));
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    private List<Szerep> rowsToList(List<Map<String, Object>> rows) {
        List<Szerep> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            result.add(new Szerep(
                    (Integer) row.get("szerep_id"),
                    (String) row.get("nev")
            ));
        }
        return result;
    }
}
