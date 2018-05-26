package info.ideatower.springboot.validator.web;

import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.resource.ValidatorResource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证器环境装配
 */
@Slf4j
public class RequestValidatorContextAssembler {

    @Getter
    private ValidatorResource resource;

    public RequestValidatorContextAssembler(ValidatorResource resource) {
        this.resource = resource;
    }

    /**
     * 组装ValidationContext
     *
     * @param request
     * @return
     */
    public ValidatorContext assemble(final HttpServletRequest request) {
        ValidatorContext validationContext = new ValidatorContext();
        validationContext.setResource(resource);

        return validationContext;
    }


}
