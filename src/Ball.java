import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Ball extends GameObject{
	private Handler handler;
	private HUD hud;
	private int escore = 0;
	private int pscore = 0;
	private int velXLim = 5;
	//private int velYLim = 5;
	public static int BallY;
	public static int BallX;
	Random r = new Random();
	int n = r.nextInt(4);
	boolean randomOver = false;
	
	public Ball(int x, int y, ID id, int w, int h, Handler handler) {
		super(x, y, id, w, h);
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.id = id;
		this.handler = handler;
	}

	public void tick() { 
		x += velX;
		y += velY;
		
		BallY = y;
		BallX = x;
		
		if(GameMain.wasHit){
			velXLim += 1;
			//velYLim += 1;
			System.out.println(velXLim+ " " +GameMain.wasHit);
			if(velX < 0){
				velX = -velXLim;
				//velY = velYLim;
			}
			if(velX > 0){
				//velY = velYLim;
				velX = velXLim;
			}
			GameMain.wasHit = false;
		}
		
		//System.out.println(velXLim);
		
		if(!randomOver){
			if(n == 0){
				velX = -5;
				velY = 5;
				randomOver = true;
			}else if(n == 1){
				velX = 5;
				velY = -5;
				randomOver = true;
			}else if(n == 2){
				velX = 5;
				velY = 5;
				randomOver = true;
			}else{
				velX = -5;
				velY = -5;
				randomOver = true;
			}
		}
		if(velY == 0){
			velX = 0;
		}
		if(y <= 0 || y >= GameMain.HEIGHT - 120){velY *= -1;System.out.println("hi");}
		if(x <= 0){
			escore++; 
			HUD.setEScore(escore); 
			this.x = GameMain.WIDTH/2;
			randomOver = false;
			velXLim = 0;
		}
		if(x >= GameMain.WIDTH - w){
			System.out.println("NO");
			pscore++; 
			HUD.setPScore(pscore); 
			this.x = GameMain.WIDTH/2;
			randomOver = false;
			velXLim = 0;
		}
		
		handler.addObject(new Trail(x, y + 3, ID.Trail, Color.white, w - 5, h - 5, 0.095f,handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, w, h);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
}
