package System;

import java.time.LocalDate;
import java.util.UUID;

public class Session {
    
    private UUID id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int ageGroup;

    private String theme;

    private int availableSports;

    public UUID getUuid(){
        return id;
    }

    public Session(LocalDate startDate, LocalDate endDate, int ageGroup){
        this.startDate=startDate;
        this.endDate=endDate;
        this.ageGroup=ageGroup;
    }
}
