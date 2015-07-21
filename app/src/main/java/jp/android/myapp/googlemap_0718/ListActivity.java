package jp.android.myapp.googlemap_0718;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class ListActivity extends Activity {
    private ListView myListView;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    private Cursor searchToDB() throws Exception {
        Cursor c = db.rawQuery("select * from " + MyDBHelper.TABLE_NAME, null);
        return c;
    }

    @Override
    public void onStart(){
        super.onStart();

        myListView = (ListView)findViewById(R.id.listView);

        MyDBHelper dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase();

        Button btn = (Button)findViewById(R.id.transRegButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        String[] regData = new String[7];
        Intent intent = getIntent();

        if(intent != null) {

            regData[0] = intent.getStringExtra("name");
            regData[1] = intent.getStringExtra("address");
            regData[2] = intent.getStringExtra("browse");
            regData[3] = intent.getStringExtra("beauty");
            regData[4] = intent.getStringExtra("service");
            regData[5] = intent.getStringExtra("customer");
            regData[6] = intent.getStringExtra("toilet");


            try {
                insertToDB(regData);
                Cursor c = searchToDB();
                String[] from = {MyDBHelper.REG_NAME, MyDBHelper.REG_ADDRESS, MyDBHelper.REG_BROWSE, MyDBHelper.REG_BEAUTY,
                        MyDBHelper.REG_SERVICE, MyDBHelper.REG_CUSTOMER, MyDBHelper.REG_TOILET};

                int[] to = {R.id.regName, R.id.regAddress, R.id.regBrowse, R.id.regBeauty, R.id.regService,
                        R.id.regCustomer, R.id.regToilet};

                SimpleCursorAdapter adapter =
                        new SimpleCursorAdapter(this, R.layout.list_layout, c, from, to, 0);

                myListView.setAdapter(adapter);

                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent intent = new Intent(ListActivity.this, MapsActivity.class);

                        String s1 = ((TextView) view.findViewById(R.id.regName)).getText().toString();
                        String s2 = ((TextView) view.findViewById(R.id.regAddress)).getText().toString();

                        //MapActivityに
                        intent.putExtra("address",s2);

                        startActivity(intent);
                    }
                });

            } catch (Exception e) {

                e.printStackTrace();

            } finally {
                db.close();
            }
        }
    }
    private void insertToDB(String[] regData) throws Exception{

        db.execSQL("insert into myDate("
                + MyDBHelper.REG_NAME + ","
                + MyDBHelper.REG_ADDRESS + ","
                + MyDBHelper.REG_BROWSE + ","
                + MyDBHelper.REG_BEAUTY + ","
                + MyDBHelper.REG_SERVICE + ","
                + MyDBHelper.REG_CUSTOMER + ","
                + MyDBHelper.REG_TOILET + ")values('"+regData[0]+"','"+regData[1]+"','"+regData[2]+"','"+regData[3]+"','"+regData[4]+"','"+regData[5]+"','"+regData[6]+"')");
    }
}
