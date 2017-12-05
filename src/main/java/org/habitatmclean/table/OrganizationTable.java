package org.habitatmclean.table;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Organization;
import org.habitatmclean.entity.Person;
import org.habitatmclean.entity.RelationType;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class OrganizationTable extends Table {
    public OrganizationTable() {
        super(new String[]{"name", "contact"}, new OrganizationModal(), false, false); // adjust this to determine table columns
    }
    public void addRow(GenericEntity entity) {
        Organization organization = (Organization) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(organization.getName()));
        tableCells.add(new TableRow.TableCell(organization.getOrganizationContact().getFirst()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId( "" + entity.getId());
        rows.add(tr);
    }

    public void recordEdit(HttpServletRequest request, int id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        Organization organization = (Organization) HibernateAdapter.getBoByEntityName("Organization").findByPrimaryKey(new Long(id));

        organization.setName(request.getParameter("name"));

        Person contact = (Person) HibernateAdapter.getBoByEntityName("Person").findByPrimaryKey(Long.parseLong(request.getParameter("organizationContact")));
        organization.setOrganizationContact(contact);

        RelationType rt = (RelationType) HibernateAdapter.getBoByEntityName("RelationType").findByPrimaryKey(new Long(request.getParameter("actorRelationType")));
        organization.setActorRelationType(rt);

        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    public void recordAdd(HttpServletRequest request){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        Organization organization = new Organization();

        organization.setName(request.getParameter("name"));

        Person contact = (Person) HibernateAdapter.getBoByEntityName("Person").findByPrimaryKey(Long.parseLong(request.getParameter("organizationContact")));
        organization.setOrganizationContact(contact);

        RelationType rt = (RelationType) HibernateAdapter.getBoByEntityName("RelationType").findByPrimaryKey(new Long(request.getParameter("actorRelationType")));
        organization.setActorRelationType(rt);

        GenericDao saver = new HibernateAdapter();
        saver.save(organization);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    static class OrganizationModal extends Modal {
        public OrganizationModal(){
            super("Organizations");
        }

        public void buildModal(){
            forms.add(Form.builder().setType("text").setName("name").setLabel("Name").setMaxLength(120).build());
            forms.add(Form.builder().setType("select").setName("organizationContact").setLabel("Contact").setFromTable("Person","this").build());
            forms.add(Form.builder().setType("select").setName("actorRelationType").setPath(new String[] {"actorRelationType","id"}).setLabel("Relation Type").setFromTable("RelationType","relation_name").build());
        }
    }
}
