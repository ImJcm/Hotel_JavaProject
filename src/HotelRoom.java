public class HotelRoom {
    // 객실은 크기, 숙박비를 가진다.
    private String roomSize; // 방 크기
    private int roomCharge; // 숙박비

    public HotelRoom(String roomsize, int roomcharge) {
        this.roomSize = roomsize;
        this.roomCharge = roomcharge;
    }

    public String getroomsize() {
        return this.roomSize;
    }

    public int getroomcharge() {
        return this.roomCharge;
    }
}
