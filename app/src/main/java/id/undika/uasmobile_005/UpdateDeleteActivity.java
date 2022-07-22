package id.undika.uasmobile_005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import id.undika.uasmobile_005.model.modelJadwal;

public class UpdateDeleteActivity extends AppCompatActivity {
    public static String EXTRA_MATKUL = "extra_matkul";
    EditText namaMK, waktuMK;
    Spinner kelasMK, hariMK;
    Button btnUpdate, btnDelete;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        namaMK = findViewById(R.id.txtEditNamaMK);
        waktuMK = findViewById(R.id.txtEditWaktu);
        kelasMK = findViewById(R.id.spEditKelas);
        hariMK = findViewById(R.id.spEditHari);

        modelJadwal jm = getIntent().getParcelableExtra(EXTRA_MATKUL);

        namaMK.setText(jm.getNamaMK());
        waktuMK.setText(jm.getWaktuMK());

        int pos = 0;
        ArrayAdapter<CharSequence> sp_adapter = ArrayAdapter.createFromResource(this,R.array.arr_kelas, android.R.layout.simple_spinner_item);
        sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kelasMK.setAdapter(sp_adapter);
        pos = sp_adapter.getPosition(jm.getKelas());
        kelasMK.setSelection(pos);

        sp_adapter = ArrayAdapter.createFromResource(this,R.array.arr_hari, android.R.layout.simple_spinner_item);
        sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hariMK.setAdapter(sp_adapter);
        pos = sp_adapter.getPosition(jm.getHariMK());
        hariMK.setSelection(pos);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(view -> {
            db.collection("jadwal").document(jm.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Error", "Failure" + e);
                }
            });
            Toast.makeText(this, "ID" + jm.getId() + "Deleted", Toast.LENGTH_SHORT).show();
        });

        btnUpdate.setOnClickListener(view -> {
            DocumentReference documentReference = db.collection("jadwal").document(jm.getId());
            documentReference.update("namaMK", namaMK.getText().toString());
            documentReference.update("waktuMK", waktuMK.getText().toString());
            documentReference.update("hariMK", hariMK.getSelectedItem().toString());
            documentReference.update("kelasMK", kelasMK.getSelectedItem().toString());

            Toast.makeText(this, "ID : " + jm.getId()+ " Updated", Toast.LENGTH_SHORT).show();
        });
    }
}