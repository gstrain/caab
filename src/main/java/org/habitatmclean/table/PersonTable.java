package org.habitatmclean.table;

import org.habitatmclean.dao.CreateUpdateDeleteDAO;
import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.dao.ReadDAO;
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
    @Override
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
        newAddress.setStreet(request.getParameter("address"));
        newAddress.setCity(request.getParameter("city"));
        newAddress.setState(request.getParameter("state"));
        newAddress.setZipcode(request.getParameter("zip"));

        person.setFirst(request.getParameter("firstName"));
        person.setMiddle(request.getParameter("middleName"));
        person.setLast(request.getParameter("lastName"));
        person.setHome_phone(request.getParameter("home-phone"));
        person.setEmail(request.getParameter("email"));

        GenericDao relationDao = HibernateAdapter.getBoByEntityName("RelationType");
        RelationType rt = (RelationType) relationDao.findByPrimaryKey(new Long(3));
        person.setRelationType(rt);

        personDao.save(person);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    public void recordAdd(HttpServletRequest request){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        //make address
        Address newAddress = new Address();
        newAddress.setStreet(request.getParameter("address"));
        newAddress.setCity(request.getParameter("city"));
        newAddress.setState(request.getParameter("state"));
        newAddress.setZipcode(request.getParameter("zip"));

        Person newPerson = new Person();
        newPerson.setFirst(request.getParameter("firstName"));
        newPerson.setMiddle(request.getParameter("middleName"));
        newPerson.setLast(request.getParameter("lastName"));
        newPerson.setHome_phone(request.getParameter("home-phone"));
        newPerson.setEmail(request.getParameter("email"));

        GenericDao dao = HibernateAdapter.getBoByEntityName("RelationType");
        RelationType rt = (RelationType) dao.findByPrimaryKey(new Long(3));

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
            //we JQuery now
            //name and type are the required fields. name will be used for grabbing the value. Type will be used to determine the type
            //text is currently the only supported type. more will be supported soon!
            //maxLength is important for fields that could be complained about in the database.
            forms.add(Form.builder().setType("text").setName("firstName").setLabel("First Name").build());
            forms.add(Form.builder().setType("text").setName("middleName").setLabel("Middle Name").setRequired(false).build());
            forms.add(Form.builder().setType("text").setName("lastName").setLabel("Last Name").build());
            forms.add(Form.builder().setType("text").setName("address").setLabel("Address Line 1").setMaxLength(120).build());
            forms.add(Form.builder().setType("text").setName("city").setLabel("City").setMaxLength(120).build());
            forms.add(Form.builder().setType("text").setName("state").setLabel("State").setMaxLength(20).build());
            forms.add(Form.builder().setType("text").setName("zip").setLabel("Zip").setMaxLength(9).build());
            forms.add(Form.builder().setType("text").setName("home-phone").setLabel("Phone Number").build());
            forms.add(Form.builder().setType("text").setName("email").setLabel("Email").setMaxLength(120).build());
        }
    }
}



