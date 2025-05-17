package bg.tu_varna.sit.b1.f23621705.cli.commands.file;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.interfaces.FileSupplier;
import bg.tu_varna.sit.b1.f23621705.modules.FileManager;
import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;

import java.util.List;

/**
 * Save класът отговаря за запазването на текущото състояние на Jedi данните
 * във файл. Той използва JediManager за извличане на данните и 
 * FileSupplier за определяне на пътя до файла. Този клас имплементира Command
 * интерфейса, позволявайки да се придържа към командна структура на изпълнение.
 *
 * Save командата проверява дали е избран подходящ файл за записване и
 * верифицира коректността на входните аргументи. Ако в момента няма отворен файл
 * или аргументите са невалидни, се хвърля CommandException. 
 * В противен случай се прави опит за запазване на данните в указания файл, като всякакви
 * грешки по време на процеса също ще предизвикат CommandException.
 */
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
