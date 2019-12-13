package ua.biedin.register;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import ua.biedin.register.controller.PublicShareController;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
public class PublicShareTest extends AbstractTest {

    @Autowired
    PublicShareController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void check() {
        assertThat(controller).isNotNull();
    }

//    @Test
//    public void getShares() {
//        String URI = "api/v1/public/share";
//        MvcResult mvcResult = mvc.perform()
//    }

}
