package bg.tu_varna.sit.b1.f23621705.cli.commands.jedi;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.Universe;

import java.io.IOException;

/**
 * Представлява команда за извличане на най-използвания цвят на светлинен меч на определена планета
 * или от джедаи с определен ранг на тази планета. Информацията за цвета на светлинния меч се
 * извлича чрез JediManager инстанцията, свързана с тази команда.
 *
 * Тази команда валидира входните аргументи, за да осигури правилна употреба и проверява
 * съществуването на посочената планета и ранг на джедай преди изпълнение на операцията.
 * Ако не е предоставен ранг, проверява за джедаи с ранг GRAND_MASTER.
 * Изключения се хвърлят при невалидни входни данни или ако условията за извличане не са изпълнени.
 */
public class GetMostUsedLightsaberColour implements Command {
    private final JediManager jediManager;
    private final Universe universe = Universe.getUniverseInstance();

    public GetMostUsedLightsaberColour(JediManager jediManager) {
        this.jediManager = jediManager;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {

        if (args.length >= Commands.GET_MOST_USED_SABER_COLOUR.getI() && args.length <= Commands.GET_MOST_USED_SABER_COLOUR.getJ()) {

            Planet planet = universe.getPlanet(args[1]);

            if (planet != null) {

                if (args.length == Commands.GET_MOST_USED_SABER_COLOUR.getI()) {

                    if (planet.getJedis().stream().anyMatch(jedi1 -> jedi1.getJediRank().equals(JediRank.GRAND_MASTER))) {

                        System.out.println("The most used light saber colour on the planet " + args[1] +
                                " is " + jediManager.mostUsedLightSaberColour(args[1],null));

                    } else {

                        throw new IOException("There are no jedis with the rank GRAND MASTER on the planet " + args[1]);

                    }

                } else {
                    JediRank rank;

                    if (args.length==Commands.GET_MOST_USED_SABER_COLOUR.getJ()) {

                        rank = JediRank.getRank(args[2]+"_"+args[3]);

                    } else {

                        rank=JediRank.getRank(args[2]);

                    }

                    if(rank==null){

                        throw new IOException("Please enter valid data for jedi rank!");

                    }

                    if(planet.getJedis().stream().anyMatch(jedi1 -> jedi1.getJediRank().equals(rank))){
                        System.out.println("The most used light saber colour on the planet " + args[1] +
                                "\nby the jedis with rank " + rank.name() + " is " + jediManager.mostUsedLightSaberColour(args[1], rank));
                    }else{
                        throw new IOException("There are no jedis with the rank " + rank.name() + " on the planet " + args[1]);
                    }
                    
                }

            }else{

                throw new IOException("Please enter valid data for planet!");

            }

        } else {

            throw new CommandException("Usage: get_most_used_saber_colour <planet_name> /\n       get_most_used_saber_colour <planet_name> <jedi_rank>");

        }

    }

}
