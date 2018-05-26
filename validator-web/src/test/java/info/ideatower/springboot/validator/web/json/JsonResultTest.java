package info.ideatower.springboot.validator.web.json;

import com.google.gson.JsonObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonResultTest {

    @Test
    public void to() {
        JsonResult.to("000", "okay", "xxx");
    }

    @Test
    public void to1() {
        JsonResult.to("xxx");
    }

    @Test
    public void okay() {
        JsonResult.okay();
    }

    @Test
    public void error() {
        JsonResult.error("400", "bad");
    }

    @Test
    public void error1() {
        JsonResult.error("400");
    }

    @Test
    public void error2() {
        JsonResult.error();
    }
}