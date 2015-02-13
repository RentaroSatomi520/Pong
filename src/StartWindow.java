import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class StartWindow {
	private boolean isActive = true;
	private String startQuote = "Are you ready?";
	private int colorValue = 255;
	private int timer = 0;
	private int colorAct = 0;

	public void tick(){
		if(isActive){
			if(colorValue == 255){
				colorAct = 1;
			}
			if(colorValue == 0){
				colorAct = 2;
			}
			
			if(colorAct == 1){
				colorValue--;
			}
			if(colorAct == 2){
				colorValue++;
			}
		}
		
		timer++;
		
		if(timer == 200){
			isActive = false;
		}
	}
	
	
	public void render(Graphics g){
		if(isActive){
			g.setFont(new Font(null, Font.PLAIN, 50));
			g.setColor(new Color(75, colorValue, 0));
			g.drawString(startQuote, GameMain.WIDTH/2 - 150, GameMain.HEIGHT/2 - 50);
			g.drawString(""+ timer, GameMain.WIDTH/2 - 150, GameMain.HEIGHT/2 - 30);
		}
	}
	
	public void setActive(boolean active){
		this.isActive = active;
	}
	
	public boolean getActive(){
		return isActive;
	}
}
