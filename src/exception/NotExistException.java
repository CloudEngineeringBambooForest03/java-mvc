/* 프로젝트, 기부자(Activist), 대상자(Recipient)가 미 존재할 경우 발생
 * 
 *  @ Author 김 혜 경
 */
package exception;

public class NotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotExistException() {}

	public NotExistException(String message) {
		super(message);
	}
}
