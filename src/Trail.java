import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.AlphaComposite;

public class Trail extends GameObject{
	private float alpha = 1;
	private float life;
	private Handler handler;
	private Color color;
	
	public Trail(int x, int y, ID id, Color color, int w, int h, float life, Handler handler) {
		super(x, y, id, w, h);
		
		this.x = x;
		this.y = y;
		this.id = id;
		this.color = color;
		this.h = h;
		this.life = life;
		this.w = w;
		this.handler = handler;
	}

	public void tick() {
		if(alpha > life){
			alpha -= (life - 0.00001f);
		}else{
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {	
		Graphics2D g2d = (Graphics2D) g;
			//set to alpha, or it will make other things transparent
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect(x, y, w, h);
			
		g2d.setComposite(makeTransparent(1));
	}
		
		//method renders transparent object trails
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {
		return null;
	}

}
