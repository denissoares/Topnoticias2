package com.example.denis.topnoticiasnovo;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by denis on 04/06/2018.
 */

public class Conexao {

    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public List<Noticia> enviarRequisicao() throws Exception {
        //https://api.myjson.com/bins/3kpyw
        //http://api.flickr.com/services/feeds/photos_public.gne?tags=beatles&format=json&jsoncallback=?
        //String url = "https://api.myjson.com/bins/3gkfk";
        String url = "https://raw.githubusercontent.com/filiperdt/TopNoticias/master/top.json";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //String url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=bcb0e25a2c47424d8551ff4b32879f81";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //JSONObject objeto = new JSONObject(response.toString());
        //JSONArray artigos = objeto.getJSONArray("articles");
        List<Noticia> encontradas = encontrarTodasNoticias(new JSONArray(response.toString()));

        return encontradas;
    }

    public List<Noticia> encontrarTodasNoticias(JSONArray enc) {
        List<Noticia> encontradas = new LinkedList<Noticia>();
        try {
            for (int i = 0; i < enc.length(); i++) {
                JSONObject obj = enc.getJSONObject(i);
                //encontradas.add(new Noticia(obj.getString("publishedAt"),obj.getString("author"),obj.getString("title"),obj.getString("url")));
                encontradas.add(new Noticia(obj.getString("dataPost"),obj.getString("autor"),obj.getString("titulo"),obj.getString("conteudo")));
            }
        } catch (JSONException e) {
            // handle exception
        }
        return encontradas;
    }
}
