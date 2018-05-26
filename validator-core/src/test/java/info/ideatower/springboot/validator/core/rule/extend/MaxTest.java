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

public class MaxTest {

    ValidatorContext context;

    RuleFactory ruleFactory;

    @Before
    public void setup() {
        ruleFactory = new RuleFactory();
        context = new MockValidatorContext();
    }

    @Test
    public void testSupports() {
        Rule max = ruleFactory.build("max", 18);

        assertTrue(max.supports(Integer.class));
        assertTrue(max.supports(Float.class));
        assertTrue(max.supports(Double.class));

        assertFalse(max.supports(String.class));
    }

    @Test
    public void test() {

        Errors errors = new ValidatorResult();
        Rule max = ruleFactory.build("max", 18);

        assertTrue(max.accept(context, null, errors));
        assertTrue(max.accept(context, 16, errors));
        assertFalse(max.accept(context, 20, errors));

    }

}