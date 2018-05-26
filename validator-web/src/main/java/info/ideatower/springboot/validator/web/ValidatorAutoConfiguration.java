package info.ideatower.springboot.validator.web;

import info.ideatower.springboot.validator.core.resource.ValidatorResource;
import info.ideatower.springboot.validator.core.resource.ValidatorResourceLoader;
import info.ideatower.springboot.validator.core.rule.RuleFactory;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Springboot 自动配置
 */
@Slf4j
@Configuration
public class ValidatorAutoConfiguration extends WebMvcConfigurerAdapter {

    public ValidatorAutoConfiguration() {
        log.info("ValidatorAutoConfiguration Start up!");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        val resource = validatorResource(resourceLoader(ruleFactory()));
        registry.addInterceptor(new JsonResultInterceptor(new RequestValidator(resource)));
    }

    /**
     * 参数解析器
     */
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        val resource = validatorResource(resourceLoader(ruleFactory()));
        argumentResolvers.add(
                new ValidatorResultArgumentResolver(
                        new RequestValidator(resource)));
    }

    /**
     * 可以返回规则集合的 Controller
     * @return
     */
    @Bean
    public ValidatorRuleController validationController() {
        return new ValidatorRuleController();
    }

    @Bean
    public RuleFactory ruleFactory() {
        return new RuleFactory();
    }

//    @Bean
//    public RequestValidatorContextAssembler requestValidatorContextAssembler(ValidatorResource resource) {
//        RequestValidatorContextAssembler assembler = new RequestValidatorContextAssembler(resource);
//        return assembler;
//    }

    @Bean
    public ValidatorResourceLoader resourceLoader(RuleFactory ruleFactory) {
        ValidatorResourceLoader loader = new ValidatorResourceLoader(ruleFactory);
        return loader;
    }

    @Bean
    public ValidatorResource validatorResource(ValidatorResourceLoader loader) {
        return loader.load();
    }
}

