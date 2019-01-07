package app.hapo.car.freight.domain.user;/*
 * Created by hapo
 * Date : 19. 1. 1 오후 10:43
 * Description : Car DTO
 */

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name="ta2user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "level_no")
    private String level_no;
}
