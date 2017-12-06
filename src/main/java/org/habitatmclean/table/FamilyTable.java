package org.habitatmclean.table;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.Class;
import org.habitatmclean.entity.Family;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Milestone;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FamilyTable extends Table {
    public FamilyTable() {
        super(new String[]{"class", "milestone", "equity hours", "income"}, new FamilyModal(), true, true); // adjust this to determine table columns
    }
    public void addRow(GenericEntity entity) {
        Family family = (Family) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        if(family.getFamilyMilestone() != null)
            tableCells.add(new TableRow.TableCell("" + family.getFamilyClassType().getClass_name()));
        else
            tableCells.add(new TableRow.TableCell(""));
        if(family.getFamilyMilestone() != null)
            tableCells.add(new TableRow.TableCell("" + family.getFamilyMilestone().getMilestone()));
        else
            tableCells.add(new TableRow.TableCell(""));
        tableCells.add(new TableRow.TableCell("" + family.getEquity_hrs()));
        tableCells.add(new TableRow.TableCell("$" + String.format("%.2f", family.getIncome())));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    public void recordEdit(HttpServletRequest request, int id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        Family family = (Family) HibernateAdapter.getBoByEntityName("Family").findByPrimaryKey(new Long(id));

        try {
            family.setEquity_hrs(Double.parseDouble(request.getParameter("equity_hrs").trim()));
        } catch (NumberFormatException | NullPointerException e) {
            family.setEquity_hrs(0);
        }
        try {
            family.setIncome(Integer.parseInt(request.getParameter("income").trim()));
        } catch (NumberFormatException | NullPointerException e) {
            family.setIncome(0);
        }

        GenericDao dao = HibernateAdapter.getBoByEntityName("Class");
        Class clazz = (Class) dao.findByPrimaryKey(new Long(request.getParameter("familyClassType")));
        family.setFamilyClassType(clazz);

        dao = HibernateAdapter.getBoByEntityName("Milestone");
        Milestone milestone = (Milestone) dao.findByPrimaryKey(new Long(request.getParameter("familyMilestone")));
        family.setFamilyMilestone(milestone);

        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    public void recordAdd(HttpServletRequest request){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        Family family = new Family();

        try {
            family.setEquity_hrs(Double.parseDouble(request.getParameter("equity_hrs").trim()));
        } catch (NumberFormatException | NullPointerException e) {
            family.setEquity_hrs(0);
        }
        try {
            family.setIncome(Integer.parseInt(request.getParameter("income").trim()));
        } catch (NumberFormatException | NullPointerException e) {
            family.setIncome(0);
        }
        GenericDao dao;
        try {
            dao = HibernateAdapter.getBoByEntityName("Class");
            Class clazz = (Class) dao.findByPrimaryKey(new Long(request.getParameter("familyClassType")));
            family.setFamilyClassType(clazz);
        } catch (NumberFormatException e) {
            family.setFamilyClassType(null);
        }

        try {
            dao = HibernateAdapter.getBoByEntityName("Milestone");
            Milestone milestone = (Milestone) dao.findByPrimaryKey(new Long(request.getParameter("familyMilestone")));
            family.setFamilyMilestone(milestone);
        } catch (NumberFormatException e) {
            family.setFamilyMilestone(null);
        }


        dao = new HibernateAdapter();
        dao.save(family);
        sessionFactory.getCurrentSession().getTransaction().commit();    }

    static class FamilyModal extends Modal {
        public FamilyModal(){
            super("Family");
        }

        public void buildModal(){
            forms.add(Form.builder().setType("text").setName("equity_hrs").setLabel("Equity Hours").build());
            forms.add(Form.builder().setType("text").setName("income").setLabel("Income").build());
            forms.add(Form.builder().setType("select").setName("familyMilestone").setLabel("Milestone").setFromTable("Milestone","milestone").build());
            forms.add(Form.builder().setType("select").setName("familyClassType").setLabel("Class").setFromTable("Class","class_name").build());
        }
    }

}
