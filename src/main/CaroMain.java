/**
 * Copyright(C) 2021 Luvina Software Company
 * CaroMain.java, Jul 31, 2021, DuyPN
 */
package main;

import java.io.File;
import java.util.List;

import controller.CaroController;
import filelogic.FileLogic;
import model.Matrix;
import view.CaroView;

/**
 *
 * @author
 */
public class CaroMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CaroView caroView = new CaroView();
		CaroController caroController = new CaroController(caroView);
	}

}
