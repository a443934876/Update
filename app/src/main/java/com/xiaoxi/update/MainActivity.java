package com.xiaoxi.update;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xiaoxi.update.version.DownloadIntentService;


import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private String mVersionName = "2.0.14";
    //    private String mDownloadUrl = "http://download.sharejoy.com" +
//            ".cn/8AT54G3R/86T52EBL/86W52EBS/CMPP_Lakala_Release_2.0.14.apk";
    private String mDownloadUrl = "http://imtt.dd.qq.com/16891/2FD2E21FB312CFF4E8E5195A88FF24C1.apk?fsname=com" +
            ".ishangbin.shop_2.0.12_26.apk&csr=1bbd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {


                String[] key = {"packageid", "comid"};
                Object[] value = {30, 6};
                ArrayList<HashMap<String, Object>> getVision = WebServiceUtil.getWebServiceMsg(key, value,
                        "getNewPackageVersion", new String[]{"ver"}, WebServiceUtil.HUIWEI_PM_URL,
                        WebServiceUtil.HUIWEI_PM_NAMESPACE);
                String ver = getVision.get(0).get("ver").toString();
                e.onNext(ver);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.e("TAG", "value: " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * IntentService现在
     *
     * @param view
     */
    public void updateIntentService(View view) {
        Toast.makeText(this, "开始下载", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DownloadIntentService.class);
        intent.putExtra(DownloadIntentService.INTENT_VERSION_NAME, mVersionName);
        intent.putExtra(DownloadIntentService.INTENT_DOWNLOAD_URL, mDownloadUrl);
        startService(intent);
    }




}
