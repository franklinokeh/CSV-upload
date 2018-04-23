
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Cell;
import jxl.Sheet;
import model.Mobilepaymentusers;


@WebServlet(urlPatterns = {"/ShowServlet"})
public class ShowServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        Sheet sheet = (Sheet)session.getAttribute("sheet");
        LinkedList validlist = new LinkedList();
        LinkedList invalidlist = new LinkedList();

        String accountno = null;
        String fullname = null;
        String addr = null;
        String phone = null;
        String custId = null;
        String dob = null;
        String mfbId = null;

        int column = sheet.getColumns();

        Cell[] MFBID = null;

        Cell[] phoneNumber = sheet.getColumn(0);
        
        for (int i = 0; i < phoneNumber.length; i++) {
            Mobilepaymentusers phonebook = new Mobilepaymentusers();
            boolean isvalid = true;
            if (!phoneNumber[i].getContents().startsWith("Account")) {
                phone = phoneNumber[i].getContents();
                System.out.println("Get me the value: " + phone);
                phonebook.setAccountNumber(phone);
                System.out.println("Extract Number " + phonebook.getAccountNumber());
              
                
                HashMap dataMap = new HashMap(); 
                dataMap.put("Phone", phone);
                
               
                GenericAPIPacket generic = new GenericAPIPacket();
                generic.setAppName("");
                generic.setBaseUrl("");
                generic.setServiceParams(dataMap);
                try {
                    String postTransaction = PostUtil.postTransaction(generic);
                    validlist.add(postTransaction);
                    request.setAttribute("show", validlist);
                    
                    System.out.println("Response " + postTransaction);
                  
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                   
                }
       
    }
        }
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }
    
    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
