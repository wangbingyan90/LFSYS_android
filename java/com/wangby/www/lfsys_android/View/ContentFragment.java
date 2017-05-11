package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wangby.www.lfsys_android.Object.Goods;
import com.wangby.www.lfsys_android.Object.MessageUse;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.SqlTool;

import java.util.ArrayList;

/**
 * Created by 王炳炎 on 2017/4/24.
 */
public class ContentFragment extends Fragment {

    ListView listView;
    ArrayList<Goods> goodsList;
    SqlTool sqlTool;
    Context mContext;
    public static ContentFragment getFragment(String type){
        Bundle arguments = new Bundle();
        arguments.putString("TYPE", type);//放入map
        ContentFragment tabContentFragment = new ContentFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_content, null);
        mContext = getActivity();
        listView = (ListView) contentView.findViewById(R.id.listview);
        inilistview(getArguments().getString("TYPE"));

        return contentView;
    }

    private void inilistview(String type) {
        switch(type){
            case "goods_lost":
                listView.setAdapter(new GoodsAdapter(mContext, SqlTool.getTextGoods()));
                break;
            case "goods_found":
                listView.setAdapter(new GoodsAdapter(mContext, SqlTool.getTextGoods()));
                break;
            case "issue":
                listView.setAdapter(new IssueAdapter(mContext));
                break;
            case "message":
                //通过Sql获取信息
                ArrayList<MessageUse> messlist = new ArrayList<MessageUse>();
                for(int i = 0; i<4;i++){
                    MessageUse ad = new MessageUse();
                    ad.name="小明";
                    ad.talk.add("你好");
                    ad.talk.add("hai");
                    messlist.add(ad);
                }
                listView.setAdapter(new MessageAdapter(mContext,messlist));
                break;
        }

    }

}
