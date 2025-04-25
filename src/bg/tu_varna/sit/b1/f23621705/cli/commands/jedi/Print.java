package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;

import java.util.Scanner;

public class Print implements Command {
    JediManager jediManager;
    Scanner scanner = new Scanner(System.in);

    public Print(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute() {
        String name = scanner.nextLine();

        if (jediManager.getJedi(name) != null) {
            System.out.println(jediManager.printByJediName(name));
        } else {
            System.out.println("There is no jedi with the name " + name);
        }
    }
}
