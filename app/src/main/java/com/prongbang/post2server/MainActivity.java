package com.prongbang.post2server;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.prongbang.post2server.service.AccountService;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private String HOST_SERVICE = "https://ex-prongbang.rhcloud.com";


    private Button buttonPost;
    private TextView textViewResponse;
    private EditText editTextID;
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSurname = (EditText) findViewById(R.id.editTextSurname);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewResponse = (TextView) findViewById(R.id.textViewResponse);

        // เรียกใช้ Button ใน XML id=button_post
        buttonPost = (Button) findViewById(R.id.button_post);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // ทำงานเมื่อกดปุ่ม POST DATA

                // เตรียมข้อมุลเพื่อจะส่งไปให้ Server
                Map<String, Object> parameter = new HashMap<String, Object>();
                parameter.put("id"      , editTextID.getText());        // ดึงค่า ID โดยใช้ editTextID.getText(); เพื่อกำหนดค่าให้กับ id
                parameter.put("name"    , editTextName.getText());      //                      ""
                parameter.put("surname" , editTextSurname.getText());   //                      ""
                parameter.put("username", editTextUsername.getText());  //                      ""
                parameter.put("password", editTextPassword.getText());  //                      ""

                /**
                 * POST DATA
                 */
                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(HOST_SERVICE).build();

                // เรียกใช้ Service ที่เราเขียน interface ไว้ มันจะส่งค่าไปที่ Url ที่เรากำหนดไว้ให้
                AccountService accountService = adapter.create(AccountService.class);
                accountService.post(parameter, new Callback<Object>() {
                    @Override
                    public void success(Object o, Response response) {
                        // o คือค่าที่ได้จาก Server
                        Log.i("success", o.toString());         // แสดงค่าแบบ Log

                        // ส่งค่าไปแสดงที่หน้าจอ
                        textViewResponse.setText(o.toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // Error
                        Log.e("failure", error.getMessage());   // แสดงค่าแบบ Log
                    }
                });
            }
        });


    }
}
