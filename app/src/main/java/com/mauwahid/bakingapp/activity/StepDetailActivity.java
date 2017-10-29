package com.mauwahid.bakingapp.activity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mauwahid.bakingapp.R;
import com.mauwahid.bakingapp.model.domain.Step;
import com.mauwahid.bakingapp.utils.DisplayMetricUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Handsome on 10/19/2017.
 */

public class StepDetailActivity extends AppCompatActivity {


    @BindView(R.id.tv_previous_step)
    TextView tvPreviousStep;
    @BindView(R.id.tv_next_step)
    TextView tvNextStep;
    @BindView(R.id.ll_step_navigation)
    LinearLayout llStepNavigation;
    @BindView(R.id.v_horizontal)
    View vHorizontal;

    private ArrayList<Step> steps = new ArrayList<>();
    private int currentStepIndex;
    private StepDetailFragment fragment;
    private Bundle savedInstanceState;
    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Activity", "onCreate");

        this.savedInstanceState = savedInstanceState;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);

        if (getIntent() != null && getIntent().hasExtra("steps") &&
                getIntent().hasExtra("currentStepIndex")) {
            steps = getIntent().getParcelableArrayListExtra("steps");
            if (savedInstanceState == null) {
                currentStepIndex = getIntent().getIntExtra("currentStepIndex", 0);
            } else {
                currentStepIndex = savedInstanceState.getInt("currentStepIndex");
            }
            setNavigationButton(currentStepIndex);
        }

        setupFragment(savedInstanceState, currentStepIndex);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (fragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "stepDetailFragment", fragment);
        }
        outState.putInt("currentStepIndex", currentStepIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        fragment.setOrientation(orientation);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
            llStepNavigation.setVisibility(View.GONE);
            vHorizontal.setVisibility(View.GONE);
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            llStepNavigation.setVisibility(View.VISIBLE);
            vHorizontal.setVisibility(View.VISIBLE);
        }
    }

    private void setNavigationButton(int stepIndex) {
        if (steps.size() > 1) {
            if (stepIndex == 0) {
                tvPreviousStep.setVisibility(View.INVISIBLE);
                tvNextStep.setText(steps.get(stepIndex + 1).getShortDescription());
            } else if (stepIndex == steps.size() - 1) {
                tvNextStep.setVisibility(View.INVISIBLE);
                tvPreviousStep.setText(steps.get(stepIndex - 1).getShortDescription());
            } else {
                tvNextStep.setVisibility(View.VISIBLE);
                tvPreviousStep.setVisibility(View.VISIBLE);
                tvNextStep.setText(steps.get(stepIndex + 1).getShortDescription());
                tvPreviousStep.setText(steps.get(stepIndex - 1).getShortDescription());
            }
        } else {
            tvPreviousStep.setVisibility(View.GONE);
            tvNextStep.setVisibility(View.GONE);
        }
    }

    private void setupFragment(Bundle savedInstanceState, int stepIndex) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(steps.get(stepIndex).getShortDescription());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            if (DisplayMetricUtils.getDeviceOrientation(this) == Configuration.ORIENTATION_LANDSCAPE) {
                getSupportActionBar().hide();
            }
        }

        if (savedInstanceState != null) {
            fragment = (StepDetailFragment) getSupportFragmentManager().getFragment(savedInstanceState, "stepDetailFragment");
        } else {
            fragment = StepDetailFragment.newInstance(steps.get(stepIndex), false);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rl_container, fragment)
                .commit();
    }

    @OnClick(R.id.tv_next_step)
    public void NextStepClick() {
        currentStepIndex = currentStepIndex + 1;
        setupFragment(savedInstanceState, currentStepIndex);
        setNavigationButton(currentStepIndex);
    }

    @OnClick(R.id.tv_previous_step)
    public void PreviousStepClick() {
        currentStepIndex = currentStepIndex - 1;
        setupFragment(savedInstanceState, currentStepIndex);
        setNavigationButton(currentStepIndex);
    }

   @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        orientation = newConfig.orientation;
        onWindowFocusChanged(true);
    }
}
