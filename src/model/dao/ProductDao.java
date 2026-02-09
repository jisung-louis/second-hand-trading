package model.dao;

import model.dto.ProductDto;

import java.sql.*;
import java.util.ArrayList;

public class ProductDao {
    private ProductDao(){ connect(); }
    private static final ProductDao instance = new ProductDao();

    public static ProductDao getInstance() {
        return instance;
    }

    private String url = "jdbc:mysql://localhost:3306/second_hand_trading";
    private String user = "root";
    private String password = "1234";

    private Connection conn;

    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            System.out.printf("[경고] MySQL 드라이버를 못 찾았어요.\n%s\n",e);
        } catch (SQLException e) {
            System.out.printf("[경고] DB랑 통신하다가 문제가 생겼어요.\n%s\n",e);
        }
    }

    public boolean create(String seller, String name, String description, int price, String password, String contact){
        try {
            String sql = "insert into product(name,description,price,seller,password,contact) values (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, price);
            ps.setString(4, seller);
            ps.setString(5, password);
            ps.setString(6, contact);

            int count = ps.executeUpdate();
            if( count == 1) { return true; }
            else { return false; }
        } catch (SQLException e) {
            System.out.println("[시스템오류] SQL 문법 문제 발생 : " + e);
        }
        return false;
    }
    public ArrayList<ProductDto> readAll(){
        ArrayList<ProductDto> products = new ArrayList<>();
        try{
            String sql = "select * from product order by createdat";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String seller = rs.getString("seller");
                String createdAt = rs.getDate("createdat").toString();
                boolean isForSale = rs.getBoolean("isforsale");
                String contact = rs.getString("contact");
                ProductDto product = new ProductDto(id, name, price, seller, createdAt, isForSale, contact);
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("[시스템오류] SQL 문법 문제 발생 : " + e);
        }
        return products;
    }
    public boolean update(int id, String name, String description, int price, String contact, boolean isForSale){
        try {
            String sql = "update product set name = ?, description = ?, price = ?, contact = ?, isforsale = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, price);
            ps.setString(4, contact);
            ps.setBoolean(5, isForSale);
            ps.setInt(6, id);

            int count = ps.executeUpdate();
            if( count == 1) { return true; }
            else { return false; }
        } catch (SQLException e) {
            System.out.println("[시스템오류] SQL 문법 문제 발생 : " + e);
        }
        return false;
    }
    public boolean delete(int id){
        try {
            String sql = "delete from product where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            int count = ps.executeUpdate();
            if( count == 1) { return true; }
            else { return false; }
        } catch (SQLException e) {
            System.out.println("[시스템오류] SQL 문법 문제 발생 : " + e);
        }
        return false;
    }

    public ProductDto findById(int id){
        ProductDto product = new ProductDto();
        try {
            String sql = "select * from product where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String password = rs.getString("password");
                String seller = rs.getString("seller");
                String createdAt = rs.getDate("createdat").toString();
                boolean isForSale = rs.getBoolean("isforsale");
                String contact = rs.getString("contact");

                product.setName(name);
                product.setPrice(price);
                product.setPassword(password);
                product.setSeller(seller);
                product.setCreatedAt(createdAt);
                product.setForSale(isForSale);
                product.setContact(contact);

                return product;
            }

        } catch (SQLException e) {
            System.out.println("[시스템오류] SQL 문법 문제 발생 : " + e);
        }
        return null;
    }
}
