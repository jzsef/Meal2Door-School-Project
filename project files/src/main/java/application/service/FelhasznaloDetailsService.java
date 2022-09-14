package application.service;

import application.dao.FelhasznaloDAO;
import application.model.Felhasznalo;
import application.model.FelhasznaloDetails;
import application.model.Kosar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class FelhasznaloDetailsService implements UserDetailsService {
    @Autowired
    FelhasznaloDAO felhasznaloDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Felhasznalo felhasznalo = felhasznaloDAO.getFelhasznaloByEmail(email);

        if (felhasznalo != null) {
            return new FelhasznaloDetails(felhasznalo, new Kosar());
        }

        throw new UsernameNotFoundException("Nem talalhato felhasznalo ezzel az emailcimmel!");
    }
}
