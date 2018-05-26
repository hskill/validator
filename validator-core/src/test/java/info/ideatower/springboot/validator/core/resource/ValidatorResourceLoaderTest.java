package info.ideatower.springboot.validator.core.resource;

import info.ideatower.springboot.validator.core.rule.RuleFactory;
import org.junit.Test;

public class ValidatorResourceLoaderTest {

    @Test
    public void load() {

        ValidatorResourceLoader loader = new ValidatorResourceLoader(new RuleFactory());
        ValidatorResource resource = loader.load("validator");
    }
}