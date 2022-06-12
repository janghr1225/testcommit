package pet_management;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import member.MemberDao;
import member.MemberDto;
import reservation.ReservationDao;
import reservation.ReservationDto;

public class ReservationPage extends JFrame {	// 예약관리페이지

	List list;
	ArrayList<String> listCombo;

	public ReservationPage() {
		
		JPanel jp = new JPanel();
		
		Label l1 = new Label("예약 관리 페이지", SwingConstants.CENTER);
		Label l2 = new Label("회원 아이디");
		Label l3 = new Label("예약 아이디");
		Label l4 = new Label("동물 이름");
		Label l5 = new Label("예약 일자");
		add(l1); 
		add(l2); 
		add(l3);
		add(l4); 
		add(l5);
		
		TextField t1 = new TextField(); // 회원 아이디 회원목록보여주기
		
		listCombo = new ArrayList<String>();
		selectCombo(); 
		JComboBox<?> jcmemberID = new JComboBox(listCombo.toArray(new String [listCombo.size()]));
		TextField t2 = new TextField(); // 예약 아이디
		TextField t3 = new TextField(); // 동물 이름
		TextField t4 = new TextField(); // 예약 일자
		add(jcmemberID); 
		add(t2); 
		add(t3); 
		add(t4);
		
		t2.setEnabled(false);// 회원아이디'수정막기'인데 이거 아닌 거 같음
		t3.setEnabled(false);
		
		Font font1 = new Font("맑은 고딕", Font.BOLD, 25);
		l1.setFont(font1);
		
		JButton btnSave = new JButton("신규");
		JButton btnUpdate = new JButton("수정");
		JButton btnDelete = new JButton("삭제");
		JButton btnSearch = new JButton("조회");
		JButton btnReset = new JButton("지우기");
		JButton btnBack = new JButton("뒤로가기");
		add(btnSave);
		add(btnUpdate);
		add(btnDelete);
		add(btnSearch);
		add(btnReset);
		add(btnBack);
		
		list = new List();
		add(list);
		Font font2 = new Font("맑은 고딕", 0, 15);
		list.setFont(font2);
		
		l1.setBounds(300, 30, 200, 40); // 예약 관리 페이지 
		
		l2.setBounds(350, 100, 70, 30); // 회원 아이디
		l3.setBounds(530, 100, 70, 30); // 예약 아이디
		l4.setBounds(350, 130, 70, 30); // 동물 이름
		l5.setBounds(530, 130, 70, 30); // 예약 일자
		
		jcmemberID.setBounds(430, 100, 80, 30);
		t2.setBounds(600, 100, 100, 30);
		t3.setBounds(430, 130, 80, 30);
		t4.setBounds(600, 130, 100, 30);
		
		btnSave.setBounds(350, 200, 75, 30);
		btnUpdate.setBounds(430, 200, 75, 30);
		btnDelete.setBounds(510, 200, 75, 30);
		btnSearch.setBounds(590, 200, 75, 30);
		btnReset.setBounds(670, 200, 75, 30);
		btnBack.setBounds(660, 700, 90, 30);
		
		list.setBounds(50, 260, 700, 400);
		
		add(jp);
		
		setSize(800, 790);
		setTitle("예약관리화면");
		setResizable(false);
		Dimension frameSize = jp.getSize();
		
		// 모니터 크기
		setResizable(false);
		setLocationRelativeTo(jp);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jp.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		//displayAll();
		
		// 리스트 아이템 선택 시 텍스트 필드로 값 넣기
		list.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String str = list.getSelectedItem();
				StringTokenizer st = new StringTokenizer(str);
				t1.setText(st.nextToken());
				t2.setText(st.nextToken());
				t3.setText(st.nextToken());
				t4.setText(st.nextToken());
				//t5.setText(st.nextToken());
			}
		});
		
		// 신규등록
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				String str = jcmemberID.getSelectedItem().toString();
				if(str=="전체") return;
				int memberID = Integer.parseInt(str);
				int reservationID = Integer.parseInt(t2.getText());
				String memberName = t3.getText();
				String nextReserve = t4.getText();
				//String hostName = t5.getText();

				ReservationDao dao = new ReservationDao();
				dao.insertReserve(reservationID, memberID, memberName, nextReserve);
				JOptionPane.showMessageDialog(null, "저장 완료");
				displayAll(memberID);
				
			}
		});
		
		
		// 수정하기
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = jcmemberID.getSelectedItem().toString();
				if(str=="전체") return;
				int memberID = Integer.parseInt(str);
				int reservationID = Integer.parseInt(t2.getText());
				String nextReserve = t4.getText();
				//String hostName = t5.getText();

				ReservationDao dao = new ReservationDao();
				dao.updateReserve(reservationID, memberID, nextReserve);
				JOptionPane.showMessageDialog(null, "수정 완료");
				
				displayAll(memberID);
			}
		});
		
		
		// 삭제하기
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = jcmemberID.getSelectedItem().toString();
				if(str=="전체") return;
				int memberID = Integer.parseInt(str);
				int reservationID = Integer.parseInt(t2.getText());
				ReservationDao dao = new ReservationDao();
				dao.deleteReserve(memberID,reservationID);
				JOptionPane.showMessageDialog(null, "삭제 완료");
				displayAll(memberID);
			}
		});
		 
		// 조회하기
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = jcmemberID.getSelectedItem().toString();//t1.getText();
				if(str=="전체") str ="0";
				int memberID = Integer.parseInt(str);
				ReservationDao dao = new ReservationDao();
				ReservationDto dto = dao.searchMemberName(memberID);
				t3.setText(dto.getMemberName());				//동물이름
				
				displayAll(memberID);
				
			}
		}); 
		
		// 뒤로가기(메뉴)
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuPage managerPage = new MenuPage();
            }
        });
		
		// 지우기
        btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				//t5.setText("");
			}
		});
	}
	

	// 회원아이디 콤보
    private void selectCombo() {
    	listCombo.clear();
    	listCombo.add("전체");
    	
    	ReservationDao dao = new ReservationDao();

    	ArrayList<ReservationDto> allData = dao.selectMemberID();
    	for(ReservationDto dto : allData) {
    		int memberID = dto.getMemberID();
    		listCombo.add(memberID+"");
    		//System.out.println(listCombo);
    	}
    	
    }	
	
	// 화면 출력
    private void displayAll(int memberID) {
    	list.removeAll();
    	ReservationDao dao = new ReservationDao();
    	ArrayList<ReservationDto> allData = dao.selectListReserve(memberID);
    	for(ReservationDto dto : allData) {
    		int memberID2 = dto.getMemberID();
    		int reservationID = dto.getReservationID();
    		String memberName = dto.getMemberName();
    		String nextReserve = dto.getNextReserve();
    		list.add(memberID2+"              "
    			+reservationID+"            "
    			+memberName+"         "
    			+nextReserve+"                  "		
    		);
    		
    	}
    	
    }

}
