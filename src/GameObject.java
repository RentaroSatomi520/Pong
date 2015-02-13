import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class GameObject {
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	protected ID id;
	protected int velX;
	protected int velY;
	
	public GameObject(int x, int y, ID id2, int w, int h){
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setID(ID id){
		this.id = id;
	}
	
	public void setVelX(int velX){
		this.velX = velX;
	}
	
	public void setVelY(int velY){
		this.velY = velY;
	}
	
	public void setW(int w){
		this.w = w;
	}
	
	public void setH(int h){
		this.h = h;
	}
	
	//
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public ID getID(){
		return id;
	}
	
	public int getVelX(){
		return velX;
	}
	
	public int getVelY(){
		return velY;
	}
	
	public int getW(){
		return w;
	}
	
	public int getH(){
		return h;
	}
	
	public void chaVelX(){
		this.velX *= -1;
	}
}
