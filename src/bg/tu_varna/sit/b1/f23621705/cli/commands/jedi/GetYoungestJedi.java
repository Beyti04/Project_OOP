package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.Universe;

import java.io.IOException;

public class GetYoungestJedi implements Command {
    private final JediManager jediManager;
    private final Universe universe = Universe.getUniverseInstance();

    public GetYoungestJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {

        if (args.length == Commands.GET_YOUNGEST_JEDI.getI() || args.length == Commands.GET_YOUNGEST_JEDI.getJ()) {

            Planet planet = universe.getPlanet(args[1]);

            if (planet != null) {

                JediRank rank;

                if(args.length==Commands.GET_YOUNGEST_JEDI.getI()){

                    rank=JediRank.getRank(args[2]);

                }else{

                    rank=JediRank.getRank(args[2]+"_"+args[3]);

                }

                if (rank==null || jediManager.getYoungestJedi(args[1], rank) == null) {

                    throw new IOException("There is no jedi with the rank of " + args[2].toUpperCase() + " on the planet " + args[1]);

                } else {

                    System.out.println(jediManager.getYoungestJedi(args[1], rank));

                }

            } else {

                throw new IOException("There is no planet with the name " + args[1]);

            }

        }else{

            throw new CommandException("Usage: get_youngest_jedi <planet_name> <jedi_rank>");

        }

    }

}
