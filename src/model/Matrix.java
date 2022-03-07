/**
 * Copyright(C) 2021 Luvina Software Company
 * Matrix.java, Aug 3, 2021, DuyPN
 */
package model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import filelogic.FileLogic;

/**
 *Lớp để lấy ra các ma trận
 * @author Phạm Ngọc Duy
 */
public class Matrix {

	public File file;
	public List arrayMatrix;
	public FileLogic fileLogic;
	/**
	 * Hàm contructor
	 * 
	 */
	public Matrix(File file, FileLogic fileLogic) {
		this.file = file;
		this.fileLogic = fileLogic;
	}

	/**
	 * @return the arrayMatrix
	 */
	public List getArrayMatrix() {
		try {
			// đọc file
			return fileLogic.readFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayMatrix;
	}

}
