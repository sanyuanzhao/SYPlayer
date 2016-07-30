package com.sy.syplayer.ui;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sy.syplayer.MyApplication;
import com.sy.syplayer.R;
import com.sy.syplayer.decoder.AudioProperty;
import com.sy.syplayer.player.Player;


/**
 * Created by ASUS User on 2016/7/18.
 */
public class MainActivity extends Activity implements View.OnClickListener, MyApplication.DisplayComponent{
    private EditText mDestEdit = null;
    private EditText mUrlEdit = null;
    private Button mStartBtn = null;
    private Button mStopBtn = null;
    private EditText mMsgTextView = null;
    private Player mPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initUiControl();
        mPlayer = new Player();
    }

    private void initUiControl(){
        mUrlEdit = (EditText) findViewById(R.id.url_edit);
        mDestEdit = (EditText) findViewById(R.id.dest_edit);
        mMsgTextView = (EditText) findViewById(R.id.msg_textview);

        mUrlEdit.setText("/sdcard/test.mp3");
        mDestEdit.setText("/sdcard/test.pcm");
        mStartBtn = (Button)findViewById(R.id.start_btn);
        mStopBtn = (Button)findViewById(R.id.stop_btn);

        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.getAppliction().addDisplayComponent(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getAppliction().removeDisplayComponent(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.start_btn:
                mPlayer.play(mUrlEdit.getText().toString().trim());
                break;
            case R.id.stop_btn:
                mPlayer.stop();
                break;
            default:
        }
    }

    public void showMsg(final String msg){
        Runnable oRun = new Runnable() {
            @Override
            public void run() {
                mMsgTextView.append(msg);
                mMsgTextView.append("\n");
            }
        };
        MyApplication.getAppliction().runOnUiThread(oRun, 0);
    }


}
