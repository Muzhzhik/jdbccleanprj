package project.utils;

public enum Action {
    EXIT, GET_ALL, GET, CREATE, UPDATE, DELETE;

    public static Action getAction(int actionIndex) {
        Action action = null;
        if (actionIndex >= EXIT.ordinal() && actionIndex <= DELETE.ordinal()) {
            action = values()[actionIndex];
        }
        return action;
    }
}
