package bg.tu_varna.sit.b1.f23621705.cli.commands.planet;

import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.util.Scanner;

public class addPlanet implements Command {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        String planetName=scanner.nextLine();

        if(PlanetsList.getPlanetsInstance().getPlanet(planetName)==null){
            PlanetsList.getPlanetsInstance().createPlanet(new Planet(planetName));
        }else{
            System.out.println("Planet with the name "+planetName+" already exists!");
        }
    }
}
