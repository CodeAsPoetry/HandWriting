package com.chuxuezhe.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class CreateYangben extends Thread {
	File file;
	FileOutputStream fos = null;
	OutputStreamWriter osw = null;
	BufferedWriter bw = null;
	int[][] yangben;
	
	public void run() {
		
		
		Random random = new Random();
		int x=Math.abs(random.nextInt()*1000)+10;
		file = new File("0/"+x+".txt");
		

		if(!file.exists()){
			try {
				file.createNewFile();
				fos = new FileOutputStream(file);
				osw = new OutputStreamWriter(fos,"UTF-8");
				bw = new BufferedWriter(osw);
				for(int i=0;i<32;i++){
					for(int j=0;j<32;j++){
						if(yangben[i][j]==0){
							bw.write("0");
						}
						if(yangben[i][j]==1){
							bw.write("1");
						}
			
					}
					
					bw.write('\n');
				}
				

				bw.close();
				osw.close();
				fos.close();
					
				
				
				



			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public CreateYangben(int[][] yangben){
		this.yangben=yangben;
	}

}
