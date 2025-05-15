package bg.tu_varna.sit.b1.f23621705.cli;

import bg.tu_varna.sit.b1.f23621705.cli.commands.file.*;
import bg.tu_varna.sit.b1.f23621705.cli.commands.jedi.*;
import bg.tu_varna.sit.b1.f23621705.cli.commands.planet.AddPlanet;
import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.interfaces.FileStatus;
import bg.tu_varna.sit.b1.f23621705.interfaces.FileSupplier;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    private final JediManager jediManager = new JediManager();
    private final Scanner scanner = new Scanner(System.in);
    private final Map<Commands, Command> commands = new HashMap<>();
    private String openedFile = null;
    FileStatus fileStatus = this::setCurrentFile;
    FileSupplier fileSupplier = this::getCurrentFile;

    public CLI() {

        commands.put(Commands.ADD_PLANET, new AddPlanet());
        commands.put(Commands.CREATE_JEDI, new CreateJedi(jediManager));
        commands.put(Commands.REMOVE_JEDI, new RemoveJedi(jediManager));
        commands.put(Commands.PROMOTE_JEDI, new PromoteJedi(jediManager));
        commands.put(Commands.DEMOTE_JEDI, new DemoteJedi(jediManager));
        commands.put(Commands.GET_STRONGEST_JEDI, new GetStrongestJedi(jediManager));
        commands.put(Commands.GET_YOUNGEST_JEDI, new GetYoungestJedi(jediManager));
        commands.put(Commands.GET_MOST_USED_SABER_COLOUR, new GetMostUsedLightsaberColour(jediManager));
        commands.put(Commands.PRINT, new Print(jediManager));
        commands.put(Commands.HELP, new Help());
        commands.put(Commands.OPEN, new Open(jediManager, fileStatus));
        commands.put(Commands.CLOSE, new Close(jediManager, fileStatus, fileSupplier));
        commands.put(Commands.SAVE, new Save(jediManager, fileSupplier));
        commands.put(Commands.SAVE_AS, new SaveAs(jediManager, fileStatus));
        commands.put(Commands.EXIT, new Exit());

    }

    public void run() {

        System.out.println("Welcome to the Star Wars universe! \nType 'help' for a list of the commands.");

        while (true) {

            System.out.print("> ");
            String input = scanner.nextLine();

            String[] args = input.trim().split(" ");

            if (args.length == 0) {

                continue;

            }

            String commandName = args[0];

            if (fileSupplier.get() == null && !commandName.equals("open") && !commandName.equals("help") && !commandName.equals("exit")) {

                System.out.println("No file is currently open! Please open a file first.");
                continue;

            }

            if(Commands.exists(commandName)){

                Command command = commands.get(Commands.valueOf(commandName.toUpperCase()));

                try {

                    command.execute(args);

                } catch (Exception e) {

                    System.err.println(e.getMessage());

                }

            }else{

                System.out.println("Unknown command: " + commandName +
                        "\nType 'help' for a list of the commands.");

            }

        }

    }

    private String getCurrentFile() {
        return this.openedFile;
    }

    private void setCurrentFile(String file) {
        this.openedFile = file;
    }

}
