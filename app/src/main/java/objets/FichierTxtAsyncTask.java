package objets;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.mehdibeggas.apibib_apresreunion.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinbettencourt on 03/04/2015.
 */
public class FichierTxtAsyncTask extends AsyncTask<String, String,  List<String>> {

    public Activity activity;
    public static final String LOG_TAG = "HttpGet";

    public FichierTxtAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected List<String> doInBackground(String... params) {
        List<String>  stringBuffer = new ArrayList<String>();
        BufferedReader bufferedReader = null;

        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet();
            URI uri = null;
            uri = new URI(params[0]);
            httpGet.setURI(uri);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = bufferedReader.readLine();

            while (readLine != null) {
                stringBuffer.add(readLine);
                readLine = bufferedReader.readLine();
            }
        }
        catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage());

        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }
        }
        return stringBuffer;

    }

    @Override
    protected void onPostExecute( List<String> s) {
        super.onPostExecute(s);

        double quantiteInitiale = Double.parseDouble(s.get(0).replace("g",""));
        double quantiteRestante = Double.parseDouble(s.get(1).replace("g",""));

        if(quantiteRestante<0){
            quantiteRestante = 0;
        }

        TextView textView = (TextView) activity.findViewById(R.id.quInitiale);
        textView.setText("Quantité initiale : " + quantiteInitiale + " mL");

        textView = (TextView) activity.findViewById(R.id.quBue);
        textView.setText("Quantité bue : " + (quantiteInitiale - quantiteRestante) + " mL");

        int minutes = (Integer.parseInt(s.get(2))/1000)/60;
        int secondes = (Integer.parseInt(s.get(2))/1000) % 60;

        textView = (TextView) activity.findViewById(R.id.duree);
        textView.setText("Durée : " + minutes + " minutes " + secondes + " secondes");

        Log.e(this.getClass().getPackage().toString(), "Valeur de s : " + s);

    }
}
