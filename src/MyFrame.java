
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

class MyFrame extends JFrame {
	
		private JTextArea area=null;
		public JTextField field[];
		private JButton btn_tj =null;//提交
		private JButton btn_begin =null;//开始测试
		private JPanel p_area = null;
		private JPanel p_btn = null;
		private GridLayout layout =null;
		private JLabel lbl=null;//显示时间
		
		Date now = new Date();
							
		public MyFrame(String title) {
			super(title);
			init();
			registerListener();
		}
		//初始化函数
		public void init() {
			now.setHours(0);
			now.setMinutes(0);
			now.setSeconds(0);
			 
			area=new JTextArea();
			area.setFont(new Font("宋体",Font.BOLD,20));
			
			layout = new GridLayout();

			btn_tj = new JButton("提交");
			btn_begin = new JButton("开始测试");
			
			p_area= new JPanel();
			p_btn=new JPanel();
			p_btn.setBackground(Color.LIGHT_GRAY);
			
			lbl = new JLabel("计时区域");
		    lbl.setBackground(Color.black);
			p_area.setLayout(layout);
			p_area.add(area);
			
			this.add(p_area,BorderLayout.CENTER);
			
			p_btn.add(btn_begin);
			p_btn.add(btn_tj);;
			p_btn.add(lbl);
			this.add(p_btn,BorderLayout.SOUTH);
			
			this.setBackground(Color.BLUE);
			this.setBounds(100,20,1000,700);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setVisible(true);
		}
		
		//注册监听
		private void registerListener() {
			//计时
			Timer timer = new Timer(1000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Date now2 = new Date(now.getTime() + 1000);
					now = now2;
					SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
					lbl.setText(formatter.format(now));
				}
			});
			btn_begin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//开始测试函数调用
					begin();
					timer.start();
				}				
			});
			btn_tj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					timer.stop();
					try {
						submit();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
		//开始测试函数
		public void begin() {			
			//算式生成代码
			area.setText("");
			CalTest calrandom= new CalTest();
			try {
				calrandom.random();
			} catch (IOException e) {
				e.printStackTrace();
			}
			File file = new File("suanshi.txt");
		    if (file.exists() && file.isFile()) 
		    {
		    	try {
		            BufferedReader input = new BufferedReader(new FileReader(file));
		            String text;
		            while ((text = input.readLine()) != null)
		            	area.setText(area.getText() + text + "\n");
		    	} catch (IOException ioException) {
		    	System.err.println("File Error!");
		        }		
		    }	    
		}
		
		//提交函数
		public void submit() throws IOException {
			FileWriter fw = null;
			try {
				fw = new FileWriter("result.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			String str=area.getText();
			for(int i=0;i<str.length();i++)
			{
				if(str.charAt(i)==10)
				{
					fw.write(13);//写入
			        fw.write(10);//写入
			    }
				else
			    {
					fw.write(str.charAt(i));
			    }
			}
			fw.close();						
			//this.dispose();
			SubFrame subframe= new SubFrame("结果");
		}	
	}
		