package info.ideatower.springboot.validator.core.rule.extend;

import info.ideatower.springboot.validator.core.MockValidatorContext;
import info.ideatower.springboot.validator.core.ValidatorContext;
import info.ideatower.springboot.validator.core.result.Errors;
import info.ideatower.springboot.validator.core.result.ValidatorResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChieseCellPhoneTest {

    ValidatorContext context;

    @Before
    public void setup() {
        context = new MockValidatorContext();
    }

    @Test
    public void accept() throws Exception {

        ChieseCellPhone chineseCellPhone = new ChieseCellPhone();

        Errors errors = new ValidatorResult();
        assertTrue(chineseCellPhone.accept(context, "18810280048", errors));
        assertFalse(chineseCellPhone.accept(context, "182810280048", errors));
    }

}