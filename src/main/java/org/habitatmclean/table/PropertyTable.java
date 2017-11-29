package org.habitatmclean.table;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.*;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PropertyTable<P> extends Table {
    public PropertyTable() {
        super(new String[]{"property number", "address", "owner", "property status", "zone"},new PropertyModal(), true, true); // adjust this to determine table columns
    }
    public void addRow(GenericEntity entity) {
        Property property = (Property) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell("" + property.getId()));
        tableCells.add(new TableRow.TableCell("" + property.getPropertyAddress().toString()));
        tableCells.add(new TableRow.TableCell("" + property.getOwner().toString()));
        tableCells.add(new TableRow.TableCell(property.getProperty_status().getPstatus()));
        tableCells.add(new TableRow.TableCell(property.getZone().getZone_info()));
        TableRow tr = new TableRow(tableCells);
        tr.setRowId("" + entity.getId());
        rows.add(tr);
    }

    public void recordEdit(HttpServletRequest request, int id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        Property property = (Property) HibernateAdapter.getBoByEntityName("Property").findByPrimaryKey(new Long(id));

        try {
            property.setAppraised_value(Double.parseDouble(request.getParameter("appraised_value").trim()));
        } catch (NumberFormatException | NullPointerException e) {
            property.setAppraised_value(0);
        }
        property.setNotes(request.getParameter("notes"));
//        try {
//            property.setAppraised_date(request.getParameter("appriase_date").trim()));
//        } catch (NumberFormatException e) {
//            property.setAppraised_date(null);
//        } //TODO once date types are supported

        Address address = property.getPropertyAddress();
        address.setApartment_no(request.getParameter("apartment_no"));
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));
        address.setState(request.getParameter("state"));
        address.setZipcode(request.getParameter("zipcode"));
        property.setPropertyAddress(address);

        GenericDao dao = HibernateAdapter.getBoByEntityName("Actor");
        Actor owner = (Actor) dao.findByPrimaryKey(new Long(request.getParameter("owner")));
        property.setOwner(owner);

        dao = HibernateAdapter.getBoByEntityName("PropertyStatus");
        PropertyStatus propertyStatus = (PropertyStatus) dao.findByPrimaryKey(new Long(request.getParameter("property_status")));
        property.setProperty_status(propertyStatus);

        dao = HibernateAdapter.getBoByEntityName("Zone");
        Zone zone = (Zone) dao.findByPrimaryKey(new Long(request.getParameter("zone")));
        property.setZone(zone);

        sessionFactory.getCurrentSession().getTransaction().commit();

    }
    public void recordAdd(HttpServletRequest request) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        Property property = new Property();

        try {
            property.setAppraised_value(Double.parseDouble(request.getParameter("appraised_value").trim()));
        } catch (NumberFormatException | NullPointerException e) {
            property.setAppraised_value(0);
        }
        property.setNotes(request.getParameter("notes").trim());
//        try {
//            property.setAppraised_date(request.getParameter("appriase_date").trim()));
//        } catch (NumberFormatException e) {
//            property.setAppraised_date(null);
//        } //TODO once date types are supported

        Address address = new Address();
        address.setApartment_no(request.getParameter("apartment_no"));
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));
        address.setState(request.getParameter("state"));
        address.setZipcode(request.getParameter("zipcode"));
        property.setPropertyAddress(address);

        GenericDao dao = HibernateAdapter.getBoByEntityName("Actor");
        Actor owner = (Actor) dao.findByPrimaryKey(new Long(request.getParameter("owner")));
        property.setOwner(owner);

        dao = HibernateAdapter.getBoByEntityName("PropertyStatus");
        PropertyStatus propertyStatus = (PropertyStatus) dao.findByPrimaryKey(new Long(request.getParameter("property_status")));
        property.setProperty_status(propertyStatus);

        dao = HibernateAdapter.getBoByEntityName("Zone");
        Zone zone = (Zone) dao.findByPrimaryKey(new Long(request.getParameter("zone")));
        property.setZone(zone);

        dao = new HibernateAdapter();
        dao.save(property);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    static class PropertyModal extends Modal {
        public PropertyModal(){
            super("Property");
        }

        public void buildModal(){
            forms.add(Form.builder().setType("text").setName("street").setLabel("Address").setParent("propertyAddress").setMaxLength(120).build());
            forms.add(Form.builder().setType("text").setName("city").setLabel("City").setParent("propertyAddress").setMaxLength(120).build());
            forms.add(Form.builder().setType("text").setName("state").setLabel("State").setParent("propertyAddress").setMaxLength(20).build());
            forms.add(Form.builder().setType("text").setName("zipcode").setLabel("Zip").setParent("propertyAddress").setMaxLength(9).build());
            forms.add(Form.builder().setType("text").setName("appraised_value").setLabel("Appraised Value").setRequired(false).build());
//            forms.add(Form.builder().setType("date").setName("appraised_date").setLabel("Appraised Date").build());   //TODO type should be date
            forms.add(Form.builder().setType("text").setName("taxes").setLabel("Taxes").setRequired(false).build());
            forms.add(Form.builder().setType("text").setName("notes").setLabel("Notes").setRequired(false).build());
            forms.add(Form.builder().setType("select").setName("owner").setLabel("Owner").setFromTable("Actor", "this").setRequired(false).build());
            forms.add(Form.builder().setType("select").setName("property_status").setLabel("Property Status").setFromTable("PropertyStatus", "this").setRequired(false).build());
            forms.add(Form.builder().setType("select").setName("zone").setLabel("Zone").setFromTable("Zone","this").setRequired(false).build());
        }
    }
}
