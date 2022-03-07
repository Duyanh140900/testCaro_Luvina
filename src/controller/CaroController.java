/**
 * Copyright(C) 2021 Luvina Software Company
 * CaroController.java, Jul 31, 2021, DuyPN
 */
package controller;

import java.awt.Button;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import filelogic.FileLogic;
import model.Matrix;
import model.Move;
import view.CaroView;

/**
 * Lớp điều khiển game
 * 
 * @author Phạm Ngọc Duy
 */
public class CaroController {

	private CaroView caroView;
	private Move move;

	/**
	 * Hàm khởi tạo Controller
	 * 
	 * @param caroView : đối tượng view
	 */
	public CaroController(CaroView caroView) {
		this.caroView = caroView;

		// lắng nghe sự kiện khi click vào button
		this.caroView.addMouseListener(new CaroListener());
	}

	/**
	 * Lớp lắng nghe sự kiện
	 * 
	 * @author Phạm Ngọc Duy
	 */
	class CaroListener implements MouseListener {

		// lấy dữ liệu từ view
		JButton[][] arrayButton = caroView.getArrayButton();
		boolean[][] arrEnd = caroView.getArrEnd();
		int[][] arrayInt = caroView.getArrayInt();
		boolean turn = caroView.isTurn();

		Matrix matrix = new Matrix(new File("G:\\CoCaro\\TheCoCaro.txt"), new FileLogic());
		List<ArrayList<ArrayList<String>>> danhSachTheCo = matrix.getArrayMatrix();

		/**
		 * Hàm lấy ra thế cờ trong bàn cờ để so sánh
		 * 
		 * @param i           : vị trí dòng
		 * @param j           : vị trí cột
		 * @param arrayButton : mảng button chứa giá trị
		 * @return move lớp vị trí của máy
		 */
		public String[][] layTheCoBanCo(int i, int j, JButton[][] arrayButton) {
			String theCo[][] = new String[5][5];
			int m = 0, n = 0;
			for (int y = i; y <= i + 4; y++) {
				for (int x = j; x <= j + 4; x++) {
					theCo[m][n++] = arrayButton[y][x].getText();
					//System.out.println(theCo[y][x]);
				}
				m++;
				n = 0;
			}

//			for (int y = 0; y<5; y++) {
//				for(int x=0; x<5; x++)
//					System.out.print(theCo[y][x] + " ");
//				System.out.println();
//			}

			return theCo;
		}

