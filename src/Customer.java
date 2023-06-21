public class Customer {
    // 고객은 고객은 id, 이름, 전화번호, 소지금을 가진다.
    private Long id;
    private String name;
    private String phoneNumber;
    private int money;

    public Customer(Long id, String name, String phone, int money) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phone;
        this.money = money;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
} //
