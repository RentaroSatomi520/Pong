import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class HUD {
	private Ball ball;
	
	int hudSize = GameMain.WIDTH/5;
	
	private static int pScore = 0;
	private static int eScore = 0;
	
	public void render(Graphics g){
		g.setColor(Color.white);
		for(int i = 0; i < GameMain.HEIGHT; i += 20){
			g.fillRect(GameMain.WIDTH/2, i, 10, 10);
		}
		g.setFont(new Font(null, Font.PLAIN, 50));
		g.drawString(""+pScore, hudSize, 50);
		g.drawString(""+eScore, hudSize * 4, 50);
	}
	
	public void tick(){

	}
	
	public static void setPScore(int score){
		pScore = score;
	}
	
	public static void setEScore(int score){
		eScore = score;
	}
	
	public static int getPScore(){
		return pScore;
	}
	
	public static int getEScore(){
		return eScore;
	}
}
