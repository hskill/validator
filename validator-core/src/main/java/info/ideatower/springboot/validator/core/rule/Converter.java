package info.ideatower.springboot.validator.core.rule;

import org.apache.commons.lang3.math.NumberUtils;

public class Converter {

    public static Number getNumber(Object target) {
        if (target == null) {
            return null;
        }

        if (target instanceof Number) {
            return (Number) target;
        }
        else if (NumberUtils.isNumber(String.valueOf(target))) {
            return NumberUtils.createNumber(String.valueOf(target));
        }
        return null;
    }
}
