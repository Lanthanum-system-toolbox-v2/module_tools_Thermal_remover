import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import utils.Dialog;
import xzr.La.systemtoolbox.modules.java.LModule;
import xzr.La.systemtoolbox.ui.StandardCard;

public class Thermal_remover implements LModule {
    @Override
    public String classname() {
        //注册到小工具页面
        return "tools";
    }

    @Override
    public View init(Context context) {
        LinearLayout linearLayout=new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = StandardCard.title(context);
        textView.setText("温控删除工具");
        linearLayout.addView(textView);

        TextView textView2 = StandardCard.subtitle(context);
        textView2.setText("移除温控引擎");
        linearLayout.addView(textView2);

        LinearLayout linearLayout2=new LinearLayout(context);
        linearLayout.addView(linearLayout2);


            Button button = new Button(context);
            linearLayout2.addView(button);
            button.setText("详情");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog.init(context).show();
                }
            });
            return linearLayout;
    }

    @Override
    public String onbootapply() {
        //小工具页面根本就没有开机自启 返回命令也没用
        return null;
    }
}
