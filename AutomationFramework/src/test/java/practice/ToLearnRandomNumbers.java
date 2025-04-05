package practice;

import java.util.Random;

public class ToLearnRandomNumbers {

	public static void main(String[] args) {
		Random r=new Random();
		int value = r.nextInt(1000);
		System.out.println(value);
	}
}