package bg.tu_varna.sit.b1.f23621705.cli.commands.file;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.interfaces.FileStatus;
import bg.tu_varna.sit.b1.f23621705.modules.FileManager;
import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;

import java.util.List;

public class SaveAs implements Command {
    private final JediManager jediManager;
    private final FileStatus fileStatus;

    public SaveAs(JediManager jediManager, FileStatus fileStatus) {
        this.jediManager = jediManager;
        this.fileStatus = fileStatus;
    }

    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length != Commands.SAVE_AS.getI()) {
            throw new IllegalArgumentException("Usage: save_as <file>");
        }

        if (args[1].isBlank()) {
            throw new IllegalArgumentException("File path can't be empty!");
        }

        String filePath = args[1];

        try {
            List<Jedi> jedis = jediManager.getJedis();

            if (jedis.isEmpty()) {
                throw new IllegalArgumentException("There are no jedis to save!");
            }

            FileManager.saveToFile(filePath, jedis);
            fileStatus.SetCurrentFile(filePath);
            String filename = args[1].split("/")[args[1].split("/").length - 1];
            System.out.println("Successfully saved " + filename);
        } catch (Exception e) {
            throw new CommandException("Failed to save as: " + e.getMessage());
        }
    }
}
