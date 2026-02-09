package model.dto;

public class ProductDto {
    private int id;
    private String name;
    private String description;
    private int price;
    private String seller;
    private String password;
    private String contact;
    private boolean isForSale;
    private String createdAt;

    public ProductDto() {
    }

    public ProductDto(int id, String name, String description, int price, String seller, String password, String contact, boolean isForSale, String createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.seller = seller;
        this.password = password;
        this.contact = contact;
        this.isForSale = isForSale;
        this.createdAt = createdAt;
    }

    public ProductDto(int id, String name, int price, String seller, String createdAt, boolean isForSale, String contact) { // read
        this.id = id;
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.createdAt = createdAt;
        this.isForSale = isForSale;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isForSale() {
        return isForSale;
    }

    public void setForSale(boolean forSale) {
        isForSale = forSale;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", seller='" + seller + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + contact + '\'' +
                ", isForSale=" + isForSale +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
