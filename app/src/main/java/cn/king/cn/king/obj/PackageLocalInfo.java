package cn.king.cn.king.obj;

import android.graphics.drawable.Drawable;


/**
 * 包信息管理类
 */
public class PackageLocalInfo {

    /**
     * 包名
     */
    private String PackageName;
    /**
     * 程序的ico图标
     */
    private Drawable Ico;
    /**
     * 程序版本号
     */
    private String Versin;


    /**
     *
     * 是否启动修改
     *
     */
    private boolean IsChecked;



    public PackageLocalInfo() {

    }


    public String getVersin() {
        return Versin;
    }

    public void setVersin(String versin) {
        Versin = versin;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public Drawable getIco() {
        return Ico;
    }

    public void setIco(Drawable ico) {
        Ico = ico;
    }

    public boolean isChecked() {
        return IsChecked;
    }

    public void setChecked(boolean checked) {
        IsChecked = checked;
    }


}
