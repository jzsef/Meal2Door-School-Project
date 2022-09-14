package application.dao;

import application.model.Ertekeles;
import application.model.Felhasznalo;
import application.model.Rendeles;
import application.model.Termek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class RendelesDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }




//  todo: listazas, implementalni maincontrollerbe, es profilon kiiratni oket.
//
//    public List<Rendeles> listTermek(int id) {
//        String sql = "SELECT * FROM termek WHERE felhasznalo_id = ?";
//
//        assert getJdbcTemplate() != null;
//        return rowsToList(getJdbcTemplate().queryForList(sql));
//    }


    private List<Rendeles> rowsToList(List<Map<String, Object>> rows) {
        List<Rendeles> result = new ArrayList<Rendeles>();
        for(Map<String, Object> row:rows){
            Rendeles rendeles = new Rendeles(
                    (Integer)row.get("rendelesid"),
                    (String)row.get("termeknev"),
                    (Integer)row.get("ar"),
                    (Integer)row.get("termekid"),
                    (Integer)row.get("felhasznalo_id"));
            result.add(rendeles);
        }
        return result;
    }


    public List<Rendeles> saveAll() {
        String sql = "SELECT * FROM rendelesek";

        return rowsToList(getJdbcTemplate().queryForList(sql));
    }

    public List<Rendeles> listRendeles(int id) {
        String sql = "Select * from rendelesek where felhasznalo_id=?";

        return rowsToList(getJdbcTemplate().queryForList(sql,id));
    }

    public Rendeles getRendelesByID(int id) {
        String sql = "select * from rendelesek where rendelesid=?";

        List<Rendeles> result = rowsToList(getJdbcTemplate().queryForList(sql, id));
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
}
