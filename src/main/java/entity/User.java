package entity;

import java.time.LocalDateTime;

public class User extends NewUser{
    public final long id;
    public final LocalDateTime registered;
    public final LocalDateTime lastLogin;

    public User(UserEntity ue){
        super(ue.getEmail(), ue.getNick(), ue.getPassword());
        this.id = ue.getId();
        this.lastLogin = ue.getLastLogin();
        this.registered = ue.getRegistered();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
}
