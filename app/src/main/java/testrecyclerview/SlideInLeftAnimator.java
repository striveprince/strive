package testrecyclerview;

/**
 * Copyright (C) 2015 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Interpolator;

public class SlideInLeftAnimator extends BaseItemAnimator {

  public SlideInLeftAnimator() {

  }
  public SlideInLeftAnimator(Interpolator interpolator) {
    mInterpolator = interpolator;
  }

  @Override protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
    ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView, "translationX", 0,holder.itemView.getRootView().getWidth());
    animator.setInterpolator(mInterpolator);
    animator.setDuration(500);
    animator.start();
  }
  @Override protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
    ViewCompat.setTranslationX(holder.itemView, -holder.itemView.getRootView().getWidth());
  }
  @Override protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
    ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView, "translationX", -holder.itemView.getRootView().getWidth(),0);
    animator.setInterpolator(mInterpolator);
    animator.setDuration(500);
    animator.start();
  }
  /**
   * 创建执行animateChange的动画Info对象，内部封装了所需要执行一个动画类的相关信息
   * 起始alpha属性动画，和起始旋转属性动画
   */

//  @Override
//  public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preInfo, @NonNull ItemHolderInfo postInfo) {
//
//    if (oldHolder != newHolder) {
//      //第一次显示所有的RecyclerView时，新旧ViewHolder是不相等的
//      return super.animateChange(oldHolder, newHolder, preInfo, postInfo);
//    }
//
//   return true;
//  }
}
