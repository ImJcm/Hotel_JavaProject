import java.awt.desktop.QuitStrategy;

public class Room {
    // 객실은 크기, 숙박비를 가진다.
    private String roomSize; // 방 크기
    private int roomCharge; // 숙박비
    private boolean isReserved = false; // 예약여부

    public Room(String roomsize, int roomcharge, boolean isReserved) {
        this.roomSize = roomsize;
        this.roomCharge = roomcharge;
        this.isReserved = isReserved;
    }

    public String getroomsize() {
        return this.roomSize;
    }

    public int getroomcharge() {
        return this.roomCharge;
    }

    public boolean getisReserved() {
        return this.isReserved;
    }

    public void setisReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }
} //
