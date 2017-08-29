package com.inteall.consumablesstorage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inteall.consumablesstorage.Listener.RvOnClickItemListener;
import com.inteall.consumablesstorage.R;
import com.inteall.consumablesstorage.entity.MainMenu;

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {

    private List<MainMenu> mainMenus;
    private Context mContext;
    private RvOnClickItemListener mClickItemListener;

    public MainMenuAdapter(Context context, List<MainMenu> menus) {
        mainMenus = menus;
        mContext = context;
    }

    public void setOnItemClickListener(RvOnClickItemListener onClickItemListener) {
        mClickItemListener = onClickItemListener;
    }

    // 重写的自定义ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTvMenu;
        LinearLayout mllStorage;

        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.item_iv);
            mTvMenu = (TextView) v.findViewById(R.id.tv_menuTitle);
            mllStorage = (LinearLayout) v.findViewById(R.id.ll_Storage);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mImageView.setImageResource(mainMenus.get(position).getImageSrc());
        holder.mTvMenu.setText(mainMenus.get(position).getTitle());
        if (mClickItemListener != null){
            holder.mllStorage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickItemListener.onClickItemListener(holder.mllStorage,position);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mainMenus.size();
    }
}
