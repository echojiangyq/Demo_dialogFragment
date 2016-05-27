package jyq.demo_dialogfragment;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MyDialogFragment extends DialogFragment {
    private static final String ARG_PARAM1 = "msg";
    private String msg;

    public MyDialogFragment() {
    }

    public static MyDialogFragment newInstance(String msg) {
        MyDialogFragment fragment = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, msg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            msg = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.view_my_progressdialog, null);
        final TextView msgTextView = (TextView) view.findViewById(R.id.tv_msg);
        if(msg.isEmpty()){
            msgTextView.setVisibility(View.GONE);
        }else{
            msgTextView.setText(msg);
        }
        final Dialog dialog = new Dialog(getActivity(), R.style.Custom_Progress);
        dialog.setContentView(view);
        return dialog;
    }
}
