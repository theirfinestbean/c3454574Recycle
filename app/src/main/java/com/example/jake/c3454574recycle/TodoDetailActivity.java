package com.example.jake.c3454574recycle;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class TodoDetailActivity extends AppCompatActivity {

    private int mTodoIndex;

    //any calling activity would call this static method and pass the necessary key, value data pair in an intent object
    public static Intent newIntent(Context packageContext, int todoIndex){
        Intent intent = new Intent(packageContext, TodoDetailActivity.class);
        intent.putExtra(TODO_INDEX,todoIndex);
        return intent;
    }

    //name, value pair to be returned in an intent
    private static final String IS_TODO_COMPLETE = "com.example.jake.c3454574recycle.isTodoComplete";

    //in case of state change due to rotation, store mTodoIndex to display the same mTodos element post state change
    //small pieces of data, normally IDs can be stored as key, value pairs in a Bundle, another way is to abstract view data
    //to a ViewModel which can be in scope in all activity states and more suitable for larger amounts of data
    private static final String TODO_INDEX = "com.example.jake.c3454574recycle.todoIndex";

    //override to write the value of mTodoIndex into the Bundle with TODO_INDEX as its key
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TODO_INDEX, mTodoIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_todo);

        if (savedInstanceState != null){
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
        }

        //initialize member TextView for manipulation
        TextView TodoDetailTextView;
        TodoDetailTextView = findViewById(R.id.textViewTodoDetail);

        //get intent extra int for the todos index
        int mTodoIndex = getIntent().getIntExtra(TODO_INDEX, 0);
        updateTextViewTodoDetail(mTodoIndex);

        CheckBox checkboxIsComplete = findViewById(R.id.checkBoxIsComplete);
        //register onClick listener with generic implementation mTodoListener
        checkboxIsComplete.setOnClickListener(mTodoListener);
    }

    private void updateTextViewTodoDetail(int todoIndex) {

        final TextView textViewTodoDetail;
        textViewTodoDetail = findViewById(R.id.textViewTodoDetail);

        //refactor data
        Resources res = getResources();
        String[] todoDetails = res.getStringArray(R.array.todo_detail);

        //display the first task from mTodo array in the TodoTextView
        textViewTodoDetail.setText(todoDetails[todoIndex]);

    }

    //create anon implementation of OnClickListener for all clickable view objects
    private OnClickListener mTodoListener = new OnClickListener() {
        public void onClick (View v) {
            //take clicked object and do an action
            switch (v.getId() ) {
                case R.id.checkBoxIsComplete:
                    CheckBox checkboxIsComplete = (CheckBox)findViewById(R.id.checkBoxIsComplete);
                    setIsComplete(checkboxIsComplete.isChecked());
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    private void setIsComplete(boolean isChecked) {

        //static Toast message
        if(isChecked){
            Toast.makeText(TodoDetailActivity.this,
                    "Good job!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(TodoDetailActivity.this,
                    "Try again next time!", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent();
        intent.putExtra(IS_TODO_COMPLETE, isChecked);
        setResult(RESULT_OK, intent);
    }

}
