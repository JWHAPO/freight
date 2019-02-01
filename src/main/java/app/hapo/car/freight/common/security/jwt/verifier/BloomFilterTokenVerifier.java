package app.hapo.car.freight.common.security.jwt.verifier;

import org.springframework.stereotype.Component;

/**
 * freight
 * Class: BloomFilterTokenVerifire
 * Created by hapo on 2019-02-01.
 * Description: BloomFilterTokenVerifire
 */
@Component
public class BloomFilterTokenVerifier implements TokenVerifier {
    @Override
    public boolean verify(String jti) {
        return true;
    }
}
