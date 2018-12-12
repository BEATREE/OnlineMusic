package club.teenshare.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileName {
    public static String getFileName() {
    String fname = null;
    Date date = new Date();    
    SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddhhmmss");   
    fname=sf.format(date);
    fname+=(int)(Math.random()*100);//��2λ�����
    return fname;
 }
    public static String reName(String name) {
    	String type = name.substring(name.lastIndexOf("."));
        String fname = null;
        Date date = new Date();    
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddhhmmss");   
        fname=sf.format(date);
        fname+=(int)(Math.random()*100);//��2λ�����
        fname+=type;
        return fname;
     }
}
