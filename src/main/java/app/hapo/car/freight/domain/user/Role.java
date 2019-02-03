package app.hapo.car.freight.domain.user;

/**
 * freight
 * Class: Role
 * Created by hapo on 2019-01-31.
 * Description: Role Model
 */
public enum Role {
    ADMIN, PREMIUM_MEMBER, MEMBER;

    public String authority() {
        return "ROLE_" + this.name();
    }
}