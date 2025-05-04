package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Jedi> readFile(String filePath) throws Exception {
        File file = new File(filePath);

        List<Jedi> jedis = new ArrayList<>();

        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String fileLine;

                while ((fileLine = reader.readLine()) != null) {
                    String[] parts = fileLine.split(("\\|"));
                    if (parts.length != 6) {
                        throw new IllegalArgumentException("Invalid file format!");
                    } else {
                        if (PlanetsList.getPlanetsInstance().getPlanet(parts[5]) == null) {
                            PlanetsList.getPlanetsInstance().createPlanet(new Planet(parts[5]));
                        }
                        String planet = parts[0];
                        String jediName = parts[1];
                        JediRank rank = JediRank.valueOf(parts[2]);
                        int age = Integer.parseInt(parts[3]);
                        LightsaberColour colour = LightsaberColour.valueOf(parts[4]);
                        double strength = Double.parseDouble(parts[5]);

                        jedis.add(new Jedi(planet, jediName, rank, age, colour, strength));
                    }
                }
            }
        }
        return jedis;
    }

    public static void saveToFile(String filePath, List<Jedi> jedis) throws Exception {
        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Jedi jedi : jedis) {
                    String line = jedi.getName() +
                            "|" + jedi.getJediRank() +
                            "|" + jedi.getAge() +
                            "|" + jedi.getLightsaberColour() +
                            "|" + jedi.getStrength() +
                            "|" + jedi.getPlanet();

                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}
