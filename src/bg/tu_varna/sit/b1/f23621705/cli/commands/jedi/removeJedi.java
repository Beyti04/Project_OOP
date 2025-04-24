package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetManager;

import java.util.Scanner;

public class removeJedi implements Command {
    private final JediManager jediManager;
    private final PlanetManager planetManager;
    private final Scanner scanner=new Scanner(System.in);

    public removeJedi(JediManager jediManager,PlanetManager planetManager) {
        this.jediManager = jediManager;
        this.planetManager=planetManager;
    }

    @Override
    public void execute() {
        String name;
        String planet;
        System.out.println("Enter jedi name: ");
        name=scanner.nextLine();

        System.out.println("Enter planet name: ");
        planet=scanner.nextLine();

        if(jediManager.getJedi(name)==null){
            System.out.println("There is no jedi with this name!");
        }else if(planetManager.getPlanet(planet)!=null){
            jediManager.removeJedi(name,planet);
        }
    }
}
