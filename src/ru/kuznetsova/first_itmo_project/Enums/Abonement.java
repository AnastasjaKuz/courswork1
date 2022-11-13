package ru.kuznetsova.first_itmo_project.Enums;

import java.time.LocalTime;

public enum Abonement {
    SINGLE(LocalTime.of(8, 0),
            LocalTime.of(22, 0),
            "Разовый"),

    DAILY(LocalTime.of(8, 0),
            LocalTime.of(16, 0),
            "Дневной"),

    FULL(LocalTime.of(8, 0),
            LocalTime.of(22, 0),
            "Полный");

    private final LocalTime startTime, endTime;
    public final String abonementTypeName;

    Abonement(LocalTime startTime, LocalTime endTime, String abonementTypeName) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.abonementTypeName = abonementTypeName;
    }

    public LocalTime getStartTime() {
    return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getAbonementTypeName() {
        return abonementTypeName;
    }


}
