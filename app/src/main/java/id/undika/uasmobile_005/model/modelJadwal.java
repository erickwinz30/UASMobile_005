package id.undika.uasmobile_005.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class modelJadwal implements Parcelable {
    private String namaMK, waktuMK, hariMK, kelas, idDoc;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public modelJadwal() {
    }

    public modelJadwal(Parcel parcel) {
        this.namaMK = parcel.readString();
        this.waktuMK = parcel.readString();
        this.hariMK = parcel.readString();
        this.kelas = parcel.readString();
        this.idDoc = parcel.readString();
    }

    public static final Creator<modelJadwal> CREATOR = new Creator<modelJadwal>() {
        @Override
        public modelJadwal createFromParcel(Parcel in) {
            return new modelJadwal(in);
        }

        @Override
        public modelJadwal[] newArray(int size) {
            return new modelJadwal[size];
        }
    };

    public String getId_Doc() {
        return idDoc;
    }

    public void setId_Doc(String idDoc) {
        this.idDoc = idDoc;
    }

    public String getNamaMK() {
        return namaMK;
    }

    public void setNamaMK(String namaMK) {
        this.namaMK = namaMK;
    }

    public String getWaktuMK() {
        return waktuMK;
    }

    public void setWaktuMK(String waktuMK) {
        this.waktuMK = waktuMK;
    }

    public String getHariMK() {
        return hariMK;
    }

    public void setHariMK(String hariMK) {
        this.hariMK = hariMK;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(namaMK);
        parcel.writeString(waktuMK);
        parcel.writeString(hariMK);
        parcel.writeString(kelas);
        parcel.writeString(idDoc);
    }
}
