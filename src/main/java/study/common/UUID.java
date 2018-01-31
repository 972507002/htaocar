package study.common;

public class UUID {
    public static String getUUID(){
        java.util.UUID uuid = java.util.UUID.randomUUID();
        return uuid.toString();
    }
}
