package yang.model;

public class Orderlist {
    private Integer oid;

    private String oname;

    private String oaddress;

    private String ophone;

    private Integer uid;

    private String opay;

    private Integer odate;

    private String ostat;

    private Integer paypri;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname == null ? null : oname.trim();
    }

    public String getOaddress() {
        return oaddress;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress == null ? null : oaddress.trim();
    }

    public String getOphone() {
        return ophone;
    }

    public void setOphone(String ophone) {
        this.ophone = ophone == null ? null : ophone.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOpay() {
        return opay;
    }

    public void setOpay(String opay) {
        this.opay = opay == null ? null : opay.trim();
    }

    public Integer getOdate() {
        return odate;
    }

    public void setOdate(Integer odate) {
        this.odate = odate;
    }

    public String getOstat() {
        return ostat;
    }

    public void setOstat(String ostat) {
        this.ostat = ostat == null ? null : ostat.trim();
    }

    public Integer getPaypri() {
        return paypri;
    }

    public void setPaypri(Integer paypri) {
        this.paypri = paypri;
    }
}