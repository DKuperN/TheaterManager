package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.Utils;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})

public class TestUtilsFunctions {
    @Autowired
    private Utils utils;

    @Test
    public void loadAuditoriums() throws IOException {
        utils.loadAuditoriumsFromProperties();
    }
}
