package ru.kuznetsova.first_itmo_project;

import ru.kuznetsova.first_itmo_project.Enums.Abonement;

import java.time.LocalDate;

public class GymPatron {
    private String name;
    private String surname;
    private int birthYear;

    private Abonement abonementType;
    private LocalDate startTime, endTime;

    public GymPatron(String name, String surname, int birthYear, String abonementType, int duration) {
        setName(name);
        setSurname(surname);
        setBirthYear(birthYear);
        setAbonementType(abonementType);
        this.startTime = LocalDate.now();
        setEndTime(duration);
    }

    public void setName(String name) {
        if (name.length() < 2 || name == null) {
            throw new IllegalArgumentException("Имя должно содержаь не менее 2 букв");
        }
        this.name = name;
    }

    public void setSurname(String surname) {
        if (name.length() < 2 || name == null) {
            throw new IllegalArgumentException("Фамилия должна содержаь не менее 2 букв");
        }
        this.surname = surname;
    }

    public void setBirthYear(int birthYear) {
        int currentYear = LocalDate.now().getYear();

        if (currentYear - birthYear > 100 || currentYear - birthYear < 16) {
            throw new IllegalArgumentException("Возраст клиента не может быть меньше 100 или меньше 16");
        }
        this.birthYear = birthYear;
    }

    public void setAbonementType(String abonementType) {
        if (abonementType == null) throw new IllegalArgumentException();

        for (Abonement typeOfAbonement : Abonement.values()) {
            if (typeOfAbonement.getAbonementTypeName().equalsIgnoreCase(String.valueOf(abonementType))){
                this.abonementType = typeOfAbonement;
            }
            if (this.abonementType == null) throw new IllegalArgumentException("Такого абонемента не сушествует");
        }
    }


    public void setEndTime(int duration) {
        if (duration < 1) throw new IllegalArgumentException("Длительность абонемента не может быть меньше 1 дня");

        if (this.abonementType.equals(Abonement.SINGLE) && duration == 1) {
            System.out.println("Длительность разового абонемента - 1 день");
            this.endTime = this.startTime.plusDays(1);
        } else this.endTime = this.startTime.plusDays(duration);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Abonement getAbonementType() {
        return abonementType;
    }

    public LocalDate getEndTime() {
        return endTime;
    }
}
