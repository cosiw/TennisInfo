package TENNIS.TENNISINFO.Player.Domain.Enum;

public enum BackHandType {
    ONE_HANDED,
    TWO_HANDED,
    UNKNOWN;


    public static BackHandType fromString(String value){
        if(value.equals("Unknown")){
            return BackHandType.UNKNOWN;
        }
        return BackHandType.valueOf(value.replace("-","_").toUpperCase());
    }
}
