package testrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;

import java.util.List;

/**
 * Created by maomao on 2017/4/5.
 */

public class DiffCallBack extends DiffUtil.Callback {
    private List<TestBean> oldLists, newLists;

    public DiffCallBack(List<TestBean> oldLists, List<TestBean> newLists) {
        this.oldLists = oldLists;
        this.newLists = newLists;
    }

    @Override
    public int getOldListSize() {return oldLists != null ? oldLists.size() : 0;}

    @Override
    public int getNewListSize() {return newLists != null ? newLists.size() : 0;}

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Log.i("test1", "" + (oldLists.get(oldItemPosition).getName() == (newLists.get(newItemPosition).getName())));
        Log.i("test1", "" + oldItemPosition + "," + newItemPosition);
        Log.i("test1", "name" + oldLists.get(oldItemPosition).getName() + "," + newLists.get(newItemPosition).getName());
        return oldLists.get(oldItemPosition).getName() == (newLists.get(newItemPosition).getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Log.i("test1", "areContentsTheSame");
        TestBean beanOld = oldLists.get(oldItemPosition);
        TestBean beanNew = newLists.get(newItemPosition);
        if (beanOld.getPic() != beanNew.getPic()) {
            return false;//如果有内容不同，就返回false
        }
        if (beanOld.getName() != beanNew.getName()) {
            return false;//如果有内容不同，就返回false
        }
        return true; //默认两个data内容是相同的
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //实现这个方法 就能成为文艺青年中的文艺青年
        // 定向刷新中的部分更新
        // 效率最高
        //只是没有了ItemChange的白光一闪动画，（反正我也觉得不太重要）
        TestBean oldBean = oldLists.get(oldItemPosition);
        TestBean newBean = newLists.get(newItemPosition);

        //这里就不用比较核心字段了,一定相等
        Bundle payload = new Bundle();
        if (oldBean.getPic() != newBean.getPic()) {
            payload.putInt("KEY_PIC", newBean.getPic());
        }
        if (oldBean.getName() != newBean.getName()) {
            payload.putInt("KEY_NAME", newBean.getName());
        }
        if (payload.size() == 0)//如果没有变化 就传空
            return null;
        return payload;
    }
}
