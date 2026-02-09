package controller;

import model.dao.ProductDao;
import model.dto.ProductDto;

import java.util.ArrayList;

public class ProductController {
    private ProductController(){}
    private static final ProductController instance = new ProductController();

    public static ProductController getInstance() {
        return instance;
    }

    private ProductDao pd = ProductDao.getInstance();

    public boolean create(String seller, String name, String description, int price, String password, String contact){
        return pd.create(seller,name,description,price,password,contact);
    }
    public ArrayList<ProductDto> readAll(){
        return pd.readAll();
    }
    public boolean update(int id, String name, String description, int price, String contact, boolean isForSale){
        return pd.update(id,name,description,price,contact,isForSale); // 수정 성공 : 1, 수정 실패 : 0
    }
    public boolean delete(int id){
        return pd.delete(id);
    }
    public boolean isExist(int id){
        return pd.findById(id) != null;
    }
    public boolean verifyPassword(int id, String password){
        ProductDto product = pd.findById(id);
        return product.getPassword().equals(password);
    }
}
