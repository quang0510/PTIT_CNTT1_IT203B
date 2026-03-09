package Session_02;

public class Bai3 {
    public static class Account implements Authenticatable {
        String password;
        public Account(String password) {
            this.password = password;
        }

        @Override
        public String getPassword() {
            return password;
        }

        public static void encrypt(String rawPassword){
            System.out.println("Mã hoá mật khẩu : "+rawPassword);
        }
    }

    public static void main(String[] args) {
        Account a = new Account("123");
        Account b = new Account("");

        if(a.isAuthenticated()){
            Account.encrypt("123");
        }else{
            System.out.println("Mật khẩu không được bỏ trống");
        }

        if(b.isAuthenticated()){
            Account.encrypt("123");
        }else{
            System.out.println("Mật khẩu không được bỏ trống");
        }
    }
}
