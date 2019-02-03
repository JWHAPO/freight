package app.hapo.car.freight.common.security.common;/*
 * Created by hapo
 * Date : 19. 2. 3 오후 2:00
 * Description : WebUtil
 */

import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    public static final String X_REQUESTED_WITH = "X-Requested-With";

    public static final String CONTENT_TYPE = "Content-type";
    public static final String CONTENT_TYPE_JSON = "application/json";

    public static boolean isAjax(HttpServletRequest request){
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
    }

    public static boolean isAjax(SavedRequest request) {
        return request.getHeaderValues(X_REQUESTED_WITH).contains(XML_HTTP_REQUEST);
    }

    public static boolean isContentTypeJson(SavedRequest request) {
        return request.getHeaderValues(CONTENT_TYPE).contains(CONTENT_TYPE_JSON);
    }
}
