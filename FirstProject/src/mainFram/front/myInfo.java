package mainFram.front;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dao.MemberDAO;
import com.vo.MemberVO;

public class myInfo extends JPanel {
    private static final long serialVersionUID = 1L;
    private String memberId;
    private JLabel lblNewLabel;

    private JLabel lblModify;
    private JButton modifyCompleteBtn;

	private JPanel contentPane;
	private JLabel lblinfo;
	private JButton joinCompleteBtn;
	private JTextField tfUsername;
	private JTextField tfPassword;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfBdate;
	private JCheckBox maleCheckBox; // 남자 체크박스 추가
	private JCheckBox femaleCheckBox; // 여자 체크박스 추가
	private MemberDAO memberDAO; // MemberDAO 인스턴스 생성
	private MemberVO memberVO; // MemberDAO 인스턴스 생성

    public void setLoginInfo(Map<String, String> loginInfo) {
        this.memberId = loginInfo.get("username");
        System.out.println("마이페이지 로그인정보: " + loginInfo); // 출력문 추가
        if (memberId != null) {
            lblNewLabel.setText("어서오세요, " + memberId + "님!");
        } else {
            lblNewLabel.setText("회원 정보를 불러올 수 없습니다.");
        }
    }

    public myInfo() {
    	start();
        setLayout(null);
        
		
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);

		lblinfo = new JLabel("회원 정보 수정");
		Font f1 = new Font("돋움", Font.BOLD, 20); // 궁서 바탕 돋움
		lblinfo.setFont(f1);
		lblinfo.setBounds(364, 126, 101, 20);
		contentPane.add(lblinfo);

		JLabel label = new JLabel("아이디");
		label.setBounds(258, 195, 69, 20);
		contentPane.add(label);

		JLabel lblUsername = new JLabel("비밀번호");
		lblUsername.setBounds(258, 245, 69, 20);
		contentPane.add(lblUsername);

		JLabel lblName = new JLabel("이름");
		lblName.setBounds(258, 292, 69, 20);
		contentPane.add(lblName);

		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setBounds(258, 339, 69, 20);
		contentPane.add(lblEmail);

		JLabel lblPhone = new JLabel("생년월일");
		lblPhone.setBounds(258, 386, 69, 20);
		contentPane.add(lblPhone);

		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(348, 188, 186, 35);
		contentPane.add(tfUsername);

		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(348, 238, 186, 35);
		contentPane.add(tfPassword);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(348, 285, 186, 35);
		contentPane.add(tfName);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(348, 332, 186, 35);
		contentPane.add(tfEmail);

		tfBdate = new JTextField();
		tfBdate.setColumns(10);
		tfBdate.setBounds(348, 379, 186, 35);
		contentPane.add(tfBdate);

		// 남자 체크박스 생성 및 설정
		maleCheckBox = new JCheckBox("남자");
		maleCheckBox.setBounds(348, 426, 80, 20);
		contentPane.add(maleCheckBox);

		// 여자 체크박스 생성 및 설정
		femaleCheckBox = new JCheckBox("여자");
		femaleCheckBox.setBounds(430, 426, 80, 20);
		contentPane.add(femaleCheckBox);

		joinCompleteBtn = new JButton("회원가입완료");
		joinCompleteBtn.setBounds(395, 472, 139, 29);
		contentPane.add(joinCompleteBtn);

		setVisible(true);
        

        // 회원 정보를 불러와서 텍스트 필드에 기존 정보를 채워줌
        memberDAO = new MemberDAO();
      
            memberVO = memberDAO.getInfo(memberId);
            System.out.println(memberVO.getId());
            tfUsername.setText(memberVO.getId());
            tfPassword.setText(memberVO.getPw());
            tfName.setText(memberVO.getName());
            tfEmail.setText(memberVO.getemail());
            tfBdate.setText(memberVO.getbdate());

            // 성별 체크박스 설정
            int gender = memberVO.getgender();
            if (gender == 1) {
                maleCheckBox.setSelected(true);
            } else if (gender == 2) {
                femaleCheckBox.setSelected(true);
            }
        

        // 텍스트 필드 등 GUI 요소들 추가

        modifyCompleteBtn = new JButton("회원 정보 수정 완료");
        modifyCompleteBtn.setBounds(395, 472, 139, 29);
        add(modifyCompleteBtn);

        modifyCompleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 수정된 정보를 가져옴
                String userid = tfUsername.getText();
                String password = tfPassword.getText();
                String name = tfName.getText();
                String email = tfEmail.getText();
                String bdate = tfBdate.getText();
                char gender;

                if (maleCheckBox.isSelected()) {
                    gender = 'm';
                } else if (femaleCheckBox.isSelected()) {
                    gender = 'f';
                } else {
                    gender = 0;
                }

                memberVO.setId(userid);
                memberVO.setPw(password);
                memberVO.setName(name);
                memberVO.setemail(email);
                memberVO.setbdate(bdate);
                memberVO.setgender((char) gender);

                // 모든 필드가 채워져 있는지 확인
                if (userid.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || bdate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "모든 항목을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // MemberDAO를 사용하여 회원 정보 업데이트
//                try {
//                    String updateStatus = memberDAO.update_m(memberVO);
//                    if (updateStatus.equals("회원 정보 수정 성공!")) {
//                        JOptionPane.showMessageDialog(null, "회원 정보를 수정했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
//                        // 수정이 완료되면 추가 작업 수행
//                    } else {
//                        JOptionPane.showMessageDialog(null, updateStatus, "에러", JOptionPane.ERROR_MESSAGE);
//                    }
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
            }
        });
    }
    private void start() {
		Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
		memberId = loginInfo.get("userid");
	}
    
}
