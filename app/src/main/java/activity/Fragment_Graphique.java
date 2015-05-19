package activity;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mehdibeggas.apibib_apresreunion.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import objets.OnChartRepasGestureListener;
import SqlLite.Repas;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Graphique.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Graphique#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Graphique extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private HashMap<Float,Integer>quantite;
    private int typeGraphique;
    private List<Repas> listeRepas;
    private Repas repasChoisi;
    private BarChart chart;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment_Graphique.
     */
    public static Fragment_Graphique newInstance(HashMap<Float,Integer>quantite, int typeGraphique) {
        Fragment_Graphique fragment = new Fragment_Graphique();
        fragment.setQuantite(quantite);
        fragment.setTypeGraphique(typeGraphique);
        return fragment;
    }

    public static Fragment_Graphique newInstance(List<Repas> listeRepas,int typeGraphique) {
        Fragment_Graphique fragment = new Fragment_Graphique();
        fragment.setListeRepas(listeRepas);
        fragment.setTypeGraphique(typeGraphique);
        return fragment;
    }

    public Fragment_Graphique() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(this.quantite!=null || listeRepas!=null){
            ArrayList<BarEntry> entries = new ArrayList<>();

            /*
            for(Map.Entry<Float,Integer> entry : quantite.entrySet()){
                entries.add(new BarEntry(entry.getKey(), entry.getValue()));
                //entries.add(new BarEntry(4f, 0));
                //entries.add(new BarEntry(8f, 1));
                //entries.add(new BarEntry(6f, 2));
                //entries.add(new BarEntry(12f, 3));
                //entries.add(new BarEntry(18f, 4));
                //entries.add(new BarEntry(9f, 5));
            }
            */


            ArrayList<String> labels = new ArrayList<String>();

            switch (typeGraphique){
                // Graphique journée : affiche les quantités bues par tranche de x heures
                // (en fonction du délai choisi par l'utilisateur)
                case 1:
                    /*
                    labels.add("00h");
                    for (int heure = 1; heure<24; heure++){
                        labels.add(Integer.toString(heure) + "h");
                    }
                    */
                    int i = 0;
                    Calendar dateDuJour = Calendar.getInstance();

                    for(Repas repas : listeRepas){
                        dateDuJour.setTime(repas.getDate_heure());
                        entries.add(new BarEntry(repas.getQuantite(),i));
                        labels.add(Integer.toString(dateDuJour.get(Calendar.HOUR_OF_DAY)));
                        i++;
                    }

                    break;
                case 2:
                    labels.add("Lundi");
                    labels.add("Mardi");
                    labels.add("Mercredi");
                    labels.add("Jeudi");
                    labels.add("Vendredi");
                    labels.add("Samedi");
                    labels.add("Dimanche");

                    break;
                case 3:
                    labels.add("Janvier");
                    labels.add("Février");
                    labels.add("Mars");
                    labels.add("Avril");
                    labels.add("Mai");
                    labels.add("Juin");
                    labels.add("Juillet");
                    labels.add("Août");
                    labels.add("Septembre");
                    labels.add("Octobre");
                    labels.add("Novembre");
                    labels.add("Décembre");
                    break;
            }

            BarDataSet dataset = new BarDataSet(entries, "Quantité bue");


            chart = new BarChart(this.getActivity().getApplicationContext());

            final Activity activity = this.getActivity();

            chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry entry, int i, Highlight highlight) {
                    repasChoisi = listeRepas.get(entry.getXIndex());
                    chart.setOnChartGestureListener(new OnChartRepasGestureListener(activity,repasChoisi));
                }

                @Override
                public void onNothingSelected() {

                }
            });

            BarData data = new BarData(labels, dataset);
            chart.setData(data);
            chart.setScaleXEnabled(false);
            chart.setScaleYEnabled(false);
            chart.animateXY(1000,1000);
        }
        else{
            System.out.println("Aucune quantité dispo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_graphique, container, false);

        // Ajoute le graphique à la vue
        LinearLayout parent = (LinearLayout) v.findViewById(R.id.graphique);
        parent.addView(chart);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public HashMap<Float, Integer> getQuantite() {
        return quantite;
    }

    public void setQuantite(HashMap<Float, Integer> quantite) {
        this.quantite = quantite;
    }

    public void setTypeGraphique(int typeGraphique) {
        this.typeGraphique = typeGraphique;
    }

    public List<Repas> getListeRepas() {
        return listeRepas;
    }

    public void setListeRepas(List<Repas> listeRepas) {
        this.listeRepas = listeRepas;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
