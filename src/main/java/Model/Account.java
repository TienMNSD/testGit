package Model;

public class Account {
    private String rank;
    private String id_user;
    private String username;
    private String password;
    public Account(){}
    public Account(String rank, String id_user, String username, String password){
        this.rank=rank;
        this.id_user=id_user;
        this.username=username;
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
