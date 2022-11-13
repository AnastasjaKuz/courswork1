package ru.kuznetsova.first_itmo_project;

import ru.kuznetsova.first_itmo_project.FitnessClub;

import java.time.LocalDate;
import java.time.LocalTime;

public class Application {
    public static void main(String[] args) {
        GymPatron patron1 = new GymPatron("Никита", "Чайка", 1980, "Разовый", 1);
        GymPatron patron2 = new GymPatron("Алена", "Крымская", 2003, "Дневной", 30);
        GymPatron patron3 = new GymPatron("Елена", "Ишхнели", 2003, "Полный", 30);

        FitnessClub topClub = new FitnessClub();

        topClub.doTraining(patron1, "бассейн", LocalTime.of(7, 30)); // проверка работы когда клуб закрыт

        topClub.startWorking(LocalDate.now());

        topClub.doTraining(patron1, "бассейн", LocalTime.of(9, 5));
        topClub.doTraining(patron2, "тренажерный зал", LocalTime.of(9, 10));
        topClub.doTraining(patron3, "тренажерный зал", LocalTime.of(9, 10));
        topClub.doTraining(patron1, "груповые занятия", LocalTime.of(10, 30));
        topClub.doTraining(patron2, "бассейн", LocalTime.of(11, 0));
        topClub.doTraining(patron2, "бассейн", LocalTime.of(11, 0));

        topClub.printFCPatronInfo();

        topClub.stopWorking();

    }
}
