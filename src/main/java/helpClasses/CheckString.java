package helpClasses;

public class CheckString {

    public static boolean isBlank(String s) {
        return (s == null) || (s.trim().length() == 0);
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }
}
