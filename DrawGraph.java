//import awt
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawGraph
{
	//define main window
	private Frame mainWindow = new Frame("Draw Graph");
	private Box rowBox = Box.createHorizontalBox();
	private Box colBox = Box.createVerticalBox();

	//define controls
	Choice fnChooser = new Choice();
	Button drawButton = new Button("Draw");

	public void init()
	{

		//define item in fnChooser
		fnChooser.add("ax+b");
		fnChooser.add("ax^2+bx+c");
		fnChooser.add("ax^3+bx^2+cx+d");
		fnChooser.add("ax^4+bx^3+cx^2+dx+e");
		fnChooser.add("a^x+b");
		fnChooser.add("log(a,x)+b");
		fnChooser.add("a*(x-l)^2+b*(y-h)^2=1");
		fnChooser.add("triangle(a,b,c)");

		//design fnPanel
		Panel fnPanel = new Panel();
		fnPanel.add(fnChooser);
		fnPanel.add(drawButton);

		//design main window
		mainWindow.add(fnPanel);
		mainWindow.pack();
		mainWindow.setVisible(true);
	}

	public static void main(String[] args)
	{
		new DrawGraph().init();
	}
}