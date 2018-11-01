package in.surjitsingh.customviewsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    MySurfaceView surfaceView;
    CET editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        surfaceView = findViewById(R.id.surfaceView);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("stop")){
                    surfaceView.stop();
                }else if (s.toString().equals("play")){
                    surfaceView.resume();
                }else if (s.toString().equals("random")){
                    surfaceView.random();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        surfaceView = findViewById(R.id.surfaceView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        surfaceView.stop();
    }
}
