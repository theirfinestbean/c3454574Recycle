package com.example.jake.c3454574recycle;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity
        implements TodoListFragment.OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
                if (savedInstanceState != null) {
                return;
            }
            // Create a new Fragment to be placed in the activity layout with support from fragment manager
            TodoListFragment firstFragment = new TodoListFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }


    public void onTodoSelected(int position) {

        TodoFragment todoFragment = (TodoFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_todo);

        if (todoFragment != null) {
            // Call a method in the TodoFragment to update its content
            todoFragment.updateTodoView(position);

        } else {

            // Create fragment and give it an argument for the selected article
            TodoFragment newFragment = new TodoFragment();
            Bundle args = new Bundle();
            args.putInt(TodoFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment, then add the transaction to the back stack so user can still navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }
}