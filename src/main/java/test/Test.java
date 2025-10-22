package test;

import entities.machine;
import entities.salle;
import services.machineservice;
import  services.salleservice;

import java.util.Date;


public class Test {
    public static void main(String[] args) {
        salleservice salleService = new salleservice();
        machineservice machineService = new machineservice();

        // Création et insertion de salles/machines
        salle salle1 = new salle("A1");
        salle salle2 = new salle("B2");
        salleService.create(salle1);
        salleService.create(salle2);

        machine machine1 = new machine("M123", new Date(), salleService.findById(1));
        machine machine2 = new machine("M124", new Date(), salleService.findById(2));
        machineService.create(machine1);
        machineService.create(machine2);

        // Affichage des salles et leurs machines
        for(salle salle : salleService.findAll()) {
            System.out.println("Salle: " + salle.getCode());
            for(machine machine : salle.getMachines()) {
                System.out.println("  Machine: " + machine.getRef());
            }
        }

        // Utilisation de la méthode findBetweenDate
        Date d1 = new Date(110, 0, 1); // 1er janvier 2010
        Date d2 = new Date(); // Date actuelle
        System.out.println("Machines achetées entre " + d1 + " et " + d2 + ":");
        for(machine m : machineService.findBetweenDate(d1, d2)) {
            System.out.println(m.getRef() + " achetée le " + m.getDateAchat());
        }
    }
}
