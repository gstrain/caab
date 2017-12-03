package org.habitatmclean.table;
import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.*;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Log Table class, extends Table functionality
 */

public class LogTable extends Table {
    public LogTable() {
        super(new String[]{"Reason", "Date", "Notes", "Status"}, new LogModal(), false, false);
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
        /*TODO get the ids for contact and property from the servlet */

        tableCells.add(new TableRow.TableCell(log.getReason()));
        tableCells.add(new TableRow.TableCell(log.getDate().toString()));
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

            Log log = new Log();
            log.setReason(request.getParameter("reason"));
            log.setNotes(request.getParameter("notes"));
            log.setStatus(request.getParameter("status"));

            String check = request.getParameter("pname");
            String fk = request.getParameter("fk");
            System.out.println(check);
            System.out.println(fk);


                switch (check) {
                    case "property":
                        GenericDao dao = HibernateAdapter.getBoByEntityName("Property");
                        Property property = (Property) dao.findByPrimaryKey(new Long(request.getParameter("fk")));
                        log.setLogProperty(property);
                        log.setLogFamily(null);
                        log.setLogHouse(null);
                        break;
                    case "house":
                        GenericDao hD = HibernateAdapter.getBoByEntityName("House");
                        House house = (House) hD.findByPrimaryKey(new Long(request.getParameter("fk")));
                        log.setLogHouse(house);
                        log.setLogFamily(null);
                        log.setLogProperty(null);
                        break;
                    case "family":
                        GenericDao fD = HibernateAdapter.getBoByEntityName("Family");
                        Family family = (Family) fD.findByPrimaryKey(new Long(request.getParameter("fk")));
                        log.setLogFamily(family);
                        log.setLogHouse(null);
                        log.setLogProperty(null);
                        break;
                    default:
                        System.out.println("Nothing passed");
                }


//            System.out.println(log.toString());
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