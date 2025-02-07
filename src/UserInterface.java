import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public class UserInterface {
    private static final String pattern = "(\\d{3})(\\d{4})(\\d{4})";  //전화번호 정규식 : XXX-XXXX-XXXX
    static Hotel hotel = new Hotel();
    static Customer customer;
    private static Scanner sc = new Scanner(System.in);

    public static void SelectDisplay() throws Exception{
        boolean isProcessEnded = true;
        while(isProcessEnded) {
            System.out.println("환영합니다. 오잉호텔입니다.");
            System.out.println("원하는 작업을 선택해주세요.");
            System.out.println("================================================================");
            System.out.println("1. 고객 메뉴 \t 2.관리자 메뉴 \t 3. 종료");
            System.out.println("================================================================");
            System.out.print("여기에 번호를 입력해주세요 => ");
            int menuClick = sc.nextInt();
            switch(menuClick) {
                case 1:
                    printClientDisplay();
                    break;
                case 2:
                    printAdminDisplay();
                    break;
                case 3:
                    isProcessEnded = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시입력해주세요.");
                    break;
            }
        }
    }

    public static void printClientDisplay() throws Exception {
        boolean isProcessEnded = true;

        while (isProcessEnded) {
            System.out.println("\n고객님 환영합니다. 오잉호텔입니다.");
            System.out.println("원하시는 기능의 번호를 입력해주세요.");
            System.out.println("================================================================");
            System.out.println("1. 호텔 예약");
            System.out.println("2. 예약 조회");
            System.out.println("3. 예약 취소");
            System.out.println("4. 종료");
            System.out.println("================================================================");
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
                    cancelReservation();
                    break;
                case 4 :
                    customer = null;    //고객 메뉴 종료 시, 고객 정보 초기화
                    isProcessEnded = false;      //시스템 종료
                    break;
            } // switch 끝
        } //while 끝
    } // method 끝
    
    public static void printAdminDisplay() {
        boolean isProcessEnded = true;
        while(isProcessEnded) {
            System.out.println("\n관리자님 환영합니다. 오잉호텔입니다.");
            System.out.println("원하시는 기능의 번호를 입력해주세요.");
            System.out.println("================================================================");
            System.out.println("1. 예약 조회");
            System.out.println("2. 종료");
            System.out.println("================================================================");
            System.out.print("여기에 번호를 입력해주세요 => ");
            int selectNumber = Integer.parseInt(sc.nextLine());

            switch (selectNumber) {
                case 1 :
                    //호텔 측 예약 조회
                    printReservationlist();
                    break;
                case 2 :
                    isProcessEnded = false; //시스템 종료
                    break;
            } 
        }
    }

    //Client
    /*
     * 예약 시, 고객 정보 등록
     * 등록한 고객이름과 전화번호가 동일한 고객이 있다면 해당 고객을 반환하고, 등록하지않은 고객일 경우 새로운 Customer 객체 반환
     */
    private static Customer registcustomer() {
        System.out.println("환영합니다. 오잉호텔입니다. 고객 정보를 등록합니다.");
        System.out.print("이름을 입력해주세요 : ");
        String name = sc.nextLine();

        String phone;
        do {
            System.out.print("전화번호를 입력해주세요 : ");
            phone = sc.nextLine();
        } while(!checkPhoneNumber(phone));
        phone =  phone.replaceAll(pattern,"$1-$2-$3");

        System.out.print("소지금을 입력해주세요 : ");
        int money = Integer.parseInt(sc.nextLine());

        if(hasCutomer(name,phone)) {
            System.out.println("이미 등록한 고객입니다.");
            System.out.println("소지금을 추가합니다.\n");
            String finalPhone = phone;
            Customer cutomer = hotel.getcustomerlist().stream().filter((c) -> c.getName().equals(name) && c.getPhoneNumber().equals(finalPhone)).toList().get(0);
            cutomer.setMoney(cutomer.getMoney() + money);
            return cutomer;
        } else {
            Long nextId;
            if(hotel.getcustomerlist().size() == 0) {
                nextId = 0L;
            } else {
                nextId = hotel.getcustomerlist().stream().max(Comparator.comparing(Customer::getId)).get().getId() + 1;
            }
            Customer newCutomer = new Customer(nextId,name, phone, money);
            hotel.getcustomerlist().add(newCutomer);
            return newCutomer;
        }
    }
    private static boolean hasCutomer(String username, String phonenumber) {
        if(hotel.getcustomerlist().stream().filter((Customer c) -> c.getName().equals(username) && c.getPhoneNumber().equals(phonenumber)).toList().size() > 0) {
            return true;
        }
        return false;
    }
    private static boolean checkPhoneNumber(String phonenumber) {
        if(Pattern.matches(pattern, phonenumber)) {
            return true;
        } else {
            System.out.println("올바른 전화번호가 아닙니다. 다시입력해주세요.");
            return false;
        }
    }

    /*private static String checkPhoneNumber() {
        Scanner sc = new Scanner(System.in);
        String pattern = "(\\d{3})(\\d{4})(\\d{4})";  //전화번호 정규식 : XXX-XXXX-XXXX
        boolean chk = false;
        String pNum = "";

        while(!chk) {
            pNum = sc.nextLine();
            if(Pattern.matches(pattern, pNum)) {
                chk = true;
            } else {
                System.out.println("올바른 전화번호가 아닙니다. 다시입력해주세요.");
                System.out.print("전화번호를 입력해주세요 : ");
            }
        }
        return pNum.replaceAll(pattern,"$1-$2-$3");
    }*/

    /*
     * 등록된 고객 정보로 호텔 예약
     */
    private static void reservate() throws Exception{
        boolean isduplicated = true;

        //고객 등록 시, 고객 존재 여부도 검사
        customer = registcustomer();
        // -------------------------------------------------------------
        //고객은 하루 한번의 예약만 가능 = 현재 고객정보가 예약 정보에 있다면 예약 기능 사용 불가
        //현재는 cutomer 객체의 주소값을 비교하여 예약정보가 있다면 안되는 방식으로 했지만, Hotel객체에 Customer 정보가 담긴 List가 생긴다면,
        //List에 등록 시, Customer 객체의 정보를 받아오기 때문에 주소값을 비교해도 동일한 기능을 할것으로 생각된다.
        // ---------------------------------------------------------------
        //호텔 예약 시 고객정보를 등록하는 방법을 이용하기로 했기때문에, 고객은 하루에 한번만 예약이 가능하다는 조건을 만족하기 위해
        //같은 고객인지 검사하는 방법을 객체의 주소가 아닌 전화번호(unique)로 예약 목록에 같은 전화번호가 있는지 검사한다.
        // --------------------------------------------------------------
        //등록된 고객인지 아닌지는 고객의 이름과 휴대전화를 통해 registcutomer()에서 검사하고, 아래 조건문을 통해 이미 등록된 고객이면 해당 고객 객체를 반환하고
        //새로운 고객이면 새로운 고객 객체를 반환하여 hotel 측에 저장된 예약 목록에 해당 고객이 예약한 고객의 휴대전화 정보가 있는지 검사한다.
        //휴대전화로 검사한 이유는 유일한 값이기 때문에 가능하다고 판단했다.
        if(hotel.getreservationlist().stream().filter((Reservation r) -> r.getCutomer().getPhoneNumber().equals(customer.getPhoneNumber())).toList().size() > 0) {
            isduplicated = false;
        }

        if(!isduplicated) {
            System.out.println("\n이미 예약 정보가 존재합니다.");
            System.out.println("예약을 종료합니다.");
            System.out.println("2초후 고객메뉴로 돌아갑니다.....");
            Thread.sleep(2000);
            return;
        }

        System.out.println("[ 환영합니다. 오잉호텔입니다. 예약을 시작합니다. ] ");
        /*
         * 예약할 고객 정보 등록
         * 우선, 예약 수행 시, 고객정보를 입력받는 방식으로 구현
         * 만약, 기존의 고객정보가 존재한다는 형태면 Hotel 클래스에 Customer 객체를 담은 List가 존재할테니 해당 객체에서 고객 리스트를
         * 출력하고 자신의 고객번호를 입력하고 예약을 수행하면 되지만 이러한 방법은 고객의 정보에 본인을 식별할 수 있는 정보가 필요하기 때문에
         * 요구사항에 요구되지 않는 기능을 고려하지 않아서 고객정보를 입력받는 방식으로 구현하였다.
         */
        
        /*
         * 출력된 객실 번호 외에 다른 번호를 입력했을 때 다시 번호를 요구하는 과정에서 reservate() 함수를 재호출하고 싶지만, 
         * 재호출하게되면, 고객정보를 등록하는 함수또한 실행되기 때문에 재호출하는 방식을 사용하려면 userDisplay()를 호출할 때, 고객정보를
         * 등록하는 형태로 변경해야한다.
         */
        boolean chk = true;
        while(chk) {
            //호텔의 객실 리스트 출력
            System.out.println("[ 호텔 객실 리스트 ]");
            hotel.getroomlist().stream().forEach((Room hr) -> {
                if(!hr.getisReserved()) {
                    System.out.println(String.format("%2d | %-15s | %d | 예약가능",hotel.getroomlist().indexOf(hr)+1,hr.getroomsize(),hr.getroomcharge()));
                } else {
                    System.out.println(String.format("\u001B[31m%2d | %-15s | %d | 예약 불가능\u001b[0m ",hotel.getroomlist().indexOf(hr)+1,hr.getroomsize(),hr.getroomcharge()));
                }
            });
            
            //예약을 위한 객실 번호 입력
            int roomNum = Integer.parseInt(sc.nextLine());
            if(roomNum > 0 && roomNum <= hotel.getroomlist().size()) {
                if (customer.getMoney() < hotel.getroomlist().get(roomNum-1).getroomcharge()) {
                    System.out.println("소지금이 부족합니다. 다른 방을 선택해주세요.");
                } else if (hotel.getroomlist().get(roomNum-1).getisReserved()) {
                    System.out.println("이미 예약이 된 방입니다. 다른 방을 선택해주세요.");
                } else {
                    Reservation newReservation = new Reservation(customer, hotel.getroomlist().get(roomNum-1), OffsetDateTime.now(ZoneId.of("Asia/Seoul")));
                    hotel.getroomlist().get(roomNum-1).setisReserved(true);
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
                    Thread.sleep(2000); //잘못될경우 삭제함
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
            System.out.println("예약순번 : " + (hotel.getreservationlist().indexOf(r)+1));
            System.out.println("예약번호 : " + r.getUuid());
            System.out.println("객실 : " + r.getRoom().getroomsize());
            System.out.println("고객이름 : " + r.getCutomer().getName());
            System.out.println("고객 전화번호 : " + r.getCutomer().getPhoneNumber());
            System.out.println("예약날짜 : " + r.getDate());
            System.out.println();   //예약 리스트 구분 공백
        });
        System.out.println("=======================================================");
    }

    private static void printReservationlist_Customer() throws InterruptedException {   //손님측에서의 예약조회
        boolean nonHaveCheck = false;
        System.out.println("========================예약조회========================");
        System.out.print("예약번호를 입력해주세요 => ");
        String input = sc.nextLine();
        for (int i = 0; i < hotel.getreservationlist().size(); i++) {
            if ((hotel.getreservationlist().get(i).getUuid() + "").equals(input)) {
                System.out.println("고객님 성함: " + hotel.getreservationlist().get(i).getCutomer().getName());
                System.out.println("호텔방: " + hotel.getreservationlist().get(i).getRoom().getroomsize()) ;
                System.out.println("예약 일자: " + hotel.getreservationlist().get(i).getDate() );
//                System.out.println("예약 번호: " + hotel.getreservationlist().get(i).getUuid() );
//                System.out.println("보유 금액: " + hotel.getreservationlist().get(i).getCutomer().getMoney());
//                (보유 금액)과 (예약 번호)는 표시하지 않는것이 좋아보여서 표시하지 않았습니다.
                nonHaveCheck = true;
            }
        }
        if (nonHaveCheck == false) {
            System.out.println("예약 번호를 잘못 입력하셨습니다.");
        }

        Thread.sleep(2000);
    }
    private static void cancelReservation() throws InterruptedException {
        boolean exist = true;
        while(exist) {
            // 1. 예약 취소 화면 출력
            System.out.println("================================================================");
            System.out.println("예약 취소를 선택하셨습니다.");
            System.out.println("예약 번호를 조회합니다. 예약 번호를 입력해주세요");
            System.out.print("여기에 예약번호를 입력해주세요 => ");
            UUID cancelID = UUID.fromString(sc.next());
            System.out.print("여기에 예약하신 전화번호 뒷 자리를 입력해주세요 => ");
            String phoneNum = sc.next();

            // 2. 예약 번호 조회
            for (int i = 0;  i < hotel.getreservationlist().size(); i++) { // 예약 번호와 전화 번호가 맞는 경우
                String phone = hotel.getreservationlist().get(i).getCutomer().getPhoneNumber().substring(9);
                if(cancelID.equals(hotel.getreservationlist().get(i).getUuid()) && phoneNum.equals(phone)) {
                    exist = false;
                    System.out.println("================================================================");
                    System.out.println("고객 이름    : " + hotel.getreservationlist().get(i).getCutomer().getName());
                    System.out.println("고객 번호    : " + hotel.getreservationlist().get(i).getCutomer().getPhoneNumber());
                    System.out.println("객실        : " + hotel.getreservationlist().get(i).getRoom().getroomsize());
                    System.out.println("예약 날짜    : " + hotel.getreservationlist().get(i).getDate());
                    System.out.println("================================================================");
                    System.out.println("위의 예약을 취소하시겠습니까?"); // 3. 예약 취소 확인
                    System.out.println("\n1. 예.         2. 아니오.");
                    System.out.print("여기에 번호를 입력해주세요 => ");

                    int a = sc.nextInt();
                    switch (a) {
                        case 1 -> {   // 4. 예약 취소
                            // 취소 금액 반환
                            hotel.getreservationlist().get(i).getCutomer().setMoney(hotel.getreservationlist().get(i).getCutomer().getMoney() + hotel.getreservationlist().get(i).getRoom().getroomcharge());
                            System.out.println(hotel.getreservationlist().get(i).getCutomer().getName() + "고객님의 총 보유금액 : " + hotel.getreservationlist().get(i).getCutomer().getMoney());
                            // 취소 방 반환
                            hotel.getreservationlist().get(i).getRoom().setisReserved(false);

                            System.out.println("================================================================");
                            System.out.println("해당 예약을 취소했습니다.");
                            System.out.println("2초 후 초기 화면으로 돌아갑니다.");
                            hotel.getreservationlist().remove(i);
                            Thread.sleep(2000);
                        }
                        case 2 -> {
                            System.out.println("================================================================");
                            System.out.println("2초 후 이전 화면으로 돌아갑니다.");
                            Thread.sleep(2000);
                        }
                    }
                }
            }
            if(exist) {
                System.out.println("================================================================");
                System.out.println("입력하신 예약번호 또는 전화번호 뒷 자리가 다릅니다.");
                System.out.println("2초 후 이전 화면으로 돌아갑니다.");
                Thread.sleep(2000);
            }
        }
    }
} //class 끝
