package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ShopRepositoryTest {

    ShopRepository repo = new ShopRepository();

    Product product1 = new Product(15, "bread", 45);
    Product product2 = new Product(7, "milk", 67);
    Product product3 = new Product(32, "motherboard", 65000);
    Product product4 = new Product(33, "snowboard", 37000);
    Product product5 = new Product(17, "bread", 65);

    @Test
    public void findAllProduct() { //поиск всех продуктов

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findForId(){

        repo.add(product1);
        repo.add(product3);
        repo.add(product5);

        Product expected = product5;
        int id = 17;
        Assertions.assertEquals(expected, repo.findById(id));
    }

        @Test
    public void findForNotId(){

        repo.add(product1);
        repo.add(product3);
        repo.add(product5);

        int id = 171;
        Assertions.assertNull(repo.findById(id));
    }


    @Test
    public void shouldNotFindId() { //удаление не существующего ID

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeByID(9);
        });
    }

    @Test
    public void removeNumberId() { //удаление продуктов по ID

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.removeByID(15);

        Product[] expected = {product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldDoubleNumberId() { //добавление продуктов по тому же ID

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product3);
        });
    }

    @Test
    public void shouldAddProduct() { //удаление по ID с последующей заменой товара

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.removeByID(32);

        repo.add(product4);

        Product[] expected = {product1, product2, product4};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindID() { //удаление по ID с последующей заменой товара

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.removeByID(32);


        Product[] expected = new Product[]{product1, product2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByTitle() { //поиск по названию
        repo.add(product1);
        repo.add(product2);

        Product[] expected = new Product[]{product2};
        Product[] actual = repo.searchByTitle("milk");

        Assertions.assertArrayEquals(expected, actual);
    }

        @Test
    void shouldSearchByTitleEmpty() { //пустое название

        Product[] expected = new Product[0];
        Product[] actual = repo.searchByTitle(" ");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReplayTitle() { //репозиторий с одинаковым названием

        repo.add(product1);
        repo.add(product3);
        repo.add(product5);

        Product[] expected = new Product[] {product1, product5};
        Product[] actual = repo.searchByTitle("bread");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldByTitleUnFound() { //в репозиториях нет искомого названия

        repo.add(product1);
        repo.add(product4);
        repo.add(product5);

        Product[] expected = new Product[0];
        Product[] actual = repo.searchByTitle("milk");

        Assertions.assertArrayEquals(expected, actual);
    }

        @Test
    void shouldByTitleFoundHightCase() { //по названию с высоким регистром

        repo.add(product1);
        repo.add(product4);
        repo.add(product5);

        Product[] expected = new Product[] {product1, product5};
        Product[] actual = repo.searchByTitle("Bread");

        Assertions.assertArrayEquals(expected, actual);
    }

        @Test
    public void findForPrice(){

        repo.add(product1);
        repo.add(product3);
        repo.add(product5);

        Product expected = product5;
        int price = 65;
        Assertions.assertEquals(expected, repo.findByPrice(price));
    }

            @Test
    public void findForNotPrice(){

        repo.add(product1);
        repo.add(product3);
        repo.add(product5);

        int price = 0;
        Assertions.assertNull(repo.findById(price));
    }
}
