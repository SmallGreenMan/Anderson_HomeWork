package android.examample.imageloader_hw_31.HomeWorck_2;

import androidx.appcompat.app.AppCompatActivity;

import android.examample.imageloader_hw_31.R;
import android.examample.imageloader_hw_31.databinding.ActivityMainHw21Binding;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_HW_2_1 extends AppCompatActivity {
    private int mCount;
    private TextView mShowCount;
    private static final String LOG_TAG = MainActivity_HW_2_1.class.getSimpleName();
    private static final String MCONUN = "MCONUN";

    private ActivityMainHw21Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "onCreate");

        binding = ActivityMainHw21Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mShowCount = (TextView) findViewById(R.id.show_count);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt(MCONUN);
        } else {
            mCount = 0;
        }
        showMCountToUser();


        binding.buttonZero.setOnClickListener(v -> {
            mCount = 0;
            showMCountToUser();
        });

        binding.toolBarToAst.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    private void showMCountToUser(){
        mShowCount.setText(Integer.toString(mCount));
        chngeCollor();
    }

    private void chngeCollor(){
        if (mCount == 0){
            binding.buttonZero.setBackgroundColor(getColor(R.color.gray));
            binding.buttonCount.setBackgroundColor(getColor(R.color.blue));
        } else {
            binding.buttonZero.setBackgroundColor(getColor(R.color.purple_200));
            if (mCount%2 == 0)
                binding.buttonCount.setBackgroundColor(getColor(R.color.green));
            else
                binding.buttonCount.setBackgroundColor(getColor(R.color.red));
        }
    }

    public void showToast(View view) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null) {
            showMCountToUser();
        }
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MCONUN, mCount);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}