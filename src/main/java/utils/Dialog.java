/*
 * Copyright (c) 2018 LibXZR
 */

package utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import java.util.ArrayList;

import android.widget.Toast;
import xzr.La.systemtoolbox.utils.process.ShellUtil;

public class Dialog {
    public static AlertDialog init(final Context context){
        String thermals=ShellUtil.run("for i in /system/*/*thermal* /system/*/*/*thermal* /system/*/*/*/*thermal* /system/*/*/*/*/*thermal*\ndo\necho $i\ndone\n",true);
        ArrayList<String> paths=StringToList.to(thermals);
        String result="";
        final ArrayList<String> realpaths=new ArrayList<>();
        for(int i=0;i<paths.size();i++){
            if(!paths.get(i).contains("*thermal*")&&!paths.get(i).contains("android.hardware")){
                realpaths.add(paths.get(i));
            }
        }
        for(int i=0;i<realpaths.size();i++){
            result=result+realpaths.get(i)+"\n";
        }

       return new AlertDialog.Builder(context)
                .setTitle("温控删除工具")
                .setMessage("本工具将会寻找任何温控相关内容并将其移除，如果你已经确定了要移除这些内容，请点击右下角“移除”按钮。建议在进行本操作前做好备份，很有可能会发生各种意外。"+"\n"+
                        "找到的温控："+"\n"+result)
               .setPositiveButton("移除温控", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       String cmd="mount -o rw,remount /system\nmount -o rw,remount /vendor\n";
                       for(int i=0;i<realpaths.size();i++){
                           cmd=cmd+"rm -f "+realpaths.get(i)+"\n";
                       }
                       cmd=cmd+"mount -o ro,remount /system\nmount -o ro,remount /vendor\n";
                       ShellUtil.run(cmd,true);
                       Toast.makeText(context,"完成！",Toast.LENGTH_LONG).show();
                   }
               })
               .setNegativeButton("取消",null)
               .create();
    }
}
