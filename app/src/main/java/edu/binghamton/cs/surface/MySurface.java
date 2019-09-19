package edu.binghamton.cs.surface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.Random;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder = null;
    private Paint paint = null;
    private float circleX = 0;
    private float circleY = 0;
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
        // Get and lock canvas object from surfaceHolder.
        canvas = surfaceHolder.lockCanvas();

        Paint surfaceBackground = new Paint();
        Paint grid = new Paint();
        // Set the surfaceview background color.
        surfaceBackground.setColor(Color.rgb(153,219,255));

        canvas.drawColor(Color.rgb(153,219,255));

        // Initialize a new Paint instance to draw the line
        // Draw the surfaceview background color.
        //canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), surfaceBackground);

        // Draw the circle.
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
        //canvas.drawCircle(circleX, circleY, 100, paint);

        //canvas.drawCircle(50, 50, 200, paint);

        // Unlock the canvas object and post the new draw.
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
        paint.setTextSize(50);
        paint.setStrokeWidth(4);
        canvas.drawText(String.valueOf(circleX), 500, 100, paint);
        canvas.drawText(String.valueOf(circleY), 500, 150, paint);
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

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
