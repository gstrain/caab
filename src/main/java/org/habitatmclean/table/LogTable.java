package org.habitatmclean.table;


import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.*;
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
        super(new String[]{"Date", "Reason", "Notes", "Status", "contact"}, new LogModal(), false, false);
    }

    /**
     * Extends Table addRow. Builds table rows from the log object
     * @param entity the entity to add data from
     */

    @Override
    public void addRow(GenericEntity entity) {
        Log log = (Log) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(new SimpleDateFormat("hh:mm aa '-' MM/dd/yyyy").format(log.getDate())));
        tableCells.add(new TableRow.TableCell(log.getReason()));
        tableCells.add(new TableRow.TableCell(log.getNotes()));
        tableCells.add(new TableRow.TableCell(log.getStatus()));
        tableCells.add(new TableRow.TableCell(log.getLogContact().toString()));

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
        GenericDao dao = HibernateAdapter.getBoByEntityName("Actor");
        Actor contact = (Actor) dao.findByPrimaryKey(new Long(request.getParameter("logContact")));
        switch (parentName) {
            case "property":
                GenericDao propDao = HibernateAdapter.getBoByEntityName("Property");
                Property property = (Property) propDao.findByPrimaryKey(Long.parseLong(parentId));
                log.setLogProperty(property);

                log.setLogContact(contact);
                log.setLogHouse(null);
                log.setLogFamily(null);
                break;
            case "house":
                GenericDao houseDao = HibernateAdapter.getBoByEntityName("House");
                House house = (House) houseDao.findByPrimaryKey(Long.parseLong(parentId));
                log.setLogHouse(house);
                log.setLogFamily(null);
                log.setLogProperty(null);
                log.setLogContact(contact);
                break;
            case "family":
                GenericDao familyDao = HibernateAdapter.getBoByEntityName("Family");
                Family family = (Family) familyDao.findByPrimaryKey(Long.parseLong(parentId));
                log.setLogHouse(null);
                log.setLogFamily(family);
                log.setLogProperty(null);
                log.setLogContact(contact);
                break;
            default:
                // other lunchables
                break;
        }

        dao = new HibernateAdapter();
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
            forms.add(Form.builder().setType("select").setName("logContact").setLabel("Contact").setFromTable("Actor", "this").build());
        }
    }
}