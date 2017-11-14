package org.habitatmclean.table;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.Address;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Person;
import org.habitatmclean.entity.RelationType;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PersonTable extends Table {
    public PersonTable() {
        super(new String[]{"first", "middle", "last", "home phone", "email"}, new PersonModal(), false); // adjust this to determine table columns
    }
    public void addRow(GenericEntity entity) {
        Person person = (Person) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
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
        Address newAddress = person.getAddress();
        newAddress.setStreet(request.getParameter("street"));
        newAddress.setCity(request.getParameter("city"));
        newAddress.setState(request.getParameter("state"));
        newAddress.setZipcode(request.getParameter("zipcode"));

        person.setFirst(request.getParameter("first"));
        person.setMiddle(request.getParameter("middle"));
        person.setLast(request.getParameter("last"));
        person.setHome_phone(request.getParameter("home_phone"));
        person.setEmail(request.getParameter("email"));

        GenericDao relationDao = HibernateAdapter.getBoByEntityName("RelationType");
        RelationType rt = (RelationType) relationDao.findByPrimaryKey(new Long(request.getParameter("relationType")));
        person.setRelationType(rt);

        personDao.save(person);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    public void recordAdd(HttpServletRequest request){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        //make address
        Address newAddress = new Address();
        newAddress.setStreet(request.getParameter("street"));
        newAddress.setCity(request.getParameter("city"));
        newAddress.setState(request.getParameter("state"));
        newAddress.setZipcode(request.getParameter("zipcode"));

        Person newPerson = new Person();
        newPerson.setFirst(request.getParameter("first"));
        newPerson.setMiddle(request.getParameter("middle"));
        newPerson.setLast(request.getParameter("last"));
        newPerson.setHome_phone(request.getParameter("home_phone"));
        newPerson.setEmail(request.getParameter("email"));

        GenericDao dao = HibernateAdapter.getBoByEntityName("RelationType");
        RelationType rt = (RelationType) dao.findByPrimaryKey(new Long(request.getParameter("relationType"))); //TODO

        newPerson.setRelationType(rt);
        newPerson.setAddress(newAddress);

        dao = new HibernateAdapter();
        dao.save(newPerson);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    static class PersonModal extends Modal{

        public PersonModal(){
            super("Person");
        }
        public void buildModal(){
            //name and type are the required fields. name will be used for grabbing the value. Type will be used to determine the type
            //text is currently the only supported type. more will be supported soon!
            //maxLength is important for fields that could be complained about in the database.
            //make sure to name the name EXACTLY
            forms.add(Form.builder().setType("text").setName("first").setLabel("First Name").build());
            forms.add(Form.builder().setType("text").setName("middle").setLabel("Middle Name").setRequired(false).build());
            forms.add(Form.builder().setType("text").setName("last").setLabel("Last Name").build());
            forms.add(Form.builder().setType("text").setName("street").setLabel("Address Line 1").setMaxLength(120).build());
            forms.add(Form.builder().setType("text").setName("city").setLabel("City").setMaxLength(120).build());
            forms.add(Form.builder().setType("text").setName("state").setLabel("State").setMaxLength(20).build());
            forms.add(Form.builder().setType("text").setName("zipcode").setLabel("Zip").setMaxLength(9).build());
            forms.add(Form.builder().setType("tel").setName("home_phone").setLabel("Phone Number").setMaxLength(20).build());
            forms.add(Form.builder().setType("email").setName("email").setLabel("Email").setMaxLength(120).build());
            forms.add(Form.builder().setType("select").setName("relationType").setLabel("Relation Type")
                    .addOption("Tennant","1").addOption("Volunteer","5").build());
        }
    }
}



