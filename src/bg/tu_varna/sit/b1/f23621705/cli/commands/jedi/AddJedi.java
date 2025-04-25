package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddJedi implements Command {
    private final JediManager jediManager;
    private final Scanner scanner = new Scanner(System.in);

    private final double MAX_STRENGTH = 2;
    private final double MIN_STRENGTH = 1;

    private final List<JediRank> ranks= List.of(JediRank.values());
    private final List<LightsaberColour> colours = List.of(LightsaberColour.values());


    public AddJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute() {
        boolean flag;

        String input;
        String name = null;
        JediRank rank = null;
        int age = 0;
        LightsaberColour lightsaberColour = null;
        double strength = 0;
        String planet = null;

        String line = "=".repeat(20);
        System.out.println(line);

        System.out.println("Name: ");
        do {
            flag = true;
            input = scanner.nextLine();
            if (input.isBlank()) {
                System.out.println("Please enter valid data for the name!");
            } else {
                if (jediManager.getJedi(name) == null) {
                    name = input;
                    flag = false;
                }

            }
        } while (flag);

        System.out.println("Rank: ");
        do {
            flag = true;
            input = scanner.nextLine().toUpperCase();
            String finalInput = input;
            if (input.isBlank() || ranks.stream().noneMatch(rank1 -> rank1.name().equals(finalInput))) {
                System.out.println("Please enter valid data for rank!");
            } else {
                rank = ranks.stream().filter(rank1 -> rank1.name().equals(finalInput)).findFirst().get();
                flag = false;
            }
        } while (flag);

        System.out.println("Age: ");
        do {
            flag = true;
            input = scanner.nextLine();
            if (input.isBlank() || !input.matches("\\d+") || input.equals("0")) {
                System.out.println("Please enter valid data for age!");
            } else {
                age = Integer.parseInt(input);
                flag = false;
            }
        } while (flag);

        System.out.println("Light saber colour: ");
        do {
            flag = true;
            input = scanner.nextLine().toUpperCase();
            String finalInput = input;
            if (input.isBlank() || colours.stream().noneMatch(colour1 -> colour1.name().equals(finalInput))) {
                System.out.println("Please enter valid data for a light saber colour!");
            } else {
                lightsaberColour = colours.stream().filter(colour1 -> colour1.name().equals(finalInput)).findFirst().get();
                flag = false;
            }
        } while (flag);

        System.out.println("Strength: ");
        do {
            flag = true;
            input = scanner.nextLine();
            if (input.isBlank() || input.matches("\\d+") || Double.parseDouble(input) < MIN_STRENGTH || Double.parseDouble(input) > MAX_STRENGTH) {
                System.out.println("Please enter valid data for strength!");
            } else {
                strength = Double.parseDouble(input);
                flag = false;
            }
        } while (flag);

        System.out.println("Planet: ");
        do {
            flag = true;
            input = scanner.nextLine();
            if (input.isBlank()) {
                System.out.println("Please enter valid data for planet!");
            } else if (PlanetsList.getPlanetsInstance().getPlanet(input) == null) {
                String temp = input;
                System.out.println("There is no planet with this name!");
                System.out.println("Do you want to create a planet with this name?  Y/N    YES/NO");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
                    PlanetsList.getPlanetsInstance().createPlanet(new Planet(temp));
                    planet = temp;
                    flag = false;
                }
            } else {
                planet = input;
                flag = false;
            }
        } while (flag);

        jediManager.createJedi(new Jedi(name, rank, age, lightsaberColour, strength, planet));

        System.out.println(PlanetsList.getPlanetsInstance().getPlanet(planet).toString());
    }
}
