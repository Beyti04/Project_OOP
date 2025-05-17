package bg.tu_varna.sit.b1.f23621705.cli.commands.file;

import bg.tu_varna.sit.b1.f23621705.interfaces.Command;

/**
 * Exit класът имплементира Command интерфейса и предоставя функционалност 
 * за прекратяване изпълнението на програмата. При изпълнение извежда 
 * съобщение за информиране на потребителя и завършва приложението със статус код 0.
 */
public class Exit implements Command {
    @Override
    public void execute(String[] args) {

        System.out.println("Exiting program...");
        System.exit(0);

    }
}
