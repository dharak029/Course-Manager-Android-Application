/*
HomeWork06
MainActivity.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Login.OnFragmentInteractionListener,SignUp.OnFragmentInteractionListener,CourseManager.OnFragmentInteractionListener,addinstructor.OnFragmentInteractionListener,InstructorList.OnFragmentInteractionListener,CreateCourse.OnFragmentInteractionListener{

    DatabaseDataManager dm;
    static String unameForSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unameForSession = null;
        getSupportFragmentManager().beginTransaction().
                add(R.id.container,new Login(),"login").commit();
        getSupportActionBar().setTitle("Course Manager");
    }

    @Override
    public long saveUser(String fname,String lname, String uname, String password, byte[] image) {
        dm = new DatabaseDataManager(this);
        return dm.save(new Registeration(uname,password,image,fname,lname));
    }

    @Override
    public void goToCourseManager(String uname) {
        CourseManager courseManager = new CourseManager();
        Bundle bundle = new Bundle();
        bundle.putString("UNAME", uname);
        unameForSession = uname;
        courseManager.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,courseManager,"coursemanager")
                .commit();
        getSupportActionBar().setTitle("Course Manager");
    }

    @Override
    public void goToSignup() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new SignUp(),"signup")
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle("Register");
    }

    @Override
    public void goToCourseManagerFromLogin(String uname, String password) {
        dm = new DatabaseDataManager(this);
        Registeration user = dm.getUser(uname);

        if(user!=null && user.getUname().equals(uname) && user.getPassword().equals(password)){
            CourseManager courseManager = new CourseManager();
            Bundle bundle = new Bundle();
            bundle.putString("UNAME", uname);
            courseManager.setArguments(bundle);
            unameForSession = uname;
            getSupportFragmentManager().beginTransaction().replace(R.id.container,courseManager,"coursemanager")
                    .commit();
            getSupportActionBar().setTitle("Course Manager");
        }
        else{
            Toast.makeText(MainActivity.this,"Please enter valid Username and Password",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void saveCourse(String title, String instructor, String time, String semester, int credit, byte[] image, String uname) {
        dm = new DatabaseDataManager(this);
        long id = dm.saveCourse(new Course(title,instructor,time,semester,credit,image, uname));
        if(id>0){
            Toast.makeText(MainActivity.this,"Course successfully created!!",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("here", "just check here");
        if(item.getItemId() == R.id.action_home){
            if (unameForSession != null) {
                CourseManager courseManager = new CourseManager();
                Bundle bundle = new Bundle();
                bundle.putString("UNAME", unameForSession);
                courseManager.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, courseManager, "coursemanager")
                        .commit();
                getSupportActionBar().setTitle("Course Manager");
            }
            else
            {
                Toast.makeText(MainActivity.this, "You have to Log in First", Toast.LENGTH_SHORT).show();
            }
        }
        else if(item.getItemId()== R.id.action_add_instructors){
            if (unameForSession != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new addinstructor(), "addinstructor")
                        .commit();
                getSupportActionBar().setTitle("Add Instructor");
            }else
            {
                Toast.makeText(MainActivity.this, "You have to Log in First", Toast.LENGTH_SHORT).show();
            }
        }

        else if(item.getItemId() == R.id.action_instructors){
            if (unameForSession != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new InstructorList(), "addinstructor")
                        .commit();
                getSupportActionBar().setTitle("Course Manager");
            }
            else
            {
                Toast.makeText(MainActivity.this, "You have to Log in First", Toast.LENGTH_SHORT).show();
            }
        }

        else if(item.getItemId() == R.id.action_logout){
            if (unameForSession != null) {
                unameForSession = null;
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, new Login(), "login").commit();
                getSupportActionBar().setTitle("Course Manager");
            }
        }

        else{
            finish();
            moveTaskToBack(true);
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void saveInstructor(String fname, String lname, String email, String website, byte[] image) {
        dm = new DatabaseDataManager(this);
        long id = dm.saveInstructor(new Instructor(fname,lname,email,website,image));
        if(id>0){
            Toast.makeText(MainActivity.this,"Instructor successfully added!!",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void goToCreateCourse(String uname) {
        CreateCourse createCourse = new CreateCourse();
        Bundle bundle = new Bundle();
        bundle.putString("UNAME", uname);
        createCourse.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,createCourse,"createCourse")
                .commit();
        getSupportActionBar().setTitle("Create Course");
    }

    @Override
    public void reloadCreateCourse() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new CreateCourse(),"reloadCreateCourse")
                .commit();
    }

    @Override
    public void goToDisplayCourse(Course course) {
        unameForSession = course.getUname();
        Bundle bundle = new Bundle();
        bundle.putSerializable("COURSE", course);
        DisplayCourse displayCourse = new DisplayCourse();
        displayCourse.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,displayCourse, "displayCourse")
                .commit();
    }

    @Override
    public void onBackPressed() {
        getSupportActionBar().setTitle("Course Manager");
        if(getFragmentManager().getBackStackEntryCount() > 0)
        {
            getFragmentManager().popBackStack();
        }
        else
        {
            super.onBackPressed();
        }
    }
}
