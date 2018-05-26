package info.ideatower.springboot.validator.web;

import com.alibaba.fastjson.JSON;
import info.ideatower.springboot.validator.core.resource.ValidatorResource;
import info.ideatower.springboot.validator.web.json.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 供客户端使用
 */
@RestController
@RequestMapping("/validator/rule")
public class ValidatorRuleController {

    @Autowired
    private ValidatorResource validatorResource;

    /**
     * 获取指定mark的规则集合
     *
     * @param rule
     * @param item
     * @return
     */
    @GetMapping("/{rule}/{item}")
    public String get(@PathVariable("rule") String rule, @PathVariable("item") String item) {

        if (validatorResource.has(rule, item)) {
            return JSON.toJSONString(validatorResource.get(rule, item));
        }
        return JsonResult.error("404", "Not Found");
    }
}
