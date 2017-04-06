package testrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by maomao on 2017/4/6.
 */

public class DiffHolder extends RecyclerView.ViewHolder  {
    private TextView tv1;
    private ImageView iv;
    public DiffHolder(View itemView) {
        super(itemView);
        tv1 = (TextView) itemView.findViewById(R.id.tv1);
        iv = (ImageView) itemView.findViewById(R.id.iv);
    }

    public void setTv1(int tv) {
        this.tv1.setText(String.valueOf(tv));
    }

    public void setIv(int iv) {
        this.iv.setImageResource(iv);
    }
}
