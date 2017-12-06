package org.habitatmclean.table;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.*;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PersonTable extends Table {
    public PersonTable() {
        super(new String[]{"id", "first", "middle", "last", "home phone", "email"}, new PersonModal(), false, false); // adjust this to determine table columns
    }
    public void addRow(GenericEntity entity) {
        Person person = (Person) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(""+person.getId()));
        tableCells.add(new TableRow.TableCell(person.getFirst()));
        tableCells.add(new TableRow.TableCell(person.getMiddle()));
        tableCells.add(new TableRow.TableCell(person.getLast()));
        tableCells.add(new TableRow.TableCell(person.getHome_phone()));
        tableCells.add(new TableRow.TableCell(person.getEmail()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }
    public void recordEdit(HttpServletRequest request, int id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        GenericDao personDao = HibernateAdapter.getBoByEntityName("Person");
        Person person = (Person) personDao.findByPrimaryKey(new Long(id));

        person.setFirst(request.getParameter("first"));
        person.setMiddle(request.getParameter("middle"));
        person.setLast(request.getParameter("last"));
        person.setHome_phone(request.getParameter("home_phone"));
        person.setEmail(request.getParameter("email"));

        Address newAddress = person.getActorAddress();
        newAddress.setApartment_no(request.getParameter("apartment_no"));
        newAddress.setStreet(request.getParameter("street"));
        newAddress.setCity(request.getParameter("city"));
        newAddress.setState(request.getParameter("state"));
        newAddress.setZipcode(request.getParameter("zipcode"));

        GenericDao dao = HibernateAdapter.getBoByEntityName("RelationType");
        RelationType rt = (RelationType) dao.findByPrimaryKey(new Long(request.getParameter("actorRelationType")));
        person.setActorRelationType(rt);

        dao = HibernateAdapter.getBoByEntityName("Family");
        Family family = (Family) dao.findByPrimaryKey(new Long(request.getParameter("family")));
        person.setFamily(family);

        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    public void recordAdd(HttpServletRequest request){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        Person newPerson = new Person();

        //make address
        Address newAddress = new Address();
        newAddress.setApartment_no(request.getParameter("apartment_no"));
        newAddress.setStreet(request.getParameter("street"));
        newAddress.setCity(request.getParameter("city"));
        newAddress.setState(request.getParameter("state"));
        newAddress.setZipcode(request.getParameter("zipcode"));
        newPerson.setActorAddress(newAddress);

        newPerson.setFirst(request.getParameter("first"));
        newPerson.setMiddle(request.getParameter("middle"));
        newPerson.setLast(request.getParameter("last"));
        newPerson.setHome_phone(request.getParameter("home_phone"));
        newPerson.setEmail(request.getParameter("email"));

        GenericDao dao = HibernateAdapter.getBoByEntityName("RelationType");
        RelationType rt = (RelationType) dao.findByPrimaryKey(new Long(request.getParameter("actorRelationType")));
        newPerson.setActorRelationType(rt);

        dao = HibernateAdapter.getBoByEntityName("Family");
        Family family = (Family) dao.findByPrimaryKey(new Long(request.getParameter("family")));
        newPerson.setFamily(family);

        dao = new HibernateAdapter();
        dao.save(newPerson);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    static class PersonModal extends Modal {

        public PersonModal(){
            super("Person");
        }
        public void buildModal(){
            //name and type are the required fields. name will be used for grabbing the value. Type will be used to determine the type
            //text is currently the only supported type. more will be supported soon!
            //maxLength is important for fields that could be complained about in the database.
            //make sure to name the name EXACTLY
            forms.add(Form.builder().setType("text").setName("first").setLabel("First Name").setMaxLength(20).build());
            forms.add(Form.builder().setType("text").setName("middle").setLabel("Middle Name").setMaxLength(20).setRequired(false).build());
            forms.add(Form.builder().setType("text").setName("last").setLabel("Last Name").setMaxLength(20).build());
            forms.add(Form.builder().setType("text").setName("apartment_no").setLabel("Apartment #").setParent("actorAddress").setRequired(false).build());
            forms.add(Form.builder().setType("text").setName("street").setLabel("Address").setMaxLength(120).setParent("actorAddress").build());
            forms.add(Form.builder().setType("text").setName("city").setLabel("City").setMaxLength(120).setParent("actorAddress").build());
            forms.add(Form.builder().setType("text").setName("state").setLabel("State").setMaxLength(20).setParent("actorAddress").build());
            forms.add(Form.builder().setType("text").setName("zipcode").setLabel("Zip").setMaxLength(9).setParent("actorAddress").build());
            forms.add(Form.builder().setType("tel").setName("home_phone").setLabel("Home Phone").setMaxLength(20).build());
            forms.add(Form.builder().setType("tel").setName("cell_phone").setLabel("Cell Phone").setMaxLength(20).setRequired(false).build());
            forms.add(Form.builder().setType("tel").setName("work_phone").setLabel("Work Phone").setMaxLength(20).setRequired(false).build());
            forms.add(Form.builder().setType("email").setName("email").setLabel("Email").setMaxLength(120).build());
            forms.add(Form.builder().setType("select").setName("actorRelationType").setLabel("Relation Type").setFromTable("RelationType","relation_name").build());
            forms.add(Form.builder().setType("select").setName("family").setLabel("Family").setFromTable("Family","this").build());
        }
    }
}



