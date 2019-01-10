package com.example.jake.c3454574recycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TodoListFragment extends Fragment {

    private RecyclerView mTodoRecyclerView;
    private TodoAdapter mTodoAdapter;
    private Todo mTodo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.todo_recycler_view, container, false);

        mTodoRecyclerView = v.findViewById(R.id.todo_recycler_view);
        mTodoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    private void updateUI() {

        //refactor to data layer
        ArrayList todos = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Todo todo = new Todo();
            todo.setTitle("Task number " + i);
            todo.setIsComplete(i % 2 == 0);
            todos.add(todo);
        }

        mTodoAdapter = new TodoAdapter(todos);
        mTodoRecyclerView.setAdapter(mTodoAdapter);

    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView mTextViewTitle;
        private TextView mTextViewDate;
        private TextView mTextViewDetail;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_todo, parent, false));

            mTextViewTitle = itemView.findViewById(R.id.textViewTodoTitle);
            mTextViewDate = itemView.findViewById(R.id.textViewTodoDate);
            mTextViewDetail = itemView.findViewById(R.id.textViewTodoDetail);

        }

        public void bind(Todo todo) {
            mTodo = todo;
            mTextViewTitle.setText(mTodo.getTitle());
            mTextViewDate.setText(mTodo.getDate().toString());
            mTextViewDetail.setText(mTodo.getDetail());

        }
    }
    public class TodoAdapter extends RecyclerView.Adapter<TodoListFragment.Holder> {

        private List<Todo> mTodos;

        public TodoAdapter(List<Todo> todos) {
            mTodos = todos;
        }

        @Override
        public TodoListFragment.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new TodoListFragment.Holder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TodoListFragment.Holder holder, int position) {
            Todo todo = mTodos.get(position);
            holder.bind(todo);
        }

        @Override
        public int getItemCount() {
            return mTodos.size();
        }
    }
}