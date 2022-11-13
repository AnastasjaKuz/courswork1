package ru.kuznetsova.first_itmo_project;

import ru.kuznetsova.first_itmo_project.Enums.Abonement;
import ru.kuznetsova.first_itmo_project.Enums.GymZone;
import ru.kuznetsova.first_itmo_project.GymPatron;

import javax.swing.text.GapContent;
import java.time.LocalDate;
import java.time.LocalTime;

public class FitnessClub {

    private GymPatron[] swimmingPoolMembers;
    private GymPatron[] groupClassMembers;
    private GymPatron[] gymMembers;

    private LocalDate workingDate;

    public FitnessClub(){
        clubInitialization();
    }

    private void setWorkingDate(LocalDate workingDate) {
        if (workingDate == null) throw new IllegalArgumentException("Необходимо передать корректное значение работы клуба");
        this.workingDate = workingDate;
    }

    private void clubInitialization() {
        workingDate = null;
        swimmingPoolMembers = new GymPatron[20];
        groupClassMembers = new GymPatron[20];
        gymMembers = new GymPatron[20];
    }

    public void startWorking(LocalDate workingDate) {
        System.out.println("Дата: " + workingDate + ". Клуб начинает работу");
        clubInitialization();
    }

    public void stopWorking(){
        System.out.println("Дата: " + workingDate + ". Клуб закрывается");
        clubInitialization();
    }

    private boolean isFitnessClubWorking(){
        return workingDate != null;
    }

    private boolean isAbonementActive (GymPatron patron){
        return patron.getEndTime().isAfter(workingDate);
    }

    private boolean isTrainingTimeCorrect(GymPatron patron, LocalTime trainingTime){
        return  trainingTime.isAfter(patron.getAbonementType().getStartTime()) && trainingTime.isBefore(patron.getAbonementType().getEndTime());
    }

    private boolean isAccessToZone(GymZone gymZone, GymPatron patron){
        return gymZone.isAccessOpen(patron);
    }

    private boolean isZoneFull(GymPatron[] trainingZone) {
        for (GymPatron patron : trainingZone){
            if (patron == null) return false;
        }
        return true;
    }

    private boolean isPatronInZone (GymPatron patron, GymPatron[] zone) {
        for (GymPatron patronZone : zone){
            if (patronZone == null) break;
            if (patronZone.equals(patron)) return true;
        }
        return false;
    }

    private void addToZone(GymZone gymZone, GymPatron patron, LocalDate trainingDate, LocalTime trainingTime) {
        GymPatron[] trainingZone;
        if (gymZone.equals(GymZone.SWIMMING_POOL)) {
            trainingZone = swimmingPoolMembers;
        } else if (gymZone.equals(GymZone.GROUP_CLASS)) {
            trainingZone = groupClassMembers;
        } else trainingZone = gymMembers;

        for (int i = 0; i < trainingZone.length; i++) {
            if (trainingZone[i] == null) {
                trainingZone[i] = patron;
                System.out.println("Посетитель: " + patron.getName() + patron.getSurname()
                        + "Посещаемая зона: " + gymZone.getZoneName());
                System.out.println("Дата тренировки: " + trainingDate + "Время тринировки: "+ trainingTime);
                break;
            }
        }
    }

    public void doTraining(GymPatron patron, String trainingZone, LocalTime trainingTime){
        if (patron == null) throw new IllegalArgumentException("Такого абонемемента нет");
        if (trainingZone == null) throw new IllegalArgumentException("Зона занятия выбраа некорректно");
        if (trainingTime == null) throw new IllegalArgumentException("Время выбрано некорректно");

        if (!isFitnessClubWorking()) {
            System.out.println("Фитнес клуб закрыт");
             return;
        }

        GymZone zone = GymZone.getZoneByName(trainingZone);

        if (zone == null) System.out.println("В нашем фитнес клубе отсутствует данная зона");

        if (!isAbonementActive(patron)) System.out.println("Ваш абонемент закончился" + patron.getEndTime());

        if (!isAccessToZone(zone, patron)) System.out.println("Ваш тип абонемента " +
                patron.getAbonementType() + " не дает права на тренировки в выбранной зоне");

        if (!isTrainingTimeCorrect(patron, trainingTime)){
            System.out.println("Ваш абонемент не позволяет вам заниматься в это время");
        }

        if (isPatronInZone(patron, swimmingPoolMembers) ||
            isPatronInZone(patron, groupClassMembers) ||
            isPatronInZone(patron, gymMembers)){
            System.out.println("Вы уже записаны в другую зону");
            return;
        }

        if (zone.equals(GymZone.GROUP_CLASS) && !isZoneFull(groupClassMembers)){
            addToZone(GymZone.GROUP_CLASS, patron, workingDate, trainingTime);
            System.out.println("В зале груповых занятий есть свободные места, можете посетить тренировку");
        } else if (zone.equals(GymZone.SWIMMING_POOL) && !isZoneFull(swimmingPoolMembers)){
            addToZone(GymZone.SWIMMING_POOL, patron, workingDate, trainingTime);
            System.out.println("В бассейне есть свободные места, можете посетить занятие");
        } else if (zone.equals(GymZone.GYM) && !isZoneFull(gymMembers)){
            addToZone(GymZone.GYM, patron, workingDate, trainingTime);
            System.out.println("В тренажерном зале есть свободные места, можете посетить занятие");
        } else System.out.println("В выбранной вами зоне нет свободных мест");

    }

    private void printPatronInZone(GymZone gymZone){
        GymPatron[] trainingZone;

        if (gymZone.equals(GymZone.GYM)) trainingZone = gymMembers;
        else if (gymZone.equals(GymZone.SWIMMING_POOL)) trainingZone = swimmingPoolMembers;
        else if (gymZone.equals(GymZone.GROUP_CLASS)) trainingZone = groupClassMembers;
        System.out.println("Зона тренировки: " + gymZone.getZoneName());

        int counter = 0;
        for (GymPatron patron : trainingZone){
            if (patron != null) {
                counter ++;
                System.out.println("Посетитель: " + patron.getName() + patron.getSurname()
                        + "год рождения: " + patron.getBirthYear() +
                        ". Абонемент " + patron.getAbonementType() +
                        "действует до " + patron.getEndTime());
            }
        }
        if (counter == 0) System.out.println("Посетителей нет");
    }

    public void printFCPatronInfo() {
        printPatronInZone(GymZone.GYM);
        printPatronInZone(GymZone.SWIMMING_POOL);
        printPatronInZone(GymZone.GROUP_CLASS);
    }

}
