package com.dafen.xuejie.activity.wodedizhi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.SiteBean;

import java.util.ArrayList;
import java.util.List;


public class MyAddressActivity extends BaseActivity implements AdapterView.OnItemClickListener,View.OnClickListener{
    private RelativeLayout rl_back;
    private ListView       lv;
    private Button         but_add_address;
    List<SiteBean> list;
    Intent intent;
    AddressAdapter addressAdapter;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_address);
    }

    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        lv = (ListView) findViewById(R.id.lv);
        but_add_address = (Button) findViewById(R.id.but_add_address);
    }

    /**
     * 实例化监听
     */
    @Override
    public void initListeners() {
        rl_back.setOnClickListener(this);
        but_add_address.setOnClickListener(this);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        list = getData();
        addressAdapter = new AddressAdapter(this, list);
        lv.setAdapter(addressAdapter);
    }

    public List<SiteBean> getData() {
        list = new ArrayList<SiteBean>();
        SiteBean bean = new SiteBean();
        bean.setWrite("3号楼610");
        bean.setTel("182*********");
        bean.setAlter("修改");
        bean.setDelete("删除");

        for (int i = 0; i < 6; i++) {
            list.add(bean);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击创建地址按钮 跳转到创建页面
            case R.id.but_add_address:
                intent =new Intent(MyAddressActivity.this,AddSiteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.scale_translate,R.anim.my_alpha_action);
                break;
            //返回键
            case R.id.rl_back:
                finish();
                break;
        }
    }
    private class AddressAdapter extends BaseAdapter{
        Context   context;
        List<SiteBean> list;
        public AddressAdapter(Context context, List<SiteBean> list){
            this.context =context;
            this.list =list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, final ViewGroup parent) {
            ViewHolder holder =null;
            if (view==null){
                holder =new ViewHolder();
                view = LayoutInflater.from(context).inflate(R.layout.adapter_address_item,null);
                holder.et_write = (EditText) view.findViewById(R.id.et_write);
                holder.tv_tel = (TextView) view.findViewById(R.id.tv_tel);
            }else {
                holder = (ViewHolder) view.getTag();
            }
            holder.et_write.setText(list.get(position).getWrite());
            holder.tv_tel.setText(list.get(position).getTel());
            holder.tv_alter = (TextView) view.findViewById(R.id.tv_alter);
            holder.tv_delete = (TextView) view.findViewById(R.id.tv_delete);
            view.setTag(holder);
            /**
             * 监听事件
             */
            holder.tv_alter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"修改"+ position,Toast.LENGTH_LONG).show();
                }
            });

            holder.tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"删除"+ position,Toast.LENGTH_LONG).show();
                        dialog(position);
                }
            });
            return view;
        }

        final class ViewHolder{
            public EditText et_write;
            public TextView tv_tel;
            public TextView tv_alter;
            public TextView tv_delete;
        }
        protected  void  dialog(final int i){
            AlertDialog.Builder builder = new AlertDialog.Builder(MyAddressActivity.this);
            builder.setMessage("您确定要删除该地址吗？");
            builder.setTitle("温馨提示");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    list.remove(i);
                    lv.deferNotifyDataSetChanged();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   dialog.dismiss();
                }
            });
            builder.create().show();
        }

    }

}
