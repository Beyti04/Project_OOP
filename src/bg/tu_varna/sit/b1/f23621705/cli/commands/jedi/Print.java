package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.Universe;

import java.io.IOException;

/**
 * Print класът отговаря за обработката на командата "print" в приложението. 
 * Той взаимодейства с инстанциите на JediManager и Universe за извличане на информация
 * относно планети, джедаи или връзки между планети въз основа на предоставените входни аргументи.
 *
 * Командата може да се използва за:
 * - Отпечатване на информация за конкретна планета.
 * - Отпечатване на детайли за конкретен джедай по неговото име и фамилия.
 * - Отпечатване на информация за две планети.
 *
 * Този клас имплементира интерфейса Command, осигурявайки съвместимост с другите команди
 * в рамките на приложението.
 */
public class Print implements Command {
    JediManager jediManager;
    Universe universe = Universe.getUniverseInstance();

    public Print(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {

        if (args.length == Commands.PRINT.getI()) {

            Planet planet = universe.getPlanet(args[1]);

            if (planet != null) {

                System.out.println(jediManager.printByPlanetName(args[1]));

            } else {

                throw new IOException("There is no data for this input!");

            }

        } else if (args.length == Commands.PRINT.getJ()) {

            if (jediManager.getJedi(args[1] + " " + args[2]) != null) {

                System.out.println(jediManager.printByJediName(args[1] + " " + args[2]));

            } else if (universe.getPlanet(args[1]) != null && universe.getPlanet(args[2]) != null) {

                System.out.println(jediManager.printByPlanetsName(args[1], args[2]));

            } else {

                throw new IOException("There is no data for this input!");

            }

        }else{

            throw new CommandException("Usage: print <planet_name>/" +
                    "\nprint <jedi_first_name> <jedi_last_name>/" +
                    "\nprint <first_planet> <second_planet>");

        }

    }

}
