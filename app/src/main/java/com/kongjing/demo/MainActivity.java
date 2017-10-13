package com.kongjing.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.kongjing.multilevelmenu.R;
import java.util.ArrayList;
import java.util.List;
import xyz.kongjing.treelist.Node;
import xyz.kongjing.treelist.TreeListViewAdapter;

/**
 * TODO 添加默认全选按钮
 */
public class MainActivity extends AppCompatActivity {

    protected List<Node> mDatas = new ArrayList<Node>();
    private TreeListViewAdapter mAdapter;
    boolean FLAG = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mTree = (ListView) findViewById(R.id.lv_tree);
        //第一个参数  ListView
        //第二个参数  上下文
        //第三个参数  数据集
        //第四个参数  默认展开层级数 0为不展开
        //第五个参数  展开的图标
        //第六个参数  闭合的图标
        initDatas();
        mAdapter = new SimpleTreeAdapter(mTree, this,
            mDatas, 0,R.drawable.tree_ex,R.drawable.tree_ec);
        mTree.setAdapter(mAdapter);

        findViewById(R.id.btnMainDialog).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
            }
        });

        /**
         * 全选或者全不选
         */
        findViewById(R.id.btnMainChooseAll).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (!FLAG){
                    mAdapter.setAllNodesSelected(true);
                    mAdapter.notifyDataSetChanged();
                    FLAG = true;
                }else {
                    mAdapter.setAllNodesSelected(false);
                    mAdapter.notifyDataSetChanged();
                    FLAG = false;
                }
            }
        });
    }

    private void initDatas()
    {
        // id , pid , label , 其他属性
        mDatas.add(new Node("1", "-1", "文件管理系统"));

        mDatas.add(new Node(2+"", 1+"", "游戏"));
        mDatas.add(new Node(3+"", 1+"", "文档"));
        mDatas.add(new Node(4+"", 1+"", "程序"));
        mDatas.add(new Node(5+"", 2+"", "war3"));
        mDatas.add(new Node(6+"", 2+"", "刀塔传奇"));

        mDatas.add(new Node(7+"", 4+"", "面向对象"));
        mDatas.add(new Node(8+"", 4+"", "非面向对象"));

        mDatas.add(new Node(9+"", 7+"", "C++"));
        mDatas.add(new Node(10+"", 7+"", "JAVA"));
        mDatas.add(new Node(11+"", 7+"", "Javascript"));
        mDatas.add(new Node(12+"", 8+"", "C"));
        mDatas.add(new Node(13+"", 12+"", "C"));
        mDatas.add(new Node(14+"", 13+"", "C"));
        mDatas.add(new Node(15+"", 14+"", "C"));
        mDatas.add(new Node(16+"", 15+"", "C"));
    }
}
