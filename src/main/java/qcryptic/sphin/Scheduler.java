package qcryptic.sphin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import qcryptic.sphin.enums.ConnectionTypes;
import qcryptic.sphin.service.IConnectionsSvc;

/**
 * Created by Kyle on 11/18/2017.
 */
@Component
public class Scheduler {



    @Scheduled(fixedRate = 3600000)
    public void updateLocalMedia() {
        //connectionsSvc.getActiveConnection(ConnectionTypes.MOVIE);
    }

}
