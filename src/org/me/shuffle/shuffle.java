/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.shuffle;

import java.applet.Applet;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Choice;  //show pop up menu of choices
import java.awt.Color;
import java.awt.Event.*;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.lang.Math;
import java.util.logging.Level;
import java.util.logging.Logger;

public class shuffle extends Applet implements ActionListener{

        Thread t1, t2, t3, t4;
        Label l1,l2,l3,l4,l5,l6,l7,l8;
        TextField t;
        Button yes,no,play1,play2, quit, easy, medium, difficult;
        int score=0,i,randpicker,flag=0;
        String s;
        String str2="";
        List<String> coll_easy=new ArrayList<>();
        List<String> coll_medium=new ArrayList<>();
        List<String> coll_difficult=new ArrayList<>();
        InputStream fis=null;
        InputStream fis2=null;
        InputStreamReader isr=null;
        DataInputStream dis=null;
        BufferedReader br=null;
        BufferedReader br2=null;
        BufferedWriter bw=null;
        OutputStream os=null;
        OutputStreamWriter osw=null;
        List<Integer> num_easy=new ArrayList<Integer>();
        List<Integer> num_medium=new ArrayList<Integer>();
        List<Integer> num_difficult=new ArrayList<Integer>();
        Image pic;
        int n_game = 0, c_game = 0, e_game = 0, m_game = 0, d_game = 0;
        
    public void init() {
            Frame title = (Frame)this.getParent().getParent();
            title.setTitle("SHUFFLE !!!");

            pic = getImage(getDocumentBase(), "icon.png");
            this.setSize(900,600);
            setBackground(Color.yellow);
            l1=new Label();
            l2=new Label();
            l3=new Label();
            l4=new Label();
            l5=new Label();
            l6=new Label();
            l7=new Label();
            l8=new Label();
            t=new TextField();
            yes=new Button("YES");
            no=new Button("NO");
            play1=new Button("PLAY NEW GAME");
            play2=new Button("CONTINUE");
            quit=new Button("QUIT");
            easy=new Button("EASY");
            medium=new Button("MEDIUM");
            difficult=new Button("DIFFICULT");
           
            l1.setForeground(Color.red);
            l2.setForeground(Color.blue);
            l3.setForeground(Color.black);
            l4.setForeground(Color.red);
            l5.setForeground(Color.blue);
            l6.setForeground(Color.blue);
            l7.setForeground(Color.red);
            l8.setForeground(Color.red);
            t.setForeground(Color.blue);
            
            Font myfont1=new Font("Serif",Font.BOLD,12);
            l3.setFont(myfont1);
            l4.setFont(myfont1);
            l5.setFont(myfont1);
            l6.setFont(myfont1);
            l7.setFont(myfont1);
            Font myfont2=new Font("Serif",Font.BOLD,14);
            l1.setFont(myfont2);
            l2.setFont(myfont2);
            l8.setFont(myfont2);
            t.setFont(myfont2);
            
            play1.setBackground(Color.cyan);
            play2.setBackground(Color.magenta);
            yes.setBackground(Color.cyan);
            no.setBackground(Color.pink);
            quit.setBackground(Color.GREEN);
            easy.setBackground(Color.cyan);
            medium.setBackground(Color.magenta);
            difficult.setBackground(Color.GREEN);
            
            l1.setBounds(170, 220, 150, 20); 
            l2.setBounds(50, 300, 230, 20);
            l3.setBounds(50, 338, 200, 20);
            l4.setBounds(50, 220, 250, 20);
            l5.setBounds(50, 265, 250, 20);
            l6.setBounds(50, 220, 150, 20);
            l7.setBounds(50, 260, 150, 20);
            l8.setBounds(290, 300, 150, 20);
            t.setBounds(220, 260, 150, 20);
            yes.setBounds(50, 378, 60, 30);
            no.setBounds(140, 378, 60, 30);
            play1.setBounds(130, 230, 200, 40);
            play2.setBounds(180, 300, 100, 40);
            quit.setBounds(180, 300, 100, 40);
            easy.setBounds(130, 230, 200, 40);
            medium.setBounds(130, 300, 200, 40);
            difficult.setBounds(130, 370, 200, 40);
            
            yes.addActionListener(this);
            no.addActionListener(this);
            play1.addActionListener(this);
            play2.addActionListener(this);
            quit.addActionListener(this);
            easy.addActionListener(this);
            medium.addActionListener(this);
            difficult.addActionListener(this);
            
            t.addActionListener(this);
            add(play1);add(play2);
            /*When you set a layout to null, you tell the container that it is using no layout at all,
              and so you the programmer are thus completely responsible for setting all the sizes and 
              positions of components that are added to this container.*/
            setLayout(null);
            
            //filling easy words
            try{
                /*Java FileInputStream class obtains input bytes from a file. 
                  It is used for reading byte-oriented data (streams of raw bytes)
                  such as image data, audio, video etc. 
                  You can also read character-stream data. */
                fis=new FileInputStream("words_easy.txt");
                
                /*bridge from byte streams to character streams.It reads bytes and 
                  decodes them into characters using a specified charset.*/
                isr=new InputStreamReader(fis);
                
                /*Java BufferedReader class is used to read the text from a character-based 
                  input stream. It can be used to read data line by line by readLine() method. 
                  It makes the performance fast. It inherits Reader class.*/
                br=new BufferedReader(isr);
               
                coll_easy.clear();
                while((s=br.readLine())!=null){
                    String[] words = s.split("\\s");
                    int i=0;
                    int l=words.length;
                    while(l!=0){
                        coll_easy.add(words[i]);
                        i++;
                        l--;
                    }
                }
            }catch(Exception e){}
            
            //filling medium words
            try{
                fis=new FileInputStream("words_medium.txt");
                isr=new InputStreamReader(fis);
                br=new BufferedReader(isr);
               
            coll_medium.clear();
            while((s=br.readLine())!=null){
                String[] words=s.split("\\s");
                int i=0;
                int l=words.length;
                while(l!=0){
                    coll_medium.add(words[i]);
                    i++;
                    l--;
                }
            }
            }catch(Exception e){}
            
            //filling difficult words
            try{
                fis=new FileInputStream("words_difficult.txt");
                isr=new InputStreamReader(fis);
                br=new BufferedReader(isr);
               
            coll_difficult.clear();
            while((s=br.readLine())!=null){
                String[] words=s.split("\\s");
                int i=0;
                int l=words.length;
                while(l!=0){
                    coll_difficult.add(words[i]);
                    i++;
                    l--;
                }
            }
            }catch(Exception e){}
            
            
        for(int j=0;j<150;j++){
            num_easy.add(j);
        }
        for(int j=0;j<150;j++){
            num_medium.add(j);
        }
        for(int j=0;j<150;j++){
            num_difficult.add(j);
        }
    }
    
