package utils;

import java.util.ArrayList;

/**
 * Created by xzr on 2018/3/10.
 */

public class StringToList {
    public static ArrayList<String> to(String s){
        ArrayList<String> a=new ArrayList<>();
        String buffer="";
        int i=0;
        while (true){
            try {
                String c = s.substring(i, i + 1);
                if (c.equals("\n")) {
                    a.add(buffer);
                    buffer = "";
                } else {
                    buffer = buffer+c;
                }
            }
            catch (Exception e){
                a.add(buffer);
                break;
            }
            i++;
        }
        return a;
    }
}
