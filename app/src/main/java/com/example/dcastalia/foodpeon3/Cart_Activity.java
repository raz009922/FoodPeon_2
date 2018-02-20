package com.example.dcastalia.foodpeon3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcastalia.foodpeon3.SQLite.SqliteDatabaseAdapter;

public class Cart_Activity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    TextView txtName, txtDescription,txtPrice;
    SqliteDatabaseAdapter sqliteDatabaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        init();

    }

    private void init() {
        imageView=findViewById(R.id.cartImageView);
        txtName=findViewById(R.id.FoodNameTextView);
        txtDescription=findViewById(R.id.DescriptioextViewnT);
        txtPrice=findViewById(R.id.PriceTextView);

        sqliteDatabaseAdapter=new SqliteDatabaseAdapter(Cart_Activity.this);
        String id =sqliteDatabaseAdapter.getID();
        String name=sqliteDatabaseAdapter.getName();
        String address=sqliteDatabaseAdapter.getAddress();

        if(id!=null){

            Toast.makeText(Cart_Activity.this,
                    "Successfully retrieve from database.",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Cart_Activity.this,
            "Unsuccessful to retrieve from database.",Toast.LENGTH_SHORT).show();
        }

        txtName.setText(id);
        txtDescription.setText(name);
        txtPrice.setText(address);

    }
}
