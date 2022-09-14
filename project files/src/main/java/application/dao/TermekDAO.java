package application.dao;

import application.model.Ertekeles;
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
public class TermekDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    public void insertTermek(Termek termek) {
        String sql = "INSERT INTO termek(termeknev, kategoria, ar, etteremid) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().update(sql,
                termek.getNev(),
                termek.getKategoria(),
                termek.getAr(),
                termek.getEtteremID());
    }


    public List<Termek> saveAll() {
        String sql = "SELECT * FROM termek";

        return rowsToList(getJdbcTemplate().queryForList(sql));
    }

    public void insertRendeles(Termek termek, int felhasznalo_id){

        String sql = "INSERT INTO rendelesek (termeknev, ar, termekid, felhasznalo_id) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, termek.getNev(), termek.getAr(), termek.getId(), felhasznalo_id);


    }


    public Termek getTermekByID(int id) {
        String sql = "SELECT * FROM termek WHERE termekid=?";

        List<Termek> result = rowsToList(getJdbcTemplate().queryForList(sql, id));
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public List<Termek> getTermekByEtteremID(int etteremID) {
        String sql = "SELECT * FROM termek WHERE etteremid=?";

        return rowsToList(getJdbcTemplate().queryForList(sql, etteremID));
    }

    private List<Termek> rowsToList(List<Map<String, Object>> rows) {
        List<Termek> result = new ArrayList<Termek>();
        for(Map<String, Object> row:rows){
            Termek termek = new Termek(
                    (Integer)row.get("termekid"),
                    (String)row.get("termeknev"),
                    (String)row.get("kategoria"),
                    (Integer)row.get("ar"),
                    (Integer)row.get("etteremid")
            );
            result.add(termek);
        }
        return result;
    }

    public void deleteTermek(int id) {
        String sql = "DELETE FROM termek WHERE termekid=?";
        getJdbcTemplate().update(sql, id);
    }

    public void updateTermek(int id, String nev, String kategoria, Integer ar, Integer etteremID) {
        String sql = "UPDATE termek SET nev=?, kategoria=?, ar=?, etteremid=? WHERE termekid=?";
        getJdbcTemplate().update(sql,
                nev,
                kategoria,
                ar,
                etteremID,
                id);
    }

    public void updateNev(int id, String nev) {
        String sql = "UPDATE termek SET termeknev=? WHERE termekid=?";
        getJdbcTemplate().update(sql,
                nev,
                id
        );
    }

    public void updateKategoria(int id, String kategoria) {
        String sql = "UPDATE termek SET kategoria=? WHERE termekid=?";
        getJdbcTemplate().update(sql,
                kategoria,
                id
        );
    }

    public void updateAr(int id, int ar) {
        String sql = "UPDATE termek SET ar=? WHERE termekid=?";
        getJdbcTemplate().update(sql,
                ar,
                id
        );
    }
}
