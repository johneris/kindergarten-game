package com.johneris.kindergartengame;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends View implements OnTouchListener {

	private static final float STROKE_WIDTH = 10f;

	List<Point> points = new ArrayList<Point>();
	Paint paint = new Paint();

	public DrawView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		init();
	}
	
	public DrawView(Context context) {
		super(context);
		init();
	}
	
	public void init() {
		setFocusable(true);
		setFocusableInTouchMode(true);

		this.setOnTouchListener(this);

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(STROKE_WIDTH);
		paint.setColor(Color.RED);
	}

	@Override
	public void onDraw(Canvas canvas) {
		for (Point point : points) {
			canvas.drawCircle(point.x, point.y, 5, paint);
		}
	}

	public boolean onTouch(View view, MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_UP) {
			for (int i = 0; i < event.getHistorySize(); i++) {
				Point point = new Point();
				point.x = event.getHistoricalX(i);
				point.y = event.getHistoricalY(i);
				points.add(point);
			}
			invalidate();
			return true;
		}
		return super.onTouchEvent(event);
	}

	public void clear() {
		points.clear();
		invalidate();
	}
	
	class Point {
		float x, y;
		float dx, dy;

		@Override
		public String toString() {
			return x + ", " + y;
		}
	}

}