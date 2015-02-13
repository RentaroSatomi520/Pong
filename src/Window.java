import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Window extends Canvas{

	private static final long serialVersionUID = -8255319694373975038L;
	
	public Window(int width, int height, String title, GameMain gamemain){
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(gamemain);
		frame.setVisible(true);
		
		gamemain.start();
	}
}
