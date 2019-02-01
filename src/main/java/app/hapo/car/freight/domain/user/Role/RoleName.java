package app.hapo.car.freight.domain.user.Role;

/**
 * freight
 * Class: RoleName
 * Created by hapo on 2019-01-31.
 * Description: Role Enum
 */
public enum RoleName {
    ROLE_USER,
    ROLE_ADMIN;

    public String authority() {
        return this.name();
    }
}
