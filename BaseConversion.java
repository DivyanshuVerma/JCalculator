/*
//	Written by Divyanshu Verma
//	dev.verma1010@gmail.com
//	www.github.com/DivyanshuVerma
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BaseConversion implements ActionListener
{
  public int height = 200;
  JLabel lb1;
  JLabel lb2;
  JLabel lb3;
  JTextField tf1;
  JTextField tf2;
  JComboBox c1;
  JComboBox c2;
  JButton bt1;

  public static String decimalTo(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 2)
      return Integer.toBinaryString(paramInt1);
    if (paramInt2 == 8)
      return Integer.toOctalString(paramInt1);
    if (paramInt2 == 16) {
      return Integer.toHexString(paramInt1);
    }
    return String.valueOf(paramInt1);
  }

  public static int toDecimal(String paramString, int paramInt)
  {
    return Integer.parseInt(paramString, paramInt);
  }

  public void initCmps()
  {
    this.lb1 = new JLabel("From");
    this.lb1.setBounds(10, 40, 45, 30);

    this.c1 = new JComboBox();
    this.c1.addItem("Binary");
    this.c1.addItem("Octal");
    this.c1.addItem("Decimal");
    this.c1.addItem("Hexadecimal");
    this.c1.setBounds(60, 40, 100, 30);

    this.tf1 = new JTextField();
    this.tf1.setBounds(165, 40, 118, 30);

    this.lb2 = new JLabel("To");
    this.lb2.setBounds(10, 75, 45, 30);

    this.c2 = new JComboBox();
    this.c2.addItem("Binary");
    this.c2.addItem("Octal");
    this.c2.addItem("Decimal");
    this.c2.addItem("Hexadecimal");
    this.c2.setBounds(60, 75, 100, 30);

    this.lb3 = new JLabel("Result");
    this.lb3.setBounds(10, 120, 45, 30);

    this.tf2 = new JTextField();
	this.tf2.setEditable(false);
	this.tf2.setBackground(Color.WHITE);
    this.tf2.setBounds(60, 120, 160, 30);

    this.bt1 = new JButton("OK");
    this.bt1.setBounds(225, 120, 58, 30);
    this.bt1.addActionListener(this);
  }

  public Component[] getCmps()
  {
    Component[] arrayOfComponent = new Component[8];

    arrayOfComponent[0] = this.tf2;
    arrayOfComponent[1] = this.lb1;
    arrayOfComponent[2] = this.lb2;
    arrayOfComponent[3] = this.c2;
    arrayOfComponent[4] = this.c1;
    arrayOfComponent[5] = this.tf1;
    arrayOfComponent[6] = this.bt1;
    arrayOfComponent[7] = this.lb3;

    return arrayOfComponent;
  }

  public void actionPerformed(ActionEvent paramActionEvent)
  {
    if (validate(this.tf1.getText(), this.c1.getSelectedIndex()))
    {
      int i = 0;
      switch (this.c1.getSelectedIndex()) {
      case 0:
        i = toDecimal(this.tf1.getText(), 2); break;
      case 1:
        i = toDecimal(this.tf1.getText(), 8); break;
      case 2:
        i = toDecimal(this.tf1.getText(), 10); break;
      case 3:
        i = toDecimal(this.tf1.getText(), 16);
      }
      switch (this.c2.getSelectedIndex()) {
      case 0:
        this.tf2.setText(decimalTo(i, 2)); break;
      case 1:
        this.tf2.setText(decimalTo(i, 8)); break;
      case 2:
        this.tf2.setText(decimalTo(i, 10)); break;
      case 3:
        this.tf2.setText(decimalTo(i, 16));
      }
    }
    else {
      this.tf2.setText("Error in Input.");
    }
  }

  public boolean validate(String paramString, int paramInt) {
    char[] arrayOfChar = new char[16];

    for (int i = 0; i < 10; i++) {
      arrayOfChar[i] = String.valueOf(i).charAt(0);
    }
    arrayOfChar[10] = 'a';
    arrayOfChar[11] = 'b';
    arrayOfChar[12] = 'c';
    arrayOfChar[13] = 'd';
    arrayOfChar[14] = 'e';
    arrayOfChar[15] = 'f';

    if (paramInt == 0) paramInt = 2;
    else if (paramInt == 1) paramInt = 8;
    else if (paramInt == 2) paramInt = 10; else {
      paramInt = 16;
    }
    if(paramString.charAt(0)=='-')
        paramString = paramString.substring(1);
    for (int i = 0; i < paramString.length(); i++)
    {
      int j = 0;
      for (int k = 0; k < paramInt; k++) {
        if (paramString.charAt(i) != arrayOfChar[k])
          continue;
        j = 1;
        break;
      }
      if (j == 0)
        return false;
    }
    return true;
  }
}
