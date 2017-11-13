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
        super(new String[]{"address", "construction cost", "bedrooms", "bathrooms", "style"}, new HouseModal(), true); // adjust this to determine table columns
    }
    public void addRow(GenericEntity entity) {
        House house = (House) entity;
        List<TableRow.TableCell> tableCells = new ArrayList<TableRow.TableCell>();
        tableCells.add(new TableRow.TableCell(house.getAddress().getNumber() + " " +
                                            house.getAddress().getStreet() + "\n" +
                                            house.getAddress().getCity() + ", " +
                                            house.getAddress().getState() + " " +
                                            house.getAddress().getZipcode()));
        tableCells.add(new TableRow.TableCell(String.format("$%.2f", house.getConstruction_cost())));
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

        //make address
        Address newAddress = house.getAddress();
        newAddress.setStreet(request.getParameter("address"));
        newAddress.setCity(request.getParameter("city"));
        newAddress.setState(request.getParameter("state"));
        newAddress.setZipcode(request.getParameter("zip"));

        GenericDao dao = HibernateAdapter.getBoByEntityName("Property");
        Property property = (Property) dao.findByPrimaryKey(new Long(1)); // TODO
        house.setProperty(property);

        dao = HibernateAdapter.getBoByEntityName("Family");
        Family family = (Family) dao.findByPrimaryKey(new Long(3)); // TODO
        house.setFamily(family);

        dao = HibernateAdapter.getBoByEntityName("ConstructionStatus");
        ConstructionStatus cs = (ConstructionStatus) dao.findByPrimaryKey(new Long(3)); // TODO
        house.setConstruction_status(cs);

        dao = HibernateAdapter.getBoByEntityName("HouseStyle");
        HouseStyle hs = (HouseStyle) dao.findByPrimaryKey(2L); // TODO
        house.setHouse_style(hs);

        dao = new HibernateAdapter();
        dao.save(house);
        sessionFactory.getCurrentSession().getTransaction().commit();

    }
    public void recordAdd(HttpServletRequest request){
    }

    static class HouseModal extends Modal{
        public HouseModal(){
            super("House");
        }

        public void buildModal(){
            forms.add(Form.builder().setType("text").setName("construction-cost").setLabel("Construction Cost").build()); // max length is irrelevant for double type in MySQL
            forms.add(Form.builder().setType("text").setName("size").setLabel("Square Ft").setMaxLength(30).build());
            forms.add(Form.builder().setType("text").setName("bedrooms").setLabel("Bedrooms").setMaxLength(2).build());
            forms.add(Form.builder().setType("text").setName("bathrooms").setLabel("Bathrooms").build()); // double
        }
    }

}
