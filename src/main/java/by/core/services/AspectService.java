package by.core.services;

import by.core.models.AspectsModel;

import java.text.ParseException;
import java.util.List;

public interface AspectService {
    AspectsModel getAspectByName(String aspectName, String aspectStartTime) throws ParseException;
    List<AspectsModel> getAllAspects();
    void storeAspect(AspectsModel aspectsModel);
}
