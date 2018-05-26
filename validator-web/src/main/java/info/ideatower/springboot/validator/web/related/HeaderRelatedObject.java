package info.ideatower.springboot.validator.web.related;

import com.google.common.collect.Maps;
import info.ideatower.springboot.validator.core.RelatedObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

public class HeaderRelatedObject implements RelatedObject {

    private final Map<Object, Object> headerMap;

    public HeaderRelatedObject(final HttpServletRequest request) {
        this.headerMap = Maps.newHashMap();
        Enumeration<String> headerEnumeration = request.getHeaderNames();
        while (headerEnumeration.hasMoreElements()) {
            String headerName = headerEnumeration.nextElement();
            this.headerMap.put(headerName, request.getHeader(headerName));

        }
    }

    @Override
    public boolean contains(String field) {
        return headerMap.containsKey(field);
    }

    @Override
    public Object getValue(String field) {
        return headerMap.get(field);
    }
}
