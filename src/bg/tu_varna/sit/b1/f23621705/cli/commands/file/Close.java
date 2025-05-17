package bg.tu_varna.sit.b1.f23621705.cli.commands.file;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.interfaces.FileStatus;
import bg.tu_varna.sit.b1.f23621705.interfaces.FileSupplier;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;

/**
 * Close класът имплементира Command интерфейса и предоставя функционалност
 * за обработка на затварянето на файл в системата. Също така извършва
 * операции по почистване, премахвайки всички данни свързани със затворения файл.
 */
public class Close implements Command {
    private final JediManager jediManager;
    private final FileStatus fileStatus;
    private final FileSupplier fileSupplier;

    public Close(JediManager jediManager, FileStatus fileStatus, FileSupplier fileSupplier) {
        this.jediManager = jediManager;
        this.fileStatus = fileStatus;
        this.fileSupplier = fileSupplier;
    }

    @Override
    public void execute(String[] args) throws CommandException {

        if(args.length!= Commands.CLOSE.getI()){

            throw new CommandException("Usage: close");

        }

        String currentFile = fileSupplier.get();
        String fileName = currentFile.split("/")[currentFile.split("/").length - 1];
        fileStatus.SetCurrentFile(null);
        System.out.println("Successfully closed file " + fileName);
        jediManager.removeAll();

    }
}
