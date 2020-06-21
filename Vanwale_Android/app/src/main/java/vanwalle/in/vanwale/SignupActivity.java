package vanwalle.in.vanwale;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vanwalle.in.vanwale.Retrofit.ApiHelperClass;
import vanwalle.in.vanwale.Retrofit.ApiWebservices;
import vanwalle.in.vanwale.Retrofit.CustomNetwork;

public class SignupActivity extends AppCompatActivity {
EditText phone;
TextInputEditText name,email,password;
Button sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        phone=(EditText)findViewById(R.id.phone_no);
        name=(TextInputEditText)findViewById(R.id.name);
        password=(TextInputEditText)findViewById(R.id.password);
        email=(TextInputEditText)findViewById(R.id.email);
        sign=(Button) findViewById(R.id.sign);
        if(getIntent()!=null)
        {
            phone.setText(getIntent().getStringExtra("phone"));
        }
sign.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if((TextUtils.isEmpty(phone.getText().toString())&&TextUtils.isEmpty(name.getText().toString())&&TextUtils.isEmpty(password.getText().toString())&&TextUtils.isEmpty(email.getText().toString())))
        {
            Toast.makeText(getApplicationContext(),"Enter the Details First",Toast.LENGTH_SHORT).show();
        }
        else
        {
            getRegistrationWS(name.getText().toString(), email.getText().toString(), phone.getText().toString(), password.getText().toString());
        }
    }
});


    }
    private void getRegistrationWS(String username, String email, String phone, String password) {
        if (CustomNetwork.isNetworkAvailable(getApplicationContext())) {

            ApiWebservices apiWebservices = ApiHelperClass.getClient().create(ApiWebservices.class);
            Call<String> call = apiWebservices.getRegistrationRes(username, email, phone, password);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String modelRes = response.body();
                    Toast.makeText(getApplicationContext(),modelRes,Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        } else {

        }
    }


}
