package ca.qc.cegepstefoy.timetable;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class CourseHelper {
    private Context mAppContext;

    private ArrayList<Course> mCourses;

    private static CourseHelper sCourseHelper;

    private CourseHelper(Context appContext) {
        mAppContext = appContext;
        mCourses = new ArrayList<Course>();

        for (int i = 0; i < 20; i++) {
            Course c = new Course();
            c.setTitle("Course #" + i);
            c.setDone(i % 2 == 0);
            mCourses.add(c);
        }
    }

    public static CourseHelper get(Context c) {
        if (sCourseHelper == null) {
            sCourseHelper = new CourseHelper(c.getApplicationContext());
        }

        return sCourseHelper;
    }

    public ArrayList<Course> getCourses() {
        return mCourses;
    }

    public Course getCourse(UUID id) {
        for (Course c : mCourses) {
            if (c.getId().equals(id)) {
                return c;
            }
        }

        return null;
    }
}
