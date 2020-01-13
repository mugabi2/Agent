package com.example.samuelhimself.agent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class bicyclesIn extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private final FirebaseFirestore db=FirebaseFirestore.getInstance();

    static String json = "";
    static JSONObject jObj = null;

    String pkafrica,pkcedat,pkcomplex,pkfema,pklibrary,pklivingstone,pklumumba,pkmaingate,pkmarystuart,pkmitchell,pknkrumah,pkuh;


    CollectionReference collectionReference=db.collection("mukbikes");
    private DocumentReference dbref = db.document("BVSMUK/population");
    String somsing="256";
    int voom=0;

    private SharedPreferences prefol;
    private String prefName ="prearray";
    private static final String ID_KEY ="Identity";
    int number=20;

    ArrayList<bikesinclass> bikesarray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycles_in);

        prefol=getSharedPreferences(prefName,MODE_PRIVATE);
        bikesarray = new ArrayList<>();
//        bikesarray.add(new bikesinclass(somsing));

        db.collection("mukbikes")
                .whereEqualTo("location", "africa")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            ArrayList<bikesinclass> bikesray = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("torino", document.getId() + " => " + document.getData());
                                number++;
//                                SharedPreferences.Editor editor=prefol.edit();
//                                editor.putString(ID_KEY,document.getId());
//                                editor.commit();
//                                bikesinclass bine=new bikesinclass(document.getData().toString());
//                                bikesray.add(bine);
//                                Log.d("milan", bikesray.toString());
                                putinarray(document.getId().toString());
                            }
                        } else {
                            Log.d("torino", "Error getting documents: ", task.getException());
                        }
                    }
                });

//        String strings =prefol.getString(ID_KEY,"");
//        bikesarray.add(new bikesinclass(strings));

//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("task list", null);
//        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
//        bikesarray = gson.fromJson(json, type);
//
//        Log.d("lyon", bikesarray.toString());

//        Log.d("milan", bikesarray.toString());


//        STATUS BAR
        if(Build.VERSION.SDK_INT >=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.darkdarkTurq));
        }


        //---------------toolbar--------------------
        Toolbar toolbar =findViewById(R.id.intool);
        setSupportActionBar(toolbar);

//        bikesarray = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerViewbikesin);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new adapterbikesin(this,bikesarray);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        
    }
//    public ArrayList<bikesinclass> makebikesarray(){
//        ArrayList<bikesinclass> bikesarray;
//
//        return bikesarray;
//    }

    public void putinarray(String entry){
        bikesarray.add(new bikesinclass(entry));
//        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(bikesarray);
//        editor.putString("task list", json);
//        editor.apply();
        Log.d("milan", json);
        //////////////////////////////////////

//        Log.d(TAG, document.getId() + " => " + document.getData());
//
//        //document.getData().get("Date").toString();
//        //document.getData().get("Version").toString();
//        //List<String> dungeonGroup = (List<String>) document.get("Details");
//        //ArrayList<String> documentGroup = (ArrayList<String>) document.get("Details");
//
//        UpdateMessage updateMessage = new UpdateMessage(
//                document.getData().get("Date").toString(),
//                document.getData().get("Version").toString(),
//                (ArrayList<String>) document.get("Details"));
//
//        recyclerList.add(updateMessage);
//
//        //Log.d(TAG, "onComplete: ");
//
//        Log.d(TAG, document.getId() + " => " + document.getData());
//
//        //document.getData().get("Date").toString();
//        //document.getData().get("Version").toString();
//        //List<String> dungeonGroup = (List<String>) document.get("Details");
//        //ArrayList<String> documentGroup = (ArrayList<String>) document.get("Details");
//
//        UpdateMessage updateMessage = new UpdateMessage(
//                document.getData().get("Date").toString(),
//                document.getData().get("Version").toString(),
//                (ArrayList<String>) document.get("Details"));
//
//        recyclerList.add(updateMessage);
//
//        //Log.d(TAG, "onComplete: ");
//    }
//
//                            Log.d(TAG, "onComplete: " + recyclerList.size());
//    //isDataFinish = false;
//
//    MessageUpdateAdapter adapter = null;
//
//    // init
//    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                            mRecyclerView.setLayoutManager(linearLayoutManager);
//
//    // show data
//    adapter = new MessageUpdateAdapter(listSort.sortList(recyclerList), getContext());
//                            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
//                            mRecyclerView.setAdapter(adapter);
//                            adapter.notifyDataSetChanged();
//
//
    ///////////////////////////////////
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mainmenu:
                Intent int1 =new Intent(getApplicationContext(),profile.class);
                startActivity(int1);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
