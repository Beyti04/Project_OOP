package bg.tu_varna.sit.b1.f23621705;

import bg.tu_varna.sit.b1.f23621705.cli.CLI;
import bg.tu_varna.sit.b1.f23621705.cli.commands.jedi.CreateJedi;
import bg.tu_varna.sit.b1.f23621705.cli.commands.jedi.GetMostUsedLightsaberColour;
import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;
import bg.tu_varna.sit.b1.f23621705.modules.Jedi;
import bg.tu_varna.sit.b1.f23621705.modules.JediManager;
import bg.tu_varna.sit.b1.f23621705.modules.Planet;
import bg.tu_varna.sit.b1.f23621705.modules.PlanetsList;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        CLI cli=new CLI();
        cli.run();
    }
}
