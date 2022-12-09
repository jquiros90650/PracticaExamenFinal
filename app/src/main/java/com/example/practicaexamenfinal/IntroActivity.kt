package com.example.practicaexamenfinal;

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.example.practicaexamenfinal.MainActivity
import com.example.practicaexamenfinal.R


public class IntroActivity extends AppCompatActivity {
    private ConstraintLayout startBtn;

    @override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        startBtn=findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }
        })
    }
}