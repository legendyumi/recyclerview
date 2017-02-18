package com.example.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ENZ on 2016/10/15.
 */
class ViewHoderAdaapters extends RecyclerView.Adapter implements TextView.OnEditorActionListener{
    String name;

    private Context context;
    private List<String> datas;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;

    //构造方法
    public ViewHoderAdaapters(MainActivity mainActivity, List<String> data) {
        this.context = mainActivity;
        this.datas = data;
        inflater = LayoutInflater.from(context);
    }


    //定义一个监听接口，里面有两个方法
    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    //给监听设置一个构造函数，用于main中调用
    public void setOnItemListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_item,parent,false);
        ViewHoders viewHoders = new ViewHoders(view);
        return viewHoders;
    }
    @Override
    //填充onCreateViewHolder方法返回的holder中的控件
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ViewHoders) holder).tv.setTag(position);
        ((ViewHoders) holder).tv.setText(datas.get(position));
        ((ViewHoders) holder).tv.setOnEditorActionListener(this);
        ((ViewHoders) holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datas.remove(position);
                notifyDataSetChanged();
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,datas.size());
            }
        });
        if (mOnItemClickListener!=null) {
            //直接给某个空间添加监听
            ((ViewHoders) holder).li_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                    name=((ViewHoders) holder).tv.getText().toString();
                }
            });
            //直接给某个空间添加长按监听
            ((ViewHoders) holder).li_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    @Override
    public boolean onEditorAction(TextView v, int i, KeyEvent keyEvent) {
        int position=(int)v.getTag();
        String updata=v.getText().toString();
        datas.set(position,updata);
        notifyDataSetChanged();
        return false;
    }

    //自定义ViewHolder,
    /*
    * RecylerView封装了viewholder的回收复用，也就是说RecylerView标准化了ViewHolder，编写Adapter面向的是ViewHolder而不再是View了
    * */
    class ViewHoders extends RecyclerView.ViewHolder{
        private EditText tv;
        private LinearLayout li_layout;
        private Button delete;

        public ViewHoders(View itemView) {
            super(itemView);
            tv= (EditText) itemView.findViewById(R.id.textview);
            li_layout= (LinearLayout) itemView.findViewById(R.id.li_layout);
            delete= (Button) itemView.findViewById(R.id.btn_delete);
        }
    }

    public void addData(int position) {
//        if(datas.size()==0){
//            datas.add(0, "我是熊大");
//            notifyItemInserted(position);
//            notifyItemRangeChanged(position,datas.size());
//        }else {
            datas.add(position, "");
            notifyItemInserted(position);
            notifyItemRangeChanged(position,datas.size());
//        }
    }

    public void removeData(int position) {
        if(datas.size()<2&&datas.size()!=0){
            datas.remove(0);
            notifyDataSetChanged();
        }else if(datas.size()==0){
            Toast.makeText(context,"搞毛啊，没数据了",Toast.LENGTH_SHORT).show();
        }else{
            datas.remove(position);
            notifyDataSetChanged();
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,datas.size());
        }

    }
}
