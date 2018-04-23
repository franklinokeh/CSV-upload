

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

            htSess = request.getSession();     
            htSess.setAttribute("sheet", sheet);
    
            request.getRequestDispatcher("ShowServlet").forward(request, response);
            
           
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
                
               
                GenericAPIPacket generic = new GenericAPIPacket();
                generic.setAppName(" //APP NAME");
                generic.setBaseUrl("// URL HERE ");
                generic.setServiceParams(dataMap);
                try {
                    String postTransaction = PostUtil.postTransaction(generic);
                 
                    String[] split = postTransaction.split("\\|");
                    
                    System.out.println(split[1]+"|"+split[2]);
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                    
                }
                
            }
            
  
        }
        return validlist;
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
