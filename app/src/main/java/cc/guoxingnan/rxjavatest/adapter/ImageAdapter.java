package cc.guoxingnan.rxjavatest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cc.guoxingnan.rxjavatest.R;
import cc.guoxingnan.rxjavatest.entity.Image;

/**
 * Created by mixinan on 2016/7/24.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    private List<Image> data;

    public ImageAdapter(List<Image> data) {
        this.data = data;
    }

    @Override
    public ImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.MyViewHolder holder, int position) {
        Image image = data.get(position);
        Glide.with(holder.itemView.getContext()).load(image.getUrl()).into(holder.iv);
        holder.tv.setText(image.getCreatedAt().substring(0, 10));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_iv);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
