package com.example.demojava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demojava.model.User;

public class DetailActivity extends AppCompatActivity {
    static final String TAG = DetailActivity.class.toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
    }
    void initView() {
        TextView tv_detail = findViewById(R.id.tv_detail);
        Button b_exit_detail = findViewById(R.id.b_exit_detail);

        b_exit_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToFinish();
            }
        });

        User user = (User) getIntent().getSerializableExtra("user");
        Log.d(TAG, user.toString());

        tv_detail.setText(user.toString());
    }

    void backToFinish() {
        Intent intent = new Intent();
        intent.putExtra("result", "Data returned");
        setResult(Activity.RESULT_OK, intent);
        finish();

    }
}
