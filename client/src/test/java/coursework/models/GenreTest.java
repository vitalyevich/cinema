package coursework.models;

import coursework.rmi.BillingClient;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

class GenreTest {

    private Genre genre;
    private Genre genreNotAdd;

    @BeforeEach
    public void setUp() throws Exception {
        genre = new Genre(1,"Биография");
        genreNotAdd = new Genre(0, null);
        client = new BillingClient();
    }

    private BillingClient client;
    @Test
    void getAllGenre_NO_NULL() throws RemoteException {
        List<Genre> genres = client.getGenreList();
        Assert.assertNotNull(genres);
    }

    @Test
    public void getAllGenre_CHOICE() throws RemoteException {
        List<Genre> expected = client.getGenre(1);

        List<Genre> actual = new ArrayList<>();
        actual.add(genre);

        Assert.assertEquals(expected.get(0).getGenreName(), actual.get(0).getGenreName());
    }

    @Test
    public void getHowManyGenre() throws RemoteException {
        int expected = client.getGenreList().size();
        int actual = 20;

        Assert.assertEquals(expected, actual);
    }
}