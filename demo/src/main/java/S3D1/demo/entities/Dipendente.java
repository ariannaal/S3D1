package S3D1.demo.entities;

import S3D1.demo.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "dipendenti")
@JsonIgnoreProperties ({"username", "enabled", "accountNonLocked", "accountNonExpired", "credentialsNonExpired"})
public class Dipendente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String immagineProfilo;
    @Enumerated(EnumType.STRING)
    private Role role;


    public Dipendente(){
    }

    public Dipendente(String username, String nome, String cognome, String email) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.role=Role.IMPIEGATO;
    }

    public int getId() {
        return id;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // deve restituire una lista di ruoli dell’utente che si chiama SimpleGrantedAuthority
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImmagineProfilo() {
        return immagineProfilo;
    }

    public void setImmagineProfilo(String immagineProfilo) {
        this.immagineProfilo = immagineProfilo;
    }

    @Override
    public String toString() {
        return "Dipendente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
