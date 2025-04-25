package bg.tu_varna.sit.b1.f23621705.cli.commands.planet;

import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.util.Scanner;

public class PrintForTwoPlanets implements Command {
    JediManager jediManager;
    Scanner scanner=new Scanner(System.in);

    public PrintForTwoPlanets(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute() {
        String planet1=scanner.nextLine();
        if(PlanetsList.getPlanetsInstance().getPlanet(planet1)!=null){
            String planet2=scanner.nextLine();
            if(PlanetsList.getPlanetsInstance().getPlanet(planet2)!=null){
                System.out.println(jediManager.printByPlanetName(planet1,planet2));
            }else{
                System.out.println("There is no planet with the name "+planet2);
            }
        }else {
            System.out.println("There is no planet with the name "+planet1);
        }
    }
}
