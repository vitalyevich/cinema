package coursework.rmi;

import coursework.models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BillingService extends Remote {

    public boolean checkConnectivity();

    public int AddNewStaff(Staff staff) throws RemoteException, SQLException;
    public void AddNewAccess(Access access) throws RemoteException, SQLException;

    public void EditStaff(Staff staff) throws  RemoteException;
    public void EditAccess(Access access) throws  RemoteException;
    public void DeleteStaffById(int id) throws RemoteException;
    public void DeleteStaffByPosition(Department role) throws RemoteException;
    public void TruncateTableStaff() throws RemoteException;

    public List<Access>  getStaff(int searchId) throws RemoteException;

    public List<Access>  getStaffList() throws RemoteException;
    public List<Access> findByAccess(String lastName, String password) throws RemoteException;

    public void AddNewDepartment(Department department) throws RemoteException, SQLException;
    public void EditDepSalary(Department department) throws  RemoteException;
    public void EditDepartment(Department department) throws  RemoteException;
    public void DeleteDepartment(int id) throws RemoteException;
    public void TruncateTableDepartment() throws RemoteException;
    public List<Department> getDepartmentList() throws RemoteException;

/*    public void AddNewDrink(Drink drink) throws RemoteException;
    public void EditDrink(Drink drink) throws  RemoteException;
    public void DeleteDrink(Drink drink) throws RemoteException;*/
    public List<Product> getProductList(String name) throws RemoteException;
    public List<Product> getProductList() throws RemoteException;
    public List<Product> getProductList(int searchId) throws RemoteException;
/*    public List<Drink> findByDrink(String drink) throws RemoteException;*/

    public void AddNewProduct(Product product) throws RemoteException;
    public void EditProduct(Product product) throws  RemoteException;
    public void DeleteProduct(int id) throws RemoteException;

    public void DeleteProductType(int id) throws RemoteException;
    public void AddNewProductType(ProductType product) throws RemoteException;
    public void EditProductType(ProductType product) throws  RemoteException;

    public List<ProductType> getProductTypeList() throws RemoteException;
    public List<ProductType> findByProduct(ProductType productType) throws RemoteException;

    public int AddNewFilm(Film film) throws RemoteException, SQLException;
    public void EditFilm(Film film) throws  RemoteException;
    public void DeleteFilmById(int id) throws RemoteException;
    public void DeleteFilm(String name) throws RemoteException;
    public void TruncateTableFilm() throws RemoteException;
    public List<Film> getFilmList() throws RemoteException;
    public List<Film>  getFilm(int searchId) throws RemoteException;

    public List<GenresName>  getFilmGenre(int searchId) throws RemoteException;
    public List<CountriesName>  getFilmCountry(int searchId) throws RemoteException;

    public void AddNewFilmGenre(GenresName genresName) throws RemoteException, SQLException;
    public void EditFilmGenre(int id) throws RemoteException, SQLException;
    public void EditFilmCountry(int id) throws RemoteException, SQLException;
    public void AddNewFilmCountry(CountriesName countriesName) throws RemoteException, SQLException;

    public void AddNewSeance(Seance seance) throws RemoteException, SQLException;
    public void EditSeance(Seance seance) throws  RemoteException;
    public void DeleteSeanceById(int id) throws RemoteException;
    public void DeleteSeanceByDate(LocalDate date, LocalTime time) throws RemoteException;
    public void DeleteSeanceByName(int id) throws RemoteException;
    public void TruncateTableSeance() throws RemoteException;
    public List<Seance> getSeanceList() throws RemoteException;
    public List<Seance>  getSeance(int searchId) throws RemoteException;

    public void AddNewHall(Hall hall) throws RemoteException, SQLException;
    public void EditHall(Hall hall) throws  RemoteException;
    public void DeleteHall(int id) throws RemoteException;
    public void TruncateTableHall() throws RemoteException;
    public List<Hall> getHallList() throws RemoteException;
    public List<Hall> getHall(int searchId) throws RemoteException;

    public void AddNewSeat(Seat seat) throws RemoteException, SQLException;
    public void EditSeat(Seat seat) throws  RemoteException;
    public void DeleteSeat(Seat seat) throws RemoteException;
    public void TruncateTableSeat() throws RemoteException;
    public List<Seat> getSeatList() throws RemoteException;
    public List<Seat> getSeat(int searchId) throws RemoteException;
    public List<Seat> getSeatCategoryName(int idHall, int row, int seat) throws RemoteException;
    public int getSeatByRow(int row, int hallId) throws  RemoteException;

    public void AddNewCategory(Category category) throws RemoteException, SQLException;
    public void EditCategory(Category category) throws  RemoteException;
    public void DeleteCategory(int id) throws RemoteException;
    public void TruncateTableCategory() throws RemoteException;
    public List<Category> getCategoryList() throws RemoteException;
    public List<Category> getCategory(int searchId) throws RemoteException;

    public void AddNewGenre(Genre genre) throws RemoteException, SQLException;
    public void EditGenre(Genre genre) throws  RemoteException;
    public void DeleteGenre(int id) throws RemoteException;
    public void TruncateTableGenre() throws RemoteException;
    public List<Genre> getGenreList() throws RemoteException;
    public List<Genre> getGenre(int searchId) throws RemoteException;

    public void AddNewCountry(Country country) throws RemoteException, SQLException;
    public void EditCountry(Country country) throws  RemoteException;
    public void DeleteCountry(int id) throws RemoteException;
    public void TruncateTableCountry() throws RemoteException;
    public List<Country> getCountryList() throws RemoteException;
    public List<Country> getCountry(int searchId) throws RemoteException;

    public int AddNewOrder(Order order) throws  RemoteException;
    public void AddNewOrderProduct(OrderProduct orderProduct) throws  RemoteException;
    public void AddNewCheck(Check check) throws  RemoteException;
    public List<Check> getCheckList() throws  RemoteException;
    public List<OrderProduct> getOrderList() throws  RemoteException;


    public List<Ticket> getTicketList() throws  RemoteException;
    public List<TicketsPrice> getTicketPriceList() throws  RemoteException;

    public List<Seat> getSeatList(int hallId) throws RemoteException;
    public List<Hall> getHallList(int hallId) throws RemoteException;
}
