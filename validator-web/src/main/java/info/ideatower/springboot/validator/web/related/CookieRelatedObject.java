package info.ideatower.springboot.validator.web.related;

import com.google.common.collect.Maps;
import info.ideatower.springboot.validator.core.RelatedObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CookieRelatedObject implements RelatedObject {

    private final Map<String, Cookie> cookieMap;

    public CookieRelatedObject(final HttpServletRequest request) {
        this.cookieMap = Maps.newHashMap();
        for (Cookie cookie : request.getCookies()) {
            this.cookieMap.put(cookie.getName(), cookie);
        }
    }

    @Override
    public boolean contains(String field) {
        return this.cookieMap.containsKey(field);
    }

    @Override
    public Object getValue(String field) {
        return this.cookieMap.get(field).getValue();
    }
}
