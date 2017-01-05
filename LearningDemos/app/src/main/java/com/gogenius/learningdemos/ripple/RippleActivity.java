package com.gogenius.learningdemos.ripple;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.fastjson.JSONObject;
import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.dialog.SimpleDialog;

import java.text.SimpleDateFormat;

/**
 * Created by shijiwei on 2016/10/14.
 */
public class RippleActivity extends Activity implements SimpleDialog.SimpleDialogClickListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        JSONObject param = new JSONObject();
        param.put("Time", sdf.format(System.currentTimeMillis()));
//        String paramStr = "{\r\n" + "\"Time\":" + "\"" + sdf.format(System.currentTimeMillis()) + "\"" + "\r\n}";
        String paramStr = param.toString();
        paramStr = paramStr.replace("{", "{\r\n").replace("}", "\r\n}");

        new SimpleDialog.Builder(this)
//                .setContent(paramStr)
//                .setCancelText("22")
                .build()
                .setSimpleDialogClickListner(this)
                .show();

    }

    @Override
    public void onConfirmButtonClick(SimpleDialog dialog) {
        dialog.dismiss();
    }

    @Override
    public void onCancelButtonClick(SimpleDialog dialog) {

        dialog.dismiss();
    }
}
