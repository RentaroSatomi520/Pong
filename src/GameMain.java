import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


public class GameMain extends Canvas implements Runnable{
	private static final long serialVersionUID = 4096615933246805558L;

	static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	 
	boolean isRunning = false;
	public static boolean wasHit = false;
	
	public static final int WIDTH = screensize.width;
	public static final int HEIGHT = screensize.height;
	
	private HUD hud;
	private Handler handler;
	private Thread thread;
	private StartWindow startwindow;
	private String isAct = "y";
	
	//render and tick are private
	public GameMain(){
		handler = new Handler();
		
		this.addKeyListener(new KeyInput(handler));
		
		hud = new HUD();
		
		new Window(WIDTH, HEIGHT, "Pong Recreated Alpha v0.2, Remade by: Kenny Stepney", this);
		
		startwindow = new StartWindow();
	}
	
	public void run(){
		this.requestFocus();
		long lasTime = System.nanoTime();
		double amountOfTicks = 60.0;
		//9 zeros
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lasTime) / ns;
			lasTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			while(isAct != null){
				isWind();
			}
			if(isRunning)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println(""++"");
				frames = 0;
			}
		}
		stop();
	}
	
	private void isWind(){
		if(startwindow.getActive()){
			startwindow.tick();
		}
		if(!startwindow.getActive()){
			isAct = "l";
		}
		if(isAct == "l"){
			handler.addObject(new Player(50, HEIGHT/2 - 40, ID.Player, 15, 80, handler));
			handler.addObject(new Enemy(WIDTH-50, HEIGHT/2 - 40, ID.Enemy, 15, 80, handler));
			handler.addObject(new Ball(WIDTH/2, HEIGHT/2, ID.ball, 20, 20, handler));
			
			isAct = null;
		}
	}
	
	private void tick(){
		handler.tick();
		hud.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		while(startwindow.getActive()){
			startwindow.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public synchronized void start(){
		if(isRunning){
			return;
		}
		
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	public void stop(){
		try{
			thread.join();
			isRunning = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int clamp(int max, int min, int var){
		if(var <= min){
			return var = min;
		}else if(var >= max){
			return var = max;
		}else{
			return var;
		}
	}
	
	public static void main(String args[]){
		new GameMain();

	}
}
