package vn.com.example.appcaculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextInput;
    private TextView mTextOutput;
    private boolean mAdd;
    private boolean mSub;
    private boolean mMulti;
    private boolean mDiv;
    private boolean mPer;
    private float mFirstNumber;
    private float mSecondNumber;
    private String mFirstResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        readata();
    }

    private void readata() {
        SharedPreferences sp = getSharedPreferences(getString(R.string.save_result),MODE_PRIVATE);
        mTextOutput.setText(sp.getString(getResources().getString(R.string.key_save),""));
    }

    private void setEvent() {
        findViewById(R.id.button_zero).setOnClickListener(this);
        findViewById(R.id.button_one).setOnClickListener(this);
        findViewById(R.id.button_two).setOnClickListener(this);
        findViewById(R.id.button_three).setOnClickListener(this);
        findViewById(R.id.button_four).setOnClickListener(this);
        findViewById(R.id.button_five).setOnClickListener(this);
        findViewById(R.id.button_six).setOnClickListener(this);
        findViewById(R.id.button_seven).setOnClickListener(this);
        findViewById(R.id.button_eight).setOnClickListener(this);
        findViewById(R.id.button_nine).setOnClickListener(this);
        findViewById(R.id.button_addition).setOnClickListener(this);
        findViewById(R.id.button_division).setOnClickListener(this);
        findViewById(R.id.button_subtraction).setOnClickListener(this);
        findViewById(R.id.button_multiplation).setOnClickListener(this);
        findViewById(R.id.button_result).setOnClickListener(this);
        findViewById(R.id.button_percent).setOnClickListener(this);
        findViewById(R.id.button_dot).setOnClickListener(this);
        findViewById(R.id.button_clear).setOnClickListener(this);
        findViewById(R.id.button_sign).setOnClickListener(this);
    }

    private void init() {
        mTextInput = findViewById(R.id.text_input);
        mTextOutput = findViewById(R.id.text_output);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_caculator,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_clear:
                clear();
                break;
            case R.id.menu_save:
                SharedPreferences sp = getSharedPreferences(getString(R.string.save_result),MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(getString(R.string.key_save), mTextOutput.getText().toString().trim());
                editor.apply();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_zero:
                mFirstResult = mTextInput.getText() + getString(R.string.zero);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_one:
                mFirstResult = mTextInput.getText() + getString(R.string.one);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_two:
                mFirstResult = mTextInput.getText() + getString(R.string.two);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_three:
                mFirstResult = mTextInput.getText() + getString(R.string.three);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_four:
                mFirstResult = mTextInput.getText() + getString(R.string.four);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_five:
                mFirstResult = mTextInput.getText() + getString(R.string.five);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_six:
                mFirstResult = mTextInput.getText() + getString(R.string.six);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_seven:
                mFirstResult = mTextInput.getText() + getString(R.string.seven);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_eight:
                mFirstResult = mTextInput.getText() + getString(R.string.eight);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_nine:
                mFirstResult = mTextInput.getText() + getString(R.string.night);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_addition:
                addition();
                break;
            case R.id.button_division:
                division();
                break;
            case R.id.button_subtraction:
                subtraction();
                break;
            case R.id.button_multiplation:
                multiplation();
                break;
            case R.id.button_result:
                result();
                break;
            case R.id.button_dot:
                mFirstResult = mTextInput.getText() + getString(R.string.dot);
                mTextInput.setText(mFirstResult);
                break;
            case R.id.button_percent:
                percent();
                break;
            case R.id.button_clear:
                clear();
                break;
            case R.id.button_sign:
                sign();
                break;
        }
    }

    private void percent() {
        if (mPer) {
            Toast.makeText(this, R.string.msg_mod, Toast.LENGTH_SHORT).show();
        } else if (mTextInput.getText().toString().equals("")) {
            Toast.makeText(this, R.string.msg_not_enter, Toast.LENGTH_SHORT).show();
        } else {
            try {
                mFirstNumber = Float.parseFloat(mTextInput.getText().toString().trim());
                mAdd = false;
                mDiv = false;
                mMulti = false;
                mSub = false;
                mPer = true;
                mTextInput.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, R.string.msg_check_format, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sign() {
        if (mTextInput.getText().toString().equals("")) {
            mFirstResult = mTextInput.getText() + getString(R.string.sign);
            mTextInput.setText(mFirstResult);
        } else {
            Toast.makeText(this, R.string.msg_not_sign, Toast.LENGTH_SHORT).show();
        }
    }

    private void result() {
        try {
            mSecondNumber = Float.parseFloat(mTextInput.getText().toString().trim());
            String output;
            if (mAdd) {
                mAdd = false;
                output = mFirstNumber + mSecondNumber + "";
                mTextOutput.setText(output);
            } else if (mSub) {
                mSub = false;
                output = mFirstNumber - mSecondNumber + "";
                mTextOutput.setText(output);
            } else if (mMulti) {
                mMulti = false;
                output = mFirstNumber * mSecondNumber + "";
                mTextOutput.setText(output);
            } else if (mDiv) {
                mDiv = false;
                output = mFirstNumber / mSecondNumber + "";
                mTextOutput.setText(output);
            } else {
                mPer = false;
                output = mFirstNumber % mSecondNumber + "";
                mTextOutput.setText(output);
            }
            mTextInput.setText("");
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.msg_check_format, Toast.LENGTH_SHORT).show();
        }
    }

    private void addition() {
        if (mAdd) {
            Toast.makeText(this, R.string.msg_added, Toast.LENGTH_SHORT).show();
        } else if (mTextInput.getText().toString().equals("")) {
            Toast.makeText(this, R.string.msg_not_enter, Toast.LENGTH_SHORT).show();
        } else {
            try {
                mFirstNumber = Float.parseFloat(mTextInput.getText().toString().trim());
                mAdd = true;
                mDiv = false;
                mMulti = false;
                mSub = false;
                mPer = false;
                mTextInput.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, R.string.msg_check_format, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void subtraction() {
        if (mSub) {
            Toast.makeText(this, R.string.msg_subed, Toast.LENGTH_SHORT).show();
        } else if (mTextInput.getText().toString().equals("")) {
            Toast.makeText(this, R.string.msg_not_enter, Toast.LENGTH_SHORT).show();
        } else {
            try {
                mFirstNumber = Float.parseFloat(mTextInput.getText().toString().trim());
                mAdd = false;
                mPer = false;
                mDiv = false;
                mMulti = false;
                mSub = true;
                mTextInput.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, R.string.msg_check_format, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void multiplation() {
        if (mMulti) {
            Toast.makeText(this, R.string.msg_multied, Toast.LENGTH_SHORT).show();
        } else if (mTextInput.getText().toString().equals("")) {
            Toast.makeText(this, R.string.msg_not_enter, Toast.LENGTH_SHORT).show();
        } else {
            try {
                mFirstNumber = Float.parseFloat(mTextInput.getText().toString().trim());
                mAdd = false;
                mPer = false;
                mDiv = false;
                mMulti = true;
                mSub = false;
                mTextInput.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, R.string.msg_check_format, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void division() {
        if (mDiv) {
            Toast.makeText(this, R.string.msg_dived, Toast.LENGTH_SHORT).show();
        } else if (mTextInput.getText().toString().equals("")) {
            Toast.makeText(this, R.string.msg_not_enter, Toast.LENGTH_SHORT).show();
        } else {
            try {
                mFirstNumber = Float.parseFloat(mTextInput.getText().toString().trim());
                mAdd = false;
                mDiv = true;
                mMulti = false;
                mPer = false;
                mSub = false;
                mTextInput.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, R.string.msg_check_format, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void clear() {
        mPer = false;
        mMulti = false;
        mSub = false;
        mDiv = false;
        mAdd = false;
        mTextInput.setText("");
        mTextOutput.setText("");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
