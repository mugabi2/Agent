package com.example.samuelhimself.agent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class digitalTime extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String AGENT_CODE_KEY ="Agent Code";

    String serverKey="2y10pN0pj28Q9WNoLrPCI3mIwtDkHhBmfpFGWshiuHxvqmzsltGQKzS";
    String meso,surname,firstname,phonenumber,digitalPack,cash,agentee;
    static String json = "";
    static JSONObject jObj = null;

    private SharedPreferences prefs;
    private String prefName ="preProfile";

    ArrayList<ExampleItem> exampleList;

    int position,n;

    private onClickInterface onclickInterface;
    JSONObject usea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_time);

//        STATUS BAR
        if(Build.VERSION.SDK_INT >=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.darkdarkTurq));
        }


        prefs=getSharedPreferences(prefName,MODE_PRIVATE);
        agentee =prefs.getString(AGENT_CODE_KEY,"");

        Bundle extras=getIntent().getExtras();
        meso=extras.getString("info");

//        Toast.makeText(getApplicationContext(), meso, Toast.LENGTH_LONG).show();

        exampleList = new ArrayList<>();

        try {
            jObj = new JSONObject(meso);
            int  number = jObj.getInt("number");

            JSONArray userArray=jObj.getJSONArray("user");
            JSONArray userArr=userArray.getJSONArray(0);

            for (n=0;n<number;n++) {
                usea = userArr.getJSONObject(n);
                surname = usea.getString("SN");
                firstname = usea.getString("FN");
                phonenumber = usea.getString("PN");
                digitalPack = usea.getString("PK");
                cash = usea.getString("CS");

                exampleList.add(new ExampleItem(R.drawable.ic_carwash, surname, firstname, phonenumber, digitalPack, cash));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc) {
//                exampleList.remove(abc);
//                Toast.makeText(digitalTime.this,"Position is"+abc,Toast.LENGTH_LONG).show();

                try {
                    jObj = new JSONObject(meso);
                    int  number = jObj.getInt("number");

                    JSONArray userArray=jObj.getJSONArray("user");
                    JSONArray userArr=userArray.getJSONArray(0);

                        usea = userArr.getJSONObject(abc);
                        surname = usea.getString("SN");
                        firstname = usea.getString("FN");
                        phonenumber = usea.getString("PN");
                        digitalPack = usea.getString("PK");
                        cash = usea.getString("CS");
                Toast.makeText(digitalTime.this,surname+phonenumber+firstname,Toast.LENGTH_LONG).show();

                    new digitalTime.backgrounddtrep(digitalTime.this).execute();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(this,exampleList,onclickInterface);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }


    class backgrounddtrep extends AsyncTask<String, Void,String> {

        AlertDialog dialog;
        Context context;
        public backgrounddtrep(Context context){
            this.context=context;
        }

        @Override
        protected void onPostExecute(String s) {
//            dialog.setMessage(s);
//            dialog.show();

            json = s.toString();

            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            Log.e("diti", s);

            try {
                jObj = new JSONObject(json);
                int  success = jObj.getInt("success");

                switch (success){
                    case 0:
                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Intent int1 =new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(int1);
                        break;
                    case 2:
                        Intent int2 =new Intent(getApplicationContext(),finesOfUsers.class);
                        startActivity(int2);
                        break;
                    case 3:
                        Intent int3 =new Intent(getApplicationContext(),finalReg.class);
                        int3.putExtra("gettingInfo",s);
                        startActivity(int3);
                        break;

                }
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error creating the json object " + e.toString());
            }

//            ProgressBar pb =findViewById(R.id.progressBar11);
//            pb.setVisibility(ProgressBar.INVISIBLE);
//
//            Intent int2=new Intent(MainActivity.this,confirmRequests.class);
//            int2.putExtra("getInfo",s);
//            startActivity(int2);

        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";

//            String acode=voids[0];
            String connstr="http://stardigitalbikes.com/digital_time_sell.php";

            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("agent","UTF-8")+"="+URLEncoder.encode(agentee,"UTF-8")
                        +"&&"+ URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(surname,"UTF-8")
                        +"&&"+ URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(firstname,"UTF-8")
                        +"&&"+ URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(phonenumber,"UTF-8")
                        +"&&"+ URLEncoder.encode("digitaltimepack","UTF-8")+"="+URLEncoder.encode(digitalPack,"UTF-8")
                        +"&&"+ URLEncoder.encode("serverKey","UTF-8")+"="+URLEncoder.encode(serverKey,"UTF-8")
                        +"&&"+ URLEncoder.encode("cash","UTF-8")+"="+URLEncoder.encode(cash,"UTF-8");

                writer.write(data);
                writer.flush();
                writer.close();
                ops.close();
                Log.d("JSON Exception","DONE SENDING");

                InputStream ips =http.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
                String line ="";
                while ((line=reader.readLine()) !=null){
                    result +=line;

                }
//#######INTRODICING THE READING OOF THE RETURNED JSON
                ips.close();
                reader.close();
                json = result.toString();

//                try {
//                    jObj = new JSONObject(json);
//                    if(json!=null){
//                        int success=jObj.getInt("success");
//
//                        Log.d("JSONStatus", "JSON RETURNED");
//
//                        if(success==1){
//                            JSONArray userArray=jObj.getJSONArray("user");
//                            JSONObject user=userArray.getJSONObject(0);
//                            usersurname=user.getString("SN");
//                            userfirstname=user.getString("FN");
//                            userphonenumb=user.getString("PN");
//                            useremailadd=user.getString("EM");
//                            userresidence=user.getString("RD");
//                            userduration=user.getString("DR");
//                            userpaymeth=user.getString("PM");
//                            usercash=user.getString("CS");
//                            Log.d("JSONStatus","Login success");
//
//                        }else{
//                            Log.d("JSONStatus","Login failure");
//                            message=jObj.getString("message");
//                            Log.d("JSONStatus",message);
//                        }
//                    }else{
//                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
//                    }
//                } catch (JSONException e) {
//                    Log.e("JSON Parser", "Error creating the json object " + e.toString());
//                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }
            return result;
        }
    }

}
