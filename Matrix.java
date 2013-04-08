/*
//	Written by Divyanshu Verma
//	dev.verma1010@gmail.com
//	www.github.com/DivyanshuVerma
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Matrix implements ActionListener
{

// C A L C U L A T I O N :

    public static double[][] add(double[][] a, double[][] b)
    {
        if(a.length!=b.length)
            return null;
        
        for(int i=0; i<a.length ; i++)
            if(a[i].length!=b[i].length)
                return null;
        
        double temp[][] = new double[a.length][a[0].length];
        
        for(int i=0; i<a.length ; i++)
            for(int j=0; j<a.length ; j++)
                temp[i][j] = a[i][j] + b[i][j];
                
        return temp;
    }
    
    public static double[][] subtract(double[][] a, double[][] b)
    {
        if(a.length!=b.length)
            return null;
        
        for(int i=0; i<a.length ; i++)
            if(a[i].length!=b[i].length)
                return null;
        
        double temp[][] = new double[a.length][a[0].length];
        
        for(int i=0; i<a.length ; i++)
            for(int j=0; j<a.length ; j++)
                temp[i][j] = a[i][j] - b[i][j];
                
        return temp;    
    }
    
    public static double[][] multiply(double[][] a, double[][] b)
    {
        if(a[0].length != b.length)
            return null;
        
        for(int i=0; i<a.length ;i++)
            if( a[i].length != a[0].length )
                return null;
        
        int row = a.length;
        int col = b[0].length;
        
        double temp[][] = new double[row][col];
        
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                for(int k=0;k<a[0].length;k++)
                    temp[i][j]+=a[i][k]*b[k][j];

        return temp;
    }
    
    public static double[][] divide(double[][] a, double[][] b)
    {
        double itemp[][] = Matrix.inverse(b);
        
        if( itemp == null )
            return null;

        double temp[][] = Matrix.multiply( a, itemp );
        
        return temp;
    }
    
    public static double[] det(double[][] a)
    {
        int row = a.length;
        int col = a[0].length;
        
        double d[] = { 0 };
        
        for(int i=0;i<row;i++)
            if( a[i].length != row)
                return null;

        if( row == 2 )
        {
            d[0] = a[0][0] * a[1][1] - a[0][1] * a[1][0];
            return d;
        }
        else
        {
            double temp[][] = new double[ row -1 ][ row-1 ];

            for( int i=0;i< col ;i++)
            {
                int l=0;
                int m=0;
                for( int j=0; j< row;j++)
                    for(int k=0;k<col;k++)
                    {
                        if( j==0 || k==i )
                            continue;
                        temp[l][m] = a[j][k];
                        m++;
                        if(m>=row-1)
                        {
                            l++;
                            m=0;
                        }
                    }
                d[0] += a[0][i] * det(temp)[0] * Math.pow( -1, i);
            }
            return d;
        }
    }
    
    public static double[][] inverse(double[][] a)
    {           
        double d[] = det(a);
        
        if( d == null || d[0] == 0 )
            return null;
            
        int row = a.length;
        int col = a[0].length;
        
        double itemp[][] = new double[ row ][ row ];
        double temp[][] = new double[ row -1 ][ row-1 ];

        for(int i=0; i<row; i++)
            for( int j=0; j<col; j++)
            {
                int m=0;
                int n=0;
                for( int k=0; k<row; k++)
                    for(int l=0; l<col; l++)
                    {
                        if( k==i || l==j )
                            continue;
                            
                        temp[m][n] = a[k][l];
                        n++;
                        if(n>=row-1)
                        {
                            m++;
                            n=0;
                        }
                    }
                itemp[j][i] = det(temp)[0]/d[0] * Math.pow( -1, (i+j) );
            }
            
        return itemp;
    }

// G R A P H I C A L    U S E R     I N T E R F A C E :

    public int height = 380;
	JButton add,sub,mul,div,detr,inv;
	JTextArea ta1,ta2,ta3;
	JLabel lb_res,lb_mat1,lb_mat2;
	
	int button_width = 65;
	int button_height = 30;
	
	double mat1[][],mat2[][];
	
	public boolean validateMatrix(int type)
	{
		String t;
		if(type==1)
			t = ta1.getText();
		else
			t = ta2.getText();

		String m1[] = t.split("\\n");
		String mm[][] = new String[ m1.length ][];
		double dm[][];
		for(int i=0;i<m1.length;i++)
			mm[i] = m1[i].split(" ");
		dm = new double[ mm.length ][ mm[0].length ];
		try
		{
			for(int i=0;i<mm.length;i++)
				for(int j=0;j<mm[i].length;j++)
					dm[i][j] = Double.parseDouble(mm[i][j]);
		}
		catch(Exception ex)
		{ return false; }
		
		if(type==1)
			mat1 = dm;
		else
			mat2 = dm;
			
		return true;
	}
    
    public void initCmps()
    {		
		add = new JButton("Add");
		add.addActionListener(this);
		add.setBounds(10,155, button_width, button_height);
		
		sub = new JButton("Sub");
		sub.addActionListener(this);
		sub.setBounds(78,155, button_width, button_height);
		
		mul = new JButton("Mul");
		mul.addActionListener(this);
		mul.setBounds(147,155, button_width, button_height);
		
		div = new JButton("Div");
		div.addActionListener(this);
		div.setBounds(215,155, button_width, button_height);
		
		inv = new JButton("Inv");
		inv.addActionListener(this);
		inv.setBounds(78,190, button_width, button_height);
		
		detr = new JButton("Det");
		detr.addActionListener(this);
		detr.setBounds(147,190, button_width, button_height);
		
		ta1 = new JTextArea();
		ta1.setBounds(10,50,130,100);
		
		ta2 = new JTextArea();
		ta2.setBounds(150,50,130,100);
		
		ta3 = new JTextArea();
		ta3.setEditable(false);
		ta3.setBounds(10,245,270,100);
		
		lb_res = new JLabel("Result:");
		lb_res.setBounds(10,230,285,15);
		
		lb_mat1 = new JLabel("Matrix 1:");
		lb_mat1.setBounds(10,35,130,15);
		
		lb_mat2 = new JLabel("Matrix 2:");
		lb_mat2.setBounds(150,35,130,15);
    }
    
    public Component[] getCmps()
    {
		Component cmp[] = new Component[12];

		cmp[0] = add;
		cmp[1] = sub;
		cmp[2] = mul;
		cmp[3] = div;
		cmp[4] = inv;
		cmp[5] = detr;
		cmp[6] = ta1;
		cmp[7] = ta2;
		cmp[8] = ta3;
		cmp[9] = lb_res;
		cmp[10] = lb_mat1;
		cmp[11] = lb_mat2;
		
		return cmp;
    }
    
    public void actionPerformed(ActionEvent e)
    {
		if(!validateMatrix(1))
		{
			ta3.setText("Error! Matrix 1 is not defined properly.");
			return;
		}
		if(e.getSource()==detr)
		{
			double temp[] = Matrix.det(mat1);
			if(temp==null)
			{
				ta3.setText("Matrix 1 is not a square matrix.");
				return;
			}
			ta3.setText("Determinant of Matrix 1:\n"+temp[0]);
		}
		else if(e.getSource()==inv)
		{
			double tmp[][] = Matrix.inverse(mat1);
			if( tmp == null)
			{
				ta3.setText("Matrix 1 is not a square matrix\nor its deterinant is zero.");
				return;				
			}
			ta3.setText("");
			for(int i=0;i<tmp.length;i++)
			{
				for(int j=0;j<tmp[i].length;j++)
				{
					String tt = String.valueOf(tmp[i][j]);
					int dec = Math.min(tt.substring( tt.indexOf(".")+1 ).length(),4);
					ta3.setText( ta3.getText() + tt.substring( 0, tt.indexOf(".") + dec +1) + "      ");
				}
				ta3.setText( ta3.getText() + "\n");
			}
		}
		else
		{
			if(!validateMatrix(2))
			{
				ta3.setText("Error! Matrix 2 is not defined properly.");
				return;
			}
			double tmp[][] = null;
			if(e.getSource()==add)
			{
				tmp = Matrix.add(mat1,mat2);
				if( tmp == null)
				{
					ta3.setText("Matrices are of different dimensions.");
					return;				
				}
			}
			else if(e.getSource()==sub)
			{
				tmp = Matrix.subtract(mat1,mat2);
				if( tmp == null)
				{
					ta3.setText("Matrices are of different dimensions.");
					return;				
				}
			}
			else if(e.getSource()==mul)
			{
				tmp = Matrix.multiply(mat1,mat2);
				if( tmp == null)
				{
					ta3.setText("Matrices are of different dimensions.");
					return;				
				}
			}
			else if(e.getSource()==div)
			{
				tmp = Matrix.divide(mat1,mat2);
				if( tmp == null)
				{
					ta3.setText("Matrices are of different dimensions\nor the determinant of Matrix 2 is 0");
					return;				
				}
			}
			else;
			
			ta3.setText("");
			for(int i=0;i<tmp.length;i++)
			{
				for(int j=0;j<tmp[i].length;j++)
				{
					String tt = String.valueOf(tmp[i][j]);
					int dec = Math.min(tt.substring( tt.indexOf(".")+1 ).length(),4);
					ta3.setText( ta3.getText() + tt.substring( 0, tt.indexOf(".") + dec +1) + "      ");
				}
				ta3.setText( ta3.getText() + "\n");
			}

		}
    }
}
