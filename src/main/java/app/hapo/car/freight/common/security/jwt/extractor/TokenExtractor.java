package app.hapo.car.freight.common.security.jwt.extractor;

/**
 * freight
 * Class: TokenExtractor
 * Created by hapo on 2019-02-01.
 * Description: TokenExtractor
 */
public interface TokenExtractor {
    public String extract(String payload);
}
