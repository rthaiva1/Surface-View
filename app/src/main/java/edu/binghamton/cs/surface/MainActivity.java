package edu.binghamton.cs.surface;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private Button redButton = null;

    private SeekBar m= null;

    private SeekBar b = null;

    private boolean drawBall = true;

    private LinearLayout canvasLayout = null;

    MySurface customSurfaceView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("SurfaceView");

        initControls();

        // Hide the app title bar.
        getSupportActionBar().hide();

        // Make app full screen to hide top status bar.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create custom surfaceview object.
        customSurfaceView = new MySurface(getApplicationContext());

        // Set this as the onTouchListener to process custom surfaceview ontouch event.
        customSurfaceView.setOnTouchListener(this);

        // Add the custom surfaceview object to the layout.
        canvasLayout.addView(customSurfaceView);

        // Click this button to draw a red circle ball move after finger touch.
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawBall = true;
            }
        });
        m.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onProgressChanged(SeekBar s,int value,boolean val) {
            customSurfaceView.setm(Float.parseFloat(String.valueOf(value)));
            customSurfaceView.drawBall();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    });

        b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar s,int value,boolean val) {
                customSurfaceView.setb(Float.parseFloat(String.valueOf(value)));
                customSurfaceView.drawBall();
            }

            @Override
            public void onStartTrackingTouch(SeekBar s) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar s) {
            }
        });

    }

    /* Initialise ui controls. */
    private void initControls()
    {
        if(redButton == null)
        {
            redButton = (Button)findViewById(R.id.redButton);
        }

        if(m == null)
        {
            m = (SeekBar) findViewById(R.id.seekBar);
        }

        if(b == null)
        {
            b = (SeekBar) findViewById(R.id.seekBar1);
        }

        // This layout is used to contain custom surfaceview object.
        if(canvasLayout == null)
        {
            canvasLayout = (LinearLayout)findViewById(R.id.customViewLayout);
        }
    }

    /* If user finger touch the surfaceview object. */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        // If user touch the custom SurfaceView object.
        if(view instanceof SurfaceView) {
            view.invalidate();

            float x = motionEvent.getX();

            float y = motionEvent.getY();

            customSurfaceView.setCircleX(x);

            customSurfaceView.setCircleY(y);

            if (drawBall) {
                // Create and set a red paint to custom surfaceview.
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                customSurfaceView.setPaint(paint);

                customSurfaceView.drawBall();
            }
            // Tell android os the onTouch event has been processed.
            return true;
        }else
        {
            // Tell android os the onTouch event has not been processed.
            return false;
        }
    }
}
