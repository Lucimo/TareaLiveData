package com.appclase.utad.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView textViewWork;
    private TextView textViewPass;
    private TextView textViewAge;
    private EditText editTextAge;
    private Switch sWork;
    private Switch sPass;
    private MutableLiveData<String> mCurrentName;
    private MutableLiveData<Boolean> mWork;
    private MutableLiveData<Boolean> mPass;
    private MutableLiveData<String> mAge;
    private Boolean path1 = false;
    private Boolean path2 = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sWork = findViewById(R.id.switch1);
        sPass = findViewById(R.id.switch2);
        textViewWork = findViewById(R.id.textViewWork);
        textViewPass = findViewById(R.id.textViewPass);
        textViewAge = findViewById(R.id.textViewAge);
        editTextAge = findViewById(R.id.editViewAge);


        sWork.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (path1 == true) {
                    getCurrentWork().setValue(false);
                    path1 = false;
                } else {
                    getCurrentWork().setValue(true);
                    path1 = true;
                }

            }
        });
        sPass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (path2 == true) {
                    getCurrentPass().setValue(false);
                    path2 = false;
                } else {
                    getCurrentPass().setValue(true);
                    path2 = true;
                }

            }
        });
        final Observer<Boolean> workObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean bol) {
                if (bol == true) {
                    textViewWork.setText("You work at home");
                } else {
                    textViewWork.setText("You dont work at home");
                }

            }
        };
        getCurrentWork().observe(this, workObserver);
        final Observer<Boolean> passObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean bool) {
                if (bool == true) {
                    textViewPass.setText("You are going to pass");
                } else {
                    textViewPass.setText("You are not going to pass");
                }

            }
        };
        getCurrentPass().observe(this, passObserver);
        final Observer<String> ageObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String age) {
                textViewAge.setText("You are: " + age + " years old");

            }
        };




        editTextAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                getCurrentAge().setValue((editTextAge.getText()).toString());
            }

        });

        getCurrentAge().observe(this, ageObserver);
    }

    public MutableLiveData<Boolean> getCurrentWork() {
        if (mWork == null) {
            mWork = new MutableLiveData<Boolean>();
        }
        return mWork;
    }

    public MutableLiveData<Boolean> getCurrentPass() {
        if (mPass == null) {
            mPass = new MutableLiveData<Boolean>();
        }
        return mPass;
    }

    public MutableLiveData<String> getCurrentAge() {
        if (mAge == null) {
            mAge = new MutableLiveData<String>();
        }
        return mAge;
    }
}



