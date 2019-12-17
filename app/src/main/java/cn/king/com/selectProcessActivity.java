package cn.king.com;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.king.AdapterTool.ProcessAdapter;
import cn.king.cn.king.obj.PackageLocalInfo;

public class selectProcessActivity extends AppCompatActivity {
    private String TAG = "PackmanagerAdapter";
    ListView listPack = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectprocessactivity);
        FindSourceID();
    }


    /**
     * 初始化查找ID
     */
    public void FindSourceID() {
        listPack = findViewById(R.id.PackList);
        getAllProcess();
        listPack.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CheckBox ck_Select = (CheckBox) view.findViewById(R.id.checked);
                boolean status = ck_Select.isChecked();

                if (!status) {
                    ck_Select.setChecked(true);
                } else {
                    ck_Select.setChecked(false);
                }

                CharSequence PackName = ((TextView) view.findViewById(R.id.packname)).getText();
                Toast.makeText(selectProcessActivity.this, PackName.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 获取所有安装好的信息
     */
    public void getAllProcess() {
        Application appliction = getApplication();
        Context con = appliction.getApplicationContext();
        PackageManager manager = con.getPackageManager();
        List<PackageInfo> packs = manager.getInstalledPackages(0);
        List<PackageLocalInfo> LocalPack = new ArrayList<>();
        for (PackageInfo info : packs) {
            try {
                String packName = info.packageName;
                Drawable Ico = manager.getApplicationIcon(packName);
                String Version = info.versionName;
                PackageLocalInfo LocalInfo = new PackageLocalInfo();
                LocalInfo.setPackageName(packName);
                LocalInfo.setIco(Ico);
                LocalInfo.setVersin(Version);
                LocalPack.add(LocalInfo);

            } catch (Exception e) {
                Log.i(TAG, "getAllProcess: " + e.getMessage());
            }

        }


        //读取集合到ListView
        ProcessAdapter adapter = new ProcessAdapter(LocalPack, this);
        listPack.setAdapter(adapter);


    }

    /**
     * 获取所有选中的包
     *
     * @return 返回选中的所有包名
     */
    public List<String> GetSelectItem() {

        ListAdapter list = listPack.getAdapter();
        for (int i = 0; i < list.getCount(); i++) {

/*            Adapter localAdapter = (Adapter) list.getItem(i);
            localAdapter.getView();*/

        }

        return null;

    }

}
