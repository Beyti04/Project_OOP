package bg.tu_varna.sit.b1.f23621705;

import bg.tu_varna.sit.b1.f23621705.cli.commands.jedi.addJedi;
import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.modules.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        /*
        JediManager jediManager = new JediManager();
        Planet planet = new Planet("Planet1");
        PlanetsList.getPlanetsInstance().addPlanet(planet);

        Jedi jedi = new Jedi("Jedi1", JediRank.GRAND_MASTER, 120, LightsaberColour.RED, 1.5, "Planet1");

        System.out.println(PlanetsList.getPlanetsInstance().searchPlanet("Planet1"));
        jediManager.createJedi(jedi);
        System.out.println(PlanetsList.getPlanetsInstance().searchPlanet("Planet1"));


         final double MAX_STRENGTH = 2.0;
         final double MIN_STRENGTH = 0.99;

        boolean flag;
        String input;
        double age;
        List<JediRank> ranks= Arrays.asList(JediRank.YOUNGLING, JediRank.INITIATE, JediRank.PADAWAN, JediRank.KNIGHT_ASPIRANT, JediRank.KNIGHT, JediRank.MASTER, JediRank.BATTLE_MASTER, JediRank.GRAND_MASTER);
        Scanner scanner=new Scanner(System.in);
        JediRank rank;
        double strength;

        System.out.println("Strength: ");
        do {
            flag=true;
            input=scanner.nextLine();

            if(input.isBlank()||input.matches("\\d+")||Double.parseDouble(input)<MIN_STRENGTH||Double.parseDouble(input)>MAX_STRENGTH){
                System.out.println("Please enter valid data for strength!");
            }
            else{
                strength=Double.parseDouble(input);
                flag=false;
            }
        }while (flag);
    }*/
        addJedi addJedi = new addJedi(new JediManager(), new PlanetManager());
        addJedi.execute();
    }
}
