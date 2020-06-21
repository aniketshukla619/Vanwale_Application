package vanwalle.in.vanwale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vanwalle.in.vanwale.Retrofit.ApiHelperClass;
import vanwalle.in.vanwale.Retrofit.ApiWebservices;

public class LoginActivity extends AppCompatActivity {
EditText phone;
    String modelRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone=(EditText)findViewById(R.id.phone_no);


    }
    public void continue_button(View view) {
if(TextUtils.isEmpty(phone.getText().toString()))
{
    Toast.makeText(getApplicationContext(),"Please Enter the Phone Number",Toast.LENGTH_SHORT).show();
}
else {
    ApiWebservices apiWebservices = ApiHelperClass.getClient().create(ApiWebservices.class);
    Call<String> call = apiWebservices.checkuser(phone.getText().toString());
    call.enqueue(new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            modelRes = response.body();
            if (modelRes != null) {
                Log.d("phone",modelRes);
                if (modelRes.equals("false")) {

                    String phonenumber = "+91" + phone.getText().toString();

                    Intent newintent = new Intent(LoginActivity.this, OtpVerification.class);
                    newintent.putExtra("phonenumber", phonenumber);
                    startActivity(newintent);


                } else if(modelRes.equals("true")) {
                    //login with otp
                    Intent newintent = new Intent(LoginActivity.this, SignupActivity.class);
                    newintent.putExtra("phone", phone.getText().toString());
                    startActivity(newintent);
                    LoginActivity.this.finish();
                }

            }
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {

//                    CustomToast.showerror(getContext(), getString(R.string.SERVERERROR));
        }
    });
}   }




    private void getLoginWS(String email,  String password) {
            ApiWebservices apiWebservices = ApiHelperClass.getClient().create(ApiWebservices.class);
            Call<String> call = apiWebservices.getLoginRes(email, password);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String modelRes = response.body();

                    if (modelRes != null) {


                    }
                }


                @Override
                public void onFailure(Call<String> call, Throwable t) {

//                    CustomToast.showerror(getContext(), getString(R.string.SERVERERROR));
                }
            });
        }
}
