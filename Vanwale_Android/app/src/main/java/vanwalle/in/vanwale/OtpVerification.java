package vanwalle.in.vanwale;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerification extends AppCompatActivity {


    private String verificationId;
    private FirebaseAuth mAuth;
    private EditText otpEdit1;
    private EditText otpEdit2;
    private EditText otpEdit3;
    private EditText otpEdit4;
    private EditText otpEdit5;
    private EditText otpEdit6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
        FirebaseApp.initializeApp(this);
        String phonenumber = getIntent().getStringExtra("phonenumber");
        Toast.makeText(getApplicationContext(), phonenumber, Toast.LENGTH_LONG).show();
        mAuth = FirebaseAuth.getInstance();
        otpEdit1 = findViewById(R.id.otp_1);
        otpEdit2 = findViewById(R.id.otp_2);
        otpEdit3 = findViewById(R.id.otp_3);
        otpEdit4 = findViewById(R.id.otp_4);
        otpEdit5 = findViewById(R.id.otp_5);
        otpEdit6 = findViewById(R.id.otp_6);

        sendVerificationCode(phonenumber);
        findViewById(R.id.verify_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = otpEdit1.getText().toString().trim() + otpEdit2.getText().toString().trim() +
                        otpEdit3.getText().toString().trim() + otpEdit4.getText().toString().trim() + otpEdit5.getText().toString().trim()
                        + otpEdit6.getText().toString().trim();

                if (code.isEmpty()) {
                    otpEdit1.addTextChangedListener(new GenericTextWatcher(otpEdit1));
                    otpEdit2.addTextChangedListener(new GenericTextWatcher(otpEdit2));
                    otpEdit3.addTextChangedListener(new GenericTextWatcher(otpEdit3));
                    otpEdit4.addTextChangedListener(new GenericTextWatcher(otpEdit4));
                    otpEdit5.addTextChangedListener(new GenericTextWatcher(otpEdit5));
                    otpEdit6.addTextChangedListener(new GenericTextWatcher(otpEdit6));
                }
                verifyCode(code);
            }
        });

    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        SignInWithCredential(credential);
    }

    private void SignInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(OtpVerification.this, Dashboard.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(OtpVerification.this, "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OtpVerification.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    public class GenericTextWatcher implements TextWatcher {
        private View view;

        GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean allOtherFilled = false;
            EditText nextEdit = null;
            EditText previousEdit = null;
            switch (view.getId()) {
                case R.id.otp_1:
                    allOtherFilled = otpEdit2.getText().length() == 1
                            && otpEdit3.getText().length() == 1
                            && otpEdit4.getText().length() == 1
                            && otpEdit5.getText().length() == 1
                            && otpEdit6.getText().length() == 1;
                    nextEdit = otpEdit2;
                    break;
                case R.id.otp_2:
                    allOtherFilled = otpEdit1.getText().length() == 1
                            && otpEdit3.getText().length() == 1
                            && otpEdit4.getText().length() == 1
                            && otpEdit5.getText().length() == 1
                            && otpEdit6.getText().length() == 1;
                    nextEdit = otpEdit3;
                    previousEdit = otpEdit1;
                    break;
                case R.id.otp_3:
                    allOtherFilled = otpEdit1.getText().length() == 1
                            && otpEdit2.getText().length() == 1
                            && otpEdit4.getText().length() == 1
                            && otpEdit5.getText().length() == 1
                            && otpEdit6.getText().length() == 1;
                    nextEdit = otpEdit4;
                    previousEdit = otpEdit2;
                    break;
                case R.id.otp_4:
                    allOtherFilled = otpEdit1.getText().length() == 1
                            && otpEdit2.getText().length() == 1
                            && otpEdit3.getText().length() == 1
                            && otpEdit5.getText().length() == 1
                            && otpEdit6.getText().length() == 1;
                    nextEdit = otpEdit5;
                    previousEdit = otpEdit3;
                    break;
                case R.id.otp_5:
                    allOtherFilled = otpEdit1.getText().length() == 1
                            && otpEdit2.getText().length() == 1
                            && otpEdit3.getText().length() == 1
                            && otpEdit4.getText().length() == 1
                            && otpEdit6.getText().length() == 1;
                    nextEdit = otpEdit6;
                    previousEdit = otpEdit4;
                    break;
                case R.id.otp_6:
                    allOtherFilled = otpEdit1.getText().length() == 1
                            && otpEdit2.getText().length() == 1
                            && otpEdit3.getText().length() == 1
                            && otpEdit4.getText().length() == 1
                            && otpEdit5.getText().length() == 1;
                    previousEdit = otpEdit5;
                    break;
            }

            if (s.length() == 1) {
                if (allOtherFilled) {
                    //if next 2 edit texts are filled , enable the pay button
                }
            } else if (s.length() > 1) {
                if (allOtherFilled) {
                    //if all next edit texts are filled , enable the pay button

                } else if (nextEdit != null) {
                    if (nextEdit.getText().length() == 0) {
                        //if next edit is not filled, move to next edit and set the second digit
                        moveToNextEdit(nextEdit, (EditText) view);
                    } else {
                        //if any other edit is not filled, stay in current edit

                        stayOnCurrentEdit((EditText) view);
                    }
                }
            } else if (s.length() < 1) {
                if (null != previousEdit)
                    moveToPreviousEdit(previousEdit);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        private void stayOnCurrentEdit(EditText editText) {
            editText.setText(editText.getText().toString().substring(0, 1));
            editText.setSelection(editText.getText().length());
        }

        private void moveToPreviousEdit(EditText editText) {
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
        }

        private void moveToNextEdit(EditText editText2, EditText editText1) {
            editText2.setText(editText1.getText().toString().substring(1, 2));
            editText2.requestFocus();
            editText2.setSelection(editText2.getText().length());
            editText1.setText(editText1.getText().toString().substring(0, 1));
        }

    }
}
