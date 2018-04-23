 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author Seun
 */
@WebServlet(urlPatterns = {"/ShowServlet"})
public class ShowServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
    
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
        
        
        
        
        
        
        
        /**
         * if (column > 1) { fNames = sheet.getColumn(1); } if (column > 2) {
         * addrs = sheet.getColumn(2); } if (column > 3) { phonenumber =
         * sheet.getColumn(3); } if (column > 4) { cusId = sheet.getColumn(4); }
         * if (column > 5) { MFBID = sheet.getColumn(5); }  *
         */
        
        
        
        
        
        for (int i = 0; i < phoneNumber.length; i++) {
            Mobilepaymentusers phonebook = new Mobilepaymentusers();
            boolean isvalid = true;
            if (!phoneNumber[i].getContents().startsWith("Account")) {
                phone = phoneNumber[i].getContents();
                System.out.println("Get me the value: " + phone);
                phonebook.setAccountNumber(phone);
                System.out.println("Extract Number " + phonebook.getAccountNumber());
               // validlist.add(phonebook);
                
                HashMap dataMap = new HashMap(); 
                dataMap.put("Phone", phone);
                
                //http://41.73.252.230:8080/SmartWallet/Proxy/ChangePinSergovia
                GenericAPIPacket generic = new GenericAPIPacket();
                generic.setAppName("search");
                generic.setBaseUrl("https://tcapi.phphive.info/1ImlX1d0f8/");
                generic.setServiceParams(dataMap);
                try {
                    String postTransaction = PostUtil.postTransaction(generic);
                    validlist.add(postTransaction);
                    request.setAttribute("show", validlist);
                    
                    System.out.println("Response " + postTransaction);
                   
                   
                   // Create a stream to hold the output
                    
                   
                   
                  //  String[] split = postTransaction.split("\\|");
                    
                   // System.out.println(split[1]+"|"+split[2]);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                    //Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
       
    }
        }
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }
    
    
     

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
