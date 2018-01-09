package com.example.shenhaichen.mobileassistant.presenter.contract;

import com.example.shenhaichen.mobileassistant.bean.AppInfo;
import com.example.shenhaichen.mobileassistant.ui.BaseView;

import java.util.List;

/**
 * mvp结构中的contract, 可以说是View和presenter的接口方法设定类
 * Created by shenhaichen on 03/01/2018.
 */

public interface RecommendContract {
    interface View extends BaseView {

        //得到结果
        void showResult(List<AppInfo> mData);

        //无数据
        void noData();

        //显示错误信息
        void error(String error);

        void onRequestPermissionSuccess();
        void onRequestPermissionFailed();

    }


}
