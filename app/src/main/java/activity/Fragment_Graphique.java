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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Graphique.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Graphique#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Graphique extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private HashMap<Float,Integer>quantite;
    private BarChart chart;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment_Graphique.
     */
    public static Fragment_Graphique newInstance(HashMap<Float,Integer>quantite) {
        Fragment_Graphique fragment = new Fragment_Graphique();
        fragment.setQuantite(quantite);
        return fragment;
    }

    public Fragment_Graphique() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(this.quantite!=null){
            ArrayList<BarEntry> entries = new ArrayList<>();

            for(Map.Entry<Float,Integer> entry:quantite.entrySet()){
                entries.add(new BarEntry(entry.getKey(), entry.getValue()));
                //entries.add(new BarEntry(4f, 0));
                //entries.add(new BarEntry(8f, 1));
                //entries.add(new BarEntry(6f, 2));
                //entries.add(new BarEntry(12f, 3));
                //entries.add(new BarEntry(18f, 4));
                //entries.add(new BarEntry(9f, 5));
            }

            BarDataSet dataset = new BarDataSet(entries, "# of Calls");

            ArrayList<String> labels = new ArrayList<String>();
            labels.add("January");
            labels.add("February");
            labels.add("March");
            labels.add("April");
            labels.add("May");
            labels.add("June");

            chart = new BarChart(this.getActivity().getApplicationContext());
            //setContentView(chart);

            BarData data = new BarData(labels, dataset);
            chart.setData(data);
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
