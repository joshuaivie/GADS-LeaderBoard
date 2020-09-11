package com.example.gadsleaderboard.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gadsleaderboard.MainActivity;
import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.ui.apiUtils.RetrofitInterface_Submit;
import com.example.gadsleaderboard.ui.apiUtils.ServiceBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Submit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        //Form Fields
        EditText first = (EditText) findViewById(R.id.editFirstSubmitForm);
        EditText last = (EditText) findViewById(R.id.editLastSubmitForm);
        EditText email = (EditText) findViewById(R.id.editEmailSubmitForm);
        EditText link = (EditText) findViewById(R.id.editLinkSubmitForm);

        //Action Buttons
        ImageView backButton = (ImageView) findViewById(R.id.imgBack);
        Button submitButton = (Button) findViewById(R.id.btnSubmitForm);

        //Set-Up Back Button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Submit.this, MainActivity.class));
            }
        });

        //Set-Up Submit Button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Input from Text Fields
                String firstName = first.getText().toString();
                String lastName = last.getText().toString();
                String Email = email.getText().toString();
                String mLink = link.getText().toString();

                //Confirm that no field is empty
                if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(Email) && !TextUtils.isEmpty(mLink)){

                    //Link all Elements on Confirmation Page
                    LayoutInflater layoutInflater = Submit.this.getLayoutInflater();
                    View confirmView = layoutInflater.inflate(R.layout.notification_confirm, null);
                    ImageView cancelImage = (ImageView) confirmView.findViewById(R.id.cancelButton);
                    Button confirmButton = (Button) confirmView.findViewById(R.id.confirmButton);

                    //Create Alert Dialog
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Submit.this);
                    dialogBuilder.setView(confirmView);
                    AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();

                    //Create Cancel Action
                    cancelImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    //Create Submit Button
                    confirmButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                getResponse(firstName, lastName, Email, mLink);
                                alertDialog.dismiss();
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    });
                }

                // Set Errors for Blank Fields
                else if (TextUtils.isEmpty(firstName)){
                    first.setError("Enter first Name");
                }
                else if (TextUtils.isEmpty(lastName)){
                    last.setError("Enter Last Name");
                }
                else if (TextUtils.isEmpty(Email)){
                    email.setError("Enter Email");
                }
                else if (TextUtils.isEmpty(mLink)){
                    link.setError("Enter Link");
                }

            }
        });

    }

    private void getResponse(String firstName, String lastName, String email, String mLink) throws IOException {
        //Implement Retrofit Interface
        RetrofitInterface_Submit retrofitSubmitInterface = ServiceBuilder.buildPostService(RetrofitInterface_Submit.class);
        Call<Void> call = retrofitSubmitInterface.postProjectData(firstName, lastName, email, mLink);

        //Run Call
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Submit.this);
                LayoutInflater layoutInflater = Submit.this.getLayoutInflater();
                View v = layoutInflater.inflate(R.layout.notification_success, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Submit.this);
                LayoutInflater layoutInflater = Submit.this.getLayoutInflater();
                View v = layoutInflater.inflate(R.layout.notification_failure, null);
                builder.setView(v);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }
}