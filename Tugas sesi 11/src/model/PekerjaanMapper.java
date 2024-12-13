package model;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PekerjaanMapper {

    @Insert("INSERT INTO pekerjaan (kurir_id, alamat_penjemputan, alamat_pengantaran, berat_barang, jenis_barang) "
            + "VALUES (#{kurirId}, #{alamatPenjemputan}, #{alamatPengantaran}, #{beratBarang}, #{jenisBarang})")
    void insertPekerjaan(Pekerjaan pekerjaan);

    @Update("UPDATE pekerjaan SET alamat_penjemputan = #{alamatPenjemputan}, "
            + "alamat_pengantaran = #{alamatPengantaran}, berat_barang = #{beratBarang}, "
            + "jenis_barang = #{jenisBarang} WHERE id = #{id} AND kurir_id = #{kurirId}")
    void updatePekerjaan(Pekerjaan pekerjaan);

    @Delete("DELETE FROM pekerjaan WHERE id = #{id}")
    void deletePekerjaan(int id);

    @Select("SELECT * FROM pekerjaan WHERE kurir_id = #{kurirId}")
    List<Pekerjaan> getPekerjaanByKurir(int kurirId);
}