    public void paint(Graphics g){
        
        g.drawImage(pic, 500, 150, this);
        g.setColor(Color.blue);
        l6.setText("JUMBLED WORD: ");
        l7.setText("CAN YOU CORRECT IT?  ");
        
        t.requestFocusInWindow();
        shuffle sh=new shuffle();
          
        //for easy level
        if(e_game == 1){
            while(true){
                randpicker=(int)(Math.random()*num_easy.size());
                if(randpicker<150) break;
                else continue;
            }
            int x=num_easy.remove(randpicker);
        
            try{
                String n = sh.shuffle(coll_easy.get(x));
                l1.setText(n);
 
                t1=new Thread(){
                    public  void run(){
                        String val= t.getText();
                        if(val.equalsIgnoreCase(coll_easy.get(x))){
                            l2.setText("CORRECT");
                            score++;    
                        }
                        else{
                            l2.setText("INCORRECT.     The correct word is:");
                            add(l8);
                            l8.setText(coll_easy.get(x));
                        }
                    }
                };
        
                t2=new Thread(){
                    public  void run(){
                        l3.setText("DO YOU WANT TO CONTINUE?");
                        add(yes);add(no);        
                    }
                };
            }catch(Exception e){
                System.out.println(x+e.toString());
            }
        
        }
        //for medium level
        else if(m_game == 1){
            while(true){
                randpicker=(int)(Math.random()*num_medium.size());
                if(randpicker<150) break;
                else continue;
            }
            int x=num_medium.remove(randpicker);
        
            try{
                String n = sh.shuffle(coll_medium.get(x));
                l1.setText(n);
 
                t3=new Thread(){
                    public  void run(){
                        String val= t.getText();
                        if(val.equalsIgnoreCase(coll_medium.get(x))){
                            l2.setText("CORRECT");
                            score++;    
                        }
                        else{
                            l2.setText("INCORRECT.     The correct word is:");
                            add(l8);
                            l8.setText(coll_medium.get(x));
                        }
                    }
                };
        
                t2=new Thread(){
                    public  void run(){
                        l3.setText("DO YOU WANT TO CONTINUE?");
                        add(yes);add(no);        
                    }
                };
            }catch(Exception e){
                System.out.println(x+e.toString());
            }
        
        }  
        
         //for difficult level
        else if(d_game == 1){
            while(true){
                randpicker=(int)(Math.random()*num_difficult.size());
                if(randpicker<150) break;
                else continue;
            }
            int x=num_difficult.remove(randpicker);
        
            try{
                String n = sh.shuffle(coll_difficult.get(x));
                l1.setText(n);
 
                t4=new Thread(){
                    public  void run(){
                        String val= t.getText();
                        if(val.equalsIgnoreCase(coll_difficult.get(x))){
                            l2.setText("CORRECT");
                            score++;    
                        }
                        else{
                            l2.setText("INCORRECT.     The correct word is:");
                            add(l8);
                            l8.setText(coll_difficult.get(x));
                        }
                    }
                };
        
                t2=new Thread(){
                    public  void run(){
                        l3.setText("DO YOU WANT TO CONTINUE?");
                        add(yes);add(no);        
                    }
                };
            }catch(Exception e){
                System.out.println(x+e.toString());
            }
        
        }  
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String str4=ae.getSource().toString();
        int len=(str4.toCharArray()).length;
        System.out.println("" + str4);
      
        //NEW GAME button pressed
        if((str4.toCharArray())[9]=='B' && (str4.toCharArray())[14]=='n' && (str4.toCharArray())[len-5]=='G'){
            remove(play1);remove(play2);
            add(easy); add(medium); add(difficult);
            n_game = 1;
            c_game = 0;      
        }
        //CONTINUE button pressed
        else if((str4.toCharArray())[9]=='B' && (str4.toCharArray())[14]=='n' && (str4.toCharArray())[len-9]=='C'){
            remove(play1);remove(play2);
            add(easy); add(medium); add(difficult);
            n_game = 0;
            c_game = 1;
        }
        //EASY button pressed
        else if((str4.toCharArray())[9]=='B' && (str4.toCharArray())[14]=='n' && (str4.toCharArray())[len-5]=='E'){
            e_game = 1;
            m_game = 0;
            d_game = 0;
            if(n_game == 1){
                
                try{
                    os=new FileOutputStream("highestscore_easy.txt");
                    osw=new OutputStreamWriter(os);
                    bw=new BufferedWriter(osw);
                
                    bw.write(0+"");
                    bw.flush();  //flushes this output stream and forces any buffered output bytes to be written out.
                
                    bw.close();
                    osw.close();
                    os.close();
                }catch(Exception ex){
                      System.out.println("problem");
                }    
            
                remove(easy); remove(medium); remove(difficult);
                add(l1);add(l2);add(l3);add(l6);add(l7);
                add(t);
                repaint();
            }
            else{
                remove(easy); remove(medium); remove(difficult);
                add(l1);add(l2);add(l3);add(l6);add(l7);
                add(t);
                repaint();
            }
            
        }
        //MEDIUM button pressed
        else if((str4.toCharArray())[9]=='B' && (str4.toCharArray())[14]=='n' && (str4.toCharArray())[len-7]=='M'){
            e_game = 0;
            m_game = 1;
            d_game = 0;
            if(n_game == 1){
   
                try{
                    os=new FileOutputStream("highestscore_medium.txt");
                    osw=new OutputStreamWriter(os);
                    bw=new BufferedWriter(osw);
                
                    bw.write(0+"");
                    bw.flush();
                
                    bw.close();
                    osw.close();
                    os.close();
                }catch(Exception ex){
                    System.out.println("problem");
                }   
            
                remove(easy); remove(medium); remove(difficult);
                add(l1);add(l2);add(l3);add(l6);add(l7);
                add(t);
                repaint();
            }
            else{
                remove(easy); remove(medium); remove(difficult);
                add(l1);add(l2);add(l3);add(l6);add(l7);
                add(t);
                repaint();
            }   
        }
        ////DIFFICULT button pressed
        else if((str4.toCharArray())[9]=='B' && (str4.toCharArray())[14]=='n' && (str4.toCharArray())[len-10]=='D'){
            e_game = 0;
            m_game = 0;
            d_game = 1;
            if(n_game == 1){
            
                try{
                    os=new FileOutputStream("highestscore_difficult.txt");
                    osw=new OutputStreamWriter(os);
                    bw=new BufferedWriter(osw);
                
                    bw.write(0+"");
                    bw.flush();
                
                    bw.close();
                    osw.close();
                    os.close();
                }catch(Exception ex){
                    System.out.println("problem");
                }
                
                remove(easy); remove(medium); remove(difficult);
                add(l1);add(l2);add(l3);add(l6);add(l7);
                add(t);
                repaint();
            }
            else{
                remove(easy); remove(medium); remove(difficult);
                add(l1);add(l2);add(l3);add(l6);add(l7);
                add(t);
                repaint();
            }
        }
        ////YES button pressed
        else if((str4.toCharArray())[9]=='B' && (str4.toCharArray())[14]=='n' && (str4.toCharArray())[len-4]=='Y'){
            remove(yes);remove(no);remove(l8);l2.setText("");l3.setText("");t.setText("");repaint();
            
        }
        ////NO button pressed
        else if((str4.toCharArray())[9]=='B' && (str4.toCharArray())[14]=='n' && (str4.toCharArray())[len-3]=='N'){
            
            remove(yes);remove(no);remove(l1);remove(l2); remove(l3); remove(t);remove(l6);remove(l7);remove(l8);
            add(l4);add(quit);
            if(e_game == 1)
                l4.setText("YOUR SCORE IN EASY LEVEL IS: "+score);
            if(m_game == 1)
                l4.setText("YOUR SCOREIN MEDIUM LEVEL IS: "+score);
            if(d_game == 1)
                l4.setText("YOUR SCORE IN DIFFICULT LEVEL IS: "+score);
            
            try {
                if(e_game == 1)
                   fis2=new FileInputStream("highestscore_easy.txt");
                else if(m_game == 1)
                   fis2=new FileInputStream("highestscore_medium.txt");
                else if(d_game == 1)
                   fis2=new FileInputStream("highestscore_difficult.txt");
                
                dis=new DataInputStream(fis2);
                String oldscore=dis.readLine();
                
                dis.close();
                fis2.close();
                
                if(e_game == 1)
                   os=new FileOutputStream("highestscore_easy.txt");
                if(m_game == 1)
                   os=new FileOutputStream("highestscore_medium.txt");
                if(d_game == 1)
                   os=new FileOutputStream("highestscore_difficult.txt");
                
                osw=new OutputStreamWriter(os);
                bw=new BufferedWriter(osw);
                
                if(score > Integer.parseInt(oldscore)){
                    bw.write(score+"");
                    bw.flush();
                }
                else{
                    bw.write(oldscore + "");
                    bw.flush();
                } 
                bw.close();
                osw.close();
                os.close();
                
                if(e_game == 1)
                    fis2=new FileInputStream("highestscore_easy.txt");
                if(m_game == 1)
                    fis2=new FileInputStream("highestscore_medium.txt");
                if(d_game == 1)
                    fis2=new FileInputStream("highestscore_difficult.txt");
                
                dis=new DataInputStream(fis2);
                String highScore=dis.readLine();
                
                add(l5);
                
                if(e_game == 1)
                    l5.setText("YOUR BEST IN EASY LEVEL: "+Integer.parseInt(highScore));
                if(m_game == 1)
                    l5.setText("YOUR BEST IN MEDIUM LEVEL: "+Integer.parseInt(highScore));
                if(d_game == 1)
                    l5.setText("YOUR BEST IN DIFFICULT LEVEL: "+Integer.parseInt(highScore));
                
                dis.close();
                fis2.close();
               
                
            } catch (FileNotFoundException ex) {
                System.out.println("file not found.");
            } catch (IOException ex) {
                Logger.getLogger(shuffle.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if((str4.toCharArray())[9]=='T' && (str4.toCharArray())[17]=='d'){
            if(e_game == 1)
                t1.start();
            if(m_game == 1)
                t3.start();
            if(d_game == 1)
                t4.start();
            
            t2.start();
        }
        //QUIT button pressed
        else if((str4.toCharArray())[9]=='B' && (str4.toCharArray())[14]=='n' && (str4.toCharArray())[len-5]=='Q'){
            System.exit(0);
        }
    }
    public String shuffle(String input){
        
        List<Character> characters=new ArrayList<Character>();
        for(char a:input.toCharArray()){
            characters.add(a);   
        }
        while(true){
            str2="";
        Collections.shuffle(characters);
        for(char a:characters){
            str2=str2+a;
        }
        if(str2.equalsIgnoreCase(input)){
            continue;
        }
        else{
            return str2;
         }
        }
        
 }
    
}
