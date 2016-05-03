package microchat.run;

import microchat.control.InputController;
/**
 * 
 * @author Jonas Praem
 * Runs the program
 */
public class Main {
	public static void main(String[] args) {
		InputController microchat = new InputController();
		microchat.initializeMicrochat();
	}

}
