package app.hapo.car.freight.common.security.model;

/**
 * freight
 * Class: UserContext
 * Created by hapo on 2019-02-01.
 * Description: Scopes
 */
public enum Scopes {
    REFRESH_TOKEN;
    
    public String authority() {
        return "ROLE_" + this.name();
    }
}
