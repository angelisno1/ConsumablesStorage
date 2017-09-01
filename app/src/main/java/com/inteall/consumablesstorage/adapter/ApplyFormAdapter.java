package com.inteall.consumablesstorage.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inteall.consumablesstorage.Listener.RvOnClickItemListener;
import com.inteall.consumablesstorage.R;
import com.inteall.consumablesstorage.entity.ApplyForm;

import java.util.List;

/**
 * Created by lyz on 2017/8/31.
 */

public class ApplyFormAdapter extends RecyclerView.Adapter<ApplyFormAdapter.ApplyHolder> {

    private List<ApplyForm> mForms;
    private Context mContext;
    private RvOnClickItemListener mClickItemListener;

    public ApplyFormAdapter(Context context, List<ApplyForm> forms) {
        mContext = context;
        mForms = forms;
    }

    public void setOnClickItemListener(RvOnClickItemListener clickItemListener) {
        mClickItemListener = clickItemListener;
    }

    @Override
    public ApplyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.applyform_item, parent, false);
        ApplyHolder applyHolder = new ApplyHolder(view);
        return applyHolder;
    }

    @Override
    public void onBindViewHolder(final ApplyHolder holder, final int position) {
        holder.tv_applyCode.setText(mForms.get(position).getDepartment_Name());
    }

    @Override
    public int getItemCount() {
        return mForms.size();
    }

    public  class ApplyHolder extends RecyclerView.ViewHolder {

        TextView tv_applyCode;
        LinearLayout llApplyForm;
        CardView applyCard;
        public ApplyHolder(View itemView) {
            super(itemView);
            tv_applyCode = (TextView) itemView.findViewById(R.id.tv_apply_code);
            llApplyForm= (LinearLayout) itemView.findViewById(R.id.ll_apply_form);
            applyCard= (CardView) itemView.findViewById(R.id.apply_card);
            applyCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickItemListener != null) {
                        mClickItemListener.onClickItemListener(v,getAdapterPosition());
                    }
                }
            });
        }
    }
}
