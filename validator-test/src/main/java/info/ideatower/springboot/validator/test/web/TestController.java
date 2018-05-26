package info.ideatower.springboot.validator.test.web;

import info.ideatower.springboot.validator.web.annoation.JsonValidatorResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/view/{id}")
    @JsonValidatorResult("test.view")
    public String edit(String id) {

        return "{'code': '000000'}";
    }
}
