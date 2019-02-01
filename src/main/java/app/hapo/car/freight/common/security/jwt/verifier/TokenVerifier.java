package app.hapo.car.freight.common.security.jwt.verifier;

/**
 * freight
 * Class: TokenVerifire
 * Created by hapo on 2019-02-01.
 * Description: TokenVerifire
 */
public interface TokenVerifier {
    public boolean verify(String jti);
}
