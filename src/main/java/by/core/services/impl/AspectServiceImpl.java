package by.core.services.impl;

import by.core.daos.impl.AspectDaoImpl;
import by.core.models.AspectsModel;
import by.core.services.AspectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AspectServiceImpl implements AspectService {

    @Autowired
    AspectDaoImpl aspectDao;

    public AspectServiceImpl(AspectDaoImpl aspectDao) {
        this.aspectDao = aspectDao;
    }

    @Override
    public AspectsModel getAspectByName(String aspectCounterName, String aspectStartTime) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH.mm");
        java.sql.Time timeValue = new java.sql.Time(formatter.parse(aspectStartTime).getTime());
        return aspectDao.getAspectByName(aspectCounterName, timeValue);
    }

    @Override
    public List<AspectsModel> getAllAspects() {
        return aspectDao.getAllAspectCounters();
    }

    @Override
    public void storeAspect(AspectsModel aspectsModel) {
        aspectDao.storeAspect(aspectsModel);
    }
}
