package read.eyydf.terr.jokecollection.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import read.eyydf.terr.jokecollection.R;
import read.eyydf.terr.jokecollection.views.TabsView;
import read.eyydf.terr.jokecollection.views.TabsView.OnTabsItemClickListener;

/**
 * 首页 Fragment
 */
@ContentView(R.layout.viewpagerfragmentlayouthome)
public class MainFirstHomeFragment extends Fragment {
	@ViewInject(R.id.homeViewpager)
	private ViewPager homeViewpager;
	@ViewInject(R.id.tabslayout)
	private TabsView tabslayout;
	private Myadapter myadapter;
	private List<Fragment> list;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		return x.view().inject(this, inflater, container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		init();
	}

	@SuppressLint("Recycle")
	/**
	 * 初始化
	 */
	private void init() {
		if (getActivity() == null) {
			return;
		}
		// 初始化选项卡
		tabslayout.setTabs("发现", "推荐","热榜");
		tabslayout.setOnTabsItemClickListener(new OnTabsItemClickListener() {

			@Override
			public void onClick(View view, int position) {
				homeViewpager.setCurrentItem(position, true);
			}
		});
		FragmentManager fm = getChildFragmentManager();
		fm.beginTransaction();
		myadapter = new Myadapter(fm);
		homeViewpager.setAdapter(myadapter);
		// 设置当前显示页面相邻的a个页面进行缓存
		homeViewpager.setOffscreenPageLimit(3);
		homeViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				if (getActivity() == null) {
					return;
				}
				switch (arg0) {
				case 0:
					tabslayout.setCurrentTab(0, true);
					break;
				case 1:
					tabslayout.setCurrentTab(1, true);
					break;
				case 2:
					tabslayout.setCurrentTab(2, true);
					break;
				/*case 3:
					tabslayout.setCurrentTab(3, true);
					break;*/
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	class Myadapter extends FragmentStatePagerAdapter {
		// 只适配一次。
		public Myadapter(FragmentManager fm) {
			super(fm);
			list = new ArrayList<>();
			list.add(new FirstHometuijianFragment());
			list.add(new FirstHomezuixinFragment(2));
			//list.add(new FirstHomezuixinFragment(3));
			list.add(new FirstHomepaihangFragment());
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public int getItemPosition(Object object) {
			return PagerAdapter.POSITION_NONE;
		}
	}
}
