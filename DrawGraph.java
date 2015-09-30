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
		//define Canvas
		DrawCanvas drawArea = new DrawCanvas();
		drawArea.repaint();
		drawArea.setPreferredSize(new Dimension(300,300));


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

		//define buttons
		Box buttonBox = Box.createVerticalBox();
		Button drawButton = new Button("Draw");
		Button clearButton = new Button("Clear");
		buttonBox.add(Box.createVerticalGlue());
		buttonBox.add(drawButton);
		buttonBox.add(Box.createVerticalGlue());
		buttonBox.add(clearButton);
		buttonBox.add(Box.createVerticalGlue());

		//define parameter input area
		Box lbBox = Box.createVerticalBox();
		lbBox.add(new Label("a=",Label.RIGHT));
		lbBox.add(new Label("b=",Label.RIGHT));
		lbBox.add(new Label("c=",Label.RIGHT));
		lbBox.add(new Label("d=",Label.RIGHT));
		lbBox.add(new Label("e=",Label.RIGHT));
		lbBox.add(new Label("f=",Label.RIGHT));
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
		inputBox.add(new TextField(5));
		Box prmtBox = Box.createHorizontalBox();
		prmtBox.add(lbBox);
		prmtBox.add(inputBox);
		prmtBox.add(buttonBox);

		//design main window
		Box mainBox = Box.createVerticalBox();
		mainBox.add(drawArea);
		mainBox.add(fnChooser);
		mainBox.add(Box.createVerticalStrut(5));
		mainBox.add(prmtBox);
		mainWindow.add(mainBox);
		mainWindow.pack();
		mainWindow.setVisible(true);

		//window exit button
		mainWindow.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}


	class DrawCanvas extends Canvas
	{
		private boolean[][] pxArray= new boolean[300][300];
		
		public DrawCanvas()
		{
			for(int x=0;x<300;x++)
				for(int y=0;y<300;y++)
					this.pxArray[x][y]=false;
		}


		public void addPoint(boolean[][] addArray)
		{
			for(int x=0;x<300;x++)
				for(int y=0;y<300;y++)
					if(addArray[x][y])
						pxArray[x][y]=true;
		}

		public void deletePoint(boolean[][] deleteArray)
		{
			for(int x=0;x<300;x++)
				for(int y=0;y<300;y++)
					if(deleteArray[x][y])
						pxArray[x][y]=false;
		}

		public void clearCanvas()
		{
			for(int x=0;x<300;x++)
				for(int y=0;y<300;y++)
					this.pxArray[x][y]=false;
		}

		public void paint(Graphics g)
		{
			//add X-axis and Y-axis to the point for draw
			for(int x=0;x<300;x++)
				for(int y=0;y<300;y++)
				{
					if(x==150||y==150) pxArray[x][y]=true;
					else pxArray[x][y]=false;
				}
			//draw function graph
			g.setColor(new Color(0,0,0));
			for(int x=0;x<300;x++)
				for(int y=0;y<300;y++)
					if(pxArray[x][y])
						g.fillOval(x,y,1,1);
		}
	}

	class BasicFn
	{
		//fnExp will be override by sonclass
		public double fnExp(double x){return Double.NaN;}
		//300*300 px array of the function's graph
		public boolean[][] graphFnArray = new boolean[300][300];
		//pxToPoint can convert a px to a point(-10<=x<=10,-10<=y<=10) ,and point_x=point[0] ,point_y=point[1]
		public double[] pxToPoint(int x,int y)
		{
			double[] point = new double[2];
			point[0] = (x/15)-10;
			point[1] = (y/15)+10;
			return point;
		}
		//isPointOnFn check if a point on the function
		public boolean isPointOnFn(double x,double y)
		{
			//sqrt(2*(1/15)^2)=0.094280904158206 ,use 0.095 to check if a point on the graph
			return (Math.abs(fnExp(x)-y)<=0.095);
		}
		//make a 300*300 px array for graph and storage in graphFnArray[300][300]
		public void mkGraphArray()
		{
			for(int x=0;x<300;x++)
				for(int y=0;y<300;y++)
				{
					if(isPointOnFn(pxToPoint(x,y)[0],pxToPoint(x,y)[1]))
						graphFnArray[x][y]=true;
					else graphFnArray[x][y]=false;	
				}
		}
	}

	public static void main(String[] args)
	{
		new DrawGraph().init();
	}
}