package app.hapo.car.freight.common.model;/*
 * Created by hapo
 * Date : 19. 1. 29 오후 11:59
 * Description : JwtUser
 */

public class JwtUser {
    private String userName;
    private long id;
    private String role;

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}