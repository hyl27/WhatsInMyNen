package com.controller;

import java.awt.Component;
import java.awt.FlowLayout;
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
import javax.swing.SwingUtilities;

import com.main.LoginInfoManager;
import com.model.dao.IngredientDAO;
import com.model.dao.menuDAO;
import com.view.IngredientView;
import com.view.MenuView;
import com.view.MainFrameView;

public class IngredientController {
	private IngredientView view;
	private IngredientDAO idao;
	private menuDAO menuDAO;
	private String memberId;
	private int nenId;

	public IngredientController(IngredientView view, IngredientDAO idao, menuDAO menuDAO) {
		this.view = view;
		this.idao = idao;
		this.menuDAO = menuDAO;
		initialize();
	}

	private void initialize() {
		start();
		addListeners();
		addExistingIngredients();
	}

	private void start() {
		Map<String, String> loginInfo = LoginInfoManager.getInstance().getLoginInfo();
		memberId = loginInfo.get("userid");
		nenId = LoginInfoManager.getInstance().getNenId();
	}

	private void addListeners() {
		view.getAddButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addIngredient(view.getTextField().getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				view.getTextField().setText("");
			}
		});

		view.getSearchButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMenus();

			}
		});

		view.getSelectAllButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleAllCheckBoxes(true);
			}
		});

		view.getDeselectAllButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleAllCheckBoxes(false);
			}
		});
	}

	private void addExistingIngredients() {
		try {
			List<String> existingIngredients = idao.view_i(memberId, nenId);

			for (String ingredient : existingIngredients) {
				addIngredientToPanel(ingredient);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addIngredient(String ingredientName) throws SQLException {
		if (!ingredientName.isEmpty()) {
			if (!ingredientExistsInPanel(ingredientName)) {
				addIngredientToPanel(ingredientName);
				idao.insert_i(memberId, nenId, ingredientName);
			} else {
				JOptionPane.showMessageDialog(view, "이미 있는 재료입니다!");
			}
		}
	}

	private void addIngredientToPanel(String ingredientName) {
		if (ingredientExistsInPanel(ingredientName)) {
			JOptionPane.showMessageDialog(view, "이미 있는 재료입니다!");
			return;
		}

		JPanel ingredientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JCheckBox checkBox = new JCheckBox();
		ingredientPanel.add(checkBox);
		JLabel ingredientLabel = new JLabel(ingredientName + " ");
		ingredientPanel.add(ingredientLabel);

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

		ingredientLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
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

		view.getTextPanel().add(ingredientPanel);
		view.getTextPanel().revalidate();
		view.getTextPanel().repaint();
	}

	private boolean ingredientExistsInPanel(String ingredientName) {
		String trimmedIngredientName = ingredientName.trim();
		for (Component component : view.getTextPanel().getComponents()) {
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

	private void removeIngredientFromPanel(JPanel ingredientPanel, String ingredientName) throws SQLException {
		view.getTextPanel().remove(ingredientPanel);
		idao.delete_i(memberId, nenId, ingredientName);
		view.getTextPanel().revalidate();
		view.getTextPanel().repaint();
	}

	private void toggleAllCheckBoxes(boolean selected) {
		for (Component component : view.getTextPanel().getComponents()) {
			if (component instanceof JPanel) {
				JPanel ingredientPanel = (JPanel) component;
				JCheckBox checkBox = (JCheckBox) ingredientPanel.getComponent(0);
				checkBox.setSelected(selected);
			}
		}
	}

	private void searchMenus() {
		List<String> selectedIngredients = new ArrayList<>();
		for (Component component : view.getTextPanel().getComponents()) {
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
				List<String> menus = menuDAO.search(selectedIngredients);
				LoginInfoManager.getInstance().setMenu(menus);
				MainFrameView mainFrame = (MainFrameView) SwingUtilities.getWindowAncestor(view);
				MenuView menuview = new MenuView();
				menuDAO menudao = new menuDAO();
				MenuController menuc = new MenuController(menuview, menudao);
				mainFrame.getCardPanel().add(menuview, "MenuView");
				mainFrame.showPanel("MenuView");
				if (menus.isEmpty()) {
					JOptionPane.showMessageDialog(view, "메뉴를 찾을 수 없습니다.");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(view, "메뉴 검색 중 오류가 발생했습니다.");
			}
		} else {
			JOptionPane.showMessageDialog(view, "재료를 선택하세요.");
		}
	}
}
