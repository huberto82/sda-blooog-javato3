package entity.user;

import java.time.LocalDateTime;

public class User extends NewUser{
    public final long id;
    public final LocalDateTime registered;
    public final LocalDateTime lastLogin;
    public final boolean enabled;

    public User(UserEntity ue){
        super(ue);
        this.id = ue.getId();
        this.lastLogin = ue.getLastLogin();
        this.registered = ue.getRegistered();
        this.enabled = ue.isEnabled();
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

    public boolean isEnabled() {
        return enabled;
    }

    public User enable(){
        UserEntity ue = new UserEntity(this);
        ue.setEnabled(true);
        return new User(ue);
    }

}
