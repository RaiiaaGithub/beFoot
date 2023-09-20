package com.example.befoot.modules.apiClient.services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.befoot.modules.apiClient.interfaces.ApiResource;
import com.example.befoot.modules.apiClient.interfaces.Deserializer;
import com.example.befoot.modules.apiClient.interfaces.Serializer;
import com.example.befoot.modules.shared.interfaces.DataServices;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiService implements DataServices {

    private final RequestQueue requestQueue;

    public ApiService(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public <T extends Deserializer> T get(T objResponse, String url) {
        final int httpMethod = Request.Method.GET;
        final String apiUrl = url;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(httpMethod, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    objResponse.deserialize(response);
                    printRequest(httpMethod, apiUrl, response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
        return objResponse;
    }

    public <T extends Deserializer> T get(T objResponse, int id, String url) {
        final int httpMethod = Request.Method.GET;
        final String apiUrl = url + "/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(httpMethod, apiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    objResponse.deserialize(response);
                    printRequest(httpMethod, apiUrl, response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
        return objResponse;
    }

    public <T extends Serializer> void post(@NonNull T objRequest, String url) {
        final int httpMethod = Request.Method.POST;
        final String apiUrl = url;

        JSONObject jsonRequest;
        try {
            jsonRequest = objRequest.serialize();
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(httpMethod, url, jsonRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                printRequest(httpMethod, apiUrl, response, jsonRequest);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public <T extends ApiResource> T put(@NonNull T objRequest, String url) {
        final int httpMethod = Request.Method.PUT;
        final String apiUrl = url + "/" + objRequest.getId();

        JSONObject jsonRequest;
        try {
            jsonRequest = objRequest.serialize();
        } catch (JSONException e) {
            e.printStackTrace();
            return objRequest;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(httpMethod, apiUrl, jsonRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                printRequest(httpMethod, apiUrl, response, jsonRequest);
                try {
                    objRequest.deserialize(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
        return objRequest;
    }

    public <T extends ApiResource> T put(@NonNull T objRequest, int id, String url) {
        final int httpMethod = Request.Method.PUT;
        final String apiUrl = url + "/" + id;

        JSONObject jsonRequest;
        try {
            jsonRequest = objRequest.serialize();
        } catch (JSONException e) {
            e.printStackTrace();
            return objRequest;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(httpMethod, apiUrl, jsonRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                printRequest(httpMethod, apiUrl, response, jsonRequest);
                try {
                    objRequest.deserialize(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
        return objRequest;
    }

    public <T extends ApiResource> void delete(@NonNull T objRequest, String url) {
        final int httpMethod = Request.Method.DELETE;
        final String apiUrl = url + "/" + objRequest.getId();

        JSONObject jsonRequest;
        try {
            jsonRequest = objRequest.serialize();
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(httpMethod, apiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                printRequest(httpMethod, apiUrl, response, jsonRequest);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public <T extends Serializer> void delete(int id, String url) {
        final int httpMethod = Request.Method.DELETE;
        final String apiUrl = url + "/" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(httpMethod, url + "/" + id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                printRequest(httpMethod, url + "/" + id, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private static void printRequest(int httpMethod, String url, JSONObject response) {
        String method = getMethodString(httpMethod);
        try {
            Log.i("HTTP REQUEST ----------------",
                    "Method: " + method +
                            "URL: " + url +
                            "-----------------------------------" +
                            "Response: " + response.toString(4)
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void printRequest(int httpMethod, String url, JSONObject response, JSONObject request) {
        String method = getMethodString(httpMethod);
        try {
            Log.i("HTTP REQUEST ----------------",
                    "Method: " + method +
                            "URL: " + url +
                            "Body: " + request.toString(4) +
                            "-----------------------------------" +
                            "Response: " + response.toString(4)
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String getMethodString(int httpMethod) {
        String method;
        switch (httpMethod) {
            case 0:
                method = "GET";
                break;
            case 1:
                method = "POST";
                break;
            case 2:
                method = "PUT";
                break;
            case 3:
                method = "DELETE";
                break;
            default:
                method = "Error";
                break;
        }
        return method;
    }
}
