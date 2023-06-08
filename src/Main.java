public class Main {
    public static void main(String[] args) throws Exception{
        UserInterface userInterface = new UserInterface();  //고객, 관리자 기능 인터페이스
        //userInterface.userDisplay(); // userdisplay 호출
        userInterface.userSelectDisplay(); //고객, 관리자 중 선택
    }
}