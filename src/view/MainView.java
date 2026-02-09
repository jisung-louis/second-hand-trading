package view;

import controller.ProductController;
import model.dto.ProductDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    private MainView(){}
    private static final MainView instance = new MainView();

    public static MainView getInstance() {
        return instance;
    }

    private ProductController pc = ProductController.getInstance();
    Scanner scan = new Scanner(System.in);

    public void index(){
        for(;;){
            System.out.println("======== 간편한 중고 물품 거래 플랫폼 ========");
            System.out.println("1. 제품 등록");
            System.out.println("2. 전체 물품 목록 조회");
            System.out.println("3. 물품 정보 수정");
            System.out.println("4. 등록 물품 삭제");
            System.out.println("5. 종료");
            System.out.print("선택 > ");
            try {
                int ch = scan.nextInt();
                if( ch == 1 ){ create(); }
                else if ( ch == 2 ) { read(); }
                else if ( ch == 3 ) { update(); }
                else if ( ch == 4 ) { delete(); }
                else if ( ch == 5 ) { System.out.println("프로그램을 종료합니다."); break; }
                else { System.out.println("잘못된 숫자를 입력했습니다."); System.out.println(); }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 숫자를 입력했습니다.");
                System.out.println();
                scan = new Scanner(System.in);
            }
        }
    }

    public void create(){
        System.out.println();
        System.out.println("---- 제품 등록 ----");
        try {
            System.out.print("판매자 닉네임 : ");
            scan.nextLine();
            String seller = scan.nextLine();
            System.out.print("물품명 : ");
            String name = scan.nextLine();
            System.out.print("설명 : ");
            String description = scan.nextLine();
            System.out.print("가격 : ");
            int price = scan.nextInt();
            System.out.print("비밀번호(등록용) : ");
            scan.nextLine();
            String password = scan.nextLine();
            System.out.print("연락처 : ");
            String contact = scan.nextLine();

            boolean result = pc.create(seller, name, description, price, password, contact);
            System.out.println(result ? "[안내] 제품 등록에 성공했습니다" : "[안내] 제품 등록에 실패했습니다.");
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력값을 입력했습니다.");
            System.out.println();
            scan = new Scanner(System.in);
        }
    }
    public void read(){
        System.out.println();
        System.out.println("---- 전체 물품 목록 조회 ----");

        ArrayList<ProductDto> products = pc.readAll();
        System.out.println("| 물품번호 | 물품명 | 가격 | 닉네임 | 등록일 | 판매여부 | 연락처 |");
        for (ProductDto product : products) {
            int id = product.getId();
            String name = product.getName();
            int price = product.getPrice();
            String seller = product.getSeller();
            String createdAt = product.getCreatedAt();
            String isForSale = product.isForSale() ? "판매 중" : "판매 완료";
            String contact = product.getContact();
            System.out.printf("| %d | %s | %d | %s | %s | %s | %s |\n", id, name, price, seller, createdAt, isForSale, contact);
        }
        System.out.println();
    }
    public void update(){
        System.out.println();
        System.out.println("---- 물품 정보 수정 ----");
        try {
            System.out.print("물품 번호 : ");
            int id = scan.nextInt();
            if(!pc.isExist(id)){
                System.out.printf("[안내] %d번 물품은 존재하지 않습니다.\n\n", id);
                return;
            }

            System.out.print("비밀번호 : ");
            scan.nextLine();
            String password = scan.nextLine();
            if(!pc.verifyPassword(id,password)){
                System.out.println("[안내] 비밀번호가 틀렸습니다.");
                System.out.println();
                return;
            }

            System.out.print("수정할 물품명 : ");
            String name = scan.nextLine();
            System.out.print("수정할 설명 : ");
            String description = scan.nextLine();
            System.out.print("수정할 가격 : ");
            int price = scan.nextInt();
            System.out.print("수정할 연락처 : ");
            scan.nextLine();
            String contact = scan.nextLine();
            System.out.print("판매 여부 (y/n) : ");
            boolean isForSale = scan.next().equals("y");

            boolean result = pc.update(id, name, description, price, contact, isForSale);
            System.out.println(result ? "[안내] 제품 수정에 성공했습니다." : "[안내] 제품 수정에 실패했습니다.");
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력값을 입력했습니다.");
            System.out.println();
            scan = new Scanner(System.in);
        }
    }
    public void delete(){
        System.out.println();
        System.out.println("---- 등록 물품 삭제 ----");
        try {
            System.out.print("물품 번호 : ");
            int id = scan.nextInt();
            if(!pc.isExist(id)){
                System.out.printf("[안내] %d번 물품은 존재하지 않습니다.\n\n", id);
                return;
            }
            System.out.print("비밀번호 : ");
            scan.nextLine();
            String password = scan.nextLine();
            if(!pc.verifyPassword(id,password)){
                System.out.println("[안내] 비밀번호가 틀렸습니다.");
                System.out.println();
                return;
            }

            boolean result = pc.delete(id);
            System.out.println(result ? "[안내] 제품 삭제에 성공했습니다." : "[안내] 제품 삭제에 실패했습니다.");
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력값을 입력했습니다.");
            System.out.println();
            scan = new Scanner(System.in);
        }
    }
}
