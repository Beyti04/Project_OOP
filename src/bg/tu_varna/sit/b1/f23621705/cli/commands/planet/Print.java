package bg.tu_varna.sit.b1.f23621705.cli.commands.planet;

import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.util.Scanner;

public class Print implements Command {
    JediManager jediManager;
    Scanner scanner=new Scanner(System.in);

    public Print(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute() {
        String planet=scanner.nextLine();

        if(PlanetsList.getPlanetsInstance().getPlanet(planet)!=null){
            System.out.println(jediManager.printByPlanetName(planet));
        }else{
            System.out.println("There is no planet with the name "+planet);
        }
    }
}
