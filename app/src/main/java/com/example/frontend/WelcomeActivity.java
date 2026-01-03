package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

/**
 * WelcomeActivity - Professional welcome/splash screen
 * Shows app branding and provides entry point to main app
 */
public class WelcomeActivity extends AppCompatActivity {

    private ImageView ivLogo;
    private TextView tvWelcome, tvAppName, tvSubtitle;
    private MaterialButton btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Hide system bars for immersive experience
        hideSystemUI();

        // Initialize views
        initViews();

        // Start animations
        startAnimations();

        // Set button click listener
        btnGetStarted.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        });
    }

    /**
     * Initialize UI views
     */
    private void initViews() {
        ivLogo = findViewById(R.id.ivLogo);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvAppName = findViewById(R.id.tvAppName);
        tvSubtitle = findViewById(R.id.tvSubtitle);
        btnGetStarted = findViewById(R.id.btnGetStarted);

        // Initially hide elements for animation
        ivLogo.setAlpha(0f);
        tvWelcome.setAlpha(0f);
        tvAppName.setAlpha(0f);
        tvSubtitle.setAlpha(0f);
        btnGetStarted.setAlpha(0f);
    }

    /**
     * Start entrance animations
     */
    private void startAnimations() {
        // Animate logo
        ivLogo.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(800)
                .setStartDelay(200)
                .start();

        // Animate welcome text
        tvWelcome.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(600)
                .setStartDelay(600)
                .start();

        // Animate app name
        tvAppName.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(600)
                .setStartDelay(800)
                .start();

        // Animate subtitle
        tvSubtitle.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(600)
                .setStartDelay(1000)
                .start();

        // Animate button
        btnGetStarted.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(600)
                .setStartDelay(1200)
                .start();
    }

    /**
     * Hide system UI for immersive experience
     */
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
}

