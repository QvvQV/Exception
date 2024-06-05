package ru.netology;

public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
//        products = addToArray(products, product);
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException(
                    "Element with id: " + product.getId() + " already exists"
            );
        }
        Product[] tmp = new Product[products.length + 1];
        System.arraycopy(products, 0, tmp, 0, products.length);
        tmp[products.length] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public void removeByID(int id) { //вернуть number ID
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }
        // Этот способ мы рассматривали в теории в теме про композицию
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (id == product.getId()) {
                return product;
            }
        }
        return null;
    }

        public Product findByPrice(int price) {
        for (Product product : products) {
            if (price == product.getPrice()) {
                return product;
            }
        }
        return null;
    }

    public Product[] searchByTitle(String searchInput) {
        Product[] results = new Product[0];
        for (Product product : products) {
            if (matches(product, searchInput)) {
                Product[] tmp = new Product[results.length + 1];
                int i = 0;
                for (Product result : results) {
                    tmp[i] = result;
                    i++;
                }
                tmp[results.length] = product;
                results = tmp;
            }
        }
        return results;
    }

    public boolean matches(Product product, String searchInput) {
        return product.getTitle().toUpperCase().contains(searchInput.toUpperCase());
    }

}