package cn.king.AdapterTool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.king.cn.king.obj.PackageLocalInfo;
import cn.king.com.R;


/**
 * 进程适配器
 */
public class ProcessAdapter extends BaseAdapter {

    private List<PackageLocalInfo> processAdapter = null;
    private LayoutInflater inflater = null;


    /**
     * Constructor
     * @param processAdapter 进程信息集合
     * @param context 上下文
     */
    public ProcessAdapter(List<PackageLocalInfo> processAdapter, Context context) {
        this.processAdapter = processAdapter;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return processAdapter == null ? 0:processAdapter.size();
    }

    @Override
    public PackageLocalInfo getItem(int position) {
        return processAdapter != null ? processAdapter.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PackageLocalInfo adapter =  getItem(position);
        View view;
        if (convertView == null)
        {
            view = inflater.inflate(R.layout.packinfolistitem,null);
        }else
        {
            view = convertView;
        }

        ImageView img = view.findViewById(R.id.packico);
        TextView packName = view.findViewById(R.id.packname);
        TextView version = view.findViewById(R.id.version);
        CheckBox box = view.findViewById(R.id.checked);


        img.setImageDrawable(adapter.getIco());
        packName.setText(adapter.getPackageName());
        version.setText("Vsersion : "+adapter.getVersin());
        box.setChecked(box.isChecked());

        return view;
    }






}
