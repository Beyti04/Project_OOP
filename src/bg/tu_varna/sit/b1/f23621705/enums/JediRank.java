package bg.tu_varna.sit.b1.f23621705.enums;

public enum JediRank {
    YOUNGLING,
    INITIATE,
    PADAWAN,
    KNIGHT_ASPIRANT,
    KNIGHT,
    MASTER,
    BATTLE_MASTER,
    GRAND_MASTER;

    private static final JediRank[] ranks = JediRank.values();

    /**
     * Проверява дали съществува следващ ранг
     * @return връща обект от тип JediRank ако съществува
     */
    public JediRank next() {

        if (this.ordinal() < ranks.length - 1) {

            return ranks[(this.ordinal() + 1)];

        } else {

            return null;

        }
    }

    /**
     * Проверява дали съществува предходен ранг
     * @return връща обект от тип JediRank ако съществува
     */
    public JediRank prev(){

        if(this.ordinal()>0){

            return ranks[(this.ordinal()-1)];

        }else{

            return null;

        }
    }

    /**
     * Проверява
     * @param input
     * @return
     */
    public static JediRank getRank(String input){

        try{

            return JediRank.valueOf(input.toUpperCase().replace(" ","_"));

        }catch (IllegalArgumentException e){

            return null;

        }

    }

}
