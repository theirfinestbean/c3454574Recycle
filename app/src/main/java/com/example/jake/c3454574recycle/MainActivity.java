package com.example.jake.c3454574recycle;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


public class MainActivity extends FragmentActivity
        implements FragmentListTwo.OnTodoSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check that the activity for "fragment_container" layout, if no save state is needed then do nothing to prevent fragment overlapping
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            //create a fragment to be placed in the activity layout
            TodoListFragment firstFragment = new TodoListFragment();
            //if activity has special instructions, pass Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            //add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    public void onTodoSelected(int position) {
        //capture todo fragment from activity
        TodoFragment todoFragment = (TodoFragment)
                getSupportFragmentManager().findFragmentById(R.id.todo_recycler_view);

        if (todoFragment != null) {

            //call method to update content
            todoFragment.updateTodoView(position);

        } else {
            //create new fragment for selected article
            TodoFragment newFragment = new TodoFragment();
            Bundle args = new Bundle();
            args.putInt(TodoFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            //replace fragment_container view with fragment then add transaction to back stack so user can move more freely
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}