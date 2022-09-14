package application.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FelhasznaloDetails implements UserDetails {
    Felhasznalo felhasznalo;
    Kosar kosar;

    public FelhasznaloDetails(Felhasznalo felhasznalo, Kosar kosar) {
        this.felhasznalo = felhasznalo;
        this.kosar = kosar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(felhasznalo.getSzerep().getNev()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return felhasznalo.getEmail();
    }
    @Override
    public String getPassword() {
        return felhasznalo.getJelszo();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Kosar getKosar() {
        return kosar;
    }
}
