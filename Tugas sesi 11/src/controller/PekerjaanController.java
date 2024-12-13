package controller;

import model.Pekerjaan;
import model.PekerjaanMapper;
import model.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PekerjaanController {

    public boolean addPekerjaan(Pekerjaan pekerjaan) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PekerjaanMapper mapper = session.getMapper(PekerjaanMapper.class);
            mapper.insertPekerjaan(pekerjaan);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePekerjaan(Pekerjaan pekerjaan) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PekerjaanMapper mapper = session.getMapper(PekerjaanMapper.class);
            mapper.updatePekerjaan(pekerjaan);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePekerjaan(int id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PekerjaanMapper mapper = session.getMapper(PekerjaanMapper.class);
            mapper.deletePekerjaan(id);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pekerjaan> getPekerjaanByKurir(int kurirId) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            PekerjaanMapper mapper = session.getMapper(PekerjaanMapper.class);
            return mapper.getPekerjaanByKurir(kurirId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
