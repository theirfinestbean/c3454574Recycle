package com.example.jake.c3454574recycle;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TodoFragment extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //if activity recreated restore the previous todo selection set by onSaveInstanceState().
        //this is necessary when in a two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_todo, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the todo text.
        Bundle args = getArguments();
        if (args != null) {
            //set todo based on argument passed in
            updateTodoView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            //set todo based on saved instance state defined during onCreateView
            updateTodoView(mCurrentPosition);
        }
    }
    public void updateTodoView(int position) {
        TextView todo = getActivity().findViewById(R.id.todo_recycler_view);
        todo.setText(Todo.Todos[position]);
        mCurrentPosition = position;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //save the current todo for recreation
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

}