package read.eyydf.terr.jokecollection.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.model.GetListData;
import read.eyydf.terr.jokecollection.tools.RecyclerInterface;
import read.eyydf.terr.jokecollection.views.MyTransition;

/**
 * Created by liang on 2018/8/22.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolderView> implements View.OnClickListener {
    private List<GetListData> listDatas;
    private RecyclerInterface recyclerInterface;
    private Context context;
    private MyHolderView myHolderView;

    public MyAdapter(List<GetListData> listDatas,Context context) {
        this.listDatas = listDatas;
        this.context=context;
    }

    @Override
    public MyHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.recycler_item,null);
        view.setOnClickListener(this);
        myHolderView=new MyHolderView(view);
        return myHolderView;
    }

    @Override
    public void onBindViewHolder(MyHolderView holder, int position) {
        if(position==5){
            holder.more.setVisibility(View.VISIBLE);
            holder.linearLayout.setVisibility(View.GONE);
            holder.itemView.setTag(position);
        }else {
            holder.textView.setText(listDatas.get(position).getArticle_name());
            RequestOptions requestOptions=RequestOptions.centerCropTransform().optionalTransform(new MyTransition(context));
            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(listDatas.get(position).getContentPictures().get(0)).into(holder.imageView);
            holder.itemView.setTag(position);
        }
    }

    public void setJieKou(RecyclerInterface recyclerInterface) {
        this.recyclerInterface = recyclerInterface;
    }

    @Override
    public int getItemCount() {
        return listDatas.size();
    }

    @Override
    public void onClick(View v) {
        recyclerInterface.danJi(v, (Integer) v.getTag());
    }

    class MyHolderView extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView,more;
        private LinearLayout linearLayout;

        public MyHolderView(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            linearLayout = itemView.findViewById(R.id.linear);
            more = itemView.findViewById(R.id.more);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
