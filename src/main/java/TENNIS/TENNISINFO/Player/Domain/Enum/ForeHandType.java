package TENNIS.TENNISINFO.Player.Domain.Enum;

public enum ForeHandType {
    RIGHT_HANDED,
    LEFT_HANDED,
    UNKNOWN;

    public static ForeHandType fromString(String value){
        if(value.equals("Unknown")){
            return ForeHandType.UNKNOWN;
        }
        return ForeHandType.valueOf(value.replace("-","_").toUpperCase());
    }
}
