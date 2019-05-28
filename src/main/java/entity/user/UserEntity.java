package entity.user;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", unique = true, nullable = false)
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String nick;


    private LocalDateTime registered;

    private LocalDateTime lastLogin;


    private String password;

    @Column(nullable = false)
    private boolean enabled;

    public UserEntity() {
    }

    public UserEntity(NewUser nu){
        this.password = nu.password;
        this.email = nu.email;
        this.nick = nu.nick;
        this.registered = LocalDateTime.now();
        this.lastLogin = LocalDateTime.now();
        this.enabled = false;
    }

    public UserEntity(User u){
        this.password = u.password;
        this.email = u.email;
        this.nick = u.nick;
        this.registered = u.registered;
        this.lastLogin = u.lastLogin;
        this.enabled = u.enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
