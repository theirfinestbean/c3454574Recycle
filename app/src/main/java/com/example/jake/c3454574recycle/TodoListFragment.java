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
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list_todo, container, false);

        mTodoRecyclerView = (RecyclerView) view.findViewById(R.id.todo_recycler_view);
        mTodoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }

    private void updateUI(){

        /*
        TODO: refactor to data layer
         */
        ArrayList todos = new ArrayList<>();
        for (int i=0; i < 100; i++){
            Todo todo = new Todo();
            todo.setTitle("Todo number " + i);
            todo.setIsComplete(i % 2 == 0);
            todos.add(todo);
        }
        mTodoAdapter = new TodoAdapter(todos);
        mTodoRecyclerView.setAdapter(mTodoAdapter);
    }

    public class TodoHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTitle;
        private TextView mTextViewDate;

        public TodoHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_todo, parent, false));
            mTextViewTitle = (TextView) itemView.findViewById(R.id.textViewTodoTitle);
            mTextViewDate = (TextView) itemView.findViewById(R.id.textViewTodoDate);
        }
        public void bind(Todo todo){
            mTodo = todo;
            mTextViewTitle.setText(mTodo.getTitle());
            mTextViewDate.setText(mTodo.getDate().toString());
        }
    }

    public class TodoAdapter extends RecyclerView.Adapter<TodoListFragment.TodoHolder> {

        private List<Todo> mTodo;
        public TodoAdapter(List<Todo> todos) {
            mTodos = todos;
        }
        @Override
        public TodoListFragment.TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new TodoHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(TodoHolder holder, int position) {
            Todo todo = mTodos.get(position);
            holder.bind(todo);
        }
        @Override
        public int getItemCount() {
            return mTodos.size();
        }
    }

}