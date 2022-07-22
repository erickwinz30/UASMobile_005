package id.undika.uasmobile_005;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInputMk#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInputMk extends Fragment {
    ArrayList<String> listId = new ArrayList<>();
    ArrayList<String> listNama = new ArrayList<>();
    ArrayList<String> listWaktu = new ArrayList<>();
    ArrayList<String> listHari = new ArrayList<>();
    ArrayList<String> listKelas = new ArrayList<>();

    private ArrayList listJadwal = new ArrayList<>();

    FloatingActionButton fabAdd;
    RecyclerView rvDaftar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public FragmentInputMk() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInputMk.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInputMk newInstance(String param1, String param2) {
        FragmentInputMk fragment = new FragmentInputMk();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_mk_rv, container, false);
        rvDaftar = view.findViewById(R.id.rv_mk);
        return inflater.inflate(R.layout.fragment_input_mk, container, false);
    }
}