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

    public JediRank next() {
        if (this.ordinal() < ranks.length - 1) {
            return ranks[(this.ordinal() + 1)];
        } else {
            return null;
        }
    }

    public JediRank prev(){
        if(this.ordinal()>0){
            return ranks[(this.ordinal()-1)];
        }else{
            return null;
        }
    }

    public static boolean exists(String input){
        try{
            JediRank.valueOf(input.toUpperCase());
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    public static JediRank getRank(String input){
        try{
            return JediRank.valueOf(input.toUpperCase().replace(" ","_"));
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
