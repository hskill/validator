package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.MockValidatorContext;
import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.Errors;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.Rule;
import info.ideatower.springboot.validator.core.rule.RuleFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotBlankTest {

    ValidatorContext context;

    RuleFactory ruleFactory;

    @Before
    public void setup() {
        ruleFactory = new RuleFactory();
        context = new MockValidatorContext();
    }

    @Test
    public void test() {
        Errors errors = new ValidatorResult();
        Rule notBlank = ruleFactory.build("notBlank", null);

        assertTrue(notBlank.accept(context, null, errors));
        assertFalse(notBlank.accept(context, "", errors));
        assertFalse(notBlank.accept(context, "  ", errors));
        assertTrue(notBlank.accept(context, "xxx", errors));
    }

}