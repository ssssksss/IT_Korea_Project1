package Exception;

import java.util.regex.Pattern;

import Exception.LetterException;

//입력시 제한을 두기 위하여 작성하였다
//자바정규식 표현(pattern)을 사용하여 작성
//참고: 자바 정규표현식 표정리
//https://coding-factory.tistory.com/529
public class InputException {

	//아이디 확인
	public void idFormat(String str) throws LetterException{
      if(str.length()<5 || str.length()>15){

         throw new LetterException("5~15자 이내의 아이디만 가능합니다");
      }
      int cnt1=0;
      int cnt2=0;
      for(int i=0;i<str.length();i++){
         char ch = str.charAt(i);
         if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))
            cnt1++;
         else if(ch>='0' && ch<='9')
            cnt2++;
      }
      if(cnt1==0 || cnt2==0)
         throw new LetterException("아이디는 영문자와 숫자를 혼용해서 만들어주세요");   
   }


 //비밀번호 확인
   public void pwCheck(String pw1, String pw2) throws LetterException{

      int cnt1=0;
      int cnt2=0;
      for(int i=0;i<pw1.length();i++){
         char ch = pw1.charAt(i);
         if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))
            cnt1++;
         else if(ch>='0' && ch<='9')
            cnt2++;
      }
      if(cnt1==0 || cnt2==0)
         throw new LetterException("비밀번호는 영문자와 숫자를 혼용해서 만들어주세요");   
      if(!pw1.equals(pw2))
         throw new LetterException("비밀번호가 다릅니다");   

   }
	//이름 확인
	public void nameCheck(String name) throws LetterException {
	      boolean check = Pattern.matches("^[ㄱ-ㅎ가-힣]*$", name);
	      if (!check)
	         throw new LetterException("※이름은 한글로 입력해주세요");
   }

  // 전화번호 확인
   public void phoneCheck(String phone) throws LetterException {
      boolean check = Pattern.matches(
            "(010|011|016|017|018?019)-(\\d{3,4})-(\\d{4})", phone);
      if (!check)
         throw new LetterException("※전화번호 입력 형식은 [XXX-XXXX-XXXX]입니다");
   }   
}
