package android.examample.imageloader_hw_31;

import androidx.appcompat.app.AppCompatActivity;

import android.examample.imageloader_hw_31.databinding.ActivityMainHw21Binding;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_HW_2_1 extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;

    private ActivityMainHw21Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainHw21Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mShowCount = (TextView) findViewById(R.id.show_count);
        Log.d("HW_2.1","main_activity_onCreate_executed");

        binding.buttonZero.setOnClickListener(v -> {
            mCount = 0;
            mShowCount.setText(R.string.defoult_count_text);
            binding.buttonZero.setBackgroundColor (getColor(R.color.gray));
            binding.buttonCount.setBackgroundColor(getColor(R.color.blue));
        });
    }

    public void showToast(View view) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        binding.buttonZero.setBackgroundColor(getColor(R.color.purple_200));
        binding.buttonCount.setBackgroundColor(getColor(R.color.green));
    }
}