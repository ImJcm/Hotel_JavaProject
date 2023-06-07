import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private int totalFunds; // 호텔 보유 자산
    //객실 정보
    private List<HotelRoom> roomlist;   //객실 정보
    private List<Reservation> reservationlist;  //예약 정보

    public Hotel() {
        //객실 초기 정보 추가
        this.roomlist = new ArrayList<>() {{
            add(new HotelRoom("Stanard Room", 10000));
            add(new HotelRoom("Superior Room", 20000));
            add(new HotelRoom("Deluxe Room", 30000));
            add(new HotelRoom("Executive Room", 40000));
            add(new HotelRoom("Suite Room", 50000));
        }};   

        //예약 정보 배열생성
        this.reservationlist = new ArrayList<>();
    }

    public List<HotelRoom> getroomlist() {
        return this.roomlist;
    }

    public List<Reservation> getreservationlist() {
        return this.reservationlist;
    }

    public int getTotalfunds() {
        return this.totalFunds;
    }

    public void setTotalfunds(int funds) {
        this.totalFunds = funds;
    }
}
