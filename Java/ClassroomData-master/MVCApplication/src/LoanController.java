

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoanController
 */
@WebServlet("/LoanController")
public class LoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		out.println("Ammount Borrowed: " + request.getParameter("ammountBorrowed"));
		out.println("PaymentFrequency: " + this.finder(request.getParameter("paymentFrequency")+""));
		out.println("Interest Rate: " + request.getParameter("interestRate"));
		out.println("Period: " + request.getParameter("period"));
		out.println("GET");
		
		PersonalLoanCalculator calc = new PersonalLoanCalculator();
		calc.setAmmountBorrowd(Integer.parseInt(request.getParameter("ammountBorrowed")));
		calc.setPaymentFrequency(this.finder(request.getParameter("paymentFrequency")));
		calc.setInterestRate(Double.parseDouble(request.getParameter("interestRate")));
		calc.setTimePeriod(Integer.parseInt(request.getParameter("period")));
		
		out.println("Answer: " + calc.getAnswer());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		out.println("Ammount Borrowed: " + request.getParameter("ammountBorrowed"));
		out.println("PaymentFrequency: " + this.finder(request.getParameter("paymentFrequency")+""));
		out.println("Interest Rate: " + request.getParameter("interestRate"));
		out.println("Period: " + request.getParameter("period"));
		out.println("POST");
		
		PersonalLoanCalculator calc = new PersonalLoanCalculator();
	}
	
	private int finder(String value){
		
		switch(value){
			
		case "Weekly":
			return 52;
		case "Bi-Weekly":
			return 36;
		case "Semi-Monthly":
			return 24;
		case "Monthly":
			return 12;
		default:
			return 0;
		}
		
	}

}
