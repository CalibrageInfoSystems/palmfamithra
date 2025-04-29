package in.calibrage.palm360fa.common;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;

import androidx.recyclerview.widget.RecyclerView;


/*
* This util helpfull For Help Loading Animations in Lists
* */
public class AnimationUtil {

    public static void animate(RecyclerView.ViewHolder holder, boolean goesdown) {
        AnimatorSet animatorSet = new AnimatorSet();
        // Animate from bottom
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator animatorTransilateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesdown == true ? 200 : -200, 0);
        animatorTransilateY.setDuration(1500);

       // Animate from left
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator animatorTransilateX = ObjectAnimator.ofFloat(holder.itemView, "translationX", 200, 0);
        animatorTransilateX.setDuration(1500);

        // Animate Rottate
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator rotation = ObjectAnimator.ofFloat(holder.itemView, "rotation", 360);
        rotation.setDuration(1000);
      /*  if(Common.anim_type == Common.BOTTOM)
        {
            animatorSet.playTogether(animatorTransilateY);
            animatorSet.start();
        }else if(Common.anim_type == Common.LEFT)
        {
            animatorSet.playTogether(animatorTransilateX);
            animatorSet.start();
        }else if(Common.anim_type == Common.RIGHT)
        {
            animatorSet.playTogether(rotation);
            animatorSet.start();
        }
*/
        animatorSet.playTogether(animatorTransilateY);
        animatorSet.start();

    }
}