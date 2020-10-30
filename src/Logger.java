import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;



public aspect Logger{
	pointcut successT() : call(* money*(..) );    
	after() : successT() {
    	try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("log.txt"),true))){
    		Calendar cal = new GregorianCalendar();
    		String fecha = Calendar.HOUR_OF_DAY+":"+Calendar.MINUTE+":"+Calendar.SECOND;
    		if(thisJoinPoint.getSignature().getName().startsWith("moneyWith")) {
    			bw.append("transaccion: "+fecha);
        		System.out.println("Transaction: "+fecha);
    			System.out.println("** Transaccion creada **");	
        	}else {
    			bw.append("deposito: "+fecha);
    			System.out.println("deposito: "+fecha);
        		System.out.println("** Deposito realizado **");
        	} 
    		    		
    		bw.newLine();
    		}catch(Exception e){
    			System.out.println(e.getMessage());
    		}        
    	   	
	}
}
  