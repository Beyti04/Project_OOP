package bg.tu_varna.sit.b1.f23621705.cli.commands.file;

import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.interfaces.FileStatus;
import bg.tu_varna.sit.b1.f23621705.modules.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Open implements Command {
    private final JediManager jediManager;
    private final FileStatus fileStatus;

    public Open(JediManager jediManager, FileStatus fileStatus) {
        this.jediManager = jediManager;
        this.fileStatus = fileStatus;
    }

    @Override
    public void execute(String[] args) throws CommandException, IOException {
        if (args.length != 2) {
            throw new CommandException("Usage: open <filename>");
        }

        String filePath = args[1];
        if (!filePath.endsWith(".txt")) {
            throw new CommandException("Invalid file format. Please use file with .txt format!");
        }

        String filename = args[1].split("/")[args[1].split("/").length - 1];
        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            List<Jedi> jedis = FileManager.readFile(filePath);

            for (Jedi jedi : jedis) {
                if(PlanetsList.getPlanetsInstance().getPlanet(jedi.getPlanet())==null){
                    PlanetsList.getPlanetsInstance().createPlanet(new Planet(jedi.getPlanet()));
                }
                jediManager.createJedi(jedi);
            }
            fileStatus.SetCurrentFile(filePath);
            System.out.println("Successfully opened file:" + filename);
        } catch (Exception e) {
            throw new CommandException("Failed to open file: " + e.getMessage());
        }
    }
}
