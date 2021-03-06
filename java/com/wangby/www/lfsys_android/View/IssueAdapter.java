package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangby.www.lfsys_android.Object.Issue;
import com.wangby.www.lfsys_android.R;

import java.util.ArrayList;

/**
 * Created by 王炳炎 on 2017/5/2.
 */
public class IssueAdapter extends BaseAdapter {

    private ArrayList<Issue> list = new ArrayList<Issue>();

    private Context mContext;

    public IssueAdapter(Context mContext) {
        this.mContext = mContext;
        list.add(new Issue(mContext.getResources().getDrawable(R.drawable.personal_finishal),"我的发布"));
        list.add(new Issue(mContext.getResources().getDrawable(R.drawable.lost_title),"失物发布"));
        list.add(new Issue(mContext.getResources().getDrawable(R.drawable.foundtitles),"拾物发布"));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    int a=0;
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView != null){
            view = convertView;
        }else {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.lssue_list, null);
        }
        ImageView img = (ImageView) view.findViewById(R.id.image_issue);
        TextView textView = (TextView) view.findViewById(R.id.textView_issue);
        Issue issue = list.get(position);
        textView.setText(issue.title);
        img.setImageDrawable(issue.drawable);


        return view;
    }
}
