package lifegames;
/*
 * by yw
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class LifeGame extends JFrame{
	private final WorldMap world;
	
	public LifeGame(int rows,int columns)
	{
		world = new WorldMap(rows,columns);
		new Thread(world).start();
		add(world);
	}
	
	public static void main(String []args)
	{
		LifeGame frame = new LifeGame(40,50);
		
		JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);
        
        JMenu options = new JMenu("Options");
        menu.add(options);
        
        JMenuItem arrow =options.add("Arrow");
        arrow.addActionListener(frame.new ArrowActionListener());
        
        JMenuItem square = options.add("Square");
        square.addActionListener(frame.new SquareActionListener());       
        
        JMenu custom = new JMenu("Custom");
        menu.add(custom);
        custom.addMenuListener(frame.new CustomActionListener());
        
        JMenu random = new JMenu("Random");
        menu.add(random);
        random.addMenuListener(frame.new RandomActionListener());
        
        JMenu clean = new JMenu("Clean");
        menu.add(clean);
        clean.addMenuListener(frame.new CleanActionListener());
        
        JMenu help = new JMenu("Help");        
        menu.add(help);
        
        frame.addMouseListener(frame.new MouseHandler());
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1005, 853);
        frame.setTitle("Game of Life");
        frame.setVisible(true);
        frame.setResizable(false);
	}

	class ArrowActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO 自动生成的方法存根
			world.setArrow();
		}
		
	}
	
	class SquareActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO 自动生成的方法存根
			world.setSquare();
		}
		
	}
	
	class CustomActionListener implements MenuListener
	{
		@Override
		public void menuSelected(MenuEvent e) {
			// TODO 自动生成的方法存根
			if(world.isSelecting==false)
			{
				world.isSelecting=true;
				world.selectGeneration=new int[40][50];
			}
			else
			{
				world.isSelecting=false;
				world.selectGeneration=null;
			}
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO 自动生成的方法存根
			
		}
		
	}
	
	class RandomActionListener implements MenuListener
	{

		@Override
		public void menuSelected(MenuEvent e) {
			// TODO 自动生成的方法存根
			world.setRandom();
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO 自动生成的方法存根
			
		}
		
	}
	
	class CleanActionListener implements MenuListener
	{

		@Override
		public void menuSelected(MenuEvent e) {
			// TODO 自动生成的方法存根
			world.clean();
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO 自动生成的方法存根
			
		}

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO 自动生成的方法存根
			
		}
		
	}
	
	class MouseHandler extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(world.isSelecting==true)
			{
				int x=(int)(e.getX()/20);
				int y=(int)((e.getY()-50)/20);
				if(x>=0 && x<=50 && y>=0 && y<=40)
				{
					world.selectGeneration[y][x]=1;
					world.setCustom();
				}
			}
		}
	}
}
