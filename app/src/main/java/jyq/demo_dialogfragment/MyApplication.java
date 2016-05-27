package jyq.demo_dialogfragment;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：EchoJ on 2016/5/27 13:43 <br>
 * 邮箱：echojiangyq@gmail.com <br>
 * 描述：
 */
public class MyApplication extends Application{
    public  static ExecutorService MY_EXECUTOR;
    @Override
    public void onCreate() {
        super.onCreate();
        MY_EXECUTOR = Executors.newCachedThreadPool();
    }
}
