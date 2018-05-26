package info.ideatower.springboot.validator.core.rule;

import com.google.common.collect.Maps;
import info.ideatower.springboot.validator.core.exception.RuleCannotInstanceException;
import info.ideatower.springboot.validator.core.result.Errors;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import info.ideatower.springboot.validator.core.rule.extend.Max;
import info.ideatower.springboot.validator.core.rule.extend.Required;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RuleFactoryTest {

    private RuleFactory ruleFactory;

    @Before
    public void setup() {
        this.ruleFactory = new RuleFactory();
    }

    @Test(expected = RuleCannotInstanceException.class)
    public void testException() {
        Rule rule = this.ruleFactory.build("notNull", null);
    }

    @Test
    public void testRequired() {

        Errors errors = new ValidatorResult();
        Map<String, Object> map = Maps.newHashMap();
        map.put("message", "必须");
        Rule rule = this.ruleFactory.build("required", map);

        assertEquals(rule.getClass(), Required.class);

    }

    @Test
    public void testMax() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("value", 16);
        map.put("message", "必须");
        Rule rule = this.ruleFactory.build("max", map);

        Errors errors = new ValidatorResult();

        assertEquals(rule.getClass(), Max.class);


    }


    @Test
    public void testOtherRuleName() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("value", 16);
        map.put("message", "必须");
        Rule rule = this.ruleFactory.build(Max.class.getName(), map);

        Errors errors = new ValidatorResult();

        assertEquals(rule.getClass(), Max.class);

    }
}
