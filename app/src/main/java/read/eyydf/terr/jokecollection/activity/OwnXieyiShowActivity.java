package read.eyydf.terr.jokecollection.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.tools.ActivityUtils;
import read.eyydf.terr.jokecollection.views.zitiTextView;

/**
 * Created by fenghu on 2017/5/18.
 */
@ContentView(R.layout.own_xieyi_show_activity)
public class OwnXieyiShowActivity extends BaseActivity {
    private zitiTextView xieYi;
    private ImageView back;
    private zitiTextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Event(value = {R.id.image_back}, type = View.OnClickListener.class)
    private void click(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
        }
    }

    public void init() {
        x.view().inject(this);
        ActivityUtils.initTranslucentStatus(this);
        xieYi = findViewById(R.id.web_layout);
        title = findViewById(R.id.title);
        ImageView back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
            title.setText("每日笑点");
        else if (getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
            title.setText("爆笑百科");
        else if (getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
            title.setText("爆笑日报");
        if (getApplication().getPackageName().equals("read.eyydf.jokecollection.episode"))
            xieYi.setText(R.string.duanzixieyi);
        else if (getApplication().getPackageName().equals("read.terr.jokecollection.encyclopedia"))
            xieYi.setText(R.string.baikexieyi);
        else if (getApplication().getPackageName().equals("read.eyydf.terr.jokecollection"))
            xieYi.setText(R.string.ribaoxieyi);
    }
}
