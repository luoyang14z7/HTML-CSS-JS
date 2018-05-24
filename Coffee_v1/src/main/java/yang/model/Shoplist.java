package yang.model;

public class Shoplist {
    private Integer sid;

    private String shopname;

    private String shopdes;

    private Integer shoppri;

    private String shopimg;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    public String getShopdes() {
        return shopdes;
    }

    public void setShopdes(String shopdes) {
        this.shopdes = shopdes == null ? null : shopdes.trim();
    }

    public Integer getShoppri() {
        return shoppri;
    }

    public void setShoppri(Integer shoppri) {
        this.shoppri = shoppri;
    }

    public String getShopimg() {
        return shopimg;
    }

    public void setShopimg(String shopimg) {
        this.shopimg = shopimg == null ? null : shopimg.trim();
    }
}