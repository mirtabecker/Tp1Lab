package com.example.tp1lab.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp1lab.modelo.Usuario;
import com.example.tp1lab.request.ApiClient;
import com.example.tp1lab.ui.registro.RegistroActivity;

import java.util.ConcurrentModificationException;

public class MainActivityViewModel extends AndroidViewModel {
private Context context;
private MutableLiveData<String> error;
private ApiClient apiClient;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        apiClient=new ApiClient();
    }

 public LiveData<String> getError(){
        if(error == null){
            error = new MutableLiveData<>();
        }
        return error;
 }

 public void autenticar(String mail, String pass){
     Usuario usuario=apiClient.login(context,mail,pass);
     if (usuario != null){
         error.setValue("");
         Intent intent=new Intent(context, RegistroActivity.class);
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         intent.putExtra("usuario",usuario);
         context.startActivity(intent);
     }
     else {
         error.setValue("Email o Password incorrecto ");
     }
 }

 public void aRegistrar(){
        Intent intent=new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
}

