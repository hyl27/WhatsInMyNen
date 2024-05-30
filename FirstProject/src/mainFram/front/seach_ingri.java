package mainFram.front;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.dao.IngredientDAO;
import com.dao.menuDAO;
import com.front.main_frame;

public class seach_ingri extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPanel textPanel;
	private String memberId;
	public int nenId;
	private IngredientDAO idao = new IngredientDAO();
	private menuDAO menu = new menuDAO();

	public seach_ingri() {
		initialize();
	}

	private void initialize() {
		start();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNewLabel = new JLabel(memberId+"의 "+nenId+" 냉장고");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 10));
		topPanel.add(lblNewLabel);
		JButton changeNenButton = new JButton("냉장고 변경");
		changeNenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Action for changing refrigerator
			}
		});
		topPanel.add(changeNenButton);
		contentPane.add(topPanel, BorderLayout.NORTH);

		JPanel sayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblNewLabel2 = new JLabel("재료를 추가하세요");
		lblNewLabel2.setFont(new Font("굴림", Font.PLAIN, 20));
		sayPanel.add(lblNewLabel2);
		contentPane.add(sayPanel, BorderLayout.CENTER);

		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textField = new JTextField(20);
		inputPanel.add(textField);
		JButton addButton = new JButton("추가");

		inputPanel.add(addButton);
		JButton searchButton = new JButton("메뉴 검색");
		inputPanel.add(searchButton);

		centerPanel.add(inputPanel, BorderLayout.NORTH);

		JButton selectAllButton = new JButton("전체 선택");
		inputPanel.add(selectAllButton);

		JButton deselectAllButton = new JButton("전체 해제");
		inputPanel.add(deselectAllButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(250, 400)); // Set preferred size here
		textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		scrollPane.setViewportView(textPanel);
		centerPanel.add(scrollPane, BorderLayout.CENTER);

		contentPane.add(centerPanel, BorderLayout.SOUTH);

		add(contentPane);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addIngredient(textField.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				textField.setText("");
			}
		});

		searchButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        List<String> selectedIngredients = new ArrayList<>();
		        for (Component component : textPanel.getComponents()) {
		            if (component instanceof JPanel) {
		                JPanel ingredientPanel = (JPanel) component;
		                JCheckBox checkBox = (JCheckBox) ingredientPanel.getComponent(0);
		                if (checkBox.isSelected()) {
		                    JLabel ingredientLabel = (JLabel) ingredientPanel.getComponent(1);
		                    selectedIngredients.add(ingredientLabel.getText().trim());
		                }
		            }
		        }

				if (!selectedIngredients.isEmpty()) {
					try {
						System.out.println(selectedIngredients);
						List<String> menus = menu.search(selectedIngredients); 
						// <메뉴 찾기> 화면으로 넘어가서 메뉴 이름 리스트패널로 뽑아주기
						LoginInfoManager.getInstance().setMenu(menus);; // 메뉴 이름 가져오기
		                main_frame mainFrame = (main_frame) SwingUtilities.getWindowAncestor(contentPane);
		                JPanel cardPanel = mainFrame.getCardPanel();
		                cardPanel.add(new seach_menu(), "seach_menu");
		                mainFrame.showPanel("seach_menu");
						
						if (menus.isEmpty()) {
							JOptionPane.showMessageDialog(null, "메뉴를 찾을 수 없습니다.");
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "메뉴 검색 중 오류가 발생했습니다.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "재료를 선택하세요.");
				}
				
			}
		});

		selectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleAllCheckBoxes(true);
			}
		});

		deselectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleAllCheckBoxes(false);
			}
		});

		addExistingIngredients();
	}

	private void start() {
		Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
		memberId = loginInfo.get("userid");
		// System.out.println("왜 너가 나와?");
		nenId = LoginInfoManager.getInstance().getNenId();
	}

	// memberId, nenId에 맞는 기존 재료 메서드 띄워기
	private void addExistingIngredients() {
		try {
			List<String> existingIngredients = idao.view_i(memberId, nenId);

			for (String ingredient : existingIngredients) {
				addIngredientToPanel(ingredient);
			}

			contentPane.revalidate();
			contentPane.repaint();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// db에 재료 저장
	private void addIngredient(String ingredientName) throws SQLException {
		if (!ingredientName.isEmpty()) {
			if (!ingredientExistsInPanel(ingredientName)) { // 중복 체크 추가
				addIngredientToPanel(ingredientName);
				idao.insert_i(memberId, nenId, ingredientName);
			} else {
				JOptionPane.showMessageDialog(this, "이미 있는 재료입니다!");
			}
		}
	}

	// 냉장고 패널에서 할 수 있는 일: 
	// 냉장고 내 재료 띄워주기(체크박스&label&x버튼), 재료 수정, 재료 삭제 실제 실행 되는 부분
	private void addIngredientToPanel(String ingredientName) {
		if (ingredientExistsInPanel(ingredientName)) { // 중복 체크 추가
			JOptionPane.showMessageDialog(this, "이미 있는 재료입니다!");
			return;
		}

		JPanel ingredientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JCheckBox checkBox = new JCheckBox();
		ingredientPanel.add(checkBox);

		JLabel ingredientLabel = new JLabel(ingredientName + " ");
		ingredientPanel.add(ingredientLabel);
		
		// 재료 삭제
		JButton deleteButton = new JButton("x");
		deleteButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					removeIngredientFromPanel(ingredientPanel, ingredientName);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		ingredientPanel.add(deleteButton);
		
		// 재료 수정
		ingredientLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Double click to edit ingredient
					String newIngredientName = JOptionPane.showInputDialog("재료 수정", ingredientLabel.getText().trim());
					if (newIngredientName != null && !newIngredientName.isEmpty()) {
						ingredientLabel.setText(newIngredientName + " ");
						try {
							idao.update_i(memberId, nenId, ingredientName, newIngredientName);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		textPanel.add(ingredientPanel);
		textPanel.revalidate();
		textPanel.repaint();
	}

	// 냉장고 내 재료 중복 체크
	private boolean ingredientExistsInPanel(String ingredientName) {
		String trimmedIngredientName = ingredientName.trim();
		for (Component component : textPanel.getComponents()) {
			if (component instanceof JPanel) {
				JPanel ingredientPanel = (JPanel) component;
				JLabel ingredientLabel = (JLabel) ingredientPanel.getComponent(1);
				String existingIngredient = ingredientLabel.getText().trim();
				if (existingIngredient.equals(trimmedIngredientName)) {
					return true;
				}
			}
		}
		return false;
	}

	// db 재료 삭제
	private void removeIngredientFromPanel(JPanel ingredientPanel, String ingredientName) throws SQLException {
		textPanel.remove(ingredientPanel);
		idao.delete_i(memberId, nenId, ingredientName);
		textPanel.revalidate();
		textPanel.repaint();
	}

	private void toggleAllCheckBoxes(boolean selected) {
		for (Component component : textPanel.getComponents()) {
			if (component instanceof JPanel) {
				JPanel ingredientPanel = (JPanel) component;
				JCheckBox checkBox = (JCheckBox) ingredientPanel.getComponent(0);
				checkBox.setSelected(selected);
			}
		}
	}
}
