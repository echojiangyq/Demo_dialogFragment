package jyq.demo_dialogfragment;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class RetainDataFragment extends Fragment {
    private Bitmap imgData;

    public Bitmap getImgData() {
        return imgData;
    }

    public void setImgData(Bitmap imgData) {
        this.imgData = imgData;
    }

    /** this fragment only instance once*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
