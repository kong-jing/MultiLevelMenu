package com.kongjing.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import com.google.gson.Gson;
import com.kongjing.demo.adapter.DialogAdapter;
import com.kongjing.demo.data.bean.DepartmentBean;
import com.kongjing.demo.data.bean.HospitalBean;
import com.kongjing.demo.data.json.JsonHospital;
import com.kongjing.multilevelmenu.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import java.util.ArrayList;
import java.util.List;
import xyz.kongjing.treelist.Node;

/**
 * 对话框形式的菜单
 * @author Jing 
 */
public class DialogActivity extends AppCompatActivity {
  private static final String TAG = "DialogActivity";

  Button btnDialog;//按钮
  protected List<Node> mTreeDatas = new ArrayList<Node>();//树形菜单数据集合
  DialogPlus dialog;//对话框
  DialogAdapter mDialogAdapter;//对话框中的树形多级菜单适配器
  HospitalBean hospitalData;
  DepartmentBean departmentData;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dialog);
    initData();
    btnDialog = (Button) findViewById(R.id.btn_Dialog);
    btnDialog.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //想在这里打开一个底部对话框,能够进行树形菜单的选择
        dialog = DialogPlus.newDialog(DialogActivity.this)
            .setOnItemClickListener(null)
            .setExpanded(true)//是否可以上下拉动
            .setContentHeight(ViewGroup.LayoutParams.MATCH_PARENT)
            .setOverlayBackgroundResource(android.R.color.transparent)
            .setContentHolder(new ViewHolder(R.layout.listview_dialog_multimenu))
            .setHeader(R.layout.dialog_grid_header)
            .setGravity(Gravity.BOTTOM)
            .create();
        dialog.show();
        //获取一个listview的实例
        ListView listView = (ListView) dialog.findViewById(R.id.lv_tree_menu);
        //确认按钮
        dialog.findViewById(R.id.btn_dialog_confirm).setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sbLibId = new StringBuilder();
            //获取排序过的nodes
            //如果不需要刻意直接用 mDatas既可
            final List<Node> allNodes = mDialogAdapter.getAllNodes();
            for (int i = 0; i < allNodes.size(); i++) {
              if (allNodes.get(i).isChecked()) {
                if (allNodes.get(i).bean != null) {
                  DepartmentBean.DataBean dataBean = (DepartmentBean.DataBean) allNodes.get(i).bean;
                  sb.append(dataBean.getName() + ",");
                  sbLibId.append(dataBean.getId() + ",");
                }
              }
            }
            sbLibId.toString();
            String strNodesName = sb.toString();
            btnDialog.setText(strNodesName);
            dialog.dismiss();
          }
        });
        dialog.findViewById(R.id.btn_dialog_cancel).setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            dialog.dismiss();
          }
        });
        //初始化dialog上的这个listview的适配器
        //第一个参数  ListView, 第二个参数  上下文,第三个参数  数据集, 第四个参数  默认展开层级数 0为不展开,第五个参数  展开的图标,第六个参数  闭合的图标
        mDialogAdapter =
            new DialogAdapter(listView, DialogActivity.this, mTreeDatas, 0, R.mipmap.tree_ex,
                R.mipmap.tree_ec);
        listView.setAdapter(mDialogAdapter);//绑定适配器
      }
    });
  }

  /**
   * 初始化数据
   */
  private void initData() {
    hospitalData = new Gson().fromJson(JsonHospital.hospital, HospitalBean.class);
    departmentData = new Gson().fromJson(JsonHospital.department, DepartmentBean.class);
    initTreeDatas(hospitalData, departmentData);
  }

  /**
   * 加载多级菜单的数据
   */
  private void initTreeDatas(HospitalBean hospitalData, DepartmentBean departmentData) {

    //当前id、父级id即pid，显示的内容
    int n = 0, m = 0;
    mTreeDatas.add(new Node("0", "", "数据源"));//加载主节点
    for (int i = 0; i < hospitalData.getData().size(); i++) {
      mTreeDatas.add(new Node(++n + "", "0", hospitalData.getData().get(i).getName()));//加载医院节点
      Log.e(TAG, "initTreeDatas: " + "id = " + n + " ; pid= " + " " + "内容：" + hospitalData.getData()
          .get(i)
          .getName());
      String libid = hospitalData.getData().get(i).getId() + "";
      List<DepartmentBean.DataBean> dataBeans = findSourceName(libid, departmentData);
      if (dataBeans.size() > 0) {
        m = n;
        for (int j = 0; j < dataBeans.size(); j++) {//加载科室节点
          mTreeDatas.add(new Node(++m + "", n + "", dataBeans.get(j).getName(), dataBeans.get(j)));
          Log.e(TAG, "initTreeDatas: " + "id = " + m + "; pid= " + n + "内容 :" + dataBeans.get(j)
              .getName());
        }
        n = n + dataBeans.size();
      } else {
        //n ++;
      }
    }
  }

  private List<DepartmentBean.DataBean> findSourceName(String id, DepartmentBean departmentData) {
    List<DepartmentBean.DataBean> dataBeans = new ArrayList<>();
    for (int i = 0; i < departmentData.getData().size(); i++) {
      if (departmentData.getData().get(i).getDepartment() == id) {
        dataBeans.add(departmentData.getData().get(i));
      }
    }
    return dataBeans;
  }
}
