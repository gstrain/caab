package org.habitatmclean.table;


import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.House;
import org.habitatmclean.entity.Log;
import org.habitatmclean.entity.Property;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Log Table class, extends Table functionality
 */

public class LogTable extends Table {

    public LogTable() {
        super(new String[]{"Id","Reason", "Date", "Notes", "Status"}, new LogModal(), false, false);
    }

    /**
     * Extends Table addRow. Builds table rows from the log object
     * @param entity the entity to add data from
     */

    @Override
    public void addRow(GenericEntity entity) {
        Log log = (Log) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();

        tableCells.add(new TableRow.TableCell("" + log.getId()));
        tableCells.add(new TableRow.TableCell(log.getReason()));
        tableCells.add(new TableRow.TableCell(new SimpleDateFormat("MM.dd.yyyy '-' hh:mm aa").format(log.getDate())));
        tableCells.add(new TableRow.TableCell(log.getNotes()));
        tableCells.add(new TableRow.TableCell(log.getStatus()));

        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    @Override
    public void recordEdit(HttpServletRequest request, int id) {}

    @Override
    public void recordAdd(HttpServletRequest request) {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            sessionFactory.getCurrentSession().beginTransaction();

            String parentName = request.getParameter("pName");
            String parentId = request.getParameter("pId");

            Log log = new Log();
            log.setReason(request.getParameter("reason"));
            log.setNotes(request.getParameter("notes"));
            log.setStatus(request.getParameter("status"));
            Date date = new Date();
            log.setDate(date);
            if(parentName.equals("property"))
            {
                GenericDao propDao = HibernateAdapter.getBoByEntityName("Property");
                Property property = (Property) propDao.findByPrimaryKey(Long.parseLong(parentId));
                log.setLogProperty(property);
                log.setLogContact(property.getOwner());
                log.setLogHouse(null);
                log.setLogFamily(null);
            }
            else if (parentName.equals("house"))
            {
                GenericDao houseDao = HibernateAdapter.getBoByEntityName("House");
                House house = (House) houseDao.findByPrimaryKey(Long.parseLong(parentId));
                log.setLogHouse(house);
                log.setLogFamily(null);
                log.setLogProperty(null);
                log.setLogContact(house.getHouseProperty().getOwner());
            }
            else
            {
                // FamilyDAO stuff should go here.
            }

            GenericDao dao = new HibernateAdapter();
            dao.save(log);
            sessionFactory.getCurrentSession().getTransaction().commit();
    }

    static class LogModal extends Modal {

        public LogModal(){
            super("Log");
        }

        @Override
        public void buildModal() {
            forms.add(Form.builder().setType("text").setName("reason").setLabel("Reason").setMaxLength(120).setRequired(false).build());
            forms.add(Form.builder().setType("text").setName("notes").setLabel("Notes").setMaxLength(500).build());
            forms.add(Form.builder().setType("text").setName("status").setLabel("Status").setMaxLength(50).build());
        }
    }
}