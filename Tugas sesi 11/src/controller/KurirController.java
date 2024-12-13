package controller;

import model.Kurir;
import model.KurirMapper;
import model.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class KurirController {

    public boolean registerKurir(String nama, String email, String password, String noTelepon, String alamat) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            KurirMapper mapper = session.getMapper(KurirMapper.class);
            Kurir kurir = new Kurir(0, nama, email, password, noTelepon, alamat);
            mapper.insertKurir(kurir);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Kurir loginKurir(String email, String password) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            KurirMapper mapper = session.getMapper(KurirMapper.class);
            return mapper.loginKurir(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateKurir(Kurir kurir) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            KurirMapper mapper = session.getMapper(KurirMapper.class);
            mapper.updateKurir(kurir);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteKurir(int id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            KurirMapper mapper = session.getMapper(KurirMapper.class);
            mapper.deleteKurir(id);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
