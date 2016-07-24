package cc.guoxingnan.rxjavatest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cc.guoxingnan.rxjavatest.R;
import cc.guoxingnan.rxjavatest.entity.AppInfo;

public class AppAdapter extends BaseAdapter {
    private List<AppInfo> data;
    private LayoutInflater inflater;

    public AppAdapter(Context context, List<AppInfo> data) {
        super();
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public AppInfo getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_app, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.icon);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.size = (TextView) convertView.findViewById(R.id.size);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();

        AppInfo app = getItem(position);

        holder.image.setImageDrawable(app.getImage());
        holder.title.setText(app.getName());
        holder.size.setText(scaleDouble(app.getSize()));
        holder.time.setText(timeFormat(app.getTime()));

        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView title;
        TextView time;
        TextView size;
    }

    /**
     * 保留2位小数
     */
    private String scaleDouble(String s) {
        double d = Double.parseDouble(s);
        BigDecimal bd = BigDecimal.valueOf(d);
        String size = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + " MB";
        return size;
    }

    /**
     * 毫秒值的格式转化
     */
    private String timeFormat(String s) {
        long timeMillis = Long.parseLong(s);
        Date date = new Date(timeMillis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        String time = sdf.format(date);
        return "安装时间：" + time;
    }


}
