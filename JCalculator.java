/*
//	Written by Divyanshu Verma
//	dev.verma1010@gmail.com
//	www.github.com/DivyanshuVerma
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.lang.reflect.*;

class JCalculator extends JFrame implements ActionListener
{
    JButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt0,bteq,btad,btsb,btmul,btdiv,btclr,btdec;
	JButton slide;
    JTextField t1;
    JButton npr,ncr,nf,bksp,sqrx,cubx,sqrtx,xy,mod,neg,invx,ex,logx,lnx,sx,cx,tx,asx,acx,atx;
    JToolBar tbar;
	
    int width_frame = 295;
    int height_frame = 260;
    int height_frame_changed = 445;
    
    int button_width = 68;
    int button_height = 30;
    
    int button_x1 = 5;
    int button_x2 = 75;
    int button_x3 = 145;
    int button_x4 = 215;
    
    int button_y1 = 70;
    int button_y2 = 105;
    int button_y3 = 140;
    int button_y4 = 175;
    int button_y5 = 210;
    
    int button_y6 = 70;
    int button_y7 = 105;
    int button_y8 = 140;
    int button_y9 = 175;
    
    int button_y6_changed = 255;
    int button_y7_changed = 290;
    int button_y8_changed = 325;
    int button_y9_changed = 360;
    
    Font small = new Font("SansSerif", Font.BOLD, 10);
    boolean slided = false;
    
    double n1 = 0;
	String opr = "";
	String mode = "";
    
    Container c;
	
	JComboBox list;
	JButton about;
	
    JCalculator()
    {
        super("JCalculator");
		try{
		Thread.sleep(2000);
		}catch(Exception ex){}
            
        setSize( width_frame, height_frame );
        c = this.getContentPane();
        
        t1 = new JTextField();
        t1.setBounds( 5, 35,209, 30 );
		t1.setEditable(false);
		t1.setBackground(Color.WHITE);
        t1.setHorizontalAlignment(JTextField.RIGHT);
		
		list = new JComboBox();
		list.setBounds(5,0,208,30);
		populatelist();
		list.addActionListener(this);
		
		about = new JButton("About");
        about.addActionListener(this);
        about.setBounds( 215,0,button_width,button_height);
        
        tbar = new JToolBar();
        tbar.setBounds( 0, 0, width_frame, button_height );
        tbar.setLayout(null);
		
		tbar.add(list);
		tbar.add(about);

        bt1 = new JButton("1");
        bt1.setBounds( button_x1, button_y6, button_width, button_height );

        bt2 = new JButton("2");
        bt2.setBounds( button_x2, button_y6, button_width, button_height );

        bt3 = new JButton("3");
        bt3.setBounds( button_x3, button_y6, button_width, button_height );

        bt4 = new JButton("4");
        bt4.setBounds( button_x1, button_y7, button_width, button_height );

        bt5 = new JButton("5");
        bt5.setBounds( button_x2, button_y7, button_width, button_height );

        bt6 = new JButton("6");
        bt6.setBounds( button_x3, button_y7, button_width, button_height );

        bt7 = new JButton("7");
        bt7.setBounds( button_x1, button_y8, button_width, button_height );

        bt8 = new JButton("8");
        bt8.setBounds( button_x2, button_y8, button_width, button_height );

        bt9 = new JButton("9");
        bt9.setBounds( button_x3, button_y8, button_width, button_height );

        btad = new JButton("+");
        btad.setBounds( button_x4, button_y9, button_width, button_height );

        btsb = new JButton("-");
        btsb.setBounds( button_x4, button_y8, button_width, button_height );

        btmul = new JButton("*");
        btmul.setBounds( button_x4, button_y7, button_width, button_height );

        btdiv = new JButton("/");
        btdiv.setBounds( button_x4, button_y6, button_width, button_height );

        bteq = new JButton("=");
        bteq.setBounds( button_x3, button_y9, button_width, button_height );

        bt0 = new JButton("0");
        bt0.setBounds( button_x1, button_y9, button_width, button_height );

        btdec = new JButton(".");
        btdec.setBounds( button_x2, button_y9, button_width, button_height );

        btclr = new JButton("CLR");
        btclr.setBounds( button_x4, 35, button_width, button_height );

        btdec.addActionListener(this);
        bteq.addActionListener(this);
        btad.addActionListener(this);
        btsb.addActionListener(this);
        btdiv.addActionListener(this);
        btmul.addActionListener(this);
        btclr.addActionListener(this);
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        bt4.addActionListener(this);
        bt5.addActionListener(this);
        bt6.addActionListener(this);
        bt7.addActionListener(this);
        bt8.addActionListener(this);
        bt9.addActionListener(this);
        bt0.addActionListener(this);
        
        c.add(tbar);
		
		slide = new JButton("Show");
		slide.addActionListener(this);
		slide.setBounds( button_x1, 210, width_frame -17, button_height/2);
		
		initScientific();
		
		addSimple();

        this.setLayout(null);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Window win = this;
		win.setLocation( (d.width-width_frame)/2 , (d.height-height_frame)/2  );
		
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false); 
    }
	  
	public void populatelist()
	{
		File f = new File(".");
		String ll[] = f.list();
		
		list.addItem("Basic");
		
		for(String s : ll)
			if(s.endsWith(".class") && !s.startsWith("JCalculator"))
				list.addItem( s.substring(0, s.indexOf(".class")) );
	}
	 
    public void actionPerformed(ActionEvent e)
    {
		Component ctt = (Component) e.getSource();
	
		if(ctt == about)
		{
			JOptionPane.showMessageDialog(this, "<html>Developed By:<br/>Divyanshu Verma<br/>Email: dev.verma_1010@gmail.com</html>" , "About" , JOptionPane.PLAIN_MESSAGE);
		}
		else if(ctt == list)
		{
			String so = (String) list.getSelectedItem();
			
			Component arr[] = c.getComponents();
			for(int i=0;i<arr.length;i++)
				if(arr[i] != tbar)
			c.remove(arr[i]);			
			repaint();
			
			if(so.equals("Basic"))
				addSimple();
			else
			{
				try
				{					
					Class cl = Class.forName(so);
					
					Object o = cl.newInstance();
					
					cl.getMethod("initCmps").invoke(o);
										
					Component arr2[] = (Component[]) cl.getMethod("getCmps").invoke(o);
					for(int i=0;i<arr2.length;i++)
						c.add(arr2[i]);
					
					Field fieldlist[] = cl.getDeclaredFields();
					for (int i = 0; i < fieldlist.length; i++)
						if(fieldlist[i].getName().equals("height"))
						{
							this.setSize(width_frame, (Integer) fieldlist[i].get(o));
							break;
						}
					
					repaint();
				}
				catch (Exception exx)
				{
					System.err.println(exx);
				}
			}
		}
        else if(ctt==slide)
		{
			expand();
		}
		else if(ctt==bt0 || ctt==bt1 || ctt==bt2 || ctt==bt3 || ctt==bt4 || ctt==bt5 || ctt==bt6 || ctt==bt7 || ctt==bt8 || ctt==bt9 || ctt==btdec)
		{
		    if(mode.equals("opr"))
		        t1.setText("");
            t1.setText(t1.getText() + e.getActionCommand());
            mode = "num";
		}
		else if(ctt==btclr)
		{
			t1.setText("");
		}
		else if(ctt==btad)
		{
			if(mode.equals("num"))
				n1 = Double.parseDouble(t1.getText());
		    mode = "opr";
			opr = "add";
		}
		else if(ctt==btsb)
		{
		    if(mode.equals("num"))
				n1 = Double.parseDouble(t1.getText());
		    mode = "opr";
			opr = "sub";
		}
		else if(ctt==btmul)
		{
		    if(mode.equals("num"))
				n1 = Double.parseDouble(t1.getText());
		    mode = "opr";
			opr = "mul";
		}
		else if(ctt==btdiv)
		{
		    if(mode.equals("num"))
				n1 = Double.parseDouble(t1.getText());
		    mode = "opr";
			opr = "div";
		}
		else if(ctt==mod)
		{
		    if(mode.equals("num"))
				n1 = Double.parseDouble(t1.getText());
		    mode = "opr";
			opr = "mod";
		}
		else if(ctt==bteq)
		{
		    double dtemp = Double.parseDouble(t1.getText());
			if(opr.equals("add"))
			{
				n1 = n1 + dtemp;
				t1.setText(String.valueOf(n1));
			}
			else if(opr.equals("sub"))
			{
				n1 = n1 - dtemp;
				t1.setText(String.valueOf(n1));
			}
			else if(opr.equals("mul"))
			{
				n1 = n1 * dtemp;
				t1.setText(String.valueOf(n1));
			}
			else if(opr.equals("div"))
			{
				n1 = n1 / dtemp;
				t1.setText(String.valueOf(n1));
			}
			else if(opr.equals("mod"))
			{
				n1 = n1 % dtemp;
				t1.setText(String.valueOf(n1));
			}
			else;
				
		    mode = "opr";
			opr = "eq";
		}
		else if(ctt==npr)
		{
			if( t1.getText().endsWith(".0") || t1.getText().indexOf(".")==-1)
			{
				double dt = Math.round(Double.parseDouble(t1.getText()));
				double r = Double.parseDouble(JOptionPane.showInputDialog( "Enter r: " ));
				dt = fact(dt) / fact( dt -r ) ;
				t1.setText(String.valueOf(dt));
			}
			else
			{
				String temp = t1.getText();
				t1.setText("Not an integer");
				repaint();
				try{
				Thread.sleep(1000);
				}catch(Exception ex){}
				t1.setText(temp);
			}
		}
		else if(ctt==ncr)
		{
			if( t1.getText().endsWith(".0") || t1.getText().indexOf(".")==-1)
			{
				double dt = Math.round(Double.parseDouble(t1.getText()));
				double r = Double.parseDouble(JOptionPane.showInputDialog( "Enter r: " ));
				dt = fact(dt) / (fact(r) * fact( dt -r )) ;
				t1.setText(String.valueOf(dt));
			}
			else
			{
				String temp = t1.getText();
				t1.setText("Not an integer");
				repaint();
				try{
				Thread.sleep(1000);
				}catch(Exception ex){}
				t1.setText(temp);
			}
		}
		else if(ctt==nf)
		{
			if( t1.getText().endsWith(".0") || t1.getText().indexOf(".")==-1)
			{
				double dt = Math.round(Double.parseDouble(t1.getText()));
				dt = fact(dt);
				t1.setText(String.valueOf(dt));
			}
			else
			{
				String temp = t1.getText();
				t1.setText("Not an integer");
				repaint();
				try{
				Thread.sleep(1000);
				}catch(Exception ex){}
				t1.setText(temp);
			}
		}
		else if(ctt==sqrx)
		{
			t1.setText( String.valueOf( Math.pow( Double.parseDouble(t1.getText()), 2)));
		}
		else if(ctt==cubx)
		{
			t1.setText( String.valueOf( Math.pow( Double.parseDouble(t1.getText()), 3)));
		}
		else if(ctt==sqrtx)
		{
			t1.setText( String.valueOf( Math.sqrt( Double.parseDouble(t1.getText()))));
		}		
		else if(ctt==xy)
		{
			double y = Double.parseDouble(JOptionPane.showInputDialog( "Enter y: " ));
			t1.setText( String.valueOf( Math.pow( Double.parseDouble(t1.getText()), y)));
		}
		else if(ctt==bksp)
		{
			t1.setText( t1.getText().substring( 0, t1.getText().length() -1) );
		}
		else if(ctt==neg)
		{
			if(t1.getText().startsWith("-"))
				t1.setText( t1.getText().substring(1) );
			else
				t1.setText( "-" + t1.getText() );
		}
		else if(ctt==invx)
		{
			t1.setText( String.valueOf( 1/( Double.parseDouble(t1.getText()) ) ) );
		}
		else if(ctt==ex)
		{
			t1.setText( String.valueOf( Math.pow( Math.E , Double.parseDouble(t1.getText()))));
		}
		else if(ctt==logx)
		{
			t1.setText( String.valueOf( Math.log10( Double.parseDouble(t1.getText()))));
		}
		else if(ctt==lnx)
		{
			t1.setText( String.valueOf( Math.log( Double.parseDouble(t1.getText()))));
		}
		else if(ctt==sx)
		{
			t1.setText( String.valueOf( Math.sin( Double.parseDouble(t1.getText()))));
		}
		else if(ctt==cx)
		{
			t1.setText( String.valueOf( Math.cos( Double.parseDouble(t1.getText()))));
		}
		else if(ctt==tx)
		{
			t1.setText( String.valueOf( Math.tan( Double.parseDouble(t1.getText()))));
		}
		else if(ctt==asx)
		{
			t1.setText( String.valueOf( Math.asin( Double.parseDouble(t1.getText()))));
		}
		else if(ctt==acx)
		{
			t1.setText( String.valueOf( Math.acos( Double.parseDouble(t1.getText()))));
		}
		else if(ctt==atx)
		{
			t1.setText( String.valueOf( Math.atan( Double.parseDouble(t1.getText()))));
		}
		else
			t1.setText( t1.getText() + e.getActionCommand());
    }
    
	public double fact(double a)
	{
		for(double i=a-1;i>0;i--)
			a *= i;
		return a;
	}
	
    public void removeScientific()
    {
        if( slided )
        {
            c.remove(npr);
            c.remove(ncr);
            c.remove(nf);
            c.remove(bksp);
            c.remove(sqrx);
            c.remove(cubx);
            c.remove(sqrtx);
            c.remove(xy);
            c.remove(mod);
            c.remove(neg);
            c.remove(invx);
            c.remove(ex);
            c.remove(logx);
            c.remove(lnx);
            c.remove(sx);
            c.remove(cx);
            c.remove(tx);
            c.remove(asx);
            c.remove(acx);
            c.remove(atx);
            slided = false;
        }
    }
    
    public void addScientific()
    {
        if(!slided)
        {
            c.add(npr);
            c.add(ncr);
            c.add(nf);
            c.add(bksp);
            c.add(sqrx);
            c.add(cubx);
            c.add(sqrtx);
            c.add(xy);
            c.add(mod);
            c.add(neg);
            c.add(invx);
            c.add(ex);
            c.add(logx);
            c.add(lnx);
            c.add(sx);
            c.add(cx);
            c.add(tx);
            c.add(asx);
            c.add(acx);
            c.add(atx);
            slided = true;
        }
    }
    
    public void addSimple()
    {
        c.add(t1);
        c.add(bt1);
        c.add(bt2);
        c.add(bt3);
        c.add(bt4);
        c.add(bt5);
        c.add(bt6);
        c.add(bt7);
        c.add(bt8);
        c.add(bt9);
        c.add(bt0);
        c.add(btad);
        c.add(btsb);
        c.add(btmul);
        c.add(btdiv);
        c.add(bteq);
        c.add(btclr);
        c.add(btdec);
		c.add(slide);
		
		repositionSimple();
    
    }
    
    public void repositionSimple()
    {
        bt1.setBounds(button_x1, button_y6, button_width, button_height );
        bt2.setBounds(button_x2, button_y6, button_width, button_height );
        bt3.setBounds(button_x3, button_y6, button_width, button_height );
        btdiv.setBounds(button_x4, button_y6, button_width, button_height );

        bt4.setBounds(button_x1, button_y7, button_width, button_height );
        bt5.setBounds(button_x2, button_y7, button_width, button_height );
        bt6.setBounds(button_x3, button_y7, button_width, button_height );
        btmul.setBounds(button_x4, button_y7, button_width, button_height );

        bt7.setBounds(button_x1, button_y8, button_width, button_height );
        bt8.setBounds(button_x2, button_y8, button_width, button_height );
        bt9.setBounds(button_x3, button_y8, button_width, button_height );
        btsb.setBounds(button_x4, button_y8, button_width, button_height );

        bt0.setBounds(button_x1, button_y9, button_width, button_height );
        btdec.setBounds(button_x2, button_y9, button_width, button_height );
        bteq.setBounds(button_x3, button_y9, button_width, button_height );
        btad.setBounds(button_x4, button_y9, button_width, button_height );

        slide.setBounds( button_x1, 210, width_frame-17, button_height/2 );
        slide.setText("Show");
        
    	this.setSize(width_frame,height_frame);
    	slided = false;
        
    }
    
    public void expand()
    {
        if( slided )
        {
			removeScientific();
        
            repaint();
            
            for(int i=height_frame_changed; i>=height_frame;i=i-10)
			{
				this.setSize(width_frame,i);
				try{
				Thread.sleep(10);
				}catch(Exception ex){}
			}
			
            repositionSimple();
        }
        else
        {
            for(int i=height_frame; i<=height_frame_changed;i=i+10)
			{
				this.setSize(width_frame,i);
				try{
				Thread.sleep(10);
				}catch(Exception ex){}
			}
			
			this.setSize(width_frame,height_frame_changed);
			
    		slide.setBounds( button_x1, 395, width_frame-17, button_height/2);
    		slide.setText("Hide");
			
            bt0.setBounds(button_x1, button_y9_changed, button_width, button_height );
            btdec.setBounds(button_x2, button_y9_changed, button_width, button_height );
            bteq.setBounds(button_x3, button_y9_changed, button_width, button_height );
            btad.setBounds(button_x4, button_y9_changed, button_width, button_height );			
            
			bt7.setBounds(button_x1, button_y8_changed, button_width, button_height );
            bt8.setBounds(button_x2, button_y8_changed, button_width, button_height );
            bt9.setBounds(button_x3, button_y8_changed, button_width, button_height );
            btsb.setBounds(button_x4, button_y8_changed, button_width, button_height );
            
            bt4.setBounds(button_x1, button_y7_changed, button_width, button_height );
            bt5.setBounds(button_x2, button_y7_changed, button_width, button_height );
            bt6.setBounds(button_x3, button_y7_changed, button_width, button_height );
            btmul.setBounds(button_x4, button_y7_changed, button_width, button_height );
            
            bt1.setBounds(button_x1, button_y6_changed, button_width, button_height );
            bt2.setBounds(button_x2, button_y6_changed, button_width, button_height );
            bt3.setBounds(button_x3, button_y6_changed, button_width, button_height );
            btdiv.setBounds(button_x4, button_y6_changed, button_width, button_height );
   			
   			addScientific();
   			
   			repaint();
        }
    }

    public void initScientific()
    {
        npr = new JButton("nPr");
		ncr = new JButton("nCr");
		nf = new JButton("n!");
		bksp = new JButton("BKSP");
		bksp.setFont(small);
		sqrx = new JButton("x^2");
		cubx = new JButton("x^3");
		sqrtx = new JButton("sqrt(x)");
		sqrtx.setFont(small);
		xy = new JButton("x^y");
		mod = new JButton("%");
		neg = new JButton("+/-");
		invx = new JButton("1/x");
		ex = new JButton("e^x");
		logx = new JButton("log");
		lnx = new JButton("ln");
		sx = new JButton("sin");
		cx = new JButton("cos");
		tx = new JButton("tan");
		asx = new JButton("asin");
		acx = new JButton("acos");
		atx = new JButton("atan");
		
		npr.addActionListener(this);
		ncr.addActionListener(this);
		nf.addActionListener(this);
		bksp.addActionListener(this);
		sqrx.addActionListener(this);
		cubx.addActionListener(this);
		sqrtx.addActionListener(this);
		xy.addActionListener(this);
		mod.addActionListener(this);
		neg.addActionListener(this);
		invx.addActionListener(this);
		ex.addActionListener(this);
		logx.addActionListener(this);
		lnx.addActionListener(this);
		sx.addActionListener(this);
		cx.addActionListener(this);
		tx.addActionListener(this);
		asx.addActionListener(this);
		acx.addActionListener(this);
		atx.addActionListener(this);
		
		npr.setBounds( button_x1, button_y1, button_width, button_height );
		ncr.setBounds( button_x2, button_y1, button_width, button_height );
		nf.setBounds( button_x3, button_y1, button_width, button_height );
		bksp.setBounds( button_x4, button_y1, button_width, button_height );
		
		sqrx.setBounds( button_x1, button_y2, button_width, button_height );
		cubx.setBounds( button_x2, button_y2, button_width, button_height );
		sqrtx.setBounds( button_x3, button_y2, button_width, button_height );
		xy.setBounds( button_x4, button_y2, button_width, button_height );
		
		mod.setBounds( button_x1, button_y3, button_width, button_height );
		neg.setBounds( button_x2, button_y3, button_width, button_height );
		invx.setBounds( button_x3, button_y3, button_width, button_height );
		ex.setBounds( button_x4, button_y3, button_width, button_height );
		
		logx.setBounds( button_x1, button_y4, button_width, button_height );
		lnx.setBounds( button_x2, button_y4, button_width, button_height );
		sx.setBounds( button_x3, button_y4, button_width, button_height );
		cx.setBounds( button_x4, button_y4, button_width, button_height );
		
		tx.setBounds( button_x1, button_y5, button_width, button_height );
		asx.setBounds( button_x2, button_y5, button_width, button_height );
		acx.setBounds( button_x3, button_y5, button_width, button_height );
		atx.setBounds( button_x4, button_y5, button_width, button_height );
		
    }

    public static void main(String args[])
    {
        JCalculator j  =  new JCalculator();
    }
}
