package in.surjitsingh.customviewsandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class MySurfaceViewGame extends SurfaceView {

    Paint mPaint;
    SurfaceHolder holder;
    Bitmap bitmap;
    boolean isRunning;
    Thread mThread;
    int mx, my;

    public MySurfaceViewGame(Context context) {
        super(context);
        init();
    }


    public MySurfaceViewGame(Context context, AttributeSet attrs) {
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
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                Random ran = new Random();
                mx = ran.nextInt(getWidth() - bitmap.getWidth());
                my = ran.nextInt(getHeight() - bitmap.getHeight());
                Log.e("AAAAAa", "AAAAAAaaaa " + getHeight());

                canvas = holder.lockCanvas();
                if (canvas != null) {
                    onDraw(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    Canvas canvas;

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
        canvas.drawCircle(40, 40, 20, mPaint);
        canvas.drawBitmap(bitmap, mx, my, null);
    }

    void random(){
        Random ran = new Random();
        mx = ran.nextInt(getWidth() - bitmap.getWidth());
        my = ran.nextInt(getHeight() - bitmap.getHeight());
        postInvalidate();
        Log.e("AAAAAa", "AAAAAAaaaa " + getHeight());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {

            Rect myViewRect = new Rect();
            myViewRect.set(mx, my, getWidth(), getHeight());

            Rect otherViewRect2 = new Rect();
            myViewRect.set((int)event.getX()-1, (int)event.getY()-1, (int)event.getX(), (int)event.getY());

            if (Rect.intersects(myViewRect, otherViewRect2)) {
                Log.e("AAAAAAAAAa", "Intersect yes ");
            }
            Log.e("AAAAAAAAAa", "Intersect not ");

        }
        return true;
    }

}
