package com.chuxuezhe.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class ReadYangben extends Thread {
	int[][] cheshiyangben;
	int hangbiao;
	int d[] = new int[32];
	int e[] = new int[32];
	int wenjianjia;
	int wenjian;
	int flag;
	double weight=0;
	double m=0;
	double n=0;
	String s;
	char[] c = new char[32];
	Data juli = new Data();
	int perjuli;
	
	public Data getJuli() {
		return juli;
	}

    public ReadYangben(int[][] cheshiyangben,int flag){
		this.cheshiyangben=cheshiyangben;
		this.flag = flag;
		this.wenjianjia = flag/10;
		this.wenjian = flag%10;
	}
	
	
	public void run() {
		File file = new File(wenjianjia+"/"+wenjian+".txt");
		
		
		if(file.exists()){
			
			
			try {
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
				BufferedReader br = new BufferedReader(isr);
				hangbiao=-1;
				
				while((s = br.readLine())!=null){
					hangbiao++;
					c = s.toCharArray();
					for(int i=0;i<32;i++){
						if('0'==c[i]){
							d[i]=0;
						}
						if('1'==c[i]){
							d[i]=1;
							m++;
						}
					}
					e = cheshiyangben[hangbiao];
					
					for(int i=0;i<32;i++){
						perjuli += Math.sqrt((e[i]-d[i])*(e[i]-d[i]));
						juli.setJuli(perjuli);
					}
				    
				}
				
				for(int i=0;i<32;i++){
					for(int j=0;j<32;j++){
						if(cheshiyangben[i][j]==1){
							n++;

						}
					}
				}
				

//				weight = Math.sqrt(Math.abs(m-n)/1024);
				juli.setJuli(juli.getJuli());
				juli.setIndex(flag);
//				System.out.println("juli="+juli+"|"+"m="+m+"|"+"weight="+weight);
				
				br.close();
				isr.close();
				fis.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
