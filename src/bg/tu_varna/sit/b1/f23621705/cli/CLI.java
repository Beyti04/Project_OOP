package bg.tu_varna.sit.b1.f23621705.cli;

import bg.tu_varna.sit.b1.f23621705.cli.commands.file.*;
import bg.tu_varna.sit.b1.f23621705.cli.commands.jedi.*;
import bg.tu_varna.sit.b1.f23621705.cli.commands.planet.AddPlanet;
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
    private final Map<String, Command> commands = new HashMap<>();
    private String openedFile = null;
    FileStatus fileStatus = this::setCurrentFile;
    FileSupplier fileSupplier = this::getCurrentFile;

    public CLI() {
        commands.put("add_planet", new AddPlanet());
        commands.put("create_jedi", new CreateJedi(jediManager, scanner));
        commands.put("remove_jedi", new RemoveJedi(jediManager));
        commands.put("promote_jedi", new PromoteJedi(jediManager));
        commands.put("demote_jedi", new DemoteJedi(jediManager));
        commands.put("get_strongest_jedi", new GetStrongestJedi(jediManager));
        commands.put("get_youngest_jedi", new GetYoungestJedi(jediManager));
        commands.put("get_most_used_saber_colour", new GetMostUsedLightsaberColour(jediManager));
        commands.put("print", new Print(jediManager));
        commands.put("help", new Help());
        commands.put("open", new Open(jediManager, fileStatus));
        commands.put("close", new Close(jediManager, fileStatus, fileSupplier));
        commands.put("save", new Save(jediManager, fileSupplier));
        commands.put("save_as", new SaveAs(jediManager, fileStatus));
        commands.put("exit", new Exit());
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
            String commandName = args[0].toLowerCase();
            Command command = commands.get(commandName);

            if (fileSupplier.get() == null && !commandName.equals("open") && !commandName.equals("help") && !commandName.equals("exit")) {
                System.out.println("No file is currently open! Please open a file first.");
                continue;
            }

            if (command != null) {
                try {
                    command.execute(args);
                } catch (CommandException | IOException e) {
                    System.err.println(e.getMessage());
                }
            } else {
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
