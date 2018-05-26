package info.ideatower.springboot.validator.web.json;

import com.alibaba.fastjson.JSON;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * 默认 JSON 处理器
 */
public class SimpleJsonResultHandler implements JsonResultHandler {

    @Override
    public void handle(HttpServletResponse response, ValidatorResult result) {
        try {
            HttpStatus bad = HttpStatus.BAD_REQUEST;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            String json = JsonResult.to(String.valueOf(bad.value()), bad.getReasonPhrase(), result.getAllErrors());
            response.getOutputStream().write(json.getBytes());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.flushBuffer();

        }
        catch (Exception e) {
            // 如果向客户端写入消息异常
            throw new RuntimeException("客户端访问参数出错，向客户端输出错误信息时导致异常", e);
        }

    }
}
