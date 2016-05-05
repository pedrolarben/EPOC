package amigosdevaro.com.epoc.UI_Medicinas;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.Farmaco;

public class DisplayMeds extends AppCompatActivity {

    /*Esta actividad es la encargada de mostrar todas las medicinas y de proporcionar una forma de editar y eliminar medicinas*/

 /*Android developers frequently create a TAG constant with the name of the class for logging.*/
    //Constants:
    public static String TAG = "DisplayMeds";
    RecyclerView recyclerView;
    private List<Farmaco> datos;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_meds);

        datos = EpocDB.getFarmacos();
        recyclerView = (RecyclerView) findViewById(R.id.displayMed_lista);
        recyclerView.setHasFixedSize(true);

        final AdaptadorDisplayMed adaptador = new AdaptadorDisplayMed(datos);

        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.action_add_medicine);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayMeds.this, MedForm.class));
            }
        });

    }

    //Adapted to update the UI with the new task stored in the database:

    /*To see the updated data, you need to call the updateUI() method
    every time the underlying data of the app changes.*/

    private void updateUI(){

        //Now we have to fetch all the data from the database and show it in the main view.
       // ArrayList<String> taskList = new ArrayList<>();


        /*For what I see it seems like we are creating a cursor and while there's an entry for a task in our databse tasks table we make sure such task is displayed.*/
      /*  Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
            //The tasks are added into this array of strings.
            taskList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_todo,//The view to use for the items.
                    R.id.task_title,//The place where to put the String of data.
                    taskList);//Where to get all the data from.
            //TODO: Check out what the adapter of a view means in android.
            mTaskListView.setAdapter(mAdapter);//Set it as the adapter of the ListView instance.
        } else {

            /*If the adapter is already created, which implies that's already asssigned to the
            * ListView , clear it, re-populate it and notify the vieew that the data has changed.
            * This means that the view will repaint on the screen with the new data*/
         /*   mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();*/
    }


    //When the done button in item_todo.xml is clicked it calls this method:
    /*public void deleteTask(View view){
        //TODO: Find out what it's meant by the parent of a view
        View parent = (View) view.getParent();

        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);

        //Task name:

        String task = String.valueOf(taskTextView.getText());

        //Updating the database, removing the entry:

        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(TaskContract.TaskEntry.TABLE,TaskContract.TaskEntry.COL_TASK_TITLE+"=?",new String[]{task});

        db.close();

        //Update UI after we have deleted it:

        this.updateUI();
    }*/


}
