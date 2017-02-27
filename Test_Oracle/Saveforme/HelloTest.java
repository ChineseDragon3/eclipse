
public class HelloTest {
public static void main(String[] args) {
	String cell_EJYM = "www.fdty.fudan.edu.cn";
	String oralcle_EJYM ="www.cse.fudan.edu.cn";
	String cellString = "www.cse.fudan.edu.cn";
	if (cell_EJYM.equals(oralcle_EJYM)) {
		System.out.println("cell_EJYM与oralcle_EJYM相同");
	}
	else {
		System.out.println("cell_EJYM与oralcle_EJYM不同");
	}
	if (oralcle_EJYM.equals(cellString)) {
		System.out.println("cell_EJYM与cellString相同");
	}
}
}
