import java.sql.Date;
import java.time.LocalDate;

public class FamilyMember {
    private int id;
    private String fname;
    private String sname;
    private Date dateofbirth;

    public FamilyMember() {
    }

    public FamilyMember(int id, String fname, String sname, Date dateofbirth) {
        this.id = id;
        this.fname = fname;
        this.sname = sname;
        this.dateofbirth = dateofbirth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getSname() {
        return sname;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    @Override
    public String toString() {
        return "FamilyMember " + "| id | " + id + "| fname | " + fname + "| sname |" + sname + "| dateofbirth |"
                + dateofbirth +
                '}';
    }
}
