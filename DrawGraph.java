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
		TextField inputArg_a = new TextField("0",5); 
		TextField inputArg_b = new TextField("0",5); 
		TextField inputArg_c = new TextField("0",5); 
		TextField inputArg_d = new TextField("0",5); 
		TextField inputArg_e = new TextField("0",5); 
		TextField inputArg_f = new TextField("0",5); 
		TextField inputArg_l = new TextField("0",5); 
		TextField inputArg_h = new TextField("0",5); 
		inputBox.add(inputArg_a);
		inputBox.add(inputArg_b);
		inputBox.add(inputArg_c);
		inputBox.add(inputArg_d);
		inputBox.add(inputArg_e);
		inputBox.add(inputArg_f);
		inputBox.add(inputArg_l);
		inputBox.add(inputArg_h);
		Box prmtBox = Box.createHorizontalBox();
		prmtBox.add(lbBox);
		prmtBox.add(inputBox);
		prmtBox.add(buttonBox);

		//define buttons listeners
		drawButton.addActionListener(e ->
		{
			switch (fnChooser.getItem(fnChooser.getSelectedIndex()))
			{
				case "ax+b":
					drawArea.addPoint(new LinearFn(Double.parseDouble(inputArg_a.getText().trim()),Double.parseDouble(inputArg_b.getText().trim())).graphFnArray);
					break;
				case "ax^2+bx+c":
					drawArea.addPoint(new QuadraticFn(Double.parseDouble(inputArg_a.getText().trim()),Double.parseDouble(inputArg_b.getText().trim()),Double.parseDouble(inputArg_c.getText().trim())).graphFnArray);
					break;
				case "ax^3+bx^2+cx+d":
					drawArea.addPoint(new CubicFn(Double.parseDouble(inputArg_a.getText().trim()),Double.parseDouble(inputArg_b.getText().trim()),Double.parseDouble(inputArg_c.getText().trim()),Double.parseDouble(inputArg_d.getText().trim())).graphFnArray);
					break;
				case "ax^4+bx^3+cx^2+dx+e":
					drawArea.addPoint(new FourPowerFn(Double.parseDouble(inputArg_a.getText().trim()),Double.parseDouble(inputArg_b.getText().trim()),Double.parseDouble(inputArg_c.getText().trim()),Double.parseDouble(inputArg_d.getText().trim()),Double.parseDouble(inputArg_e.getText().trim())).graphFnArray);
					break;
				case "a^x+b":
					drawArea.addPoint(new ExpFn(Double.parseDouble(inputArg_a.getText().trim()),Double.parseDouble(inputArg_b.getText().trim())).graphFnArray);
					break;
				case "log(a,x)+b":
					drawArea.addPoint(new LogFn(Double.parseDouble(inputArg_a.getText().trim()),Double.parseDouble(inputArg_b.getText().trim())).graphFnArray);
					break;
				case "(x-l)^2/a^2+(y-h)^2/b^2=1":
					drawArea.addPoint(new Ellipse(Double.parseDouble(inputArg_a.getText().trim()),Double.parseDouble(inputArg_b.getText().trim()),Double.parseDouble(inputArg_l.getText().trim()),Double.parseDouble(inputArg_h.getText().trim())).graphFnArray);
					break;
				case "triangle(a,b,c,d,e,f)":
					drawArea.addPoint(new Triangle(Double.parseDouble(inputArg_a.getText().trim()),Double.parseDouble(inputArg_b.getText().trim()),Double.parseDouble(inputArg_c.getText().trim()),Double.parseDouble(inputArg_d.getText().trim()),Double.parseDouble(inputArg_e.getText().trim()),Double.parseDouble(inputArg_f.getText().trim())).graphFnArray);
					break;
			}
			drawArea.repaint();
		});
		clearButton.addActionListener(e ->
		{
			drawArea.clearCanvas();
			drawArea.repaint();
		});

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
			point[0] = ((double)x/15)-10;
			point[1] = 10-((double)y/15);
			return point;
		}
		//isPointOnFn check if a point on the function
		public boolean isPointOnFn(double x,double y)
		{
			//sqrt(2*(1/15)^2)==0.094280904158206
			return (Math.abs(fnExp(x)-y)<=0.095);
		}
		//make a 300*300 px array for graph and storage in graphFnArray[300][300]
		public void mkGraphArray()
		{
			for(int x=0;x<300;x++)
				for(int y=0;y<300;y++)
					graphFnArray[x][y]=isPointOnFn(pxToPoint(x,y)[0],pxToPoint(x,y)[1]);
		}
	}

	class LinearFn extends BasicFn
	{
		private double a,b;
		public double fnExp(double x)
		{
			return a*x+b;
		}
		public LinearFn(double a,double b)
		{
			this.a=a;
			this.b=b;
			mkGraphArray();
		}
	}

	class QuadraticFn extends BasicFn
	{
		private double a,b,c;
		public double fnExp(double x)
		{
			return a*x*x+b*x+c;  //ax^2+bx+c
		}
		public QuadraticFn(double a,double b,double c)
		{
			this.a=a;
			this.b=b;
			this.c=c;
			mkGraphArray();
		}
	}

	class CubicFn extends BasicFn
	{
		private double a,b,c,d;
		public double fnExp(double x)
		{
			return a*x*x*x+b*x*x+c*x+d;  //ax^3+bx^2+cx+d
		}
		public CubicFn(double a,double b,double c,double d)
		{
			this.a=a;
			this.b=b;
			this.c=c;
			this.d=d;
			mkGraphArray();
		}
	}

	class FourPowerFn extends BasicFn
	{
		private double a,b,c,d,e;
		public double fnExp(double x)
		{
			return a*x*x*x*x+b*x*x*x+c*x*x+d*x+e;  //ax^4+bx^3+cx^2+dx+e
		}
		public FourPowerFn(double a,double b,double c,double d,double e)
		{
			this.a=a;
			this.b=b;
			this.c=c;
			this.d=d;
			this.e=e;
			mkGraphArray();
		}
	}

	class ExpFn extends BasicFn
	{
		private double a,b;
		public double fnExp(double x)
		{
			return Math.pow(a,x)+b;  //a^x+b
		}
		public ExpFn(double a,double b)
		{
			this.a=a;
			this.b=b;
			mkGraphArray();
		}
	}

	class LogFn extends BasicFn
	{
		private double a,b;
		public double fnExp(double x)
		{
			return Math.log(x)/Math.log(a)+b;  //log(a,x)+b = ln(x)/ln(a)+b
		}
		public LogFn(double a,double b)
		{
			this.a=a;
			this.b=b;
			mkGraphArray();
		}
	}

	class Ellipse extends BasicFn
	{
		private double a,b,l,h;
		//ellipse is a kind of implicit function ,so override the isPointOnFn() instead 
		public boolean isPointOnFn(double x,double y)
		{
			return (Math.abs((((x-l)*(x-l))/(a*a)+((y-h)*(y-h))/(b*b))-1)<=0.090);  //(x-l)^2/a^2+(y-h)^2/b^2=1
		}
		public Ellipse(double a,double b,double l,double h)
		{
			this.a=a;
			this.b=b;
			this.l=l;
			this.h=h;
			mkGraphArray();
		}
	}

	class Triangle extends BasicFn
	{
		private double a,b,c,d,e,f;

		//use two point to make a function expression
		public double fnExp(double x,double x1,double y1,double x2,double y2)
		{
			return((y1/(x1-x2)-y2/(x1-x2))*x+((x1*y2)/(x1-x2)+(x2*y1)/(x2-x1)));
		}
		//override isPointOnFn to adapt the change of fnExp
		public boolean isPointOnFn(double x,double y)
		{
			//sqrt(2*(1/15)^2)==0.094280904158206
			return (Math.abs(fnExp(x,a,b,c,d)-y)<=0.095||Math.abs(fnExp(x,a,b,e,f)-y)<=0.095||Math.abs(fnExp(x,c,d,e,f)-y)<=0.095);
		}
		//remove no use lines ,draw a rect out of triangle and remove all out of the rect
		public void rmNoUseLine()
		{
			double x1=Math.min(a,Math.min(c,e)) ,y1=Math.min(b,Math.min(d,f)) ,x2=Math.max(a,Math.max(c,e)) ,y2=Math.max(b,Math.max(d,f));
			for(int x=0;x<300;x++)
				for(int y=0;y<300;y++)
					if(pxToPoint(x,y)[0]<x1||pxToPoint(x,y)[1]<y1||pxToPoint(x,y)[0]>x2||pxToPoint(x,y)[1]>y2)
						graphFnArray[x][y]=false;
		}

		public Triangle(double a,double b,double c,double d,double e,double f)
		{
			this.a=a;
			this.b=b;
			this.c=c;
			this.d=d;
			this.e=e;
			this.f=f;
			mkGraphArray();
			rmNoUseLine();
		}
	}

	public static void main(String[] args)
	{
		new DrawGraph().init();
	}
}