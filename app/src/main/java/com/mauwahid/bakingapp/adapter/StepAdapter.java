package com.mauwahid.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mauwahid.bakingapp.R;
import com.mauwahid.bakingapp.model.domain.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Handsome on 10/15/2017.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private ArrayList<Step> steps;
    private StepClickListener clickListener;

    public StepAdapter(ArrayList<Step> steps, StepClickListener clickListener) {
        this.steps = steps;
        this.clickListener = clickListener;
    }

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_step, parent, false);

        return new StepAdapter.StepViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        Step item = steps.get(position);
        if (position == 0) {
            holder.vStepConnectorTop.setVisibility(View.INVISIBLE);
            holder.vStepConnectorBottom.setVisibility(View.VISIBLE);
        } else if (position < getItemCount() - 1) {
            holder.vStepConnectorTop.setVisibility(View.VISIBLE);
            holder.vStepConnectorBottom.setVisibility(View.VISIBLE);
        } else { // Last
            holder.vStepConnectorTop.setVisibility(View.VISIBLE);
            holder.vStepConnectorBottom.setVisibility(View.INVISIBLE);
        }
        holder.tvStepShortDescription.setText(item.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public interface StepClickListener {
        void onStepItemClick(Step step);
    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.ll_step)
        LinearLayout llStep;
        @BindView(R.id.tv_step_short_description)
        TextView tvStepShortDescription;
        @BindView(R.id.v_step_connector_top)
        View vStepConnectorTop;
        @BindView(R.id.v_step_connector_bottom)
        View vStepConnectorBottom;

        StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            tvStepShortDescription.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onStepItemClick(steps.get(getAdapterPosition()));
        }
    }
}
