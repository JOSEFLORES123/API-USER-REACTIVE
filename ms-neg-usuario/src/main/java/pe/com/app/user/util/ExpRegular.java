package pe.com.app.user.util;

public final class ExpRegular {

    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String EMAIL_PATTERN2 = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)(\\.[A-Za-z]{2,})$";
    public static final String ONLY_NUMBERS = "^\\d+$";
    public static final String NOT_SPECIAL_CHARACTERS = "^[a-zA-Z0-9.-]+$";
    public static final String NOT_ALPHABETIC_AND_DASH = "^[a-zA-Z0-9áéíóúÁÉÍÓÚÑñ -]+$";

    private ExpRegular() {
    }
}
