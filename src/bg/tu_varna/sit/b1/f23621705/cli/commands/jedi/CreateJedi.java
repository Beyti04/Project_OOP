package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CreateJedi implements Command {
    private final JediManager jediManager;
    private final PlanetsList planetsList=PlanetsList.getPlanetsInstance();

    private final double MAX_STRENGTH = 2;
    private final double MIN_STRENGTH = 1;

    public CreateJedi(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws IOException, CommandException {
        if(args.length!= Commands.CREATE_JEDI.getI()){
            throw new CommandException("Usage: create_jedi <planet> <jedi_first_name> <jedi_last_name> <rank> <age> <saber_colour> <strength>");
        }

        String planet = args[1];
        String name = args[2]+" "+args[3];
        String rank = args[4];
        String age = args[5];
        String lightsaberColour = args[6];
        String strength = args[7];

        if(planet.isBlank()||planetsList.getPlanet(planet)==null){
            throw new IOException("Please enter valid data for planet!");
        }

        if(name.isBlank()||jediManager.getJedi(name)!=null){
            throw new IOException("Please enter valid data for the jedis name!");
        }

        if(rank.isBlank()||!JediRank.exists(rank)){
            throw new IOException("Please enter valid data for rank!");
        }

        if (age.isBlank() || !age.matches("\\d+") || age.equals("0")){
            throw new IOException("Please enter valid data for age!");
        }

        if(lightsaberColour.isBlank()||!LightsaberColour.exists(lightsaberColour)){
            throw new IOException("Please enter valid data for saber colour!");
        }

        if(strength.isBlank()||!strength.matches("([0-9]*[.])?[0-9]+")||Double.parseDouble(strength)<MIN_STRENGTH||Double.parseDouble(strength)>MAX_STRENGTH){
            throw new IOException("Please enter valid data for strength!");
        }

        Jedi jedi=new Jedi(planetsList.getPlanet(planet),name,JediRank.valueOf(rank),Integer.parseInt(age),LightsaberColour.valueOf(lightsaberColour),Double.parseDouble(strength));
        jediManager.createJedi(jedi);
    }
}
