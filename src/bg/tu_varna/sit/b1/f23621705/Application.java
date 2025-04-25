package bg.tu_varna.sit.b1.f23621705;

import bg.tu_varna.sit.b1.f23621705.cli.commands.jedi.AddJedi;
import bg.tu_varna.sit.b1.f23621705.cli.commands.jedi.RemoveJedi;
import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.modules.*;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        List<JediRank> ranks= List.of(JediRank.values());


        JediManager manager=new JediManager();

        AddJedi addJedi = new AddJedi(manager);
        addJedi.execute();

        RemoveJedi removeJedi=new RemoveJedi(manager);
        removeJedi.execute();
    }
}
