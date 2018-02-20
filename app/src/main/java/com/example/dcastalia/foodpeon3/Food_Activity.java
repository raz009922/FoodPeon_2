package com.example.dcastalia.foodpeon3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dcastalia.foodpeon3.API.Retrofit2;
import com.example.dcastalia.foodpeon3.Adapter.MyAdapter;
import com.example.dcastalia.foodpeon3.Model.Resturent;
import com.example.dcastalia.foodpeon3.SQLite.SqliteDatabaseAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Food_Activity extends AppCompatActivity {

    ListView listView;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    View v;
    SqliteDatabaseAdapter sqliteDatabaseAdapter;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_food);
        setupRecyclerView();
        //listView=findViewById(R.id.listView);
        //here we build the retrofit
        Gson gson = new GsonBuilder() .setLenient() .create();
        Log.d(""+gson,"JSON");
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();

        Log.i("JSON","yahooo  "+gson);
//now we have retofit object.
//then we need to build our api.
        Retrofit2   retroFIT=retrofit.create(Retrofit2.class);

        Call<List<Resturent>> call =  retroFIT.getRes();

        //calling the api. it need two callback one is response and the other is failure

        call.enqueue(new Callback<List<Resturent>>() {
            @Override
            public void onResponse(Call<List<Resturent>> call, final Response<List<Resturent>> response) {
                Log.i("JSON","yahoooaa  "+response);

                // Log.i(""+response,"JSON");
                final List<Resturent> resturents=response.body();

                adapter=new MyAdapter(resturents,getApplicationContext());
                recyclerView.setAdapter(adapter);
                SwipeHelper swipeHelper = new SwipeHelper(Food_Activity.this, recyclerView) {
                    @Override
                    public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                        underlayButtons.add(new SwipeHelper.UnderlayButton(
                                "Cart",
                                0,
                                Color.parseColor("#FF3C30"),
                                new SwipeHelper.UnderlayButtonClickListener() {
                                    @Override
                                    public void onClick(int pos) {
                                        // TODO: CART
                                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Food_Activity.this);
                                        String PlaceName= preferences.getString("PlaceName","");
                                        Log.i("PlaceName","PlaceName:  "+PlaceName);


                                            int kitchenID = adapter.kid;
                                            String KID= String.valueOf(kitchenID);
                                            String KitchenNAME = adapter.kName;
                                        Log.i("PlaceName","ID:  "+kitchenID);
                                        Log.i("PlaceName","Kitchen Name:  "+KitchenNAME);
                                        if(kitchenID>0){
                                            Log.i("PlaceName","LONG ID empty");

                                            sqliteDatabaseAdapter=new SqliteDatabaseAdapter(Food_Activity.this);


                                          long id=  sqliteDatabaseAdapter.insertData(KID,KitchenNAME,PlaceName);
                                            Log.i("PlaceName","LONG ID:  "+id);

                                          if (id<0){
                                              Toast.makeText(Food_Activity.this,
                                                      "Unsuccessful to insert a row",Toast.LENGTH_SHORT)
                                                      .show();


                                          }

                                          else {

                                              Toast.makeText(Food_Activity.this,
                                                      "Successful to insert a row",Toast.LENGTH_SHORT)
                                                      .show();
                                          }



                                        }
                                        else {
                                            Toast.makeText(Food_Activity.this,
                                                    "SORRY!!! The Kitchen ID is empty",Toast.LENGTH_SHORT)
                                                    .show();

                                        }





//                                        Toast.makeText(Food_Activity.this,
//                                                "Cart Added!! Please check it below",Toast.LENGTH_SHORT)
//                                                .show();

                                    }
                                }
                        ));


                    }
                };

               /* swipeController = new SwipeController(new SwipeControllerActions() {
                    @Override
                    public void onRightClicked(int position) {

                        Toast.makeText(Food_Activity.this,"Cart Added",Toast.LENGTH_LONG);
                        super.onRightClicked(position);
                    }
                });*/









/*                adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        showFoodDetailsDialouge();


                    }


                });*/



              /*  List<Resturent> Resturent=response.body();
                MyAdapter adapter;
                adapter=new MyAdapter(Resturent,getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(KitchenActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                int itemPosition = recyclerView.getChildLayoutPosition(view);
                                String item = mList.get(itemPosition);
                                Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Toast.makeText(KitchenActivity.this,"Press More",Toast.LENGTH_SHORT).show();

                            }
                        })
                );
                in response we can get the list of movies..or anything that we need or we have in our class
                and we get it from above response object

                List<Resturent> kitchexns222 = new ArrayList<>();
                 now we can do whatever we want to with this Resturent object

                lets create an array {after the equal we define the size of the array :p}
                String [] moviesNAME= new String[Resturent.size()];
                String [] moviesBio= new String[Resturent.size()];

                for (int i=0; i< Resturent.size();i++){
                  moviesNAME[i]=Resturent.get(i).getName();
                   moviesBio[i+1]=Resturent.get(i+1).getBio();
                    Resturent Resturent1=new Resturent(moviesNAME,,moviesBio);
                    Resturent Resturent1=new Resturent("raz"+i+1,"asdasdasdjhasgdhas");

                    kitchexns222.add(Resturent1);


                   // moviesNAME[i]=Resturent.get(i).getBio();
                }
                adapter=new MyAdapter(kitchexns222,getApplicationContext());
                recyclerView.setAdapter(adapter);

                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,moviesNAME));
*/

            }

            @Override
            public void onFailure(Call<List<Resturent>> call, Throwable t) {
                Log.i("JSON","yahooobb  "+call);

                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG)
                        .show();

            }
        });

    }

    private void showFoodDetailsDialouge() {

    }

    private void setupRecyclerView(){

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent foodlist =new Intent(MapsActivity.this,Food_Activity.class);

                startActivity(foodlist);*/

                Intent intent = new Intent(Food_Activity.this,Cart_Activity.class);
                startActivity(intent);
            }
        });



//        swipeController=new SwipeController();
//        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
//        itemTouchhelper.attachToRecyclerView(recyclerView);
//
//
//
//        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                swipeController.onDraw(c);
//            }
//        });







    }

}
