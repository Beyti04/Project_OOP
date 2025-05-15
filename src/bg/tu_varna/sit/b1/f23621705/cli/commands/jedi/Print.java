package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.io.IOException;

public class Print implements Command {
    JediManager jediManager;
    PlanetsList planetsList=PlanetsList.getPlanetsInstance();

    public Print(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if(args.length==Commands.PRINT.getI()){
            String planet=args[1];
            if(planetsList.getPlanet(planet)!=null){
                System.out.println(jediManager.printByPlanetName(planet));
            }else{
                throw new IOException("There is no data for this input!");
            }
        }else if(args.length==Commands.PRINT.getJ()){
            String arg1=args[1];
            String arg2=args[2];

            if(jediManager.getJedi(arg1+" "+arg2)!=null){
                String name=arg1+" "+arg2;
                System.out.println(jediManager.printByJediName(name));
            }else if(planetsList.getPlanet(arg1)!=null&&planetsList.getPlanet(arg2)!=null){
                System.out.println(jediManager.printByPlanetName(arg1,arg2));
            }else{
                throw new IOException("There is no data for this input!");
            }
        }
    }
}
