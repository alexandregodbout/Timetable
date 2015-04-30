package ca.qc.cegepstefoy.timetable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;


public class CoursePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Course> mCourses;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mCourses = CourseHelper.get(this).getCourses();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int pos) {
                Course c = mCourses.get(pos);

                return CourseFragment.newInstance(c.getId());
            }

            @Override
            public int getCount() {
                return mCourses.size();
            }
        });

        final UUID crimeId = (UUID)getIntent().getSerializableExtra(CourseFragment.EXTRA_CRIME_ID);
        for (int i  = 0; i < mCourses.size(); i++) {
            if (mCourses.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) { }

            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) { }

            public void onPageSelected(int pos) {
                Course c = mCourses.get(pos);
                if (c.getTitle() != null) {
                    setTitle(c.getTitle());
                }
            }
        });
    }
}
