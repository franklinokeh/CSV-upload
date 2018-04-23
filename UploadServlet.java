/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Cell;
import jxl.Sheet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import model.Mobilepaymentusers;

/**
 *
 * @author Shedrach
 */
public class UploadServlet extends HttpServlet {

    private HttpSession htSess;
     private String eBatchName;
     private String bookid;
  
     LinkedList parsedLists = null;
 

  
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        this.htSess = request.getSession();
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = upload.parseRequest(request);
            InputStream is = null;
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                System.out.println("Fieldname are = " + item.getFieldName());
                if (!item.isFormField()) {
                    is = item.getInputStream();
                }
            }
            System.out.println("extracting worksheet");

            System.out.println("extracting worksheet");
            jxl.Workbook w = jxl.Workbook.getWorkbook(is);
            System.out.println("Worksheet extracted");
            Sheet sheet = w.getSheet(0);

//            this.parsedLists = extractSheet(sheet);




            htSess = request.getSession();        //Declare Session 
            htSess.setAttribute("sheet", sheet);
           
            
//request.setAttribute("message", "Uploaded Successfully...");
//response.sendRedirect("http://41.73.252.230:8080/SmartWallet/Proxy/ChangePinSergovia");





            request.getRequestDispatcher("ShowServlet").forward(request, response);
            
            
            
            
            

            /**
             * int ctr = 0; Workbook wb = WorkbookFactory.create(is);
             * org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0); Row
             * row = null; Cell cell = null; boolean isNull = false; do{ try{
             * row = sheet.getRow(ctr); cell = row.getCell(0);
             * System.out.println(cell.toString()); ctr++; } catch(Exception e)
             * { isNull = true; }
             *
             * }while(isNull!=true); is.close();  *
             */
            /**
             * HSSFWorkbook w = new HSSFWorkbook(is);
             * System.out.println("Worksheet extracted"); HSSFSheet sheet =
             * w.getSheet("");
             *
             * Random r = new Random();  *
             */
     // this.parsedLists = extractSheet(sheet);
            //   statt = this.loadData.processCustomers(this.parsedLists);
            //if (statt == 1) {}
           /** String info = "Your contact list has updated successfully!";
            request.setAttribute("StatusInfo", info);
            RequestDispatcher disp = request.getRequestDispatcher("/upload.jsp");
            disp.forward(request, response);  **/
           
         
           
           
           
           
        } catch (IOException ex) {
            ex
                    = ex;
            ex.printStackTrace();
            System.out.println("Excel Formart Error .....");
        } catch (Exception ex) {
            ex
                    = ex;
            ex.printStackTrace();
        } finally {
        }
    }

    
    
    private LinkedList extractSheet(Sheet sheet) {
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
                validlist.add(phonebook);
                
                HashMap dataMap = new HashMap(); 
                dataMap.put("Phone", phone);
                
                //http://41.73.252.230:8080/SmartWallet/Proxy/ChangePinSergovia
                GenericAPIPacket generic = new GenericAPIPacket();
                generic.setAppName("ChangePinSergovia");
                generic.setBaseUrl("http://10.25.10.105:8080/SmartWallet/Proxy/");
                generic.setServiceParams(dataMap);
                try {
                    String postTransaction = PostUtil.postTransaction(generic);
                   // System.out.println("Response " + postTransaction);
                    String[] split = postTransaction.split("\\|");
                    
                    System.out.println(split[1]+"|"+split[2]);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                    //Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
            
            
            

   // LinkedList[] contactLists = new LinkedList[1];
            // contactLists[0] = validlist;
            /* 110:    */ /**
             * private LinkedList extractSheet(Sheet sheet){ LinkedList
             * validlist = new LinkedList(); LinkedList invalidlist = new
             * LinkedList(); String accountno = null; String fullname = null;
             * String addr = null; String phone = null; String dob = null;
             * String mfbId = null;
             *
             * int column = sheet.getColumns();
             *
             * Cell[] fNames = null; Cell[] addrs = null; Cell[] phonenumber =
             * null; Cell[] cusId = null; Cell[] MFBID = null;
             *
             * Cell[] accounts = sheet.getColumn(0); if (column > 1) { fNames =
             * sheet.getColumn(1); } if (column > 2) { addrs =
             * sheet.getColumn(2); } if (column > 3) { phonenumber =
             * sheet.getColumn(3); } if (column > 4) { cusId =
             * sheet.getColumn(4); } if (column > 5) { MFBID =
             * sheet.getColumn(5); } for (int i = 0; i < accounts.length; i++) {
             * Mobilepaymentusers phonebook = new Mobilepaymentusers(); boolean
             * isvalid = true; if
             * (!accounts[i].getContents().startsWith("Account")) { accountno =
             * accounts[i].getContents(); phonebook.setAccountNumber(accountno);
             * System.out.println("Extract Number " +
             * phonebook.getAccountNumber()); validlist.add(phonebook); } }
             * LinkedList[] contactLists = new LinkedList[1]; contactLists[0] =
             * validlist;
             *
             * return validlist; }
             *
             * //
             * <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
             * /** Handles the HTTP <code>GET</code> method.
             *
             * @param request servlet request
             * @param response servlet response
             * @throws ServletException if a servlet-specific error occurs
             * @throws IOException if an I/O error occurs
             */
        }
        return validlist;
    }

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
