package com.fit.cvut.bietjv.project.carevidenceclient;

import com.fit.cvut.bietjv.project.carevidenceclient.client.BookClient;
import com.fit.cvut.bietjv.project.carevidenceclient.client.BusClient;
import com.fit.cvut.bietjv.project.carevidenceclient.client.DriverClient;
import com.fit.cvut.bietjv.project.carevidenceserver.entities.Book;
import com.fit.cvut.bietjv.project.carevidenceserver.entities.BookBox;
import com.fit.cvut.bietjv.project.carevidenceserver.entities.Bus;
import com.fit.cvut.bietjv.project.carevidenceserver.entities.BusBox;
import com.fit.cvut.bietjv.project.carevidenceserver.entities.Driver;
import com.fit.cvut.bietjv.project.carevidenceserver.entities.DriverBox;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        BookClient bookclient = new BookClient();
        BusClient busclient = new BusClient();
        DriverClient driverclient = new DriverClient();
        
        Book myBook = new Book();
        Bus myBus = new Bus();
        Driver myDriver = new Driver();
        
        DriverBox drivers = driverclient.findAllDrivers_JSON(DriverBox.class);
        Grid<Driver> driverGrid = initDriverGrid(drivers.getDrivers());
        
        HorizontalLayout search1 = new HorizontalLayout();
        TextField searchText1 = new TextField();
        Button searchButton1 = new Button("Search");
        searchButton1.addClickListener(e -> {
              DriverBox tmp = driverclient.findSearchedDrivers_JSON(DriverBox.class, searchText1.getValue());
              driverGrid.setItems(tmp.getDrivers());
           
        });
        
        Button resetButton1 = new Button("Reset");
        resetButton1.addClickListener(e -> {
            resetDriverGrid(driverGrid);
        });
        
        search1.addComponent(searchText1);
        search1.addComponent(searchButton1);
        search1.addComponent(resetButton1);

        
        layout.addComponents(driverGrid,search1);
        
        final VerticalLayout layout2 = new VerticalLayout();
        
        final TextField name = new TextField();
        final TextField surname = new TextField();
        
        name.setCaption("Name:");
        surname.setCaption("Surname");
        
        Button createDriver = new Button("Add");
        createDriver.addClickListener(e -> {
            Notification.show("Thanks, " + name.getValue() + " has been added");
            myDriver.setName(name.getValue());
            myDriver.setSurname(surname.getValue());
            driverclient.create_JSON(myDriver);
            resetDriverGrid(driverGrid);
        });
         
        
        
        final TextField id = new TextField();
        id.setCaption("Driver Id:");
        
        Button deleteDriver = new Button("Delete Customer");
        deleteDriver.addClickListener(e -> {
           Notification.show("Thanks, driver,  has been deleted"); 
            driverclient.remove(id.getValue());
            resetDriverGrid(driverGrid);
        });
        
        layout2.addComponents(name, surname, createDriver, id, deleteDriver);
        
        final VerticalLayout layout3 = new VerticalLayout();
        
        final TextField id2 = new TextField();
        id2.setCaption("Driver Id:");
        
        final TextField name2 = new TextField();
        name2.setCaption("Driver Name:");
        
        final TextField surname2 = new TextField();
        surname2.setCaption("Driver Surname:");
        
        Button edit = new Button("Edit Driver");
        edit.addClickListener(e -> {
            Driver newDriver = new Driver();
            newDriver.setId(Long.parseLong(id2.getValue()));
            newDriver.setName(name2.getValue());
            newDriver.setSurname(surname2.getValue()); 
            driverclient.edit_JSON(newDriver, id2.getValue()); 
             Notification.show("Thanks driver has been edited");
             resetDriverGrid(driverGrid);
        });
        
        layout3.addComponents(id2, name2, surname2, edit);
        
        
        
        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponents(layout,layout2, layout3);
        
        
        
        
        final VerticalLayout BookLayout1 = new VerticalLayout();
        BookBox books = bookclient.findAllBooks_JSON(BookBox.class);
        Grid<Book> bookGrid = initBookGrid(books.getBooks());
        HorizontalLayout search2 = new HorizontalLayout();
        TextField searchText2 = new TextField();
        Button searchButton2 = new Button("Search");
        searchButton2.addClickListener(e -> {
              BookBox tmp = bookclient.findSearchedBooks_JSON(BookBox.class, searchText2.getValue());
              bookGrid.setItems(tmp.getBooks());
           
        });
        
        Button resetButton2 = new Button("Reset");
        resetButton2.addClickListener(e -> {
            resetBookGrid(bookGrid);
        });
        
        search2.addComponent(searchText2);
        search2.addComponent(searchButton2);
        search2.addComponent(resetButton2);
 
        BookLayout1.addComponents(bookGrid, search2);
        
        final VerticalLayout BookLayout2 = new VerticalLayout();
        final TextField date_r = new TextField();
        final TextField notes = new TextField();
        
        date_r.setCaption("Date:");
        notes.setCaption("Notes");
        
       Button createBook = new Button("Add");
        createBook.addClickListener(e -> { 
            Notification.show("Thanks, book has been added");
            myBook.setDate_R(date_r.getValue());
            myBook.setNotes(notes.getValue()); 
            bookclient.create_JSON(myBook); 
            resetBookGrid(bookGrid);
        });
        
        final TextField id_book = new TextField();
        id_book.setCaption("Id");
         
        Button deleteBook = new Button("Delete Book");
        deleteBook.addClickListener(e -> {
           Notification.show("Thanks, book has been deleted"); 
            bookclient.remove(id_book.getValue()); 
            resetBookGrid(bookGrid);
        });
        
        BookLayout2.addComponents(date_r, notes, createBook, id_book, deleteBook);
        
        
        final VerticalLayout bookLayout3 = new VerticalLayout();
        
        final TextField id2_book = new TextField();
        id2_book.setCaption("Id:");
        
        final TextField date_r2 = new TextField();
        date_r2.setCaption("Date:");
        
        final TextField notes2 = new TextField();
        notes2.setCaption("Notes:");
        
        Button edit_book = new Button("Edit Book");
        edit_book.addClickListener(e -> {
            Book newBook = new Book();
            newBook.setId(Long.parseLong(id2_book.getValue()));
            newBook.setDate_R(date_r2.getValue());
            newBook.setNotes(notes2.getValue()); 
            bookclient.edit_JSON(newBook, id2_book.getValue()); 
             Notification.show("Thanks book has been edited"); 
             resetBookGrid(bookGrid);
        });
        
        bookLayout3.addComponents(id2_book, date_r2, notes2, edit_book);
        
        final HorizontalLayout horizontalLayout2 = new HorizontalLayout();
        horizontalLayout2.addComponents(BookLayout1, BookLayout2, bookLayout3);
        
        final VerticalLayout BusLayout1 = new VerticalLayout();
        BusBox buses = busclient.findAllBuses_JSON(BusBox.class);
        Grid<Bus> busGrid = initBusGrid(buses.getBuses());
        HorizontalLayout search3 = new HorizontalLayout();
        TextField searchText3 = new TextField();
        Button searchButton3 = new Button("Search");
        searchButton3.addClickListener(e -> {
              BusBox tmp = busclient.findSearchedBuses_JSON(BusBox.class, searchText3.getValue());
              busGrid.setItems(tmp.getBuses());
           
        });
        
        Button resetButton3 = new Button("Reset");
        resetButton3.addClickListener(e -> {
            resetBusGrid(busGrid);
        });
        
        search3.addComponent(searchText3);
        search3.addComponent(searchButton3);
        search3.addComponent(resetButton3);
        BusLayout1.addComponents(busGrid, search3);
        
        final VerticalLayout BusLayout2 = new VerticalLayout();
        final TextField line_id = new TextField();
        final TextField capacity = new TextField();
        
        line_id.setCaption("line_id:");
        capacity.setCaption("capacity");
        
       Button createBus = new Button("Add");
        createBus.addClickListener(e -> { 
            Notification.show("Thanks, bus has been added");
            myBus.setLine_ID(line_id.getValue());
            myBus.setCapacity(Integer.parseInt(capacity.getValue())); 
            busclient.create_JSON(myBus); 
            resetBusGrid(busGrid);
        });
        
        final TextField id_bus = new TextField();
        id_bus.setCaption("Id");
         
        Button deleteBus = new Button("Delete Bus");
        deleteBus.addClickListener(e -> {
           Notification.show("Thanks, bus has been deleted"); 
            busclient.remove(id_bus.getValue()); 
            resetBusGrid(busGrid);
        });
        
        BusLayout2.addComponents(line_id, capacity, createBus, id_bus, deleteBus);
        
        final VerticalLayout busLayout3 = new VerticalLayout();
        
        final TextField id2_bus = new TextField();
        id2_bus.setCaption("Id:");
        
        final TextField line_id2 = new TextField();
        line_id2.setCaption("line_id:");
        
        final TextField capacity2 = new TextField();
        capacity2.setCaption("capacity:");
        
        Button edit_bus = new Button("Edit Bus");
        edit_bus.addClickListener(e -> {
            Bus newBus = new Bus();
            newBus.setId(Long.parseLong(id2_bus.getValue()));
            newBus.setLine_ID(line_id2.getValue());
            newBus.setCapacity(Integer.parseInt(capacity2.getValue())); 
            busclient.edit_JSON(newBus, id2_bus.getValue()); 
             Notification.show("Thanks bus has been edited"); 
             resetBusGrid(busGrid);
        });
        
        busLayout3.addComponents(id2_bus, line_id2, capacity2, edit_bus);
        
        final HorizontalLayout horizontalLayout3 = new HorizontalLayout();
        horizontalLayout3.addComponents(BusLayout1, BusLayout2, busLayout3);
        
        final VerticalLayout commonLayout = new VerticalLayout();
        commonLayout.addComponents(horizontalLayout, horizontalLayout2, horizontalLayout3);
        setContent(commonLayout);
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    private Grid<Driver> initDriverGrid(List<Driver> drivers) {
        Grid<Driver> grid = new Grid<>();
        grid.setSizeFull();
        grid.setItems(drivers);
        grid.addColumn(Driver::getId).setCaption("ID");
        grid.addColumn(Driver::getName).setCaption("Name");
        grid.addColumn(Driver::getSurname).setCaption("Surname"); 
        return grid;
    }
    
    private void resetDriverGrid(Grid<Driver> driverGrid) {
        DriverClient cc = new DriverClient();
        DriverBox tmp = cc.findAllDrivers_JSON(DriverBox.class);
        driverGrid.setItems(tmp.getDrivers());
    }
    
    private Grid<Book> initBookGrid(List<Book> books) {
        Grid<Book> grid = new Grid<>();
        grid.setSizeFull();
        grid.setItems(books);
        grid.addColumn(Book::getId).setCaption("ID");
        grid.addColumn(Book::getDate_R).setCaption("Date_R");
        grid.addColumn(Book::getNotes).setCaption("Notes"); 
        return grid;
    }
    
    private void resetBookGrid(Grid<Book> bookGrid) {
        BookClient cc = new BookClient();
        BookBox tmp = cc.findAllBooks_JSON(BookBox.class);
        bookGrid.setItems(tmp.getBooks());
    }
    
    
    private Grid<Bus> initBusGrid(List<Bus> buses) {
        Grid<Bus> grid = new Grid<>();
        grid.setSizeFull();
        grid.setItems(buses);
        grid.addColumn(Bus::getId).setCaption("ID");
        grid.addColumn(Bus::getLine_ID).setCaption("Line_ID");
        grid.addColumn(Bus::getCapacity).setCaption("Capacity"); 
        return grid;
    }
    
    private void resetBusGrid(Grid<Bus> busGrid) {
        BusClient cc = new BusClient();
        BusBox tmp = cc.findAllBuses_JSON(BusBox.class);
        busGrid.setItems(tmp.getBuses());
    }
}
