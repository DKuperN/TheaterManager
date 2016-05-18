package by.core.daos;

import by.core.models.AspectsModel;

import java.sql.Time;
import java.util.List;

public interface AspectDAO {
    AspectsModel getAspectByName(String aspectName, Time aspectTime);
    List<AspectsModel> getAllAspectCounters();
    void storeAspect(AspectsModel aspectsModel);
}
