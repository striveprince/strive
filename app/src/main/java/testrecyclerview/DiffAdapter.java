package testrecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.R;

import java.util.List;

public class DiffAdapter extends RecyclerView.Adapter<DiffHolder> {
    private final static String TAG = "zxt";
    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public DiffAdapter(Context mContext, List<TestBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<TestBean> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public DiffHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiffHolder(mInflater.inflate(R.layout.item_diff, parent, false));
    }

    @Override
    public void onBindViewHolder(final DiffHolder holder, final int position) {
        TestBean bean = mDatas.get(position);
        holder.setIv(bean.getPic());
        holder.setTv1(bean.getName());
    }
    @Override
    public void onBindViewHolder(DiffHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            //文艺青年中的文青
            Bundle payload = (Bundle) payloads.get(0);//取出我们在getChangePayload（）方法返回的bundle
            TestBean bean = mDatas.get(position);//取出新数据源，（可以不用）
            for (String key : payload.keySet()) {
                switch (key) {
                    case "KEY_PIC":
                        holder.setIv(payload.getInt(key));
                        break;
                    case "KEY_NAME":
                        holder.setTv1(payload.getInt(key));
                        break;
                    default:
                        break;
                }
            }
        }
    }
    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

//    class DiffVH extends RecyclerView.ViewHolder {
//        TextView tv1;
//        ImageView iv;
//
//        public DiffVH(View itemView) {
//            super(itemView);
//            tv1 = (TextView) itemView.findViewById(R.id.tv1);
//            iv = (ImageView) itemView.findViewById(R.id.iv);
//        }
//
//
//
//}


}