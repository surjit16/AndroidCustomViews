package in.surjitsingh.customviewsandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class MySurfaceView extends SurfaceView implements Runnable {

    Paint mPaint;
    SurfaceHolder holder;
    Bitmap bitmap;
    boolean isRunning;
    Thread mThread;
    float mx, my;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }


    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        holder = getHolder();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(Color.RED);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ss);
        mx = 0;

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                my = getHeight() / 2;
                Log.e("AAAAAa", "AAAAAAaaaa " + getHeight());
                resume();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    void stop() {
        isRunning = false;
        try {
            mThread.join();
        } catch (Exception e) {

        }
    }

    void resume() {
        isRunning = true;
        mThread = new Thread(this);
        mThread.start();
    }

    Canvas canvas;

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(200);
                canvas = holder.lockCanvas();
                if (canvas != null) {
                    onDraw(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }
            } catch (Exception e) {
                Log.e("Exception", "Hello Exception");
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
        canvas.drawCircle(40, 40, 20, mPaint);
        canvas.drawBitmap(bitmap, mx, my, null);
        mx = mx + 10;
        if (mx > getWidth()) mx = 0;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            mx = event.getX();
            my = event.getY();
            postInvalidate();
        }
        return true;
    }

    public void random() {
        Random ran = new Random();
        mx = ran.nextInt(getWidth() - bitmap.getWidth());
        my = ran.nextInt(getHeight() - bitmap.getHeight());
        postInvalidate();
    }
}
