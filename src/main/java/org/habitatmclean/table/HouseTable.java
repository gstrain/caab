package org.habitatmclean.table;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.*;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class HouseTable extends Table {
    public HouseTable() {
        super(new String[]{"address", "Sq Feet", "bedrooms", "bathrooms", "style"}, new HouseModal(), true); // adjust this to determine table columns
    }
    public void addRow(GenericEntity entity) {
        House house = (House) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(house.getAddress().getNumber() + " " +
                                            house.getAddress().getStreet() + "\n" +
                                            house.getAddress().getCity() + ", " +
                                            house.getAddress().getState() + " " +
                                            house.getAddress().getZipcode()));
        tableCells.add(new TableRow.TableCell("" + house.getSize()));
        tableCells.add(new TableRow.TableCell("" + house.getBedrooms()));
        tableCells.add(new TableRow.TableCell("" + house.getBathrooms()));
        tableCells.add(new TableRow.TableCell(house.getHouse_style().getStyle()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    public void recordEdit(HttpServletRequest request, int id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        House house = (House) HibernateAdapter.getBoByEntityName("House").findByPrimaryKey(new Long(id));

        Address address = house.getAddress();
        address.setApartment_no(request.getParameter("apartment_no"));
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));
        address.setState(request.getParameter("state"));
        address.setZipcode(request.getParameter("zipcode"));

        GenericDao dao = HibernateAdapter.getBoByEntityName("Property");
        Property property = (Property) dao.findByPrimaryKey(new Long(request.getParameter("property")));
        house.setProperty(property);

        dao = HibernateAdapter.getBoByEntityName("Family");
        Family family = (Family) dao.findByPrimaryKey(new Long(request.getParameter("family")));
        house.setFamily(family);

        dao = HibernateAdapter.getBoByEntityName("ConstructionStatus");
        ConstructionStatus cs = (ConstructionStatus) dao.findByPrimaryKey(new Long(request.getParameter("construction_status")));
        house.setConstruction_status(cs);

        dao = HibernateAdapter.getBoByEntityName("HouseStyle");
        HouseStyle hs = (HouseStyle) dao.findByPrimaryKey(new Long(request.getParameter("house_style")));
        house.setHouse_style(hs);

        sessionFactory.getCurrentSession().getTransaction().commit();

    }
    public void recordAdd(HttpServletRequest request){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        House house = new House();

        Address address = new Address();
        address.setApartment_no(request.getParameter("apartment_no"));
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));
        address.setState(request.getParameter("state"));
        address.setZipcode(request.getParameter("zipcode"));
        house.setAddress(address);

        GenericDao dao = HibernateAdapter.getBoByEntityName("Property");
        Property property = (Property) dao.findByPrimaryKey(new Long(request.getParameter("property")));
        house.setProperty(property);

        dao = HibernateAdapter.getBoByEntityName("Family");
        Family family = (Family) dao.findByPrimaryKey(new Long(request.getParameter("family")));
        house.setFamily(family);

        dao = HibernateAdapter.getBoByEntityName("ConstructionStatus");
        ConstructionStatus cs = (ConstructionStatus) dao.findByPrimaryKey(new Long(request.getParameter("construction_status")));
        house.setConstruction_status(cs);

        dao = HibernateAdapter.getBoByEntityName("HouseStyle");
        HouseStyle hs = (HouseStyle) dao.findByPrimaryKey(new Long(request.getParameter("house_style")));
        house.setHouse_style(hs);

        dao = new HibernateAdapter();
        dao.save(house);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    static class HouseModal extends Modal {
        public HouseModal(){
            super("House");
        }

        public void buildModal(){
            forms.add(Form.builder().setType("text").setName("construction_cost").setLabel("Construction Cost").build()); // max length is irrelevant for double type in MySQL
            forms.add(Form.builder().setType("text").setName("size").setLabel("Square Ft").setMaxLength(30).build());
            forms.add(Form.builder().setType("text").setName("bedrooms").setLabel("Bedrooms").setMaxLength(2).build());
            forms.add(Form.builder().setType("text").setName("bathrooms").setLabel("Bathrooms").build());
            forms.add(Form.builder().setType("text").setName("apartment_no").setLabel("Apartment #").setMaxLength(6).build());
            forms.add(Form.builder().setType("text").setName("street").setLabel("Address").setMaxLength(120).build());
            forms.add(Form.builder().setType("text").setName("city").setLabel("City").setMaxLength(120).build());
            forms.add(Form.builder().setType("text").setName("state").setLabel("State").setMaxLength(20).build());
            forms.add(Form.builder().setType("text").setName("zipcode").setLabel("Zip").setMaxLength(9).build());
            forms.add(Form.builder().setType("select").setName("property").setLabel("Property").setFromTable("Property","address").build());
            forms.add(Form.builder().setType("select").setName("family").setLabel("Family").setFromTable("Family","this").build());
            forms.add(Form.builder().setType("select").setName("house_style").setLabel("House Style").setFromTable("HouseStyle","style").build());
            forms.add(Form.builder().setType("select").setName("construction_status").setLabel("Construction Status").setFromTable("ConstructionStatus","this").build());
        }
    }

}
