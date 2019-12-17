package cn.king.cn.king.obj;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.king.NativeTools.ShellUtils;

public class androidInfo {

    /**
     * android_id
     */
    private String android_id;

    /**
     * IMEI
     */
    private String IMEI;

    /**
     * Package List
     */
    private ArrayList<String> Packages;
    /**
     * String Packages
     */
    private String PackagesStr;


    /**
     * 创建android重定向的信息文件
     *
     * @return
     */
    public androidInfo Builder() {

        JSONObject obj = new JSONObject();
        try {
            obj.put("package", GetStringPackage());
            obj.put("android_id", android_id);
            obj.put("IMEI", IMEI);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.PackagesStr = obj.toString();
        return this;
    }

    /**
     * 置入配置信息文件
     *
     * @return
     */
    public boolean InputFile(Context Context) {
        String InfoPath = Context.getFilesDir().getPath();
        File file = new File(InfoPath + "/androidInfo.txt");

        //检测配置文件或创建文件
        if (!file.exists()) {
            try {
                boolean Create =  file.createNewFile();
                if (Create == false)
                {
                    Log.e(staticSource.TAG, "InputFile Create File Fail");
                    return false;
                }
            } catch (IOException e) {
                Log.e(staticSource.TAG, "InputFile Create File Fail Exception : " + e.getMessage());
                return false;
            }
        }

        //写入到自己的目录地址
        try {
            FileWriter write = new FileWriter(file);
            write.write(this.PackagesStr);
            write.close();
        } catch (IOException e) {
            Log.i(staticSource.TAG, "InputFile Write File Content Exception : " + e.getMessage());
        }


        //检测手机root权限
        if (ShellUtils.checkRootPermission()) {
            ShellUtils.CommandResult state = ShellUtils.execCommand(String.format("cp %s data/king/androidInfo.txt", file.getPath()), true);
            if (state.result != 0) {
                Log.i(staticSource.TAG, "InputFile Copy File  Fail : " + state.errorMsg);
                return false;
            }
            state = ShellUtils.execCommand("chmod 777 data/king/androidInfo.txt", true);
            if (state.result != 0) {
                Log.i(staticSource.TAG, "InputFile Chmod  File  Fail : " + state.errorMsg);
                return false;
            }

            return true;

        }

        return false;


    }

    /**
     * 获取文本包名列表
     *
     * @return
     */
    private String GetStringPackage() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Packages.size(); i++) {
            if (i+1 == Packages.size())
            {
                builder.append(Packages.get(i));
            }else
            {
                builder.append(Packages.get(i)+"|");
            }

        }

        return builder.toString();
    }


    //========================================================================== Get And Set========================================
    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public List<String> getPackages() {
        return Packages;
    }

    public void setPackages(ArrayList<String> packages) {
        Packages = packages;
    }


}
