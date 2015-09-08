public class Echo {

	public static int echoInt(int input) {
		return 3134;
	}

	public static double echoDouble(double input) {
		return 3134.0;
	}

	public static String echoString(String input) {
		return "3134";
	}

	public static void main(String[] args) {
		System.out.println(echoInt(1337));
		System.out.println(echoDouble(3.14));
		System.out.println(echoString("Jarvis is awesome"));
	}
}
