package info.ideatower.springboot.validator.web.json;

import info.ideatower.springboot.validator.core.result.ValidatorResult;

import javax.servlet.http.HttpServletResponse;

/**
 * JSON 处理器
 */
public interface JsonResultHandler {

    void handle(HttpServletResponse response, ValidatorResult result);
}
