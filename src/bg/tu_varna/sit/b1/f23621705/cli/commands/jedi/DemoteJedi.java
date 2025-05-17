package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;

import java.io.IOException;

/**
 * Представлява команда за понижаване на Джедай в ранг и съответно коригиране на силата му.
 * Този клас отговаря за обработката на потребителския вход, проверката на валидността на данните,
 * и изпълнението на необходимите операции за понижаване на Джедай чрез JediManager.
 */
public class DemoteJedi implements Command {
    private final JediManager jediManager;

    public DemoteJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (args.length != Commands.DEMOTE_JEDI.getI()) {

            throw new CommandException("Usage: demote_jedi <jedi_first_name> <jedi_last_name> <multiplier>");

        }

        String name = args[1]+" "+args[2];

        Jedi jedi=jediManager.getJedi(name);

        if (jedi != null) {

            if (jedi.getJediRank().prev()!=null) {

                String multiplier = args[3];

                if (multiplier.isBlank() || !multiplier.matches("([0-9]*[.])?[0-9]+")||Double.parseDouble(multiplier)<=0) {

                    throw new IOException("There must be valid data for the multiplier!");

                } else {

                    jediManager.demoteJedi(name, Double.parseDouble(multiplier));
                    System.out.println("Jedi:" + name + " has been demoted successfully!");
                    System.out.println("New rank:" + jedi.getJediRank());
                    System.out.println("New strength:" + jedi.getStrength());

                }
            } else {

                throw new IOException("Jedi" + name + " has reached the lowest rank possible!");

            }
        } else {

            throw new IOException("There are no jedi with the name " + name);

        }

    }

}