		/**
		 * Hàm tìm vị trí cho máy
		 * 
		 * @param arrayButton   : mảng button
		 * @param danhSachTheCo : danh sách thế cờ
		 * @return move lớp vị trí của máy
		 */
		public Move timViTriChoMay(JButton[][] arrayButton, List danhSachTheCo) {
			Move move = new Move();

//			for (int x = 0; x < arrayButton.length; x++) {
//				for (int y = 0; y < arrayButton.length; y++) {
//					if (arrayButton[x][y].getText() == "") {
//						arrayButton[x][y].setText("D");
//					}
//				}
//			}
			int soLuongTheCo = danhSachTheCo.size();

			for (int dem=0; dem<soLuongTheCo; dem++) {
				// Lấy ra từng thế cờ
				List<ArrayList<String>> theCo = (List<ArrayList<String>>) danhSachTheCo.get(dem);
//				for (int v=0; v<5; v++) {
//					for (int b=0; b<5; b++) {
//						System.out.print(theCo.get(v).get(b));
//					}
//					System.out.println();
//				}

				loop_xetbanco: {
					for (int i = 0; i < 16; i++) {

						for (int j = 0; j < 16; j++) {
							int flag = 0;
							// method lấy ra mảng 5*5 trên bàn cờ tính từ vị trí [i][j] i->i+4, j->j+4
							String b[][] = layTheCoBanCo(i, j, arrayButton);


							loop_cong:
							for (int k = 0; k < 5; k++) {
								for (int l = 0; l < 5; l++) {
									if (theCo.get(k).get(l).equals("O")) {
										if (!b[k][l].equals("O")) {
											flag = 0;
											//System.out.println("Break 1");
											break loop_cong;
										}
									}
									if (theCo.get(k).get(l).equals("X")) {
										flag = 0;
										break loop_cong;
									}
									if (theCo.get(k).get(l).equals("D") && !b[k][l].equals("D")) {
										flag = 0;
										break loop_cong;
									}
									if (theCo.get(k).get(l).equals("T")) {
										if (!b[k][l].equals("")) {
											flag = 0;
											//System.out.println("Break 2");
											break loop_cong;
										}
										move.setX(i+k);
										move.setY(j+l);
										flag = 1;

										//System.out.println("--" + i + " " + j + "--" + k + " " + l);
									}
								}
							}

							loop_thu:
							for (int k = 0; k < 5; k++) {
								for (int l = 0; l < 5; l++) {
									if (theCo.get(k).get(l).equals("X")) {
										if (!b[k][l].equals("X")) {
											flag = 0;
											//System.out.println("Break 1");
											break loop_thu;
										}
									}
									if (theCo.get(k).get(l).equals("O")) {
										flag = 0;
										break loop_thu;
									}
									if (theCo.get(k).get(l).equals("D") && !b[k][l].equals("D")) {
										flag = 0;
										break loop_thu;
									}
									if (theCo.get(k).get(l).equals("T")) {
										if (!b[k][l].equals("")) {
											flag = 0;
											//System.out.println("Break 2");
											break loop_thu;
										}
										move.setX(i+k);
										move.setY(j+l);
										flag = 1;

										//System.out.println("--" + i + " " + j + "--" + k + " " + l);
									}
								}
							}


							if (flag==1) {
								System.out.println("Found -" + dem + "--" + i + "-" + j);
								for (int v=0; v<5; v++) {
									for (int q=0; q<5; q++) {
										System.out.print(theCo.get(v).get(q));
									}
									System.out.println();
								}
								return move;
							}
							// Sau khi so sánh 2 mảng, nếu không bị break tức là thế cờ phù hợp

							// Lúc này vị trí máy cần đánh là [x][y]

						}
					}

				}

//				for (int x = 0; x < arrayButton.length; x++) {
//					for (int y = 0; y < arrayButton.length; y++) {
//						if (arrayButton[x][y].getText() == "D") {
//							arrayButton[x][y].setText("");
//						}
//					}
//				}
			}
			// không tìm được trong file
			move.setX((int)((Math.random()) * ((19 - 0) + 1)) + 0);
			move.setY((int)((Math.random()) * ((19 - 0) + 1)) + 0);
			System.out.println("Used Random");
			return move;
		}

