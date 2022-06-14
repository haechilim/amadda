package com.example.a_matta_library.manager;

import android.util.Log;

import com.example.a_matta_library.domain.Classroom;
import com.example.a_matta_library.helper.AsyncJob;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiManager {
    public static final String HOST1 = "https://amadda-django-test.herokuapp.com";
    public static final String HOST2 = "https://amadda-flask-test.herokuapp.com";
    public static List<Classroom> classrooms = new ArrayList<>();

    public static void initClassrooms(SuccessCallback callback) {
        request(String.format("%s/%s/?format=%s", HOST1, "teacher", "json"), json -> {
            try {
                JSONArray jsonArray = new JSONArray(json);

                if(jsonArray.length() == 0) callback.success(false);

                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONArray teacherNameArray = jsonObject.getJSONArray("teacher");

                    String classroom = jsonObject.getString("classroom");
                    String teacherNames[] = new String[teacherNameArray.length()];

                    for(int j = 0; j < teacherNameArray.length(); j++) {
                        teacherNames[j] = teacherNameArray.get(j).toString();
                    }

                    classrooms.add(new Classroom(classroom, teacherNames));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            callback.success(true);
        });
    }

    public static void checkLogin(String id, String password, SuccessCallback callback) {
        request(String.format("%s/isvalid?id=%s&pw=%s", HOST2, id, password), json -> {
            try {
                JSONObject jsonObject = new JSONObject(json);
                boolean isValid = jsonObject.getBoolean("isvalid");

                callback.success(isValid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public static void applicationFormat(int num, int type, int selectedIndex, String title, String object, String loud, String teacher, SuccessCallback callback) {
        String uri = String.format("%s/reserve?num=%d&type=%d&class=%d&title=%s&object=%s&loud=%s&teacher=%s", HOST2, num, type, selectedIndex, title, object, loud, teacher);
        request(uri, json -> {
            try {
                JSONObject jsonObject = new JSONObject(json);
                boolean isValid = jsonObject.getBoolean("success");

                callback.success(isValid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public static void reserveClass(int num, String id, String pw, String time, SuccessCallback callback) {
        String uri = String.format("%s/reserveclass?num=%d&id=%s&pw=%s&time=%s", HOST2, num, id, pw, time);
        request(uri, json -> {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray isValid = jsonObject.getJSONArray("success");

                Log.d("test", isValid.toString());

                for(int i = 0; i < isValid.length(); i++) {
                    if(isValid.get(i).toString() == "false") {
                        callback.success(false);
                        return;
                    }
                }

                callback.success(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public static void getMenu(int year, int month, int day, MenusCallback callback) {
        String uri = String.format("%s/menu?yy=%d&mm=%d&dd=%d", HOST2, year, month, day);
        request(uri, json -> {
            try {
                JSONObject jsonObject = new JSONObject(json);

                String breakfast = jsonObject.getString("breakfast");
                String lunch = jsonObject.getString("lunch");
                String dinner = jsonObject.getString("dinner");
                String snack = jsonObject.getString("snack");

                String menus[] = { breakfast, lunch, dinner, snack };

                callback.menus(menus);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    private static void request(String url, JsonCallback callback) {
        Log.d("test", url);

        new AsyncJob<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return request(strings[0]);
            }

            @Override
            protected void onPostExecute(String result) {
                callback.success(result);
            }
        }.execute(url);
    }

    private static String request(String uri) {
        String result = "";

        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("content-type", "application/json");
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            while(true) {
                String line = reader.readLine();
                if(line == null) break;
                result += line;
            }

            reader.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            Log.d("test", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public interface JsonCallback {
        void success(String json);
    }

    public interface SuccessCallback {
        void success(boolean success);
    }

    public interface MenusCallback {
        void menus(String[] menus);
    }
}
