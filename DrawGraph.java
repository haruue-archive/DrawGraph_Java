//import awt
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawGraph
{
	//define main window
	private Frame mainWindow = new Frame("Draw Graph");

	public void init()
	{


		//define item in fnChooser
		Choice fnChooser = new Choice();
		fnChooser.add("ax+b");
		fnChooser.add("ax^2+bx+c");
		fnChooser.add("ax^3+bx^2+cx+d");
		fnChooser.add("ax^4+bx^3+cx^2+dx+e");
		fnChooser.add("a^x+b");
		fnChooser.add("log(a,x)+b");
		fnChooser.add("(x-l)^2/a^2+(y-h)^2/b^2=1");
		fnChooser.add("triangle(a,b,c,d,e,f)");

		//define button "draw"
		Button drawButton = new Button("Draw");

		//define parameter input area
		Box lbBox = Box.createVerticalBox();
		lbBox.add(new Label("a=",Label.RIGHT));
		lbBox.add(new Label("b=",Label.RIGHT));
		lbBox.add(new Label("c=",Label.RIGHT));
		lbBox.add(new Label("d=",Label.RIGHT));
		lbBox.add(new Label("e=",Label.RIGHT));
		lbBox.add(new Label("l=",Label.RIGHT));
		lbBox.add(new Label("h=",Label.RIGHT));
		Box inputBox = Box.createVerticalBox();
		inputBox.add(new TextField(5));
		inputBox.add(new TextField(5));
		inputBox.add(new TextField(5));
		inputBox.add(new TextField(5));
		inputBox.add(new TextField(5));
		inputBox.add(new TextField(5));
		inputBox.add(new TextField(5));
		Box prmtBox = Box.createHorizontalBox();
		prmtBox.add(lbBox);
		prmtBox.add(inputBox);
		prmtBox.add(Box.createHorizontalGlue());

		//design fnPanel
		Panel fnPanel = new Panel();
		fnPanel.add(fnChooser);
		fnPanel.add(drawButton);

		//design main window
		Box mainBox = Box.createVerticalBox();
		mainBox.add(fnPanel);
		mainBox.add(prmtBox);
		mainWindow.add(mainBox);
		mainWindow.pack();
		mainWindow.setVisible(true);
	}

	public static void main(String[] args)
	{
		new DrawGraph().init();
	}
}