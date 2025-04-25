package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.util.Scanner;

public class GetStrongestJedi implements Command {
    private final JediManager jediManager;
    private final Scanner scanner = new Scanner(System.in);

    public GetStrongestJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute() {
        System.out.println("Enter planet name: ");

        String planet = scanner.nextLine();
        if (PlanetsList.getPlanetsInstance().getPlanet(planet) != null) {
            if (jediManager.getStrongestJedi(planet) != null) {
                System.out.println(jediManager.getStrongestJedi(planet).toString());
            } else {
                System.out.println("There are no jedis on the planet " + planet);
            }
        } else {
            System.out.println("There is no planet with the name " + planet);
        }
    }
}
