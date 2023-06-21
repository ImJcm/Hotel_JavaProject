import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private int totalFunds; // 호텔 보유 자산
    //객실 정보
    private List<Room> roomlist;   //객실 정보
    private List<Reservation> reservationlist;  //예약 정보
    private List<Customer> customerlist;    //등록 고객 정보

    public Hotel() {
        //객실 초기 정보 추가
        this.roomlist = new ArrayList<>() {{
            add(new Room("Stanard Room", 10000,false));
            add(new Room("Superior Room", 20000,false));
            add(new Room("Deluxe Room", 30000,false));
            add(new Room("Executive Room", 40000,false));
            add(new Room("Suite Room", 50000,false));
        }};   

        //예약 정보 배열생성
        this.reservationlist = new ArrayList<>();
        this.customerlist = new ArrayList<>();
    }

    public List<Room> getroomlist() {
        return this.roomlist;
    }

    public List<Reservation> getreservationlist() {
        return this.reservationlist;
    }

    public List<Customer> getcustomerlist() { return this.customerlist;}

    public int getTotalfunds() {
        return this.totalFunds;
    }

    public void setTotalfunds(int funds) {
        this.totalFunds = funds;
    }

} //
