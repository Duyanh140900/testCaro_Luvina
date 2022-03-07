/**
 * Copyright(C) 2021 Luvina Software Company
 * FileLogic.java, Jul 31, 2021, DuyPN
 */
package filelogic;

import java.io.*;
import java.util.*;

/**
 * Lớp chứa các xử lí file
 * 
 * @author Pham Ngoc Duy
 */
public class FileLogic {
	/**
	 * Method dùng để đọc file
	 * 
	 * @param file : file cần đọc
	 * @return result : danh sách ma trận
	 */
	public static List<ArrayList<ArrayList<String>>> readFile(File file) throws IOException {

		ArrayList<ArrayList<String>> listFile = new ArrayList<ArrayList<String>>();
		List<ArrayList<ArrayList<String>>> result = new ArrayList<ArrayList<ArrayList<String>>>();

		if (file.exists()) {// check file tồn tại hay không
			// đọc file
			FileReader fileReader = new FileReader(file);
			// theo dõi số dòng
			LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
			String line;
			// duyệt qua các dòng có trong file
			while ((line = lineNumberReader.readLine()) != null) {
				// khởi tạo mảng chứa các kí tự trên dòng
				ArrayList<String> listLineCharacter = new ArrayList<String>();
				// thêm các kí tự của từng dòng vào từng list
				for (int j = 0; j < line.length(); j++) {
					// thêm các kí tự vào mảng
					listLineCharacter.add(String.valueOf(line.charAt(j)));
				}
				// loại bỏ tất cả khoảng trắng trong dòng
				for (int i = 0; i < listLineCharacter.size(); i++) {
					listLineCharacter.remove("\s");
				}
				// nếu như mảng chưa các kí tự khác mảng chứa kí tự xuống dòng thì thêm vào
				// listFile
				if (listLineCharacter.size() != 0) {
					listFile.add(listLineCharacter);
				}
				// nếu như gặp mảng chứa kí tự xuống dòng thì thêm vào danh sách chứa ma trận
				// cần lấy ra là result
				if (listLineCharacter.size() == 0) {
					result.add(listFile);
					// khởi tạo lại mảng chứa ma trận
					listFile = new ArrayList<ArrayList<String>>();
				}
			}
			// trả về list ma trận trong file
			return result;
		} else {
			// trả về null nếu file không tồn tại
			return null;
		}
	}

//	public static void main(String args[]) throws IOException {
//		List<ArrayList<ArrayList<String>>> theCo = readFile(new File("G:\\CoCaro\\TheCoCaro.txt"));
//		//List a =  layTheCoFile(theCo);
//		System.out.print(theCo.get(0).get(0).get(0));
//
//	}
}
