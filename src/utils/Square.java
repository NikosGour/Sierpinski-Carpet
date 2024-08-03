package utils;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

public class Square {
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