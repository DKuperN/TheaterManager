package by.core.services;

import by.core.models.AuditoriumModel;

import java.io.IOException;
import java.util.Map;

public interface AuditoriumService {

    Map<String, AuditoriumModel> getAllAuditoriums() throws IOException;
    AuditoriumModel getAuditoriumByName(String aName) throws IOException;
    int[]getVipSeats(String aName) throws IOException;
    int getAuditoriumCapacity(String aName);
    boolean isPlaceVip(String eName, int placeNumber) throws IOException;

}
