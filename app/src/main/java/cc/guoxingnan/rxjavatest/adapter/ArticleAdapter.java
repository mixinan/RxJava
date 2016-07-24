package cc.guoxingnan.rxjavatest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cc.guoxingnan.rxjavatest.R;
import cc.guoxingnan.rxjavatest.entity.Article;

/**
 * Created by mixinan on 2016/7/24.
 */
public class ArticleAdapter extends BaseAdapter {
    private List<Article> data;
    private LayoutInflater inflater;

    public ArticleAdapter(Context context, List<Article> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Article getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_article, null);
            holder.url = (TextView) convertView.findViewById(R.id.tvUrl);
            holder.desc = (TextView) convertView.findViewById(R.id.tvDesc);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();

        Article article = data.get(i);
        holder.desc.setText(article.getDesc());
        holder.url.setText(article.getUrl());

        return convertView;
    }


    class ViewHolder {
        TextView url, desc;
    }
}
