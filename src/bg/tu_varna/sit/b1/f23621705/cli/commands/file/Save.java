package bg.tu_varna.sit.b1.f23621705.cli.commands.file;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.interfaces.FileSupplier;
import bg.tu_varna.sit.b1.f23621705.modules.FileManager;
import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;

import java.util.List;

public class Save implements Command {
    private final JediManager jediManager;
    private final FileSupplier fileSupplier;

    public Save(JediManager jediManager, FileSupplier fileSupplier) {
        this.jediManager = jediManager;
        this.fileSupplier = fileSupplier;
    }

    @Override
    public void execute(String[] args) throws CommandException {

        if(args.length!= Commands.SAVE.getI()){

            throw new CommandException("Usage: save\nIf you want to save to a new file use: save_as <file>");

        }

        String currentFile = fileSupplier.get();
        String filename = currentFile.split("/")[currentFile.split("/").length - 1];

        if (filename == null || filename.isBlank()) {

            throw new CommandException("No file is currently opened!");

        }

        try {

            List<Jedi> jedis = jediManager.getJedis();
            FileManager.saveToFile(currentFile, jedis);

            System.out.println("Successfully saved " + filename);

        } catch (Exception e) {

            throw new CommandException("Failed to save: " + e.getMessage());

        }
    }

}
