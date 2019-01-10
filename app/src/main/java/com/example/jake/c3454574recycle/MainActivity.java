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

        // Check that the activity for "fragment_container" Layout, if no save state is needed then do nothing to prevent fragment overlapping
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            // Create a new Fragment to be placed in the activity layout
            TodoListFragment firstFragment = new TodoListFragment();
            // if activity has special instructions, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    public void onTodoSelected(int position) {
        // Capture todo fragment from activity
        TodoFragment todoFragment = (TodoFragment)
                getSupportFragmentManager().findFragmentById(R.id.todo_recycler_view);

        if (todoFragment != null) {

            // Call method to update content
            todoFragment.updateTodoView(position);

        } else {
            //Create new fragment for selected article
            TodoFragment newFragment = new TodoFragment();
            Bundle args = new Bundle();
            args.putInt(TodoFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            //Replace fragment_container view with this fragment then add transaction to back stack so user can move more freely
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }
}