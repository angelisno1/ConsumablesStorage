package com.inteall.consumablesstorage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inteall.consumablesstorage.R;
import com.inteall.consumablesstorage.entity.ApplyForm;

import java.util.List;

/**
 * Created by lyz on 2017/8/31.
 */

public class ApplyFormAdapter extends RecyclerView.Adapter<ApplyFormAdapter.ApplyHolder> {

    private List<ApplyForm> mForms;
    private Context mContext;

    public ApplyFormAdapter(Context context, List<ApplyForm> forms) {
        mContext = context;
        mForms = forms;
    }

    @Override
    public ApplyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.applyform_item, parent, false);
        ApplyHolder applyHolder=new ApplyHolder(view);
        return applyHolder;
    }

    @Override
    public void onBindViewHolder(ApplyHolder holder, int position) {
        holder.tv_applyCode.setText(mForms.get(position).getDepartment_Name());
    }

    @Override
    public int getItemCount() {
        return mForms.size();
    }

    public static class ApplyHolder extends RecyclerView.ViewHolder {

        TextView tv_applyCode;

        public ApplyHolder(View itemView) {
            super(itemView);
            tv_applyCode = (TextView) itemView.findViewById(R.id.tv_apply_code);
        }
    }
}
