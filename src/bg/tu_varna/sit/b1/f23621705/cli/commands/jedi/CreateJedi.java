package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.Universe;

import java.io.IOException;

public class CreateJedi implements Command {
    private final JediManager jediManager;
    private final Universe universe = Universe.getUniverseInstance();

    private final double MAX_STRENGTH = 2;
    private final double MIN_STRENGTH = 1;

    public CreateJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws IOException, CommandException {

        if (args.length == Commands.CREATE_JEDI.getI() || args.length == Commands.CREATE_JEDI.getJ()) {

            Planet planet = universe.getPlanet(args[1]);
            String name = args[2] + " " + args[3];
            JediRank rank;
            String age;
            LightsaberColour lightsaberColour;
            String strength;

            if(args.length==Commands.CREATE_JEDI.getI()){

                rank = JediRank.getRank(args[4]);
                age = args[5];
                lightsaberColour = LightsaberColour.getColour(args[6]);
                strength = args[7];

            }else{

                rank=JediRank.getRank(args[4]+"_"+args[5]);
                age = args[6];
                lightsaberColour = LightsaberColour.getColour(args[7]);
                strength = args[8];
            }

            if (planet == null) {

                throw new IOException("Please enter valid data for planet!");

            }

            if (name.isBlank() || jediManager.getJedi(name) != null) {

                throw new IOException("Please enter valid data for the jedis name!");

            }

            if (rank == null) {

                throw new IOException("Please enter valid data for rank!");

            }

            if (age.isBlank() || !age.matches("\\d+") || age.equals("0")) {

                throw new IOException("Please enter valid data for age!");

            }

            if (lightsaberColour == null) {

                throw new IOException("Please enter valid data for saber colour!");

            }


            if (strength.isBlank() || !strength.matches("([0-9]*[.])?[0-9]+") || Double.parseDouble(strength) < MIN_STRENGTH || Double.parseDouble(strength) > MAX_STRENGTH) {

                throw new IOException("Please enter valid data for strength!");

            }

            jediManager.createJedi(new Jedi(planet, name, rank, Integer.parseInt(age), lightsaberColour, Double.parseDouble(strength)));

        } else {

            throw new CommandException("Usage: create_jedi <planet> <jedi_first_name> <jedi_last_name> <rank> <age> <saber_colour> <strength>");

        }

    }

}
