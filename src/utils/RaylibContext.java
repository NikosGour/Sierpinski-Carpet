package utils;

import com.raylib.java.Raylib;

public class RaylibContext {
	// Todo: acually make this a singleton
	public         int           WINDOW_WIDTH;
	public         int           WINDOW_HEIGHT;
	public         int           TARGET_FPS;
	public         Raylib        rl;
	private static RaylibContext _instance;
	
	public RaylibContext(int WINDOW_WIDTH , int WINDOW_HEIGHT , int TARGET_FPS , Raylib rl) {
		if (_instance != null) {
			throw new IllegalStateException("You have already created a singleton instance. Please use getSingleton instead");
		}
		this.WINDOW_WIDTH       = WINDOW_WIDTH;
		this.WINDOW_HEIGHT      = WINDOW_HEIGHT;
		this.TARGET_FPS         = TARGET_FPS;
		this.rl                 = rl;
		RaylibContext._instance = this;
	}
	
	public static RaylibContext getSingleton() {
		return RaylibContext._instance;
	}
	
	
}