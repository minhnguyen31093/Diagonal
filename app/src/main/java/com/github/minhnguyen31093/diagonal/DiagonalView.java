package com.github.minhnguyen31093.diagonal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.minhnguyen31093.diagonal.polygon.Point;
import com.github.minhnguyen31093.diagonal.polygon.Polygon;

/**
 * Created by Minh Nguyen on 7/9/2016.
 */
//https://www.mnafian.net/membuat-custom-diagonalshapeview-android/
public class DiagonalView extends View {
    private Paint bgPaint;

    public DiagonalView(Context context) {
        super(context);
        init();
    }

    public DiagonalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiagonalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bgPaint = new Paint();
        bgPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(getResources().getColor(R.color.colorGray));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Point[] polygon = getPolygon();
        Path path = new Path();
        path.lineTo(polygon[0].x, polygon[0].y);
        path.lineTo(polygon[1].x, polygon[1].y);
        path.lineTo(polygon[2].x, polygon[2].y);
        path.close();
        canvas.drawPath(path, bgPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // check if touch event is within the triangle shape
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            Point touch = new Point((int) event.getX(), (int) event.getY());
            Point[] polygon = getPolygon();

//            if (!isPointInsidePolygon(touch, polygon)) {
//                // ignore touch event outside triangle
//                return false;
//            }

            if (!isPointInsidePolygon(touch, polygon[0], polygon[1], polygon[2])) {
                // ignore touch event outside triangle
                return false;
            }
        }

        return super.onTouchEvent(event);
    }

    private boolean isPointInsidePolygon(Point touch, Point[] points) {
        Polygon polygon = Polygon.Builder()
                .addVertex(points[0])
                .addVertex(points[1])
                .addVertex(points[2]).build();
        return polygon.contains(touch);
    }

    private boolean isPointInsidePolygon(Point s, Point a, Point b, Point c) {
        // stolen from http://stackoverflow.com/a/9755252
        float as_x = s.x - a.x;
        float as_y = s.y - a.y;
        boolean s_ab = (b.x - a.x) * as_y - (b.y - a.y) * as_x > 0;
        if ((c.x - a.x) * as_y - (c.y - a.y) * as_x > 0 == s_ab)
            return false;
        if ((c.x - b.x) * (s.y - b.y) - (c.y - b.y) * (s.x - b.x) > 0 != s_ab)
            return false;
        return true;
    }

    private Point[] getPolygon() {
        int h = getMeasuredHeight();
        int w = getMeasuredWidth();
        Point a = new Point(w, 0);
        Point b = new Point(w, h / 2);
        Point c = new Point(0, h);
        return new Point[]{a, b, c};
    }
}