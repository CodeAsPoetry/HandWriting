package com.chuxuezhe.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class WritingBoard extends JFrame {
	private Data[] juli = new Data[100];
	private int[] sort= new int[100];
	private int[][] grap01 = new int[32][32];
	private JPanel jpanel_north = new JPanel();
	private JPanel jpanel_center = new JPanel();
	private JButton jbu = new JButton("Identify");
	private Graphics g;

	public static void main(String[] args) {
		WritingBoard wb = new WritingBoard();
		wb.creatUI();
		
		
	}

	public void creatUI(){
		this.setTitle("Writing Board");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(3);
		jpanel_north.setLayout(null);
		jpanel_north.setPreferredSize(new Dimension(500,50));
		jbu.setBounds(150,20,100,30);
		jpanel_north.add(jbu);
		jbu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				play();
				



			}
		});
		
		this.add(jpanel_center,BorderLayout.CENTER);
		this.add(jpanel_north,BorderLayout.NORTH);
		
		this.setVisible(true);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		g = jpanel_center.getGraphics();
		draw();
		

		MouseMotionListener listener1 = new MouseMotionListener() {


			public void mouseMoved(MouseEvent e) {
			}


			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				if(x>80&&x<400&&y>80&&y<400){
					g.setColor(Color.black);
					g.fillRect(10*(x/10), 10*(y/10), 10, 10);
					grap01[(y-80)/10][(x-80)/10] = 1;
				}


			}
		};

		MouseListener listener = new MouseListener() {



			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				if(x>80&&x<400&&y>80&&y<400){
					g.setColor(Color.black);
					g.fillRect(10*(x/10), 10*(y/10), 10, 10);
					grap01[(y-80)/10][(x-80)/10] = 1;
				}
			}

			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				if(x>80&&x<400&&y>80&&y<400){
					g.setColor(Color.black);
					g.fillRect(10*(x/10), 10*(y/10), 10, 10);
					grap01[(y-80)/10][(x-80)/10] = 1;
				}
				
				
				

			}
		};

		jpanel_center.addMouseListener(listener);
		jpanel_center.addMouseMotionListener(listener1);
	}

	public void draw(){	
		g.setColor(Color.red);
		for(int i=80;i<=400;i+=10){
			g.drawLine(i, 80, i, 400);
			
		}
		for(int j=80;j<=400;j+=10){
			g.drawLine(80, j, 400, j);
		}

	}


    public void Print01(){
    	for(int i=0;i<32;i++){
    		for(int j=0;j<32;j++){
    			System.out.print(grap01[i][j]+" ");
    		}
    		System.out.println();
    	}
    }

    
	public void play(){
    	
    	
    	int[][] yangben = new int[32][32];
    	for(int i=0;i<32;i++){
	    	for(int j=0;j<32;j++){
	    		yangben[i][j]=grap01[i][j];
	    	}
	    }
    	
    	//创建训练样本
//    	System.out.println(123);
//    	CreateYangben yangbenfile = new CreateYangben(yangben);
//    	yangbenfile.start();
    	
    	//测试
    	ReadYangben yangbenfile[] = new ReadYangben[100];    	
    	
    	for(int i=0;i<100;i++){
    		yangbenfile[i] = new ReadYangben(yangben,  i);
    		yangbenfile[i].start();
    		try {
				yangbenfile[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    	for(int i=0;i<100;i++){
    		sort[i] = i;
    		juli[i] = yangbenfile[i].getJuli();	
    	}
    	for(int i=0;i<100;i++){
    		for(int j=i+1;j<100;j++){
    			if(juli[i].getJuli()>juli[j].getJuli()){
    				Data temp = juli[i];
    				juli[i] = juli[j];
    				juli[j] = temp;
    			}
    		}
    	}
    	
    	
    
   	
    	for(int i=0;i<25;i++){
    		System.out.println(juli[i].getIndex()/10+"|"+juli[i].getJuli());
    	}
    	
    	//K=10
    	int[] n = {0,0,0,0,0,0,0,0,0,0};
    	for(int i=0;i<10;i++){
    		//System.out.println(sort[i]/10+"|"+juli[sort[i]]);
    		switch (juli[i].getIndex()/10) {
    		case 0:
    			n[0]+=9;
    		case 1:
    			n[1]++;
    			break;
    		case 2:
    			n[2]+=9;
    			break;
    		case 3:
    			n[3]+=9;
    			break;
    		case 4:
    			n[4]+=9;
    			break;
    		case 5:
    			n[5]+=9;
    			break;
    		case 6:
    			n[6]+=9;
    			break;
    		case 7:
    			n[7]+=2;
    			break;
    		case 8:
    			n[8]+=9;
    			break;
    		case 9:	
    			n[9]+=9;
    			break;

    		}
    	}
    	
    	int max=0;
    	for(int i=0;i<10;i++){
    		
    		if(n[i]>max){
    			max=i;
    		}
    	}
    	String s = Integer.toString(max);
    	JOptionPane.showMessageDialog(null, s, "是否继续？", JOptionPane.YES_NO_OPTION);
    	System.out.println(max);
    	
    	
    	
    	

    	
    	

    }

    
}
