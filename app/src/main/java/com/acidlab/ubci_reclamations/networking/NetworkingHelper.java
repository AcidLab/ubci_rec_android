package com.acidlab.ubci_reclamations.networking;

import android.content.Context;
import android.util.Log;

import com.acidlab.ubci_reclamations.Models.Entity;
import com.acidlab.ubci_reclamations.Models.Local;
import com.acidlab.ubci_reclamations.Models.User;
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
    private String url = "http://demo.ubci.acidlabs.co/api/";

    public NetworkingHelper(Context context) {
        this.context = context;
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

                                //getAttributs
                                int id = clientObject.optInt("id");
                                String lname = clientObject.optString("lname");
                                String fname = clientObject.optString("fname");
                                String email = clientObject.optString("email");
                                User user = new User(id, lname, fname, email);

                                List<Local> locals = new ArrayList<Local>();
                                JSONArray localsObject = jsonResponse.getJSONArray("locals");

                                for (int i = 0; i < localsObject.length(); i++) {
                                    JSONObject local = localsObject.getJSONObject(i);
                                    int idLocal = local.getInt("id");
                                    String labelLocal = local.getString("label");
                                    Local localObject = new Local(idLocal,labelLocal);
                                    locals.add(localObject);
                                }


                                List<Entity> entities = new ArrayList<Entity>();
                                JSONArray entitiesObject = jsonResponse.getJSONArray("entities");

                                for (int i = 0; i < entitiesObject.length(); i++) {
                                    JSONObject entity = entitiesObject.getJSONObject(i);
                                    int idEntity = entity.getInt("id");
                                    String codeEntity = entity.getString("code");
                                    String labelEntity = entity.getString("label");
                                    String abregeEntity = entity.getString("abrege");
                                    String regionEntity = entity.getString("region");
                                    Entity entityObject = new Entity(idEntity,codeEntity,labelEntity,abregeEntity,regionEntity);
                                    entities.add(entityObject);
                                }



                                Log.e("ENTITY",entities.toString());
                                Log.e("sepratro","$$$$$$$$$$$$$$$$$$$$$$$");
                                Log.e("LOCAL",locals.toString());

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
}
