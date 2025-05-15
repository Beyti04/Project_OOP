package bg.tu_varna.sit.b1.f23621705.enums;

public enum LightsaberColour {
    PURPLE, RED, YELLOW, BLUE, GREEN, WHITE, BLACK, ORANGE;

    public static boolean exists(String input){
        try{
            LightsaberColour.valueOf(input.toUpperCase());
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}
