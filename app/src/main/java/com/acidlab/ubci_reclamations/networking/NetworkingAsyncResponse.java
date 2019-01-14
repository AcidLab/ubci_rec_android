package com.acidlab.ubci_reclamations.networking;


import com.acidlab.ubci_reclamations.Models.User;

public interface NetworkingAsyncResponse {

    default void onUserLogin(User user){}

    default void onUserRegister(User user) {}
}
