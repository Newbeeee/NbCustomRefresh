package com.newbeeee.qt.nbcustomrefresh;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        setRefresh();
        setRecyclerView();
    }

    private void setRefresh() {
        CustomProgressDrawable mDrawable = new CustomProgressDrawable(this, mSwipeRefreshLayout);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mountain);
        mDrawable.setBitmap(bitmap);
        mSwipeRefreshLayout.setProgressView(mDrawable);
        mSwipeRefreshLayout.setBackgroundColor(getResources().getColor(R.color.refreshBg));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
//          在子线程睡眠三秒后发送消息停止刷新。
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0);
                    }
                }).start();
            }
        });
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CheeseRvAdapter(Cheeses.getRandomSublist(Cheeses.sCheeseStrings, 30)));
    }
}
