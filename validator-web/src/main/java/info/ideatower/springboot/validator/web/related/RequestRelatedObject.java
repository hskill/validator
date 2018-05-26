package info.ideatower.springboot.validator.web.related;

import info.ideatower.springboot.validator.core.RelatedObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Request包装器
 */
public class RequestRelatedObject implements RelatedObject {

    private final HttpServletRequest request;

    public RequestRelatedObject(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public boolean contains(String field) {
        return request.getParameterMap().containsKey(field);
    }

    @Override
    public Object getValue(String field) {
        return request.getParameter(field);
    }

}
