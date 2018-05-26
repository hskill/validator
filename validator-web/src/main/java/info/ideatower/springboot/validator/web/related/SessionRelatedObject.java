package info.ideatower.springboot.validator.web.related;

import info.ideatower.springboot.validator.core.RelatedObject;

import javax.servlet.http.HttpServletRequest;

/**
 * session related object
 */
public class SessionRelatedObject implements RelatedObject {

    private final HttpServletRequest request;

    public SessionRelatedObject(final HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public boolean contains(String field) {
        return request.getSession().getAttribute(field) != null;
    }

    @Override
    public Object getValue(String field) {
        return request.getSession().getAttribute(field);
    }
}
