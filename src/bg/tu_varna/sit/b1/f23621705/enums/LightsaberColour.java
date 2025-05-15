package bg.tu_varna.sit.b1.f23621705.enums;

public enum LightsaberColour {
    PURPLE, RED, YELLOW, BLUE, GREEN, WHITE, BLACK, ORANGE;

    public static LightsaberColour getColour(String input){

        try{

            return LightsaberColour.valueOf(input.toUpperCase());


        }catch (IllegalArgumentException e){

            return null;

        }

    }

}
