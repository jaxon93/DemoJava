package com.example.demojava;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demojava.model.User;

public class MainActivity extends AppCompatActivity {
    static final String TAG = DetailActivity.class.toString();
    //int LAUNCH_DETAIL = 1001; for old version
    TextView tv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    ActivityResultLauncher<Intent> detailLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String text = data.getStringExtra("result");
                        Log.d(TAG, text);
                        tv_main.setText(text);
                    }
                }
            }
    );

    protected void initView() {
        tv_main = findViewById(R.id.tv_main);
        Button b_open_detail = findViewById(R.id.b_open_detail);
        b_open_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(11, "PDP Academy");
                openDetailActivity(user);
            }
        });
    }
    void openDetailActivity(User user) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("user", user);
        // startActivity(intent); we don't use this method, when we get result from new activity
        detailLauncher.launch(intent);

    }
}