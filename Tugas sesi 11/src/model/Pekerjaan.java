package model;

public class Pekerjaan {

    private int id;
    private int kurirId;
    private String alamatPenjemputan;
    private String alamatPengantaran;
    private String beratBarang;
    private String jenisBarang;

    public Pekerjaan(int kurirId, String alamatPenjemputan, String alamatPengantaran, String beratBarang, String jenisBarang) {
        this.kurirId = kurirId;
        this.alamatPenjemputan = alamatPenjemputan;
        this.alamatPengantaran = alamatPengantaran;
        this.beratBarang = beratBarang;
        this.jenisBarang = jenisBarang;
    }

    public Pekerjaan(int id, int kurirId, String alamatPenjemputan, String alamatPengantaran, String beratBarang, String jenisBarang) {
        this.id = id;
        this.kurirId = kurirId;
        this.alamatPenjemputan = alamatPenjemputan;
        this.alamatPengantaran = alamatPengantaran;
        this.beratBarang = beratBarang;
        this.jenisBarang = jenisBarang;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKurirId() {
        return kurirId;
    }

    public void setKurirId(int kurirId) {
        this.kurirId = kurirId;
    }

    public String getAlamatPenjemputan() {
        return alamatPenjemputan;
    }

    public void setAlamatPenjemputan(String alamatPenjemputan) {
        this.alamatPenjemputan = alamatPenjemputan;
    }

    public String getAlamatPengantaran() {
        return alamatPengantaran;
    }

    public void setAlamatPengantaran(String alamatPengantaran) {
        this.alamatPengantaran = alamatPengantaran;
    }

    public String getBeratBarang() {
        return beratBarang;
    }

    public void setBeratBarang(String beratBarang) {
        this.beratBarang = beratBarang;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }
}
