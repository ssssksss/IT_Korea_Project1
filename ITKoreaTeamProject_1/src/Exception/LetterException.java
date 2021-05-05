package Exception;

public class LetterException extends Exception{
	//객체간 직렬화 통신하기 위해 serialVersionUID를 사용하여 통신이 정상적인 판단
	private static final long serialVersionUID = 1L;
	public LetterException(String message){
		super(message);
	}
}
