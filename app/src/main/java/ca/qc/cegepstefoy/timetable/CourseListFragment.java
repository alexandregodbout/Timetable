package ca.qc.cegepstefoy.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class CourseListFragment extends ListFragment {
    private ArrayList<Course> mCourses;

    private static final String TAG = "CrimeListFragment";
    private static final int REQUEST_CRIME = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle(R.string.courses_title);

        mCourses = CourseHelper.get(getActivity()).getCourses();

        CrimeAdapter adapter = new CrimeAdapter(mCourses);
        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CRIME) {
            ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Course c = ((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, c.getTitle() + " was clicked");

        Intent i = new Intent(getActivity(), CoursePagerActivity.class);
        i.putExtra(CourseFragment.EXTRA_CRIME_ID, c.getId());
        startActivityForResult(i, REQUEST_CRIME);
    }

    private class CrimeAdapter extends ArrayAdapter<Course> {
        public CrimeAdapter(ArrayList<Course> courses) {
            super(getActivity(), 0, courses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity()
                        .getLayoutInflater()
                        .inflate(R.layout.list_item_course, null);
            }

            Course c = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());

            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isDone());

            return convertView;
        }
    }
}
