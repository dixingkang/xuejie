package com.dafen.xuejie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.activity.wode.BalanceActivity;
import com.dafen.xuejie.activity.wode.IdeaActivity;
import com.dafen.xuejie.activity.wode.IndexActivity;
import com.dafen.xuejie.activity.wode.LeagueActivity;
import com.dafen.xuejie.activity.wode.LoginActivity;
import com.dafen.xuejie.activity.wode.MyAccountActivity;
import com.dafen.xuejie.activity.wode.SetActivity;
import com.dafen.xuejie.activity.wodedingdan.IndentActivity;
import com.dafen.xuejie.activity.wodedizhi.MyAddressActivity;
import com.dafen.xuejie.manager.LoginManger;


/**
 * Created by Administrator on 2016/11/15.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView iv_set,iv_icon;
    private Button bt_login;
    private LinearLayout ll_balance,ll_indent;
    private RelativeLayout rl_site,rl_Account_Info,rl_partner,rl_feedback,rl_Index,rl_update;
    private TextView tv_firm_Service,tv_school_Service;

    private LoginManger loginManger;


    Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,null);
        initView(view);
        initData();
        initListener();
        return view;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_mine;
    }

    /**
     * 实例化控件
     */
    public void initView(View view){
        iv_set = (ImageView) view.findViewById(R.id.iv_set);
        iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        bt_login = (Button) view.findViewById(R.id.bt_login);
        ll_balance = (LinearLayout) view.findViewById(R.id.ll_balance);//余额
        ll_indent = (LinearLayout) view.findViewById(R.id.ll_indent); //订单

        rl_update = (RelativeLayout) view.findViewById(R.id.rl_update);           //检查更新
        rl_site = (RelativeLayout) view.findViewById(R.id.rl_site);                             // 我的地址
        rl_feedback = (RelativeLayout) view.findViewById(R.id.rl_feedback);                 // 意见反馈
        rl_Index = (RelativeLayout) view.findViewById(R.id.rl_Index);                      //关于我们
        rl_partner = (RelativeLayout) view.findViewById(R.id.rl_partner);                 //加盟合作
        rl_Account_Info = (RelativeLayout) view.findViewById(R.id.rl_Account_Info);     // 账号信息
        tv_firm_Service = (TextView) view.findViewById(R.id.tv_firm_Service);    //公司客服
        tv_school_Service = (TextView) view.findViewById(R.id.tv_school_Service);//学校客服
        context = getActivity();
        loginManger = new LoginManger(context);
    }
    public void  initData(){}

    /**
     * 实例化监听,
     */
    public void initListener(){
        ll_balance.setOnClickListener(this);
        iv_set.setOnClickListener(this);
        iv_icon.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        ll_indent.setOnClickListener(this);
        rl_site.setOnClickListener(this);
        rl_feedback.setOnClickListener(this);
        rl_Index.setOnClickListener(this);
        rl_partner.setOnClickListener(this);
        rl_Account_Info.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //判断是否判断
            //点击跳转到我的订单
            case R.id.ll_indent:
                if (loginManger.CheckLoginStatic()){
                    Intent intent = new Intent(context, IndentActivity.class);
                    startActivity(intent);
                }else {
                    loginManger.LoginCheck(IndentActivity.class, true);
                }

                break;
            //点击跳转到我的地址
            case R.id.rl_site:
                if (loginManger.CheckLoginStatic()){
                    Intent intent = new Intent(context, MyAddressActivity.class);
                    startActivity(intent);
                }else {
                    loginManger.LoginCheck(MyAddressActivity.class, true);
                }
                break;
            //点击按钮跳转到登录页面
            case R.id.bt_login:
                if (!(loginManger.CheckLoginStatic())){
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }else {
                    loginManger.LoginCheck(MyAccountActivity.class, true);
                }
                break;
            //点击按钮跳转到反馈页面
            case R.id.rl_feedback:

                if (loginManger.CheckLoginStatic()){
                    Intent intent = new Intent(context, IdeaActivity.class);
                    startActivity(intent);
                }else {
                    loginManger.LoginCheck(IdeaActivity.class, true);
                }
                break;
            //点击按钮跳转到关于我们页面
            case  R.id.rl_Index:
                intent =new Intent(getActivity(), IndexActivity.class);
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
                break;
            //点击按钮跳转到加盟页面
            case R.id.rl_partner:

                if (loginManger.CheckLoginStatic()){
                    Intent intent = new Intent(context, LeagueActivity.class);
                    startActivity(intent);
                }else {
                    loginManger.LoginCheck(LeagueActivity.class, true);
                }
                break;
            //点击按钮跳转到设置页面
            case R.id.iv_set:

                if (loginManger.CheckLoginStatic()){
                    Intent intent = new Intent(context, SetActivity.class);
                    startActivity(intent);
                }else {
                    loginManger.LoginCheck(SetActivity.class, true);
                }
               break;
            //点击账户余额进入余额页面
            case R.id.ll_balance:

                if (loginManger.CheckLoginStatic()){
                    Intent intent = new Intent(context, BalanceActivity.class);
                    startActivity(intent);
                }else {
                    loginManger.LoginCheck(BalanceActivity.class, true);
                }
                break;
            //点击进入账户信息页面
            case  R.id.rl_Account_Info:
                if (loginManger.CheckLoginStatic()){
                    Intent intent = new Intent(context, MyAccountActivity.class);
                    startActivity(intent);
                }else {
                    loginManger.LoginCheck(MyAccountActivity.class, true);
                }
                break;
            default:break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
