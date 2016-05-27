package jyq.demo_dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 作者：EchoJ on 2016/5/27 15:15 <br>
 * 邮箱：echojiangyq@gmail.com <br>
 * 描述：
 */
public class MyProgressDialog extends Dialog {
    public MyProgressDialog(Context context) {
        super(context);
    }
    
    public static Dialog createDialog(Context mContext, String msg, boolean cancelable, OnCancelListener onCancelListener){
        final Dialog dialog = new Dialog(mContext,R.style.Custom_Progress);
        dialog.setTitle("");
        dialog.setContentView(R.layout.view_my_progressdialog);
        final TextView msgTextView = (TextView) dialog.findViewById(R.id.tv_msg);

        if(msg.isEmpty()){
            msgTextView.setVisibility(View.GONE);
        }else{
            msgTextView.setText(msg);
        }
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(onCancelListener);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);
        return dialog;
    }

    public static Dialog createDialog(Context mContext, String msg){
        return createDialog(mContext,msg,false,null);
    }
}
