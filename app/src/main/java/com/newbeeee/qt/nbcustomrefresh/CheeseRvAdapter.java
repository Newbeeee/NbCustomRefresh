package com.newbeeee.qt.nbcustomrefresh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by xiuxiongding on 2017/4/25.
 */

public class CheeseRvAdapter extends RecyclerView.Adapter<CheeseRvAdapter.RecyclerItemViewHolder>{

    List<String> mValues;

    public CheeseRvAdapter(List<String> items) {
        this.mValues = items;
    }

    @Override
    public RecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cheese_list, parent, false);
        return new RecyclerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerItemViewHolder holder, int position) {
        holder.mTextView.setText(mValues.get(position));
        Glide.with(holder.mImageView.getContext())
                .load(Cheeses.getRandomCheeseDrawable())
                .fitCenter()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    static class RecyclerItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        public RecyclerItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.nb_img);
            mTextView = (TextView) itemView.findViewById(R.id.nb_tv);
        }
    }
}
