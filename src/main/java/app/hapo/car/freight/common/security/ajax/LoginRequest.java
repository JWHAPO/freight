package app.hapo.car.freight.common.security.ajax;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * freight
 * Class: LoginRequest
 * Created by hapo on 2019-02-01.
 * Description: LoginRequest
 */

public class LoginRequest {
    private String email;
    private String password;

    @JsonCreator
    public LoginRequest(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
