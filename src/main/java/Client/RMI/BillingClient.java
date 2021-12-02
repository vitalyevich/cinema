package Client.RMI;

import Server.Model.*;
import Server.RMI.BillingService;
import javafx.collections.ObservableList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BillingClient implements Client.RMI.BillingService {

    private String localhost    = "127.0.0.1";
    private String RMI_HOSTNAME = "java.rmi.server.hostname";
    private String SERVICE_PATH = "rmi://localhost/BillingService";

    private BillingService bs;
    private boolean check = true;

    public BillingClient()
    {
        try {

            System.setProperty(RMI_HOSTNAME, localhost);
            // URL удаленного объекта
            String objectName = SERVICE_PATH;

            bs = (BillingService) Naming.lookup(objectName);

        } catch (MalformedURLException e) {
            this.check = false;
        } catch (RemoteException e) {
            this.check = false;
        } catch (NotBoundException e) {
            this.check = false;
        }
    }

    public static void main(String[] args) {
        new BillingClient();
    }

    @Override
    public void AddNewStaff(Staff staff) throws RemoteException, SQLException {
        bs.AddNewStaff(staff);
    }

    public void AddNewAccess(Access access) throws RemoteException, SQLException {
        bs.AddNewAccess(access);
    }

    @Override
    public void EditStaff(Staff employee) throws RemoteException {
        bs.EditStaff(employee);
    }

    @Override
    public void DeleteStaff(String lastName, String firstName, String middleName) throws RemoteException, SQLException {
        bs.DeleteStaff(lastName,firstName,middleName);
    }

    @Override
    public void DeleteStaffById(int id) throws RemoteException {
        bs.DeleteStaffById(id);
    }

    @Override
    public void DeleteStaffByPosition(Department roleId) throws RemoteException {
        bs.DeleteStaffByPosition(roleId);
    }

    @Override
    public void TruncateTableStaff() throws RemoteException {
        bs.TruncateTableStaff();
    }

    @Override
    public List<Access>  getStaffList() throws RemoteException {
        return bs.getStaffList();
    }

    @Override
    public List<Access> getStaff(int searchId) throws RemoteException {
        return bs.getStaff(searchId);
    }

    @Override
    public List<Access> findByAccess(String lastName, String password) throws RemoteException {
        return bs.findByAccess(lastName, password);
    }

    @Override
    public boolean checkConnectivity() {
        return this.check;
    }

    @Override
    public void AddNewDepartment(Department department) throws RemoteException, SQLException {
        bs.AddNewDepartment(department);
    }

    @Override
    public void EditDepSalary(Department department) throws RemoteException {
        bs.EditDepSalary(department);
    }

    @Override
    public void EditDepartment(Department department) throws RemoteException {
        bs.EditDepartment(department);
    }

    @Override
    public void DeleteDepartment(int id) throws RemoteException {
        bs.DeleteDepartment(id);
    }

    @Override
    public void TruncateTableDepartment() throws RemoteException {
        bs.TruncateTableDepartment();
    }

    @Override
    public List<Department> getDepartmentList(Department department) throws RemoteException {
        return bs.getDepartmentList(department);
    }

   /* @Override
    public void AddNewDrink(Drink drink) throws RemoteException {
        bs.AddNewDrink(drink);
    }

    @Override
    public void EditDrink(Drink drink) throws RemoteException {
        bs.EditDrink(drink);
    }

    @Override
    public void DeleteDrink(Drink drink) throws RemoteException {

    }
    @Override
    public List<Drink> findByDrink(String drink) throws RemoteException {
        return null;
    }*/

    @Override
    public List<Product> getProductList(String name) throws RemoteException {
        return bs.getProductList(name);
    }

    @Override
    public List<Product> getProductList() throws RemoteException {
        return bs.getProductList();
    }

    @Override
    public List<Product> getProductList(int searchId) throws RemoteException {
        return bs.getProductList(searchId);
    }

    @Override
    public void AddNewProduct(Product product) throws RemoteException {
        bs.AddNewProduct(product);
    }

    @Override
    public void EditProduct(Product product) throws RemoteException {
        bs.EditProduct(product);
    }

    @Override
    public void DeleteProductById(int id) throws RemoteException {
        bs.DeleteProductById(id);
    }

    @Override
    public void DeleteProductByName(String name) throws RemoteException {
        bs.DeleteProductByName(name);
    }

    public void TruncateTableProduct() throws RemoteException {
        bs.TruncateTableProduct();
    }

    @Override
    public List<ProductType> getProductTypeList() throws RemoteException {
        return bs.getProductTypeList();
    }

    @Override
    public List<ProductType> findByProduct(ProductType productType) throws RemoteException {
        return null;
    }

    @Override
    public void AddNewFilm(Film film) throws RemoteException {
        bs.AddNewFilm(film);
    }

    @Override
    public void EditFilm(Film film) throws RemoteException {
        bs.EditFilm(film);
    }

    @Override
    public void DeleteFilmById(int id) throws RemoteException {
        bs.DeleteFilmById(id);
    }

    @Override
    public void DeleteFilm(String name) throws RemoteException {
        bs.DeleteFilm(name);
    }

    @Override
    public void TruncateTableFilm() throws RemoteException {
        bs.TruncateTableFilm();
    }

    @Override
    public List<Film> getFilmList() throws RemoteException {
        return bs.getFilmList();
    }

    @Override
    public List<Film> getFilm(int searchId) throws RemoteException {
        return bs.getFilm(searchId);
    }

    @Override
    public List<GenresName> getFilmGenre(int searchId) throws RemoteException {
        return bs.getFilmGenre(searchId);
    }

    @Override
    public List<CountriesName> getFilmCountry(int searchId) throws RemoteException {
        return bs.getFilmCountry(searchId);
    }

    @Override
    public void AddNewFilmGenre(GenresName genresName) throws RemoteException {
        bs.AddNewFilmGenre(genresName);
    }

    @Override
    public void AddNewFilmCountry(CountriesName countriesName) throws RemoteException {
        bs.AddNewFilmCountry(countriesName);
    }

    @Override
    public void AddNewSeance(Seance seance) throws RemoteException, SQLException {
        bs.AddNewSeance(seance);
    }

    @Override
    public void EditSeance(Seance seance) throws RemoteException {
        bs.EditSeance(seance);
    }

    @Override
    public void DeleteSeanceById(int id) throws RemoteException {
        bs.DeleteSeanceById(id);
    }

    @Override
    public void DeleteSeanceByDate(LocalDate date, LocalTime time) throws RemoteException {
        bs.DeleteSeanceByDate(date, time);
    }

    @Override
    public void DeleteSeanceByName(int id) throws RemoteException {
        bs.DeleteSeanceByName(id);
    }

    @Override
    public void TruncateTableSeance() throws RemoteException {
        bs.TruncateTableSeance();
    }

    @Override
    public List<Seance> getSeanceList() throws RemoteException {
        return bs.getSeanceList();
    }

    @Override
    public List<Seance> getSeance(int searchId) throws RemoteException {
        return bs.getSeance(searchId);
    }

    @Override
    public void AddNewHall(Hall hall) throws RemoteException, SQLException {
        bs.AddNewHall(hall);
    }

    @Override
    public void EditHall(Hall hall) throws RemoteException {
        bs.EditHall(hall);
    }

    @Override
    public void DeleteHall(int id) throws RemoteException {
        bs.DeleteHall(id);
    }

    @Override
    public void TruncateTableHall() throws RemoteException {
        bs.TruncateTableHall();
    }

    @Override
    public List<Hall> getHallList() throws RemoteException {
        return bs.getHallList();
    }

    @Override
    public List<Hall> getHall(int searchId) throws RemoteException {
        return bs.getHall(searchId);
    }

    @Override
    public void AddNewSeat(Seat seat) throws RemoteException, SQLException {
        bs.AddNewSeat(seat);
    }

    @Override
    public void EditSeat(Seat seat) throws RemoteException {
        bs.EditSeat(seat);
    }

    @Override
    public void DeleteSeat(Seat seat) throws RemoteException {
        bs.DeleteSeat(seat);
    }

    @Override
    public void TruncateTableSeat() throws RemoteException {
        bs.TruncateTableSeat();
    }

    @Override
    public List<Seat> getSeatList() throws RemoteException {
        return bs.getSeatList();
    }

    @Override
    public List<Seat> getSeat(int searchId) throws RemoteException {
        return bs.getSeat(searchId);
    }

    @Override
    public List<Seat> getSeatCategoryName(int idHall, int row, int seat) throws RemoteException {
        return bs.getSeatCategoryName(idHall, row, seat);
    }

    @Override
    public int getSeatByRow(int row) throws  RemoteException {
        return bs.getSeatByRow(row);
    }

    @Override
    public void AddNewCategory(Category category) throws RemoteException, SQLException {
        bs.AddNewCategory(category);
    }

    @Override
    public void EditCategory(Category category) throws RemoteException {
        bs.EditCategory(category);
    }

    @Override
    public void DeleteCategory(int id) throws RemoteException {
        bs.DeleteCategory(id);
    }

    @Override
    public void TruncateTableCategory() throws RemoteException {
        bs.TruncateTableCategory();
    }

    @Override
    public List<Category> getCategoryList() throws RemoteException {
        return bs.getCategoryList();
    }

    @Override
    public List<Category> getCategory(int searchId) throws RemoteException {
        return bs.getCategory(searchId);
    }

    @Override
    public void AddNewGenre(Genre genre) throws RemoteException, SQLException {
        bs.AddNewGenre(genre);
    }

    @Override
    public void EditGenre(Genre genre) throws RemoteException {
        bs.EditGenre(genre);
    }

    @Override
    public void DeleteGenre(int id) throws RemoteException {
        bs.DeleteGenre(id);
    }

    @Override
    public void TruncateTableGenre() throws RemoteException {
        bs.TruncateTableGenre();
    }

    @Override
    public List<Genre> getGenreList() throws RemoteException {
        return bs.getGenreList();
    }

    @Override
    public List<Genre> getGenre(int searchId) throws RemoteException {
        return bs.getGenre(searchId);
    }

    @Override
    public void AddNewCountry(Country country) throws RemoteException, SQLException {
        bs.AddNewCountry(country);
    }

    @Override
    public void EditCountry(Country country) throws RemoteException {
        bs.EditCountry(country);
    }

    @Override
    public void DeleteCountry(int id) throws RemoteException {
        bs.DeleteCountry(id);
    }

    @Override
    public void TruncateTableCountry() throws RemoteException {
        bs.TruncateTableCountry();
    }

    @Override
    public List<Country> getCountryList() throws RemoteException {
        return bs.getCountryList();
    }

    @Override
    public List<Country> getCountry(int searchId) throws RemoteException {
        return bs.getCountry(searchId);
    }

}
