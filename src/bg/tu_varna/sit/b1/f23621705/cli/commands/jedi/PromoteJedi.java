package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;

import java.io.IOException;

public class PromoteJedi implements Command {
    private final JediManager jediManager;


    public PromoteJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {

        if (args.length != Commands.PROMOTE_JEDI.getI()) {

            throw new CommandException("Usage: promote_jedi <jedi_first_name> <jedi_last_name> <multiplier>");

        }

        String name = args[1] + " " + args[2];

        if (jediManager.getJedi(name) != null) {

            if (jediManager.getJedi(name).getJediRank().next() != null) {

                String multiplier = args[3];

                if (multiplier.isBlank() || !multiplier.matches("([0-9]*[.])?[0-9]+")) {

                    throw new IOException("There must be valid data for the multiplier!");

                } else {

                    jediManager.promoteJedi(name, Double.parseDouble(multiplier));
                    System.out.println("Jedi has been promoted successfully!");

                }

            } else {

                throw new IOException("Jedi" + name + " has reached the highest rank possible!");

            }

        } else {

            throw new IOException("There are no jedi with the name " + name);

        }

    }

}
