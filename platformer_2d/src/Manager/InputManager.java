package Manager;

public final class InputManager{

    // KeyMap Values
    public static int W = 87;
    public static int A = 65;
    public static int S = 83;
    public static int D = 68;

    public static int ESCAPE = 27;
    public static int SPACE = 32;
    public static int ENTER = 10;
    public static int ARROW_UP = 38;
    public static int ARROW_DOWN = 40;
    public static int ARROW_LEFT = 37;
    public static int ARROW_RIGHT = 39;

    //
    private static int MAX_KEY_VALUE = 150;
    private static boolean[] Keys = new boolean[MAX_KEY_VALUE];
    private static boolean[] Keys_Previous = new boolean[MAX_KEY_VALUE];

    private InputManager() {}

    public static void Input_Pressed(int k) {
    	if(k >= MAX_KEY_VALUE)return;
        Keys[k] = true;
    }

    public static void Input_Released(int k) {
    	if(k >= MAX_KEY_VALUE)return;
        Keys[k] = false;
        Keys_Previous[k] = false;
    }

    public static boolean getKeyState(int k) {
    	if(k >= MAX_KEY_VALUE)return false;
        return Keys[k];
    }

    public static boolean getTypedKeyState(int k) {
    	if(k >= MAX_KEY_VALUE)return false;
        boolean ans = Keys[k] && !Keys_Previous[k];
        Keys_Previous[k] = Keys[k];
        return ans;
    }
}
