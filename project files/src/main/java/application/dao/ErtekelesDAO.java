package application.dao;

import application.model.*;
import application.model.Felhasznalo;
import application.model.Rendeles;
import application.model.Termek;
import application.model.Ertekeles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ErtekelesDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;



    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    public void createErtekeles(Ertekeles ertekeles) {
        String sql = "INSERT INTO ertekelesek(rendelesid, termekid, termeknev, felhasznalo_id, csillagok, megjegyzes, etterem_id, felhasznalo_nev) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql,
                ertekeles.getRendelesid(),
                ertekeles.getTermekid(),
                ertekeles.getTermeknev(),
                ertekeles.getFelhasznalo_id(),
                ertekeles.getCsillagok(),
                ertekeles.getMegjegyzes(),
                ertekeles.getEtterem_id(),
                ertekeles.getFelhasznalo_nev()
        );


    }

    public List<Ertekeles> listErtekeles(int id) {
        String sql = "SELECT * FROM ertekelesek where etterem_id=?";

        return rowsToList(getJdbcTemplate().queryForList(sql,id));
    }

    public List<Ertekeles> saveAll() {
        String sql = "SELECT * FROM ertekelesek";

        return rowsToList(getJdbcTemplate().queryForList(sql));
    }


    private List<Ertekeles> rowsToList(List<Map<String, Object>> rows) {
        List<Ertekeles> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            int rendelesID = (Integer) row.get("rendelesid");



            result.add(new Ertekeles(
                    rendelesID,
                    (Integer) row.get("termekid"),
                    (String) row.get("termeknev"),
                    (Integer) row.get("felhasznalo_id"),
                    (Integer) row.get("csillagok"),
                    (String) row.get("megjegyzes"),
                    (Integer) row.get("etterem_id"),
                    (String) row.get("felhasznalo_nev")

            ));
        }
        return result;
    }

    public Ertekeles getErtekelesByEtteremID(int id) {
        String sql = "SELECT * FROM ertekelesek WHERE etterem_id=?";

        List<Ertekeles> result = rowsToList(getJdbcTemplate().queryForList(sql, id));
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }










}
