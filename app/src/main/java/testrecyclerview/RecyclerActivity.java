package testrecyclerview;

import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    private List<TestBean> mDatas;
    private RecyclerView mRv;
    private DiffAdapter mAdapter;
    private List<TestBean> mNewDatas = new ArrayList<>();//增加一个变量暂存newList
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initData();
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DiffAdapter(this, mDatas);
        mRv.setAdapter(mAdapter);
        mRv.setItemAnimator(new SlideInLeftAnimator());
        mRv.addOnItemTouchListener(new OnRecyclerItemClickListener(mRv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Toast.makeText(getBaseContext(), String.valueOf(mDatas.get(vh.getLayoutPosition()).getName()), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                //判断被拖拽的是否是前两个，如果不是则执行拖拽
                Toast.makeText(getBaseContext(), "长按", Toast.LENGTH_SHORT).show();
                    mItemTouchHelper.startDrag(vh);
                    //获取系统震动服务
                    Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                    vib.vibrate(70);
            }
        });
        DiffItemTouchHelp diffItemTouchHelp = new DiffItemTouchHelp(mAdapter,mDatas);
        diffItemTouchHelp.setMove((fromPosition, toPosition) -> {
            mNewDatas.clear();
            mNewDatas.addAll(mDatas);
            TestBean fromTestBean = mNewDatas.get(fromPosition);//模拟数据位移
            TestBean toTestBean = mNewDatas.get(toPosition);//模拟数据位移
            mNewDatas.set(toPosition, fromTestBean);
            mNewDatas.set(fromPosition, toTestBean);
            new Thread(() -> {
                //放在子线程中计算DiffResult
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mDatas, mNewDatas), true);
                Message message = mHandler.obtainMessage(H_CODE_ADD);
                message.obj = diffResult;//obj存放DiffResult
                message.sendToTarget();
            }).start();
        });
        mItemTouchHelper = new ItemTouchHelper(diffItemTouchHelp);
        mItemTouchHelper.attachToRecyclerView(mRv);


        }

    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(new TestBean(mDatas.size(), R.mipmap.navigation1));
        mDatas.add(new TestBean(mDatas.size(), R.mipmap.navigation2));
        mDatas.add(new TestBean(mDatas.size(), R.mipmap.navigation3));
        mDatas.add(new TestBean(mDatas.size(), R.mipmap.navigation4));
        mDatas.add(new TestBean(mDatas.size(), R.mipmap.navigation5));
    }

    /**
     * 模拟刷新操作
     *
     * @param view
     */
    private static final int H_CODE_ADD = 1;

    public void add(View view) {
        mNewDatas.clear();
        mNewDatas.addAll(mDatas);

        mNewDatas.add(new TestBean(mDatas.size()+1, R.mipmap.navigation6));//模拟新增数据
        //别忘了将新数据给Adapter

        new Thread(() -> {
            //放在子线程中计算DiffResult
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mDatas, mNewDatas), true);
            Message message = mHandler.obtainMessage(H_CODE_ADD);
            message.obj = diffResult;//obj存放DiffResult
            message.sendToTarget();
        }).start();
    }


    public void change(View view) {
        mNewDatas.clear();
        mNewDatas.addAll(mDatas);
//        TestBean testBean = mNewDatas.get(0);
//        testBean.setName(99999);
        mNewDatas.set(0,new TestBean(12828,R.mipmap.navigation7));//模拟修改数据
        new Thread(() -> {
            //放在子线程中计算DiffResult
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mDatas, mNewDatas), true);
            Message message = mHandler.obtainMessage(H_CODE_ADD);
            message.obj = diffResult;//obj存放DiffResult
            message.sendToTarget();
        }).start();

    } public void move(View view) {
        mNewDatas.clear();
        mNewDatas.addAll(mDatas);
        TestBean testBean = mNewDatas.get(1);//模拟数据位移
        mNewDatas.remove(testBean);
        mNewDatas.add(testBean);
        new Thread(() -> {
            //放在子线程中计算DiffResult
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mDatas, mNewDatas), true);
            Message message = mHandler.obtainMessage(H_CODE_ADD);
            message.obj = diffResult;//obj存放DiffResult
            message.sendToTarget();
        }).start();

    }


    public void remove(View view) {
        mNewDatas.clear();
        mNewDatas.addAll(mDatas);
        if (mNewDatas.size()>0)
            mNewDatas.remove(0);
        new Thread(() -> {
            //放在子线程中计算DiffResult
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mDatas, mNewDatas), true);
            Message message = mHandler.obtainMessage(H_CODE_ADD);
            message.obj = diffResult;//obj存放DiffResult
            message.sendToTarget();
        }).start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case H_CODE_ADD:
                    //取出Result
                    DiffUtil.DiffResult diffResult = (DiffUtil.DiffResult) msg.obj;
                    //利用DiffUtil.DiffResult对象的dispatchUpdatesTo（）方法，传入RecyclerView的Adapter，轻松成为文艺青年
                    diffResult.dispatchUpdatesTo(mAdapter);
                    //别忘了将新数据给Adapter
                    mDatas.clear();
                    mDatas.addAll(mNewDatas);
                    mAdapter.setDatas(mDatas);
                    break;
            }
        }
    };
}
