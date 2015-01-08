package com.example.newcontrolexample.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import com.example.newcontrolexample.R;

public class JoyStickView extends View {
	
	public interface IJoystickListener {
		
		void onClick();

		void onLeft();

		void onUp();

		void onRight();

		void onDown();

	}
	
	private static final int DEFAULT_BORDER_WIDTH = 10;
	private static final int DEFAULT_BORDER_COLOR = Color.rgb(0, 252, 0); // green
	private static final int DEFAULT_BORDER_FILL_COLOR = Color.argb(0, 0, 0, 0); // transparent

	private IJoystickListener mListener;
	private GestureDetector mGestureDetector;

	private Paint mBorderPaint;

	private Bitmap mBallBitmap;
	private Bitmap mBallDefaultBitmap;
	private Bitmap mBallPressedBitmap;

	private PointF mBallPosition;
	private Rect mBorders;
	private Matrix mDrawMatrix;

	// moving square's side length
	private int mSize;

	// custom attributes
	private float mBorderWidth;
	private int mBorderColor;
	private int mFillColor;

	// indicates whether the ball has reached any side. Used to send callback
	// only once per slide.
	private boolean mIsSideReached;

	public JoyStickView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// enable hardware acceleration, all drawing commands will be passed to
		// GPU via OpenGL drivers
		if (!isInEditMode()) {
			setLayerType(View.LAYER_TYPE_HARDWARE, null);
		}

		TypedArray attrsArray = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.joystick_attr, 0, 0);

		try {
			mBorderWidth = attrsArray.getDimension(R.styleable.joystick_attr_borderWidth, DEFAULT_BORDER_WIDTH);
			mBorderColor = attrsArray.getColor(R.styleable.joystick_attr_borderColor, DEFAULT_BORDER_COLOR);
			mFillColor = attrsArray.getColor(R.styleable.joystick_attr_fillColor, DEFAULT_BORDER_FILL_COLOR);
		} finally {
			attrsArray.recycle();
		}

		init(context);
	}

	public IJoystickListener getListener() {
		return mListener;
	}

	public void setListener(IJoystickListener listener) {
		this.mListener = listener;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);

		// make joystick container square
		if (width < height) {
			width = height;
		} else {
			height = width;
		}

		mSize = width;
		resetBallPosition();

		super.onMeasure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Log.i("draw", "drawing");
		
		// NEVER CREATE NEW OBJECTS IN onDraw(), especially when the view is frequently redrawn

		// move ball
		mDrawMatrix.reset();
		mDrawMatrix.postTranslate(mBallPosition.x, mBallPosition.y);

		// draw fill color
		canvas.drawColor(mFillColor);
		// draw border rectangle
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mBorderPaint);
		// draw joystick ball
		canvas.drawBitmap(mBallBitmap, mDrawMatrix, null);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (isTouchOnBall(event)) {
			if (mGestureDetector.onTouchEvent(event)) {
				return true;
			}
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			mBallBitmap = mBallDefaultBitmap;
			mIsSideReached = false;
			resetBallPosition();
		}

		return true;
	}

	private void init(Context context) {
		mGestureDetector = new GestureDetector(context, new JoystickGestureListener());

		// TODO homework: Get default and pressed bitmaps from attributes
		mBallDefaultBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_joystick_default);
		mBallPressedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_joystick_pressed);
		// TODO homework: Get ball's size from attributes and scale the bitmaps with the specified size
		// Bitmap.createScaledBitmap(src, dstWidth, dstHeight, filter)
		mBallBitmap = mBallDefaultBitmap;

		// border paint
		mBorderPaint = new Paint();
		mBorderPaint.setStyle(Paint.Style.STROKE);
		mBorderPaint.setColor(mBorderColor);
		mBorderPaint.setStrokeWidth(mBorderWidth);
		mBorderPaint.setAntiAlias(true);
		mBorderPaint.setDither(false);

		mBallPosition = new PointF();
		mDrawMatrix = new Matrix();
		mBorders = new Rect(0, 0, mBallBitmap.getWidth(), mBallBitmap.getHeight());
	}

	private void resetBallPosition() {
		mBallPosition.x = (mSize - mBorders.width()) / 2;
		mBallPosition.y = (mSize - mBorders.height()) / 2;
		invalidate();
	}

	private boolean isTouchOnBall(MotionEvent event) {
		float touchX = event.getX();
		float touchY = event.getY();

		if ((touchX > mBallPosition.x) && (touchX < mBallPosition.x + mBorders.width())) {
			if ((touchY > mBallPosition.y) && (touchY < mBallPosition.y + mBorders.height())) {
				return true;
			}
		}

		return false;
	}

	private class JoystickGestureListener extends SimpleOnGestureListener {

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			mListener.onClick();
			return true;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			// do nothing if the ball has reached any side
			if (mIsSideReached) {
				return true;
			}

			if (mBallPosition.x - mBorderWidth < 0) {
				sideReached();
				mListener.onLeft();
				return true;
			}

			if (mBallPosition.x + mBorders.width() + mBorderWidth > mSize) {
				sideReached();
				mListener.onRight();
				return true;
			}

			if (mBallPosition.y - mBorderWidth < 1) {
				sideReached();
				mListener.onUp();
				return true;
			}

			if (mBallPosition.y + mBorders.height() + mBorderWidth > mSize) {
				sideReached();
				mListener.onDown();
				return true;
			}

			mBallPosition.x -= distanceX;
			mBallPosition.y -= distanceY;
			invalidate();
			
			Log.i("scroll", "(dx, dy) = (" + distanceX + "; " + distanceY + ")");
			
			return true;
		}

		private void sideReached() {
			mIsSideReached = true;
			mBallBitmap = mBallPressedBitmap;
			invalidate();
		}

	}
}
