package info.ideatower.springboot.validator.web.related;

import info.ideatower.springboot.validator.core.RelatedObject;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 */
public class PathVariableRelatedObject implements RelatedObject {

    private final Map pathVariables;

    public PathVariableRelatedObject(HttpServletRequest request) {
        this.pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }

    @Override
    public boolean contains(String field) {
        return pathVariables.containsKey(field);
    }

    @Override
    public Object getValue(String field) {
        return pathVariables.get(field);
    }
}
