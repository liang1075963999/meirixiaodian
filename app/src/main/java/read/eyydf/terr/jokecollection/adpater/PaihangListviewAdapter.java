package read.eyydf.terr.jokecollection.adpater;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.model.GetListData;
import read.eyydf.terr.jokecollection.views.MyTransition;

/**
 * Created by fenghu on 2017/5/19.
 */

public class PaihangListviewAdapter extends BaseAdapter {
    private Context context;
    private List<GetListData> list;

    public PaihangListviewAdapter(List<GetListData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder_one_image holder_one_image = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.viewpagerfirsthomepaihangfragmentitem_have_image,
                    null);
            holder_one_image = new ViewHolder_one_image();
            holder_one_image.paihang_xuhao = (TextView) view.findViewById(R.id.paihang_xuhao);
            holder_one_image.paihang_text = (TextView) view.findViewById(R.id.paihang_text);
            holder_one_image.content = (TextView) view.findViewById(R.id.content);
            holder_one_image.redu = (TextView) view.findViewById(R.id.redu);
            holder_one_image.paihang_image = (ImageView) view.findViewById(R.id.paihang_image);
            view.setTag(holder_one_image);
        } else {
            holder_one_image = (ViewHolder_one_image) view.getTag();
        }

        String str = String.valueOf((position + 1));
        if (str.length() == 1) {
            str = 0 + str;
        }
        switch (position) {
            case 0:
             /*   holder_one_image.paihang_xuhao.setTextColor(ContextCompat.getColor(context, R.color.yyelow));
                break;*/
            case 1:
               /* holder_one_image.paihang_xuhao.setTextColor(ContextCompat.getColor(context, R.color.gray));
                break;*/
            case 2:
                holder_one_image.paihang_xuhao.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                holder_one_image.paihang_xuhao.setTextColor(ContextCompat.getColor(context, R.color.black));
                break;
        }
        holder_one_image.paihang_xuhao.setText(str);
        if (position < list.size()) {
            if (list.get(position) != null) {
                holder_one_image.paihang_text.setText(list.get(position).getArticle_name().trim());
                if (list.get(position).getContent().trim().startsWith("[img1]")) {
                    holder_one_image.content.setText(list.get(position).getContent().trim().substring(6, list.get(position).getContent().trim().length()));
                } else
                    holder_one_image.content.setText(list.get(position).getContent().trim());
                holder_one_image.redu.setText(list.get(position).getCountlike() + "热度");
                if (list.get(position).getContentPictures() != null) {
                    Log.d("PaihangListviewAdapter", list.get(position).getContentPictures().get(0).toString());
                    RequestOptions requestOptions = RequestOptions.centerCropTransform().optionalTransform(new MyTransition(context));
                    Glide.with(context).applyDefaultRequestOptions(requestOptions).load(list.get(position).getContentPictures().get(0)).into(holder_one_image.paihang_image);
                }
            }
        }
        return view;
    }

    class ViewHolder_one_image {
        TextView paihang_xuhao, paihang_text, content, redu;
        ImageView paihang_image;
    }
}
