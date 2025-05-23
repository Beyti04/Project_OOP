package bg.tu_varna.sit.b1.f23621705.enums;

public enum Commands {
    ADD_PLANET (2),
    CREATE_JEDI(8,9),
    REMOVE_JEDI(4),
    PROMOTE_JEDI(4),
    DEMOTE_JEDI(4),
    GET_STRONGEST_JEDI(2),
    GET_YOUNGEST_JEDI(3,4),
    GET_MOST_USED_SABER_COLOUR(2,4),
    PRINT(2,3),
    HELP(1),
    OPEN(2),
    CLOSE(1),
    SAVE(1),
    SAVE_AS(2),
    EXIT(1);

    private final int i;
    private final int j;

    Commands(int i) {
        this.i=i;
        this.j=i;
    }

    Commands(int i,int j){
        this.i=i;
        this.j=j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    /**
     * Проверява дали съществува команда по дадедан входна данна
     * @param input входна данна за проверка
     * @return връща True ако съществува такава команда или False ако не съществува
     */
    public static boolean exists(String input){

        try{

            Commands.valueOf(input.toUpperCase());
            return true;

        }catch (IllegalArgumentException e){

            return false;

        }

    }

}
