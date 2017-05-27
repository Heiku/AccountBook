package com.example.ljh.accountbook.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ljh.accountbook.R;
import com.example.ljh.accountbook.model.Tb_accout;

import java.util.List;


/**
 * Created by Administrator on 2017/5/22 0022.
 */
public class TbAccoutAdapter extends BaseAdapter {

    private List<Tb_accout> mList;
    private Context mContext;
    private LayoutInflater mLayutInflater;

    public TbAccoutAdapter( Context context,List<Tb_accout> list) {
        mList = list;
        mContext = context;
        mLayutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mLayutInflater.inflate(R.layout.list_item,null);

            viewHolder.mtime = (TextView)convertView.findViewById(R.id.it_time);
            viewHolder.mtype = (TextView) convertView.findViewById(R.id.it_type);
            viewHolder.mMoney = (TextView) convertView.findViewById(R.id.it_money);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Tb_accout accout = mList.get(position);
        viewHolder.mtime.setText(accout.getData());
        viewHolder.mtype.setText(accout.getType());
        viewHolder.mMoney.setText(accout.getMoney());
        return convertView;
    }


    private static class  ViewHolder{
        public TextView mMoney;
        public TextView mtime;
        public TextView mtype;
    }
}
