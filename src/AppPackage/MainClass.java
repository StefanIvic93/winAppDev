package AppPackage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
        
        

public class MainClass 
{
  FileInputStream FIS;
  BufferedInputStream BIS;
  
  public Player player;
  
  public long pauseLocation;
  public long songTotalLenght;
  public String fileLocation;
  
  public void stop()
  {
   if(player != null)
   {
       player.close();
       pauseLocation = 0;
       songTotalLenght = 0;
       
       MP3PlayerGUI.Display.setText("");
   }             
  }
  public void Pause()
  {
   if(player != null)
   {
       try {
           pauseLocation = FIS.available();
           player.close();
       } catch (IOException ex) {
           
       }
       player.close();
   }             
  }
  
  public void Resume()
{
      try {
          FIS = new FileInputStream(fileLocation);
          BIS = new BufferedInputStream(FIS);
          try {
              player = new Player(BIS);
              FIS.skip(songTotalLenght - pauseLocation); 
          } catch (JavaLayerException ex) {
             
          } catch (IOException ex) {
              
          }
           
      } catch (FileNotFoundException ex ) 
      {
    
      }
      
      new Thread()
      {
          @Override
          public void run()
          {
              try {
                  player.play();
              } catch (JavaLayerException ex) {
                  
                 
              }
          }
      }.start();
}
  
public void Play(String path)
{
      try {
          
          FIS = new FileInputStream(path);
          BIS = new BufferedInputStream(FIS);
          try {
              player = new Player(BIS);
             songTotalLenght = FIS.available();
             fileLocation = path + "";
          } catch (JavaLayerException ex) {
             
          } catch (IOException ex) {
          
          }
           
      } catch (FileNotFoundException ex ) 
      {
    
      }
      
      new Thread()
      {
          @Override
          public void run()
          {
              try {
                  player.play();
                  if(player.isComplete() && MP3PlayerGUI.count == 1)
                  {
                      Play(fileLocation);
                  }
              } catch (JavaLayerException ex) {
                  
                 
              }
          }
      }.start();
}
}


