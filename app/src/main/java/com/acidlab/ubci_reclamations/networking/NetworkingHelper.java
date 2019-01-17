package com.acidlab.ubci_reclamations.networking;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.acidlab.ubci_reclamations.Models.Entity;
import com.acidlab.ubci_reclamations.Models.Local;
import com.acidlab.ubci_reclamations.Models.Reclamation;
import com.acidlab.ubci_reclamations.Models.User;
import com.acidlab.ubci_reclamations.Utils.Utilities;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkingHelper {


    private Context context;
    private Fragment fragment;
    private String url = "http://demo.ubci.acidlabs.co/api/";

    public NetworkingHelper(Context context) {
        this.context = context;
    }

    public NetworkingHelper(Fragment fragment) {
        this.fragment = fragment;
    }


    public void register(final String fname, final String lname, final String email, final String password, final int code) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = this.url + "register";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            if (!response.equals("error")) {

                                JSONObject jsonResponse = new JSONObject(response);
                                JSONObject clientObject = jsonResponse.getJSONObject("user");

                                //getAttributs
                                int id = clientObject.optInt("id");
                                String lname = clientObject.optString("lname");
                                String fname = clientObject.optString("fname");
                                String email = clientObject.optString("email");
                                User user = new User(id, lname, fname, email);

                                user.saveUser(context);
                                List<Local> locals = new ArrayList<Local>();

                                JSONArray localsObject = jsonResponse.getJSONArray("locals");

                                for (int i = 0; i < localsObject.length(); i++) {
                                    JSONObject local = localsObject.getJSONObject(i);
                                    int idLocal = local.getInt("id");
                                    String labelLocal = local.getString("label");
                                    Local localObject = new Local(idLocal, labelLocal);
                                    locals.add(localObject);
                                }

                                Local.setLocals(locals);

                                List<Entity> entities = new ArrayList<Entity>();
                                JSONArray entitiesObject = jsonResponse.getJSONArray("entities");

                                for (int i = 0; i < entitiesObject.length(); i++) {
                                    JSONObject entity = entitiesObject.getJSONObject(i);
                                    int idEntity = entity.getInt("id");
                                    String codeEntity = entity.getString("code");
                                    String labelEntity = entity.getString("label");
                                    String abregeEntity = entity.getString("abrege");
                                    String regionEntity = entity.getString("region");
                                    Entity entityObject = new Entity(idEntity, codeEntity, labelEntity, abregeEntity, regionEntity);
                                    entities.add(entityObject);
                                }

                                ((NetworkingAsyncResponse) context).onUserRegister(user);

                            } else {

                                ((NetworkingAsyncResponse) context).onUserRegister(null);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("email", email);
                params.put("lname", lname);
                params.put("fname", fname);
                params.put("poste", String.valueOf(code));
                params.put("password", password);

                return params;
            }
        };
        queue.add(postRequest);
    }

    public void login(final String login, final String password) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = this.url + "login";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            if (!response.equals("error")) {

                                JSONObject jsonResponse = new JSONObject(response);
                                JSONObject clientObject = jsonResponse.getJSONObject("user");

                                int id = clientObject.optInt("id");
                                String lname = clientObject.optString("lname");
                                String fname = clientObject.optString("fname");
                                String email = clientObject.optString("email");
                                User user = new User(id, lname, fname, email);

                                user.saveUser(context);
                                List<Local> locals = new ArrayList<Local>();
                                JSONArray localsObject = jsonResponse.getJSONArray("locals");

                                for (int i = 0; i < localsObject.length(); i++) {
                                    JSONObject local = localsObject.getJSONObject(i);
                                    int idLocal = local.getInt("id");
                                    String labelLocal = local.getString("label");
                                    Local localObject = new Local(idLocal, labelLocal);
                                    locals.add(localObject);
                                }
                                Local.setLocals(locals);

                                List<Entity> entities = new ArrayList<Entity>();
                                JSONArray entitiesObject = jsonResponse.getJSONArray("entities");

                                for (int i = 0; i < entitiesObject.length(); i++) {
                                    JSONObject entity = entitiesObject.getJSONObject(i);
                                    int idEntity = entity.getInt("id");
                                    String codeEntity = entity.getString("code");
                                    String labelEntity = entity.getString("label");
                                    String abregeEntity = entity.getString("abrege");
                                    String regionEntity = entity.getString("region");
                                    Entity entityObject = new Entity(idEntity, codeEntity, labelEntity, abregeEntity, regionEntity);
                                    entities.add(entityObject);
                                }

                                ((NetworkingAsyncResponse) context).onUserLogin(user);

                            } else {

                                ((NetworkingAsyncResponse) context).onUserLogin(null);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("email", login);
                params.put("password", password);

                return params;
            }
        };
        queue.add(postRequest);
    }


    public void getReaclamationEnAttente(final int id, final String path) {
        RequestQueue queue = Volley.newRequestQueue(fragment.getContext());
        String url = this.url + path;

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {


                            if (!response.equals("error")) {

                                JSONArray jsonResponse = new JSONArray(response);
                                if (jsonResponse.length() != 0) {

                                    List<Reclamation> reclamations = new ArrayList<Reclamation>();
                                    JSONArray reclamationsArray = jsonResponse;

                                    for (int i = 0; i < reclamationsArray.length(); i++) {
                                        JSONObject reclamationJSON = reclamationsArray.getJSONObject(i);

                                        int idReclamation = reclamationJSON.getInt("id");
                                        String dateReclamation = reclamationJSON.getString("date");
                                        String sujetReclamation = reclamationJSON.getString("sujet");
                                        int natureIdReclamation = reclamationJSON.getInt("nature_id");
                                        int familleIdReclamation = reclamationJSON.getInt("famille_id");
                                        int localIdIdReclamation = reclamationJSON.getInt("local_id");
                                        String rappelReclamation = reclamationJSON.getString("rappel");
                                        int rappelNbrReclamation = reclamationJSON.getInt("rappel_nbr");
                                        String priorityReclamation = reclamationJSON.getString("priority_id");
                                        String contentReclamation = reclamationJSON.getString("content");
                                        String commentReclamation = reclamationJSON.getString("comment");
                                        int entityIdReclamation = reclamationJSON.getInt("entity_id");
                                        String interlocuteurReclamation = reclamationJSON.getString("interlocuteur");
                                        int statusIdReclamation = reclamationJSON.getInt("status_id");
                                        int userIdReclamation = reclamationJSON.getInt("user_id");

                                        Reclamation reclamation = new Reclamation(idReclamation, dateReclamation, sujetReclamation, natureIdReclamation,
                                                familleIdReclamation, localIdIdReclamation, rappelReclamation, rappelNbrReclamation, priorityReclamation,
                                                contentReclamation, commentReclamation, entityIdReclamation, interlocuteurReclamation, statusIdReclamation,
                                                userIdReclamation);
                                        reclamations.add(reclamation);
                                    }

                                    if (path.equals(Utilities.GetRecs)) {
                                        ((NetworkingAsyncResponse) fragment).onReclamationEnCoursGetter(reclamations);
                                    } else if (path.equals(Utilities.GetQuery)) {
                                        ((NetworkingAsyncResponse) fragment).onReclamationEnAttenteGetter(reclamations);
                                    }
                                } else {
                                    if (path.equals(Utilities.GetRecs)) {
                                        ((NetworkingAsyncResponse) fragment).onReclamationEnCoursGetter(null);
                                    } else if (path.equals(Utilities.GetQuery)) {
                                        ((NetworkingAsyncResponse) fragment).onReclamationEnAttenteGetter(null);
                                    }
                                }
                            } else {

                                if (path.equals(Utilities.GetRecs)) {
                                    ((NetworkingAsyncResponse) fragment).onReclamationEnCoursGetter(null);
                                } else if (path.equals(Utilities.GetQuery)) {
                                    ((NetworkingAsyncResponse) fragment).onReclamationEnAttenteGetter(null);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("id", String.valueOf(id));

                return params;
            }
        };
        queue.add(postRequest);
    }

    public void creationReclamation(final String sujet, final int id_local ) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = this.url + "insertQuery";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.equals("error")) {

                    ((NetworkingAsyncResponse) context).onReclamationCreated(1);

                } else {

                    ((NetworkingAsyncResponse) context).onReclamationCreated(0);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((NetworkingAsyncResponse) context).onReclamationCreated(0);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("local_id", String.valueOf(id_local));
                params.put("user_id", String.valueOf(User.getCurrentUser(context).getId()));
                params.put("name", User.getCurrentUser(context).getFname() + " " + User.getCurrentUser(context).getLname());
                params.put("sujet", sujet);
                // params.put("photos", "33");

                return super.getParams();
            }
        };

        queue.add(postRequest);

    }

}
