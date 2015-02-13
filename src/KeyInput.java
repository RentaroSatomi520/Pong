import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{
	private Handler handler;
	public boolean startReady = false;
	boolean[] keyDown = new boolean[2];
	private StartWindow startwindow;
	
	public KeyInput(Handler handler){
		this.handler = handler;
		
		for(int i = 0; i < keyDown.length; i++){
			keyDown[i] = false;
		}
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player){
			   if(key == KeyEvent.VK_UP){tempObject.setVelY(-5); keyDown[0] = true;}
			   if(key == KeyEvent.VK_DOWN){tempObject.setVelY(5); keyDown[1] = true;}
			   if(key == KeyEvent.VK_2 || key == KeyEvent.VK_NUMPAD1){}
			   if(key == KeyEvent.VK_1 || key == KeyEvent.VK_NUMPAD2){}
			}
			if(tempObject.getID() == ID.Player2){
			   if(key == KeyEvent.VK_W){tempObject.setVelY(-5); keyDown[0] = true;}
			   if(key == KeyEvent.VK_S){tempObject.setVelY(5); keyDown[1] = true;}
			}
			
			if(key == KeyEvent.VK_ESCAPE){System.exit(0);}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			//System.out.println("keyDown 2 "+keyDown[0] +" keyDown1 "+ keyDown[1]+ " velY " + tempObject.getVelY());
			
			if(tempObject.getID() == ID.Player){
			   if(key == KeyEvent.VK_UP){keyDown[0] = false;}
			   if(key == KeyEvent.VK_DOWN){keyDown[1] = false;}
			   
				if(keyDown[0] && keyDown[1]){
					tempObject.setVelY(0);
				}
				if(!keyDown[0] && !keyDown[1]){
					tempObject.setVelY(0);
				}
			}
			
			if(tempObject.getID() == ID.Player2){
				   if(key == KeyEvent.VK_W){keyDown[0] = false;}
				   if(key == KeyEvent.VK_S){keyDown[1] = false;}
				   
					if(keyDown[0] && keyDown[1]){
						tempObject.setVelY(0);
					}
					if(!keyDown[0] && !keyDown[1]){
						tempObject.setVelY(0);
					}
			}
			
			if(key == KeyEvent.VK_ESCAPE){System.exit(0);}
		}
	}
	
	public boolean getReady(){
		return startReady;
	}
}
