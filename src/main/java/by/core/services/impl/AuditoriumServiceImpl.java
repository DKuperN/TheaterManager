package by.core.services.impl;

import by.core.models.AuditoriumModel;
import by.core.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.utils.Utils;

import java.io.IOException;
import java.util.Map;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private Utils utils;

    public AuditoriumServiceImpl() {}

    public AuditoriumServiceImpl(Utils utils) {
        this.utils = utils;
    }

    public Map<String, AuditoriumModel> getAllAuditoriums() throws IOException {
        return utils.loadAuditoriumsFromProperties();
    }

    public AuditoriumModel getAuditoriumByName(String aName) throws IOException {
        Map<String, AuditoriumModel> auditoriumsModelMap = getAllAuditoriums();
        AuditoriumModel auditorium = null;
        for (Map.Entry<String, AuditoriumModel> entry : auditoriumsModelMap.entrySet()) {
            if(aName.equals(entry.getValue().getAuditoriumName())){
                auditorium = entry.getValue();
            }
        }
        return auditorium;
    }

    public int[] getVipSeats(String aName) throws IOException {
        AuditoriumModel auditoriumByName = getAuditoriumByName(aName);
        return auditoriumByName.getAuditoriumVIPSeats();
    }

    public int getAuditoriumCapacity(String aName) {
        return 0;
    }

}
