package org.habitatmclean.table;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Person;
import org.habitatmclean.entity.Zone;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ZoneTable extends Table {
    public ZoneTable() {
        super(new String[]{"zone_info", "zone_desc"}, new ZoneModal(), false); // adjust this to determine table columns
    }
    public void addRow(GenericEntity entity) {
        Zone zone = (Zone) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(zone.getZone_info()));
        tableCells.add(new TableRow.TableCell(zone.getZone_desc()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    public void recordEdit(HttpServletRequest request, int id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        GenericDao personDao = HibernateAdapter.getBoByEntityName("Zone");
        Zone zone = (Zone) personDao.findByPrimaryKey(new Long(id));

        zone.setZone_info(request.getParameter("zone_info"));
        zone.setZone_desc(request.getParameter("zone_desc"));

        GenericDao dao = new HibernateAdapter();
        dao.save(zone);
        sessionFactory.getCurrentSession().getTransaction().commit();

    }
    public void recordAdd(HttpServletRequest request){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        Zone newZone = new Zone();

        newZone.setZone_info(request.getParameter("zone_info"));
        newZone.setZone_desc(request.getParameter("zone_desc"));

        GenericDao dao = new HibernateAdapter();
        dao.save(newZone);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    static class ZoneModal extends Modal {
        public ZoneModal(){
            super("Zone");
        }

        public void buildModal(){
            forms.add(Form.builder().setType("text").setName("zone_info").setLabel("Name").build());
            forms.add(Form.builder().setType("text").setName("zone_desc").setLabel("Description").setRequired(false).build());
        }
    }
}
