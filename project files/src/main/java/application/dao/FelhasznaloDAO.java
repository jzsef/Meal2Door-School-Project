package application.dao;

import application.model.Ertekeles;
import application.model.Felhasznalo;
import application.model.FelhasznaloSzerep;
import application.model.Szerep;
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
public class FelhasznaloDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @Autowired
    SzerepDAO szerepDAO;
    @Autowired
    FelhasznaloSzerepDAO felhasznaloSzerepDAO;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    public void createFelhasznalo(Felhasznalo felhasznalo) {
        String sql = "INSERT INTO felhasznalo(nev, email, jelszo, telefonszam, szuletesi_datum, cim, egyenleg) VALUES (?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql,
                felhasznalo.getNev(),
                felhasznalo.getEmail(),
                felhasznalo.getJelszo(),
                felhasznalo.getTelefonszam(),
                felhasznalo.getSzuletesiDatum(),
                felhasznalo.getCim(),
                felhasznalo.getEgyenleg()
        );

        int id = getFelhasznaloIDByEmail(felhasznalo.getEmail());
        felhasznaloSzerepDAO.createFelhasznaloSzerep(new FelhasznaloSzerep(id, felhasznalo.getSzerep().getId()));
    }

    public List<Felhasznalo> listFelhasznalo() {
        String sql = "SELECT * FROM felhasznalo";

        return rowsToList(getJdbcTemplate().queryForList(sql));
    }

    public List<Felhasznalo> saveAll() {
        String sql = "SELECT * FROM felhasznalo";

        return rowsToList(getJdbcTemplate().queryForList(sql));
    }

    public List<Felhasznalo> listEtterem() {
        String sql = "Select felhasznalo.felhasznalo_id, felhasznalo.nev, felhasznalo.email, felhasznalo.jelszo, felhasznalo.telefonszam, felhasznalo.szuletesi_datum, felhasznalo.cim, felhasznalo.egyenleg, felhasznalo_szerep.felhasznalo_id, felhasznalo_szerep.szerep_id,count(termekid) from termek join felhasznalo on termek.etteremid=felhasznalo.felhasznalo_id join felhasznalo_szerep on termek.etteremid=felhasznalo_szerep.felhasznalo_id where szerep_id=2 group by etteremid, felhasznalo.felhasznalo_id, felhasznalo_szerep.felhasznalo_id,felhasznalo_szerep.szerep_id";

        return rowsToList(getJdbcTemplate().queryForList(sql));
    }

    public Felhasznalo getFelhasznaloByID(int id) {
        String sql = "SELECT * FROM felhasznalo WHERE felhasznalo_id=?";

        List<Felhasznalo> result = rowsToList(getJdbcTemplate().queryForList(sql, id));
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public Felhasznalo getFelhasznaloByEmail(String email) {
        String sql = "SELECT * FROM felhasznalo WHERE email=?";

        List<Felhasznalo> result = rowsToList(getJdbcTemplate().queryForList(sql, email));
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public int getFelhasznaloIDByEmail(String email) {
        String sql = "SELECT felhasznalo_id FROM felhasznalo WHERE email=?";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, email);

        List<Integer> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            result.add((Integer) row.get("felhasznalo_id"));
        }

        if (result.size() > 0) {
            return result.get(0);
        }
        return -1;
    }

    private List<Felhasznalo> rowsToList(List<Map<String, Object>> rows) {
        List<Felhasznalo> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            int felhasznaloID = (Integer) row.get("felhasznalo_id");

            FelhasznaloSzerep felhasznaloSzerep = felhasznaloSzerepDAO.felhasznaloSzerepByFelhasznaloID(felhasznaloID);
            Szerep szerep = szerepDAO.getSzerepByID(felhasznaloSzerep.getSzerepID());

            result.add(new Felhasznalo(
                    felhasznaloID,
                    (String) row.get("nev"),
                    (String) row.get("email"),
                    (String) row.get("jelszo"),
                    (String) row.get("telefonszam"),
                    (Date) row.get("szuletesi_datum"),
                    (String) row.get("cim"),
                    (Integer) row.get("egyenleg"),
                    szerep
            ));
        }
        return result;
    }

    public void updateCim(String email, String cim) {
        String sql = "UPDATE felhasznalo SET cim=? WHERE email=?";
        getJdbcTemplate().update(sql,
                cim,
                email
        );
    }

    public void updateNev(String email, String nev) {
        String sql = "UPDATE felhasznalo SET nev=? WHERE email=?";
        getJdbcTemplate().update(sql,
                nev,
                email
        );
    }

    public void updateTelefonszam(String email, String telefonszam) {
        String sql = "UPDATE felhasznalo SET telefonszam=? WHERE email=?";
        getJdbcTemplate().update(sql,
                telefonszam,
                email
        );
    }

    public void updateEgyenleg(String email, int egyenleg) {
        String sql = "UPDATE felhasznalo SET egyenleg=? WHERE email=?";
        getJdbcTemplate().update(sql,
                egyenleg,
                email
        );
    }

    public void updateFelhasznalo(int id, String nev, String email, String jelszo, String telefonszam, Date szuletesiDatum, String cim, int egyenleg) {
        String sql = "UPDATE felhasznalo SET nev='"+nev+"', email='"+email+"', jelszo='"+jelszo+"', telefonszam='"+telefonszam+"', szuletesi_datum='"+szuletesiDatum+"', cim='"+cim+"'," +
                " egyenleg='"+egyenleg+"' WHERE id="+id;
        getJdbcTemplate().update(sql,
                nev,
                email,
                jelszo,
                telefonszam,
                szuletesiDatum,
                cim,
                egyenleg,
                id
        );

        getJdbcTemplate().update(sql);
    }

    public void deleteFelhasznalo(int id) {
        String sql = "DELETE FROM felhasznalo WHERE felhasznalo_id=?";
        getJdbcTemplate().update(sql, id);
    }
}
