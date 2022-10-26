package homework2.provider;
import javax.jws.WebService;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.lang.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import java.awt.List;
@WebService
public class AccessTools{
   
    public boolean reboot(){
        try{
            Runtime runtime = Runtime.getRuntime();
            String opsName = System.getProperty("os.name").toLowerCase();
            if(opsName.contains("windows")){
                runtime.exec("shutdown -r -t 0");
            }else if(opsName.contains("linux") || opsName.contains("mac os x")){
                runtime.exec("shutdown -r now"); 
            }
            return true;
        } catch(Exception e){
            return false;
        }
    }
    public byte[] getScreenShot(){
        try{
            Rectangle myRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage myCapture = new Robot().createScreenCapture(myRectangle);

            ByteArrayOutputStream myBaos = new ByteArrayOutputStream();

            ImageIO.write(myCapture, "png", myBaos );

            byte[] bytes = myBaos.toByteArray();
            
            return bytes;
        }catch(Exception e){
            return null;
        }
    }

    // for getRunningProcess, I did not find away how to return all running process, so I returned in an array
    // From an implementation prespective, I know this is not the perfect way
    public String[] getRunningProcess(){

        try {
            String line;
            Process p = Runtime.getRuntime().exec("ps -e");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

                    String result[] = new String[1000];
                    int i = 0;
            while ((line = input.readLine()) != null) {
                result[i] = line;
                i++;
            }
            return result;
        } catch (Exception err) {
            return null;
        }

    }
 
}