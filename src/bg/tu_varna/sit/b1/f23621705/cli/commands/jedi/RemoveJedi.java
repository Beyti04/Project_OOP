package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.util.Scanner;

public class RemoveJedi implements Command {
    private final JediManager jediManager;
    private final Scanner scanner = new Scanner(System.in);

    public RemoveJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute() {
        String name;
        String planet;
        System.out.println("Enter jedi name: ");
        name = scanner.nextLine();

        System.out.println("Enter planet name: ");
        planet = scanner.nextLine();
        if (PlanetsList.getPlanetsInstance().getPlanet(planet) != null) {
            if (jediManager.getJedi(name) != null && PlanetsList.getPlanetsInstance().getPlanet(planet).getJedi(name) != null) {
                jediManager.removeJedi(name, planet);
                System.out.println("Jedi " + name + " has been removed from planet " + planet);
            } else {
                System.out.println("There is no jedi with the name: " + name + " on planet: " + planet);
            }
        } else {
            System.out.println("There is no planet with the name: " + planet);
        }

    }
}
