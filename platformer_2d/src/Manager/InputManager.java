package Manager;

public final class InputManager{
	
	/*
	 * 
	 * !! Attention !!
	 * This class cannot be extended by any other class
	 * It is a working stand alone class. Other classes can utilize the static attributes or methods of this class.
	 * The constructor should never be called, therefore it is private.
	 * 
	 * To use this Input Manager correctly, call the components:
	 *     InputManager.Input_Pressed(int k); or to use the KeyMap-Values
	 *     InputManager.getKeyState(InputManager.ARROW_UP);
	 * 
	 * For easier use, import the Manager as a static package:
	 *     import static Manager.InputManager.*;
	 *  
	 * This will get rid of the need to specify the InputManager
	 *     Input_Pressed(int k);
	 *     getKeyState(ARROW_UP);
	 * 
	 * If only certain parts should be imported:
	 *     import static Manager.InputManager.ARROW_UP
	 *     InputManager.getKeyState(ARROW_UP);
	 * 
	 */

    // KeyMap Values (accessible from anywhere else if imported correctly )
    public static final int W = 87;
    public static final int A = 65;
    public static final int S = 83;
    public static final int D = 68;

    public static final int ESCAPE = 27;
    public static final int SPACE = 32;
    public static final int ENTER = 10;
    public static final int ARROW_UP = 38;
    public static final int ARROW_DOWN = 40;
    public static final int ARROW_LEFT = 37;
    public static final int ARROW_RIGHT = 39;

    //Maximum Key Value which is tracked and accepted by Manager
    private static final int MAX_KEY_VALUE = 150;
    
    // Array of KeyStates (true = pressed / false = released)
    private static boolean[] Keys = new boolean[MAX_KEY_VALUE];
    private static boolean[] Keys_Previous = new boolean[MAX_KEY_VALUE];

    private InputManager() {}

    public static void Input_Pressed(int k) {
    	// key is pressed --> State is updated to true
    	if(k >= MAX_KEY_VALUE)return;
        Keys[k] = true;
    }

    public static void Input_Released(int k) {
    	// key is released --> State / PreviousState is updated to false
    	if(k >= MAX_KEY_VALUE)return;
        Keys[k] = false;
        Keys_Previous[k] = false;
    }

    public static boolean getKeyState(int k) {
    	// get current State of the Key
    	if(k >= MAX_KEY_VALUE)return false;
        return Keys[k];
    }

    public static boolean getTypedKeyState(int k) {
    	// returns true if the Key is used for the first time
    	// resets after key is released in Input_Released(int k) method
    	if(k >= MAX_KEY_VALUE)return false;
        boolean ans = Keys[k] && !Keys_Previous[k];
        Keys_Previous[k] = Keys[k];
        return ans;
    }
}
