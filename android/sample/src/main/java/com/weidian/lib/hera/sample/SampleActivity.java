package com.weidian.lib.hera.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.weidian.lib.hera.main.HeraService;
import com.weidian.lib.hera.sample.web.WebActivity;
import com.weidian.lib.hera.utils.StorageUtil;

import java.io.File;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity);

        findViewById(R.id.enter_hera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appId = "mgtv-app";//小程序的id
                launchHome(appId);
            }
        });

        findViewById(R.id.tv_h5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWeb();
            }
        });

        findViewById(R.id.tv_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appId = "demoapp";
                launchHome(appId);
            }
        });
    }

    /**
     * 启动小程序首页
     * @param appId
     */
    private void launchHome(String appId) {
        //小程序的本地存储路径
        //sdk内部会首先读取并解压appPath下的小程序包，若appPath为空，则读取并解压assets下以appId命名的zip文件
        //小程序解压后将存储在以appId命名的文件夹下
        final String userId = "123";//标识宿主App业务用户id
        final String appPath = getMiniAppPath(this, appId);
        HeraService.launchHome(getApplicationContext(), userId, appId, appPath);
    }

    /**
     * 加载web
     */
    private void launchWeb() {
        WebActivity.start(this, WebActivity.URL_MGTV);
    }

    /**
     * 获取指定的小程序目录
     *
     * @param context 上下文
     * @param appId   小程序id
     * @return 指定小程序id的目录文件
     */
    public static String getMiniAppPath(Context context, String appId) {
        File miniAppDir = new File(StorageUtil.getHeraAppPath(context), appId);
        if (!miniAppDir.exists() || !miniAppDir.isDirectory()) {
            return null;
        }
        return miniAppDir.getAbsolutePath();
    }
}
