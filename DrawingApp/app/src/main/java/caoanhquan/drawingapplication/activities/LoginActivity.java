package caoanhquan.drawingapplication.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import caoanhquan.drawingapplication.R;

public class LoginActivity extends AppCompatActivity {

    private Button mLoginButton;
    private Button mRegisterButton;
    private EditText mEmail;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mRegisterButton = (Button) findViewById(R.id.btn_register);
        mEmail = (EditText) findViewById(R.id.email);
        final RequestQueue queue = Volley.newRequestQueue(this);
        final Intent intent = new Intent(getApplicationContext(), DrawActivity.class);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest("http://dev.colorme.vn/api/getuserdata?email=" + mEmail.getText(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("USTHWeather", "Json response " + response);
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            String name = obj.getString("name");
                            Toast.makeText(getApplicationContext(), "Welcome " + name, Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });

                queue.add(request);
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
