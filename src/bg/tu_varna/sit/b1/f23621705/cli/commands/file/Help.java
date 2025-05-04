package bg.tu_varna.sit.b1.f23621705.cli.commands.file;

import bg.tu_varna.sit.b1.f23621705.interfaces.Command;

public class Help implements Command {

    @Override
    public void execute(String[] args) {
        System.out.println("The following commands are supported: ");
        System.out.println("add_planet <planet_name>                             - adds new planet");
        System.out.println("create_jedi                                          - adds new jedi");
        System.out.println("remove_jedi <jedi_name> <planet_name>                - removes jedi");
        System.out.println("promote_jedi <jedi_name> <planet_name>               - promotes jedi");
        System.out.println("demote_jedi <jedi_name> <planet_name>                - demotes jedi");
        System.out.println("get_strongest_jedi <planet_name>                     - gets strongest jedi");
        System.out.println("get_most_used_saber_colour <planet_name>             - gets most used light saber colour");
        System.out.println("get_most_used_saber_colour <planet_name> <jedi_rank> - gets most used light saber colour");
        System.out.println("print <planet_name>                                  - prints planet info");
        System.out.println("print <planet_name> <planet_name>                    - prints info for both planets");
        System.out.println("print <jedi_name>                                    - prints jedi info");
        System.out.println("open <file>                                          - opens a file");
        System.out.println("close                                                - closes current file");
        System.out.println("save                                                 - saves current file");
        System.out.println("saveas <file>                                        - saves to selected file");
        System.out.println("help                                                 - opens help menu");
        System.out.println("exit                                                 - exits program");
    }
}
