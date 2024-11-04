package dao;

import db.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Biodata;

public class BiodataDao {
    private Connection connection;

    public BiodataDao() {
        connection = MySqlConnection.getConnection();
    }

    public List<Biodata> getAllBiodata() {
        List<Biodata> biodataList = new ArrayList<>();
        String sql = "SELECT * FROM biodata"; 

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Biodata biodata = new Biodata();
                biodata.setId(rs.getInt("id")); 
                biodata.setNama(rs.getString("nama"));
                biodata.setAlamat(rs.getString("alamat"));
                biodata.setTelepon(rs.getString("telepon"));
                biodata.setJenisKelamin(rs.getString("jenis_kelamin"));
                biodata.setHobi(rs.getString("hobi"));
                biodata.setPendidikan(rs.getString("pendidikan"));
                biodata.setKotaAsal(rs.getString("kota_asal"));
                
                biodataList.add(biodata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return biodataList;
    }

    public void insertBiodata(Biodata biodata) {
        String sql = "INSERT INTO biodata (nama, alamat, telepon, jenis_kelamin, hobi, pendidikan, kota_asal) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, biodata.getNama());
            pstmt.setString(2, biodata.getAlamat());
            pstmt.setString(3, biodata.getTelepon());
            pstmt.setString(4, biodata.getJenisKelamin());
            pstmt.setString(5, biodata.getHobi());
            pstmt.setString(6, biodata.getPendidikan());
            pstmt.setString(7, biodata.getKotaAsal());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBiodata(int id) {
        String sql = "DELETE FROM biodata WHERE id = ?";
        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting biodata: " + e.getMessage());
        }
    }
    
}
