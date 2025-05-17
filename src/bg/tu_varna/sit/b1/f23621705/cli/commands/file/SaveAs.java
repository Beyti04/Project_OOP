package bg.tu_varna.sit.b1.f23621705.cli.commands.file;

import bg.tu_varna.sit.b1.f23621705.enums.Commands;
import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;
import bg.tu_varna.sit.b1.f23621705.interfaces.Command;
import bg.tu_varna.sit.b1.f23621705.interfaces.FileStatus;
import bg.tu_varna.sit.b1.f23621705.modules.FileManager;
import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;

import java.util.List;

/**
 * Представлява командата SaveAs, която записва колекция от обекти тип Jedi в
 * определен път до файл. Тази команда проверява предоставения път до файла, осигурява
 * правилния формат на файла, записва данните и обновява текущия статус на файла.
 */
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

            int dotIndex=filePath.lastIndexOf('.');

            if(dotIndex>0 && dotIndex<filePath.length()-1){
                String extension=filePath.substring(dotIndex);
                if(extension!=null && !extension.equals(".txt")){
                    throw new IllegalArgumentException("Invalid file format. Please use file with .txt format!");
                }
            }else{
                filePath+=".txt";
            }


            FileManager.saveToFile(filePath, jedis);
            fileStatus.SetCurrentFile(filePath);
            String filename = filePath.split("/")[filePath.split("/").length - 1];
            System.out.println("Successfully saved " + filename);

        } catch (Exception e) {

            throw new CommandException("Failed to save as: " + e.getMessage());

        }
    }

}
