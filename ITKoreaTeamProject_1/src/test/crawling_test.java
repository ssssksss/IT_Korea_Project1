package test;
////package DAO;
//// 
////import java.io.IOException;
////import java.util.Iterator;
////
////import org.jsoup.Jsoup;
////import org.jsoup.nodes.Document;
////import org.jsoup.nodes.Element;
////import org.jsoup.select.Elements;
////
//////import jdk.internal.misc.FileSystemOption;
//////Jsoup 라이브러리를 import한다
//// 
////public class crawling_test {
////    public static void main(String[] args) {
////        
////    	String url="http://www.anydding.com/";
////        Document doc=null;
////        
////        try {
////           doc=Jsoup.connect(url).get();
////        } catch (IOException e) {
////           e.printStackTrace();
////        }
////        
////        Elements element=doc.select("h5.adng-prod-title");
////        Elements element1=doc.select("h3.adng-prod-title");
////        Elements element2=doc.select("div.adng-prod-price");
////        Iterator<Element> itr=element.select("h5.adng-prod-title").iterator();
////        Iterator<Element> itr1=element.select("h3.adng-prod-title").iterator();
////        Iterator<Element> itr2=element2.select("div.adng-prod-price").iterator();
////        
////        while(itr.hasNext()) {
////        	System.out.println(itr.next().text());
////        }
////        while(itr1.hasNext()) {
////        	System.out.println(itr1.next().text());
////        }
////        while(itr2.hasNext()) {
////        	System.out.println(itr2.next().text());
////        }
////    }
////}
////
//
//package DAO;
//
//import java.util.Scanner;
//
//class LogIn { // 1.로그인
//   String ID;
//   private String PW;
//
//   public String getPW() {
//      return PW;
//   }
//
//   public void setPW(String pW) {
//      PW = pW;
//   }
//
//   setInfo s = new setInfo(ID, PW);
//
//   void idCheck() { // 아이디 확인
//      if (ID.equals(s.creID)) { // 회원가입할 때의 아이디와 같니? 응
//      } else { // 아니
//         System.out.println("아이디가 일치하지 않습니다.");
//         return;
//      }
//   }
//
//   void pwCheck() { // 비밀번호 확인
//      if (PW.equals(s.crePW)) { // 회원가입할 때의 비밀번호와 같니? 응
//      } else { // 아니
//         System.out.println("비밀번호가 일치하지 않습니다");
//         return;
//      }
//   }
//
//class setInfo { // 회원가입
//      String creID;   //아이디 생성
//      String crePW;   //비밀번호 생성
//      int phoneNum;   //폰번호
//      int age;      // 나이
//      String gender;   //성별
//      Scanner sc = new Scanner(System.in);
//
//      setInfo(String creID, String crePW) {
//         this.creID = creID;
//         this.crePW = crePW;
//      }
//      setInfo(int phoneNum){
//         this.phoneNum = phoneNum;
//      }
//      void info() {
//         System.out.print("아이디 입력 : ");
//         creID = sc.next();
//         System.out.print("비밀번호 입력 : ");
//         crePW = sc.next();
//         System.out.print("휴대폰 번호 입력 : ");
//         phoneNum = sc.nextInt();
//         System.out.print("나이 입력 : ");
//         age = sc.nextInt();
//         System.out.print("성별 선택 : M or W");
//         gender = sc.next();
//
//      }
//   }
//class findInfo{               //아이디/비밀번호 찾기
//   int getNum;               // 폰번호를 확인하기 위한 변수
//   Scanner sc =  new Scanner(System.in);
//   setInfo s = new setInfo(getNum);
//   
//   void find() {
//      System.out.print("휴대폰 번호를 입력하시오 : ");
//      getNum = sc.nextInt();
//      if(getNum==s.phoneNum) {
//         System.out.print("당신의 아이디: " + s.creID + "당신의 비밀번호 : " + s.crePW);
//      }else {
//         System.out.println("일치하는 정보가 없습니다.");
//         return ;
//      }
//   }
//}
//class resetInfo{
//   String oldPW;   //이전 비밀번호를 확인하는 변수
//   String newPW;   //새로운 비밀번호를 설정하는 변수
//   
//   setInfo s = new setInfo(ID,PW);
//   Scanner sc = new Scanner(System.in);
//   
//   void reset() {      //  아이디/비밀번호 변경
//      System.out.print("비밀번호 확인 : ");
//      oldPW = sc.next();
//      if(oldPW.equals(s.crePW)) {
//         System.out.print("새로 설정할 비밀번호를 입력하시오 : ");
//         newPW = sc.next();
//      }else {
//         System.out.println("기존의 비밀번호와 불일치합니다.");
//         return ;
//      }
//   }
//}
//
// public static void main(String[] args) {
//     setInfo set = new setInfo(ID, PW);
//     findInfo f = new findInfo();
//     resetInfo r = new resetInfo();
//     int select; // 옵션 중 하나를 선택 1,2,3,4 중 하나
// 
// System.out.println("1.로그인 2.아이디/비밀번호 찾기 3.비밀번호 변경 4.회원가입");
// Scanner sc = new Scanner(System.in);
// select = sc.nextInt();
// 
// 
// while (true) {
//    switch (select) {
//    case 1:
//       set.info();
//       break;
//    case 2:
//       f.find();
//       break;
//    case 3:
//       r.reset();
//       break;
//    case 4:
//       set.info();
//       break;
//    default:
//       System.out.println("잘못된 입력입니다.");
//               return;
//        }
//     }
//  }
//
//
