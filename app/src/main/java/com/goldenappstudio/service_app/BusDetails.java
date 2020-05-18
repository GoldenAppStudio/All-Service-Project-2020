package studio.goldenapp.serviceapp;

public class BusDetails {

    private String name;
    private String fair;
    private String start;
    private String end;
    private String dname;
    private String dno;
    private String cname;
    private String cno;

    private String uid;

    public BusDetails() {
        //empty constructor needed
    }

    public BusDetails(String name, String fair, String uid, String start, String end, String dname, String dno, String cname, String cno) {
        this.name = name;
        this.fair = fair;
        this.uid = uid;
        this.start = start;
        this.end = end;
        this.dname = dname;
        this.dno = dno;
        this.cname = cname;
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getFair() {
        return fair;
    }

    public String getStart() { return start; }

    public String getEnd() { return end; }
    public String getDname() { return dname; }
    public String getDno() { return dno; }
    public String getCname() { return cname; }
    public String getCno() { return cno; }

}


