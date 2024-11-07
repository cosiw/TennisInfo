package TENNIS.TENNISINFO.Player.Domain.Enum;

public enum ForeHandType {
    RIGHT_HANDED,
    LEFT_HANDED;

    public static ForeHandType fromString(String value){
        return ForeHandType.valueOf(value.replace("-","_").toUpperCase());
    }
}
