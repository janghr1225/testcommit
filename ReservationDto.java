package reservation;

import java.sql.Timestamp;

//Dto(Data Transcation Object, �����ͺ��̽��� �ʵ带 �ڹٿ��� �а� ���� �ʵ带 �����ϴ� Ŭ����)
public class ReservationDto {
	
	int memberID;
	int reservationID;
	String memberName;
	String nextReserve;
	
	
	public int getReservationID() {
		return reservationID;
	}
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getNextReserve() {
		return nextReserve;
	}
	public void setNextReserve(String nextReserve) {
		this.nextReserve = nextReserve;
	}
	
	
	
	
}
