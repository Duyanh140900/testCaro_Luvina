/**
 * Copyright(C) 2021 Luvina Software Company
 * CaroView.java, Jul 30, 2021, DuyPN
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.*;

/**
 * lớp hiển thị
 * 
 * @author Phạm Ngọc Duy
 */
public class CaroView extends JFrame {
	// khai báo các thuộc tính
	private JPanel mainPanel, panelLayout, panelButton;
	private JButton[][] arrayButton;
	private int[][] arrayInt;
	private boolean[][] arrEnd;
	private JButton buttonReset;
	private boolean turn;
	private int column = 20, row = 20;

	public CaroView() {
		// tạo mảng 2 chiều chứa button có độ lớn là 20x20
		arrayButton = new JButton[column][row];
		arrayInt = new int[20][20];
		// mảng 2 chiều boolean để check xem button đã được đánh hay chưa
		arrEnd = new boolean[20][20];
		// hiển thị giao diện
		// taoGUI();
		taoGUI();
		// set tiêu đề Frame
		setTitle("Game Caro");
		// set size full màn hình
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// tắt phóng to màn hình
		setResizable(false);
		// tắt maxinum
		setLocationRelativeTo(null);
		// tắt chương trình khi đóng cửa sổ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// chạy chương trình
		setVisible(true);
	}

	/**
	 * Hàm tạo giao diện
	 * 
	 */
	public void taoGUI() {
		getContentPane().add(mainPanel = new JPanel());
		// add layout vào panel chính
		mainPanel.add(panelLayout = new JPanel());
		// chia layout cho bàn cơ
		panelLayout.setLayout(new GridLayout(20, 20, 3, 3));
		// thêm các button và set các thuộc tính cho nó
		for (int i = 0; i < arrayButton.length; i++) {
			for (int j = 0; j < arrayButton[i].length; j++) {
				// khởi tạo button bằng các vị trí
				arrayButton[i][j] = new JButton();

				// set size cho các button
				arrayButton[i][j].setPreferredSize(new Dimension(38, 38));
				// set màu cho các button
				arrayButton[i][j].setBackground(Color.white);
				// set font cho text hiển thị trong button
				arrayButton[i][j].setFont(new Font("Times New Roman", Font.BOLD, 38));
				// thêm các button vào layout
				panelLayout.add(arrayButton[i][j]);

			}
		}
		// thêm các button điều khiển game
		mainPanel.add(panelButton = new JPanel(), BorderLayout.SOUTH);
		// set size cho button đó
		panelButton.setPreferredSize(new Dimension(300, 100));
		// thêm button reset vào panelButton
		panelButton.add(buttonReset = new JButton("Reset"));
	}

	/**
	 * @return the arrayButton
	 */
	public JButton[][] getArrayButton() {
		return arrayButton;
	}

	/**
	 * @return the arrayInt
	 */
	public int[][] getArrayInt() {
		return arrayInt;
	}

	/**
	 * @return the arrEnd
	 */
	public boolean[][] getArrEnd() {
		return arrEnd;
	}

	/**
	 * @return the turn
	 */
	public boolean isTurn() {
		return false;
	}

	/**
	 * hàm lắng nghe sự kiện từ chuột khi click vào 1 button
	 */
	public void addMouseListener(MouseListener mouse) {
		for (int i = 0; i < arrayButton.length; i++) {
			for (int k = 0; k < arrayButton[i].length; k++) {
				// thêm sự kiện click cho các button
				arrayButton[i][k].addMouseListener(mouse);
			}
		}

	}
}
