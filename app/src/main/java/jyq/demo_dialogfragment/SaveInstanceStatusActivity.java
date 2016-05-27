package jyq.demo_dialogfragment;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
public class SaveInstanceStatusActivity extends ListActivity {

    private ArrayList<String> mDataArray = new ArrayList<String>();
    
    private ListAdapter  mListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("echo","onCreate");
        setContentView(R.layout.activity_save_instance_status);
        initData(savedInstanceState);
    }

    private void initData(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            mDataArray = savedInstanceState.getStringArrayList("FAKE_DATA");
            initAdapter();
        }else{
            if(mDataArray==null || mDataArray.size()==0){
                new MyLoadDataAsynkTask().executeOnExecutor(((MyApplication)getApplication()).MY_EXECUTOR);
            }else{
                initAdapter();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("echo","onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("echo","onSaveInstance");
        if(mDataArray != null && mDataArray.size() != 0){
            outState.putStringArrayList("FAKE_DATA", mDataArray);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        Log.e("echo","onRestoreInstanceState");
        super.onRestoreInstanceState(state);
    }

    private void processLoadingData(){
        SystemClock.sleep(3000);
        mDataArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.fake_data)));
    }
    
    class MyLoadDataAsynkTask extends AsyncTask<Void, Void, Void>{
//      ProgressDialog mProgressDialog  = ProgressDialog.show(SaveInstanceStatusActivity.this, "Tip","Loading...",true, true);
//        Dialog mProgressDialog = MyProgressDialog.createDialog(SaveInstanceStatusActivity.this, "Loading...");
       MyDialogFragment mProgressDialog = MyDialogFragment.newInstance("Loading...");
        @Override
        protected void onPreExecute() {
//            mProgressDialog.show();
            mProgressDialog.show(getFragmentManager(),"Loading");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            processLoadingData();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(SaveInstanceStatusActivity.this.isFinishing())return;
            //mProgressDialog.cancel();
            mProgressDialog.dismiss();
            initAdapter();
            super.onPostExecute(aVoid);
        }
    }

    private void initAdapter() {
        mListAdapter = new ArrayAdapter<String>(SaveInstanceStatusActivity.this, android.R.layout.simple_list_item_single_choice,mDataArray);
        setListAdapter(mListAdapter);
    }
    
    public void myOnClick(View v){
        switch (v.getId()){
            case R.id.bt_show_choosed_item:
                final int checkedItemPosition = getListView().getCheckedItemPosition();
                if(checkedItemPosition>=0){
                    Toast.makeText(SaveInstanceStatusActivity.this,mDataArray.get(checkedItemPosition),Toast.LENGTH_SHORT).show();
                }
                break;
            
            default:
        }
    }
    
}
