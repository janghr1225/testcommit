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

public class ReservationPage extends JFrame {	// �������������

	List list;
	ArrayList<String> listCombo;

	public ReservationPage() {
		
		JPanel jp = new JPanel();
		
		Label l1 = new Label("���� ���� ������", SwingConstants.CENTER);
		Label l2 = new Label("ȸ�� ���̵�");
		Label l3 = new Label("���� ���̵�");
		Label l4 = new Label("���� �̸�");
		Label l5 = new Label("���� ����");
		add(l1); 
		add(l2); 
		add(l3);
		add(l4); 
		add(l5);
		
		TextField t1 = new TextField(); // ȸ�� ���̵� ȸ����Ϻ����ֱ�
		
		listCombo = new ArrayList<String>();
		selectCombo(); 
		JComboBox<?> jcmemberID = new JComboBox(listCombo.toArray(new String [listCombo.size()]));
		TextField t2 = new TextField(); // ���� ���̵�
		TextField t3 = new TextField(); // ���� �̸�
		TextField t4 = new TextField(); // ���� ����
		add(jcmemberID); 
		add(t2); 
		add(t3); 
		add(t4);
		
		t2.setEnabled(false);// ȸ�����̵�'��������'�ε� �̰� �ƴ� �� ����
		t3.setEnabled(false);
		
		Font font1 = new Font("���� ���", Font.BOLD, 25);
		l1.setFont(font1);
		
		JButton btnSave = new JButton("�ű�");
		JButton btnUpdate = new JButton("����");
		JButton btnDelete = new JButton("����");
		JButton btnSearch = new JButton("��ȸ");
		JButton btnReset = new JButton("�����");
		JButton btnBack = new JButton("�ڷΰ���");
		add(btnSave);
		add(btnUpdate);
		add(btnDelete);
		add(btnSearch);
		add(btnReset);
		add(btnBack);
		
		list = new List();
		add(list);
		Font font2 = new Font("���� ���", 0, 15);
		list.setFont(font2);
		
		l1.setBounds(300, 30, 200, 40); // ���� ���� ������ 
		
		l2.setBounds(350, 100, 70, 30); // ȸ�� ���̵�
		l3.setBounds(530, 100, 70, 30); // ���� ���̵�
		l4.setBounds(350, 130, 70, 30); // ���� �̸�
		l5.setBounds(530, 130, 70, 30); // ���� ����
		
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
		setTitle("�������ȭ��");
		setResizable(false);
		Dimension frameSize = jp.getSize();
		
		// ����� ũ��
		setResizable(false);
		setLocationRelativeTo(jp);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jp.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		//displayAll();
		
		// ����Ʈ ������ ���� �� �ؽ�Ʈ �ʵ�� �� �ֱ�
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
		
		// �űԵ��
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				String str = jcmemberID.getSelectedItem().toString();
				if(str=="��ü") return;
				int memberID = Integer.parseInt(str);
				int reservationID = Integer.parseInt(t2.getText());
				String memberName = t3.getText();
				String nextReserve = t4.getText();
				//String hostName = t5.getText();

				ReservationDao dao = new ReservationDao();
				dao.insertReserve(reservationID, memberID, memberName, nextReserve);
				JOptionPane.showMessageDialog(null, "���� �Ϸ�");
				displayAll(memberID);
				
			}
		});
		
		
		// �����ϱ�
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = jcmemberID.getSelectedItem().toString();
				if(str=="��ü") return;
				int memberID = Integer.parseInt(str);
				int reservationID = Integer.parseInt(t2.getText());
				String nextReserve = t4.getText();
				//String hostName = t5.getText();

				ReservationDao dao = new ReservationDao();
				dao.updateReserve(reservationID, memberID, nextReserve);
				JOptionPane.showMessageDialog(null, "���� �Ϸ�");
				
				displayAll(memberID);
			}
		});
		
		
		// �����ϱ�
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = jcmemberID.getSelectedItem().toString();
				if(str=="��ü") return;
				int memberID = Integer.parseInt(str);
				int reservationID = Integer.parseInt(t2.getText());
				ReservationDao dao = new ReservationDao();
				dao.deleteReserve(memberID,reservationID);
				JOptionPane.showMessageDialog(null, "���� �Ϸ�");
				displayAll(memberID);
			}
		});
		 
		// ��ȸ�ϱ�
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = jcmemberID.getSelectedItem().toString();//t1.getText();
				if(str=="��ü") str ="0";
				int memberID = Integer.parseInt(str);
				ReservationDao dao = new ReservationDao();
				ReservationDto dto = dao.searchMemberName(memberID);
				t3.setText(dto.getMemberName());				//�����̸�
				
				displayAll(memberID);
				
			}
		}); 
		
		// �ڷΰ���(�޴�)
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuPage managerPage = new MenuPage();
            }
        });
		
		// �����
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
	

	// ȸ�����̵� �޺�
    private void selectCombo() {
    	listCombo.clear();
    	listCombo.add("��ü");
    	
    	ReservationDao dao = new ReservationDao();

    	ArrayList<ReservationDto> allData = dao.selectMemberID();
    	for(ReservationDto dto : allData) {
    		int memberID = dto.getMemberID();
    		listCombo.add(memberID+"");
    		//System.out.println(listCombo);
    	}
    	
    }	
	
	// ȭ�� ���
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
