package android.examample.imageloader_hw_31.HomeWork_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.examample.imageloader_hw_31.R;
import android.os.Bundle;

public class MainActivity_HW_2_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hw22);

        Toolbar toolbar = findViewById(R.id.toolBar_HW_22);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}