package info.ideatower.springboot.validator.web.json;

import com.alibaba.fastjson.JSON;

import java.text.MessageFormat;

public class JsonResult {

    public static String to(String code, String message, Object target) {
        return String.format("{\"code\": \"%s\", \"message\": \"%s\", \"data\": \"%s\"}",
                code,
                message,
                JSON.toJSONString(target));
    }

    public static String to(Object target) {
        return to("000000", "okay", target);
    }

    public static String okay() {
        return "{\"code\": \"000000\", \"message\": \"okay\"}";
    }

    public static String error(String code, String message) {
        return String.format("{\"code\": \"%s\", \"message\": \"%s\"}", code, message);
    }

    public static String error(String code) {
        return String.format("{\"code\": \"%s\", \"message\": \"bad\"}", code);
    }

    public static String error() {
        return "{\"code\": \"400\", \"message\": \"bad request\"}";
    }
}
