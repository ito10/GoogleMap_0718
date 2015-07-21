package jp.android.myapp.googlemap_0718;

import android.app.*;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }



    @Override
    public void onStart(){
        super.onStart();

        Button btn = (Button)findViewById(R.id.regButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, android.app.ListActivity.class);

                EditText name = (EditText)findViewById(R.id.editName);
                EditText address = (EditText)findViewById(R.id.editAddress);
                EditText browse = (EditText)findViewById(R.id.editBrowse);
                EditText beauty = (EditText)findViewById(R.id.editBeauty);
                EditText service = (EditText)findViewById(R.id.editService);
                EditText customer = (EditText)findViewById(R.id.editCustomer);
                EditText toilet = (EditText)findViewById(R.id.editToilet);

                intent.putExtra("name", name.getText().toString());
                intent.putExtra("address", address.getText().toString());
                intent.putExtra("browse", browse.getText().toString());
                intent.putExtra("beauty", beauty.getText().toString());
                intent.putExtra("service", service.getText().toString());
                intent.putExtra("customer", customer.getText().toString());
                intent.putExtra("toilet", toilet.getText().toString());

                startActivity(intent);

            }
        });
    }

}