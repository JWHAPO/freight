package app.hapo.car.freight.common.security.jwt;

import io.jsonwebtoken.lang.Assert;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * freight
 * Class: SkipPathRequestMatcher
 * Created by hapo on 2019-02-01.
 * Description: SkipPathRequestMatcher
 */
public class SkipPathRequestMatcher implements RequestMatcher {
    private OrRequestMatcher matchers;
    private RequestMatcher processMatcher;

    public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath){
        Assert.notNull(pathsToSkip);
        List<RequestMatcher> matcherList =
                pathsToSkip.stream().map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList());
        matchers = new OrRequestMatcher(matcherList);
        processMatcher = new AntPathRequestMatcher(processingPath);

    }
    @Override
    public boolean matches(HttpServletRequest request) {
        if(matchers.matches(request)) return false;
        return processMatcher.matches(request)?true:false;
    }
}
