package vanwalle.in.vanwale.model;

public class Van {
    public String cost,drivername,seats,vehicleno;
    public String place1,place2,place3,place4,place5,place6;

    public Van(String cost, String drivername,String place1, String place2, String place3, String place4, String place5, String place6, String seats, String vehicleno) {
        this.cost = cost;
        this.drivername = drivername;
        this.seats = seats;
        this.vehicleno = vehicleno;
        this.place1 = place1;
        this.place2 = place2;
        this.place3 = place3;
        this.place4 = place4;
        this.place5 = place5;
        this.place6 = place6;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public String getPlace3() {
        return place3;
    }

    public void setPlace3(String place3) {
        this.place3 = place3;
    }

    public String getPlace4() {
        return place4;
    }

    public void setPlace4(String place4) {
        this.place4 = place4;
    }

    public String getPlace5() {
        return place5;
    }

    public void setPlace5(String place5) {
        this.place5 = place5;
    }

    public String getPlace6() {
        return place6;
    }

    public void setPlace6(String place6) {
        this.place6 = place6;
    }

    public Van() {


    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }
}
