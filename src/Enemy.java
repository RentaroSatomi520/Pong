import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Enemy extends GameObject{
	private Handler handler;
	private int velYLim = 5;
	
	private int scoreUp = 1;
	
	public Enemy(int x, int y, ID id, int w, int h, Handler handler) {
		super(x, y, id, w, h);
		
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
	}
	
	public void tick(){
		y += velY;
		
		y = GameMain.clamp(GameMain.HEIGHT, 0, y);
		
		if(HUD.getPScore() < 5 || HUD.getEScore() > 5){
			if(Ball.BallX > GameMain.WIDTH/2){
				if(y > Ball.BallY  && y > 0){
					velY = -velYLim;
				}else if(y < Ball.BallY && y < GameMain.HEIGHT){
					velY = velYLim;
				}else{
					velY = 0;
				}
			}else{
				velY = 0;
			}
		}else{
			if(y > Ball.BallY  && y > 0){
				velY = -velYLim;
			}else if(y < Ball.BallY && y < GameMain.HEIGHT){
				velY = velYLim;
			}else{
				velY = 0;
			}
		}
		
		if(HUD.getPScore() == scoreUp || HUD.getEScore() == scoreUp){
			velYLim = 5;
			scoreUp++;
		}
		
		collision();
		
		//System.out.println(" "+y+" "+velY+" "+Ball.BallY);
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, w, h, 0.095f,handler));
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x, y, w, h);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, w, h);
	}
	
	public void collision(){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getID() == ID.ball){
					if(getBounds().intersects(tempObject.getBounds())){
						tempObject.chaVelX();
						velYLim -= 1;
						GameMain.wasHit = true;
					}
				}
			}
	}
}
