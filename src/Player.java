import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Player extends GameObject{
	
	private Handler handler;
	private boolean Tenable = true;

	public Player(int x, int y, ID id, int w, int h, Handler handler){
		super(x, y, id, w, h);
		
		System.out.println("1"+x +" "+ y +" "+ w +" "+ h);
		
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.id = id;
	}
	
	public void tick() {
		y += velY;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.04f, handler));
		
		y = GameMain.clamp(GameMain.HEIGHT, 0, y);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, w, h, 0.095f,handler));
		
		collision();
	}
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, w, h);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
	
	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.ball){
				if(getBounds().intersects(tempObject.getBounds())){
					tempObject.chaVelX();
					GameMain.wasHit = true;
				}
			}
		}
	}
}
