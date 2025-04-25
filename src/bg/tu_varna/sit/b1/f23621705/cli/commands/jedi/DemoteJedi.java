package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;

import java.util.Scanner;

public class DemoteJedi implements Command {
    private final JediManager jediManager;
    private final Scanner scanner = new Scanner(System.in);

    public DemoteJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute() {
        String name = scanner.nextLine();

        if (jediManager.getJedi(name) != null) {
            if (jediManager.getJedi(name).getJediRank() != JediRank.YOUNGLING) {
                String multiplier = scanner.nextLine();
                if (multiplier.isBlank() || !multiplier.matches("\\d+")) {
                    System.out.println("There must be valid data for the multiplier!");
                } else {
                    jediManager.demoteJedi(name, Double.parseDouble(multiplier));
                    System.out.println("Jedi has been demoted successfully!");
                }
            } else {
                System.out.println("Jedi" + name + " has reached the lowest rank possible!");
            }
        } else {
            System.out.println("There are no jedi with the name " + name);
        }
    }
}
