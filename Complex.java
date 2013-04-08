/*
//	Written by Divyanshu Verma
//	dev.verma1010@gmail.com
//	www.github.com/DivyanshuVerma
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Complex implements ActionListener
{

// C A L C U L A T I O N :

    double r,i;
    
    Complex(double r, double i)
    {
        this.r = r;
        this.i = i;
    }
    
    Complex()
    {
        this(0,0);
    }
	
	Complex(String str)
	{
		if(str.split("i").length > 1)
		{
			this.r = 0;
			this.i = 0;
		}
		else if( str.split("\\+").length > 2 || str.split("\\+").length == 0 || str.split("\\-").length > 2)
		{
			this.r = 0;
			this.i = 0;
		}
		else
		{
			if( str.indexOf("i") == -1)
			{
				r = Double.parseDouble(str);
				i = 0;
			}
			if(str.split("\\+").length != 1)
			{
				this.r = Double.parseDouble( str.split("\\+")[0] );
				this.i = Double.parseDouble( str.split("\\+")[1].split("i")[0] );
			}
			else
			{
				this.r = Double.parseDouble( str.split("-")[0] );
				this.i = Double.parseDouble( str.split("-")[1].split("i")[0] ) * (-1);
			}
		}
	}
    
    public Complex add( Complex a )
    {
        Complex temp = new Complex();
        
        temp.r = this.r + a.r;
        temp.i = this.i + a.i;
        
        return temp;
    }
    
    public Complex subtract( Complex a )
    {
        Complex temp = new Complex();
        
        temp.r = this.r - a.r;
        temp.i = this.i - a.i;
        
        return temp;
    }
    
    public Complex multiply( Complex a )
    {
        Complex temp = new Complex();
        
        temp.r = this.r * a.r - this.i * a.i;
        temp.i = this.r * a.i + this.i * a.r;
        
        return temp;
    }
    
    public Complex divide( Complex a )
    {
        Complex temp = new Complex();
        
        temp = this.multiply( a.conjugate() );
		temp.r = this.r / a.modulus() ;
		temp.i = this.i / a.modulus() ;
        
        return temp;
    }
        
    public Complex conjugate()
    {
        Complex temp = new Complex( this.r , this.i * (-1) );
        
        return temp;
    }
    
    public double modulus()
    {
        double temp;
        
        temp = Math.sqrt(this.r * this.r + this.i * this.i);
        
        return temp;
    }
    
    public double arg()
    {
        double temp;
        
        temp = Math.atan(this.i / this.r) * 180 / Math.PI;
        
        return temp;
    }
    
    public String toString()
    {
        if( this.i < 0 )
            return new String(r + "" + i + "i");
        else
            return new String(r + "+" + i + "i");
    }

// G R A P H I C A L    U S E R     I N T E R F A C E :

    public int height = 280 + 35;
	
	JButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt0,ibtn,mod,con,arg,bsb,bad,clr,btdec,beq,btmul,btdiv,btadd,btsub;
	JTextField tf1;
	
	Font f = new Font("Arial", Font.BOLD, 20);
    
	String mode = "";
	String opr = "";
	Complex n1;
	
    public void initCmps()
    {
		tf1=new JTextField();
		tf1.setEditable(false);
		tf1.setBackground(Color.WHITE);
		tf1.setBounds(5,40,209,30);
	
		clr=new JButton("CLR");
		clr.setBounds(215,40,68,30);
		clr.addActionListener(this);
	
		ibtn= new JButton("i");
		ibtn.setBounds(5,75,68,30);
		ibtn.addActionListener(this);
	
		mod=new JButton("mod");
		mod.setBounds(75,75,68,30);
		mod.addActionListener(this);
	
		con=new JButton("conj");
		con.setBounds(145,75,68,30);
		con.addActionListener(this);
	
		arg=new JButton("arg");
		arg.setBounds(215,75,68,30);
		arg.addActionListener(this);
		
		btmul=new JButton("MUL");
		btmul.setBounds(145,110,68,30);
		btmul.addActionListener(this);
		
		btdiv=new JButton("DIV");
		btdiv.setBounds(215,110,68,30);
		btdiv.addActionListener(this);
		
		btadd=new JButton("ADD");
		btadd.setBounds(5,110,68,30);
		btadd.addActionListener(this);
		
		btsub=new JButton("SUB");
		btsub.setBounds(75,110,68,30);
		btsub.addActionListener(this);
		
		bt1=new JButton("1");
		bt1.setBounds(5,110+35,68,30);
		bt1.addActionListener(this);
		
		bt2=new JButton("2");
		bt2.setBounds(75,110+35,68,30);
		bt2.addActionListener(this);
		
		bt3=new JButton("3");
		bt3.setBounds(145,110+35,68,30);
		bt3.addActionListener(this);
		
		bt4=new JButton("4");
		bt4.setBounds(5,145+35,68,30);
		bt4.addActionListener(this);
		
		bt5=new JButton("5");
		bt5.setBounds(75,145+35,68,30);
		bt5.addActionListener(this);
		
		bt6=new JButton("6");
		bt6.setBounds(145,145+35,68,30);
		bt6.addActionListener(this);
		
		bt7=new JButton("7");
		bt7.setBounds(5,180+35,68,30);
		bt7.addActionListener(this);
		
		bt8=new JButton("8");
		bt8.setBounds(75,180+35,68,30);
		bt8.addActionListener(this);
		
		bt9=new JButton("9");
		bt9.setBounds(145,180+35,68,30);
		bt9.addActionListener(this);
		
		bt0=new JButton("0");
		bt0.setBounds(5,215+35,68,30);
		bt0.addActionListener(this);
		
		btdec=new JButton(".");
		btdec.setBounds(75,215+35,68,30);
		btdec.addActionListener(this);
		
		bsb=new JButton("-");
		bsb.setBounds(215,110+35,68,65);
		bsb.addActionListener(this);
		bsb.setFont(f);
		
		bad=new JButton("+");
		bad.setBounds(215,180+35,68,65);
		bad.addActionListener(this);
		bad.setFont(f);
		
		beq=new JButton("=");
		beq.setBounds(145,215+35,68,30);
		beq.addActionListener(this);
    }
    
    public Component[] getCmps()
    {
		Component temp[] = new Component[24];
	
	    temp[0] = con;
		temp[1] = tf1;
		temp[2] = clr;
		temp[3] = arg;
		temp[4] = ibtn;
		temp[5] = mod;
		temp[6] = bt1;
		temp[7] = bt2;
		temp[8] = bt3;
		temp[9] = bt4;
		temp[10] = bt5;
		temp[11] = bt6;
		temp[12] = bt7;
		temp[13] = bt8;
		temp[14] = bt9;
		temp[15] = bt0;		
		temp[16] = bad;
		temp[17] = bsb;
		temp[18] = btdec;
		temp[19] = beq;
		temp[20] = btdiv;
		temp[21] = btmul;
		temp[22] = btadd;
		temp[23] = btsub;
		
		return temp;
    }
    
    public void actionPerformed(ActionEvent e)
    {
		Component ct = (Component) e.getSource();
		
		if(ct == arg)
		{
			tf1.setText( String.valueOf(new Complex( tf1.getText() ).arg()) );
		}
		else if(ct == mod)
		{
			tf1.setText( String.valueOf(new Complex( tf1.getText() ).modulus()) );
		}
		else if(ct == con)
		{
			tf1.setText( (new Complex( tf1.getText() ).conjugate()).toString());
		}
		else if(ct == clr)
		{
			tf1.setText("");
		}
		else if(ct==bt0 || ct==bt1 || ct==bt2 || ct==bt3 || ct==bt4 || ct==bt5 || ct==bt6 || ct==bt7 || ct==bt8 || ct==bt9 || ct==btdec || ct==bad || ct==bsb || ct==ibtn)
		{
			if(mode.equals("opr"))
		        tf1.setText("");
            tf1.setText(tf1.getText() + e.getActionCommand());
            mode = "num";
		}
		else if(ct==btadd)
		{
			if(mode.equals("num"))
				n1 = new Complex(tf1.getText());
		    mode = "opr";
			opr = "add";
		}
		else if(ct==btsub)
		{
			if(mode.equals("num"))
				n1 = new Complex(tf1.getText());
		    mode = "opr";
			opr = "sub";
		}
		else if(ct==btmul)
		{
			if(mode.equals("num"))
				n1 = new Complex(tf1.getText());
		    mode = "opr";
			opr = "mul";
		}
		else if(ct==btdiv)
		{
			if(mode.equals("num"))
				n1 = new Complex(tf1.getText());
		    mode = "opr";
			opr = "div";
		}
		else if(ct==beq)
		{
			Complex dtemp = new Complex(tf1.getText());
			if(opr.equals("add"))
			{
				n1 = n1.add(dtemp);
				tf1.setText(n1.toString());
			}
			else if(opr.equals("sub"))
			{
				n1 = n1.subtract(dtemp);
				tf1.setText(n1.toString());
			}
			else if(opr.equals("mul"))
			{
				n1 = n1.multiply(dtemp);
				tf1.setText(n1.toString());
			}
			else if(opr.equals("div"))
			{
				n1 = n1.divide(dtemp);
				tf1.setText(n1.toString());
			}
			else;
		    mode = "opr";
			opr = "eq";
		}
		else;
    }
}
