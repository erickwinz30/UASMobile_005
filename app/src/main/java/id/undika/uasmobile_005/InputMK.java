package id.undika.uasmobile_005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import id.undika.uasmobile_005.model.modelJadwal;

public class InputMK extends AppCompatActivity {
    EditText namaMK, waktuMK;
    Button submit;
    Spinner spHari, spKelas;
    private modelJadwal jm;


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseFirestore storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_mk);

        namaMK = findViewById(R.id.txtNamaMatakuliah);
        waktuMK = findViewById(R.id.txtInsertWaktu);
        spHari = (Spinner) findViewById(R.id.spinnerHari);
        spKelas = (Spinner) findViewById(R.id.spinnerKelas);
        submit = findViewById(R.id.btnSubmit);

        submit.setOnClickListener(view -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference documentReference = db.collection("jadwal").document();
            Map<String, Object> dataMK = new HashMap<>();
            dataMK.put("idMK", documentReference.getId());
            dataMK.put("namaMK", namaMK.getText().toString());
            dataMK.put("waktuMK", waktuMK.getText().toString());
            dataMK.put("hariMK", spHari.getSelectedItem().toString());
            dataMK.put("kelasMK", spKelas.getSelectedItem().toString());

            db.collection("jadwal").add(dataMK).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(InputMK.this, "Saved", Toast.LENGTH_SHORT).show();
                    clearData();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(InputMK.this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    void clearData() {
        namaMK.setText("");
        waktuMK.setText("");
        spHari.setSelection(0);
        spKelas.setSelection(0);
    }
}