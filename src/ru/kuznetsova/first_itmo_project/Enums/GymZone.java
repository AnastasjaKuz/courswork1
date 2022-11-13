package ru.kuznetsova.first_itmo_project.Enums;

import ru.kuznetsova.first_itmo_project.GymPatron;

public enum GymZone {
    SWIMMING_POOL
            (new Abonement[] {Abonement.SINGLE, Abonement.FULL},
                    "Бассейн"),
    GROUP_CLASS(new Abonement[] {Abonement.DAILY, Abonement.FULL},
            "Груповые занятия"),
    GYM(new Abonement[] {Abonement.SINGLE, Abonement.DAILY, Abonement.FULL},
            "Спортивный зал");

    private Abonement[] abonementAbility;
    private String zoneName;

    GymZone(Abonement[] abonementAbility, String zoneName) {
        this.abonementAbility = new Abonement[abonementAbility.length];
        for (int i = 0; i < abonementAbility.length; i++) {
            this.abonementAbility[i] = abonementAbility[i];
        }
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return this.zoneName;
    }

    public static GymZone getZoneByName(String zoneName){
        for (GymZone zone : GymZone.values()) {
            if (zoneName.equalsIgnoreCase(zone.getZoneName())) {
                return zone;
            }
        }
        return null;
    }

    public  boolean isAccessOpen (GymPatron patron){
        for (Abonement abonementType : abonementAbility) {
            if (abonementType.equals(patron.getAbonementType())) return true;
        }
        return false;
    }

}
