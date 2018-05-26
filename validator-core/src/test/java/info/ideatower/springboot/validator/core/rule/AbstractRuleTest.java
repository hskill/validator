package info.ideatower.springboot.validator.core.rule;

import info.ideatower.springboot.validator.core.rule.extend.Max;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbstractRuleTest {
    @Test
    public void generateMessage() throws Exception {
    }

    @Test
    public void getShortName() throws Exception {

        Max max = new Max();
        assertEquals("max", max.getName());
    }

}