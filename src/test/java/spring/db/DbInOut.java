package spring.db;

import core.daos.impl.EventDaoImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Deprecated // NOT READY

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao.xml"})
public class DbInOut {

    @Autowired
    EventDaoImpl eventDao;

//    @Autowired
//    public void

}
