package com.acidlab.ubci_reclamations.networking;


import com.acidlab.ubci_reclamations.Models.Reclamation;
import com.acidlab.ubci_reclamations.Models.User;

import java.util.List;

public interface NetworkingAsyncResponse {

    default void onUserLogin(User user){}

    default void onUserRegister(User user) {}

    default void onReclamationEnAttenteGetter(List<Reclamation> reclamations) {}

    default void onReclamationEnCoursGetter(List<Reclamation> reclamations) {}

}
