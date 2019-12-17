package cn.king.NativeTools;

import android.content.ContentProvider;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class inputLibrary {
    public static String TAG = "King";
    private static String basePath = "/data/king/";


    /**
     * 初始化设备目录
     */
    private static void CheckDir() {

        File baseDir = new File(basePath);
        if (!baseDir.exists()) {
            baseDir.mkdir();
        }
    }


    /**
     * 置入so库文件
     */
    public static void InputLibrary(Context con) throws InterruptedException {
        final  Context loc = con;
        File ak = new File("data/king/libAK.so");
        File NativeHook = new File("data/king/libNativeHook.so");
        File AKCompat = new File("data/king/libAKCompat.so");

        if (!ak.exists() || !NativeHook.exists() || !AKCompat.exists())
        {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    InputFileToLocal(loc);
                }
            });
            th.start();
            while( th.getState().equals(Thread.State.RUNNABLE))
            {
                Thread.sleep(1000);
            }

        }

        Log.i(TAG, "out Put File Success");



        //检测root权限
        boolean RootState = ShellUtils.checkRootPermission();
        if (!RootState) {
            Log.i(TAG, "Not Found Root Permission");
            return;
        }


        //so文件路径
        ShellUtils.CommandResult state;

        //检测路径是否存在
        if (!new File(basePath).exists()) {
            state = ShellUtils.execCommand("mkdir /data/king/", true);
            if (state.result != 0) {
                Log.i(TAG, "Create folder Failure...");
                return;
            }
        }

        //检测libAK.so文件是否存在
        if (!ak.exists()) {
            state = ShellUtils.execCommand(String.format("cp %s /data/king/libAK.so",con.getFilesDir()+"/libAK.so"), true);
            if (state.result != 0) {
                Log.i(TAG, "Create libAK File Failure...");
            }else
            {
                Log.i(TAG, "copy libAK File Success");
            }

            state = ShellUtils.execCommand("chmod 777 /data/king/libAK.so", true);
            if (state.result != 0) {
                Log.i(TAG, "chmod libAK success ");
            }else
            {
                Log.i(TAG, "chmod libAK Failure ");
            }
        }

        //检测libNativeHook.so文件是否存在
        if (!NativeHook.exists()) {
            state = ShellUtils.execCommand(String.format("cp %s /data/king/libNativeHook.so",con.getFilesDir()+"/libNativeHook.so"), true);
            if (state.result != 0) {
                Log.i(TAG, "Create libNativeHook File Failure...");
                return;
            }else
            {
                Log.i(TAG, "copy libNativeHook File Success");
            }

            state = ShellUtils.execCommand("chmod 777 /data/king/libNativeHook.so", true);
            if (state.result != 0) {
                Log.i(TAG, "chmod libNativeHook success ");
            }else
            {
                Log.i(TAG, "chmod libNativeHook Failure ");
            }

        }

        //检测libAKCompat.so文件是否存在
        if (!AKCompat.exists()) {
            state = ShellUtils.execCommand(String.format("cp %s /data/king/libAKCompat.so",con.getFilesDir()+"/libAKCompat.so"), true);
            if (state.result != 0) {
                Log.i(TAG, "Create libNativeHook File Failure...");
                return;
            }else
            {
                Log.i(TAG, "copy libNativeHook File Success");
            }

            state = ShellUtils.execCommand("chmod 777 /data/king/libAKCompat.so", true);
            if (state.result != 0) {
                Log.i(TAG, "chmod libAKCompat success ");
            }else
            {
                Log.i(TAG, "chmod libAKCompat Failure ");
            }

        }





    }


    /**
     * 将so库字节码写入到文件
     *
     * @param out   文件输出流
     * @param input 输入的数据
     */
    private static void WriteFileContent(FileOutputStream out, InputStream input) {
        byte[] buf = new byte[1024];
        int len = 0;

        while (true) {
            try {
                len = input.read(buf);
                if (len > 0) {
                    out.write(buf, 0, len);
                }else
                {
                    input.close();
                    out.close();
                    return;
                }

            } catch (IOException e) {
                Log.e(TAG, "InputLibrary WriteFileContent Error: " + e.getMessage());
            }
        }


    }


    /**
     *
     * 置入so文件到自己根目录
     * @param con
     */
    private static String InputFileToLocal(Context con) {

        AssetManager manager = con.getAssets();
        String CurrentFilePath = con.getFilesDir().getPath();
        File ak = new File(CurrentFilePath+"/libAK.so");
        File NativeHook = new File(CurrentFilePath+"/libNativeHook.so");
        File AKCompat = new File(CurrentFilePath+"/libAKCompat.so");

        try {
            boolean akStatus =  ak.createNewFile();
            boolean NativeHookStatus  = NativeHook.createNewFile();
            boolean AKCompatStatus  = AKCompat.createNewFile();
            if (!akStatus || !NativeHookStatus || !AKCompatStatus)
            {
                Log.i(TAG, "Create so File is Failure");
            }
            InputStream stream = manager.open("libAK.so");
            FileOutputStream akstream = new FileOutputStream(ak);
            WriteFileContent(akstream ,stream);

            InputStream Nativestream = manager.open("libNativeHook.so");
            WriteFileContent( new FileOutputStream(NativeHook),Nativestream);

            InputStream AKCompatstream = manager.open("libAKCompat.so");
            WriteFileContent( new FileOutputStream(AKCompat),AKCompatstream);

            return CurrentFilePath;
        } catch (IOException e) {
            Log.i(TAG, "Create so File is Exception " + e.getMessage());
            return null;
        }

    }

}
