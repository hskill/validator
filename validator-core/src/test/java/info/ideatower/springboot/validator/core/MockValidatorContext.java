package info.ideatower.springboot.validator.core;

import info.ideatower.springboot.validator.core.resource.ValidatorField;
import info.ideatower.springboot.validator.core.resource.ValidatorResource;
import lombok.val;
import org.assertj.core.util.Lists;

public class MockValidatorContext extends ValidatorContext {

    public MockValidatorContext() {

        this.setRelated(new RelatedObject() {
            @Override
            public boolean contains(String field) {
                return false;
            }

            @Override
            public Object getValue(String field) {
                return null;
            }
        });
        this.setResource(new ValidatorResource());

        val currentField = new ValidatorField();
        currentField.setName("test");
        currentField.setRules(Lists.newArrayList());
        currentField.setType("test");
        this.setCurrentValidatorField(currentField);
    }
}
