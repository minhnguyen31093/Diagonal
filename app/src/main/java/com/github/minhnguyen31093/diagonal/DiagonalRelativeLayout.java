package com.github.minhnguyen31093.diagonal;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Minh Nguyen on 7/9/2016.
 */
//https://stackoverflow.com/questions/34565525/listview-with-triangular-shaped-items
public class DiagonalRelativeLayout extends RelativeLayout {

    private double degree = -28;

    public DiagonalRelativeLayout(Context context) {
        super(context);
    }

    public DiagonalRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DiagonalRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DiagonalRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void draw(Canvas canvas) {
        // crop drawing to the triangle shape
        Path mask = new Path();
        Point[] tria = getTriangle();
        mask.moveTo(tria[0].x, tria[0].y);
        mask.lineTo(tria[1].x, tria[1].y);
        mask.lineTo(tria[2].x, tria[2].y);
        mask.lineTo(tria[3].x, tria[3].y);
        mask.close();

        canvas.save();

        canvas.clipPath(mask);
        super.draw(canvas);

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // check if touch event is within the triangle shape
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            Point touch = new Point((int) event.getX(), (int) event.getY());
            Point[] tria = getTriangle();

            if (!isPointInsideTrigon(touch, tria[0], tria[1], tria[2]) && !isPointInsideTrigon(touch, tria[0], tria[2], tria[3])) {
                // ignore touch event outside triangle
                return false;
            }
        }

        return super.onTouchEvent(event);
    }

    private boolean isPointInsideTrigon(Point s, Point a, Point b, Point c) {
        // stolen from http://stackoverflow.com/a/9755252
        int as_x = s.x - a.x;
        int as_y = s.y - a.y;
        boolean s_ab = (b.x - a.x) * as_y - (b.y - a.y) * as_x > 0;
        if ((c.x - a.x) * as_y - (c.y - a.y) * as_x > 0 == s_ab)
            return false;
        if ((c.x - b.x) * (s.y - b.y) - (c.y - b.y) * (s.x - b.x) > 0 != s_ab)
            return false;
        return true;
    }

    private Point[] getTriangle() {
        // define the triangle shape of this View
        int Ay = (int) (getWidth() / 2 * Math.tan(degree) * 2);
        Point a = new Point(0, Ay);
        Point b = new Point(0, getHeight());
        Point c = new Point(getWidth(), getHeight());
        Point d = new Point(getWidth(), 0);
        return new Point[]{a, b, c, d};
    }

}