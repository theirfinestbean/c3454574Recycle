package com.example.jake.c3454574recycle;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class FragmentListTwo extends ListFragment {
    OnTodoSelectedListener mCallback;

    //the container Activity must implement this interface so frag can deliver messages
    public interface OnTodoSelectedListener {
        void onTodoSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //use alternate list item layout if device is older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_2 : android.R.layout.simple_list_item_2;

        //create adapter for the list view, using the TodoModel headlines array
        setListAdapter(new ArrayAdapter<>(getActivity(), layout, (Todo.Todos)));
    }

    @Override
    public void onStart() {
        super.onStart();

        //when in two-pane, set the list view to highlight the selected list item; do during onStart because the list view is available.
        if (getFragmentManager().findFragmentById(R.id.todo_recycler_view) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //makes sure the container activity has implemented callback interface, if not throws exception.
        try {
            mCallback = (OnTodoSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTodoSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        mCallback.onTodoSelected(position);

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }
}
