package jyq.demo_dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class LoginInFragment extends DialogFragment {
    private static final String ARG_PARAM_USERNAME = "USERNAME";
    private static final String ARG_PARAM_PASSWORD = "PASSWORD";

    private String mParamUsername;
    private String mParamPassword;

    private OnLoginListener mListener;
    private final String TAG = "TestDialogFragment";

    public LoginInFragment() {
        // Required empty public constructor
    }

    /**
     * @param username Parameter 1.
     * @param password Parameter 2.
     * @return A new instance of fragment LoginInFragment.
     */
    public static LoginInFragment newInstance(String username, String password) {
        LoginInFragment fragment = new LoginInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_USERNAME, username);
        args.putString(ARG_PARAM_PASSWORD, password);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logd("onCreate");
        if (getArguments() != null) {
            mParamUsername = getArguments().getString(ARG_PARAM_USERNAME);
            mParamPassword = getArguments().getString(ARG_PARAM_PASSWORD);
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        logd("onCreateDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); 
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_login_in, null);
        final EditText name = (EditText) view.findViewById(R.id.et_username);
        final EditText pwd = (EditText) view.findViewById(R.id.et_pwd);
        builder.setView(view).setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mListener != null) {
                    mListener.onLogin(name.getText().toString(), pwd.getText().toString());
                }
            }
        }).setNegativeButton("Cancel", null);
     return builder.create();  
    }

    @Override
    public void onAttach(Context context) {
        logd("onAttach");
        super.onAttach(context);
        if (context instanceof OnLoginListener) {
            mListener = (OnLoginListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnLoginListener");
        }
    }

    @Override
    public void onDetach() {
        logd("onDetach");
        super.onDetach();
        mListener = null;
    }
    
    public interface OnLoginListener {
        void onLogin(String name, String pwd);
    }
    
    private void logd(String msg){
        Log.d(TAG, msg);
    }
}
