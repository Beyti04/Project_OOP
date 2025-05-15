package bg.tu_varna.sit.b1.f23621705.modules;

import bg.tu_varna.sit.b1.f23621705.enums.JediRank;
import bg.tu_varna.sit.b1.f23621705.enums.LightsaberColour;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    /**
     * Чете данни от файл
     *
     * @param filePath параметър за път към файл
     * @return връща данните от прочетения файл под формата на на списък
     * @throws Exception
     */
    public static List<Jedi> readFile(String filePath) throws Exception {

        File file = new File(filePath);
        final Universe universe = Universe.getUniverseInstance();

        List<Jedi> jedis = new ArrayList<>();

        if (!file.exists()) {

            throw new FileNotFoundException("File not found: " + filePath);

        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String fileLine = null;

            while ((fileLine = reader.readLine()) != null) {

                String[] parts = fileLine.trim().split("\\|");

                if (parts.length == 6) {

                    if (universe.getPlanet(parts[0]) == null) {

                        universe.createPlanet(new Planet(parts[0]));

                    }

                    Planet planet = universe.getPlanet(parts[0]);
                    String name = parts[1];
                    JediRank rank = JediRank.getRank(parts[2]);
                    int age = Integer.parseInt(parts[3]);
                    LightsaberColour colour = LightsaberColour.valueOf(parts[4]);
                    double strength = Double.parseDouble(parts[5]);
                    Jedi jedi = new Jedi(planet, name, rank, age, colour, strength);
                    jedis.add(jedi);

                }

            }

        }

        return jedis;

    }

    /**
     * Записва данни във файл
     *
     * @param filePath параметър за път към файл
     * @param jedis    параметър за списък от данни, които ще се запишат във файла
     * @throws Exception
     */
    public static void saveToFile(String filePath, List<Jedi> jedis) throws Exception {

        File file = new File(filePath);
        final Universe universe = Universe.getUniverseInstance();

        if (!file.exists()) {

            file.createNewFile();

        } else {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

                for(Planet planet: universe.getPlanets()){

                    String line=planet.getName();
                    writer.write(line);
                    writer.newLine();

                }

                for (Jedi jedi : jedis) {

                    String line = jedi.getPlanet().getName() +
                            "|" + jedi.getName() +
                            "|" + jedi.getJediRank() +
                            "|" + jedi.getAge() +
                            "|" + jedi.getLightsaberColour() +
                            "|" + jedi.getStrength();

                    writer.write(line);
                    writer.newLine();

                }

            }

        }

    }

}
