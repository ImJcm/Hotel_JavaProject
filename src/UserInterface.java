import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInterface {
    //Hotel 객체 생성
    static Hotel hotel = new Hotel();
    static Customer customer;

    public static void userSelectDisplay() {
        Scanner sc = new Scanner(System.in);
        boolean start = true;
        while(start) {
            System.out.println("환영합니다. 오잉호텔입니다.");
            System.out.println("원하는 작업을 선택해주세요.");
            System.out.println("====================================");
            System.out.println("1. 고객 메뉴 \t 2.관리자 메뉴 \t 3. 종료");
            System.out.println("====================================");

            int menuClick = Integer.parseInt(sc.nextLine());
            switch(menuClick) {
                case 1:
                    ClientDisplay();
                    break;
                case 2:
                    AdminDisplay();
                    break;
                case 3:
                    start = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시입력해주세요.");
                    break;
            }
        }
    }

    public static void ClientDisplay() {
        Scanner sc = new Scanner(System.in);
        boolean start = true;

        //여기서 고객 정보를 받아서 Client에 정보를 저장하면 다른 메뉴에 대한 기능을 수행하는데 수월하다고 생각함
        System.out.println("고객 정보를 등록합니다.");
        System.out.println("====================================");
        customer = registcustomer();

        while (start) {
            System.out.println("\n"+customer.getName() + "고객님 환영합니다. 오잉호텔입니다.");
            System.out.println("원하시는 기능의 번호를 입력해주세요.");
            System.out.println("====================================");
            System.out.println("1. 호텔 예약");
            System.out.println("2. 예약 조회");
            System.out.println("3. 예약 취소");
            System.out.println("4. 종료");
            System.out.println("====================================");
            System.out.print("여기에 번호를 입력해주세요 => ");
            int selectNumber = sc.nextInt();

            switch (selectNumber) {
                case 1 :
                    // 호텔 예약 메소드 호출
                    reservate();
                    break;
                case 2 :
                    // 예약 조회 메소드 호출
                    printReservationlist_Customer();
                    break;
                case 3 :
                    // 예약 취소 메소드 호출
                case 4 :
                    customer = null;    //고객 메뉴 종료 시, 고객 정보 초기화
                    start = false;      //시스템 종료
                    break;
            } // switch 끝
        } //while 끝
    } // method 끝
    
    public static void AdminDisplay() {
        Scanner sc = new Scanner(System.in);
        boolean start = true;
        while(start) {
            System.out.println("\n관리자님 환영합니다. 오잉호텔입니다.");
            System.out.println("원하시는 기능의 번호를 입력해주세요.");
            System.out.println("====================================");
            System.out.println("1. 예약 조회");
            System.out.println("2. 종료");
            System.out.println("====================================");
            System.out.print("여기에 번호를 입력해주세요 => ");
            int selectNumber = Integer.parseInt(sc.nextLine());

            switch (selectNumber) {
                case 1 :
                    //호텔 측 예약 조회
                    printReservationlist();
                    break;
                case 2 :
                    start = false; //시스템 종료
                    break;
            } 
        }
    }

    //Client
    /*
     * 예약 시, 고객 정보 등록
     */
    private static Customer registcustomer() {
        Scanner sc = new Scanner(System.in);
        //System.out.println("환영합니다. 오잉호텔입니다. 고객 정보를 등록합니다.");
        System.out.print("이름을 입력해주세요 : ");
        String name = sc.nextLine();

        System.out.print("전화번호를 입력해주세요 : ");
        String phone = checkPhoneNumber();

        System.out.print("소지금을 입력해주세요 : ");
        int money = Integer.parseInt(sc.nextLine());

        return new Customer(name, phone, money);
    }
    
    private static String checkPhoneNumber() {
        Scanner sc = new Scanner(System.in);
        String pattern = "(\\d{3})(\\d{4})(\\d{4})";  //전화번호 정규식 : XXX-XXXX-XXXX
        boolean chk = false;
        String pNum = "";

        while(!chk) {
            pNum = sc.nextLine();
            if(Pattern.matches(pattern, pNum)) {
                chk = true;
            } else {
                System.out.println("옳바른 전화번호가 아닙니다. 다시입력해주세요.");
                System.out.print("전화번호를 입력해주세요 : ");
            }
        }
        return pNum.replaceAll(pattern,"$1-$2-$3");
    }

    /*
     * 등록된 고객 정보로 호텔 예약
     */
    private static void reservate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[ 환영합니다. 오잉호텔입니다. 예약을 시작합니다. ] ");

        /*
         * 예약할 고객 정보 등록
         * 우선, 예약 수행 시, 고객정보를 입력받는 방식으로 구현
         * 만약, 기존의 고객정보가 존재한다는 형태면 Hotel 클래스에 Customer 객체를 담은 List가 존재할테니 해당 객체에서 고객 리스트를
         * 출력하고 자신의 고객번호를 입력하고 예약을 수행하면 되지만 이러한 방법은 고객의 정보에 본인을 식별할 수 있는 정보가 필요하기 때문에
         * 요구사항에 요구되지 않는 기능을 고려하지 않아서 고객정보를 입력받는 방식으로 구현하였다.
         */
        //Customer customer = registcustomer();
        
        /*
         * 출력된 객실 번호 외에 다른 번호를 입력했을 때 다시 번호를 요구하는 과정에서 reservate() 함수를 재호출하고 싶지만, 
         * 재호출하게되면, 고객정보를 등록하는 함수또한 실행되기 때문에 재호출하는 방식을 사용하려면 userDisplay()를 호출할 때, 고객정보를
         * 등록하는 형태로 변경해야한다.
         */
        boolean chk = true;
        while(chk) {
            //호텔의 객실 리스트 출력
            System.out.println("[ 호텔 객실 리스트 ]");
            hotel.getroomlist().stream().forEach((HotelRoom hr) -> {
                System.out.println(String.format("%2d | %-15s | %d",hotel.getroomlist().indexOf(hr)+1,hr.getroomsize(),hr.getroomcharge()));
            });
            
            //예약을 위한 객실 번호 입력
            int roomNum = Integer.parseInt(sc.nextLine());
            if(roomNum > 0 && roomNum <= hotel.getroomlist().size()) {
                if(customer.getMoney() < hotel.getroomlist().get(roomNum-1).getroomcharge()) {
                    System.out.println("소지금이 부족합니다. 다른 방을 선택해주세요.");
                } else {
                    Reservation newReservation = new Reservation(customer, hotel.getroomlist().get(roomNum-1), OffsetDateTime.now(ZoneId.of("Asia/Seoul")));
                    hotel.getreservationlist().add(newReservation);
                    System.out.println("예약에 성공하였습니다.");
                    System.out.println("예약번호 : " + newReservation.getUuid());
                    System.out.println("예약날짜 : " + newReservation.getDate());
                    //고객의 보유금액 차감
                    customer.setMoney(customer.getMoney() - hotel.getroomlist().get(roomNum-1).getroomcharge());
                    System.out.println("보유금액 : " + customer.getMoney());
                    //호텔 보유자금 예약 방 금액만큼 추가
                    hotel.setTotalfunds(hotel.getTotalfunds() + hotel.getroomlist().get(roomNum-1).getroomcharge());
                    chk = false;
                    System.out.println();   //console format clean
                }
            } else {
                System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
            }
        }
    }

    //Admin
    private static void printReservationlist() {        //호텔측에서의 예약조희
        System.out.println("=====================예약 리스트=======================");
        hotel.getreservationlist().forEach((Reservation r) -> {
            System.out.println("예약순번 : " + hotel.getreservationlist().indexOf(r)+1);
            System.out.println("예약번호 : " + r.getUuid());
            System.out.println("객실 : " + r.getHotelRoom().getroomsize());
            System.out.println("고객이름 : " + r.getCutomer().getName());
            System.out.println("고객 전화번호 : " + r.getCutomer().getPhoneNumber());
            System.out.println("예약날짜 : " + r.getDate());
            System.out.println();   //예약 리스트 구분 공백
        });
    }

    private static void printReservationlist_Customer() {   //손님측에서의 예약조회
        System.out.println("============"+ customer.getName() + "님의 예약정보====================");
        for (int i = 0; i < hotel.getreservationlist().size(); i++) {
            if (hotel.getreservationlist().get(i).getCutomer().getName().equals(customer.getName())) {
                System.out.println("보유 금액: " + hotel.getreservationlist().get(i).getCutomer().getMoney() );
                System.out.println("호텔방: " + hotel.getreservationlist().get(i).getHotelRoom().getroomsize()) ;
                System.out.println("예약 일자: " + hotel.getreservationlist().get(i).getDate() );
                System.out.println("예약 번호: " + hotel.getreservationlist().get(i).getUuid() );
            }
        }
    }


} //class 끝
