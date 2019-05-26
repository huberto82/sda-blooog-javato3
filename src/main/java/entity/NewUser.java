package entity;

public class NewUser {
    public final String email;
    public final String nick;
    public final String password;

    public NewUser(String email, String nick, String password) {
        this.email = email;
        this.nick = nick;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }
}
