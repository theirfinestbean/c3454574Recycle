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

        //if activity recreated restore previous selection set by onSaveInstanceState().
        //necessary in a two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        //inflate the layout for this fragment
        return inflater.inflate(R.layout.view_todo, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //in startup check for arguments passed to the fragment.
        //onStart is good to do this because the layout is already applied
        //to the fragment here so we can call method below setting todo text.
        Bundle args = getArguments();
        if (args != null) {
            //set todo based on argument
            updateTodoView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            //set todo based on savedInstanceState in onCreateView
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

        //save current todo for recreation
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

}