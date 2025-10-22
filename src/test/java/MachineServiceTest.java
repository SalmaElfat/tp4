//package services;

import entities.machine;
import entities.salle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.machineservice;
import services.salleservice;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class MachineServiceTest {

    private machineservice machineService;
    private machine machine;
    private salle salle;
    private salleservice salleService;

    @Before
    public void setUp() {
        machineService = new machineservice();
        salleService = new salleservice();
        salle = new salle("A101");


        salleService.create(salle);

        machine = new machine();
        machine.setRef("MACH-001");
        machine.setDateAchat(new Date());
        machine.setSalle(salle);

         machineService.create(machine);
    }

    @After
    public void tearDown() {
        machine foundMachine = machineService.findById(machine.getId());
        if (foundMachine != null) {
            machineService.delete(foundMachine);
        }

        salle foundSalle = salleService.findById(salle.getId());
        if (foundSalle != null) {
            salleService.delete(foundSalle);
        }
    }

    @Test
    public void testCreate() {
        assertNotNull("Machine should have been created with an ID", machine.getId());
    }

    @Test
    public void testFindById() {
        machine foundMachine = machineService.findById(machine.getId());
        assertNotNull("Machine should be found", foundMachine);
        assertEquals("Found machine should match", machine.getRef(), foundMachine.getRef());
    }

    @Test
    public void testUpdate() {
        machine.setRef("MACH-002"); // Modifiez la référence pour tester la mise à jour
        boolean result = machineService.update(machine);
        assertTrue("Machine should be updated successfully", result);

        machine updatedMachine = machineService.findById(machine.getId());
        assertEquals("Updated machine ref should match", "MACH-002", updatedMachine.getRef());
    }

    @Test
    public void testDelete() {
        boolean result = machineService.delete(machine);
        assertTrue("Machine should be deleted successfully", result);

        machine foundMachine = machineService.findById(machine.getId());
        assertNull("Machine should not be found after deletion", foundMachine);
    }

    @Test
    public void testFindBetweenDate() {
        List<machine> machines = machineService.findBetweenDate(
                new Date(System.currentTimeMillis() - 86400000), // Hier
                new Date() // Aujourd'hui
        );
        assertNotNull("Machines list should not be null", machines);
        assertTrue("Machines list should contain at least one machine", machines.size() > 0);
    }
}
