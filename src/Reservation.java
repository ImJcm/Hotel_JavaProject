import java.time.OffsetDateTime;
import java.util.UUID;

public class Reservation {
    private UUID id;    //예약 번호
    private Customer customer; //고객
    private Room room; // 예약할 방
    private OffsetDateTime date; //예약 날짜, ISO 8601형식 UTC 날짜 및 시간, Asia/Seoul로 지정


    public Reservation(Customer cutomer, Room room, OffsetDateTime date) {
        this.id = UUID.randomUUID();
        this.customer = cutomer;
        this.room = room;
        this.date = date;
    }

    public UUID getUuid() {
        return this.id;
    }

    public Customer getCutomer() {
        return this.customer;
    }
    
    public Room getRoom() {
        return this.room;
    }

    public OffsetDateTime getDate() {
        return this.date;
    }
}
