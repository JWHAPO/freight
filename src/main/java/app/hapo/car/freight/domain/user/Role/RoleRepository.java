package app.hapo.car.freight.domain.user.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * freight
 * Class: RoleRepository
 * Created by hapo on 2019-01-31.
 * Description:
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
