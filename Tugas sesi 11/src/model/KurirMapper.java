package model;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KurirMapper {

    @Insert("INSERT INTO kurir (nama, email, password, no_telepon, alamat) VALUES (#{nama}, #{email}, #{password}, #{noTelepon}, #{alamat})")
    void insertKurir(Kurir kurir);

    @Select("SELECT * FROM kurir WHERE email = #{email} AND password = #{password}")
    Kurir loginKurir(@Param("email") String email, @Param("password") String password);

    @Select("SELECT * FROM kurir WHERE id = #{id}")
    Kurir getKurirById(int id);

    @Select("SELECT * FROM kurir")
    List<Kurir> getAllKurir();

    @Update("UPDATE kurir SET nama = #{nama}, email = #{email}, no_telepon = #{noTelepon}, alamat = #{alamat} WHERE id = #{id}")
    void updateKurir(Kurir kurir);

    @Delete("DELETE FROM kurir WHERE id = #{id}")
    void deleteKurir(int id);
}
