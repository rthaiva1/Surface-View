package edu.binghamton.cs.surface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.Random;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder = null;
    private Paint paint = null;
    private float circleX = 0;
    private float circleY = 0;
    private float slope = 0;
    private float intercept = 0;
    int[][] a = new int[10][4];
    int flag;
    public MySurface(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        paint.setColor(Color.RED);
        flag=0;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawBall();
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        paint = null;
    }

    /* This method will be invoked to draw a circle in canvas. */
    public void drawBall()
    {

        surfaceHolder = getHolder();
        Bitmap bitmap = Bitmap.createBitmap(
                500, // Width
                300, // Height
                Bitmap.Config.ARGB_8888 // Config
        );
        Canvas canvas = new Canvas(bitmap);
        canvas = surfaceHolder.lockCanvas();

        Paint surfaceBackground = new Paint();
        Paint grid = new Paint();
        surfaceBackground.setColor(Color.rgb(153,219,255));

        canvas.drawColor(Color.rgb(153,219,255));

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        // Line width in pixels
        paint.setStrokeWidth(8);
        paint.setAntiAlias(true);


        grid.setColor(Color.rgb(204,237,255));
        grid.setStyle(Paint.Style.STROKE);
        // Line width in pixels
        grid.setStrokeWidth(8);
        grid.setAntiAlias(true);

        // Set a pixels value to offset the line from canvas edge
        int offset = 50;
        int temp =0;
        for(int i= 0 ; i<=canvas.getHeight() ; i ++)
        {
            temp = offset + temp ;
            canvas.drawLine(
                    0, // startX
                    canvas.getHeight() - temp, // startY
                    canvas.getWidth(), // stopX
                    canvas.getHeight() - temp, // stopY
                    grid // Paint
            );
        }
        temp =0;
        for(int i= 0 ; i<=canvas.getHeight() ; i ++)
        {
            temp = offset + temp ;
            canvas.drawLine(
                    temp, // startX
                    0, // startY
                    temp, // stopX
                    canvas.getHeight(), // stopY
                    grid // Paint
            );
        }
        canvas.drawLine(
                offset, // startX
                canvas.getHeight() /2, // startY
                canvas.getWidth() - offset, // stopX
                canvas.getHeight() /2, // stopY
                paint // Paint
        );
        canvas.drawLine(
                canvas.getWidth()/2, // startX
                offset, // startY
                canvas.getWidth()/2, // stopX
                canvas.getHeight() - offset, // stopY
                paint // Paint
        );

        canvas.drawLine((canvas.getWidth()/2)-30, offset+50, canvas.getWidth()/2, offset, paint);
        canvas.drawLine((canvas.getWidth()/2)+30, offset+50, canvas.getWidth()/2, offset, paint);
        canvas.drawLine((canvas.getWidth()/2)-30, canvas.getHeight() - offset-50, canvas.getWidth()/2, canvas.getHeight() - offset, paint);
        canvas.drawLine((canvas.getWidth()/2)+30, canvas.getHeight() - offset-50, canvas.getWidth()/2, canvas.getHeight() - offset, paint);
        canvas.drawLine(canvas.getWidth() -offset-50, (canvas.getHeight() /2)-30, canvas.getWidth() - offset, canvas.getHeight() /2, paint);
        canvas.drawLine(canvas.getWidth() -offset- 50, (canvas.getHeight() /2)+30, canvas.getWidth() - offset, canvas.getHeight() /2, paint);
        canvas.drawLine(offset+50, (canvas.getHeight() /2)-30, offset, canvas.getHeight() /2, paint);
        canvas.drawLine(offset+50, (canvas.getHeight() /2)+30, offset, canvas.getHeight() /2, paint);

        if(flag == 0)
        {
            Random r = new Random();
            int low = offset;
            int highx = canvas.getWidth() - offset;
            int highy = canvas.getHeight() - offset;
            for(int i =0; i<10 ; i++)
            {
                for(int j =0; j<4 ; j++)
                {
                    if((j == 0) || (j == 2)) {
                        a[i][j] = r.nextInt(highx - low) + low;
                    }
                    else  if((j == 1) || (j == 3))
                    {
                        a[i][j] = r.nextInt(highy - low) + low;
                    }
                }
            }
            flag =1;
        }
        else
        {
            for(int i =0; i<10 ; i++)
            {
                for(int j =0; j<4 ; j++)
                {
                    int temp1;
                    if((j == 1) || (j == 3))
                    {
                        temp1 = ((int)slope * a[i][j-1] + (int)intercept)% getHeight();
                        if(temp1 > offset)
                        {
                           a[i][j] =temp1;
                        }
                    }
                }
            }
        }
        canvas.drawLine(a[0][0], a[0][1], a[0][2], a[0][3], paint);
        canvas.drawLine(a[1][0], a[1][1], a[1][2], a[1][3], paint);
        canvas.drawLine(a[2][0], a[2][1], a[2][2], a[2][3], paint);
        canvas.drawLine(a[3][0], a[3][1], a[3][2], a[3][3], paint);
        canvas.drawLine(a[4][0], a[4][1], a[4][2], a[4][3], paint);
        canvas.drawLine(a[5][0], a[5][1], a[5][2], a[5][3], paint);
        canvas.drawLine(a[6][0], a[6][1], a[6][2], a[6][3], paint);
        canvas.drawLine(a[7][0], a[7][1], a[7][2], a[7][3], paint);
        canvas.drawLine(a[8][0], a[8][1], a[8][2], a[8][3], paint);
        canvas.drawLine(a[9][0], a[9][1], a[9][2], a[9][3], paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(getHeight()*50/1800);
        paint.setStrokeWidth(getHeight()*4/1800);

        if(circleX>=getWidth()/2)
        {
            canvas.drawText("X coordinate:     " + String.valueOf(circleX-getWidth()/2), offset, getHeight()-30, paint);
        }
        else
        {
            canvas.drawText("X coordinate:    -" + String.valueOf(getWidth()/2-circleX), offset, getHeight()-30, paint);
        }
        if(circleY>=getHeight()/2)
        {
            canvas.drawText("Y coordinate:    -" + String.valueOf(circleY-getHeight()/2), offset, offset, paint);
        }
        else
        {
            canvas.drawText("Y coordinate:     " + String.valueOf(getHeight()/2-circleY), offset, offset, paint);
        }

        canvas.drawText("X axis" , (getWidth()*1)/10, getHeight()/2-50, paint);
        canvas.drawText("X axis" , (getWidth()*8)/10, getHeight()/2-50, paint);
        canvas.drawText("Y axis" , getWidth()/2+50, getHeight()/10, paint);
        canvas.drawText("Y axis" , getWidth()/2+50, (getHeight()*9)/10, paint);


        canvas.drawText(String.valueOf((int)((getHeight()*3)/10)) , getWidth()/2+50, (getHeight()*2)/10, paint);
        canvas.drawText(String.valueOf((int)((getHeight()*2)/10)) , getWidth()/2+50, (getHeight()*3)/10, paint);
        canvas.drawText(String.valueOf((int)((getHeight()*1)/10)) , getWidth()/2+50, (getHeight()*4)/10, paint);
        canvas.drawText("0" , getWidth()/2+50, ((getHeight()*(float)5.3)/10), paint);
        canvas.drawText("-" + String.valueOf((int)((getHeight()*1)/10)) , getWidth()/2+50, (getHeight()*6)/10, paint);
        canvas.drawText("-" + String.valueOf((int)((getHeight()*2)/10)) , getWidth()/2+50, (getHeight()*7)/10, paint);
        canvas.drawText("-" + String.valueOf((int)((getHeight()*3)/10)) , getWidth()/2+50, (getHeight()*8)/10, paint);

        canvas.drawText("-" + String.valueOf((int)((getWidth()*3)/10)) , (getWidth()*2)/10, getHeight()/2+50, paint);
        canvas.drawText("-" + String.valueOf((int)((getWidth()*2)/10)), (getWidth()*3)/10, getHeight()/2+50, paint);
        canvas.drawText("-" + String.valueOf((int)((getWidth()*1)/10)) , (getWidth()*4)/10, getHeight()/2+50, paint);
        canvas.drawText(String.valueOf((int)((getWidth()*1)/10)) , (getWidth()*6)/10, getHeight()/2+50, paint);
        canvas.drawText(String.valueOf((int)((getWidth()*2)/10)) , (getWidth()*7)/10, getHeight()/2+50, paint);
        canvas.drawText(String.valueOf((int)((getWidth()*3)/10)), (getWidth()*8)/10, getHeight()/2+50, paint);

        canvas.drawLine( getWidth()/2 - (getWidth()*(float)0.1)/10,(getHeight()*2)/10 , getWidth()/2 + (getWidth()*(float)0.1)/10, (getHeight()*2)/10, paint);
        canvas.drawLine( getWidth()/2 - (getWidth()*(float)0.1)/10,(getHeight()*3)/10 , getWidth()/2 + (getWidth()*(float)0.1)/10, (getHeight()*3)/10, paint);
        canvas.drawLine( getWidth()/2 - (getWidth()*(float)0.1)/10,(getHeight()*4)/10 , getWidth()/2 + (getWidth()*(float)0.1)/10, (getHeight()*4)/10, paint);
        canvas.drawLine( getWidth()/2 - (getWidth()*(float)0.1)/10,(getHeight()*6)/10 , getWidth()/2 + (getWidth()*(float)0.1)/10, (getHeight()*6)/10, paint);
        canvas.drawLine( getWidth()/2 - (getWidth()*(float)0.1)/10,(getHeight()*7)/10 , getWidth()/2 + (getWidth()*(float)0.1)/10, (getHeight()*7)/10, paint);
        canvas.drawLine( getWidth()/2 - (getWidth()*(float)0.1)/10,(getHeight()*8)/10 , getWidth()/2 + (getWidth()*(float)0.1)/10, (getHeight()*8)/10, paint);


        canvas.drawLine((getWidth()*2)/10,getHeight()/2 - (getHeight()*(float)0.1)/10 , (getWidth()*2)/10,getHeight()/2 + (getHeight()*(float)0.1)/10 ,paint);
        canvas.drawLine((getWidth()*3)/10,getHeight()/2 - (getHeight()*(float)0.1)/10 , (getWidth()*3)/10,getHeight()/2 + (getHeight()*(float)0.1)/10 ,paint);
        canvas.drawLine((getWidth()*4)/10,getHeight()/2 - (getHeight()*(float)0.1)/10 , (getWidth()*4)/10,getHeight()/2 + (getHeight()*(float)0.1)/10 ,paint);
        canvas.drawLine((getWidth()*6)/10,getHeight()/2 - (getHeight()*(float)0.1)/10 , (getWidth()*6)/10,getHeight()/2 + (getHeight()*(float)0.1)/10 ,paint);
        canvas.drawLine((getWidth()*7)/10,getHeight()/2 - (getHeight()*(float)0.1)/10 , (getWidth()*7)/10,getHeight()/2 + (getHeight()*(float)0.1)/10 ,paint);
        canvas.drawLine((getWidth()*8)/10,getHeight()/2 - (getHeight()*(float)0.1)/10 , (getWidth()*8)/10,getHeight()/2 + (getHeight()*(float)0.1)/10 ,paint);


        paint.setColor(Color.BLACK);
        canvas.drawText("Slope:     " + String.valueOf(slope), offset, getHeight()-90, paint);
        canvas.drawText("Intercept:     " + String.valueOf(intercept), offset, offset+90, paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public float getCircleX() {
        return circleX;
    }

    public void setCircleX(float circleX) {
        this.circleX = circleX;
    }

    public float getCircleY() {
        return circleY;
    }

    public void setCircleY(float circleY) {
        this.circleY = circleY;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setm(float a) {
        this.slope = a;
    }

    public void setb(float b) {
        this.intercept = b;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
