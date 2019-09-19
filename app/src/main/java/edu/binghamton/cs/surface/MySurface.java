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

public class MySurface extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder = null;
    private Paint paint = null;
    private float circleX = 0;
    private float circleY = 0;

    public MySurface(Context context) {
        super(context);

        surfaceHolder = getHolder();

        paint = new Paint();
        paint.setColor(Color.RED);
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
        offset = 100;
        canvas.drawLine(
                offset, // startX
                canvas.getHeight() - offset, // startY
                700, // stopX
                canvas.getHeight() - offset, // stopY
                paint // Paint
        );
        canvas.drawLine(
                offset, // startX
                offset, // startY
                offset, // stopX
                canvas.getHeight() - offset, // stopY
                paint // Paint
        );
        canvas.drawLine(70, 140, 100, 96, paint);
        canvas.drawLine(130, 140, 100, 96, paint);
        canvas.drawLine(650, 960, 700, 990, paint);
        canvas.drawLine(650, 1020, 700, 990, paint);

        canvas.drawLine(100, 900, 200, 70, paint);
        canvas.drawLine(200, 70, 300, canvas.getHeight()-200, paint);
        canvas.drawLine(300, canvas.getHeight()-200, 400, 150, paint);
        canvas.drawLine(400, 150, 500, canvas.getHeight()-430, paint);
        canvas.drawLine(500, canvas.getHeight()-430, 600, 330, paint);
        canvas.drawLine(600, 330, 700, canvas.getHeight()-630, paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        paint.setStrokeWidth(4);
        canvas.drawText(String.valueOf(circleX), 500, 100, paint);
        canvas.drawText(String.valueOf(circleY), 500, 150, paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    /* This method will be invoked to draw a circle in canvas. */
    public void drawRect()
    {
        Canvas canvas = surfaceHolder.lockCanvas();

        Paint surfaceBackground = new Paint();
        // Set the surfaceview background color.
        surfaceBackground.setColor(Color.BLUE);
        // Draw the surfaceview background color.
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), surfaceBackground);

        // Draw the rectangle.
        canvas.drawRect(circleX, circleY, circleX + 200, circleY + 200, paint);

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
