/*
 * Copyright (C) 2015 RECRUIT LIFESTYLE CO., LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sa.growonline.footprint.view.utils.waveprogress;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;

/**
 * @author amyu
 */
public class WaveView extends View implements ViewTreeObserver.OnPreDrawListener {

  /**
   * {@link WaveView#mDropCircleAnimator}
   */
  private static final long DROP_CIRCLE_ANIMATOR_DURATION = 500;

  /**
   * {@link WaveView#mDropBounceVerticalAnimator}
   */
  private static final long DROP_VERTEX_ANIMATION_DURATION = 500;

  /**
   * {@link WaveView#mDropBounceVerticalAnimator}
   */
  private static final long DROP_BOUNCE_ANIMATOR_DURATION = 500;

  /**
   * {@link WaveView#mDisappearCircleAnimator}
   *    */
  private static final int DROP_REMOVE_ANIMATOR_DURATION = 200;

  private static final int WAVE_ANIMATOR_DURATION = 1000;

  private static final float MAX_WAVE_HEIGHT = 0.2f;

  private static final int SHADOW_COLOR = 0xFF000000;

   private float mDropCircleRadius = 100;

   private Paint mPaint;

   private Path mWavePath;

  private Path mDropTangentPath;

  private Path mDropCirclePath;

  private Paint mShadowPaint;

  private Path mShadowPath;

  private RectF mDropRect;

  private int mWidth;

  private float mCurrentCircleCenterY;

  private int mMaxDropHeight;

  private boolean mIsManualRefreshing = false;

  private boolean mDropHeightUpdated = false;

  private int mUpdateMaxDropHeight;
  private ValueAnimator mDropVertexAnimator;
  private ValueAnimator mDropBounceVerticalAnimator;

  /**
   * è�½ã�¡ã�Ÿç¸�ã�Œç¸¦ã�«ä¼¸ã�³ã‚‹ã�¨ã��ã�®Animator
   */
  private ValueAnimator mDropBounceHorizontalAnimator;

  /**
   * è�½ã�¡ã‚‹å††ã�®ä¸­å¿ƒåº§æ¨™ã�®Animator
   */
  private ValueAnimator mDropCircleAnimator;

  /**
   * è�½ã�¡ã�Ÿå††ã‚’æ¶ˆã�™ã�Ÿã‚�ã�®Animator
   */
  private ValueAnimator mDisappearCircleAnimator;

  /**
   * å¸°ã�£ã�¦ã��ã‚‹æ³¢ã�®ã�®Animator
   */
  private ValueAnimator mWaveReverseAnimator;

  /**
   * ãƒ™ã‚¸ã‚§æ›²ç·šã‚’å¼•ã��éš›ã�®åº§æ¨™
   * å·¦å�´ã�®2ã�¤ã�®ã‚¢ãƒ³ã‚«ãƒ¼ãƒ�ã‚¤ãƒ³ãƒˆã�§ã�„ã�„æ„Ÿã�˜ã�«å�³å�´ã�«ã‚‚
   */
  private static final float[][] BEGIN_PHASE_POINTS = {
      //1
      { 0.1655f, 0 },           //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.4188f, -0.0109f },    //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.4606f, -0.0049f },    //ã‚¢ãƒ³ã‚«ãƒ¼ãƒ�ã‚¤ãƒ³ãƒˆ

      //2
      { 0.4893f, 0.f },         //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.4893f, 0.f },         //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.5f, 0.f }             //ã‚¢ãƒ³ã‚«ãƒ¼ãƒ�ã‚¤ãƒ³ãƒˆ
  };

  private static final float[][] APPEAR_PHASE_POINTS = {
      //1
      { 0.1655f, 0.f },         //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.5237f, 0.0553f },     //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.4557f, 0.0936f },     //ã‚¢ãƒ³ã‚«ãƒ¼ãƒ�ã‚¤ãƒ³ãƒˆ

      //2
      { 0.3908f, 0.1302f },     //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.4303f, 0.2173f },     //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.5f, 0.2173f }         //ã‚¢ãƒ³ã‚«ãƒ¼ãƒ�ã‚¤ãƒ³ãƒˆ
  };

