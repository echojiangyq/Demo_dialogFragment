package jyq.demo_dialogfragment;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class VolleyAndRetainFragmentActivity extends AppCompatActivity {
    RetainDataFragment mRetainDataFragment;
    MyDialogFragment  mDialogFragment;
    ImageView mImageView;
    Bitmap mBitmap;
    Context mContext = this;
    RequestQueue mRequestQueue;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_volley_and_retain_fragment);

        final FragmentManager fragmentManager = getFragmentManager();
        mRetainDataFragment = (RetainDataFragment)fragmentManager.findFragmentByTag("DATA");
        mRequestQueue = Volley.newRequestQueue(mContext);
        if(mRetainDataFragment == null){
            mRetainDataFragment = new RetainDataFragment();
            fragmentManager.beginTransaction().add(mRetainDataFragment,"DATA").commit();
        }else{
            mBitmap = mRetainDataFragment.getImgData();
        }
        mDialogFragment = MyDialogFragment.newInstance("Loading...");
        mImageView = (ImageView) findViewById(R.id.iv_img);
        initData();
    }
    
    void initData(){
        if(mBitmap == null){
            processLoadImg();
        }else{
            mImageView.setImageBitmap(mBitmap);
        }
    }
    
    void processLoadImg(){
        mDialogFragment.show(getFragmentManager(),"dialog");
        final ImageRequest imageRequest = new ImageRequest("http://img.my.csdn.net/uploads/201407/18/1405652589_5125.jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                mBitmap = bitmap;
                mImageView.setImageBitmap(bitmap);
                mRetainDataFragment.setImgData(bitmap);
                mDialogFragment.dismiss();
            }
        }, 0, 0, Bitmap.Config.RGB_565, null);
        mRequestQueue.add(imageRequest);
    }
    
    
    
}
