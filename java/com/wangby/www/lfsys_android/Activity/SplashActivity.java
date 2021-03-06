package com.wangby.www.lfsys_android.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.SqlTool;
import com.wangby.www.lfsys_android.connect.Function;
import com.wangby.www.lfsys_android.connect.Post;
import com.wangby.www.lfsys_android.connect.User;

import java.util.List;

public class SplashActivity extends Activity {
    Context mContext;
    private SqlTool sqlTool;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sqlTool = new SqlTool(this);
        mContext = this;
        final User user = sqlTool.getUser();
        if(user!=null){
            Confing.LOGIN_STATE = true;
            Confing.user = user;
//            new Thread(new Runnable() {
//                public void run() {
//                    User result_user = Function.login(user);
//                    if (result_user!=null){
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(mContext, "登陆成功", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        Confing.LOGIN_STATE = true;
//                        Confing.user = result_user;
//                    }else {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(mContext, "登陆失败", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        Confing.LOGIN_STATE = false;
//                    }
//                }}).start();
        }

        new Thread(new Runnable() {
            public void run() {
                List<Post> list=null;
                list = Function.showLost();
                if(list !=null) {
                    sqlTool.delect("lost");
                    sqlTool.saveGoods(list, "lost");
                }
                List<Post> listf=null;

                listf = Function.showFound();
                if(listf !=null) {
                    sqlTool.delect("Found");
                    sqlTool.saveGoods(listf, "Found");
                }

            }
        }).start();


        enterHome();
    }



    private void enterHome() {
        getWindow().getDecorView().postDelayed(new Runnable() {
            public void run()
            {
                SplashActivity.this.finish();
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, HomeActivity.class));}
        }, 1000L);
    }


}
