import java.util.Scanner;

public class UserDisplay {
    public static void userDisplay() {
        Scanner sc = new Scanner(System.in);
        boolean start = true;
        while (start) {
            System.out.println("환영합니다. 오잉호텔입니다.");
            System.out.println("원하시는 기능의 번호를 입력해주세요.");
            System.out.println("====================================");
            System.out.println("1. 호텔 예약");
            System.out.println("2. 예약 조회");
            System.out.println("3. 예약 취소");
            System.out.println("4. 관리자 메뉴");
            System.out.println("5. 시스템 종료");
            System.out.println("====================================");
            System.out.print("여기에 번호를 입력해주세요 => ");
            int selectNumber = sc.nextInt();

            switch (selectNumber) {
                case 1 :
                    // 호텔 예약 메소드 호출
                case 2 :
                    // 예약 조회 메소드 호출
                case 3 :
                    // 예약 취소 메소드 호출
                case 4 :
                    // 관리자 메뉴 메소드 호출
                case 5 :
                    start = false; //시스템 종료
            } // swutch 끝
        } //while 끝
    } // method 끝

    // 각각의 기능에 맞는 메소드를 여기서부터 구현하면 될 듯 합니다.

} //class 끝