  private static final float[][] EXPAND_PHASE_POINTS = {
      //1
      { 0.1655f, 0.f },         //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.5909f, 0.0000f },     //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.4557f, 0.1642f },     //ã‚¢ãƒ³ã‚«ãƒ¼ãƒ�ã‚¤ãƒ³ãƒˆ

      //2
      { 0.3941f, 0.2061f },     //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.4303f, 0.2889f },     //ãƒ�ãƒ³ãƒ‰ãƒ«
      { 0.5f, 0.2889f }         //ã‚¢ãƒ³ã‚«ãƒ¼ãƒ�ã‚¤ãƒ³ãƒˆ
  };

  /**
   * å�„Animatorã�®AnimatorUpdateListener
   */
  private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener =
      new ValueAnimator.AnimatorUpdateListener() {
        @Override public void onAnimationUpdate(ValueAnimator valueAnimator) {
          postInvalidate();
        }
      };

  /**
   * Constructor
   * {@inheritDoc}
   */
  public WaveView(Context context) {
    super(context);
    getViewTreeObserver().addOnPreDrawListener(this);
    initView();
  }

  /**
   * Viewã�®ã‚µã‚¤ã‚ºã�Œæ±ºã�¾ã�£ã�Ÿã‚‰ {@link WaveView#mWidth} ã�«æ¨ªå¹…
   * {@inheritDoc}
   */
  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    mWidth = w;
    mDropCircleRadius = w / 14.4f;
    updateMaxDropHeight((int) Math.min(Math.min(w, h), getHeight() - mDropCircleRadius));
    super.onSizeChanged(w, h, oldw, oldh);
  }

  /**
   * æ��ç”»ã�•ã‚Œã�¦ã�‹ã‚‰ {@link WaveView#mMaxDropHeight} ã‚’æ›´æ–°ã�™ã‚‹
   * {@inheritDoc}
   */
  @Override public boolean onPreDraw() {
    getViewTreeObserver().removeOnPreDrawListener(this);
    if (mDropHeightUpdated) {
      updateMaxDropHeight(mUpdateMaxDropHeight);
    }
    return false;
  }

  @Override protected void onDraw(Canvas canvas) {
    //å¼•ã�£å¼µã�£ã�¦ã‚‹æœ€ä¸­ã�®æ³¢ã�¨çµ‚ã‚�ã�£ã�Ÿã�‚ã�¨ã�®æ³¢
    canvas.drawPath(mWavePath, mShadowPaint);
    canvas.drawPath(mWavePath, mPaint);
    mWavePath.rewind();

    //å††ã�Œè�½ã�¡ã‚‹éƒ¨åˆ†ã�®æ��ç”»
    mDropTangentPath.rewind();
    mDropCirclePath.rewind();
    float circleCenterY = (Float) mDropCircleAnimator.getAnimatedValue();
    float circleCenterX = mWidth / 2.f;
    mDropRect.setEmpty();
    //å††ã�®åº§æ¨™ã‚’RectFã�«ä¿�å­˜
    float scale = (Float) mDisappearCircleAnimator.getAnimatedValue();
    float vertical = (Float) mDropBounceVerticalAnimator.getAnimatedValue();
    float horizontal = (Float) mDropBounceHorizontalAnimator.getAnimatedValue();
    mDropRect.set(circleCenterX - mDropCircleRadius * (1 + vertical) * scale
            + mDropCircleRadius * horizontal / 2,
        circleCenterY + mDropCircleRadius * (1 + horizontal) * scale
            - mDropCircleRadius * vertical / 2,
        circleCenterX + mDropCircleRadius * (1 + vertical) * scale
            - mDropCircleRadius * horizontal / 2,
        circleCenterY - mDropCircleRadius * (1 + horizontal) * scale
            + mDropCircleRadius * vertical / 2);
    float vertex = (Float) mDropVertexAnimator.getAnimatedValue();
    mDropTangentPath.moveTo(circleCenterX, vertex);
    //å††ã�®æŽ¥ç‚¹(p1,q),(p2,q)
    double q =
        (Math.pow(mDropCircleRadius, 2) + circleCenterY * vertex - Math.pow(circleCenterY, 2)) / (
            vertex - circleCenterY);
    //2æ¬¡æ–¹ç¨‹å¼�è§£ã��ã�Ÿã‚�ã�®è§£ã�®å…¬å¼�
    double b = -2.0 * mWidth / 2;
    double c =
        Math.pow(q - circleCenterY, 2) + Math.pow(circleCenterX, 2) - Math.pow(mDropCircleRadius,
            2);
    double p1 = (-b + Math.sqrt(b * b - 4 * c)) / 2;
    double p2 = (-b - Math.sqrt(b * b - 4 * c)) / 2;
    mDropTangentPath.lineTo((float) p1, (float) q);
    mDropTangentPath.lineTo((float) p2, (float) q);
    mDropTangentPath.close();
    mShadowPath.set(mDropTangentPath);
    mShadowPath.addOval(mDropRect, Path.Direction.CCW);
    mDropCirclePath.addOval(mDropRect, Path.Direction.CCW);
    if (mDropVertexAnimator.isRunning()) {
      canvas.drawPath(mShadowPath, mShadowPaint);
    } else {
      canvas.drawPath(mDropCirclePath, mShadowPaint);
    }
    canvas.drawPath(mDropTangentPath, mPaint);
    canvas.drawPath(mDropCirclePath, mPaint);
  }

  @Override protected void onDetachedFromWindow() {
    if (mDisappearCircleAnimator != null) {
      mDisappearCircleAnimator.end();
      mDisappearCircleAnimator.removeAllUpdateListeners();
    }
    if (mDropCircleAnimator != null) {
      mDropCircleAnimator.end();
      mDropCircleAnimator.removeAllUpdateListeners();
    }
    if (mDropVertexAnimator != null) {
      mDropVertexAnimator.end();
      mDropVertexAnimator.removeAllUpdateListeners();
    }
    if (mWaveReverseAnimator != null) {
      mWaveReverseAnimator.end();
      mWaveReverseAnimator.removeAllUpdateListeners();
    }
    if (mDropBounceHorizontalAnimator != null) {
      mDropBounceHorizontalAnimator.end();
      mDropBounceHorizontalAnimator.removeAllUpdateListeners();
    }
    if (mDropBounceVerticalAnimator != null) {
      mDropBounceVerticalAnimator.end();
      mDropBounceVerticalAnimator.removeAllUpdateListeners();
    }
    super.onDetachedFromWindow();
  }

  private void initView() {
    setUpPaint();
    setUpPath();
    resetAnimator();

    mDropRect = new RectF();
    setLayerType(View.LAYER_TYPE_SOFTWARE, null);
  }

  private void setUpPaint() {
    mPaint = new Paint();
    mPaint.setColor(0xff2196F3);
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.FILL);

    mShadowPaint = new Paint();
    mShadowPaint.setShadowLayer(10.0f, 0.0f, 2.0f, SHADOW_COLOR);
  }

  private void setUpPath() {
    mWavePath = new Path();
    mDropTangentPath = new Path();
    mDropCirclePath = new Path();
    mShadowPath = new Path();
  }

  private void resetAnimator() {
    mDropVertexAnimator = ValueAnimator.ofFloat(0.f, 0.f);
    mDropBounceVerticalAnimator = ValueAnimator.ofFloat(0.f, 0.f);
    mDropBounceHorizontalAnimator = ValueAnimator.ofFloat(0.f, 0.f);
    mDropCircleAnimator = ValueAnimator.ofFloat(-1000.f, -1000.f);
    mDropCircleAnimator.start();
    mDisappearCircleAnimator = ValueAnimator.ofFloat(1.f, 1.f);
    mDisappearCircleAnimator.setDuration(1); // immediately finish animation cycle
    mDisappearCircleAnimator.start();
  }

  private void onPreDragWave() {
    if (mWaveReverseAnimator != null) {
      if (mWaveReverseAnimator.isRunning()) {
        mWaveReverseAnimator.cancel();
      }
    }
  }

  public void manualRefresh() {
    if (mIsManualRefreshing) {
      return;
    }
    mIsManualRefreshing = true;
    mDropCircleAnimator = ValueAnimator.ofFloat(mMaxDropHeight, mMaxDropHeight);
    mDropCircleAnimator.start();
    mDropVertexAnimator = ValueAnimator.ofFloat(mMaxDropHeight - mDropCircleRadius,
        mMaxDropHeight - mDropCircleRadius);
    mDropVertexAnimator.start();
    mCurrentCircleCenterY = mMaxDropHeight;
    postInvalidate();
  }

  public void beginPhase(float move1) {
    onPreDragWave();
    //å††ã‚’æ��ç”»ã�—å§‹ã‚�ã‚‹å‰�ã�®å¼•ã�£å¼µã�£ã�Ÿã‚‰è†¨ã‚Œã‚‹æ³¢ã�®éƒ¨åˆ†ã�®æ��ç”»
    mWavePath.moveTo(0, 0);
    //å·¦å�Šåˆ†ã�®æ��ç”»
    mWavePath.cubicTo(mWidth * BEGIN_PHASE_POINTS[0][0], BEGIN_PHASE_POINTS[0][1],
        mWidth * BEGIN_PHASE_POINTS[1][0], mWidth * (BEGIN_PHASE_POINTS[1][1] + move1),
        mWidth * BEGIN_PHASE_POINTS[2][0], mWidth * (BEGIN_PHASE_POINTS[2][1] + move1));
    mWavePath.cubicTo(mWidth * BEGIN_PHASE_POINTS[3][0],
        mWidth * (BEGIN_PHASE_POINTS[3][1] + move1), mWidth * BEGIN_PHASE_POINTS[4][0],
        mWidth * (BEGIN_PHASE_POINTS[4][1] + move1), mWidth * BEGIN_PHASE_POINTS[5][0],
        mWidth * (BEGIN_PHASE_POINTS[5][1] + move1));
    //å�³å�Šåˆ†ã�®æ��ç”»
    mWavePath.cubicTo(mWidth - mWidth * BEGIN_PHASE_POINTS[4][0],
        mWidth * (BEGIN_PHASE_POINTS[4][1] + move1), mWidth - mWidth * BEGIN_PHASE_POINTS[3][0],
        mWidth * (BEGIN_PHASE_POINTS[3][1] + move1), mWidth - mWidth * BEGIN_PHASE_POINTS[2][0],
        mWidth * (BEGIN_PHASE_POINTS[2][1] + move1));
    mWavePath.cubicTo(mWidth - mWidth * BEGIN_PHASE_POINTS[1][0],
        mWidth * (BEGIN_PHASE_POINTS[1][1] + move1), mWidth - mWidth * BEGIN_PHASE_POINTS[0][0],
        BEGIN_PHASE_POINTS[0][1], mWidth, 0);
    ViewCompat.postInvalidateOnAnimation(this);
  }

  public void appearPhase(float move1, float move2) {
    onPreDragWave();
    mWavePath.moveTo(0, 0);
    //å·¦å�Šåˆ†ã�®æ��ç”»
    mWavePath.cubicTo(mWidth * APPEAR_PHASE_POINTS[0][0], mWidth * APPEAR_PHASE_POINTS[0][1],
        mWidth * Math.min(BEGIN_PHASE_POINTS[1][0] + move2, APPEAR_PHASE_POINTS[1][0]),
        mWidth * Math.max(BEGIN_PHASE_POINTS[1][1] + move1 - move2, APPEAR_PHASE_POINTS[1][1]),
        mWidth * Math.max(BEGIN_PHASE_POINTS[2][0] - move2, APPEAR_PHASE_POINTS[2][0]),
        mWidth * Math.max(BEGIN_PHASE_POINTS[2][1] + move1 - move2, APPEAR_PHASE_POINTS[2][1]));
    mWavePath.cubicTo(
        mWidth * Math.max(BEGIN_PHASE_POINTS[3][0] - move2, APPEAR_PHASE_POINTS[3][0]),
        mWidth * Math.min(BEGIN_PHASE_POINTS[3][1] + move1 + move2, APPEAR_PHASE_POINTS[3][1]),
        mWidth * Math.max(BEGIN_PHASE_POINTS[4][0] - move2, APPEAR_PHASE_POINTS[4][0]),
        mWidth * Math.min(BEGIN_PHASE_POINTS[4][1] + move1 + move2, APPEAR_PHASE_POINTS[4][1]),
        mWidth * APPEAR_PHASE_POINTS[5][0],
        mWidth * Math.min(BEGIN_PHASE_POINTS[0][1] + move1 + move2, APPEAR_PHASE_POINTS[5][1]));
    //å�³å�Šåˆ†ã�®æ��ç”»
    mWavePath.cubicTo(
        mWidth - mWidth * Math.max(BEGIN_PHASE_POINTS[4][0] - move2, APPEAR_PHASE_POINTS[4][0]),
        mWidth * Math.min(BEGIN_PHASE_POINTS[4][1] + move1 + move2, APPEAR_PHASE_POINTS[4][1]),
        mWidth - mWidth * Math.max(BEGIN_PHASE_POINTS[3][0] - move2, APPEAR_PHASE_POINTS[3][0]),
        mWidth * Math.min(BEGIN_PHASE_POINTS[3][1] + move1 + move2, APPEAR_PHASE_POINTS[3][1]),
        mWidth - mWidth * Math.max(BEGIN_PHASE_POINTS[2][0] - move2, APPEAR_PHASE_POINTS[2][0]),
        mWidth * Math.max(BEGIN_PHASE_POINTS[2][1] + move1 - move2, APPEAR_PHASE_POINTS[2][1]));
    mWavePath.cubicTo(
        mWidth - mWidth * Math.min(BEGIN_PHASE_POINTS[1][0] + move2, APPEAR_PHASE_POINTS[1][0]),
        mWidth * Math.max(BEGIN_PHASE_POINTS[1][1] + move1 - move2, APPEAR_PHASE_POINTS[1][1]),
        mWidth - mWidth * APPEAR_PHASE_POINTS[0][0], mWidth * APPEAR_PHASE_POINTS[0][1], mWidth, 0);
    mCurrentCircleCenterY =
        mWidth * Math.min(BEGIN_PHASE_POINTS[3][1] + move1 + move2, APPEAR_PHASE_POINTS[3][1])
            + mDropCircleRadius;
    ViewCompat.postInvalidateOnAnimation(this);
  }

  public void expandPhase(float move1, float move2, float move3) {
    onPreDragWave();
    mWavePath.moveTo(0, 0);
    //å·¦å�Šåˆ†ã�®æ��ç”»
    mWavePath.cubicTo(mWidth * EXPAND_PHASE_POINTS[0][0], mWidth * EXPAND_PHASE_POINTS[0][1],
        mWidth * Math.min(
            Math.min(BEGIN_PHASE_POINTS[1][0] + move2, APPEAR_PHASE_POINTS[1][0]) + move3,
            EXPAND_PHASE_POINTS[1][0]), mWidth * Math.max(
            Math.max(BEGIN_PHASE_POINTS[1][1] + move1 - move2, APPEAR_PHASE_POINTS[1][1]) - move3,
            EXPAND_PHASE_POINTS[1][1]),
        mWidth * Math.max(BEGIN_PHASE_POINTS[2][0] - move2, EXPAND_PHASE_POINTS[2][0]),
        mWidth * Math.min(
            Math.max(BEGIN_PHASE_POINTS[2][1] + move1 - move2, APPEAR_PHASE_POINTS[2][1]) + move3,
            EXPAND_PHASE_POINTS[2][1]));
    mWavePath.cubicTo(mWidth * Math.min(
        Math.max(BEGIN_PHASE_POINTS[3][0] - move2, APPEAR_PHASE_POINTS[3][0]) + move3,
        EXPAND_PHASE_POINTS[3][0]), mWidth * Math.min(
        Math.min(BEGIN_PHASE_POINTS[3][1] + move1 + move2, APPEAR_PHASE_POINTS[3][1]) + move3,
        EXPAND_PHASE_POINTS[3][1]),
        mWidth * Math.max(BEGIN_PHASE_POINTS[4][0] - move2, EXPAND_PHASE_POINTS[4][0]),
        mWidth * Math.min(
            Math.min(BEGIN_PHASE_POINTS[4][1] + move1 + move2, APPEAR_PHASE_POINTS[4][1]) + move3,
            EXPAND_PHASE_POINTS[4][1]), mWidth * EXPAND_PHASE_POINTS[5][0], mWidth * Math.min(
            Math.min(BEGIN_PHASE_POINTS[0][1] + move1 + move2, APPEAR_PHASE_POINTS[5][1]) + move3,
            EXPAND_PHASE_POINTS[5][1]));

    //å�³å�Šåˆ†ã�®æ��ç”»
    mWavePath.cubicTo(
        mWidth - mWidth * Math.max(BEGIN_PHASE_POINTS[4][0] - move2, EXPAND_PHASE_POINTS[4][0]),
        mWidth * Math.min(
            Math.min(BEGIN_PHASE_POINTS[4][1] + move1 + move2, APPEAR_PHASE_POINTS[4][1]) + move3,
            EXPAND_PHASE_POINTS[4][1]), mWidth - mWidth * Math.min(
            Math.max(BEGIN_PHASE_POINTS[3][0] - move2, APPEAR_PHASE_POINTS[3][0]) + move3,
            EXPAND_PHASE_POINTS[3][0]), mWidth * Math.min(
            Math.min(BEGIN_PHASE_POINTS[3][1] + move1 + move2, APPEAR_PHASE_POINTS[3][1]) + move3,
            EXPAND_PHASE_POINTS[3][1]),
        mWidth - mWidth * Math.max(BEGIN_PHASE_POINTS[2][0] - move2, EXPAND_PHASE_POINTS[2][0]),
        mWidth * Math.min(
            Math.max(BEGIN_PHASE_POINTS[2][1] + move1 - move2, APPEAR_PHASE_POINTS[2][1]) + move3,
            EXPAND_PHASE_POINTS[2][1]));
    mWavePath.cubicTo(mWidth - mWidth * Math.min(
        Math.min(BEGIN_PHASE_POINTS[1][0] + move2, APPEAR_PHASE_POINTS[1][0]) + move3,
        EXPAND_PHASE_POINTS[1][0]), mWidth * Math.max(
        Math.max(BEGIN_PHASE_POINTS[1][1] + move1 - move2, APPEAR_PHASE_POINTS[1][1]) - move3,
        EXPAND_PHASE_POINTS[1][1]), mWidth - mWidth * EXPAND_PHASE_POINTS[0][0],
        mWidth * EXPAND_PHASE_POINTS[0][1], mWidth, 0);
    mCurrentCircleCenterY = mWidth * Math.min(
        Math.min(BEGIN_PHASE_POINTS[3][1] + move1 + move2, APPEAR_PHASE_POINTS[3][1]) + move3,
        EXPAND_PHASE_POINTS[3][1]) + mDropCircleRadius;
    ViewCompat.postInvalidateOnAnimation(this);
  }

  /**
   * @param height é«˜ã�•
   */
  private void updateMaxDropHeight(int height) {
    if (500 * (mWidth / 1440.f) > height) {
      Log.w("WaveView", "DropHeight is more than " + 500 * (mWidth / 1440.f));
      return;
    }
    mMaxDropHeight = (int) Math.min(height, getHeight() - mDropCircleRadius);
    if (mIsManualRefreshing) {
      mIsManualRefreshing = false;
      manualRefresh();
    }
  }

  public void startDropAnimation() {
    // show dropBubble again
    mDisappearCircleAnimator = ValueAnimator.ofFloat(1.f, 1.f);
    mDisappearCircleAnimator.setDuration(1);
    mDisappearCircleAnimator.start();

    mDropCircleAnimator = ValueAnimator.ofFloat(500 * (mWidth / 1440.f), mMaxDropHeight);
    mDropCircleAnimator.setDuration(DROP_CIRCLE_ANIMATOR_DURATION);
    mDropCircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        mCurrentCircleCenterY = (float) animation.getAnimatedValue();
        ViewCompat.postInvalidateOnAnimation(WaveView.this);
      }
    });
    mDropCircleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    mDropCircleAnimator.start();

    mDropVertexAnimator = ValueAnimator.ofFloat(0.f, mMaxDropHeight - mDropCircleRadius);
    mDropVertexAnimator.setDuration(DROP_VERTEX_ANIMATION_DURATION);
    mDropVertexAnimator.addUpdateListener(mAnimatorUpdateListener);
    mDropVertexAnimator.start();

    mDropBounceVerticalAnimator = ValueAnimator.ofFloat(0.f, 1.f);
    mDropBounceVerticalAnimator.setDuration(DROP_BOUNCE_ANIMATOR_DURATION);
    mDropBounceVerticalAnimator.addUpdateListener(mAnimatorUpdateListener);
    mDropBounceVerticalAnimator.setInterpolator(new DropBounceInterpolator());
    mDropBounceVerticalAnimator.setStartDelay(DROP_VERTEX_ANIMATION_DURATION);
    mDropBounceVerticalAnimator.start();

    mDropBounceHorizontalAnimator = ValueAnimator.ofFloat(0.f, 1.f);
    mDropBounceHorizontalAnimator.setDuration(DROP_BOUNCE_ANIMATOR_DURATION);
    mDropBounceHorizontalAnimator.addUpdateListener(mAnimatorUpdateListener);
    mDropBounceHorizontalAnimator.setInterpolator(new DropBounceInterpolator());
    mDropBounceHorizontalAnimator.setStartDelay(
        (long) (DROP_VERTEX_ANIMATION_DURATION + DROP_BOUNCE_ANIMATOR_DURATION * 0.25));
    mDropBounceHorizontalAnimator.start();
  }

  public void startDisappearCircleAnimation() {
    mDisappearCircleAnimator = ValueAnimator.ofFloat(1.f, 0.f);
    mDisappearCircleAnimator.addUpdateListener(mAnimatorUpdateListener);
    mDisappearCircleAnimator.setDuration(DROP_REMOVE_ANIMATOR_DURATION);
    mDisappearCircleAnimator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animator) {

      }

      @Override public void onAnimationEnd(Animator animator) {
        //ã‚¢ãƒ‹ãƒ¡ãƒ¼ã‚·ãƒ§ãƒ³ä¿®æ—…æ™‚ã�«Animatorã‚’ãƒªã‚»ãƒƒãƒˆã�™ã‚‹ã�“ã�¨ã�«ã‚ˆã‚Šè�½ã�¡ã�¦ã��ã‚‹å††ã�®åˆ�æœŸä½�ç½®ã‚’-100.fã�«ã�™ã‚‹
        resetAnimator();
        mIsManualRefreshing = false;
      }

      @Override public void onAnimationCancel(Animator animator) {

      }

      @Override public void onAnimationRepeat(Animator animator) {

      }
    });
    mDisappearCircleAnimator.start();
  }

  /**
   * @param h æ³¢ã�Œå§‹ã�¾ã‚‹é«˜ã�•
   */
  public void startWaveAnimation(float h) {
    h = Math.min(h, MAX_WAVE_HEIGHT) * mWidth;
    mWaveReverseAnimator = ValueAnimator.ofFloat(h, 0.f);
    mWaveReverseAnimator.setDuration(WAVE_ANIMATOR_DURATION);
    mWaveReverseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float h = (Float) valueAnimator.getAnimatedValue();
        mWavePath.moveTo(0, 0);
        mWavePath.quadTo(0.25f * mWidth, 0, 0.333f * mWidth, h * 0.5f);
        mWavePath.quadTo(mWidth * 0.5f, h * 1.4f, 0.666f * mWidth, h * 0.5f);
        mWavePath.quadTo(0.75f * mWidth, 0, mWidth, 0);
        postInvalidate();
      }
    });
    mWaveReverseAnimator.setInterpolator(new BounceInterpolator());
    mWaveReverseAnimator.start();
  }

  public void animationDropCircle() {
    if (mDisappearCircleAnimator.isRunning()) {
      return;
    }
    startDropAnimation();
    startWaveAnimation(0.1f);
  }

  public float getCurrentCircleCenterY() {
    return mCurrentCircleCenterY;
  }

  /**
   * @param maxDropHeight ã�‚ã‚‹ç¨‹åº¦ã�®é«˜ã�•
   */
  public void setMaxDropHeight(int maxDropHeight) {
    if (mDropHeightUpdated) {
      updateMaxDropHeight(maxDropHeight);
    } else {
      mUpdateMaxDropHeight = maxDropHeight;
      mDropHeightUpdated = true;
      if (getViewTreeObserver().isAlive()) {
        getViewTreeObserver().removeOnPreDrawListener(this);
        getViewTreeObserver().addOnPreDrawListener(this);
      }
    }
  }

  public boolean isDisappearCircleAnimatorRunning() {
    return mDisappearCircleAnimator.isRunning();
  }

  /**
   * @param radius å½±ã�®æ·±ã�•
   */
  public void setShadowRadius(int radius) {
    mShadowPaint.setShadowLayer(radius, 0.0f, 2.0f, SHADOW_COLOR);
  }

  /**
   * WaveView is colored by given color (including alpha)
   *
   * @param color ARGB color. WaveView will be colored by Black if rgb color is provided.
   * @see Paint#setColor(int)
   */
  public void setWaveColor(int color) {
    mPaint.setColor(color);
    invalidate();
  }

  public void setWaveARGBColor(int a, int r, int g, int b) {
    mPaint.setARGB(a, r, g, b);
    invalidate();
  }
}