		/**
		 * Hàm check thắng trò chơi
		 * 
		 * @param toaDoX : vị trí dòng của button được click
		 * @param toaDoY : vị trí cột của button được click
		 * @param name   : giá trị text của người đánh
		 * @return true nếu thắng, và false nếu thua
		 */
		public boolean win(int toaDoX, int toaDoY, String name) {
			int k, j;
			int dem = 0;
			// kiểm tra chiều dọc
			// ta xét trong khoảng sau và trước nước đánh
			for (k = -4; k <= 4; k++) {
				// nếu các nút thỏa mãn trong bàn cờ
				if (toaDoX + k >= 0 && toaDoX + k < arrayButton.length) {
					// nếu có nút liên tiếp bằng với giá trị của người đánh thì dem cộng thêm 1
					if (arrayButton[toaDoX + k][toaDoY].getText() == name) {
						dem++;
						// nếu đếm nhỏ hơn 5 thì tiếp tục gán đếm =0 và check thế cờ khác
					} else if (dem < 5) {
						dem = 0;
					}
				}
			}
			// nếu đếm >= 5 thì trả về true
			if (dem >= 5) {
				return true;
			} else {
				// nếu không thì gán đếm =0
				dem = 0;
			}
			// kiểm tra chiều ngang
			// ta xét trong khoảng sau và trước nước đánh
			for (k = -4; k <= 4; k++) {
				// nếu các vị trí thỏa mãn trong bàn cờ
				if (toaDoY + k >= 0 && toaDoY + k < arrayButton.length) {
					// nếu có nút liên tiếp bằng với giá trị của người đánh thì dem cộng thêm 1
					if (arrayButton[toaDoX][toaDoY + k].getText() == name) {
						dem++;
						// nếu đếm nhỏ hơn 5 thì tiếp tục gán đếm =0 và check thế cờ khác
					} else if (dem < 5) {
						dem = 0;
					}
				}
			}
			// nếu đếm >= 5 thì trả về true
			if (dem >= 5) {
				return true;
			} else {
				// nếu không thì gán đếm =0
				dem = 0;
			}
			// kiểm tra đường chéo ngược
			// ta xét trong khoảng sau và trước nước đánh và duyệt ngược lại
			for (k = -4, j = 4; k <= 4 && j >= -4; k++, j--) {
				// nếu các vị trí thỏa mãn trong bàn cờ
				if (toaDoY + k >= 0 && toaDoY + k < arrayButton.length && toaDoX + j >= 0
						&& toaDoX + j < arrayButton.length) {
					// nếu có nút liên tiếp bằng với giá trị của người đánh thì dem cộng thêm 1
					if (arrayButton[toaDoX + j][toaDoY + k].getText() == name) {
						dem++;
						// nếu đếm nhỏ hơn 5 thì tiếp tục gán đếm =0 và check thế cờ khác
					} else if (dem < 5) {
						dem = 0;
					}
				}
			}
			// nếu đếm >= 5 thì trả về true
			if (dem >= 5) {
				return true;
			} else {
				// nếu không thì gán đếm =0
				dem = 0;
			}
			// kiểm tra đường chéo xuôi
			// ta xét trong khoảng sau và trước nước đánh
			for (k = -4; k <= 4; k++) {
				// nếu các vị trí thỏa mãn trong bàn cờ
				if (toaDoY + k >= 0 && toaDoY + k < arrayButton.length && toaDoX + k >= 0
						&& toaDoX + k < arrayButton.length) {
					// nếu có nút liên tiếp bằng với giá trị của người đánh thì dem cộng thêm 1
					if (arrayButton[toaDoX + k][toaDoY + k].getText() == name) {
						dem++;
						// nếu đếm nhỏ hơn 5 thì tiếp tục gán đếm =0 và check thế cờ khác
					} else if (dem < 5) {
						dem = 0;
					}
				}
			} // nếu đếm >= 5 thì trả về true
			if (dem >= 5) {
				return true;
			}
			// nếu không thì trả về false
			return false;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// System.out.println(danhSachTheCo.size());
			// duyệt qua tất cả các button
			for (int i = 0; i < arrayButton.length; i++) {
				for (int j = 0; j < arrayButton[i].length; j++) {
					// kiểm tra nếu như click vào button và chưa có giá trị trong button đó
					if (e.getButton() == 1 && e.getSource() == arrayButton[i][j] && turn == false
							&& arrEnd[i][j] == false) {
						// lại giá trị cho button
						arrayButton[i][j].setText("X");
						// margin giá trị của button
						arrayButton[i][j].setMargin(new Insets(0, 0, 0, 0));
						// set màu cho text button
						arrayButton[i][j].setForeground(Color.black);
						arrayInt[i][j] = 1;
						// button đã được đánh
						arrEnd[i][j] = true;
						// đổi sang người khác đánh
						turn = true;
						if (win(i, j, arrayButton[i][j].getText())) {
							arrayButton[i][j].setBackground(Color.red);
							JOptionPane.showMessageDialog(null, "X win!", "Game Over!",
									JOptionPane.INFORMATION_MESSAGE);
							for (int i1 = 0; i1 < arrayButton.length; i1++) {
								for (int j1 = 0; j1 < arrayButton.length; j1++) {
									arrayButton[i1][j1].setText("");
									arrayButton[i1][j1].setBackground(Color.white);
								}
							}
						}
						// System.out.println(win(i, j, arrayButton[i][j].getText()));

					} else if (turn == true) {
						Move point = timViTriChoMay(arrayButton, danhSachTheCo);
						i = (int) point.getX();
						System.out.println("i: " + i);
						j = (int) point.getY();
						System.out.println("i: " + j);
						// lại giá trị cho button
						arrayButton[i][j].setText("O");
						// margin giá trị của button
						arrayButton[i][j].setMargin(new Insets(0, 0, 0, 0));
						// set màu cho text button
						arrayButton[i][j].setForeground(Color.red);
						arrayInt[i][j] = 1;
						// button đã được đánh
						arrEnd[i][j] = true;
						// đổi sang người khác đánh
						turn = false;
						if (win(i, j, arrayButton[i][j].getText())) {
							arrayButton[i][j].setBackground(Color.red);
							JOptionPane.showMessageDialog(null, "O win!", "Game Over!",
									JOptionPane.INFORMATION_MESSAGE);
							for (int i1 = 0; i1 < arrayButton.length; i1++) {
								for (int j1 = 0; j1 < arrayButton.length; j1++) {
									arrayButton[i1][j1].setText("");
									arrayButton[i1][j1].setBackground(Color.white);
								}
							}
						}
					}

				}
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
