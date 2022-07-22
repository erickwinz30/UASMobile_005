package id.undika.uasmobile_005.model;

public class modelMatakuliah {
    private String id, namaMK, jamMK, hariMK, kelas;

    public modelMatakuliah(String id, String namaMK, String jamMK, String hariMK, String kelas) {
        this.id = id;
        this.namaMK = namaMK;
        this.jamMK = jamMK;
        this.hariMK = hariMK;
        this.kelas = kelas;
    }

    public modelMatakuliah() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaMK() {
        return namaMK;
    }

    public void setNamaMK(String namaMK) {
        this.namaMK = namaMK;
    }

    public String getJamMK() {
        return jamMK;
    }

    public void setJamMK(String jamMK) {
        this.jamMK = jamMK;
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
}
