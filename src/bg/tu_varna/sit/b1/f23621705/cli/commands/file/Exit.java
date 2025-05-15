package bg.tu_varna.sit.b1.f23621705.cli.commands.file;

import bg.tu_varna.sit.b1.f23621705.interfaces.Command;

public class Exit implements Command {
    @Override
    public void execute(String[] args) {

        System.out.println("Exiting program...");
        System.exit(0);

    }
}
