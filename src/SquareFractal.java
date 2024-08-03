import com.raylib.java.Config;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.input.Keyboard;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.utils.Tracelog;
import utils.RaylibContext;

import java.util.ArrayList;

public class SquareFractal {
	public static final int WINDOW_HEIGHT = 1020;
	public static final int WINDOW_WIDTH  = 1920;
	public static final int TARGET_FPS    = 60;
	
	public static void main(String[] args) {
		Raylib rl = new Raylib();
		rl.core.SetConfigFlags(Config.ConfigFlag.FLAG_WINDOW_RESIZABLE);
		Tracelog.SetTraceLogLevel(Tracelog.TracelogType.LOG_ALL);
		rl.core.InitWindow(WINDOW_WIDTH , WINDOW_HEIGHT , "Mouse Control");
		rl.core.SetTargetFPS(TARGET_FPS);
		RaylibContext rl_ctx = new RaylibContext(WINDOW_WIDTH , WINDOW_HEIGHT , TARGET_FPS , rl);
		
		ArrayList<Square> squares = new ArrayList<>();
		ArrayList<Square> smallest_squares = new ArrayList<>();
		boolean is_done = false;
		
		while (! rl.core.WindowShouldClose()) {
			rl.core.BeginDrawing();
			////////////////////////
			
			rl.core.ClearBackground(Color.RAYWHITE);
			int sideLength = WINDOW_HEIGHT / 2;
			int startingX = WINDOW_WIDTH / 2 - sideLength / 2;
			int startingY = sideLength / 2;
			rl.shapes.DrawRectangle(startingX , startingY , sideLength , sideLength , Color.BLACK);
			
			for (Square square : squares) {
				square.draw();
			}
			if (! is_done) {
				if (squares.size() == 0) {
					int subdivededLength = sideLength / 3;
					int newSideLength = subdivededLength / 3;
					for (int i = subdivededLength; i <= subdivededLength * 3; i += subdivededLength) {
						for (int j = subdivededLength; j <= subdivededLength * 3; j += subdivededLength) {
							
							if (! (i / subdivededLength == 2 && j / subdivededLength == 2)) {
								Square s = new Square(newSideLength , Color.RAYWHITE ,
								                      new Vector2(startingX + (i - subdivededLength) + newSideLength ,
								                                  startingY + (j - subdivededLength) + newSideLength) , rl);
								squares.add(s);
								smallest_squares.add(s);
							}
						}
					}
				}
				else {
					ArrayList<Square> temp_smallest_squares = new ArrayList<>();
					for (Square parentSquare : smallest_squares) {
						
						int subdivededLength = parentSquare.sideLength;
						int newSideLength = subdivededLength / 3;
						startingX = ((int) parentSquare.pos.x) - parentSquare.sideLength;
						startingY = ((int) parentSquare.pos.y) - parentSquare.sideLength;
						if (subdivededLength == 0) {
							is_done = true;
							break;
						}
						for (int i = subdivededLength; i <= subdivededLength * 3; i += subdivededLength) {
							for (int j = subdivededLength; j <= subdivededLength * 3; j += subdivededLength) {
								
								if (! (i / subdivededLength == 2 && j / subdivededLength == 2)) {
									Square s = new Square(newSideLength , Color.RAYWHITE ,
									                      new Vector2(startingX + (i - subdivededLength) + newSideLength ,
									                                  startingY + (j - subdivededLength) + newSideLength) , rl);
									squares.add(s);
									temp_smallest_squares.add(s);
								}
							}
						}
					}
					smallest_squares.clear();
					smallest_squares.addAll(temp_smallest_squares);
					
				}
			}
			
			////////////////////////
			rl.core.EndDrawing();
		}
		rl.core.CloseWindow();
	}
	
	static class Square {
		public int     sideLength;
		public Color   color;
		public Vector2 pos;
		public Raylib  rl;
		
		public Square(int sideLength , Color color , Vector2 pos , Raylib rl) {
			this.sideLength = sideLength;
			this.color      = color;
			this.pos        = pos;
			this.rl         = rl;
		}
		
		public void draw() {
			this.rl.shapes.DrawRectangleV(this.pos , new Vector2(sideLength , sideLength) , this.color);
		}
	}
	
}